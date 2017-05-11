package com.kintiger.platform.order.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import sun.tools.tree.OrExpression;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.service.IKunnrAddressService;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;
import com.kintiger.platform.order.service.IOrderPlatService;

/**
 * 经销商订单平台
 * 
 * @author Administrator 2015-7-20
 */
public class OrdersAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9085525179074554626L;
	private static Log logger = LogFactory.getLog(OrdersAction.class);

	// 经销商订单表头列表
	private List<OrderTitle> orderTitleList;

	// 订单明细
	private List<OrderList> orderDetailList;

	// 物料列表
	private List<Materiel> materielList;

	// 物料类型列表
	private List<Materiel> materielTypeList;
	//送达方
	private List<KunnrAddress> kunnrAddressList;
	// 考核返还清单list
	private List<AssesReturnList> aReturnLists;
	// service接口
	private IOrderPlatService orderPlatService;
	private IKunnrAddressService kunnrAddressService;
	// 订单title对象
	private OrderTitle orderTitle;

	private int total;

	// 经销商订单ID
	private String orderId;
	private String viewFlag;// 界面区分标志
	private String kunnrNum;// 经销商编码
	@Decode
	private String kunnrName;// 经销商名称
	@Decode
	private String materielName;// 物料名称
	private String orgId;// 组织
	private String uploadFileFileName;// 附件名称
	private File uploadFile;// 附件
	private String ids;// codes
	private BigDecimal money;// 金额
	@Decode
	private String search;//模糊搜索条件
	private String matkl;//物料类别编码
	private String operFlag;//操作类型
	private String orderDate;//订单日期
	private String orderState;//订单状态
	//时间段
	private String startDate;
	private String endDate;

	/**
	 * 返回经销商订单平台页面
	 * 
	 * @return
	 */
	public String ordersPlat() {
		Map<String, String> map=DateUtil.getFLDayMap("cm","yyyyMMdd");
		startDate=map.get("firstDay");
		endDate=map.get("lastDay");
		kunnrNum = this.getUser().getIsOffice();
		orgId = this.getUser().getOrgId();
		if(StringUtils.isNotEmpty(kunnrNum)){
			kunnrName= this.getUser().getUserName();
			//可用预付款、费用兑换
		}
		return "ordersPlat";
	}
	/**
	 * 返回订单审核页面
	 * 
	 * @return
	 */
	public String ordersPlatReview() {
		Map<String, String> map=DateUtil.getFLDayMap("cm","yyyyMMdd");
		startDate=map.get("firstDay");
		endDate=map.get("lastDay");
		orderState="B";//已提交未审核
		return "ordersPlatReview";
	}

	/**
	 * 返回订单列表
	 * 
	 * @return
	 */
	@JsonResult(field = "orderTitleList", include = { "order_id","order_num",
			"order_state", "kunnr_num", "kunnr_name", "order_date",
			"order_count", "order_up_fund", "order_give_fund",
			"order_use_fund","orderTotalMoney", "kunnr_id", "user_id", "alter_date", "alter_id","provinceArea",
			"province","city" })
	public String searchOrdersTotal() {
		orderTitleList = new ArrayList<OrderTitle>();
		OrderTitle title=new OrderTitle();
		if(StringUtils.isNotEmpty(orderId)){
			title.setOrder_id(Long.valueOf(orderId));
		}
		if(StringUtils.isNotEmpty(orderState)){
			String[] codes=orderState.replace(" ", "").split(",");
			title.setStates(codes);
		}
		if(StringUtils.isNotEmpty(kunnrNum)){
			title.setKunnr_num(kunnrNum);
		}
		if(StringUtils.isNotEmpty(kunnrName)){
			title.setKunnr_name(kunnrName);
		}
		if(StringUtils.isNotEmpty(orgId)){
			title.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(startDate)){
			title.setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			title.setEndDate(endDate);
		}
		title.setStart(getStart());
		title.setEnd(getEnd());
		total=orderPlatService.getOrderTitleListCount(title);
		if(total>0){
		   orderTitleList=orderPlatService.getOrderTitleList(title);
		}
		return JSON;
	}

	/**
	 * 返回订单明细
	 */
	@JsonResult(field = "orderDetailList", include = { "list_id", "order_id",
			"materiel_mvgr", "materiel_mvgr_name", "materiel_num",
			"materiel_name", "materiel_price", "list_count", "order_up_fund","payMoney",
			"order_give_fund", "order_give_count", "order_use_fund","list_type",
			"kunnr_id", "user_id", "alter_date", "alter_id","address","remark" })
	public String findOrderListByTitle() {
		orderDetailList = orderPlatService.findOrderDetailListByTitle(Long
				.valueOf(orderId));
		return JSON;
	}

	/**
	 * 获取物料类别
	 * 
	 * @return
	 */
	@JsonResult(field = "materielTypeList", include = { "matkl", "wgbez",
			"kbetr" })
	public String findMaterielTypeList() {
		Materiel param = new Materiel();
		if(StringUtils.isNotEmpty(search)){
			param.setSearch(search);
		}
		materielTypeList = orderPlatService.findMaterielTypeList(param);
		return JSON;
	}
	/**
	 * 获取物料
	 * 
	 * @return
	 */
	@JsonResult(field = "materielList", include = { "matnr", "maktx",
			"kbetr" })
	public String findMaterielList() {
		Materiel param = new Materiel();
		if(StringUtils.isNotEmpty(matkl)){
			param.setMatkl(matkl);
		}
		if(StringUtils.isNotEmpty(search)){
			param.setSearch(search);
		}
		materielList = orderPlatService.findMaterielList(param);
		return JSON;
	}
	/**
	 * 送货地址
	 * @return
	 */
	@JsonResult(field = "kunnrAddressList", include = { "kunnrSd", "kunnr",
			"kunnrName", "address", "name", "telephone", "mobile" ,"maximum","maximumTxt","state","province","city","area","town","xzAddress"})
	public String getKunnrAddress() {
		KunnrAddress kunnrAddress = new KunnrAddress();
		kunnrAddress.setStart(getStart());
		kunnrAddress.setEnd(getEnd());
		if (StringUtils.isNotEmpty(kunnrNum)) {
			kunnrAddress.setKunnr(kunnrNum);
		}
		if (StringUtils.isNotEmpty(kunnrName)) {
			kunnrAddress.setKunnrName(kunnrName);
		}
		kunnrAddress.setPagination("false");//不分页
		total = kunnrAddressService.kunnrAddressSearchCount(kunnrAddress);
		if (total != 0) {
			kunnrAddressList = kunnrAddressService
					.kunnrAddressSearch(kunnrAddress);
		}
		return JSON;
	}
	/**
	 * 打开订单编辑窗口
	 * 
	 * @return
	 */
	public String orderEdit() {
		String kunnrNum = this.getUser().getIsOffice();
		orderTitle=new OrderTitle();
		orgId = this.getUser().getOrgId();
		viewFlag = "E";// 其他用户界面识别标志
		if("U".equals(operFlag)){//修改界面
			//根据订单ID获取订单、明细信息
			orderTitle.setOrder_id(Long.valueOf(orderId));
			orderTitle.setPagination("false");
			orderTitleList=orderPlatService.getOrderTitleList(orderTitle);
			if(null!=orderTitleList&&orderTitleList.size()>0){
				orderTitle=orderTitleList.get(0);
			}
		}
		if (StringUtils.isNotEmpty(kunnrNum)) {// 是否为经销商
			viewFlag = "K";// 经销商界面识别标志
			// 获取经销经销商信息
			orderTitle.setKunnr_name(this.getUser().getUserName());
			orderTitle.setKunnr_id(this.getUser().getUserId());
			orderTitle.setKunnr_num(kunnrNum);
			// 可用预打款金额
			orderTitle.setUsedPay(getKunnrUsedPay(kunnrNum));
			// 可兑现折扣金额
			orderTitle.setDiscountAmount(getKunnrDiscountAmount(kunnrNum));
			//通知
			orderTitle.setMsg("政策通知");
		}
		return "orderEdit";
	}

	/**
	 * 保存编辑后的订单信息
	 */
	public String editOreder(){
		BooleanResult result = new BooleanResult();
		orderTitle.setUser_id(Long.valueOf(this.getUser().getUserId()));
		orderTitle.setOrder_state(orderState);
		try {
			result = orderPlatService.editOreder(orderTitle, orderDetailList);
		} catch (Exception e) {
			logger.error(e);
		}
		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 删除、审核订单
	 * @return
	 */
	public String updateOrder(){
		BooleanResult result = new BooleanResult();
		orderTitle = new OrderTitle();
		String[] codes = ids.split(",");
		orderTitle.setCodes(codes);
		//审核
		if("C".equals(orderState)){
			//校验预打款、费用兑现金额
			for (int i = 0; i < codes.length; i++) {
				
			}
			orderTitle.setAlter_id(Long.valueOf(this.getUser().getUserId()));//审核人
		}
		orderTitle.setOrder_state(orderState);
		orderTitle.setUser_id(Long.valueOf(this.getUser().getUserId()));
		result = orderPlatService.updateOrderState(orderTitle);
		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 根据经销商编码获取可用预打款金额 可用打款金额=sap-exp已下订单 界面调用
	 * 
	 * @param kunnrNum
	 * @return
	 */
	@JsonResult(field = "money")
	public String getKunnrUsedPay() {
		money = getKunnrUsedPay(kunnrNum);
		return JSON;
	}

	/**
	 * 根据经销商编码获取可兑现折扣金额 可用打款金额=sap-exp已下订单 界面调用
	 * 
	 * @param kunnrNum
	 * @return
	 */
	@JsonResult(field = "money")
	public String getKunnrDiscountAmount() {
		money = getKunnrDiscountAmount(kunnrNum);
		return JSON;
	}

	/**
	 * 根据经销商编码获取可用预打款金额 可用打款金额=sap-exp已下订单
	 * 
	 * @param kunnrNum
	 * @return
	 */
	public BigDecimal getKunnrUsedPay(String kunnrNum) {
		BigDecimal big = new BigDecimal(130);
		return big;
	}

	/**
	 * 根据经销商编码获取可兑现折扣金额 可用打款金额=sap-exp已下订单
	 * 
	 * @param kunnrNum
	 * @return
	 */
	public BigDecimal getKunnrDiscountAmount(String kunnrNum) {
		BigDecimal big = new BigDecimal(150);
		return big;
	}

	/**
	 * 跳转至考核返还清单导入界面
	 * 
	 * @return
	 */
	public String toSearchReturnListPre() {
		return "toSearchReturnListPre";
	}

	/**
	 * 考核返回清单list
	 */
	@JsonResult(field = "aReturnLists", include = { "relistId", "orderId",
			"materielNum", "materielName", "cataId", "cataName",
			"yearPayPrice", "totalBouns", "monthAsses", "quarterAsses",
			"yearAsses", "yearNakedPrice", "status", "userId", "kunnrName",
			"kunnrNum" })
	public String searchReturnLists() {
		AssesReturnList asses = new AssesReturnList();
		asses.setStart(getStart());
		asses.setEnd(getEnd());
		if (StringUtils.isNotEmpty(orderId)) {
			asses.setOrderId(orderId);
		}
		if (StringUtils.isNotEmpty(kunnrNum)) {
			asses.setKunnrNum(kunnrNum);
		}
		if (StringUtils.isNotEmpty(kunnrName)) {
			asses.setKunnrName(kunnrName);
		}
		if (StringUtils.isNotEmpty(materielName)) {
			asses.setMaterielName(materielName);
		}
		total = orderPlatService.searchReturnListCount(asses);
		if (total > 0) {
			aReturnLists = orderPlatService.searchReturnLists(asses);
		}
		return JSON;
	}

	/**
	 * 考核返还清单模板下载
	 * 
	 * @return
	 */
	public String downloadExcel() {
		OutputStream os = null;
		String report_name = "考核返还清单模板";
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
			linedata.append("经销商编码");
			linedata.append(",");
			linedata.append("经销商名称");
			linedata.append(",");
			linedata.append("物料名称");
			linedata.append(",");
			linedata.append("品项名称");
			linedata.append(",");
			linedata.append("2015年打款价");
			linedata.append(",");
			linedata.append("考核总金额");
			linedata.append(",");
			linedata.append("月度考核");
			linedata.append(",");
			linedata.append("季度考核");
			linedata.append(",");
			linedata.append("年度考核");
			linedata.append(",");
			linedata.append("2015年裸价");
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
	 * 返还清单导入
	 * 
	 * @return
	 */
	public String importExcel() {
		String custnumberp2 = "-?[0-9]+.*[0-9]*";/* "^\\d{1,}$" */
		;// 数字验证
		BooleanResult result = new BooleanResult();// 导入返回
		aReturnLists = new ArrayList<AssesReturnList>();
		String rs = "";// 数据验证返回信息
		StringBuffer rst = new StringBuffer();// 错误信息
		try {
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {// 文件格式
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					Map<String, AssesReturnList> tempMap = new HashMap<String, AssesReturnList>();// 验证数据重复
					int r;// 行
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						int c;// 列
						r = j + 2;
						AssesReturnList asses = new AssesReturnList();// 单个数据
						if (s.length == 10) {// 列数
							Kunnr kunnr = new Kunnr();// 经销商验证
							Materiel mat = new Materiel();// 物料验证
							String userId = getUser().getUserId().toString();
							asses.setUserId(userId);// 操作人
							c = 0;
							String c0 = s[c++];
							String c1 = s[c++];
							String c2 = s[c++];
							String c3 = s[c++];
							String c4 = s[c++];
							String c5 = s[c++];
							String c6 = s[c++];
							String c7 = s[c++];
							String c8 = s[c++];
							String c9 = s[c++];

							if (c0 != null || c1 != null) {// 经销商
								kunnr.setKunnr(c0);
								kunnr.setName1(c1);
								List<Kunnr> kList = orderPlatService
										.getKunnrs(kunnr);
								if (null != kList) {
									if (kList.size() == 1) {
										asses.setKunnrNum(kList.get(0)
												.getKunnr());
										asses.setKunnrName(kList.get(0)
												.getName1());// 经销商
									} else {
										rs += "第"
												+ r
												+ "行经销商名称在系统中存在多个,请填写具体的编码和名称.</br>";
									}
								} else {
									rs += "第" + r + "行经销商编码或名称在系统中不存在.</br>";
								}
							} else {
								rs += "第" + r + "行经销商编码或名称列为空.</br>";
							}
							if (c2 != null) {// 物料名称
								mat.setMaktx(c2);
								List<Materiel> mats = orderPlatService
										.findMaterielList(mat);
								if (null != mats) {
									if (mats.size() == 1) {
										asses.setMaterielNum(mats.get(0)
												.getMatnr());
										asses.setMaterielName(mats.get(0)
												.getMaktx());// 物料
									} else {
										rs += "第"
												+ r
												+ "行经物料名称在系统中存在多个,请填写正确的全称.</br>";
									}
								} else {
									rs += "第" + r + "行物料名称在系统中不存在.</br>";
								}
							} else {
								rs += "第" + r + "行物料列为空.</br>";
							}
							if (c3 != null) {// 品项
								mat.setWgbez(c3);
								List<Materiel> mats = orderPlatService
										.findMaterielTypeList(mat);
								if (null != mats) {
									if (mats.size() == 1) {
										asses.setCataId(mats.get(0).getMatkl());
										asses.setCataName(mats.get(0)
												.getWgbez());// 品项名称
									} else {
										rs += "第"
												+ r
												+ "行经品项名称在系统中存在多个,请填写正确的全称.</br>";
									}
								} else {
									rs += "第" + r + "行品项名称在系统中不存在.</br>";
								}
							} else {
								rs += "第" + r + "行品项列为空.</br>";
							}
							if (c4 != null
									&& String.valueOf(c4).matches(custnumberp2)) {// 财年打款价
								asses.setYearPayPrice(new BigDecimal(c4));
							} else {
								rs += "第" + r + "行财年打款价为空或不是数字.</br>";
							}
							if (c5 != null
									&& String.valueOf(c5).matches(custnumberp2)) {// 考核总奖金
								asses.setTotalBouns(new BigDecimal(c5));
							} else {
								rs += "第" + r + "行考核总奖金为空或不是数字.</br>";
							}
							if (c6 != null
									&& String.valueOf(c6).matches(custnumberp2)) {// 月度考核
								asses.setMonthAsses(new BigDecimal(c6));
							} else {
								rs += "第" + r + "行月度考核为空或不是数字.</br>";
							}
							if (c7 != null
									&& String.valueOf(c7).matches(custnumberp2)) {// 季度考核
								asses.setQuarterAsses(new BigDecimal(c7));
							} else {
								rs += "第" + r + "行季度考核为空或不是数字.</br>";
							}
							if (c8 != null
									&& String.valueOf(c8).matches(custnumberp2)) {// 年度考核
								asses.setYearAsses(new BigDecimal(c8));
							} else {
								rs += "第" + r + "行年度考核为空或不是数字.</br>";
							}
							if (c9 != null
									&& String.valueOf(c9).matches(custnumberp2)) {// 财年裸价
								asses.setYearNakedPrice(new BigDecimal(c9));
							} else {
								rs += "第" + r + "行财年裸价为空或不是数字.</br>";
							}
							// 验证文档中数据是否重复：经销商+物料+品项
							StringBuilder str = new StringBuilder(c0);
							str.append("@").append(c2).append("@").append(c3);
							if (null == tempMap.get(str.toString())) {
								tempMap.put(str.toString(), asses);
							} else {
								rs += "第" + r + "行数据存在重复.</br>";
							}
							// 验证数据是否已在系统中存在：经销商+物料+品项
							int count = orderPlatService
									.searchReturnListCount(asses);
							if (count > 0) {
								rs += "第" + r + "行" + str + "系统中已存在.</br>";
							}
						} else {
							this.setFailMessage("列数不对!");
							return RESULT_MESSAGE;
						}
						// 将数据放入集合
						aReturnLists.add(asses);
					}
					// 数据验证结束
					if (!"".equals(rs)) {// 数据验证错误
						rst.append(rs.toString() + "</br>");
						this.setFailMessage(rst.toString());
						return RESULT_MESSAGE;
					} else {// 验证通过
						result = orderPlatService.importExcel(aReturnLists);
						if (result.getResult()) {
							this.setSuccessMessage("批量导入成功！导入数量"
									+ aReturnLists.size() + "条数据!");
						} else {
							this.setFailMessage("导入失败!");
						}
						return RESULT_MESSAGE;
					}
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (IOException e) {
			logger.error(e);
			this.setFailMessage("导入出现异常,请检查数据或联系管理员.");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 批量删除返还清单
	 * 
	 * @return
	 */
	public String delReturnList() {
		BooleanResult result = new BooleanResult();
		AssesReturnList asses = new AssesReturnList();
		String[] codes = ids.split(",");
		asses.setCodes(codes);
		asses.setUserId(this.getUser().getUserId());
		result = orderPlatService.delAssesReturnList(asses);
		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 批量删除订单明细
	 * @return
	 */
    public String deleteOrderDetail(){
		BooleanResult result = new BooleanResult();
		OrderList detail= new OrderList();
		String[] codes = ids.split(",");
		detail.setCodes(codes);
		detail.setUserId(this.getUser().getUserId());
		result = orderPlatService.deleteOrderDetail(detail);
		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}
		return RESULT_MESSAGE;
    }
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Materiel> getMaterielList() {
		return materielList;
	}

	public void setMaterielList(List<Materiel> materielList) {
		this.materielList = materielList;
	}

	public List<Materiel> getMaterielTypeList() {
		return materielTypeList;
	}

	public void setMaterielTypeList(List<Materiel> materielTypeList) {
		this.materielTypeList = materielTypeList;
	}

	public IOrderPlatService getOrderPlatService() {
		return orderPlatService;
	}

	public void setOrderPlatService(IOrderPlatService orderPlatService) {
		this.orderPlatService = orderPlatService;
	}

	public List<OrderTitle> getOrderTitleList() {
		return orderTitleList;
	}

	public void setOrderTitleList(List<OrderTitle> orderTitleList) {
		this.orderTitleList = orderTitleList;
	}

	public List<OrderList> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderList> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public List<AssesReturnList> getaReturnLists() {
		return aReturnLists;
	}

	public void setaReturnLists(List<AssesReturnList> aReturnLists) {
		this.aReturnLists = aReturnLists;
	}

	public OrderTitle getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(OrderTitle orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getKunnrNum() {
		return kunnrNum;
	}

	public void setKunnrNum(String kunnrNum) {
		this.kunnrNum = kunnrNum;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMatkl() {
		return matkl;
	}

	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}

	public List<KunnrAddress> getKunnrAddressList() {
		return kunnrAddressList;
	}

	public void setKunnrAddressList(List<KunnrAddress> kunnrAddressList) {
		this.kunnrAddressList = kunnrAddressList;
	}

	public IKunnrAddressService getKunnrAddressService() {
		return kunnrAddressService;
	}

	public void setKunnrAddressService(IKunnrAddressService kunnrAddressService) {
		this.kunnrAddressService = kunnrAddressService;
	}

	public String getOperFlag() {
		return operFlag;
	}

	public void setOperFlag(String operFlag) {
		this.operFlag = operFlag;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}