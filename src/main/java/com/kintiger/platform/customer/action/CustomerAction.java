package com.kintiger.platform.customer.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.channel.service.IChannelService;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.crmdict.service.ICrmDictService;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.IBatChangeService;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.framework.util.JavaBeanXMLUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.framework.util.XMLInfo;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.sales.pojo.Zwlqy;
import com.kintiger.platform.sales.service.ISalesService;
import com.kintiger.platform.webservice.resps.JsonUtil;
import com.kintiger.platform.wfe.pojo.UserUtil;
import com.kintiger.platform.wfe.service.IWfeService;

public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(CustomerAction.class);
	private Customer customer;
	private String userName;
	private List<CityDict> citydictlist;
	private String parent_city_id;
	private int total;
	@Decode
	private String cityName;
	@Decode
	private String parent_city_name;
	private String citylevel;
	private ICustomerService customerService;
	private ICrmDictService crmDictService; // 数据配置接口
	private String userId;
	private String nextUserId;
	private String eventId;
	private String title;
	private String xmlFilePath;
	private String appUrl;
	private static String key4open = "fix_storesOpen";
	private IWfeService wfeServiceHessian; // 事务接口
	private UserUtil userUtil; // 下个处理人列表
	private CmsTbDict cmsTbDict;
	private List<CmsTbDict> cmsTbDictCustStateList;
	private List<CmsTbDict> cmsTbDictVisitFreqList;
	private List<CmsTbDict> cmsTbDictCustReceiveList;
	private List<CmsTbDict> cmsTbDictVisitDaysList;
	private String customer_state;
	private String customer_receive;
	private BooleanResult executeResult;// 事务处理结束返回信息
	private String subFolders;
	private String channelName;
	private IDictService dictServiceHessian; // 字典接口
	private String custState;
	private String custState1;
	private String custReceive;
	private String stationUserName;
	private String stationUserId;
	private String visitDays;
	private String visitFreq;
	private String custProvince;
	private String custCity;
	private String custDistrict;
	private String custKunnr;
	private IKunnrService kunnrService;
	private IChannelService channelService;
	@Decode
	private String search;

	private List<Customer> customerList;
	private String custNumber;
	@Decode
	private String custName;
	private String channelId;
	private String orgId;
	@Decode
	private String contactName;
	@Decode
	private String node;
	private String orgName;
	private String orgCity;
	private Long custId;

	private String uploadFileFileName;
	private File uploadFile;
	
	private String uploadFileName;
	private File uploadCustFile;
	
	private String ids;
	private IOrgService orgServiceHessian; // 调组织接口

	private List<CrmDict> custSystemList;
	private String type; // T二阶客户，Z终端门店

	private String orgId01;
	private String orgName01;
	private String stationUserName01;
	private String stationUserId01;
	private String custType;
	private String custParent;
	private IBatChangeService batChangeService;
	private List<Zwlqy> zwlqyList;
	private ISalesService salesService;
	private String bhxjFlag;

	private String createOrgId;
	private String createDateStart;
	private String createDateEnd;
	
	private String customerImportance;//门店重要性
	private String customerAnnualSale;//门店销售金额
	
	private String custKunnrId;
	private List<AllUsers> userList;
	private String kunnrUser;
	@Decode
	private String jsonRows;
	@Decode
	private String allChannelName;
	
	
/**
 *  import from csv to chage data 
 *  by zpf
 * @return
 */
	@PermissionSearch
	public String importCustDataToChg() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();

		customerList = new ArrayList<Customer>();
		String rcs = "";
		String rcs2 = "";
		if (StringUtils.isNotEmpty(uploadFileFileName)) {
			String end = StringUtils.substring(uploadFileFileName,
					StringUtils.lastIndexOf(uploadFileFileName, '.'));
			if ((end != null && (end.equals(".csv")))
					|| (end != null && (end.equals(".CSV")))) {
				try {
					String[] header = SuperCSV.getHeaderFromFile(new File(
							uploadFile.toString()));
					List<String[]> content;

					content = SuperCSV.getContentFromFile(new File(uploadFile
							.toString()));
					// 获取String数组

					Customer cust;
					// start

					for (int j = 0; j < content.size(); j++) {
						cust = new Customer();
						// cust.getCustNumber() cust.getCustName()
						// cust.getKunnrUser()
						String[] s = content.get(j);
						if (s.length == header.length) {
							int ij;
							rcs = "";
							if (s.length > 3) {
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							ij = 0;
							String custNumber = s[ij++];
							String custName = s[ij++];
							String kunnrUser = s[ij++];

							if ((null != custNumber) && (null != custName)
									&& (null != kunnrUser)) {
								cust.setCustNumber(custNumber);
								cust.setCustName(custName);
								cust.setKunnrUser(kunnrUser);

								int flag = customerService
										.getCustomerByNumber(cust);

								if (flag == 0) {
									rcs = rcs + "第" + (j + 2)
											+ "行数据   客户编号不存在："
											+ "请不要提交新数据.</br>";
								} else {
									// 跟新数据入库
									flag = customerService
											.updateImportCustomer(cust);
								}

							} else {
								rcs = rcs + "第" + (j + 2) + "行列数不对！</br>";
							}
							rcs2 = rcs2 + rcs;
						}
						
						if (rcs2.trim().length()>0){
							this.setFailMessage(rcs2);
						}else
							this.setSuccessMessage("批量信息修改成功！");
						
					}
					return RESULT_MESSAGE;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}
	
	
	/**
	 * 导出CSV模板 批量更新经销商负责人
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv() {
		OutputStream os = null;
		String report_name = "经销商负责人批量修改导入模板";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("客户编号");
			linedata.append(",");
			linedata.append("客户名称");
			linedata.append(",");
			linedata.append("经销商负责人");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					logger.error(e);
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * MethodsTitle: 终端门店创建
	 * @author: xg.chen modify
	 * @date:2016年11月1日  version1.1
	 * @return
	 */
	@PermissionSearch
	public String toCustomerApply() {
		AllUsers user = this.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		customer = new Customer();
		customer.setCreateUser(user.getUserId());
		customer.setCreateOrgId(user.getOrgId());
		customer.setCreateDate(sdf.format(new Date()));
		userName = user.getUserName();
		cmsTbDict = new CmsTbDict();
		cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
		cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict); // 客户状态
		cmsTbDict.setDictTypeId(Long.valueOf(customer_receive));
		cmsTbDictCustReceiveList = customerService.getCmsTbDictList(cmsTbDict); // 配送方式
		cmsTbDict.setDictTypeId(Long.valueOf(visitFreq));
		cmsTbDictVisitFreqList = customerService.getCmsTbDictList(cmsTbDict); // 拜访频率
		Borg org = orgServiceHessian.getOrgByUserId(user.getUserId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		customer.setOrgId(orgId);
		customer.setOrgName(orgName);
		return "toCustomerApply";
	}

	/**
	 * combobox字典数据下拉
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictVisitDaysList", include = { "itemId",
			"itemValue", "itemName" })
	public String getCmsTbDictList() {
		cmsTbDict = new CmsTbDict();
		cmsTbDict.setDictTypeId(Long.valueOf(visitDays));
		cmsTbDictVisitDaysList = customerService.getCmsTbDictList(cmsTbDict); // 拜访日期
		return JSON;
	}

	/**
	 * 省、市、区 级联查询
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "citydictlist", include = { "dictId", "cityName",
			"cityNumber" })
	public String searchCityDictType() {
		citydictlist = new ArrayList<CityDict>();
		CityDict cityDict = new CityDict();
		if (StringUtils.isNotEmpty(citylevel)
				&& StringUtils.isNotEmpty(citylevel.trim())) {
			try {
				citylevel = new String(getServletRequest().getParameter(
						"citylevel").getBytes("ISO8859-1"), "UTF-8");
				cityDict.setCitylevel(citylevel);
				if (StringUtils.isNotEmpty(parent_city_id)
						&& StringUtils.isNotEmpty(parent_city_id.trim())) {
					parent_city_id = new String(getServletRequest()
							.getParameter("parent_city_id").getBytes(
									"ISO8859-1"), "UTF-8");
					cityDict.setParent_city_id(parent_city_id);
				}

				citydictlist = customerService.getCityList(cityDict);
			} catch (UnsupportedEncodingException e) {
				e.getMessage();
			}
		}
		return JSON;
	}

	/**
	 * 启动选择下个处理人
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectNexUser() {
		Object[] res = new Object[4];
		res[0] = key4open;
		res[3] = appUrl + "/customerAction!customerOpen.jspa";
		res[1] = userId;
		res[2] = "executeAction";
		userUtil = wfeServiceHessian.startWorkflowFix(res);
		return JSON;
	}

	/**
	 * 取消下个处理人
	 * 
	 * @return
	 */
	@PermissionSearch
	public String cancelNextUser() {
		try {
			wfeServiceHessian.cancelEvent(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		this.setSuccessMessage("ok");
		return RESULT_MESSAGE;
	}

	/**
	 * 提交终端门店开户申请
	 * 
	 * @return
	 */
	public String customerApply() {
		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = userId;
		res[2] = nextUserId;
		res[3] = title;
		res[4] = appUrl + "/customerAction!customerFormView.jspa";
		res[5] = key4open;
		res[6] = "";
		try {
			String result = wfeServiceHessian.processWorkflowFix(res);
			if ("success".endsWith(result)) {
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", customer, getUser().getUserId(), getUser()
						.getUserName(), null)) {
					this.setFailMessage("信息写入XML文件出错,请重试");
				} else {
					this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
				}
			} else {
				this.setFailMessage("事务启动失败,请重试");
			}
		} catch (Exception e) {
			wfeServiceHessian.cancelEvent(eventId);
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 开户流程中查看页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String customerFormView() {
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new Customer());
			if (info != null) {
				customer = (Customer) info.getObject();
				if (customer.getChannelId() != null
						&& !"".equals(customer.getChannelId())) {
					channelName = customerService.getChannelName(customer
							.getChannelId());
				}
				if (customer.getCustState() != null
						&& !"".equals(customer.getCustState())) {
					custState = dictServiceHessian.getItemByItemId(
							Long.valueOf(customer.getCustState()))
							.getItemName();
				}
				if (customer.getCustReceive() != null
						&& !"".equals(customer.getCustReceive())) {
					custReceive = dictServiceHessian.getItemByItemId(
							Long.valueOf(customer.getCustReceive()))
							.getItemName();
				}
				if (customer.getStationUserId() != null
						&& !"".equals(customer.getStationUserId())) {
					stationUserName = customerService
							.getStationUserNameById(customer.getStationUserId());
				}
				if (customer.getVisitDays() != null
						&& !"".equals(customer.getVisitDays())) {
					visitDays = dictServiceHessian.getItemByItemId(
							Long.valueOf(customer.getVisitDays()))
							.getItemName();
				}
				if (customer.getVisitFreq() != null
						&& !"".equals(customer.getVisitFreq())) {
					visitFreq = dictServiceHessian.getItemByItemId(
							Long.valueOf(customer.getVisitFreq()))
							.getItemName();
				}
				if (customer.getCustProvince() != null
						&& !"".equals(customer.getCustProvince())) {
					custProvince = customerService.getCustomerProvince(customer
							.getCustProvince().split(",")[0]);
					custCity = customerService.getCustomerProvince(customer
							.getCustProvince().split(",")[1]);
					custDistrict = customerService.getCustomerProvince(customer
							.getCustProvince().split(",")[2]);
				}
				if (customer.getCustKunnr() != null
						&& !"".equals(customer.getCustKunnr())) {
					Kunnr kunnr = new Kunnr();
					kunnr.setKunnr(customer.getCustKunnr());
					custKunnr = kunnrService.getKunnrEntity(kunnr).getName1();
				}
			}
		}
		return "customerFormView";
	}

	/**
	 * 事务完成 写入数据库
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String customerOpen() {
		BooleanResult result = null;
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new Customer());
			if (info != null) {
				customer = (Customer) info.getObject();
				if (customer.getCustReceive() != null
						&& !"".equals(customer.getCustReceive())) {
					customer.setCustReceive(Integer.valueOf(dictServiceHessian
							.getItemByItemId(
									Long.valueOf(customer.getCustReceive()))
							.getItemValue()));
				}
				if (customer.getCustProvince() != null
						&& !"".equals(customer.getCustProvince())) {
					custProvince = customerService.getCustomerProvince(customer
							.getCustProvince().split(",")[0]);
					CmsTbDictType cmsTbDictType = new CmsTbDictType();
					cmsTbDictType.setDictTypeName("省份首字母简称");
					List<CmsTbDict> cmList = new ArrayList<CmsTbDict>();
					cmList = dictServiceHessian
							.getDictListByDictType(cmsTbDictType);
					Long customerNumberId = customerService
							.getCustomerNumberId();
					for (CmsTbDict CmsTbDict2 : cmList) {
						if (custProvince.equals(CmsTbDict2.getItemName())) {
							customer.setCustNumber(CmsTbDict2.getItemValue()
									+ customerNumberId);
							break;
						}
					}
				}
				try {
					result = customerService.customerOpen(customer);
				} catch (Exception e) {
					logger.error(e);
					result.setResult(false);
					result.setCode(result.getCode() + "\n"
							+ "数据保存数据库失败.请联系系统管理员");
				}
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	/**
	 * 客户信息审批改为线下审批
	 * 
	 * @return
	 */
	public String createCustomer() {
		BooleanResult result = null;
		customer = customer;
		if (StringUtils.isNotEmpty(customer.getVillages())
				&& StringUtils.isNotEmpty(customer.getVillages().trim())) {
			customer.setCustProvince(customer.getVillages());
		} else if (StringUtils.isNotEmpty(customer.getCustDistrict())
				&& StringUtils.isNotEmpty(customer.getCustDistrict().trim())) {
			customer.setCustProvince(customer.getCustDistrict());
		} else if (StringUtils.isNotEmpty(customer.getCustCity())
				&& StringUtils.isNotEmpty(customer.getCustCity().trim())) {
			customer.setCustProvince(customer.getCustCity());

		} else if (StringUtils.isNotEmpty(customer.getCustProvince())
				&& StringUtils.isNotEmpty(customer.getCustProvince().trim())) {
			customer.setCustProvince(customer.getCustProvince());

		}
		/*
		 * String[] proStr=customer.getCustProvince().split(",");
		 * customer.setCustProvince(proStr[proStr.length-1]);
		 */
		if (StringUtils.isNotEmpty(customer.getCustKunnr())
				&& StringUtils.isNotEmpty(customer.getCustKunnr().trim())) {
			customer.setCustKunnr(customer.getCustKunnr().trim());
		}
		if(StringUtils.isNotEmpty(customer.getCustomerImportance())){
			customer.setCustomerImportance(customer.getCustomerImportance());
		}
		if(StringUtils.isNotEmpty(customer.getCustomerAnnualSale())) {
			customer.setCustomerAnnualSale(customer.getCustomerAnnualSale());
		}
		int n = 0;
		n = customerService.getCustomerByName(customer);
		if (n > 0) {
			this.setFailMessage("该客户名称在您选择的城市中已存在，请在名称后面加上较明确的地址信息重试！");
			return RESULT_MESSAGE;
		}
		if ("T".equals(type)) {
			customer.setCustType("T");
		} else {
			customer.setCustType("Z");
		}
		AllUsers user = this.getUser();
		customer.setModifyUser(user.getUserName());
		if(null!=customer.getCustName()){
        	String custNameStr=customer.getCustName().replace(" ", "");
        	customer.setCustName(custNameStr);
        }
		result = customerService.customerOpen(customer);
		if (result.getResult() == true) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 关闭客户
	 * 
	 * @return
	 */
	public String closeCustomer() {
		String[] l = ids.split(",");
		customer = new Customer();
		customer.setCodes(l);
		customer.setCustState(Integer.valueOf(custState1));
		AllUsers user = this.getUser();
		customer.setModifyUser(user.getUserName());
		int rs = 0;
		rs = customerService.closeCustomer(customer);
		if (rs > 0) {
			this.setSuccessMessage("操作成功！");
		} else {
			this.setFailMessage("操作失败！");
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 *  保存 经销商负责人更改数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveChagKunnrUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
	
		if (StringUtils.isNotEmpty(jsonRows)){
			
			customerList = JsonUtil.getDTOList(jsonRows, Customer.class);
			
			BooleanResult booleanResult =customerService.saveChagKunnrUser(customerList);
			if (!booleanResult.getResult()) {
				this.setFailMessage(booleanResult.getCode());
			}else
				this.setSuccessMessage(booleanResult.getCode());
			
		}else 
			this.setFailMessage("没有保存数据");
		
	
		/*
		if (rs > 0) {
			this.setSuccessMessage("操作成功！");
		} else {
			this.setFailMessage("操作失败！");
		}
		*/
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toCutomerSearch() {
		AllUsers user = this.getUser();
		userId = user.getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		cmsTbDict = new CmsTbDict();
		cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
		cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);
		return "toCutomerSearch";
	}
	
	@PermissionSearch
	public String toCutomerSearchKunnr() {
		AllUsers user = this.getUser();
		userId = user.getUserId();
		custKunnr = customerService.getKunnrByEmpId(userId);
		cmsTbDict = new CmsTbDict();
		cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
		cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);
		return "toCutomerSearchKunnr";
	}

	/**
	 * MethodsTitle:终端客户信息查询 
	 * @author: xg.chen modify
	 * @date:2016年11月1日  version 1.1
	 * @return JSON
	 * 新增两个字段：
	 * customerImportance
	 * customerAnnualSale
	 */
	@PermissionSearch
	@JsonResult(field = "customerList", include = { "custId", "custNumber",
			"custName", "channelName", "stationUserId", "contactName",
			"stationName", "contactMobile", "custProvince", "custHouserNumber",
			"custStateName", "custState", "visitFreqName", "visitFreq",
			"visitDays", "custAddress", "corporateDeputy", "createDate",
			"custKunnrName", "custTown", "custReceiveName", "custReceive",
			"empName", "lastModify", "modifyUser", "remark", "custCity",
			"custDistrict", "custTown1", "custStreet", "custSystem","kunnrUser","custKunnr",
			"custParent", "custType","orgId","orgName","longitude","latitude",
			"customerImportance","customerAnnualSale"}, total = "total")
	public String customerSearch() {
		Customer customer = new Customer();
		customer.setStart(getStart());
		customer.setEnd(getEnd());
		if (StringUtils.isNotEmpty(custNumber)) {
			customer.setCustNumber(custNumber);
		}
		if (StringUtils.isNotEmpty(custName)
				&& StringUtils.isNotEmpty(custName.trim())) {
			customer.setCustName(custName);
		}
		if (StringUtils.isNotEmpty(channelId)) {
			customer.setChannelId(Integer.valueOf(channelId));
		}
		if (StringUtils.isNotEmpty(orgId)) {
			customer.setOrgId(orgId);
		} else {
			AllUsers user = this.getUser();
			customer.setOrgId(user.getOrgId());
		}
		if (StringUtils.isNotEmpty(custState)) {
			customer.setCustState(Integer.valueOf(custState));
		}
		
		if (StringUtils.isNotEmpty(allChannelName)) {
			customer.setAllChannelName(allChannelName);
		}
		if (StringUtils.isNotEmpty(contactName)
				&& StringUtils.isNotEmpty(contactName.trim())) {
			customer.setContactName(contactName);
		}
		if (StringUtils.isNotEmpty(stationUserId)
				&& StringUtils.isNotEmpty(stationUserId.trim())) {
			try {
				stationUserId = new String(stationUserId.getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			customer.setStationUserId(stationUserId);
		}
		if (StringUtils.isNotEmpty(custKunnr)
				&& StringUtils.isNotEmpty(custKunnr.trim())) {
			try {
				custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			customer.setCustKunnr(custKunnr);
		}
		if (StringUtils.isNotEmpty(custParent)
				&& StringUtils.isNotEmpty(custParent.trim())) {
			customer.setCustParent(custParent);
		}
		if (StringUtils.isNotEmpty(custType)
				&& StringUtils.isNotEmpty(custType.trim())) {
			customer.setCustType(custType);
		}
		if (StringUtils.isNotEmpty(createOrgId)) {
			customer.setCreateOrgId(createOrgId);
		} 
		if (StringUtils.isNotEmpty(createDateStart)) {
			customer.setCreateDateStart(createDateStart);
		} 
		if (StringUtils.isNotEmpty(createDateEnd)) {
			customer.setCreateDateEnd(createDateEnd);
		}
		if (StringUtils.isNotEmpty(customerImportance)) {
			try {
				customerImportance=URLDecoder.decode(customerImportance,"UTF-8");
				customer.setCustomerImportance(customerImportance);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		total = customerService.getCustomerListCount(customer);
		if (total != 0) {
			customerList = customerService.getCustomerList(customer);
			for (int i = 0; i < customerList.size(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				Customer cust = new Customer();
				cust = customerList.get(i);
				String str = cust.getVisitDays();
				if (null != str && !"".equals(str)) {
					str = str.replace("0", "星期日").replace("1", "星期一")
							.replace("2", "星期二").replace("3", "星期三")
							.replace("4", "星期四").replace("5", "星期五")
							.replace("6", "星期六").replace("", "");
					cust.setVisitDays(str);
				}
				String[] l = new String[] {};
				String kunnrName = "";
				if (StringUtils.isNotEmpty(cust.getCustKunnr())
						&& StringUtils.isNotEmpty(cust.getCustKunnr().trim())) {
					l = cust.getCustKunnr().split(",");      // 门店获取上游经销商编号，可能双开
					for (int j = 0; j < l.length; j++) {
						Kunnr kn = new Kunnr();
						kn.setKunnr(l[j].trim());
						list.add(l[j].trim());
						kn = customerService.getKunnrByKunnrId(kn);
						if (kn != null) {
							if (!"".equals(kunnrName)) {
								kunnrName = kunnrName + "," + kn.getName1();
							} else {
								kunnrName = kn.getName1();
							}
						}
					}

				}
				cust.setCustKunnrName(kunnrName);

				// 拼行政区划字符
				Zwlqy d = new Zwlqy();
				String strp = "";
				Zwlqy d1 = new Zwlqy();
				d1.setZwl04(cust.getCustProvince());
				d = customerService.getCustomerXZArea(d1);
				if (null != d) {
					if (StringUtils.isNotEmpty(d.getZwl01())
							&& StringUtils.isNotEmpty(d.getZwl01().trim())) {
						strp = d.getZwl01t();
					}
					if (StringUtils.isNotEmpty(d.getZwl02())
							&& StringUtils.isNotEmpty(d.getZwl02().trim())) {
						strp = strp + "|" + d.getZwl02t();
					}
					if (StringUtils.isNotEmpty(d.getZwl03())
							&& StringUtils.isNotEmpty(d.getZwl03().trim())) {
						strp = strp + "|" + d.getZwl03t();
					}
					if (StringUtils.isNotEmpty(d.getZwl04())
							&& StringUtils.isNotEmpty(d.getZwl04().trim())) {
						strp = strp + "|" + d.getZwl04t();
					}
				}
				cust.setCustProvince(strp);
				customerList.set(i, cust);
				if (StringUtils.isNotEmpty(custKunnr)
						&& StringUtils.isNotEmpty(custKunnr.trim())) {
					if (!list.contains(custKunnr)) {
						customerList.remove(i);
					}
				}
			}
			
			/*if (StringUtils.isNotEmpty(custKunnr)
					&& StringUtils.isNotEmpty(custKunnr.trim())) {
				total = customerList.size();
			}*/
		}
		return JSON;
	}
	
	// zpf 经销商负责人选择
	public String selectUpKunnrUser(){
//		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
		//getUserOrgTreeByAjax
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		orgCity=borg.getOrgCity();
		return  "selectUpKunnrUser";
	}

	// 组织树
	public String orgTreePage() {
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		if("B".equals(borg.getOrgCity())){
			return "orgAllTree";
		}
		return "orgtree";
	}
	
	/**
	 *  zpf 根据经销商ID，获取经销商负责人列表
	 */
	@PermissionSearch
	@JsonResult(field = "userList", include = { "userId", "userName","orgName"}, total = "total")
	public String getCustKunnrById() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		userList = null;
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(orgName)) {
			allUser.setOrgName(orgName);
		}
	
		if ("A".equals(getUser().getCustType())) {
			allUser.setOrgId(getUser().getOrgId());
			allUser.setIsOffice(getUser().getIsOffice());
		} else if ("admin".equals(getUser().getLoginId())) {

		} else {
			allUser.setOrgId(getUser().getOrgId());
		}
		/*
		if (StringUtils.isNotEmpty(custKunnrId)) {
			allUser;
		}
		if (StringUtils.isNotEmpty(orgName)) {
			allUser.setOrgName(orgName);
		}
		*/
		// allUser.setOrgId(getUser().getOrgId());
		total = customerService.getCustKunnrByIdCount(allUser); 
		if (total != 0) {
			userList = customerService.getCustKunnrById(allUser);
		} else {
			userList = null;
		}
		return JSON;

	}

	/**
	 * 浏览终端门店信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String viewCustomer() {
		customer = new Customer();
		customer.setCustNumber(String.valueOf(custId));
		customer = customerService.getCustomerObjectByCustId(customer);
		if (StringUtils.isNotEmpty(customer.getCustKunnr())
				&& StringUtils.isNotEmpty(customer.getCustKunnr().trim())) {
			String[] l = customer.getCustKunnr().split(",");
			String kunnr_str = "";
			for (int i = 0; i < l.length; i++) {
				String str = l[i];
				Kunnr k = new Kunnr();
				k.setKunnr(str);
				k = customerService.getKunnrByKunnrId(k);
				if ("".equals(kunnr_str) && k != null) {
					kunnr_str = k.getName1();
				} else {
					kunnr_str = kunnr_str + "," + k.getName1();
				}
			}
			customer.setCustKunnrName(kunnr_str);
		}
		return "viewCustomer";
	}

	/**
	 * 修改门店信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateCustomer() {
		AllUsers user = this.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		customer = new Customer();
		customer.setCreateUser(user.getUserId());
		userName = user.getUserName();
		cmsTbDict = new CmsTbDict();
		cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
		cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);
		cmsTbDict.setDictTypeId(Long.valueOf(customer_receive));
		cmsTbDictCustReceiveList = customerService.getCmsTbDictList(cmsTbDict);
		cmsTbDict.setDictTypeId(Long.valueOf(visitFreq));
		cmsTbDictVisitFreqList = customerService.getCmsTbDictList(cmsTbDict);
	/*	cmsTbDict.setDictTypeId(Long.valueOf(visitDays));
		cmsTbDictVisitDaysList = customerService.getCmsTbDictList(cmsTbDict);*/
		customer.setCustId(custId);
		customer = customerService.getCustomerObjectByCustId(customer);
		if(StringUtils.isNotEmpty(customer.getCustomerImportance())){
			customer.setCustomerImportance(customer.getCustomerImportance());
		}
		if (StringUtils.isNotEmpty(customer.getCustomerAnnualSale())) {
			customer.setCustomerAnnualSale(customer.getCustomerAnnualSale());
		}
		Zwlqy d = new Zwlqy();
		Zwlqy d1 = new Zwlqy();
		d1.setZwl04(customer.getCustProvince());
		d = customerService.getCustomerXZArea(d1);
		if (StringUtils.isNotEmpty(d.getZwl01())
				&& StringUtils.isNotEmpty(d.getZwl01().trim())) {
			customer.setCustProvince(d.getZwl01());
		}
		if (StringUtils.isNotEmpty(d.getZwl02())
				&& StringUtils.isNotEmpty(d.getZwl02().trim())) {
			customer.setCustCity(d.getZwl02());
		}
		if (StringUtils.isNotEmpty(d.getZwl03())
				&& StringUtils.isNotEmpty(d.getZwl03().trim())) {
			customer.setCustDistrict(d.getZwl03());
		}
		if (StringUtils.isNotEmpty(d.getZwl04())
				&& StringUtils.isNotEmpty(d.getZwl04().trim())) {
			customer.setVillages(d.getZwl04());
		}
		if (StringUtils.isNotEmpty(customer.getCustKunnr())
				&& StringUtils.isNotEmpty(customer.getCustKunnr().trim())) {
			customer.setCustKunnr(customer.getCustKunnr().trim());
			String [] str=customer.getCustKunnr().trim().split(", ");
			String kunnrName="";
			for (int i = 0; i < str.length; i++) {
				Kunnr kn = new Kunnr();
				kn.setKunnr(str[i].trim());
				kn = customerService.getKunnrByKunnrId(kn);
				if (kn != null) {
					if (!"".equals(kunnrName)) {
						kunnrName = kunnrName + "," + kn.getName1();
					} else {
						kunnrName = kn.getName1();
					}
				}
			}
			customer.setCustKunnrName(kunnrName);
		}
		if (StringUtils.isNotEmpty(customer.getCustParent())
				&& StringUtils.isNotEmpty(customer.getCustParent().trim())) {
			customer.setCustParent(customer.getCustParent().trim());
			String [] str=customer.getCustParent().trim().split(", ");
			String custName="";
			for (int i = 0; i < str.length; i++) {
				Customer c =new Customer();
				c.setCustId(Long.valueOf(str[i].trim()));
				c = customerService.getCustomerObjectByCustId(c);
				if (c != null) {
					if (!"".equals(custName)) {
						custName = custName + "," + c.getCustName();
					} else {
						custName = c.getCustName();
					}
				}
			}
			customer.setCustParentName(custName);
		}
		return "toUpdateCustomer";
	}

	/**
	 * MethodsTitle: 修改终端门店信息
	 * @author: xg.chen modify
	 * @date:2016年11月2日  version1.1
	 * @return
	 */
	public String updateCustomer() {
		this.setSuccessMessage("");
		BooleanResult result = null;
		customer = customer;
		if (StringUtils.isNotEmpty(customer.getVillages())
				&& StringUtils.isNotEmpty(customer.getVillages().trim())) {
			customer.setCustProvince(customer.getVillages());
		} else if (StringUtils.isNotEmpty(customer.getCustDistrict())
				&& StringUtils.isNotEmpty(customer.getCustDistrict().trim())) {
			customer.setCustProvince(customer.getCustDistrict());
		} else if (StringUtils.isNotEmpty(customer.getCustCity())
				&& StringUtils.isNotEmpty(customer.getCustCity().trim())) {
			customer.setCustProvince(customer.getCustCity());

		} else if (StringUtils.isNotEmpty(customer.getCustProvince())
				&& StringUtils.isNotEmpty(customer.getCustProvince().trim())) {
			customer.setCustProvince(customer.getCustProvince());

		}
		if (StringUtils.isNotEmpty(customer.getCustKunnr())
				&& StringUtils.isNotEmpty(customer.getCustKunnr().trim())) {
			customer.setCustKunnr(customer.getCustKunnr().trim());
		}
		AllUsers user = this.getUser();
		customer.setModifyUser(user.getUserName());
		if(null!=customer.getCustName()){
        	String custNameStr=customer.getCustName().replace(" ", "");
        	customer.setCustName(custNameStr);
        }
		if (StringUtils.isNotEmpty(customer.getCustomerImportance())) {
			customer.setCustomerImportance(customer.getCustomerImportance());
		}
		if (StringUtils.isNotEmpty(customer.getCustomerAnnualSale())) {
			customer.setCustomerAnnualSale(customer.getCustomerAnnualSale());
		}
		result = customerService.updateCustomer(customer);

		if (result.getResult() == true) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 客户信息导出 
	 * @author: xg.chen
	 * @date:2016年11月7日  导出数据优化 version1.1
	 * @return
	 */
	public String customerExport() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		customer = new Customer();
		customer.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(custNumber)) {
				customer.setCustNumber(custNumber);
			}
			if (StringUtils.isNotEmpty(custName)
					&& StringUtils.isNotEmpty(custName.trim())) {
				customer.setCustName(custName);
			}
			if (StringUtils.isNotEmpty(channelId)) {
				customer.setChannelId(Integer.valueOf(channelId));
			}
			if (StringUtils.isNotEmpty(orgId)) {
				customer.setOrgId(orgId);
			} else {
				AllUsers user = this.getUser();
				customer.setOrgId(user.getOrgId());
			}
			if (StringUtils.isNotEmpty(custState)) {
				customer.setCustState(Integer.valueOf(custState));
			}
			if (StringUtils.isNotEmpty(contactName)
					&& StringUtils.isNotEmpty(contactName.trim())) {
				customer.setContactName(contactName);
			}
			if (StringUtils.isNotEmpty(stationUserId)
					&& StringUtils.isNotEmpty(stationUserId.trim())) {
				try {
					stationUserId = new String(
							stationUserId.getBytes("ISO8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
				customer.setStationUserId(stationUserId);
			}
			if (StringUtils.isNotEmpty(custKunnr)
					&& StringUtils.isNotEmpty(custKunnr.trim())) {
				try {
					custKunnr = new String(custKunnr.getBytes("ISO8859-1"),
							"UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
				customer.setCustKunnr(custKunnr);
			}
			if (StringUtils.isNotEmpty(custParent)
					&& StringUtils.isNotEmpty(custParent.trim())) {
				customer.setCustParent(custParent);
			}
			if (StringUtils.isNotEmpty(custType)
					&& StringUtils.isNotEmpty(custType.trim())) {
				customer.setCustType(custType);
			}
			if (StringUtils.isNotEmpty(createOrgId)) {
				customer.setCreateOrgId(createOrgId);
			} 
			if (StringUtils.isNotEmpty(createDateStart)) {
				customer.setCreateDateStart(createDateStart);
			} 
			if (StringUtils.isNotEmpty(createDateEnd)) {
				customer.setCreateDateEnd(createDateEnd);
			}
			if (StringUtils.isNotEmpty(customerImportance)) {
				try {
					customerImportance=URLDecoder.decode(customerImportance,"UTF-8");
					customer.setCustomerImportance(customerImportance);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			customerList = customerService.getCustomerList(customer);
			if (null != customerList || customerList.size() != 0) {
				for (int i = 0; i < customerList.size(); i++) {
					ArrayList<String> list = new ArrayList<String>();
					Customer cust = new Customer();
					cust = customerList.get(i);
					String[] l = new String[] {};
					String kunnrName = "";
					if (StringUtils.isNotEmpty(cust.getCustKunnr())
							&& StringUtils.isNotEmpty(cust.getCustKunnr()
									.trim())) {
						l = cust.getCustKunnr().split(",");
						for (int j = 0; j < l.length; j++) {
							Kunnr kn = new Kunnr();
							kn.setKunnr(l[j].trim());
							list.add(l[j].trim());
							kn = customerService.getKunnrByKunnrId(kn);
							if (kn != null) {
								if (!"".equals(kunnrName)) {
									kunnrName = kunnrName + "," + kn.getName1();
								} else {
									kunnrName = kn.getName1();
								}
							}
						}

					}
					cust.setCustKunnrName(kunnrName);
					Zwlqy d = new Zwlqy();
					Zwlqy d1 = new Zwlqy();
					d1.setZwl04(cust.getCustProvince());
					d = customerService.getCustomerXZArea(d1);
					if (null != d) {
						if (StringUtils.isNotEmpty(d.getZwl01())
								&& StringUtils.isNotEmpty(d.getZwl01().trim())) {
							cust.setCustProvince(d.getZwl01t());
						}
						if (StringUtils.isNotEmpty(d.getZwl02())
								&& StringUtils.isNotEmpty(d.getZwl02().trim())) {
							cust.setCustCity(d.getZwl02t());
						}
						if (StringUtils.isNotEmpty(d.getZwl03())
								&& StringUtils.isNotEmpty(d.getZwl03().trim())) {
							cust.setCustDistrict(d.getZwl03t());
						}
						if (StringUtils.isNotEmpty(d.getZwl04())
								&& StringUtils.isNotEmpty(d.getZwl04().trim())) {
							cust.setVillages(d.getZwl04t());
						}
					}
					if(null!=cust.getCustState()){
					cust.setCustStateName(String.valueOf(cust.getCustState())
							.replace("0", "新客户").replace("1", "正常")
							.replace("2", "间歇").replace("3", "冻结")
							.replace("4", "已关户"));}
					if(null!=cust.getVisitDays()){
					cust.setVisitDaysName(cust.getVisitDays()
							.replace("1", "星期一").replace("2", "星期二")
							.replace("3", "星期三").replace("4", "星期四")
							.replace("5", "星期五").replace("6", "星期六")
							.replace("0", "星期日").replace("", ""));}
					if(null!=cust.getVisitFreq()){
					cust.setVisitFreqName(String.valueOf(cust.getVisitFreq())
							.replace("1", "一周一次").replace("2", "一周两次")
							.replace("3", "一周三次").replace("4", "两周一次").replace("", ""));}
					if(null!=cust.getCustReceive()){
					cust.setCustReceiveName(String
							.valueOf(cust.getCustReceive())
							.replace("0", "经销商配送").replace("1", "二阶配送")
							.replace("2", "总仓配送").replace("", "")
							.replace("", ""));}
					customerList.set(i, cust);
					if (StringUtils.isNotEmpty(custKunnr)
							&& StringUtils.isNotEmpty(custKunnr.trim())) {
						if (!list.contains(custKunnr)) {
							customerList.remove(i);
						}
					}
				}
			}
			props.add("custNumber");
			props.add("custName");
			props.add("channelName");
			props.add("corporateDeputy");
			props.add("contactName");
			props.add("contactMobile");
			props.add("contactPhone");
			props.add("custPhone");
			props.add("orgName");
			props.add("custSystem");
			props.add("custProvince");
			props.add("custCity");
			props.add("custDistrict");
			props.add("villages");
			props.add("custTown");
			props.add("custStreet");
			props.add("custHouserNumber");
			props.add("custAddress");
			props.add("stationUserId"); // 岗位id
			props.add("visitDaysName");
			props.add("visitFreqName");
			props.add("custStateName");
			props.add("custReceiveName");
			props.add("custKunnrName");
			props.add("empName");
			props.add("kunnrUser");
			props.add("createDate");
			props.add("lastModify");
			props.add("modifyUser");
			props.add("remark");
			props.add("longitude");
			props.add("latitude");
			props.add("customerImportance");
			props.add("customerAnnualSale");

			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("客户数据导出".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("客户资料", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 设置列宽度
			wsheet.setColumnView(1, 20);
			wsheet.setColumnView(2, 15);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(14, 30);
			wsheet.setColumnView(19, 15);
			wsheet.setColumnView(20, 40);
			wsheet.setColumnView(22, 20);
			wsheet.setColumnView(23, 20);
			wsheet.setColumnView(25, 40);

			Label label_0 = new Label(0, 0, "客户编号");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "客户名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 0, "渠道名称");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "法人代表");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);

			Label label_4 = new Label(4, 0, "联系人");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);

			Label label_5 = new Label(5, 0, "联系手机");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			Label label_6 = new Label(6, 0, "联系电话");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);

			Label label_7 = new Label(7, 0, "门店电话");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);
			Label label_8 = new Label(8, 0, "所属组织");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);

			Label label_9 = new Label(9, 0, "所属系统");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);

			Label label_10 = new Label(10, 0, "省/直辖市/自治区");
			label_10.setCellFormat(cellFormat_top);
			wsheet.addCell(label_10);

			Label label_11 = new Label(11, 0, "地级城市(所在城市)");
			label_11.setCellFormat(cellFormat_top);
			wsheet.addCell(label_11);

			Label label_12 = new Label(12, 0, "县级城市(所在区)");
			label_12.setCellFormat(cellFormat_top);
			wsheet.addCell(label_12);

			Label label_13 = new Label(13, 0, "镇、乡");
			label_13.setCellFormat(cellFormat_top);
			wsheet.addCell(label_13);

			Label label_14 = new Label(14, 0, "村/社区");
			label_14.setCellFormat(cellFormat_top);
			wsheet.addCell(label_14);

			Label label_15 = new Label(15, 0, "路/街/巷/里/弄");
			label_15.setCellFormat(cellFormat_top);
			wsheet.addCell(label_15);

			Label label_16 = new Label(16, 0, "门牌号");
			label_16.setCellFormat(cellFormat_top);
			wsheet.addCell(label_16);

			Label label_17 = new Label(17, 0, "参考位置");
			label_17.setCellFormat(cellFormat_top);
			wsheet.addCell(label_17);

			Label label_18 = new Label(18, 0, "岗位编号");
			label_18.setCellFormat(cellFormat_top);
			wsheet.addCell(label_18);

			Label label_19 = new Label(19, 0, "拜访日期");
			label_19.setCellFormat(cellFormat_top);
			wsheet.addCell(label_19);

			Label label_20 = new Label(20, 0, "拜访频率");
			label_20.setCellFormat(cellFormat_top);
			wsheet.addCell(label_20);

			Label label_21 = new Label(21, 0, "客户状态");
			label_21.setCellFormat(cellFormat_top);
			wsheet.addCell(label_21);

			Label label_22 = new Label(22, 0, "配送方式");
			label_22.setCellFormat(cellFormat_top);
			wsheet.addCell(label_22);

			Label label_23 = new Label(23, 0, "上级经销商");
			label_23.setCellFormat(cellFormat_top);
			wsheet.addCell(label_23);

			Label label_24 = new Label(24, 0, "我司业务负责人");
			label_24.setCellFormat(cellFormat_top);
			wsheet.addCell(label_24);

			Label label_25 = new Label(25, 0, "经销商业务负责人");
			label_25.setCellFormat(cellFormat_top);
			wsheet.addCell(label_25);

			Label label_26 = new Label(26, 0, "创建时间");
			label_26.setCellFormat(cellFormat_top);
			wsheet.addCell(label_26);

			Label label_27 = new Label(27, 0, "修改时间");
			label_27.setCellFormat(cellFormat_top);
			wsheet.addCell(label_27);

			Label label_28 = new Label(28, 0, "操作人");
			label_28.setCellFormat(cellFormat_top);
			wsheet.addCell(label_28);

			Label label_29 = new Label(29, 0, "备注");
			label_29.setCellFormat(cellFormat_top);
			wsheet.addCell(label_29);
			Label label_30 = new Label(30, 0, "经度");
			label_30.setCellFormat(cellFormat_top);
			wsheet.addCell(label_30);
			Label label_31 = new Label(31, 0, "纬度");
			label_31.setCellFormat(cellFormat_top);
			wsheet.addCell(label_31);
			Label label_32 = new Label(32, 0, "门店重要度");
			label_32.setCellFormat(cellFormat_top);
			wsheet.addCell(label_32);
			Label label_33 = new Label(33, 0, "门店年销售额");
			label_33.setCellFormat(cellFormat_top);
			wsheet.addCell(label_33);

			ExcelUtil.createExcelWithBook(wbook, props, customerList);
			this.setSuccessMessage("导出成功！");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 下载终端客户CSV模板
	 * 
	 * @return
	 */
	@PermissionSearch
	public void exportCustomerMouldCsv() {
		OutputStream os = null;
		String report_name = "";
		if ("Z".equals(type)) {
			report_name = "终端门店导入模板";
		}
		if ("T".equals(type)) {
			report_name = "二阶客户导入模板";
		}
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("门店名称(必填)");
			if ("Z".equals(type)) {
				linedata.append(",");
				linedata.append("配送方式(必填/格式：经销商配送)");
			}
			linedata.append(",");
			linedata.append("所属组织(必填)");
			linedata.append(",");
			linedata.append("我司业务代表EXP账号(如属我司业务代表此项必填)");
			linedata.append(",");
			linedata.append("我司业务代表姓名(如属我司业务代表此项必填)");
			linedata.append(",");
			linedata.append("经销商业务代表");
			linedata.append(",");
			linedata.append("渠道(必填)");
			linedata.append(",");
			linedata.append("所属经/分销商编号(多个编号用'/'隔开)");
			linedata.append(",");
			if ("Z".equals(type)) {
				linedata.append("二阶客户(多个编号用'/'隔开)");
				linedata.append(",");
			}
			linedata.append("所属系统编号");
			linedata.append(",");
			linedata.append("拜访日期((多个用'/'隔开)格式:星期一)");
			linedata.append(",");
			linedata.append("拜访频率(必填/格式:一周一次)");
			linedata.append(",");
			linedata.append("是否有理货员(有/无)");
			linedata.append(",");
			linedata.append("资产信息");
			linedata.append(",");
			linedata.append("售点面积(平米)(必填)");
			linedata.append(",");
			linedata.append("门店电话(座机)");
			linedata.append(",");
			linedata.append("身份证号码");
			linedata.append(",");
			linedata.append("营业执照号码");
			linedata.append(",");
			linedata.append("营业执照有效期(年-月-日)");
			linedata.append(",");
			linedata.append("法人代表");
			linedata.append(",");
			linedata.append("性别");
			linedata.append(",");
			linedata.append("联系人(必填)");
			linedata.append(",");
			linedata.append("联系人手机");
			linedata.append(",");
			linedata.append("联系人电话");
			linedata.append(",");
			linedata.append("国家(必填)");
			linedata.append(",");
			linedata.append("省份(必填)");
			linedata.append(",");
			linedata.append("地级市(必填)");
			linedata.append(",");
			linedata.append("县级市(必填)");
			linedata.append(",");
			linedata.append("镇/乡/城区(必填)");
			linedata.append(",");
			linedata.append("村/社区");
			linedata.append(",");
			linedata.append("路/街/巷/里/弄");
			linedata.append(",");
			linedata.append("门牌号");
			linedata.append(",");
			linedata.append("参考位置");
			linedata.append(",");
			linedata.append("门店重要度(只能填：甲/乙/丙三个等级)");
			linedata.append(",");
			linedata.append("门店年销售额");
			linedata.append(",");
			linedata.append("备注");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					logger.error(e);
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
	}

	/**
	 * 导入终端客户CSV
	 * 
	 * @return
	 */
	@PermissionSearch
	public String importCustomerCsv() {
		StringBuffer result = new StringBuffer();
		String custnumberp2 = "^\\d{1,}$";
		BooleanResult result1 = new BooleanResult();
		try {
			customerList = new ArrayList<Customer>();
			String rcs = "";
			String rcs2 = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					String[] header=SuperCSV.getHeaderFromFile(new File(uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						if(s.length<=header.length){
						int ij;
						rcs = "";
						if("Z".equals(type)){
							if(s.length>36){
								this.setFailMessage("第" + (j + 2) + "列数不正确.");
								return RESULT_MESSAGE;
							}
						}
						if ("T".equals(type)) {
							if(s.length>34){
								this.setFailMessage("第" + (j + 2) + "列数不正确.");
								return RESULT_MESSAGE;
							}
						}
						
						Customer cust = new Customer();
						ij = 0;
						String custName = s[ij++];
						cust.setCustType(type);
						cust.setCustState(1);//门店状态1，正常
						if ("Z".equals(type)) {
							String custReceive1 = s[ij++];
							if (custReceive1 == null) {
								rcs = rcs + "第" + (j + 2) + "行配送方式为空.</br>";
							} else {
								cust.setCustReceive(Integer
										.valueOf((custReceive1.trim())
												.replace("", "")
												.replace("总仓配送", "2")
												.replace("二阶配送", "1")
												.replace("经销商配送", "0")));
							}
						}

						String orgName = s[ij++];
						String stationUserId = s[ij++];
						String stationUser = s[ij++];
						// 验证岗位
						if (null != stationUserId && null != stationUser) {
							AllUsers user = new AllUsers();
							user.setLoginId(stationUserId.trim());
							user.setUserName(stationUser);
							List<AllUsers> users = new ArrayList<AllUsers>();
							users = batChangeService.getEmpListByUserName(user);
							if (0 == users.size()) {
								rcs = rcs + "第" + (j + 2)
										+ "行业务代表账号、姓名不存在.</br>";
							} else if(1==users.size()) {
								cust.setStationUserId(users.get(0).getPosId());
							}
						}
						Borg org = new Borg();
						org.setOrgName(orgName);
						Borg orgs = new Borg();
						orgs = customerService.gerOrgIsExit(org);
						if (null != orgName) {
							if (null == orgs) {
								rcs = rcs + "第" + (j + 2) + "行组织不存在.</br>";
							} else {
								cust.setOrgId(String.valueOf(orgs.getOrgId()));
							}
						} else {
							rcs = rcs + "第" + (j + 2) + "行组织为空.</br>";
						}
						String kunnrUser = s[ij++];
						cust.setKunnrUser(kunnrUser);
						String channelId = s[ij++];
						if (null != channelId) {
							Bchannel bc = new Bchannel();
							bc.setChannelName(channelId.trim());
							if("Z".equals(type)){
							bc.setType("一阶");   //终端
							}else{
							bc.setType("二阶");   //二阶客户
							}
							Bchannel channel = new Bchannel();
							channel = channelService.getChannelById(bc);
							
							if (null == channel) {
								rcs = rcs + "第" + (j + 2) + "行"+bc.getType()+"渠道不存在.</br>";
							} else {
								cust.setChannelId(Integer.valueOf(String
										.valueOf(channel.getChannelId())));
							}
						} else {
							rcs = rcs + "第" + (j + 2) + "行渠道为空.</br>";
						}
						String custKunnr = s[ij++];
						if (null != custKunnr) {
							String[] ck = custKunnr.split("/");
							String str = "";
							String str2 = "";
							for (int i = 0; i < ck.length; i++) {
								Kunnr kunnr = new Kunnr();
								kunnr.setKunnr(StringUtils.leftPad(
										String.valueOf(ck[i].trim()), 8, '0'));
								kunnr.setPagination("false");
								int kunnrSize = kunnrService.kunnrSearch(kunnr)
										.size();
								if (0 == kunnrSize) {
									if ("".equals(str2)) {
										str2 = ck[i].trim();
									} else {
										str2 = str2 + "," + ck[i].trim();
									}
								} else {
									if ("".equals(str)) {
										str = ck[i].trim();
									} else {
										str = str + "," + ck[i].trim();
									}
								}
							}
							if (!"".equals(str2)) {
								rcs = rcs + "第" + (j + 2) + "行所属经/分销商" + str2
										+ "不存在.</br>";
							} else {
								cust.setCustKunnr(StringUtils.leftPad(
										String.valueOf(str.trim()), 8, '0'));
							}
						}
						String custParent1 = "";
						if ("Z".equals(type)) {
							custParent1 = s[ij++];
						}
						String custSystem = s[ij++];
						if (null != custSystem) {
							CrmDict crmDict = new CrmDict();
							crmDict.setItemId(Long.valueOf(custSystem.trim()));
							CrmDict crmDict1 = null;
							crmDict1 = crmDictService.getCrmDict(crmDict);
							if (null == crmDict1) {
								rcs = rcs + "第" + (j + 2) + "行所属系统不存在.</br>";
							} else {
								cust.setCustSystem(custSystem);
							}
						}
						String visitDays1 = s[ij++];
						String visitFreq1 = s[ij++];
						// 配送方式

						// 拜访频率

						if (null == visitFreq1) {
							rcs = rcs + "第" + (j + 2) + "行拜访频率为空.</br>";
						} else {
							cust.setVisitFreq(Integer.valueOf((visitFreq1
									.trim().replace("一周一次", "1")
									.replace("一周两次", "2").replace("一周三次", "3")
									.replace("两周一次", "4"))));
						}
						// 拜访日期
						if (null == visitDays1) {
							rcs = rcs + "第" + (j + 2) + "行拜访日期为空.</br>";
						} else {
							
							String daysStr=	visitDays1.replace("星期一", "1").replace("星期二", "2")
								.replace("星期三", "3").replace("星期四", "4")
								.replace("星期五", "5").replace("星期六", "6")
								.replace("星期日", "0");
							String[] days=daysStr.split("/");
							String daysTxt="";
							for (int k = 0; k < days.length; k++) {
								String text=days[k];
								if("".equals(daysTxt)){
									daysTxt=text;
								}else{
									daysTxt =daysTxt+", "+text;
								}
								
							}
							cust.setVisitDays(daysTxt);
						}
						String custLhy = s[ij++];
						if (null != custLhy) {
							cust.setCustLhy((custLhy.trim()).replace("有", "1")
									.replace("无", "0"));
						}else{
							cust.setCustLhy("0");
						}

						String registerCapital = s[ij++];
						cust.setRegisterCapital(registerCapital);
						String shopArea = s[ij++];
						if(null!=shopArea){
							if(shopArea.matches(custnumberp2)){
								cust.setShopArea(shopArea);
							}else{
								rcs = rcs + "第" + (j + 2) + "行售点面积只能为数字.</br>";
							}
						}else{
							rcs = rcs + "第" + (j + 2) + "行售点面积为空.</br>";
						}
						
						String custPhone = s[ij++];
						cust.setCustPhone(custPhone);
						String idCopy = s[ij++];
						if(null!=idCopy){
							DecimalFormat df = new DecimalFormat("#");
							 String dStr = df.format(idCopy);
						     cust.setIdCopy(dStr);
						}
						String businessLicense = s[ij++];
						if(null!=businessLicense){
							DecimalFormat df = new DecimalFormat("#");
							 String dStr = df.format(Integer.valueOf(businessLicense));
							 cust.setBusinessLicense(dStr);
						}
						
						String licenceValidity = s[ij++];
						cust.setLicenceValidity(licenceValidity);
						String corporateDeputy = s[ij++];
						cust.setCorporateDeputy(corporateDeputy);
						String contactSex = s[ij++];
						if (null != contactSex) {
							cust.setContactSex((contactSex.trim())
									.replace("男", "M").replace("女", "F")
									.replace("", ""));
						}
						String contactName = s[ij++];
						String contactMobile = s[ij++];
						String contactPhone = s[ij++];
						if (null==contactName) {
							rcs = rcs + "第" + (j + 2) + "行联系人为空.</br>";
						} else {
							cust.setContactName(contactName);
						}
						if (null == contactMobile && null == contactPhone) {
							rcs = rcs + "第" + (j + 2)
									+ "行联系人手机或电话需至少填写一个.</br>";
						}
						if (null != contactPhone) {
							cust.setContactPhone(contactPhone.trim());
						}
						if (null != contactMobile) {
							if (!contactMobile.matches(custnumberp2)) {
								rcs = rcs + "第" + (j + 2)
										+ "行联系人手机不是数字.</br>";
							} else {
								cust.setContactMobile(contactMobile.trim());
							}
						}
						String country = s[ij++];// 待验证
						if(null==country){
							rcs = rcs + "第" + (j + 2)
							+ "国家为空.</br>";
						}
						String custProvince = s[ij++];// 待验证
						if(null==custProvince){
							rcs = rcs + "第" + (j + 2)
							+ "省份为空.</br>";
						}
						String city = s[ij++];// 待验证
						if(null==city){
							rcs = rcs + "第" + (j + 2)
							+ "地级市为空.</br>";
						}
						String twon = s[ij++];// 待验证
						if(null==twon){
							rcs = rcs + "第" + (j + 2)
							+ "县级市为空.</br>";
						}
						String villegs = s[ij++];// 待验证
						if(null==villegs){
							rcs = rcs + "第" + (j + 2)
							+ "乡镇/城区为空.</br>";
						}
						if("Z".equals(type)){
							if(s.length>=30){
							  String custTown = s[ij++];
							  cust.setCustTown(custTown);
							}
							if(s.length>=31){
								String custStreet = s[ij++];
								cust.setCustStreet(custStreet);
							}
							if(s.length>=32){
								String custHouserNumber = s[ij++];
								cust.setCustHouserNumber(custHouserNumber);
							}
							if(s.length>=33){
								String custAddress = s[ij++];
								cust.setCustAddress(custAddress);
							}
							if(s.length>=34){
								String customerImportance = s[ij++];
								if(null!=customerImportance){
									if (customerImportance.equals("甲") 
										|| customerImportance.equals("乙") 
										|| customerImportance.equals("丙")) {
										cust.setCustomerImportance(customerImportance);
									}else{
										rcs = rcs + "第" + (j + 2) + "行门店重要度只能填写甲/乙/丙三个等级.</br>";
									}
								}else{
									rcs = rcs + "第" + (j + 2) + "行门店重要度不能为空.</br>";
								}
							}
							if(s.length>=35){
								String customerAnnualSale = s[ij++];
								if(null!=customerAnnualSale){
									if(customerAnnualSale.matches(custnumberp2)){
										cust.setCustomerAnnualSale(customerAnnualSale);
									}else{
										rcs = rcs + "第" + (j + 2) + "行门店年销售额只能为数字.</br>";
									}
								}
							}
							if(s.length>=36){
								String remark = s[ij++];
								cust.setRemark(remark);
							}
						}else if("T".equals(type)){
							if(s.length>=28){
								  String custTown = s[ij++];
								  cust.setCustTown(custTown);
								}
								if(s.length>=29){
									String custStreet = s[ij++];
									cust.setCustStreet(custStreet);
								}
								if(s.length>=30){
									String custHouserNumber = s[ij++];
									cust.setCustHouserNumber(custHouserNumber);
								}
								if(s.length>=31){
									String custAddress = s[ij++];
									cust.setCustAddress(custAddress);
								}
								if(s.length>=32){
									String customerImportance = s[ij++];
									if(null!=customerImportance){
										if (customerImportance.equals("甲") 
											|| customerImportance.equals("乙") 
											|| customerImportance.equals("丙")) {
											cust.setCustomerImportance(customerImportance);
										}else{
											rcs = rcs + "第" + (j + 2) + "行门店重要度只能填写甲/乙/丙三个等级.</br>";
										}
									}else{
										rcs = rcs + "第" + (j + 2) + "行门店重要度不能为空.</br>";
									}
								}
								if(s.length>=33){
									String customerAnnualSale = s[ij++];
									if(null!=customerAnnualSale){
										if(customerAnnualSale.matches(custnumberp2)){
											cust.setCustomerAnnualSale(customerAnnualSale);
										}else{
											rcs = rcs + "第" + (j + 2) + "行门店年销售额只能为数字.</br>";
										}
									}
								}
								if(s.length>=34){
									String remark = s[ij++];
									cust.setRemark(remark);
								}
							}
						if (null != custProvince&&null!=country&&null!=city&&null!=twon&&null!=villegs) {
							Zwlqy z = new Zwlqy();
							Zwlqy d1 = new Zwlqy();
							d1.setZwl00t(country.trim());
							d1.setZwl01t(custProvince.trim());
							d1.setZwl02t(city.trim());
							d1.setZwl03t(twon.trim());
							d1.setZwl04t(villegs.trim());
							z = customerService.getCustomerXZArea(d1);
							if (null != z) {
								cust.setCustProvince(z.getZwl04());
							} else {
								rcs = rcs + "第" + (j + 2) + "行行政区划不存在.</br>";
							}

						} else {
							rcs = rcs + "第" + (j + 2) + "行行政区划为空.</br>";
						}
						if (null == custName) {
							rcs = rcs + "第" + (j + 2) + "行门店名称为空.</br>";
						} else {
							Customer c = new Customer();
							c.setCustName(custName.replace(" ", ""));
							c.setCustProvince(cust.getCustProvince());
							int n = customerService.getCustomerByName(c);
							if (n > 0) {
								rcs = rcs + "第" + (j + 2)
										+ "行门店名称在该城市中已存在.</br>";
							} else {
								cust.setCustName(custName.replace(" ", ""));
							}
						}
						if (null != custParent1&&!"".equals(custParent1)) {
							String[] ck = custParent1.split("/");
							String str = "";
							String str2 = "";
							for (int i = 0; i < ck.length; i++) {
								Customer ct = new Customer();
								ct.setCustId(Long.valueOf(ck[i]));
								int n = customerService
										.getTwoLevelCustomerCount(ct);
								if (0 == n) {
									rcs = rcs + "第" + (j + 2) + "上游客户" + str2
											+ "不存在.</br>";
								} else {
									if ("".equals(str)) {
										str = ck[i].trim();
									} else {
										str = str + "," + ck[i].trim();
									}
									cust.setCustParent(str);
								}
							}

						}
						if (!"".equals(rcs)) {
							result.append(rcs.toString() + "</br>");
							continue;
						}
						customerList.add(cust);
						}else{
							rcs = rcs + "第" + (j + 2)
							+ "列数有问题.</br>";
							result.append(rcs.toString() + "</br>");
						}
					}
					if (customerList.size() != 0
							&& result.toString().equals("")) {
						for (int q = 0; q < customerList.size(); q++) {
							customerList.get(q).setModifyUser(
									this.getUser().getUserName());
							customerList.get(q).setCreateUser(
									this.getUser().getUserId());
							customerList.get(q).setCreateOrgId(
									this.getUser().getOrgId());
							try {
								rcs2 = "";
								result1 = customerService
										.customerOpen(customerList.get(q));
								rcs2 = rcs2 + result1.getCode();
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								logger.error(e);
								rcs2 = rcs2 + "第" + (q + 2)
										+ "条数据保存数据库失败.请联系系统管理员.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					
					this.getSession()
							.setAttribute("customerList", customerList);
					if (result1.getResult()) {
						this.setSuccessMessage("导入成功！导入数量为:"
								+ customerList.size() + "行");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("数据导入出现异常，导入失败，请联系系统管理员!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 下载客户导入辅助信息
	 */
	public void exportCustomerHelps() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		cmsTbDict = new CmsTbDict();
	/*	cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
		cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict); // 客户状态
*/		cmsTbDict.setDictTypeId(Long.valueOf(customer_receive));
		cmsTbDictCustReceiveList = customerService.getCmsTbDictList(cmsTbDict); // 配送方式
		cmsTbDict.setDictTypeId(Long.valueOf(visitFreq));
		cmsTbDictVisitFreqList = customerService.getCmsTbDictList(cmsTbDict); // 拜访频率
		cmsTbDict.setDictTypeId(Long.valueOf(visitDays));
		cmsTbDictVisitDaysList = customerService.getCmsTbDictList(cmsTbDict); // 拜访日期
		// 所属系统
		CrmDict crmDict = new CrmDict();
		crmDict.setDictTypeValue("system@customer");
		crmDict.setPagination("false");
		custSystemList = crmDictService.getDictList(crmDict);
	
		HttpServletResponse response = getServletResponse();
		try {

			if (StringUtils.isNotEmpty(custKunnr)
					&& StringUtils.isNotEmpty(custKunnr.trim())) {
				try {
					custKunnr = new String(custKunnr.getBytes("ISO8859-1"),
							"UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
				customer.setCustKunnr(custKunnr);
			}
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("终端门店导入辅助数据导出".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			// 表头格式
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			List<String> props = new ArrayList<String>();
			props.add("itemId");
			props.add("itemName");
			props.add("itemValue");
			wbook = Workbook.createWorkbook(os);

		//	WritableSheet wsheet0 = wbook.createSheet("客户状态数据", 0);
			WritableSheet wsheet1 = wbook.createSheet("配送方式数据", 0);
			WritableSheet wsheet2 = wbook.createSheet("所属系统数据", 1);
			WritableSheet wsheet3 = wbook.createSheet("拜访日期数据", 2);
			WritableSheet wsheet4 = wbook.createSheet("拜访频率数据", 3);
			//WritableSheet wsheet5 = wbook.createSheet("行政区划数据", 4);

			// 设置列宽度

		/*	wsheet0.setColumnView(0, 15);
			wsheet0.setColumnView(1, 20);*/

			wsheet1.setColumnView(0, 15);
			wsheet1.setColumnView(1, 20);

			wsheet2.setColumnView(0, 15);
			wsheet2.setColumnView(1, 20);
			wsheet2.setColumnView(2, 12);

			wsheet3.setColumnView(0, 15);
			wsheet3.setColumnView(1, 20);

			wsheet4.setColumnView(0, 15);
			wsheet4.setColumnView(1, 20);

		/*	Label label_1 = new Label(0, 0, "客户状态名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet0.addCell(label_1);
			Label label_2 = new Label(1, 0, "代码");
			label_2.setCellFormat(cellFormat_top);
			wsheet0.addCell(label_2);
			for (int i = 0; i < cmsTbDictCustStateList.size(); i++) {
				CmsTbDict tb = new CmsTbDict();
				tb = cmsTbDictCustStateList.get(i);
				Label label1 = new Label(0, i + 1, tb.getItemName());
				wsheet0.addCell(label1);
				Label label2 = new Label(1, i + 1, tb.getItemValue());
				wsheet0.addCell(label2);

			}*/
			Label label_4 = new Label(0, 0, "配送方式名称");
			label_4.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_4);
			Label label_5 = new Label(1, 0, "代码");
			label_5.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_5);
			for (int j = 0; j < cmsTbDictCustReceiveList.size(); j++) {
				CmsTbDict tb = new CmsTbDict();
				tb = cmsTbDictCustReceiveList.get(j);
				Label label1 = new Label(0, j + 1, tb.getItemName());
				wsheet1.addCell(label1);
				Label label2 = new Label(1, j + 1, tb.getItemValue());
				wsheet1.addCell(label2);

			}

			Label label_6 = new Label(0, 0, "所属系统编号");
			label_6.setCellFormat(cellFormat_top);
			wsheet2.addCell(label_6);
			Label label_7 = new Label(1, 0, "所属系统名称");
			label_7.setCellFormat(cellFormat_top);
			wsheet2.addCell(label_7);
			Label label_8 = new Label(2, 0, "备注");
			label_8.setCellFormat(cellFormat_top);
			wsheet2.addCell(label_8);
			for (int k = 0; k < custSystemList.size(); k++) {
				CrmDict tb = new CrmDict();
				tb = custSystemList.get(k);
				Label label0 = new Label(0, k + 1, String.valueOf(tb
						.getItemId()));
				wsheet2.addCell(label0);
				Label label1 = new Label(1, k + 1, tb.getItemName());
				wsheet2.addCell(label1);
				Label label2 = new Label(2, k + 1, tb.getItemValue());
				wsheet2.addCell(label2);

			}

			Label label_10 = new Label(0, 0, "拜访日期名称");
			label_10.setCellFormat(cellFormat_top);
			wsheet3.addCell(label_10);
			Label label_11 = new Label(1, 0, "代码");
			label_11.setCellFormat(cellFormat_top);
			wsheet3.addCell(label_11);
			for (int l = 0; l < cmsTbDictVisitDaysList.size(); l++) {
				CmsTbDict tb = new CmsTbDict();
				tb = cmsTbDictVisitDaysList.get(l);
				Label label1 = new Label(0, l + 1, tb.getItemName());
				wsheet3.addCell(label1);
				Label label2 = new Label(1, l + 1, tb.getItemValue());
				wsheet3.addCell(label2);

			}

			Label label_13 = new Label(0, 0, "拜访频率名称");
			label_13.setCellFormat(cellFormat_top);
			wsheet4.addCell(label_13);
			Label label_14 = new Label(1, 0, "代码");
			label_14.setCellFormat(cellFormat_top);
			wsheet4.addCell(label_14);
			for (int m = 0; m < cmsTbDictVisitFreqList.size(); m++) {
				CmsTbDict tb = new CmsTbDict();
				tb = cmsTbDictVisitFreqList.get(m);
				Label label1 = new Label(0, m + 1, tb.getItemName());
				wsheet4.addCell(label1);
				Label label2 = new Label(1, m + 1, tb.getItemValue());
				wsheet4.addCell(label2);

			}
			
			ExcelUtil.createExcelWithBook(wbook, props, null);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	@PermissionSearch
	public String toSearchZwlqy(){
		return "toSearchZwlqy";
	}
	@PermissionSearch
	@JsonResult(field = "customerList", include = { "custId", "custName" }, total = "total")
	public String getTwoLevelCustomer() {
		Customer c = new Customer();
		if (StringUtils.isNotEmpty(search)) {
			c.setSearch(search);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			String[] str = orgId.split(", ");
			if (str.length > 1) {
				c.setOrgId(str[1]);
			} else {
				c.setOrgId(orgId);
			}
		}
		c.setStart(this.getStart());
		c.setEnd(this.getEnd());
		total = customerService.getTwoLevelCustomerCount(c);
		if (0 != total) {
			customerList = customerService.getTwoLevelCustomer(c);
		}
		return JSON;
	}
	
	/**
	 * 批量修改门店业务员模板
	 * @return
	 */
	public String exportCustomerEmpCsv() {
		OutputStream os = null;
		String report_name = "门店业务员修改模板";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("客户编号");
			linedata.append(",");
			linedata.append("客户名称");
			linedata.append(",");
			linedata.append("业代名称");
			linedata.append(",");
			linedata.append("业代账号");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 批量修改门店业务员
	 * @return
	 */
	public String importCustomerEmpCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		try {
			String rcs = "";
			String rcs2 = "";
			customerList = new ArrayList<Customer>();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))  //判断是否为CSV文件
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File( //读取头部字段
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 读取内容数组
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>4){
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0]==null || "".equals(s[0]) || s[1]==null || "".equals(s[1])){
								rcs = rcs + "第" + (j + 2) + "行 客户编号或名称为空.</br>";
							}else{
								Customer ct=new Customer();
								ct.setCustNumber(s[0]);
								ct.setCustName(s[1]);
								int num=customerService.getCustomerListCount(ct);
								if(num==0){
									rcs = rcs + "第" + (j + 2) + "行 客户编号或名称错误.</br>";
								}
							}
							
							Long stationId=null;
							if(s[2]==null || "".equals(s[2]) || s[3]==null || "".equals(s[3])){
								rcs = rcs + "第" + (j + 2) + "行 业代名称或账号为空.</br>";
							}else{
								AllUsers user =new AllUsers();
								user.setUserName(s[2]);
								user.setLoginId(s[3]);
								stationId=customerService.getCustomerStationUserId(user);
								if(stationId==null){
									rcs = rcs + "第" + (j + 2) + "行 业代名称或账号错误.</br>";
								}
							}
							if(stationId!=null){
								customer=new Customer();
								customer.setCustNumber(s[0]);
								customer.setStationUserId(stationId.toString());
								customerList.add(customer);
							}
								
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "第" + (j + 2)
									+ "列数有问题.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
					if (customerList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<customerList.size();j++){
							try {
								rcs2 = "";
								customerService.updateCustomerStationUserId(customerList.get(j));
								result1.setResult(true);
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								rcs2 = rcs2 + "第" + (j + 2)
										+ "条数据保存数据库失败.请联系系统管理员.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("导入成功！导入数量为:"
								+ content.size() + "行");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	
	/**
	 * MethodsTitle: 门店重要度批量修改模板下载
	 * @author: xg.chen add
	 * @date:2016年11月3日  version 1.0
	 * @return
	 */
	public String exportCustomerImprotanceCsv() {
		OutputStream os = null;
		String report_name = "门店重要度批量修改模板";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("客户编号");
			linedata.append(",");
			linedata.append("客户名称");
			linedata.append(",");
			linedata.append("门店重要度(必填:甲/乙/丙)");
			linedata.append(",");
			linedata.append("门店年销售(元)");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * MethodsTitle: 门店重要度批量修改导入
	 * @author: xg.chen add
	 * @date:2016年11月3日  version1.0
	 * @return
	 */
	public String importCustomerImprotanceCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		try {
			String rcs = "";
			String rcs2 = "";
			String uploadFileName1 = uploadCustFile.getName();
			customerList = new ArrayList<Customer>();
			if (StringUtils.isNotEmpty(uploadFileName1)) {
				/*String end = StringUtils.substring(uploadFileName,
						StringUtils.lastIndexOf(uploadFileName, '.'));*/
				/*if ((end != null && (end.equals(".csv")))  //判断是否为CSV文件
						|| (end != null && (end.equals(".CSV")))) {*/
					String[] header = SuperCSV.getHeaderFromFile(new File( //读取头部字段
							uploadCustFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadCustFile.toString())); // 读取内容数组
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>4){
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							int num = 0;
							if(s[0]==null || "".equals(s[0]) || s[1]==null || "".equals(s[1])){
								rcs = rcs + "第" + (j + 2) + "行 客户编号或名称为空.</br>";
							}else{
								Customer ct=new Customer();
								ct.setCustNumber(s[0]);
								ct.setCustName(s[1]);
								num=customerService.getCustomerListCount(ct);
								if(num==0){
									rcs = rcs + "第" + (j + 2) + "行 客户编号或名称错误.</br>";
								}
							}
							if(s[2]==null || "".equals(s[2])){
								rcs = rcs + "第" + (j + 2) + "行 门店重要度为空.</br>";
							}else{
								if (!s[2].equals("甲") && !s[2].equals("乙") && !s[2].equals("丙")) {
									rcs = rcs + "第" + (j + 2) + "行门店重要度只能填写甲/乙/丙三个等级.</br>";
								}
							}
							if(s[3]!=null && !"".equals(s[3])){
								String custnumberp2 = "^\\d{1,}$";
								if(!s[3].matches(custnumberp2)){
									rcs = rcs + "第" + (j + 2) + "行门店年销售额只能为数字(注意是否有空格).</br>";
								}
							}
							if((s[2]!=null || !"".equals(s[2])) && (s[3]!=null)){
								customer=new Customer();
								customer.setCustNumber(s[0]);
								customer.setCustomerImportance(s[2]);
								customer.setCustomerAnnualSale(s[3]);
								customerList.add(customer);
							}
							if((s[2]!=null || !"".equals(s[2])) && (s[3]==null)){
								customer=new Customer();
								customer.setCustNumber(s[0]);
								customer.setCustomerImportance(s[2]);
								customerList.add(customer);
							}
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "第" + (j + 2) + "列数有问题.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
						}
					}
					if (customerList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<customerList.size();j++){
							try {
								rcs2 = "";
								if(null==customerList.get(j).getCustomerAnnualSale() 
										|| "".equals(customerList.get(j).getCustomerAnnualSale())){
									customerService.updateCustomerImportance4CustNumber1(customerList.get(j));
								}else{
									customerService.updateCustomerImportance4CustNumber(customerList.get(j));
								}
								result1.setResult(true);
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								rcs2 = rcs2 + "第" + (j + 2)
										+ "条数据保存数据库失败.请联系系统管理员.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("导入成功！导入数量为:"
								+ content.size() + "行");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
								+ result.toString());
					}
				//}
			}else{
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (IOException e) {
			logger.error(e);
			this.setFailMessage("文件不存在");
			return RESULT_MESSAGE;
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<CityDict> getCitydictlist() {
		return citydictlist;
	}

	public void setCitydictlist(List<CityDict> citydictlist) {
		this.citydictlist = citydictlist;
	}

	public String getParent_city_id() {
		return parent_city_id;
	}

	public void setParent_city_id(String parent_city_id) {
		this.parent_city_id = parent_city_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getParent_city_name() {
		return parent_city_name;
	}

	public void setParent_city_name(String parent_city_name) {
		this.parent_city_name = parent_city_name;
	}

	public String getCitylevel() {
		return citylevel;
	}

	public void setCitylevel(String citylevel) {
		this.citylevel = citylevel;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public static String getKey4open() {
		return key4open;
	}

	public static void setKey4open(String key4open) {
		CustomerAction.key4open = key4open;
	}

	public IWfeService getWfeServiceHessian() {
		return wfeServiceHessian;
	}

	public void setWfeServiceHessian(IWfeService wfeServiceHessian) {
		this.wfeServiceHessian = wfeServiceHessian;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}

	public List<CmsTbDict> getCmsTbDictCustReceiveList() {
		return cmsTbDictCustReceiveList;
	}

	public void setCmsTbDictCustReceiveList(
			List<CmsTbDict> cmsTbDictCustReceiveList) {
		this.cmsTbDictCustReceiveList = cmsTbDictCustReceiveList;
	}

	public List<CmsTbDict> getCmsTbDictCustStateList() {
		return cmsTbDictCustStateList;
	}

	public void setCmsTbDictCustStateList(List<CmsTbDict> cmsTbDictCustStateList) {
		this.cmsTbDictCustStateList = cmsTbDictCustStateList;
	}

	public String getCustomer_state() {
		return customer_state;
	}

	public void setCustomer_state(String customer_state) {
		this.customer_state = customer_state;
	}

	public String getCustomer_receive() {
		return customer_receive;
	}

	public void setCustomer_receive(String customer_receive) {
		this.customer_receive = customer_receive;
	}

	public BooleanResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}

	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustReceive() {
		return custReceive;
	}

	public void setCustReceive(String custReceive) {
		this.custReceive = custReceive;
	}

	public String getStationUserName() {
		return stationUserName;
	}

	public void setStationUserName(String stationUserName) {
		this.stationUserName = stationUserName;
	}

	public List<CmsTbDict> getCmsTbDictVisitFreqList() {
		return cmsTbDictVisitFreqList;
	}

	public void setCmsTbDictVisitFreqList(List<CmsTbDict> cmsTbDictVisitFreqList) {
		this.cmsTbDictVisitFreqList = cmsTbDictVisitFreqList;
	}

	public List<CmsTbDict> getCmsTbDictVisitDaysList() {
		return cmsTbDictVisitDaysList;
	}

	public void setCmsTbDictVisitDaysList(List<CmsTbDict> cmsTbDictVisitDaysList) {
		this.cmsTbDictVisitDaysList = cmsTbDictVisitDaysList;
	}

	public String getVisitDays() {
		return visitDays;
	}

	public void setVisitDays(String visitDays) {
		this.visitDays = visitDays;
	}

	public String getVisitFreq() {
		return visitFreq;
	}

	public void setVisitFreq(String visitFreq) {
		this.visitFreq = visitFreq;
	}

	public String getCustProvince() {
		return custProvince;
	}

	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustDistrict() {
		return custDistrict;
	}

	public void setCustDistrict(String custDistrict) {
		this.custDistrict = custDistrict;
	}

	public String getCustKunnr() {
		return custKunnr;
	}

	public void setCustKunnr(String custKunnr) {
		this.custKunnr = custKunnr;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getStationUserId() {
		return stationUserId;
	}

	public void setStationUserId(String stationUserId) {
		this.stationUserId = stationUserId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public ICrmDictService getCrmDictService() {
		return crmDictService;
	}

	public void setCrmDictService(ICrmDictService crmDictService) {
		this.crmDictService = crmDictService;
	}

	public List<CrmDict> getCustSystemList() {
		return custSystemList;
	}

	public void setCustSystemList(List<CrmDict> custSystemList) {
		this.custSystemList = custSystemList;
	}

	public String getCustState1() {
		return custState1;
	}

	public void setCustState1(String custState1) {
		this.custState1 = custState1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrgId01() {
		return orgId01;
	}

	public void setOrgId01(String orgId01) {
		this.orgId01 = orgId01;
	}

	public String getOrgName01() {
		return orgName01;
	}

	public void setOrgName01(String orgName01) {
		this.orgName01 = orgName01;
	}

	public String getStationUserName01() {
		return stationUserName01;
	}

	public void setStationUserName01(String stationUserName01) {
		this.stationUserName01 = stationUserName01;
	}

	public String getStationUserId01() {
		return stationUserId01;
	}

	public void setStationUserId01(String stationUserId01) {
		this.stationUserId01 = stationUserId01;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustParent() {
		return custParent;
	}

	public void setCustParent(String custParent) {
		this.custParent = custParent;
	}

	public IBatChangeService getBatChangeService() {
		return batChangeService;
	}

	public void setBatChangeService(IBatChangeService batChangeService) {
		this.batChangeService = batChangeService;
	}

	public List<Zwlqy> getZwlqyList() {
		return zwlqyList;
	}

	public void setZwlqyList(List<Zwlqy> zwlqyList) {
		this.zwlqyList = zwlqyList;
	}

	public ISalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(ISalesService salesService) {
		this.salesService = salesService;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getCreateOrgId() {
		return createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getCustKunnrId() {
		return custKunnrId;
	}

	public void setCustKunnrId(String custKunnrId) {
		this.custKunnrId = custKunnrId;
	}

	public List<AllUsers> getUserList() {
		return userList;
	}

	public void setUserList(List<AllUsers> userList) {
		this.userList = userList;
	}

	public String getKunnrUser() {
		return kunnrUser;
	}

	public void setKunnrUser(String kunnrUser) {
		this.kunnrUser = kunnrUser;
	}

	public String getJsonRows() {
		return jsonRows;
	}

	public void setJsonRows(String jsonRows) {
		this.jsonRows = jsonRows;
	}


	public String getAllChannelName() {
	    return allChannelName;
	}


	public void setAllChannelName(String allChannelName) {
	    this.allChannelName = allChannelName;
	}


	public String getCustomerImportance() {
		return customerImportance;
	}


	public void setCustomerImportance(String customerImportance) {
		this.customerImportance = customerImportance;
	}


	public String getCustomerAnnualSale() {
		return customerAnnualSale;
	}


	public void setCustomerAnnualSale(String customerAnnualSale) {
		this.customerAnnualSale = customerAnnualSale;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public File getUploadCustFile() {
		return uploadCustFile;
	}


	public void setUploadCustFile(File uploadCustFile) {
		this.uploadCustFile = uploadCustFile;
	}
	
	
	
}
