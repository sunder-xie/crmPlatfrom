package com.kintiger.platform.distributionGoalAppend.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;

import com.kintiger.platform.distributionGoalAppend.pojo.DistributionGoalAppend;
import com.kintiger.platform.distributionGoalAppend.service.IDistributionGoalAppendService;

import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class DistributionGoalAppendAction extends BaseAction {

	private static final long serialVersionUID = -532423459213585547L;
	private static Log logger = LogFactory
			.getLog(DistributionGoalAppendAction.class);
	private String orgId;
	@Decode
	private String orgName;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private String firstUser;
	private String position;
	private String dire_super_name;
	private String dire_super_position;
	private String kunnrId;
	@Decode
	private String kunnrName;
	private String matter;
	private String state;
	@Decode
	private String startDate;
	@Decode
	private String endDate;
	private int boxNum;
	private String inputDate;
	private String checkName;
	private String trFlag;
	private int aOne;
	private int aTwo;
	private int aThree;
	private int aFour;
	private int aFive;
	private int aSix;
	private int aSeven;
	private int aEight;
	private int bOne;
	private int bSix;
	private int bEight;
	private int cOne;
	private int cSix;
	private int cSeven;
	private int cEight;
	private int dNine;
	private int dTen;
	private int eOne;
	private int hSeven;
	private String userId;
	private long dgId;
	private String channel;
	private String store_category;
	private String resign_date;

	private String ids;
	private int size;
	private IOrgService orgServiceHessian;
	private IDistributionGoalAppendService distributionGoalAppendService;
	private List<DistributionGoalAppend> distributionGoalAppendLists;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private DistributionGoalAppend dGoal;
	private IKunnrService kunnrService;

	public String distributionGoalAppendSearch() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();	
		if (org.getOrgId() == 51240) {
			return "distributionGoalAppendSearch";
		} else {
			return "toDistributionGoalAppendSearch";
		}
	}

	/**
	 * 查询目标 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "distributionGoalAppendLists", total = "size", include = {
			"orgRegion", "orgProvince", "orgCity", "firstUser", "position",
			"dire_super_name", "dire_super_position", "kunnrName", "kunnrId",
			"inputDate", "aOne","aTwo", "dgId", "aThree", "aFour", "aFive", "aSix",
			"aSeven", "aEight", "bOne","bSix", "bEight", "cOne", "cSix", "cSeven",
			"cEight", "dNine","dTen","eOne", "hSeven", "inputName", "checkName", "trFlag",
			"channel", "store_category", "resign_date","lastDate","thisSysdate","createDate" })
	public String getDistributionGoalAppendList() {
		distributionGoalAppendLists = new ArrayList<DistributionGoalAppend>();
		DistributionGoalAppend dGoal = new DistributionGoalAppend();
		dGoal.setOrgId(this.getUser().getOrgId());
		if (StringUtils.isNotEmpty(orgId)) {
			dGoal.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			dGoal.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(kunnrId)) {
			dGoal.setKunnrId(kunnrId);
		}
		if (StringUtils.isNotEmpty(kunnrName)) {
			dGoal.setKunnrName(kunnrName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			dGoal.setStartDate(startDate.replaceAll("年","-").replaceAll("月",""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dGoal.setEndDate(endDate.replaceAll("年","-").replaceAll("月",""));
		}
		if (StringUtils.isNotEmpty(trFlag)) {
			dGoal.setTrFlag(trFlag);
		}
		dGoal.setStart(this.getStart());
		dGoal.setEnd(this.getEnd());
		size = distributionGoalAppendService
				.getDistributionGoalAppendCount(dGoal);
		if (size != 0) {
			distributionGoalAppendLists = distributionGoalAppendService
					.getDistributionGoalAppendList(dGoal);
		}
		return JSON;
	}

	/**
	 * 页面删除目标add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String deleteDistributionGoalAppend() {
		DistributionGoalAppend dGoal = new DistributionGoalAppend();
		try {
			String[] l = ids.split(",");
			dGoal.setIds(l);

			StringResult result = distributionGoalAppendService
					.deleteDistributionGoalAppend(dGoal);

			if (distributionGoalAppendService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(dGoal, e);
		}
		return RESULT_MESSAGE;
	}

	public String updateDistributionGoalAppend() {
		DistributionGoalAppend dGoal;
		List<DistributionGoalAppend> dGoals = new ArrayList<DistributionGoalAppend>();
		String[] m = { "A1","A3", "B1", "C1", "A4", "A5", "A6", "C6", "A7","C7", "A2",
				"A8", "B8", "C8", "H7","D9","D10","E1" };
		int[] b = { aOne,  aThree, bOne,cOne,aFour,aFive, aSix,cSix,aSeven,cSeven,aTwo,
				aEight,bEight, cEight, hSeven, dNine,dTen,eOne };
		for (int i = 0; i < m.length; i++) {
			dGoal = new DistributionGoalAppend();
			dGoal.setDgId(dgId);
			dGoal.setState("1");
			dGoal.setMatter(m[i]);
			dGoal.setBoxNum(b[i]);
			dGoals.add(dGoal);
		}
		for (int i = 0; i < dGoals.size(); i++) {
			try {
				StringResult result = distributionGoalAppendService
						.updateDistributionGoalAppend(dGoals.get(i));
				if (distributionGoalAppendService.ERROR
						.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					this.setSuccessMessage("处理成功");
				}
			} catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(dGoals, e);
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 导出CSV模板 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv() {
		OutputStream os = null;
		String report_name = "主管业代目标量数据导入模板";
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
			linedata.append("经销商编号");
			linedata.append(",");
			linedata.append("经销商名称");
			linedata.append(",");
			linedata.append("目标年(yyyy)");
			linedata.append(",");
			linedata.append("目标月(mm)");
			linedata.append(",");
			linedata.append("业务姓名");
			linedata.append(",");
			linedata.append("岗位(主管/业代)");
			linedata.append(",");
			linedata.append("直接上级姓名");
			linedata.append(",");
			linedata.append("直接上级岗位");
			linedata.append(",");
			linedata.append("渠道类别");
			linedata.append(",");
			linedata.append("负责的门店类型");
			linedata.append(",");
			linedata.append("A1椰果单杯-80g*30");
			linedata.append(",");
			linedata.append("A3椰果经典装-80g*30");
			linedata.append(",");
			linedata.append("B1红豆单杯-64g*30");
			linedata.append(",");
			linedata.append("C1桂圆红枣单杯-65g*30");
			linedata.append(",");
			linedata.append("A4椰果礼盒装-80g*12*8");
			linedata.append(",");
			linedata.append("A5椰果礼盒装-80g*8*10");
			linedata.append(",");
			linedata.append("A6椰果家庭分享装-80g*16*6");
			linedata.append(",");
			linedata.append("C6桂圆红枣家庭分享装-65g*16*6");
			linedata.append(",");
			linedata.append("A7椰果家庭分享装-80g*15*6");
			linedata.append(",");
			linedata.append("C7桂圆红枣家庭分享装-65g*15*6");
			linedata.append(",");
			linedata.append("A2椰果联杯装-80g*6*6");
			linedata.append(",");
			linedata.append("A8椰果联杯装-80g*3*10");
			linedata.append(",");
			linedata.append("B8红豆联杯装-80g*3*10");
			linedata.append(",");
			linedata.append("C8桂圆红枣联杯装-65g*3*10");
			linedata.append(",");
			linedata.append("H7功夫奶茶单瓶-400ml*15");
			linedata.append(",");
			linedata.append("D9超值组合装");
			linedata.append(",");
			linedata.append("D10豪华礼盒装-15*6");
			linedata.append(",");
			linedata.append("E1豪华礼盒装-12*6");
			linedata.append(",");
			linedata.append("业务人员离职日期(yyyy/mm/dd离职必填)");
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
	 * 批量导入目标By CSV add by allen 目标量维度：组织、年、月、物料 1、组织是否有组织目标量 2、判断系统组织待开目标量是否结余
	 * 3、新增的经销商目标量不能超过组织待开目标量 4、相同的目标量是否已经存在 4、同一组织下的经销商目标量之和不能超过组织待开目标量
	 * 
	 * @return
	 */
	@PermissionSearch
	public String importDistributionGoalAppendCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		try {
			distributionGoalAppendLists = new ArrayList<DistributionGoalAppend>();
			String rcs = "";
			String rcs2 = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File(
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					String[] m = { "A1", "A3", "B1", "C1", "A4", "A5", "A6",
							"C6", "A7","C7", "A2", "A8", "B8", "C8", "H7", "D9",
							"D10", "E1"  };
					DistributionGoalAppend dGoal;
					DistributionGoalAppend dGoal1;
					for (int j = 0; j < content.size(); j++) {
						dGoal1 = new DistributionGoalAppend();
						String[] s = content.get(j);
						if (s.length == header.length
								|| s.length == header.length - 1) {
							int ij;
							rcs = "";
							if (s.length > 29) {
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							ij = 0;
							String kunnrId = s[ij++];
							String kunnr_Name = s[ij++];
							String theYear = s[ij++];
							String theMonth = s[ij++];
							String firstuser = s[ij++];
							String position = s[ij++];
							String dire_super_name = s[ij++];
							String dire_super_position = s[ij++];
							String channel = s[ij++];
							String store_category = s[ij++];
							String resignDate;
							resignDate = s[18 + ij++];
							
							if ((null != kunnrId) && (null != theYear)&& (null != theMonth)
									&& (null != firstuser)) {
								String[] ki = kunnrId.split("/");
								DistributionGoalAppend dG = new DistributionGoalAppend();
								dG.setKunnrId(StringUtils.leftPad(
										String.valueOf(ki[0].trim()), 8, '0'));
								dG.setTheYear(theYear);
								dG.setTheMonth(theMonth);
								dG.setFirstUser(firstuser);
								int size = distributionGoalAppendService
										.getDistributionGoalAppendSize(dG);
								if (size != 0) {
									rcs = rcs + "第" + (j + 2)
											+ "行数据     经销商编号：" + kunnrId
											+ "业务姓名为： " + firstuser
											+ " 在日期： "
											+ theYear + "年" + theMonth + "月" + "已经导入过,请勿重复提交.</br>";
								}
							}
							if (null != firstuser) {
								dGoal1.setFirstUser(firstuser);
							} else {
								rcs = rcs + "第" + (j + 2) + "行业务姓名: 为空.</br>";
							}
							if (null != position) {
								if (position.equalsIgnoreCase("流通主管")
										|| position.equalsIgnoreCase("流通业代")
										|| position.equalsIgnoreCase("商超主管")
										|| position.equalsIgnoreCase("商超业代")
										|| position.equalsIgnoreCase("特通主管")
										|| position.equalsIgnoreCase("特通业代")
										|| position.equalsIgnoreCase("客户经理"))
									dGoal1.setPosition(position);
								else {
									rcs = rcs
											+ "第"
											+ (j + 2)
											+ "行岗位: 必须填客户经理|流通主管|商超主管|特通主管|流通业代|商超业代|特通业代.</br>";
								}
							} else {
								rcs = rcs + "第" + (j + 2)
										+ "行岗位(主管/业代): 为空.</br>";
							}
							if (null != dire_super_name) {
								dGoal1.setDire_super_name(dire_super_name);
							} else {
								rcs = rcs + "第" + (j + 2) + "行直接上级姓名: 为空.</br>";
							}
							if (null != dire_super_position) {
								dGoal1.setDire_super_position(dire_super_position);
							} else {
								rcs = rcs + "第" + (j + 2) + "行直接上级岗位: 为空.</br>";
							}
							if (null != channel) {
								dGoal1.setChannel(channel);
							} else {
								rcs = rcs + "第" + (j + 2) + "行渠道类别: 为空.</br>";
							}
							if (null != store_category) {
								dGoal1.setStore_category(store_category);
							} else {
								rcs = rcs + "第" + (j + 2)
										+ "行负责的门店类型: 为空.</br>";
							}
							if (null != kunnrId) {
								String[] ki = kunnrId.split("/");
								Kunnr kunnr = null;
								List<Kunnr> kunnr1 = new ArrayList<Kunnr>();
								for (int i = 0; i < ki.length; i++) {
									kunnr = new Kunnr();
									kunnr.setKunnr(StringUtils.leftPad(
											String.valueOf(ki[i].trim()), 8,
											'0'));
									kunnr.setPagination("false");
								}
								kunnr1 = kunnrService.kunnrSearch(kunnr);

								if (0 == kunnr1.size()) {
									rcs = rcs + "第" + (j + 2) + "行所属经销商:"
											+ kunnrId + "不存在.</br>";
								} else if (!kunnr_Name.equals(kunnr1.get(0)
										.getName1())) {
									rcs = rcs + "第" + (j + 2) + "行所属经销商名称:"
											+ kunnr_Name + "不正确.</br>";
								} else {
									dGoal1.setKunnrId(StringUtils.leftPad(
											String.valueOf(kunnrId.trim()), 8,
											'0'));
								}
							} else {
								rcs = rcs + "第" + (j + 2) + "行所属经销商: 为空.</br>";
							}

							/*if (null != input_date) {
								String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
								Pattern p = Pattern.compile(eL);
								Matcher ma = p.matcher(input_date);
								boolean flag = ma.matches();
								if (flag) {
									input_date=this.dateChange(input_date);
									dGoal1.setInputDate(input_date);
								} else {
									rcs = rcs
											+ "第"
											+ (j + 2)
											+ "行分销日期格式不对,请按照2014/01/01或2014-01-01格式填写.</br>";
								}
							} else {
								rcs = rcs + "第" + (j + 2) + "行日期: 为空.</br>";
							}*/
							if((theYear != null)&&(theMonth != null)){
								if(theYear.indexOf("年") != -1 || theMonth.indexOf("月") != -1){
									rcs = rcs + "第" + (j + 2) + "行目标年或目标月直接输入数字，不要带年/月单位.</br>";
								}else if(Integer.parseInt(theYear)>2000&&Integer.parseInt(theYear)<2050
										&&Integer.parseInt(theMonth)>=1&&Integer.parseInt(theMonth)<=12){
									String input_date=this.dateChange1(theYear,theMonth);
									dGoal1.setInputDate(input_date);
								}else {
									rcs = rcs + "第" + (j + 2) + "行目标年或目标月输入有误.</br>";
								}
								   
							} else {
								rcs = rcs + "第" + (j + 2) + "行目标年或目标月: 为空.</br>";
							}

							if (null != resignDate) {
								String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
								Pattern p = Pattern.compile(eL);
								Matcher ma = p.matcher(resignDate);
								boolean flag = ma.matches();
								if (flag) {
									resignDate=this.dateChange(resignDate);
									dGoal1.setResign_date(resignDate);
								} else {
									rcs = rcs
											+ "第"
											+ (j + 2)
											+ "行业务人员离职日期格式不对,请按照2014/01/01或2014-01-01格式填写.</br>";
								}
							}

							for (int i = 10; i < header.length - 1; i++) {
								dGoal = new DistributionGoalAppend();
								dGoal.setUserId(this.getUser().getUserId());
								dGoal.setKunnrId(dGoal1.getKunnrId());
								dGoal.setFirstUser(dGoal1.getFirstUser());
								dGoal.setPosition(dGoal1.getPosition());
								dGoal.setDire_super_name(dGoal1
										.getDire_super_name());
								dGoal.setDire_super_position(dGoal1
										.getDire_super_position());
								dGoal.setChannel(dGoal1.getChannel());
								dGoal.setStore_category(dGoal1
										.getStore_category());
								dGoal.setResign_date(dGoal1.getResign_date());
								dGoal.setInputDate(dGoal1.getInputDate());
								dGoal.setInputName(this.getUser().getUserId());
								matter = m[i - 10];
								dGoal.setMatter(matter);
								String box_num = s[i];
								if (null != box_num) {
									int k = 0;
									for (int m_digit = 0; m_digit < box_num
											.length(); m_digit++) {
										char c = box_num.charAt(m_digit);
										if ((c < '0' || c > '9')) {
											k = 1;
											rcs = rcs + "第" + (j + 2) + "行"
													+ header[i]
													+ ": 为非数字的值</br>";
										}
									}
									if (k == 0)
										dGoal.setBoxNum(Integer
												.parseInt(box_num));
								} else {
									rcs = rcs + "第" + (j + 2) + "行" + header[i]
											+ ": 为空,若无数据填0</br>";
								}
								distributionGoalAppendLists.add(dGoal);
							}
						} else {
							rcs = rcs + "第" + (j + 2) + "行列数不对！</br>";
						}
						rcs2 = rcs2 + rcs;
					}
					if (!"".equals(rcs2)) {
						result.append(rcs2.toString() + "</br>");
						this.setFailMessage(result.toString());
						return RESULT_MESSAGE;
					} else {
						if (distributionGoalAppendLists.size() != 0l
								&& result.toString().equals("")) {
							for (int q = 0; q < distributionGoalAppendLists
									.size(); q++) {

								try {
									rcs2 = "";
									result1 = distributionGoalAppendService
											.insertDistributionGoalAppend(distributionGoalAppendLists
													.get(q));
									rcs2 += result1.getCode();
									result.append(rcs2.toString() + "</br>");
								} catch (Exception e) {
									logger.error(e);
									rcs2 += "第" + (q + 2)
											+ "条数据保存数据库失败.请联系系统管理员.</br>";
									result.append(rcs2.toString() + "</br>");
								}
							}
						} else {

							this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
									+ rcs2.toString());
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute(
							"distributionGoalAppendLists",
							distributionGoalAppendLists);
					if (result1.getResult()) {
						this.setSuccessMessage("导入成功！导入数量为:" + content.size()
								+ "行");
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
		}
		return RESULT_MESSAGE;
	}

	private void List2Excel(List<DistributionGoalAppend> dbList) {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "主管业代目标量数据统计列表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setColumnView(0, 11);
			wsheet.setColumnView(1, 11);
			wsheet.setColumnView(2, 9);
			wsheet.setColumnView(3, 9);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 12);
			wsheet.setColumnView(6, 35);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 9);
			wsheet.setColumnView(9, 16);
			wsheet.setColumnView(10, 14);
			wsheet.setColumnView(11, 14);
			wsheet.setColumnView(12, 10);
			wsheet.setColumnView(13, 16);
			
			wsheet.setColumnView(14, 12);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 12);
			wsheet.setColumnView(17, 17);
			wsheet.setColumnView(18, 14);
			wsheet.setColumnView(19, 14);
			wsheet.setColumnView(20, 19);
			wsheet.setColumnView(21, 25);
			wsheet.setColumnView(22, 19);
			wsheet.setColumnView(23, 25);
			wsheet.setColumnView(24, 14);
			wsheet.setColumnView(25, 14);
			wsheet.setColumnView(26, 14);
			wsheet.setColumnView(27, 19);
			wsheet.setColumnView(28, 16);
			wsheet.setColumnView(29, 14);
			wsheet.setColumnView(30, 16);
			wsheet.setColumnView(31, 16);
			wsheet.setColumnView(32, 19);
			
			wsheet.mergeCells(0, 0, 0, 1);
			wsheet.mergeCells(1, 0, 1, 1);
			wsheet.mergeCells(2, 0, 2, 1);
			wsheet.mergeCells(3, 0, 3, 1);
			wsheet.mergeCells(4, 0, 4, 1);
			wsheet.mergeCells(5, 0, 5, 1);
			wsheet.mergeCells(6, 0, 6, 1);
			wsheet.mergeCells(7, 0, 7, 1);
			wsheet.mergeCells(8, 0, 8, 1);
			wsheet.mergeCells(9, 0, 9, 1);
			wsheet.mergeCells(10, 0, 10, 1);
			wsheet.mergeCells(11, 0, 11, 1);
			wsheet.mergeCells(12, 0, 12, 1);
			wsheet.mergeCells(13, 0, 13, 1);

			wsheet.mergeCells(29, 0, 29, 1);
			
			wsheet.mergeCells(32, 0, 32, 1);

			
			
			WritableFont wfcb = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfFG = new WritableCellFormat(wfcb);
			wcfFG.setBackground(jxl.format.Colour.GRAY_25);
			wcfFG.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFG.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFG.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableCellFormat wcfFY = new WritableCellFormat(wfcb);
			wcfFY.setBackground(jxl.format.Colour.YELLOW);
			wcfFY.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFY.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFY.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableCellFormat wcfFT = new WritableCellFormat(wfcb);
			wcfFT.setBackground(jxl.format.Colour.TAN);
			wcfFT.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFT.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFT.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfF = new WritableCellFormat(wfc);
			wcfF.setAlignment(jxl.format.Alignment.CENTRE);
			wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfF.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.HAIR);
            
			wsheet.addCell(new Label(0, 0, "创建人", wcfFG));
			wsheet.addCell(new Label(1, 0, "创建日期", wcfFG));
			wsheet.addCell(new Label(2, 0, "大区", wcfFG));
			wsheet.addCell(new Label(3, 0, "省份", wcfFG));
			wsheet.addCell(new Label(4, 0, "城市", wcfFG));
			wsheet.addCell(new Label(5, 0, "经销商编号", wcfFG));
			wsheet.addCell(new Label(6, 0, "经销商名称 ", wcfFG));
			wsheet.addCell(new Label(7, 0, "目标量日期", wcfFG));
			wsheet.addCell(new Label(8, 0, "业务姓名", wcfFG));
			wsheet.addCell(new Label(9, 0, "岗位(主管/业代)", wcfFG));
			wsheet.addCell(new Label(10, 0, "直接上级姓名", wcfFG));
			wsheet.addCell(new Label(11, 0, "直接上级岗位", wcfFG));
			wsheet.addCell(new Label(12, 0, "渠道类别", wcfFG));
			wsheet.addCell(new Label(13, 0, "负责的门店类型", wcfFG));
			
			wsheet.addCell(new Label(14, 0, "A1椰果单杯", wcfFT));
			wsheet.addCell(new Label(14, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(15, 0, "A3椰果经典装", wcfFT));
			wsheet.addCell(new Label(15, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(16, 0, "B1红豆单杯", wcfFT));
			wsheet.addCell(new Label(16, 1, "64g*30", wcfFT));
			wsheet.addCell(new Label(17, 0, "C1桂圆红枣单杯", wcfFT));
			wsheet.addCell(new Label(17, 1, "65g*30", wcfFT));
			wsheet.addCell(new Label(18, 0, "A4椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(18, 1, "80g*12*8", wcfFT));
			wsheet.addCell(new Label(19, 0, "A5椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(19, 1, "80g*8*10", wcfFT));
			wsheet.addCell(new Label(20, 0, "A6椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(20, 1, "80g*16*6", wcfFT));
			wsheet.addCell(new Label(21, 0, "C6桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(21, 1, "65g*16*6", wcfFT));
			wsheet.addCell(new Label(22, 0, "A7椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(22, 1, "80g*15*6", wcfFT));
			wsheet.addCell(new Label(23, 0, "C7桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(23, 1, "65g*15*6", wcfFT));
			wsheet.addCell(new Label(24, 0, "A2椰果联杯装", wcfFT));
			wsheet.addCell(new Label(24, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(25, 0, "A8椰果联杯装", wcfFT));
			wsheet.addCell(new Label(25, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(26, 0, "B8红豆联杯装", wcfFT));
			wsheet.addCell(new Label(26, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(27, 0, "C8桂圆红枣联杯装", wcfFT));
			wsheet.addCell(new Label(27, 1, "65g*3*10", wcfFT));
			wsheet.addCell(new Label(28, 0, "H7功夫奶茶单瓶", wcfFT));
			wsheet.addCell(new Label(28, 1, "400ml*15", wcfFT));
			wsheet.addCell(new Label(29, 0, "D9超值组合装", wcfFT));
			wsheet.addCell(new Label(30, 0, "D10豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(30, 1, "15*6", wcfFT));
			wsheet.addCell(new Label(31, 0, "E1豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(31, 1, "12*6", wcfFT));
			wsheet.addCell(new Label(32, 0, "业务人员离职日期", wcfFG));
			
			for (int i = 1; i <= dbList.size(); i++) {
				
				wsheet.addCell(new Label(0, i+1, dbList.get(i - 1)
						.getInputName(), wcfF));
				wsheet.addCell(new Label(1, i+1, dbList.get(i - 1)
						.getCreateDate(), wcfF));
				wsheet.addCell(new Label(2, i+1,
						dbList.get(i - 1).getOrgRegion(), wcfF));
				wsheet.addCell(new Label(3, i+1, dbList.get(i - 1)
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(4, i+1, dbList.get(i - 1).getOrgCity(),
						wcfF));
				wsheet.addCell(new Label(5, i+1, dbList.get(i - 1).getKunnrId(),
						wcfF));
				wsheet.addCell(new Label(6, i+1,dbList.get(i - 1).getKunnrName(), 
						wcfF));
				wsheet.addCell(new Label(7, i+1, dbList.get(i - 1)
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(8, i+1,
						dbList.get(i - 1).getFirstUser(), wcfF));
				wsheet.addCell(new Label(9, i+1, dbList.get(i - 1).getPosition(),
						wcfF));
				wsheet.addCell(new Label(10, i+1, dbList.get(i - 1)
						.getDire_super_name(), wcfF));
				wsheet.addCell(new Label(11, i+1, dbList.get(i - 1)
						.getDire_super_position(), wcfF));
				wsheet.addCell(new Label(12, i+1, dbList.get(i - 1).getChannel(),
						wcfF));
				wsheet.addCell(new Label(13, i+1, dbList.get(i - 1)
						.getStore_category(), wcfF));
				
				wsheet.addCell(new Number(14, i+1, Integer.valueOf(dbList.get(
						i - 1).getaOne()), wcfF));
				wsheet.addCell(new Number(15, i+1, Integer.valueOf(dbList.get(
						i - 1).getaThree()), wcfF));
				wsheet.addCell(new Number(16, i+1, Integer.valueOf(dbList.get(
						i - 1).getbOne()), wcfF));
				wsheet.addCell(new Number(17, i+1, Integer.valueOf(dbList.get(
						i - 1).getcOne()), wcfF));
				wsheet.addCell(new Number(18, i+1, Integer.valueOf(dbList.get(
						i - 1).getaFour()), wcfF));
				wsheet.addCell(new Number(19, i+1, Integer.valueOf(dbList.get(
						i - 1).getaFive()), wcfF));
				wsheet.addCell(new Number(20, i+1, Integer.valueOf(dbList.get(
						i - 1).getaSix()), wcfF));
				wsheet.addCell(new Number(21, i+1, Integer.valueOf(dbList.get(
						i - 1).getcSix()), wcfF));
				wsheet.addCell(new Number(22, i+1, Integer.valueOf(dbList.get(
						i - 1).getaSeven()), wcfF));
				wsheet.addCell(new Number(23, i+1, Integer.valueOf(dbList.get(
						i - 1).getcSeven()), wcfF));
				wsheet.addCell(new Number(24, i+1, Integer.valueOf(dbList.get(
						i - 1).getaTwo()), wcfF));
				wsheet.addCell(new Number(25, i+1, Integer.valueOf(dbList.get(
						i - 1).getaEight()), wcfF));
				wsheet.addCell(new Number(26, i+1, Integer.valueOf(dbList.get(
						i - 1).getbEight()), wcfF));
				wsheet.addCell(new Number(27, i+1, Integer.valueOf(dbList.get(
						i - 1).getcEight()), wcfF));
				wsheet.addCell(new Number(28, i+1, Integer.valueOf(dbList.get(
						i - 1).gethSeven()), wcfF));
				wsheet.addCell(new Number(29, i+1, Integer.valueOf(dbList.get(
						i - 1).getdNine()), wcfF));
				wsheet.addCell(new Number(30, i+1, Integer.valueOf(dbList.get(
						i - 1).getdTen()), wcfF));
				wsheet.addCell(new Number(31, i+1, Integer.valueOf(dbList.get(
						i - 1).geteOne()), wcfF));
				wsheet.addCell(new Label(32, i+1, dbList.get(i - 1)
						.getResign_date(), wcfF));
			}
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
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

	public String exportDistributionGoalAppend() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		distributionGoalAppendLists = new ArrayList<DistributionGoalAppend>();
		DistributionGoalAppend dbDataAppend = new DistributionGoalAppend();
		dbDataAppend.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();

		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		try {
			distributionGoalAppendLists = new ArrayList<DistributionGoalAppend>();
			if (StringUtils.isNotEmpty(kunnrId)) {
				dbDataAppend.setKunnrId(kunnrId);
			}
			if (StringUtils.isNotEmpty(kunnrName)) {
				dbDataAppend.setKunnrName(kunnrName);
			}
			if (StringUtils.isNotEmpty(startDate)) {
				dbDataAppend.setStartDate(startDate);
			}
			if (StringUtils.isNotEmpty(endDate)) {
				dbDataAppend.setEndDate(endDate);
			}

			if (StringUtils.isNotEmpty(trFlag)) {
				dbDataAppend.setTrFlag(trFlag);
			}
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					dbDataAppend.setOrgId(str[1]);
				} else {
					dbDataAppend.setOrgId(orgId);
				}
			}
			dbDataAppend.setStart(0);
			dbDataAppend.setEnd(100000000);
			distributionGoalAppendLists = distributionGoalAppendService
					.getDistributionGoalAppendList(dbDataAppend);

			if (distributionGoalAppendLists.size() == 0) {
				this.setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
			}
			List2Excel(distributionGoalAppendLists);
			ServletActionContext.getRequest().getSession()
					.setAttribute("DownLoad", "Over");

		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;

	}

	public String orgTreePage() {
		userId = this.getUser().getUserId();
		Borg borg = orgServiceHessian.getOrgByUserId(userId);
		if ("B".equals(borg.getOrgCity())) {
			return "orgAllTree";
		}
		return "orgtree";
	}
	
	public String dateChange(String date){
		String dateRen = null;
		String year = "";
		String month = "";
		String day = "";
		if(date.indexOf("-")!=-1){
			String [] date_arr1 = date.split("\\-");
			year = date_arr1[0];
			if(date_arr1[1].length()==1)
				month = "0"+date_arr1[1];
			else
				month = date_arr1[1];
			if(date_arr1[2].length()==1)
				day = "0"+date_arr1[2];
			else
				day = date_arr1[2];
			dateRen = year + "-" + month + "-" + day ;
			
		}else if(date.indexOf("/")!=-1){
			String [] date_arr2 = date.split("\\/");
			year = date_arr2[0];
			if(date_arr2[1].length()==1)
				month = "0"+date_arr2[1];
			else
				month = date_arr2[1];
			if(date_arr2[2].length()==1)
				day = "0"+date_arr2[2];
			else
				day = date_arr2[2];
			dateRen = year + "-" + month + "-" + day ;
		}
		return dateRen;
	}
	
	public String dateChange1(String theYear,String theMonth){
		String dateRen = null;
		String year = "";
		String month = "";
		
			if(theMonth.length()==1)
				month = "0"+theMonth;
			else 
				month = theMonth;
			year = theYear;
			dateRen = year + "-" + month;
			
		return dateRen;
	}


	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		DistributionGoalAppendAction.logger = logger;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgRegion() {
		return orgRegion;
	}

	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}

	public String getOrgProvince() {
		return orgProvince;
	}

	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDire_super_name() {
		return dire_super_name;
	}

	public void setDire_super_name(String dire_super_name) {
		this.dire_super_name = dire_super_name;
	}

	public String getDire_super_position() {
		return dire_super_position;
	}

	public void setDire_super_position(String dire_super_position) {
		this.dire_super_position = dire_super_position;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getTrFlag() {
		return trFlag;
	}

	public void setTrFlag(String trFlag) {
		this.trFlag = trFlag;
	}

	public int getaOne() {
		return aOne;
	}

	public void setaOne(int aOne) {
		this.aOne = aOne;
	}

	public int getaTwo() {
		return aTwo;
	}

	public void setaTwo(int aTwo) {
		this.aTwo = aTwo;
	}

	public int getaThree() {
		return aThree;
	}

	public void setaThree(int aThree) {
		this.aThree = aThree;
	}

	public int getaFour() {
		return aFour;
	}

	public void setaFour(int aFour) {
		this.aFour = aFour;
	}

	public int getaFive() {
		return aFive;
	}

	public void setaFive(int aFive) {
		this.aFive = aFive;
	}

	public int getaSix() {
		return aSix;
	}

	public void setaSix(int aSix) {
		this.aSix = aSix;
	}

	public int getaSeven() {
		return aSeven;
	}

	public void setaSeven(int aSeven) {
		this.aSeven = aSeven;
	}

	public int getaEight() {
		return aEight;
	}

	public void setaEight(int aEight) {
		this.aEight = aEight;
	}

	public int getbOne() {
		return bOne;
	}

	public void setbOne(int bOne) {
		this.bOne = bOne;
	}

	public int getbSix() {
		return bSix;
	}

	public void setbSix(int bSix) {
		this.bSix = bSix;
	}

	public int getdNine() {
		return dNine;
	}

	public void setdNine(int dNine) {
		this.dNine = dNine;
	}

	public int gethSeven() {
		return hSeven;
	}

	public void sethSeven(int hSeven) {
		this.hSeven = hSeven;
	}

	public int getcOne() {
		return cOne;
	}

	public void setcOne(int cOne) {
		this.cOne = cOne;
	}

	public int getcSix() {
		return cSix;
	}

	public void setcSix(int cSix) {
		this.cSix = cSix;
	}

	public int getcSeven() {
		return cSeven;
	}

	public void setcSeven(int cSeven) {
		this.cSeven = cSeven;
	}

	public int getcEight() {
		return cEight;
	}

	public void setcEight(int cEight) {
		this.cEight = cEight;
	}
	
	

	public int getbEight() {
		return bEight;
	}

	public void setbEight(int bEight) {
		this.bEight = bEight;
	}

	public int getdTen() {
		return dTen;
	}

	public void setdTen(int dTen) {
		this.dTen = dTen;
	}
	
	

	public int geteOne() {
		return eOne;
	}

	public void seteOne(int eOne) {
		this.eOne = eOne;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public IDistributionGoalAppendService getDistributionGoalAppendService() {
		return distributionGoalAppendService;
	}

	public void setDistributionGoalAppendService(
			IDistributionGoalAppendService distributionGoalAppendService) {
		this.distributionGoalAppendService = distributionGoalAppendService;
	}

	public void setDistributionGoalAppendLists(
			List<DistributionGoalAppend> distributionGoalAppendLists) {
		this.distributionGoalAppendLists = distributionGoalAppendLists;
	}

	public List<DistributionGoalAppend> getDistributionGoalAppendLists() {
		return distributionGoalAppendLists;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStore_category() {
		return store_category;
	}

	public void setStore_category(String store_category) {
		this.store_category = store_category;
	}

	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public long getDgId() {
		return dgId;
	}

	public void setDgId(long dgId) {
		this.dgId = dgId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public DistributionGoalAppend getdGoal() {
		return dGoal;
	}

	public void setdGoal(DistributionGoalAppend dGoal) {
		this.dGoal = dGoal;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

}
