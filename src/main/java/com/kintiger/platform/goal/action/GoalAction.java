package com.kintiger.platform.goal.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.channel.pojo.Tree4Ajax;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.framework.util.ExportExcelUtil;
import com.kintiger.platform.framework.util.JavaBeanXMLUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.framework.util.TokenProccessor;
import com.kintiger.platform.framework.util.XMLInfo;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.INewOrgService;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.webservice.resps.JsonUtil;
import com.kintiger.platform.wfe.pojo.UserUtil;
import com.kintiger.platform.wfe.service.IWfeService;
/**
 * Title: 销售协议目标量维护
 * Description: crmPlatform
 * @author: xg.chen
 * @date:2017年1月10日 上午9:22:34
 */
public class GoalAction extends BaseAction {
	private static final long serialVersionUID = -638516699213585547L;
	private static Log logger = LogFactory.getLog(GoalAction.class);
	private IWfeService wfeServiceHessian;
	private IGoalService goalService;
	private IDictService dictServiceHessian;
	private IAllUserService allUserServiceHessian;
	private IOrgService orgServiceHessian;
	private BCustomerTarget bct;
	private Materiel mat;
	private int size;
	private int size1;
	private String brand;
	private int year;
	private String month;
	private int theYear;
	private String theMonth;
	private String flagTemp;
	private Kunnr kunnr;
	private List<CmsTbDict> dictlist = new ArrayList<CmsTbDict>();
	private List<BCustomerTarget> bctList = new ArrayList<BCustomerTarget>();
	private List<BCustomerTarget> bctList1 = new ArrayList<BCustomerTarget>();
	private List<Materiel> matList = new ArrayList<Materiel>();
	private List<Kunnr> kunnrlist = new ArrayList<Kunnr>();
	@Decode
	private String value;
	private String box;
	private BigDecimal targetNum;
	private String id;
	private String wid;
	private String ctId;
	private String ids;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private String userId;
	private String nextUserId;
	private String eventId;
	private String title;
	private static String key4open = "fix_jxsSalesTarget";
	private String appUrl;
	private String subFolders;
	private UserUtil userUtil; // 下个处理人列表
	// private boolean executeResult;// 事务处理结束返回信息
	private String modifyFlag;
	private String curStaId;
	private String detailJsonStr; // 明细Json字符串
	private String orgId;
	private String maktx01;// 品牌名
	private String matnr01;// 品牌编码
	@Decode
	private String orgName;
	private BooleanResult executeResult;// 事务处理结束返回信息
	private List<Tree4Ajax> treeList;
	private String node;
	private String custId;
	@Decode
	private String matter;
	private String mark;
	private String type;
	private String kunnrGoalType;
	@Decode
	private String bezei;
	private String mvgr1;
	private String trFlag;
	private String approveFlag;

	private String createDate;
	@Decode
	private String startDate;
	@Decode
	private String endDate;
	private String type_ny;
	private String conFlag;
	private double countGoal;
	private MatterPriceObject priceObj;
	private List<MatterPriceObject> priceObjList;
	@Decode
	private String remark;
	private String eventTitle;
	/*
	 * 目标量物料定价(BO报表使用)
	 */
	private MatterPriceBO priceBo;// 目标量物料定价实体
	private List<MatterPriceBO> priceBoList;
	@Decode
	private String mvgTxt;// 预算口径描述
	@Decode
	private String cupType;// 杯类型
	private String fiscalYear;// 财年
	private File fileContent;// 导入表格内容
	private String fileContentFileName;
	private String download; // 下载是否现在完成标识

	private String fileName;
	private GoalSales goalSales;
	private List<GoalSales> goalSalesList;
	private GoalSalesDetail goalSalesDetail;
	private List<GoalSalesDetail> goalSalesDetailList;
	private Long changeId;
	private String token;
	private String eventState;

	private String yearFlag;// 财年
	private PrintContractGoalInfo printConGoalInfo;
	private List<PrintContractGoalInfo> pcgInfoList;
	private List<PrintContractGoalInfo> pcgInfoList2;
	@Decode
	private String userName;
	private JSONArray pcgInfoJson;
	private JSONArray pcgInfoJson2;

	public String toPrintContractGoal() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		return "toPrintContractGoal";
	}
	/**
	 * 协议目标量香飘飘打印预览
	 * @return
	 */
	public String printContractGoal() {
		printConGoalInfo = new PrintContractGoalInfo();
		if (StringUtils.isNotEmpty(yearFlag)) {
			printConGoalInfo.setYearFlag(yearFlag);
		}
		if (StringUtils.isNotEmpty(custId)) {
			printConGoalInfo.setCustId(custId);
		}
		if(StringUtils.isNotEmpty(orgId)){
			if(newOrgService.getOrgCount("51235", orgId)>0&&!"51235".equals(orgId)){
				printConGoalInfo.setOrgId(orgId);
			}else{
				printConGoalInfo.setOrgId("51235");
			}
			
		}else{
			this.setFailMessage("出错");
			return RESULT_MESSAGE;
		}
		
		pcgInfoList = goalService.searchConGolInfo(printConGoalInfo);
		pcgInfoList2 = goalService.searchConGolInfo2(printConGoalInfo);
		pcgInfoJson = JSONArray.fromObject(pcgInfoList);
		pcgInfoJson2 = JSONArray.fromObject(pcgInfoList2);
		return "toPrintContractGoalView";
	}
	/**
	 * 
	 * 协议目标量lfy&meco打印预览
	 * @return
	 */
	private INewOrgService newOrgService;
	public String printContractGoal2() {
		printConGoalInfo = new PrintContractGoalInfo();
		if (StringUtils.isNotEmpty(yearFlag)) {
			printConGoalInfo.setYearFlag(yearFlag);
		}
		if (StringUtils.isNotEmpty(custId)) {
			printConGoalInfo.setCustId(custId);
		}
		if(StringUtils.isNotEmpty(orgId)){
			if(newOrgService.getOrgCount("51235", orgId)>0&&!"51235".equals(orgId)){
				printConGoalInfo.setOrgId(orgId);
			}else{
				printConGoalInfo.setOrgId("51235");
			}
			
		}else{
			this.setFailMessage("出错");
			return RESULT_MESSAGE;
		}
		pcgInfoList2 = goalService.searchConGolInfo3(printConGoalInfo);
		pcgInfoJson2 = JSONArray.fromObject(pcgInfoList2);
		return "toPrintContractGoalView2";
	}
	
	/**
	 * MethodsTitle: 目标量维护查询跳转
	 * 
	 * @author: xg.chen
	 * @date:2016年12月22日 下午3:28:56
	 * @version 1.0
	 * @return kunnrGoalType=B GoalSearchViewPre 销售目标量页面 
	 *         kunnrGoalType=C GoalSearchContractPre 协议目标量页面
	 */
	@PermissionSearch
	public String GoalSearchPre() {
		userId = this.getUser().getUserId();
		int n = goalService.getConpointByUser(userId, approveFlag);
		if (0 < n) {
			flagTemp = "Y";
		}
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		if ("C".equals(kunnrGoalType)) {
			return "GoalSearchContractPre";
		} else {
			return "GoalSearchPre";
		}
	}

	public String toGoalReport() {
		userId = this.getUser().getUserId();
		int n = goalService.getConpointByUser(userId, approveFlag);
		if (0 < n) {
			flagTemp = "Y";
		}
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		return "toGoalReport";
	}

	public String GoalSearchViewPre() {
		Calendar ctime = Calendar.getInstance();
		// int year = ctime.get(Calendar.YEAR);
		// int month = ctime.get(Calendar.MONTH) + 1;
		userId = this.getUser().getUserId();
		int n = goalService.getConpointByUser(userId, approveFlag);
		if (0 < n) {
			flagTemp = "Y";
		}
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		// this.setYear(year);
		// this.setMonth(StringUtils.leftPad(String.valueOf(month), 2, '0'));
		if ("C".equals(kunnrGoalType)) {
			return "GoalSearchContractPre";
		} else {
			return "GoalSearchViewPre";
		}
	}

	/**
	 * 经销商目标维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createGoalPrepare() {
		Calendar ctime = Calendar.getInstance();
		int year = ctime.get(Calendar.YEAR);
		int month1 = ctime.get(Calendar.MONTH) + 1;
		this.setYear(year);
		this.setMonth(StringUtils.leftPad(String.valueOf(month1), 2, '0'));
		month = StringUtils.leftPad(String.valueOf(month1), 2, '0');
		if ("C".equals(kunnrGoalType)) {
			return "createGoalContractPrepare";
		} else {
			return "createGoalPrepare";
		}
	}

	/**
	 * 经销商组织目标维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createOrgGoalPrepare() {
		Calendar ctime = Calendar.getInstance();
		int year = ctime.get(Calendar.YEAR);
		int month = ctime.get(Calendar.MONTH) + 1;
		this.setYear(year);
		this.setMonth(StringUtils.leftPad(String.valueOf(month), 2, '0'));
		return "createOrgGoalPrepare";
	}

	/**
	 * 经销商目标修改跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateGoalPrepare() {
		if (StringUtils.isNotEmpty(ctId) && StringUtils.isNotEmpty(ctId.trim())) {
			try {
				ctId = new String(ctId.trim().getBytes("ISO8859-1"), "UTF-8");/* 转码你懂么 */
				// mark = new String(mark.trim().getBytes("ISO8859-1"),
				// "UTF-8");/*转码你懂么*/
				bct = goalService.getGoalById(ctId);
			} catch (UnsupportedEncodingException e) {
				logger.error(ctId, e);
			}
		}
		bct = bct == null ? new BCustomerTarget() : bct;
		if (null != bct) {
			Materiel mat1 = new Materiel();
			mat1.setMvgr1(bct.getMatter());
			mat1.setBezei(bct.getBezei());
			mat1.setCreateDate(bct.getCreateDate());
			mat1.setPagination("false");
			List<Materiel> mList = goalService.getMaterielViewList(mat1);
			BigDecimal b1 = new BigDecimal(0);
			if (null != mList && mList.size() > 0) {
				mat = mList.get(0);
				b1 = new BigDecimal(mat.getKbetr());
			}
			BigDecimal b2 = new BigDecimal(bct.getBox());
			bct.setTargetNum(b1.multiply(b2));
		}
		return "updateGoalPrepare";
	}

	/* 查字典 add by allen */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "dictlist", include = { "itemName", "itemId" })
	public String getDictTypeList() {
		CmsTbDictType cm = new CmsTbDictType();
		// CmsTbDict ct = new CmsTbDict();
		cm.setDictTypeName("年份");
		cm.setPagination("false");
		dictlist = dictServiceHessian.getDictListByDictType(cm);
		return JSON;
	}

	/**
	 * 查询目标 add byws
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "bctList", total = "size", include = { "ctId", "orgId",
			"custId", "targetNum", "theYear", "theMonth", "createDate",
			"modifyDate", "opId", "checkOpId", "custNameZH", "opName",
			"checkOpName", "trFlag", "box", "bezei", "matter", "kunnrGoalType",
			"orgName", "mark", "contractBox", "contractNum", "fxBox", "fxNum","status","brand"})
	public String getGoalReportList() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		List<BCustomerTarget> bctList_ = new ArrayList<BCustomerTarget>();
		if (bct == null) {
			bct = new BCustomerTarget();
		}
		if (StringUtils.isNotEmpty(matter)) {
			bct.setMatter(matter);
		}
		if (StringUtils.isNotEmpty(brand)) {
			bct.setBrand(brand);
		}
		bct.setCustId(custId);
		if (StringUtils.isNotEmpty(orgId)) {
			bct.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			bct.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			bct.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			bct.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}else if(StringUtils.isEmpty(startDate)){
			bct.setStartDate("2000-01");
			bct.setEndDate("2000-01");
		}
		/*
		 * bct.setTheYear(Long.valueOf(year)); bct.setTheMonth(month);
		 *//* 此乃搜索条件 */
		bct.setMark("Y");
		bct.setKunnrGoalType(kunnrGoalType);
		bct.setTrFlag(trFlag);
		bct.setStart(getStart());
		bct.setEnd(getEnd());
		bct.setPagination("true");
		BCustomerTarget c = new BCustomerTarget();
		BCustomerTarget c2 = new BCustomerTarget();
		if (type_ny.equals("N")) {
			if (conFlag.equals("N")) {
				size = goalService.getGoalListCountForMBL(bct);
				if (size != 0) {
					bctList = goalService.getGoalReportListForMBL(bct);
				}
			} else {
				if (StringUtils.isEmpty(custId)) {
					size = goalService.getGoalDKCount(bct);
					if (size > 0) {
						bctList = goalService.getGoalDK(bct);
					}
				}
			}

		} else {
			if (StringUtils.isNotEmpty(matter)) {
				size = 0;
				bctList = null;
			} else {

				if (conFlag.equals("N")) {
					size = goalService.getGoalSumCount(bct);
					if (size > 0) {
						bctList = goalService.getGoalSum(bct);
					}
				} else {
					if (StringUtils.isEmpty(custId)) {
						size = goalService.getGoalDKAllCount(bct);
						if (size > 0) {
							bctList = goalService.getGoalDKAll(bct);
						}
					}
				}
			}
		}
		return JSON;
	}

	/**
	 * MethodsTitle: 查询目标
	 * @author: xg.chen
	 * @date:2017年1月9日 下午4:53:38
	 * @version 1.0
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "bctList", total = "size", include = { "ctId", "orgId",
			"custId", "targetNum", "theYear", "theMonth", "createDate",
			"modifyDate", "opId", "checkOpId", "custNameZH", "opName",
			"checkOpName", "trFlag", "box", "bezei", "maktx01", "matnr01",
			"matter", "kunnrGoalType", "orgName", "mark", "boxD", "targetNumD" })
	public String getGoalList() {
		if (bct == null) {
			bct = new BCustomerTarget();
		}
		bct.setMatter(matter);
		bct.setCustId(custId);
		if (StringUtils.isNotEmpty(orgId)) {
			bct.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			bct.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			bct.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			bct.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}
		bct.setMark(mark);
		bct.setKunnrGoalType(kunnrGoalType);
		bct.setTrFlag(trFlag);
		bct.setBrand(brand);
		bct.setStart(getStart());
		bct.setEnd(getEnd());
		size = goalService.getGoalListCount(bct);
		if (size != 0) {
			bctList = goalService.getGoalList(bct);
		}
		return JSON;
	}

	@JsonResult(field = "countGoal")
	public String getGoalCountNum() {
		bct = new BCustomerTarget();
		bct.setMatter(matter);
		bct.setCustId(custId);
		if (StringUtils.isNotEmpty(orgId)) {
			bct.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			bct.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			bct.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			bct.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}
		bct.setMark(mark);
		bct.setKunnrGoalType(kunnrGoalType);
		bct.setBrand(brand);
		countGoal = goalService.getGoalCountNum(bct);
		return JSON;
	}

	/**
	 * 查询目标 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "matList", total = "size", include = { "matnr",
			"matkl", "maktx", "meins", "mvgr1", "bezei", "wgbez" })
	public String getMatList() {
		if (mat == null) {
			mat = new Materiel();
		}
		mat.setStart(getStart());
		mat.setEnd(getEnd());
		size = goalService.getMatListCount(mat);
		if (size != 0) {
			matList = goalService.getMatList(mat);
		}
		return JSON;
	}

	/**
	 * 查询物料 add by ljwang
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "matList", total = "size", include = { "mvgr1", "bezei" })
	public String getMaterielList() {
		if (mat == null) {
			mat = new Materiel();
		}
		if (StringUtils.isNotEmpty(bezei)) {
			mat.setBezei(bezei);
		}
		if (StringUtils.isNotEmpty(mvgr1)) {
			mat.setMvgr1(mvgr1);
		}
		if (StringUtils.isNotEmpty(value)) {
			mat.setSearch(value);
			// try {
			// value = new String(this.getValue().getBytes("ISO8859-1"),
			// "utf-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
		}
		if (StringUtils.isNotEmpty(brand)) {
			mat.setBrand(brand);
		}
		mat.setStart(getStart());
		mat.setEnd(getEnd());
		size = goalService.getMaterielListCount(mat);
		if (size != 0) {
			matList = goalService.getMaterielList(mat);
		}
		return JSON;
	}

	/**
	 * 查询物料 及价格add by ljwang
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "matList", total = "size", include = { "mvgr1",
			"bezei", "kbetr" })
	public String getMaterielViewList() {
		if (mat == null) {
			mat = new Materiel();
		}
		if (StringUtils.isNotEmpty(bezei)) {
			mat.setBezei(bezei);
		}
		if (StringUtils.isNotEmpty(mvgr1)) {
			mat.setMvgr1(mvgr1);
		}
		if (StringUtils.isNotEmpty(value)) {
			try {
				value = new String(this.getValue().getBytes("ISO8859-1"),
						"utf-8");
				mat.setSearch(value);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		if (StringUtils.isNotEmpty(createDate)) {
			mat.setCreateDate(createDate);
		}
		mat.setStart(getStart());
		mat.setEnd(getEnd());
		size = goalService.getMaterielViewListCount(mat);
		if (size != 0) {
			matList = goalService.getMaterielViewList(mat);
		}
		return JSON;
	}

	/**
	 * 查询客户 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "kunnrlist", total = "size", include = { "id", "kunnr",
			"name1", "mobNumber", "orgId", "orgName", "status" })
	public String getKunner() {
		Kunnr kunnr = new Kunnr();
		if (StringUtils.isNotEmpty(orgId)) {
			kunnr.setOrgId(orgId);
		}
		try {
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				// value = new String(this.getValue().getBytes("ISO8859-1"),
				// "utf-8");
				// String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				boolean isNum = value
						.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");// 判断是否为汉字
				if (isNum) {
					kunnr.setName1(value.trim());
				} else {
					kunnr.setKunnr(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		kunnr.setStart(getStart());
		kunnr.setEnd(getEnd());
		size = goalService.getKunnrListCount(kunnr);
		if (size != 0) {
			kunnrlist = goalService.getKunnrsList(kunnr);
		}
		return JSON;

	}

	/**
	 * 拿到经销商add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "kunnrlist", total = "size", include = { "id", "kunnr",
			"name1", "mobNumber", "orgId", "orgName", "status" })
	public String getKunnerJsonList() {
		Kunnr kunnr = new Kunnr();
		kunnrlist = new ArrayList<Kunnr>();
		try {
			kunnr.setStart(getStart());
			kunnr.setEnd(getEnd());
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				value = new String(this.getValue().getBytes("ISO8859-1"),
						"utf-8");
				// String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				boolean isNum = value
						.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");// 判断是否为汉字
				if (isNum) {
					kunnr.setName1(value.trim());
				} else {
					kunnr.setKunnr(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		size = goalService.getKunnrListCount(kunnr);
		if (size != 0) {
			kunnrlist = goalService.getKunnrsList(kunnr);
		} else {
			kunnrlist = null;
		}
		return JSON;
	}

	/**
	 * 拿到物料号add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "matList", total = "size", include = { "matnr",
			"matkl", "maktx", "meins", "wgbez", "bezei" })
	public String getMatJsonList() {
		Materiel mat = new Materiel();
		matList = new ArrayList<Materiel>();
		try {
			mat.setStart(getStart());
			mat.setEnd(getEnd());
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				value = new String(this.getValue().getBytes("ISO8859-1"),
						"utf-8");
				// String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				boolean isNum = value.matches("[0-9]+");// 判断是否为数字
				if (isNum) {
					mat.setMatnr(value.trim());
				} else {
					mat.setMaktx(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		size = goalService.getMatListCount(mat);
		if (size != 0) {
			matList = goalService.getMatList(mat);
		} else {
			matList = null;
		}
		return JSON;
	}

	/**
	 * 页面创建目标add by allen
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	public String createGoal() {
		bct = new BCustomerTarget();
		try {
			bct.setBox(box);
			bct.setTargetNum(targetNum);
			bct.setCustId(id);
			bct.setTheMonth(theMonth);
			bct.setTheYear(Long.valueOf(theYear));
			bct.setOpId(getUser().getUserId().toString());
			bct.setTrFlag("S");
			bct.setCtState("0");
			bct.setMatter(wid);
			bct.setKunnrGoalType(kunnrGoalType);
			bct.setMark(mark);
			if ("Y".equals(mark)) {
				bct.setOrgId(orgId);
				if ("B".equals(kunnrGoalType)) {
					/*
					 * 预算目标量不能超过组织待开目标量
					 */
					BCustomerTarget tar = new BCustomerTarget();
					tar.setOrgId(bct.getOrgId());
					tar.setMatter(wid);
					tar.setTheYear(Long.valueOf(theYear));
					tar.setTheMonth(theMonth);
					CmsTbDictType cm = new CmsTbDictType();
					cm.setDictTypeName("财务年");
					cm.setPagination("false");
					dictlist = dictServiceHessian.getDictListByDictType(cm);
					CmsTbDict cmsTbDict = new CmsTbDict();
					String str = theMonth.length() == 2 ? theMonth
							: 0 + theMonth;
					Long budgetTime = Long.parseLong(theYear + str);
					for (int c = 0; c < dictlist.size(); c++) {
						String[] financialYear1 = dictlist.get(c)
								.getItemValue().split("-");
						String[] financialYear2 = dictlist.get(c)
								.getItemDescription().split("-");
						Long cmsTime1 = Long.parseLong(financialYear1[0]
								.toString() + financialYear1[1].toString());
						Long cmsTime2 = Long.parseLong(financialYear2[0]
								.toString() + financialYear2[1].toString());
						if (budgetTime >= cmsTime1 && budgetTime <= cmsTime2) {
							cmsTbDict = dictlist.get(c);
						}
					}
					// / tar.setStartDate(cmsTbDict.getItemValue());
					// tar.setEndDate(cmsTbDict.getItemDescription());
					// 组织财年内某物料的待开目标量
					// tar = goalService.getGoalMessegeOnYearAndMatter(tar);
					tar = goalService.getGoalMessege(tar);
					if (null != tar) {
						if (Double.valueOf(tar.getBox()) <= 0) {
							this.setFailMessage("该组织下该物料待开目标量不足！");
							return RESULT_MESSAGE;
						} else {
							double b = Double.valueOf(tar.getBox());
							double target = Double.valueOf(box);
							if (target > b) {
								this.setFailMessage("新增的经销商目标量超过了该组织待开目标量，请重试！");
								return RESULT_MESSAGE;
							}
						}
					} else {
						this.setFailMessage("所在的财务年该组织还没有维护此种物料的目标量！");
						return RESULT_MESSAGE;
					}
				} else {
					bct.setTrFlag("T");
				}
			} else {
				bct.setOrgId(orgId);
			}
			if ("Y".equals(bct.getMark())) {
				if (goalService.getGoalListCount1(bct) != 0) {
					this.setFailMessage(bct.getTheYear() + "年"
							+ bct.getTheMonth() + "月该客户相同类型的经销商物料号数据库已存在");
					return RESULT_MESSAGE;
				}
			} else {
				if (goalService.getGoalListCount1(bct) != 0l) {
					this.setFailMessage(bct.getTheYear() + "年"
							+ bct.getTheMonth() + "月相同组织目标值数据库已存在");
					return RESULT_MESSAGE;
				}
			}
			boolean b = goalService.insertGoal1(bct);/* 插入项目总表 */
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(bct, e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 页面更新目标add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateGoal() {
		bct = new BCustomerTarget();
		try {
			bct.setCtId(ctId);
			bct.setBox(box);
			bct.setTargetNum(targetNum);
			bct.setOpId(getUser().getUserId().toString());
			if ("C".equals(kunnrGoalType)) {
				bct.setTrFlag("T");
			} else {
				bct.setTrFlag("S");
				// 得到系统现有的目标量标箱
				BCustomerTarget old = new BCustomerTarget();
				old = goalService.getGoalById(ctId);
				if ("Y".equals(mark)) {
					// 经销商目标量:输入的目标量如大于原有的，验证所在组织的经销商目标合计不能大于组织目标
					if (Double.valueOf(box) > Double.valueOf(old.getBox())) {
						BCustomerTarget orgBox = new BCustomerTarget();
						BCustomerTarget orgCustBox = new BCustomerTarget();
						orgCustBox.setTheYear(old.getTheYear());
						orgCustBox.setTheMonth(old.getTheMonth());
						orgCustBox.setOrgId(old.getOrgId());
						orgCustBox.setMatter(old.getMatter());
						orgBox = goalService.getGoalMessege(orgCustBox); // 组织待开目标量
						if (null != orgBox) {
							if (Double.valueOf(orgBox.getBox()) <= 0) {
								this.setFailMessage("经销商所在组织的组织目标量不足，经销商目标暂不能增加，请重试!");
								return RESULT_MESSAGE;
							} else {
								// 组织待开-(输入的目标-经销商原有的目标量)<0
								double boxNum = Double.valueOf(orgBox.getBox())
										- (Double.valueOf(box) - Double
												.valueOf(old.getBox()));
								if (boxNum < 0) {
									this.setFailMessage("经销商所在组织的组织目标量不足，请重试!");
									return RESULT_MESSAGE;
								}
							}
						}

					}

				} else {
					// 组织目标：输入的目标量如小于系统原来的，验证输入的目标量不能小于组织下的经销商目标之和；
					if (Double.valueOf(old.getBox()) > Double.valueOf(box)) {
						// 输入的值不能少于0
						if (Double.valueOf(box) < 0.0) {
							this.setFailMessage("修改的组织目标不能少于0,请重试!");
							return RESULT_MESSAGE;
						} else {
							BCustomerTarget orgCustBox = new BCustomerTarget();
							orgCustBox.setTheYear(old.getTheYear());
							orgCustBox.setTheMonth(old.getTheMonth());
							orgCustBox.setOrgId(old.getOrgId());
							orgCustBox.setKunnrGoalType(old.getKunnrGoalType());
							orgCustBox.setMark("Y");
							orgCustBox.setMatter(old.getMatter());
							double orgCustBoxSum = goalService
									.getGoalCountNum(orgCustBox); // 组织下的经销商目标
							if (Double.valueOf(box) < orgCustBoxSum) {
								this.setFailMessage("修改的组织目标少于组织下已有的经销商目标合计,请重试!");
								return RESULT_MESSAGE;
							}
						}
					}
				}
			}
			bct.setCtState("0");
			bct.setCheckOpId(this.getUser().getUserId());

			boolean b = goalService.updateGoal(bct);/* 修改项目总表 */
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(bct, e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 页面批量审批add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String approveGoal() {
		bct = new BCustomerTarget();
		if (!ids.equals("")) {
			try {
				String[] l = ids.split(",");
				bct.setCodes(l);
				List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
				list = goalService.getGoalMessegeByALL(bct);
				String str = "";
				if (0 < list.size()) {
					for (int i = 0; i < list.size(); i++) {
						BCustomerTarget t = new BCustomerTarget();
						t = list.get(i);
						/*
						 * 预算目标量不能超过组织待开目标量
						 */
						// if ("Y".equals(t.getMark())) {
						BCustomerTarget tar = new BCustomerTarget();
						tar.setOrgId(t.getOrgId());
						tar.setTheMonth(t.getTheMonth());
						tar.setTheYear(t.getTheYear());
						tar.setMatter(t.getMatter());
						// 组织待开目标量
						tar = goalService.getGoalMessege(tar);
						if (null != tar) {
							if (Double.valueOf(tar.getBox()) < 0) {
								str += t.getOrgName() + t.getTheYear() + "年"
										+ t.getTheMonth() + "月" + t.getBezei()
										+ "经销商目标量合计超过了组织目标量，请重试！" + "</br>";
							} /*
							 * else { double b=Double.valueOf(tar.getBox());
							 * double target=Double.valueOf(t.getBox()); if
							 * (b<target) { str += t.getOrgName() +
							 * t.getTheYear() + "年" + t.getTheMonth() + "月" +
							 * t.getBezei() + "目标量超过了组织待开目标量，请重试！" + "\n"; } }
							 */
						} else {
							str += t.getOrgName() + t.getTheYear() + "年"
									+ t.getTheMonth() + "月" + t.getBezei()
									+ "没有待开目标量！";

						}
					}
					// }
					if (!"".equals(str)) {
						this.setFailMessage(str);
						return RESULT_MESSAGE;
					}
				}
				bct.setTrFlag("T");
				bct.setCheckOpId(this.getUser().getUserId());
				StringResult result = goalService.approveGoal(bct);
				if (IGoalService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					this.setSuccessMessage("处理成功");
				}
			} catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(bct, e);
			}
		} else {
			this.setFailMessage("选中的行已完成审批！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 财务批量审核一年的
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	public String approveYearGoal() {
		bct = new BCustomerTarget();
		CmsTbDictType cm = new CmsTbDictType();
		cm.setDictTypeName("财务年");
		cm.setPagination("false");
		dictlist = dictServiceHessian.getDictListByDictType(cm);
		CmsTbDict cmsTbDict = new CmsTbDict();
		String theMonth = String.valueOf(DateUtil.getMonth());
		String theYear = String.valueOf(DateUtil.getYear());
		String str = theMonth.length() == 2 ? theMonth : 0 + theMonth;
		Long budgetTime = Long.parseLong(theYear + str);
		for (int c = 0; c < dictlist.size(); c++) {
			String[] financialYear1 = dictlist.get(c).getItemValue().split("-");
			String[] financialYear2 = dictlist.get(c).getItemDescription()
					.split("-");
			Long cmsTime1 = Long.parseLong(financialYear1[0].toString()
					+ financialYear1[1].toString());
			Long cmsTime2 = Long.parseLong(financialYear2[0].toString()
					+ financialYear2[1].toString());
			if (budgetTime >= cmsTime1 && budgetTime <= cmsTime2) {
				cmsTbDict = dictlist.get(c);
			}
		}
		bct.setStartDate(cmsTbDict.getItemValue());
		bct.setEndDate(cmsTbDict.getItemDescription());
		List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
		// list = goalService.getGoalMessegeByALL(bct);
		String str1 = "";
		// 财务年里组织待开目标少于0的
		int n = goalService.getKunnrGoalCount(bct);
		if (0 < n) {
			list = goalService.getKunnrGoalAll(bct);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					BCustomerTarget t = new BCustomerTarget();
					t = list.get(i);
					str1 += t.getOrgName() + t.getTheYear() + "年"
							+ t.getTheMonth() + "月" + t.getBezei()
							+ "经销商目标量合计超过了组织目标量！.</br>";
				}
			}
		}
		/*
		 * if(n>10){ str+="经销商目标量超过组织目标量！"; }
		 */
		if (!"".equals(str1)) {
			this.setFailMessage(str1);
			return RESULT_MESSAGE;
		}

		bct.setTrFlag("T");
		bct.setCheckOpId(this.getUser().getUserId());
		StringResult result = goalService.approveYearGoal(bct);
		if (IGoalService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("处理成功");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 页面批量驳回add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String rejectGoal() {
		bct = new BCustomerTarget();
		if (!ids.equals("")) {
			try {
				String[] l = ids.split(",");
				bct.setCodes(l);
				bct.setTrFlag("B");
				bct.setCheckOpId(this.getUser().getUserId());
				StringResult result = goalService.approveGoal(bct);
				if (IGoalService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					this.setSuccessMessage("处理成功");
				}
			} catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(bct, e);
			}
		} else {
			this.setFailMessage("选中的行已驳回！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 页面删除目标add by allen
	 * 
	 * @author: xg.chen
	 * @date:2016年12月27日 下午3:54:45
	 * @version 1.1 保存操作人
	 * @return
	 */
	@PermissionSearch
	public String deleteGoal() {
		bct = new BCustomerTarget();
		try {
			String[] l = ids.split(",");
			bct.setCodes(l);
			bct.setCtState("1");
			bct.setOpId(this.getUser().getUserId());
			StringResult result = goalService.approveGoal(bct);
			if (IGoalService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(bct, e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 导出Excel模板add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportMould() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		String report_name = "目标导入模板";
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			/*
			 * response.setHeader("Content-disposition", "attachment; filename="
			 * + "MPR.xls");
			 */
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			// WritableSheet.setColumnView(int i,int width);
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30);
			// wsheet.setRowView(0,10);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			// cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			// 合并单元格如（第1列第1行到第1列第2行）
			// 表头
			Label label_0 = new Label(0, 0, "经销商sap代码");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			wsheet.setColumnView(0, 20);
			Label label_1 = new Label(1, 0, "目标值");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "目标箱数");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			wsheet.setColumnView(2, 20);
			Label label_3 = new Label(3, 0, "目标年");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "目标月");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			wsheet.setColumnView(4, 20);
			Label label_5 = new Label(5, 0, "预算口径");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			wsheet.setColumnView(5, 20);
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
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 导出CSV模板 销售目标量维护之模板导出
	 * 
	 * @author: xg.chen
	 * @date:2016年12月22日 下午3:39:44
	 * @version 1.1 修改原有的导入模板的验证信息
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv() {
		OutputStream os = null;
		String report_name = "销售目标导入模板";
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
			linedata.append("组织名称");
			linedata.append(",");
			linedata.append("经销商代码");
			linedata.append(",");
			linedata.append("经销商名称");
			linedata.append(",");
			linedata.append("目标箱数(标箱)");
			linedata.append(",");
			linedata.append("目标年");
			linedata.append(",");
			linedata.append("目标月");
			linedata.append(",");
			linedata.append("预算口径代码");
			linedata.append(",");
			linedata.append("物料组描述");
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
	 * MethodsTitle: 导出CSV模板 协议目标量维护之模板导出
	 * 
	 * @author: xg.chen
	 * @date:2016年12月22日 下午3:39:44
	 * @version 1.1 修改原有的导入模板的验证信息
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv4Dealer() {
		OutputStream os = null;
		String report_name = "协议目标导入模板";
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
			linedata.append("经销商代码");
			linedata.append(",");
			linedata.append("经销商名称");
			linedata.append(",");
			linedata.append("目标箱数(标箱)");
			linedata.append(",");
			linedata.append("目标年");
			linedata.append(",");
			linedata.append("目标月");
			linedata.append(",");
			linedata.append("预算口径代码");
			linedata.append(",");
			linedata.append("物料组描述");
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

	// exportModel
	/**
	 * 经销商开户导出目标量CSV模板
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportModel() {
		OutputStream os = null;
		String report_name = "经销商开户目标导入模板";
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
			linedata.append("目标年");
			linedata.append(",");
			linedata.append("目标月");
			linedata.append(",");
			linedata.append("预算口径代码");
			linedata.append(",");
			linedata.append("物料组描述");
			linedata.append(",");
			linedata.append("目标箱数(标箱)");
			/*
			 * linedata.append(","); linedata.append("目标值(元)");
			 */
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
	 * 目标量导入辅助数据
	 */
	public void importMatterModel() {

		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		Materiel mat = new Materiel();
		mat.setStart(getStart());
		mat.setEnd(getEnd());
		mat.setPagination("false");// 不使用分页 全部查出来
		List<Materiel> matList = new ArrayList<Materiel>();
		HttpServletResponse response = getServletResponse();
		try {
			matList = goalService.getMaterielViewList(mat);
			props.add("mvgr1");
			props.add("bezei");

			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("物料信息(预算口径)表".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("物料信息(预算口径)", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_0 = new Label(0, 0, "物料组(预算口径代码)");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "描述");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			ExcelUtil.createExcelWithBook(wbook, props, matList);

		} catch (Exception e) {
			logger.error(e);
		}
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
		res[1] = userId;
		res[2] = "executeAction";
		res[3] = appUrl + "/goalAction!goalOpen.jspa";
		userUtil = wfeServiceHessian.startWorkflowFix(res);
		return JSON;
	}

	/**
	 * 经销商申请完成,正式开户入数据库
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String goalOpen() {
		BooleanResult result = new BooleanResult();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new BCustomerTarget());
			if (info != null) {
				bct = (BCustomerTarget) info.getObject();
				for (int i = 0; i < bct.getBctList().size(); i++) {
					BCustomerTarget bct1 = bct.getBctList().get(i);
					if (goalService.getGoalListCount1(bct1) != 0l) {
						result.setResult(false);
						result.setCode(bct1.getTheYear() + "年"
								+ bct1.getTheMonth() + "月" + "物料号"
								+ bct1.getMatter() + "目标值已在数据库存在");
						setExecuteResult(result);
						return JSON;
					}
				}
				bctList = bct.getBctList();
				for (BCustomerTarget bct1 : bctList) {
					bct1.setTrFlag("t");
					bct1.setCtState("0");
					bct1.setOpId(this.getUser().getUserId());
					try {
						result = goalService.insertGoal(bct1);
					} catch (Exception e) {
						logger.error(e);
						result.setResult(false);
						result.setCode(result.getCode() + "\n"
								+ "数据保存数据库失败.请联系系统管理员");
					}
				}
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	/**
	 * 导入经销商目标By Excel add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@SuppressWarnings({ "deprecation", "static-access" })
	public String importGoal() {
		HSSFSheet rs = null;
		PrintWriter out = null;
		String resultMsg = null;
		String custnumberp2 = "^\\d{1,}$";
		Date date1 = new Date();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		bctList = new ArrayList<BCustomerTarget>();
		bctList1 = new ArrayList<BCustomerTarget>();
		BCustomerTarget bct1 = new BCustomerTarget();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			String orgName = orgServiceHessian.getOrgByUserId(
					getUser().getUserId()).getOrgName();
			title = "" + year + "年" + orgName + "经销商目标";
			Object[] res = new Object[7];
			res[0] = eventId;
			res[1] = this.getUser().getUserId();
			res[2] = nextUserId;
			res[3] = title;
			res[4] = appUrl + "/goalAction!goalFormView.jspa";
			res[5] = key4open;
			res[6] = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".csv"))) {
					HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						HSSFRow row = rs.getRow(j);
						int rsColumns = row.getLastCellNum();
						if (rsColumns == 6) {
							BCustomerTarget bct = new BCustomerTarget();
							Kunnr kunnr = new Kunnr();
							Materiel mat = new Materiel();
							String userId = getUser().getUserId().toString();
							bct.setOpId(userId);
							bct.setOpName(this.getUser().getUserName());
							bct.setOrgId(getUser().getOrgId());
							bct.setCtDate((String.valueOf(year)) + "-"
									+ String.valueOf(date1.getMonth() + 1)
									+ "-" + String.valueOf(date1.getDate()));
							bct.setCtState("0");
							bct.setTrFlag("S");
							row.getCell(0).setCellType(
									row.getCell(0).CELL_TYPE_STRING);
							// String
							// str1=StringUtils.leftPad(row.getCell(5).getRichStringCellValue().toString(),
							// 18, '0');
							bct.setCustId(StringUtils.leftPad(String
									.valueOf(row.getCell(0)
											.getRichStringCellValue()), 8, '0'));
							kunnr.setKunnr(StringUtils.leftPad(String
									.valueOf(row.getCell(0)
											.getRichStringCellValue()), 8, '0'));
							size = goalService.getKunnrsList1(kunnr).size();
							if (size != 0) {
								bct.setCustNameZH(goalService
										.getKunnrsList1(kunnr).get(0)
										.getName1());
							} else {
								this.setFailMessage("第" + j + "行SAP代码不存在");
								break;
							}

							if (row.getCell(1) != null) {
								row.getCell(1).setCellType(
										row.getCell(1).CELL_TYPE_STRING);
								String str = row.getCell(1)
										.getStringCellValue();
								BigDecimal big = new BigDecimal(str);
								bct.setTargetNum(big);
							}
							if (row.getCell(2) != null) {
								row.getCell(2).setCellType(
										row.getCell(2).CELL_TYPE_STRING);
								bct.setBox(String.valueOf(row.getCell(2)
										.getRichStringCellValue()));
							}
							if (row.getCell(3) != null) {
								row.getCell(3).setCellType(
										row.getCell(3).CELL_TYPE_STRING);
								bct.setTheYear(Long.valueOf(row.getCell(3)
										.getRichStringCellValue().toString()));
							}
							if (row.getCell(4) != null) {
								row.getCell(4).setCellType(
										row.getCell(4).CELL_TYPE_STRING);
								bct.setTheMonth(row.getCell(4)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(5) != null) {
								row.getCell(5).setCellType(
										row.getCell(5).CELL_TYPE_STRING);
								String str = row.getCell(5)
										.getRichStringCellValue().toString();
								bct.setMatter(str);
							}
							mat.setMvgr1(row.getCell(5)
									.getRichStringCellValue().toString());
							size1 = goalService.getMatList1(mat).size();
							if (size1 != 0) {
								bct.setBezei(goalService.getMatList1(mat)
										.get(0).getBezei());
							} else {
								this.setFailMessage("第" + j + "行物料不存在");
								break;
							}
							if (goalService.getGoalListCount1(bct) != 0l) {
								this.setFailMessage("第" + j
										+ "行活动目标值已审批完成请勿重复提交");
								break;
							}
							for (int x = 0; x < bctList.size(); x++) {
								if (bctList.get(x).getCustId()
										.equals(bct.getCustId())
										&& bctList.get(x).getMatter()
												.equals(bct.getMatter())
										&& bctList.get(x).getTheYear()
												.equals(bct.getTheYear())
										&& bctList.get(x).getTheMonth()
												.equals(bct.getTheMonth())) {
									this.setFailMessage("第" + j + "行活动目标值与第"
											+ (x + 1) + "行目标值重复");
									break;
								}
							}
							bctList.add(bct);
							bct1.setOpId(userId);
							bct1.setOpName(this.getUser().getUserName());
							bct1.setOrgId(getUser().getOrgId());
							bct1.setTransaction_id(eventId);
							bct1.setCtDate((String.valueOf(year)) + "-"
									+ String.valueOf(date1.getMonth() + 1)
									+ "-" + String.valueOf(date1.getDate()));
							bct1.setBctList(bctList);
							if (String.valueOf(
									row.getCell(0).getRichStringCellValue())
									.equals("")) {
								this.setFailMessage("第" + j + "行经销商sap代码为空");
								break;
							} else if (String.valueOf(
									row.getCell(1).getRichStringCellValue())
									.equals("")) {
								this.setFailMessage("第" + j + "行目标值为空");
								break;
							} else if (String.valueOf(
									row.getCell(2).getRichStringCellValue())
									.equals("")) {
								this.setFailMessage("第" + j + "行目标箱数为空");
								break;
							} else if (String.valueOf(
									row.getCell(3).getRichStringCellValue())
									.equals("")) {
								this.setFailMessage("第" + j + "行目标年为空");
								break;
							} else if (String.valueOf(
									row.getCell(4).getRichStringCellValue())
									.equals("")) {
								// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标月为空'}";
								this.setFailMessage("第" + j + "行目标月为空");
								break;
							} else if (String.valueOf(
									row.getCell(5).getRichStringCellValue())
									.equals("")) {
								this.setFailMessage("第" + j + "行物料号为空");
								break;
							} else if (!String.valueOf(
									row.getCell(2).getRichStringCellValue())
									.matches(custnumberp2)) {
								this.setFailMessage("第" + j + "行目标值必须为数字");
								break;
							} else if (!String.valueOf(
									row.getCell(3).getRichStringCellValue())
									.matches(custnumberp2)) {
								this.setFailMessage("第" + j + "行目标箱数必须为数字");
								break;
							} else if (!String.valueOf(
									row.getCell(4).getRichStringCellValue())
									.matches(custnumberp2)) {
								this.setFailMessage("第" + j + "行目标年必须为数字");
								break;
							} else if (!String.valueOf(
									row.getCell(5).getRichStringCellValue())
									.matches(custnumberp2)) {
								this.setFailMessage("第" + j + "行目标月必须为数字");
								break;
							}
						} else {// 列数不对
							this.setFailMessage("excel列数不对");
							break;
						}
					}
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
			if (StringUtils.isEmpty(resultMsg)) {
				if (JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + '/' + eventId
						+ ".xml", bct1, getUser().getUserId().toString(),
						getUser().getUserName().toString(), null)
						&& "success".equals(wfeServiceHessian
								.processWorkflowFix(res))) {
					this.setSuccessMessage("事务提交成功事务ID为" + eventId);
				} else {
					this.setFailMessage("事务提交失败写入XML失败");
				}
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("数据有问题");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 协议目标量维护批量导入目标 By CSV 目标量维度：组织、年、月、物料 1、组织是否有组织目标量
	 * 2、判断系统组织待开目标量是否结余 3、新增的经销商目标量不能超过组织待开目标量 4、相同的目标量是否已经存在
	 * 5、同一组织下的经销商目标量之和不能超过组织待开目标量
	 * 
	 * @author: xg.chen
	 * @date:2016年12月26日 上午10:35:45
	 * @version 1.0
	 * @return
	 */
	@PermissionSearch
	public String importGoalCsv4Dealer() {
		StringBuffer result = new StringBuffer();
		String custnumberp2 = "^\\d{1,}$";
		BooleanResult result1 = new BooleanResult();
		try {
			bctList = new ArrayList<BCustomerTarget>();
			bctList1 = new ArrayList<BCustomerTarget>();

			String rcs = "";
			String rcs2 = "";

			setImportPercent(0);

			Map<String, BigDecimal> matterPriceMap = new HashMap<String, BigDecimal>();
			Map<String, Kunnr> kunnrMap = new HashMap<String, Kunnr>();
			Map<String, String> orgMap = new HashMap<String, String>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					Map<String, BCustomerTarget> tempMap = new HashMap<String, BCustomerTarget>();
					for (int j = 0; j < content.size(); j++) {
						// 百分比进度条标识
						int percent = (int) Math.floor((j + 1)
								/ (double) content.size() * 100 / 4);
						setImportPercent(percent);

						String[] s = content.get(j);
						int ij;
						if (s.length == 7) {
							BCustomerTarget bct = new BCustomerTarget();
							Kunnr kunnr = new Kunnr();
							Materiel mat = new Materiel();
							String userId = getUser().getUserId().toString();
							bct.setOpId(userId);
							bct.setOpName(this.getUser().getUserName());
							ij = 0;
							String i0 = s[ij++];// 经销商代码
							String i1 = s[ij++];// 经销商名称
							String i2 = s[ij++];// 目标箱数
							String i3 = s[ij++];// 目标年
							String i4 = s[ij++];// 目标月
							String i5 = s[ij++];// 品项代码
							String i6 = s[ij++];// 品项描述

							bct.setKunnrGoalType(kunnrGoalType);
							// 目标箱数导入验证
							if (i2 != null) {
								i2 = String.valueOf(i2).replace(",", "");
								bct.setBox(i2);
							} else {
								bct.setBox("0");
								rcs += "第" + (j + 2) + "行目标箱数为空或不是数字.</br>";
							}
							// 品项代码和品项描述验证
							if (i5 != null) {
								if (matterPriceMap.get(i5 + i6) == null) {
									mat.setMvgr1(i5);
									mat.setBezei(i6);
									mat.setPagination("false");
									int size1 = goalService
											.getMaterielViewListCount(mat);
									if (size1 != 0) {
										bct.setMatter(i5);
										bct.setBezei(i6);
										// 取物料价格得到目标金额
										List<Materiel> mList = goalService
												.getMaterielViewList(mat);
										BigDecimal b1 = new BigDecimal(mList
												.get(0).getKbetr());
										BigDecimal b2 = new BigDecimal(
												bct.getBox());
										bct.setTargetNum(b1.multiply(b2));
										matterPriceMap.put(i5 + i6,
												b1.multiply(b2));
										// 获取物料对应的品牌
										List<BCustomerTarget> bCustomerTargets = goalService
												.getMaktxAndMaknr(mat);
										if (bCustomerTargets.size() != 0) {
											bct.setMatnr01(bCustomerTargets
													.get(0).getMatnr01());
											bct.setMaktx01(bCustomerTargets
													.get(0).getMaktx01());
										} else {
											bct.setMatnr01("");
											bct.setMaktx01("");
										}
									} else {
										rcs += "第" + (j + 2)
												+ "行物料号不存在或者不在有效期内.</br>";
									}
								} else {
									bct.setMatter(i5);
									bct.setBezei(i6);
									bct.setTargetNum(matterPriceMap
											.get(i5 + i6));
								}
							} else {
								BigDecimal b = new BigDecimal(0);
								bct.setTargetNum(b);
								rcs += "第" + (j + 2) + "行物料号为空或不存在.</br>";
							}
							// 验证经销商
							if (type.equals("Y")) {
								if (null != i0 && i1 != null) {
									if (kunnrMap.get(i1) == null) {
										// 判断导入的kunnrId是否正确
										if (StringUtils.isNotEmpty(i0)) {
											if (i0.length() < 8) {
												i0 = "0" + i0;
											}
										}
										kunnr.setKunnr(i0);
										kunnr.setName1(i1);
										bct.setMark("Y");
										// 验证经销商是否有效
										List<Kunnr> list1 = goalService
												.getKunnrsList1(kunnr);
										if (list1.size() == 0l) {
											rcs += "第" + (j + 2)
													+ "行经销商编码和经销商名称有错误!</br>";
										} else if (i1 != null
												&& list1.size() != 0l) {
											kunnr.setPagination("false");
											List<Kunnr> kList = goalService
													.getKunnrsList(kunnr);
											if (kList.size() > 1) {
												int x = 0;
												for (Kunnr k : kList) {
													if (k.getName1().equals(i1)) {
														bct.setCustId(k
																.getKunnr());
														bct.setOrgId(k
																.getOrgId());
														bct.setOrgName(k
																.getOrgName());
														kunnrMap.put(i1, k);
														x = 1;
														break;
													}
												}
												if (x == 0) {
													rcs += "第"
															+ (j + 2)
															+ "行"
															+ "经销商名称不正确，请核对！.</br>";
												}
											} else {
												bct.setCustId(kList.get(0)
														.getKunnr());
												bct.setOrgId(kList.get(0)
														.getOrgId());
												bct.setOrgName(kList.get(0)
														.getOrgName());
											}
											bct.setKunnrGoalType(kunnrGoalType);
										} else {
											rcs += "第"
													+ (j + 2)
													+ "行"
													+ "经销商不存在,请检查导入的数据后重试!.</br>";
										}
									} else {
										Kunnr k = kunnrMap.get(i1);
										bct.setCustId(k.getKunnr());
										bct.setOrgId(k.getOrgId());
										bct.setOrgName(k.getOrgName());
										bct.setKunnrGoalType(kunnrGoalType);
										bct.setMark("Y");
									}
								} else {
									rcs += "第" + (j + 2) + "行"
											+ "经销商为空，请重试！.</br>";
								}
							}
							// 目标年月的验证
							if (i3 != null
									&& String.valueOf(i3).matches(custnumberp2)) {
								bct.setTheYear(Long.parseLong(i3));
							} else {
								rcs += "第" + (j + 2) + "行目标年为空或不是数字.</br>";
							}
							if (i4 != null
									&& String.valueOf(i4).matches(custnumberp2)) {
								bct.setTheMonth(StringUtils.leftPad(i4, 2, '0'));
							} else {
								rcs += "第" + (j + 2) + "行目标月为空或不是数字.</br>";
							}
							// 导入数据的重复验证
							int count1 = goalService.getGoalListCount1(bct);
							if (count1 != 0) {
								rcs += "第" + (j + 2) + "行" + bct.getTheYear()
										+ "年" + bct.getTheMonth()
										+ "月相同物料号数据库已存在.</br>";
							}
							StringBuilder str = new StringBuilder(i0);
							str.append(i3).append(i4).append(i5);
							if (null == tempMap.get(str.toString())) {
								tempMap.put(str.toString(), bct);
							} else {
								rcs += "第" + (j + 2) + "行" + "数据存在重复.</br>";
							}

							// 导入的数据放入到list中
							bctList.add(bct);
						} else {
							this.setFailMessage("列数有问题");
							setImportPercent(99);
							return RESULT_MESSAGE;
						}
					}
					if (!"".equals(rcs)) {
						result.append(rcs.toString() + "</br>");
						this.setFailMessage(result.toString());
						setImportPercent(99);
						return RESULT_MESSAGE;
					} else {
						if (bctList.size() != 0l
								&& result.toString().equals("")) {
							Map<String, BCustomerTarget> map = new HashMap<String, BCustomerTarget>();
							for (int q = 0; q < bctList.size(); q++) {
								if ("C".equals(bctList.get(q)
										.getKunnrGoalType())) {
									bctList.get(q).setTrFlag("T");
								} else {
									bctList.get(q).setTrFlag("S");
									if ("Y".equals(bctList.get(q).getMark())) {
										// 将同组织同年同月份同物料的条目合计
										StringBuffer b = new StringBuffer();
										b.append(bctList.get(q).getOrgId())
												.append(bctList.get(q)
														.getTheYear())
												.append(bctList.get(q)
														.getTheMonth())
												.append(bctList.get(q)
														.getMatter());
										if (map.get(b.toString()) == null) {
											BCustomerTarget t = new BCustomerTarget();
											t.setOrgId(bctList.get(q)
													.getOrgId());
											t.setTheYear(bctList.get(q)
													.getTheYear());
											t.setTheMonth(bctList.get(q)
													.getTheMonth());
											t.setMatter(bctList.get(q)
													.getMatter());
											t.setBox(bctList.get(q).getBox());
											t.setOrgName(bctList.get(q)
													.getOrgName());
											t.setBezei(bctList.get(q)
													.getBezei());
											map.put(b.toString(), t);
										} else {
											BigDecimal box = new BigDecimal(map
													.get(b.toString()).getBox());
											BigDecimal boxCur = new BigDecimal(
													bctList.get(q).getBox());
											double sumBox = box.add(boxCur)
													.doubleValue();
											map.get(b.toString()).setBox(
													String.valueOf(sumBox));
										}
									}
								}
							}
							if (!map.isEmpty()) {
								int i = 0;
								int mapSize = map.size();
								for (Map.Entry<String, BCustomerTarget> MapString : map
										.entrySet()) {
									BCustomerTarget value = MapString
											.getValue();// 获取键值对的值
									// * 预算目标量不能超过组织待开目标量
									BCustomerTarget tar = new BCustomerTarget();
									tar.setOrgId(value.getOrgId());
									tar.setTheYear(value.getTheYear());
									tar.setTheMonth(value.getTheMonth());
									tar.setMatter(value.getMatter());
									// 组织待开目标量
									tar = goalService.getGoalMessege(tar);
									if (null != tar) {
										if (Double.valueOf(tar.getBox()) <= 0) {
											rcs2 += value.getOrgName()
													+ value.getTheYear() + "年"
													+ value.getTheMonth() + "月"
													+ value.getBezei()
													+ "组织待开目标量不足，请重试!.</br>";
										} else {
											BigDecimal b1 = new BigDecimal(
													tar.getBox());
											BigDecimal b2 = new BigDecimal(
													value.getBox());
											BigDecimal num = b1.subtract(b2);
											if (num.compareTo(new BigDecimal(
													-0.01)) == -1) {
												rcs2 += value.getOrgName()
														+ value.getTheYear()
														+ "年"
														+ value.getTheMonth()
														+ "月"
														+ value.getBezei()
														+ "新增经销商目标量总计超过了组织待开目标量，请重试！.</br>";
											}
										}
									} else {
										rcs2 += value.getOrgName()
												+ value.getTheYear() + "年"
												+ value.getTheMonth() + "月"
												+ value.getBezei()
												+ "还没有组织目标量，请先维护组织目标量！.</br>";
									}

									// 百分比进度条标识
									i++;
									int percent = (int) Math.floor((i + 1)
											/ (double) mapSize * 100 / 4) + 25;
									setImportPercent(percent);
								}
							}
							if ("".equals(rcs2)) {
								for (int i = 0; i < bctList.size(); i++) {
									// 百分比进度条标识
									int percent = (int) Math
											.floor((i + 1)
													/ (double) bctList.size()
													* 100 / 2) + 50;
									setImportPercent(percent);

									if ("C".equals(bctList.get(i)
											.getKunnrGoalType())) {
										bctList.get(i).setTrFlag("T");
									} else {
										bctList.get(i).setTrFlag("S");
									}

									bctList.get(i).setCtState("0");
									bctList.get(i).setOpId(
											this.getUser().getUserId());
									try {
										rcs2 = "";
										result1 = goalService
												.insertGoal(bctList.get(i));
										rcs2 += result1.getCode();
										result.append(rcs2.toString() + "</br>");
									} catch (Exception e) {
										logger.error(e);
										rcs2 += "第" + (i + 2)
												+ "条数据保存数据库失败.请联系系统管理员.</br>";
										result.append(rcs2.toString() + "</br>");
									}
								}
							} else {
								this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
										+ rcs2.toString());
								setImportPercent(99);
								return RESULT_MESSAGE;
							}
						}
						this.getSession().setAttribute("bctList", bctList);
						if (result1.getResult()) {
							this.setSuccessMessage("导入成功！导入数量为:"
									+ bctList.size() + "行");
						} else if (!result1.getResult()) {
							this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
									+ result.toString());
						}
						setImportPercent(99);
						return RESULT_MESSAGE;
					}
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				setImportPercent(99);
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			setImportPercent(99);
			logger.error(e);
		}
		setImportPercent(99);
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 批量导入目标 By CSV add by allen 
	 * 目标量维度：组织、年、月、物料
	 *  1、组织是否有组织目标量
	 * 2、判断系统组织待开目标量是否结余 
	 * 3、新增的经销商目标量不能超过组织待开目标量 
	 * 4、相同的目标量是否已经存在
	 * 5、同一组织下的经销商目标量之和不能超过组织待开目标量
	 * 
	 * @author: xg.chen
	 * @date:2016年12月22日 下午3:45:01
	 * @version 1.1
	 * @return
	 */
	@PermissionSearch
	public String importGoalCsv() {
		StringBuffer result = new StringBuffer();
		String custnumberp2 = "^\\d{1,}$";
		BooleanResult result1 = new BooleanResult();
		try {
			bctList = new ArrayList<BCustomerTarget>();
			bctList1 = new ArrayList<BCustomerTarget>();

			String rcs = "";
			String rcs2 = "";

			setImportPercent(0);

			Map<String, BigDecimal> matterPriceMap = new HashMap<String, BigDecimal>();
			Map<String, Kunnr> kunnrMap = new HashMap<String, Kunnr>();
			Map<String, String> orgMap = new HashMap<String, String>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					Map<String, BCustomerTarget> tempMap = new HashMap<String, BCustomerTarget>();
					for (int j = 0; j < content.size(); j++) {
						// 百分比进度条标识
						int percent = (int) Math.floor((j + 1)
								/ (double) content.size() * 100 / 4);
						setImportPercent(percent);

						String[] s = content.get(j);
						int ij;
						if (s.length == 8) {
							BCustomerTarget bct = new BCustomerTarget();
							Kunnr kunnr = new Kunnr();
							Materiel mat = new Materiel();
							String userId = getUser().getUserId().toString();
							bct.setOpId(userId);
							bct.setOpName(this.getUser().getUserName());
							ij = 0;
							String i0 = s[ij++];// 组织
							String i1 = s[ij++];// 经销商代码
							String i2 = s[ij++];// 经销商名称
							String i3 = s[ij++];// 目标箱数
							String i4 = s[ij++];// 目标年
							String i5 = s[ij++];// 目标月
							String i6 = s[ij++];// 品项代码
							String i7 = s[ij++];// 品项描述

							bct.setKunnrGoalType(kunnrGoalType);
							// 目标箱数导入验证
							if (i3 != null) {
								i3 = String.valueOf(i3).replace(",", "");
								bct.setBox(i3);
							} else {
								bct.setBox("0");
								rcs += "第" + (j + 2) + "行目标箱数为空或不是数字.</br>";
							}
							// 品项代码和品项描述验证
							if (i6 != null) {
								if (matterPriceMap.get(i6 + i7) == null) {
									mat.setMvgr1(i6);
									mat.setBezei(i7);
									mat.setPagination("false");
									int size1 = goalService
											.getMaterielViewListCount(mat);
									if (size1 != 0) {
										bct.setMatter(i6);
										bct.setBezei(i7);
										// 取物料价格得到目标金额
										List<Materiel> mList = goalService
												.getMaterielViewList(mat); 
										BigDecimal b1 = new BigDecimal(mList
												.get(0).getKbetr());
										BigDecimal b2 = new BigDecimal(
												bct.getBox());
										bct.setTargetNum(b1.multiply(b2));
										matterPriceMap.put(i6 + i7,
												b1.multiply(b2));
										//根据品项获取品牌
										List<BCustomerTarget> bCustomerTargets = goalService
												.getMaktxAndMaknr(mat);
										if (bCustomerTargets.size() != 0) {
											bct.setMatnr01(bCustomerTargets
													.get(0).getMatnr01());
											bct.setMaktx01(bCustomerTargets
													.get(0).getMaktx01());
										} else {
											bct.setMatnr01("");
											bct.setMaktx01("");
										}
									} else {
										rcs += "第" + (j + 2)
												+ "行物料号不存在或者不在有效期内.</br>";
									}
								} else {
									bct.setMatter(i6);
									bct.setBezei(i7);
									bct.setTargetNum(matterPriceMap.get(i6 + i7));
								}
							} else {
								BigDecimal b = new BigDecimal(0);
								bct.setTargetNum(b);
								rcs += "第" + (j + 2) + "行物料号为空或不存在.</br>";
							}
							// 验证经销商
							if (type.equals("Y")) {
								if (null != i1 && i2 != null) {
									if (kunnrMap.get(i2) == null) {
										// 判断导入的kunnrId是否正确
										if (StringUtils.isNotEmpty(i1)) {
											if (i1.length() < 8) {
												i1 = "0" + i1;
											}
										}
										kunnr.setKunnr(i1);
										kunnr.setName1(i2);
										bct.setMark("Y");
										// 验证经销商是否有效
										List<Kunnr> list1 = goalService
												.getKunnrsList1(kunnr);
										if (list1.size() == 0l) {
											rcs += "第" + (j + 2)
													+ "行经销商编码和经销商名称有错误!</br>";
										} else if (i2 != null
												&& list1.size() != 0l) {
											kunnr.setPagination("false");
											List<Kunnr> kList = goalService
													.getKunnrsList(kunnr);
											if (kList.size() > 1) {
												int x = 0;
												for (Kunnr k : kList) {
													if (k.getName1().equals(i2)) {
														bct.setCustId(k
																.getKunnr());
														bct.setOrgId(k
																.getOrgId());
														bct.setOrgName(k
																.getOrgName());
														kunnrMap.put(i2, k);
														x = 1;
														break;
													}
												}
												if (x == 0) {
													rcs += "第"
															+ (j + 2)
															+ "行"
															+ "经销商名称不正确，请核对！.</br>";
												}
											} else {
												bct.setCustId(kList.get(0)
														.getKunnr());
												bct.setOrgId(kList.get(0)
														.getOrgId());
												bct.setOrgName(kList.get(0)
														.getOrgName());
												kunnrMap.put(i0, kList.get(0));
											}
											bct.setKunnrGoalType(kunnrGoalType);
										} else {
											rcs += "第"
													+ (j + 2)
													+ "行"
													+ "经销商不存在,请检查导入的数据后重试!.</br>";
										}
									} else {
										Kunnr k = kunnrMap.get(i2);
										bct.setCustId(k.getKunnr());
										bct.setOrgId(k.getOrgId());
										bct.setOrgName(k.getOrgName());
										bct.setKunnrGoalType(kunnrGoalType);
										bct.setMark("Y");
									}
								} else {
									rcs += "第" + (j + 2) + "行"
											+ "经销商为空，请重试！.</br>";
								}
							} else {// 验证组织
								if (null != i0) {
									if (orgMap.get(i0) == null) {
										kunnr.setOrgName(i0);
										// 验证组织是否有效
										List<OrgHelps> orgs = new ArrayList<OrgHelps>();
										orgs = goalService.getOrgIsExit(kunnr);
										if (null != orgs && orgs.size() != 0) {
											bct.setOrgId(orgs.get(0).getOrgId());
											orgMap.put(i0, orgs.get(0)
													.getOrgId());
										} else {
											rcs += "第" + (j + 2)
													+ "行组织不存在.</br>";
										}
									} else {
										bct.setOrgId(orgMap.get(i0));
									}
								} else {
									rcs += "第" + (j + 2) + "行组织为空.</br>";
								}
								bct.setMark("N");
							}

							// 目标年月的验证
							if (i4 != null
									&& String.valueOf(i4).matches(custnumberp2)) {
								bct.setTheYear(Long.parseLong(i4));
							} else {
								rcs += "第" + (j + 2) + "行目标年为空或不是数字.</br>";
							}
							if (i5 != null
									&& String.valueOf(i5).matches(custnumberp2)) {
								bct.setTheMonth(StringUtils.leftPad(i5, 2, '0'));
							} else {
								rcs += "第" + (j + 2) + "行目标月为空或不是数字.</br>";
							}

							// 导入数据的重复验证
							int count1 = goalService.getGoalListCount1(bct);
							if (count1 != 0) {
								rcs += "第" + (j + 2) + "行" + bct.getTheYear()
										+ "年" + bct.getTheMonth()
										+ "月相同物料号数据库已存在.</br>";
							}
							StringBuilder str=null;
							if(i0!=null){
								str = new StringBuilder(i0);
							} else {
								str = new StringBuilder(i1);
							}
							str.append(i4).append(i5).append(i6);
							if (null == tempMap.get(str.toString())) {
								tempMap.put(str.toString(), bct);
							} else {
								rcs += "第" + (j + 2) + "行" + "数据存在重复.</br>";
							}
							// 导入的数据放入到list中
							bctList.add(bct);
						} else {
							this.setFailMessage("列数有问题");
							setImportPercent(99);
							return RESULT_MESSAGE;
						}
					}
					if (!"".equals(rcs)) {
						result.append(rcs.toString() + "</br>");
						this.setFailMessage(result.toString());
						setImportPercent(99);
						return RESULT_MESSAGE;
					} else {
						if (bctList.size() != 0l
								&& result.toString().equals("")) {
							Map<String, BCustomerTarget> map = new HashMap<String, BCustomerTarget>();
							for (int q = 0; q < bctList.size(); q++) {
								if ("C".equals(bctList.get(q)
										.getKunnrGoalType())) {
									bctList.get(q).setTrFlag("T");
								} else {
									bctList.get(q).setTrFlag("S");
									if ("Y".equals(bctList.get(q).getMark())) {
										// 将同组织同年同月份同物料的条目合计
										StringBuffer b = new StringBuffer();
										b.append(bctList.get(q).getOrgId())
												.append(bctList.get(q)
														.getTheYear())
												.append(bctList.get(q)
														.getTheMonth())
												.append(bctList.get(q)
														.getMatter());
										if (map.get(b.toString()) == null) {
											BCustomerTarget t = new BCustomerTarget();
											t.setOrgId(bctList.get(q)
													.getOrgId());
											t.setTheYear(bctList.get(q)
													.getTheYear());
											t.setTheMonth(bctList.get(q)
													.getTheMonth());
											t.setMatter(bctList.get(q)
													.getMatter());
											t.setBox(bctList.get(q).getBox());
											t.setOrgName(bctList.get(q)
													.getOrgName());
											t.setBezei(bctList.get(q)
													.getBezei());
											map.put(b.toString(), t);
										} else {
											BigDecimal box = new BigDecimal(map
													.get(b.toString()).getBox());
											BigDecimal boxCur = new BigDecimal(
													bctList.get(q).getBox());
											double sumBox = box.add(boxCur)
													.doubleValue();
											map.get(b.toString()).setBox(
													String.valueOf(sumBox));
										}
									}
								}
							}
							if (!map.isEmpty()) {
								int i = 0;
								int mapSize = map.size();
								for (Map.Entry<String, BCustomerTarget> MapString : map
										.entrySet()) {
									BCustomerTarget value = MapString
											.getValue();// 获取键值对的值
									// * 预算目标量不能超过组织待开目标量
									BCustomerTarget tar = new BCustomerTarget();
									tar.setOrgId(value.getOrgId());
									tar.setTheYear(value.getTheYear());
									tar.setTheMonth(value.getTheMonth());
									tar.setMatter(value.getMatter());
									// 组织待开目标量
									tar = goalService.getGoalMessege(tar);
									if (null != tar) {
										if (Double.valueOf(tar.getBox()) <= 0) {
											rcs2 += value.getOrgName()
													+ value.getTheYear() + "年"
													+ value.getTheMonth() + "月"
													+ value.getBezei()
													+ "组织待开目标量不足，请重试!.</br>";
										} else {
											BigDecimal b1 = new BigDecimal(
													tar.getBox());
											BigDecimal b2 = new BigDecimal(
													value.getBox());
											BigDecimal num = b1.subtract(b2);
											if (num.compareTo(new BigDecimal(
													-0.01)) == -1) {
												rcs2 += value.getOrgName()
														+ value.getTheYear()
														+ "年"
														+ value.getTheMonth()
														+ "月"
														+ value.getBezei()
														+ "新增经销商目标量总计超过了组织待开目标量，请重试！.</br>";
											}
										}
									} else {
										rcs2 += value.getOrgName()
												+ value.getTheYear() + "年"
												+ value.getTheMonth() + "月"
												+ value.getBezei()
												+ "还没有组织目标量，请先维护组织目标量！.</br>";
									}

									// 百分比进度条标识
									i++;
									int percent = (int) Math.floor((i + 1)
											/ (double) mapSize * 100 / 4) + 25;
									setImportPercent(percent);
								}
							}
							if ("".equals(rcs2)) {
								for (int i = 0; i < bctList.size(); i++) {
									// 百分比进度条标识
									int percent = (int) Math
											.floor((i + 1)
													/ (double) bctList.size()
													* 100 / 2) + 50;
									setImportPercent(percent);

									if ("C".equals(bctList.get(i)
											.getKunnrGoalType())) {
										bctList.get(i).setTrFlag("T");
									} else {
										bctList.get(i).setTrFlag("S");
									}

									bctList.get(i).setCtState("0");
									bctList.get(i).setOpId(
											this.getUser().getUserId());
									try {
										rcs2 = "";
										result1 = goalService
												.insertGoal(bctList.get(i));
										rcs2 += result1.getCode();
										result.append(rcs2.toString() + "</br>");
									} catch (Exception e) {
										logger.error(e);
										rcs2 += "第" + (i + 2)
												+ "条数据保存数据库失败.请联系系统管理员.</br>";
										result.append(rcs2.toString() + "</br>");
									}
								}
							} else {
								this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
										+ rcs2.toString());
								setImportPercent(99);
								return RESULT_MESSAGE;
							}
						}
						this.getSession().setAttribute("bctList", bctList);
						if (result1.getResult()) {
							this.setSuccessMessage("导入成功！导入数量为:"
									+ bctList.size() + "行");
						} else if (!result1.getResult()) {
							this.setFailMessage("导入失败</br></br>  相关错误信息：</br></br></br>"
									+ result.toString());
						}
						setImportPercent(99);
						return RESULT_MESSAGE;
					}
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				setImportPercent(99);
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			setImportPercent(99);
			logger.error(e);
		}
		setImportPercent(99);
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: 批量导入修改销售目标By CSV 目标量维度：组织、年、月、物料 1、目标量是否存在
	 * 2、目标量存在：经销商目标修改：正数时，同一组织同一年月同一物料的经销商之和不能大于组织待开，负数时，经销商目标量不能少于0;
	 * 3、组织目标修改：负数时，与组织待开之和不能少于0，组织下没有经销目标时，组织目标量不能少于0;
	 * 
	 * @author: xg.chen
	 * @date:2016年12月26日 上午10:17:13
	 * @version 1.1 添加经销商以及组织分开验证
	 * @return
	 */
	@PermissionSearch
	public String importGoalListCsv() {
		StringBuffer result = new StringBuffer();
		String custnumberp2 = "^\\d{1,}$";
		// BooleanResult result1 = new BooleanResult();
		boolean rs = false;
		try {
			bctList = new ArrayList<BCustomerTarget>();
			bctList1 = new ArrayList<BCustomerTarget>();
			Map<String, BCustomerTarget> map = new HashMap<String, BCustomerTarget>();
			Map<String, BigDecimal> matterMap = new HashMap<String, BigDecimal>();
			Map<String, Kunnr> kunnrMap = new HashMap<String, Kunnr>();
			Map<String, String> orgMap = new HashMap<String, String>();
			Map<String, BCustomerTarget> orgDMap = new HashMap<String, BCustomerTarget>();

			setImportPercent(0);
			String rcs = "";
			String rcs2 = "";
			String rcs3 = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
					Map<String, BCustomerTarget> tempMap = new HashMap<String, BCustomerTarget>();
					for (int j = 0; j < content.size(); j++) {
						// 百分比进度条标识
						int percent = (int) Math.floor((j + 1)
								/ (double) content.size() * 100 / 4);
						setImportPercent(percent);

						String[] s = content.get(j);
						int ij;
						if (s.length == 8) {
							BCustomerTarget bct = new BCustomerTarget();
							Kunnr kunnr = new Kunnr();
							Materiel mat = new Materiel();
							String userId = getUser().getUserId().toString();
							bct.setOpId(userId);
							bct.setOpName(this.getUser().getUserName());
							ij = 0;
							String i0 = s[ij++];// 组织
							String i1 = s[ij++];// 经销商代码
							String i2 = s[ij++];// 经销商名称
							String i3 = s[ij++];// 目标箱数
							String i4 = s[ij++];// 目标年
							String i5 = s[ij++];// 目标月
							String i6 = s[ij++];// 品项代码
							String i7 = s[ij++];// 品项描述

							bct.setKunnrGoalType(kunnrGoalType);
							// 目标箱数验证
							if (i3 != null) {
								i3 = String.valueOf(i3).replace(",", "");
								bct.setBox(i3);
							} else {
								bct.setBox("0");
								rcs += "第" + (j + 2) + "行目标箱数为空或不是数字.</br>";
							}
							// 品项代码+品项描述的验证
							if (i7 != null && null != i6) {
								BigDecimal matterPrice = matterMap.get(i6
										.trim() + i7.trim());
								if (matterPrice != null) {
									BigDecimal b2 = new BigDecimal(bct.getBox());
									bct.setMatter(i6);
									bct.setBezei(i7);
									bct.setTargetNum(matterPrice.multiply(b2));
								} else {
									mat.setMvgr1(i6);
									mat.setBezei(i7);
									mat.setPagination("false");
									int size1 = goalService
											.getMaterielViewListCount(mat);
									if (size1 != 0) {
										bct.setMatter(i6);
										bct.setBezei(i7);
										List<Materiel> mList = goalService
												.getMaterielViewList(mat);
										BigDecimal b1 = new BigDecimal(mList
												.get(0).getKbetr());
										BigDecimal b2 = new BigDecimal(
												bct.getBox());
										bct.setTargetNum(b1.multiply(b2));
										matterMap
												.put(i6.trim() + i7.trim(), b1);
									} else {
										rcs += "第" + (j + 2)
												+ "行物料号不存在或者不在有效期内.</br>";
									}
								}
							} else {
								BigDecimal b = new BigDecimal(0);
								bct.setTargetNum(b);
								rcs += "第" + (j + 2) + "行物料号为空或不存在.</br>";
							}
							// 经销商和组织的验证：Y：dealer目标量修改 N:org目标量修改
							if (type.equals("Y")) {
								if (null != i1 && null != i2) {
									if (kunnrMap.get(i1) == null) {
										// 判断导入的kunnrId是否正确
										if (StringUtils.isNotEmpty(i1)) {
											if (i1.length() < 8) {
												i1 = "0" + i1;
											}
										}
										kunnr.setKunnr(i1);
										kunnr.setName1(i2);
										bct.setMark("Y");
										List<Kunnr> kunnrs = goalService
												.getKunnrsList1(kunnr);
										// 验证经销商是否有效
										if (i0 != null && kunnrs.size() != 0l) {
											kunnr.setPagination("false");
											List<Kunnr> kList = goalService
													.getKunnrsList(kunnr);
											if (kList.size() > 1) {
												int x = 0;
												for (Kunnr k : kList) {
													if (k.getName1().equals(i2)) {
														bct.setCustId(k
																.getKunnr());
														bct.setOrgId(k
																.getOrgId());
														bct.setOrgName(k
																.getOrgName());
														kunnrMap.put(i2, k);
														x = 1;
														break;
													}
												}
												if (x == 0) {
													rcs += "第"
															+ (j + 2)
															+ "行"
															+ "经销商名称不正确，请核对！.</br>";
												}
											} else {
												bct.setCustId(kList.get(0)
														.getKunnr());
												bct.setOrgId(kList.get(0)
														.getOrgId());
												bct.setOrgName(kList.get(0)
														.getOrgName());
												kunnrMap.put(i1, kList.get(0));
											}
											bct.setKunnrGoalType(kunnrGoalType);
										} else {
											rcs += "第" + (j + 2) + "行"
													+ "经销商不存在，请重试！.</br>";
										}
									} else {
										Kunnr k = kunnrMap.get(i1);
										bct.setCustId(k.getKunnr());
										bct.setOrgId(k.getOrgId());
										bct.setOrgName(k.getOrgName());
										bct.setKunnrGoalType(kunnrGoalType);
										bct.setMark("Y");
									}
								} else {
									rcs += "第" + (j + 2) + "行"
											+ "经销商为空，请重试！.</br>";
								}

							} else {
								if (null != i0) {
									if (orgMap.get(i0) == null) {
										kunnr.setOrgName(i0);
										// 验证组织是否有效
										List<OrgHelps> orgs = new ArrayList<OrgHelps>();
										orgs = goalService.getOrgIsExit(kunnr);
										if (null != orgs && orgs.size() != 0) {
											bct.setOrgId(orgs.get(0).getOrgId());
											orgMap.put(i0, orgs.get(0)
													.getOrgId());
										} else {
											rcs += "第" + (j + 2)
													+ "行组织不存在.</br>";
										}
									} else {
										bct.setOrgId(orgMap.get(i0));
									}
								} else {
									rcs += "第" + (j + 2) + "行组织为空.</br>";
								}
								bct.setMark("N");
							}
							// 目标年月验证
							if (i4 != null
									&& String.valueOf(i4).matches(custnumberp2)) {
								bct.setTheYear(Long.parseLong(i4));
							} else {
								rcs += "第" + (j + 2) + "行目标年为空或不是数字.</br>";
							}
							if (i5 != null
									&& String.valueOf(i5).matches(custnumberp2)) {
								bct.setTheMonth(StringUtils.leftPad(i5, 2, '0'));
							} else {
								rcs += "第" + (j + 2) + "行目标月为空或不是数字.</br>";
							}
							bct.setPagination("false");
							// 目标年月以及品项不能重复
							List<BCustomerTarget> bTargets = goalService
									.getGoalList(bct);
							if (null == bTargets || bTargets.size() == 0) {
								rcs += "第" + (j + 2) + "行" + bct.getTheYear()
										+ "年" + bct.getTheMonth()
										+ "月相同物料的目标数据库不存在,请做新增操作.</br>";
							} else if (bTargets.size() > 1) {
								rcs += "第" + (j + 2) + "行" + bct.getTheYear()
										+ "年" + bct.getTheMonth()
										+ "月相同物料的目标数据库存在重复,请联系管理员.</br>";
							}
							StringBuilder str = new StringBuilder(i0);
							str.append(i4).append(i5).append(i6);
							if (null == tempMap.get(str.toString())) {
								tempMap.put(str.toString(), bct);
							} else {
								rcs += "第" + (j + 2) + "行" + "数据存在重复.</br>";
							}
							bctList.add(bct);
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (!"".equals(rcs)) {
						result.append(rcs.toString() + "</br>");
						this.setFailMessage(result.toString());
						setImportPercent(99);
						return RESULT_MESSAGE;
					} else {
						Map<String, BCustomerTarget> mapOrg = new HashMap<String, BCustomerTarget>();
						if (bctList.size() != 0l
								&& result.toString().equals("")) {
							for (int i = 0; i < bctList.size(); i++) {
								// 百分比进度条标识
								int percent = (int) Math.floor((i + 1)
										/ (double) content.size() * 100 / 4) + 25;
								setImportPercent(percent);

								BCustomerTarget tag = new BCustomerTarget();
								tag = bctList.get(i);
								// 获取要修改的目标信息
								if ("C".equals(tag.getKunnrGoalType())) {
									tag.setTrFlag("T");
								} else {
									tag.setTrFlag("S");
								}
								if ("B".equals(tag.getKunnrGoalType())) {
									BCustomerTarget oldTarget = new BCustomerTarget();
									oldTarget.setTheYear(tag.getTheYear());
									oldTarget.setTheMonth(tag.getTheMonth());
									oldTarget.setOrgId(tag.getOrgId());
									if (StringUtils.isNotEmpty(tag.getCustId())) {
										oldTarget.setCustId(tag.getCustId());
									}
									oldTarget.setKunnrGoalType(tag
											.getKunnrGoalType());
									oldTarget.setMark(tag.getMark());
									oldTarget.setMatter(tag.getMatter());
									oldTarget = goalService
											.getGoalByCondition(oldTarget);
									if (null != oldTarget) {
										tag.setCtId(oldTarget.getCtId());
										tag.setOrgName(oldTarget.getOrgName());
										if ("Y".equals(tag.getMark())) {
											StringBuffer b = new StringBuffer();
											b.append(tag.getOrgId())
													.append(tag.getTheYear())
													.append(tag.getTheMonth())
													.append(tag.getMatter());
											if (mapOrg.get(b.toString()) == null) {
												BCustomerTarget t = new BCustomerTarget();
												t.setOrgId(tag.getOrgId());
												t.setTheYear(tag.getTheYear());
												t.setTheMonth(tag.getTheMonth());
												t.setMatter(tag.getMatter());
												t.setBox(tag.getBox());
												t.setOrgName(tag.getOrgName());
												t.setBezei(tag.getBezei());
												mapOrg.put(b.toString(), t);
											} else {
												BigDecimal b1 = new BigDecimal(
														tag.getBox());
												BigDecimal b2 = new BigDecimal(
														mapOrg.get(b.toString())
																.getBox());
												double sum = b1.add(b2)
														.doubleValue();
												mapOrg.get(b.toString())
														.setBox(String
																.valueOf(sum));
											}
											// 经销商目标修改：正数时，同一组织同一年月同一物料之和不能超过组织目标量，负数时，经销商目标量不能少于0;
											if (Double.valueOf(tag.getBox()) < 0.0) {
												BigDecimal b1 = new BigDecimal(
														tag.getBox());
												BigDecimal b2 = new BigDecimal(
														oldTarget.getBox());
												BigDecimal num = b1.add(b2);
												if (num.compareTo(new BigDecimal(
														-0.01)) == -1) {
													StringBuffer buf = new StringBuffer();
													buf.append(tag.getOrgName())
															.append(oldTarget
																	.getCustNameZH())
															.append(tag
																	.getTheYear())
															.append("年")
															.append(tag
																	.getTheMonth())
															.append("月")
															.append("修改后的目标量将少于0，请重调整后重试！.</br>");

													rcs2 += buf.toString()
															+ "</br>";
												}
											}
										} else {
											// 组织目标修改;负数时，不能少于组织经销商目标，组织下没有经销目标时，组织目标量不能少于0
											if (Double.valueOf(tag.getBox()) < 0) {
												BigDecimal b1 = new BigDecimal(
														tag.getBox());
												BigDecimal b2 = new BigDecimal(
														oldTarget.getBox());
												BigDecimal orgNum = b1.add(b2);
												if (orgNum
														.compareTo(new BigDecimal(
																-0.01)) == -1) {
													StringBuffer buf = new StringBuffer();
													buf.append(tag.getOrgName())
															.append(tag
																	.getTheYear())
															.append("年")
															.append(tag
																	.getTheMonth())
															.append(tag
																	.getBezei())
															.append("月修改后的目标量将少于0，请重调整后重试！.</br>");

													rcs2 += buf.toString()
															+ "</br>";
												} else {
													BCustomerTarget tar1 = orgDMap
															.get(tag.getOrgId()
																	+ tag.getTheYear()
																	+ tag.getTheMonth()
																	+ tag.getMatter());
													if (tar1 == null) {
														// 组织减少的目标量不能超过待开
														tar1 = new BCustomerTarget();
														tar1.setOrgId(tag
																.getOrgId());
														tar1.setTheMonth(tag
																.getTheMonth());
														tar1.setTheYear(tag
																.getTheYear());
														tar1.setMatter(tag
																.getMatter());
														// 查组织待开目标量
														tar1 = goalService
																.getGoalMessege(tar1);
														orgDMap.put(
																tag.getOrgId()
																		+ tag.getTheYear()
																		+ tag.getTheMonth()
																		+ tag.getMatter(),
																tar1);
													}
													if (null != tar1) {
														if (Double.valueOf(tar1
																.getBox()) <= 0) {
															rcs2 += tag
																	.getOrgName()
																	+ tag.getTheYear()
																	+ "年"
																	+ tag.getTheMonth()
																	+ "月"
																	+ tag.getBezei()
																	+ "组织待开目标量余量不足，请重调整后重试!.</br>";
														} else {
															double yNum = Double
																	.valueOf(tag
																			.getBox())
																	+ Double.valueOf(tar1
																			.getBox());
															if (yNum < 0) {
																rcs2 += tag
																		.getOrgName()
																		+ tag.getTheYear()
																		+ "年"
																		+ tag.getTheMonth()
																		+ "月"
																		+ tag.getBezei()
																		+ "组织目标量修改后待开将少于0，请重调整后重试!.</br>";
															}
														}
													}
												}

											}// end 组织为负

										}// end 组织验证
									}// old查询
								}// 是否为预算目标
								bctList.set(i, tag);
							}
							if (!"".equals(rcs2)) {
								this.setFailMessage("批量修改失败</br>相关错误信息：</br>"
										+ rcs2);
								setImportPercent(99);
								return RESULT_MESSAGE;
							}
							rcs3 = "";
							if (!mapOrg.isEmpty()) {
								for (Map.Entry<String, BCustomerTarget> MapString : mapOrg
										.entrySet()) {
									BCustomerTarget value = MapString
											.getValue();// 获取键值对的值
									// * 预算目标量不能超过组织待开目标量
									BCustomerTarget tar = orgDMap.get(value
											.getOrgId()
											+ value.getTheYear()
											+ value.getTheMonth()
											+ value.getMatter());
									if (tar == null) {
										tar = new BCustomerTarget();
										tar.setOrgId(value.getOrgId());
										tar.setTheYear(value.getTheYear());
										tar.setTheMonth(value.getTheMonth());
										tar.setMatter(value.getMatter());
										// 组织待开目标量
										tar = goalService.getGoalMessege(tar);
										orgDMap.put(
												value.getOrgId()
														+ value.getTheYear()
														+ value.getTheMonth()
														+ value.getMatter(),
												tar);
									}
									if (null != tar) {
										if (Double.valueOf(tar.getBox()) < 0) {
											rcs3 += value.getOrgName()
													+ value.getTheYear() + "年"
													+ value.getTheMonth() + "月"
													+ value.getBezei()
													+ "组织待开目标量不足，请重试!.</br>";
										} else {
											double boxD = Double.valueOf(tar
													.getBox());
											double boxSum = Double
													.valueOf(value.getBox());
											if (boxSum > 0) {
												if (boxSum > boxD) {
													rcs3 += value.getOrgName()
															+ value.getTheYear()
															+ "年"
															+ value.getTheMonth()
															+ "月"
															+ value.getBezei()
															+ "修改的经销商目标量总计超过了组织待开目标量，请调整后重试！.</br>";
												}
											}
										}
									} else {
										rcs3 += value.getOrgName()
												+ value.getTheYear() + "年"
												+ value.getTheMonth() + "月"
												+ value.getBezei()
												+ "还没有组织目标量，请先维护组织目标量！.</br>";
									}
								}
							}
							if (!"".equals(rcs3)) {
								this.setFailMessage("批量修改失败</br>相关错误信息：</br>"
										+ rcs3);
								setImportPercent(99);
								return RESULT_MESSAGE;
							} else {
								rcs2 = "";
								for (int q = 0; q < bctList.size(); q++) {
									// 百分比进度条标识
									int percent = (int) Math
											.floor((q + 1)
													/ (double) content.size()
													* 100 / 2) + 50;
									setImportPercent(percent);

									BCustomerTarget tagertObject = new BCustomerTarget();
									tagertObject = bctList.get(q);
									tagertObject.setCtState("0");
									tagertObject.setOpId(this.getUser()
											.getUserId());
									BCustomerTarget oldTarget = new BCustomerTarget();
									oldTarget.setTheYear(tagertObject
											.getTheYear());
									oldTarget.setTheMonth(tagertObject
											.getTheMonth());
									oldTarget.setOrgId(tagertObject.getOrgId());
									if (StringUtils.isNotEmpty(tagertObject
											.getCustId())) {
										oldTarget.setCustId(tagertObject
												.getCustId());
									}
									oldTarget.setKunnrGoalType(tagertObject
											.getKunnrGoalType());
									oldTarget.setMark(tagertObject.getMark());
									oldTarget.setMatter(tagertObject
											.getMatter());
									oldTarget = goalService
											.getGoalByCondition(oldTarget);
									oldTarget.setOpId(this.getUser()
											.getUserId());
									BigDecimal old = new BigDecimal(
											oldTarget.getBox());
									BigDecimal cur = new BigDecimal(
											tagertObject.getBox());
									BigDecimal box = old.add(cur);
									if (box.compareTo(new BigDecimal(0.01)) == -1) {
										box = new BigDecimal(0);
									}
									BigDecimal num = oldTarget.getTargetNum()
											.add(tagertObject.getTargetNum());
									oldTarget.setBox(String.valueOf(box));
									oldTarget.setTargetNum(num);
									// 批量更新至数据库
									try {
										rs = goalService.updateGoal(oldTarget);
									} catch (Exception e) {
										logger.error(e);
										rcs2 += "第" + (q + 2)
												+ "条数据保存数据库失败.请联系系统管理员.</br>";
										result.append(rcs2.toString() + "</br>");
									}
								}
							}

						}
						this.getSession().setAttribute("bctList", bctList);
						if ("".equals(rcs2)) {
							this.setSuccessMessage("批量修改成功！修改数量为:"
									+ bctList.size() + "行");
						} else if (!"".equals(rcs2)) {
							this.setFailMessage("导入失败</br>相关错误信息：</br>"
									+ result.toString());
						}
						setImportPercent(99);
						return RESULT_MESSAGE;
					}
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				setImportPercent(99);
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		setImportPercent(99);
		return RESULT_MESSAGE;
	}

	/**
	 * 事务流程修改XML 经销商目标的 By Excel add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@SuppressWarnings({ "deprecation", "static-access" })
	public String updateXMLGoal() {
		HSSFSheet rs = null;
		PrintWriter out = null;
		String resultMsg = null;
		Date date1 = new Date();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		String custnumberp2 = "^\\d{1,}$";
		bctList = new ArrayList<BCustomerTarget>();
		BCustomerTarget bct1 = new BCustomerTarget();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			String orgName = orgServiceHessian.getOrgByUserId(
					getUser().getUserId()).getOrgName();
			title = "" + year + "年" + orgName + "经销商目标";
			Object[] res = new Object[7];
			res[0] = eventId;
			res[1] = this.getUser().getUserId();
			res[2] = nextUserId;
			res[3] = title;
			res[4] = appUrl + "/goalAction!goalFormView.jspa";
			res[5] = key4open;
			res[6] = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						HSSFRow row = rs.getRow(j);
						int rsColumns = row.getLastCellNum();
						// int rsRows = rs.getLastRowNum();
						// if (rsColumns == 8) {
						// for(int i=0;i<rsColumns;i++){
						BCustomerTarget bct = new BCustomerTarget();
						Kunnr kunnr = new Kunnr();
						Materiel mat = new Materiel();
						String userId = getUser().getUserId().toString();
						bct.setOpId(userId);
						bct.setOpName(this.getUser().getUserName());
						bct.setOrgId(getUser().getOrgId());
						bct.setCtDate((String.valueOf(year)) + "-"
								+ String.valueOf(date1.getMonth() + 1) + "-"
								+ String.valueOf(date1.getDate()));
						bct.setCtState("0");
						bct.setTrFlag("S");
						row.getCell(0).setCellType(
								row.getCell(0).CELL_TYPE_STRING);
						bct.setCustId(StringUtils.leftPad(String.valueOf(row
								.getCell(0).getRichStringCellValue()), 8, '0'));
						kunnr.setKunnr(StringUtils.leftPad(String.valueOf(row
								.getCell(0).getRichStringCellValue()), 8, '0'));
						size = goalService.getKunnrsList1(kunnr).size();
						if (size != 0) {
							bct.setCustNameZH(goalService.getKunnrsList1(kunnr)
									.get(0).getName1());
						} else {
							this.setFailMessage("第" + j + "行SAP代码不存在");
							break;
						}
						if (row.getCell(1) != null) {
							row.getCell(1).setCellType(
									row.getCell(1).CELL_TYPE_STRING);
							String str = row.getCell(1).getStringCellValue();
							BigDecimal big = new BigDecimal(str);
							bct.setTargetNum(big);
						}
						if (row.getCell(2) != null) {
							row.getCell(2).setCellType(
									row.getCell(2).CELL_TYPE_STRING);
							bct.setBox(String.valueOf(row.getCell(2)
									.getRichStringCellValue()));
						}
						if (row.getCell(3) != null) {
							row.getCell(3).setCellType(
									row.getCell(3).CELL_TYPE_STRING);
							bct.setTheYear(Long.valueOf(row.getCell(3)
									.getRichStringCellValue().toString()));
						}
						if (row.getCell(4) != null) {
							row.getCell(4).setCellType(
									row.getCell(4).CELL_TYPE_STRING);
							bct.setTheMonth(row.getCell(4)
									.getRichStringCellValue().toString());
						}
						if (row.getCell(5) != null) {
							row.getCell(5).setCellType(
									row.getCell(5).CELL_TYPE_STRING);
							String str = StringUtils.leftPad(row.getCell(5)
									.getRichStringCellValue().toString(), 18,
									'0');
							bct.setMatter(str);
						}
						//
						mat.setMatnr(StringUtils.leftPad(row.getCell(5)
								.getRichStringCellValue().toString(), 18, '0'));
						size1 = goalService.getMatList1(mat).size();
						if (size1 != 0) {
							bct.setBezei(goalService.getMatList1(mat).get(0)
									.getBezei());
						} else {
							this.setFailMessage("第" + j + "行物料不存在");
							break;
						}
						if (goalService.getGoalListCount1(bct) != 0l) {
							this.setFailMessage("第" + j + "行活动目标值已审批完成请勿重复提交");
							break;
						}
						for (int x = 0; x < bctList.size(); x++) {
							if (bctList.get(x).getCustId()
									.equals(bct.getCustId())
									&& bctList.get(x).getMatter()
											.equals(bct.getMatter())
									&& bctList.get(x).getTheYear()
											.equals(bct.getTheYear())
									&& bctList.get(x).getTheMonth()
											.equals(bct.getTheMonth())) {
								this.setFailMessage("第" + j + "行活动目标值与第"
										+ (x + 1) + "行目标值重复");

								break;
							}
						}
						bctList.add(bct);
						bct1.setOpId(userId);
						bct1.setOpName(this.getUser().getUserName());
						bct1.setOrgId(getUser().getOrgId());
						bct1.setTransaction_id(eventId);
						bct1.setCtDate((String.valueOf(year)) + "-"
								+ String.valueOf(date1.getMonth() + 1) + "-"
								+ String.valueOf(date1.getDate()));
						bct1.setBctList(bctList);
						if (String.valueOf(
								row.getCell(0).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行经销商sap代码为空'}";
							this.setFailMessage("excel中第" + j + "行经销商sap代码为空");
							break;
						} else if (String.valueOf(
								row.getCell(1).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标值为空'}";
							this.setFailMessage("excel中第" + j + "行目标值为空");
							break;
						} else if (String.valueOf(
								row.getCell(2).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标箱数为空'}";
							this.setFailMessage("excel中第" + j + "行目标箱数为空");
							break;
						} else if (String.valueOf(
								row.getCell(3).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标箱数为空'}";
							this.setFailMessage("excel中第" + j + "行目标年为空");
							break;
						} else if (String.valueOf(
								row.getCell(4).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标箱数为空'}";
							this.setFailMessage("excel中第" + j + "行目标月为空");
							break;
						} else if (String.valueOf(
								row.getCell(5).getRichStringCellValue())
								.equals("")) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行物料号为空'}";
							this.setFailMessage("excel中第" + j + "行物料号为空");
							break;
						} else if (!String.valueOf(
								row.getCell(2).getRichStringCellValue())
								.matches(custnumberp2)) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标值必须为数字！'}";
							this.setFailMessage("excel中第" + j + "行目标值必须为数字");
							break;
						} else if (!String.valueOf(
								row.getCell(3).getRichStringCellValue())
								.matches(custnumberp2)) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标箱数必须为数字！'}";
							this.setFailMessage("excel中第" + j + "行目标箱数必须为数字");
							break;
						} else if (!String.valueOf(
								row.getCell(4).getRichStringCellValue())
								.matches(custnumberp2)) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标年必须为数字！'}";
							this.setFailMessage("excel中第" + j + "行目标年必须为数字");
							break;
						} else if (!String.valueOf(
								row.getCell(5).getRichStringCellValue())
								.matches(custnumberp2)) {
							// resultMsg="{success:false,msg:'excel中第"+(j)+"行目标月必须为数字！'}";
							this.setFailMessage("excel中第" + j + "行目标月必须为数字");
							break;
						}
					}
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
			}
			if (StringUtils.isEmpty(resultMsg)) {
				if (JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + '/' + eventId
						+ ".xml", bct1, getUser().getUserId().toString(),
						getUser().getUserName().toString(), subFolders)) {
					this.setSuccessMessage("事务ID" + eventId + "经销商目标修改完成");
				} else {
					this.setFailMessage("事务提交失败");
				}
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("数据有问题");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 他们说这是拿到XML add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String goalFormView() {
		this.setCurStaId(curStaId);
		// curStaId="start";
		modifyFlag = "Y";
		Calendar ctime = Calendar.getInstance();
		year = ctime.get(Calendar.YEAR);
		int month1 = ctime.get(Calendar.MONTH) + 1;
		month = StringUtils.leftPad(String.valueOf(month1), 2, '0');
		orgId = this.getUser().getOrgId();
		userId = this.getUser().getUserId();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new BCustomerTarget());
			if (info != null) {
				bct = (BCustomerTarget) info.getObject();
				bctList = bct.getBctList();
			}
		}
		return "view";
	}

	/**
	 * 页面上修改XML 走事务流程的add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@SuppressWarnings("unchecked")
	public String updateGoalForm() {
		BCustomerTarget bct = new BCustomerTarget();
		BCustomerTarget bct1 = new BCustomerTarget();
		List<BCustomerTarget> detailList = JsonUtil.getDTOList(detailJsonStr,
				BCustomerTarget.class);
		// List<BCustomerTarget> list=new ArrayList<BCustomerTarget>();
		for (int i = 0; i < detailList.size(); i++) {
			bct1 = detailList.get(i);
			for (int x = i + 1; x < detailList.size(); x++) {
				if (bct1.getCustId().equals(detailList.get(x).getCustId())
						&& bct1.getMatter().equals(
								detailList.get(x).getMatter())
						&& bct1.getTheYear().equals(
								detailList.get(x).getTheYear())
						&& bct1.getTheMonth().equals(
								detailList.get(x).getTheMonth())) {
					this.setFailMessage("第" + (i + 1) + "行活动目标值与第" + (x + 1)
							+ "行目标值重复");
					return RESULT_MESSAGE;
				}
			}
			if (goalService.getGoalListCount1(detailList.get(i)) != 0l) {
				this.setFailMessage(detailList.get(i).getTheYear() + "年"
						+ detailList.get(i).getTheMonth() + "月" + "物料号"
						+ detailList.get(i).getMatter() + "目标值已在数据库存在");
				return RESULT_MESSAGE;
			}
		}
		bct.setBctList(detailList);
		bct.setOpId(this.getUser().getUserId());
		// 更新XML文件
		if (!JavaBeanXMLUtil
				.JavaBean2XML(xmlFilePath + "/" + eventId + ".xml", bct,
						getUser().getUserId(), getUser().getUserName(),
						subFolders)) {
			this.setFailMessage("更新XML出错!");
		} else {
			this.setSuccessMessage("修改成功");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 经销商修改模版
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@PermissionSearch
	public String exportGoal() {
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new BCustomerTarget());
			if (info != null) {
				bct = (BCustomerTarget) info.getObject();
				bctList = bct.getBctList();
			}
		}
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("经销商目标值批量修改表".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("经销商目标值", 0);
			// WritableSheet wsheet1 = wbook.createSheet("渠道信息",1);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			// WritableSheet.setColumnView(int i,int width);
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30);
			// wsheet.setRowView(0,10);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			// cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// 表头
			Label label_0 = new Label(0, 0, "经销商sap代码");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "目标值");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "目标箱数");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "目标年");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "目标月");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			Label label_5 = new Label(5, 0, "物料编号(后8位)");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			Label label_6 = new Label(6, 0, "物料描述(批量修改时是非必填项)");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);
			Label label_7 = new Label(7, 0, "客户名称(批量修改时是非必填项)");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);
			wsheet.setColumnView(0, 20);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 30);
			wsheet.setColumnView(7, 30);
			// wsheet.setColumnView(3, 20);
			if (null != bctList) {
				for (int x = 0; x < bctList.size(); x++) {
					BCustomerTarget bct = new BCustomerTarget();
					bct = bctList.get(x);
					// 表格主体
					Label label_x_0 = new Label(0, x + 1, bct.getCustId());
					// label_x_0.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_x_0);
					Label label_x_1 = new Label(1, x + 1, String.valueOf(bct
							.getTargetNum()));
					// label_x_1.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_1);
					Label label_x_2 = new Label(2, x + 1, String.valueOf(bct
							.getBox()));
					wsheet.addCell(label_x_2);
					Label label_x_3 = new Label(3, x + 1, String.valueOf(bct
							.getTheYear()));
					wsheet.addCell(label_x_3);
					Label label_x_4 = new Label(4, x + 1, String.valueOf(bct
							.getTheMonth()));
					wsheet.addCell(label_x_4);
					Label label_x_5 = new Label(5, x + 1, String.valueOf(bct
							.getMatter().substring(10, 18)));
					wsheet.addCell(label_x_5);
					Label label_x_6 = new Label(6, x + 1, String.valueOf(bct
							.getBezei()));
					wsheet.addCell(label_x_6);
					Label label_x_7 = new Label(7, x + 1, String.valueOf(bct
							.getCustNameZH()));
					wsheet.addCell(label_x_7);
				}
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
		return RESULT_MESSAGE;
	}

	/**
	 * 经销商修改模版
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@PermissionSearch
	public String exportGoalCsv() {
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile,
					new BCustomerTarget());
			if (info != null) {
				bct = (BCustomerTarget) info.getObject();
				bctList = bct.getBctList();
			}
		}
		OutputStream os = null;
		String report_name = "经销商目标值批量修改表";
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
			linedata.append("经销商sap代码(或组织ID)");
			linedata.append(",");
			linedata.append("目标值");
			linedata.append(",");
			linedata.append("目标箱数");
			linedata.append(",");
			linedata.append("目标年");
			linedata.append(",");
			linedata.append("目标月");
			linedata.append(",");
			linedata.append("物料编号(后8位)");
			linedata.append(",");
			linedata.append("物料描述(批量修改时是非必填项)");
			linedata.append(",");
			linedata.append("客户名称(批量修改时是非必填项)");
			linedata.append("\n");
			for (int i = 0; i < bctList.size(); i++) {
				BCustomerTarget bct = new BCustomerTarget();
				bct = bctList.get(i);
				linedata.append(bct.getCustId());
				linedata.append(",");
				linedata.append(bct.getTargetNum());
				linedata.append(",");
				linedata.append(bct.getBox());
				linedata.append(",");
				linedata.append(bct.getTheYear());
				linedata.append(",");
				linedata.append(bct.getTheMonth());
				linedata.append(",");
				linedata.append(bct.getMatter());
				linedata.append(",");
				linedata.append(bct.getBezei());
				linedata.append(",");
				linedata.append(bct.getCustNameZH());
				linedata.append("\n");
			}
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
	 * 拿到组织树 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		try {
			orgTreeList = orgServiceHessian.getOrgListByOrgParentId(node);
		} catch (Exception e) {

			logger.error(node, e);
		}
		if (orgTreeList == null || orgTreeList.size() == 0) {
			return JSON;
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}

		return JSON;
	}

	public String orgTreePage() {
		userId = this.getUser().getUserId();
		Borg borg = orgServiceHessian.getOrgByUserId(userId);
		if ("B".equals(borg.getOrgCity())) {
			return "orgAllTree";
		}
		return "orgtree";
	}

	/**
	 * Excel导出目标量数据 byws
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportDataContract() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		List<BCustomerTarget> bctList1 = new ArrayList<BCustomerTarget>();
		List<BCustomerTarget> bctList2 = new ArrayList<BCustomerTarget>();
		if (bct == null) {
			bct = new BCustomerTarget();
		}
		bct.setMatter(matter);
		bct.setCustId(custId);
		if (StringUtils.isNotEmpty(brand)) {
			bct.setBrand(brand);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			bct.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			bct.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			bct.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			bct.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}else if(StringUtils.isEmpty(startDate)){
			bct.setStartDate(cal.get(Calendar.YEAR)+"-"+String.format("%02d", cal.get(Calendar.MONTH)));
			bct.setEndDate(cal.get(Calendar.YEAR)+"-"+String.format("%02d", cal.get(Calendar.MONTH) ));
		}
		/*
		 * bct.setTheYear(Long.valueOf(year)); bct.setTheMonth(month);
		 *//* 此乃搜索条件 */
		bct.setMark("Y");
		bct.setKunnrGoalType(kunnrGoalType);
		bct.setTrFlag(trFlag);
		bct.setStart(0);
		bct.setPagination("false");
		// BCustomerTarget c = new BCustomerTarget();
		// BCustomerTarget c2 = new BCustomerTarget();
		if (type_ny.equals("N")) {
			if (conFlag.equals("N")) {
				size = goalService.getGoalListCountForMBL(bct);
				if (size != 0) {
					bctList = goalService.getGoalReportListForMBL(bct);
				}
			} else {
				if (StringUtils.isEmpty(custId)) {
					size = goalService.getGoalDKCount(bct);
					if (size > 0) {
						bctList = goalService.getGoalDK(bct);
					}
				}
			}

		} else {
			if (StringUtils.isNotEmpty(matter)) {
				size = 0;
				bctList = null;
			} else {

				if (conFlag.equals("N")) {
					size = goalService.getGoalSumCount(bct);
					if (size > 0) {
						bctList = goalService.getGoalSum(bct);
					}
				} else {
					if (StringUtils.isEmpty(custId)) {
						size = goalService.getGoalDKAllCount(bct);
						if (size > 0) {
							bctList = goalService.getGoalDKAll(bct);
						}
					}
				}

			}
		}
		try {
			if ("N".equals(type_ny)) {
				String[] colNames = { "orgName2", "orgName1", "orgName",
						"custId", "custNameZH", "theYear", "theMonth",
						"brand","matter", "bezei", "box", "targetNum", "contractBox",
						"contractNum", "status", "ztStatus" };
				String[] rowName = { "一级组织", "二级组织", "组织名称", "经销商ID", "经销商",
						"目标年", "目标月","品牌", "品项or四级科目(SKU)", "品项or四级科目(SKU)名称", "销售目标量(标箱)", "销售目标量(金额)",
						"协议目标量(标箱)", "协议目标量(金额)", "客户状态", "状态" };
				ExportExcelUtil eeu = new ExportExcelUtil("目标量", rowName,
						colNames, bctList);
				eeu.export();
			} else {
				String[] colNames = { "orgName2", "orgName1", "orgName",
						"custId", "custNameZH","theYear", "theMonth", "box",
						"targetNum", "contractBox", "contractNum", "status",
						"ztStatus" };
				String[] rowName = { "一级组织", "二级组织", "组织名称", "经销商ID", "经销商",
						"目标年", "目标月", "销售目标量(标箱)", "销售目标量(金额)", "协议目标量(标箱)",
						"协议目标量(金额)", "客户状态", "状态" };
				ExportExcelUtil eeu = new ExportExcelUtil("目标量", rowName,
						colNames, bctList);
				eeu.export();
			}

			this.setSuccessMessage("导出成功!");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败,请联系管理员!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: Excel导出目标量数据
	 * 销售目标量和协议目标量
	 * @author: xg.chen
	 * @date:2017年1月10日 上午8:36:26
	 * @version 1.0
	 * @return
	 */
	@PermissionSearch
	public String exportData() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		String report_name = "目标量数据";
		//查询数据
		if (bct == null) {
			bct = new BCustomerTarget();
		}
		bct.setMatter(matter);
		bct.setCustId(custId);
		if (StringUtils.isNotEmpty(orgId)) {
			bct.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			bct.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			bct.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			bct.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}
		bct.setMark(mark);
		bct.setKunnrGoalType(kunnrGoalType);
		bct.setTrFlag(trFlag);
		bct.setPagination("false");
		size = goalService.getGoalListCount(bct);
		if (size != 0) {
			bctList = goalService.getGoalList(bct);
		}
		//设置写入字段
		List<String> props = new ArrayList<String>();
		HttpServletResponse response = getServletResponse();
		try {
			props.add("orgName");
			if ("Y".equals(mark)) {
				props.add("custNameZH");
			}
			props.add("theYear");
			props.add("theMonth");
			props.add("maktx01");
			props.add("bezei");
			props.add("box");
			props.add("targetNum");
			if ("N".equals(mark)) {
				props.add("boxD");
				props.add("targetNumD");
			}
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("目标量", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_1 = new Label(0, 0, "组织名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			if ("N".equals(mark)) {
				Label label_2 = new Label(1, 0, "年");
				label_2.setCellFormat(cellFormat_top);
				wsheet.addCell(label_2);
				Label label_3 = new Label(2, 0, "月");
				label_3.setCellFormat(cellFormat_top);
				wsheet.addCell(label_3);
				Label label_4 = new Label(4, 0, "品牌");
				label_4.setCellFormat(cellFormat_top);
				wsheet.addCell(label_4);
				Label label_5 = new Label(5, 0, "物料描述");
				label_5.setCellFormat(cellFormat_top);
				wsheet.addCell(label_5);
				Label label_6 = new Label(6, 0, "目标箱数(标箱)");
				label_6.setCellFormat(cellFormat_top);
				wsheet.addCell(label_6);

				Label label_7 = new Label(7, 0, "目标金额(元)");
				label_7.setCellFormat(cellFormat_top);
				wsheet.addCell(label_7);
				wsheet.setColumnView(0, 25);
				wsheet.setColumnView(3, 25);
				wsheet.setColumnView(4, 15);
				wsheet.setColumnView(5, 15);
				wsheet.setColumnView(6, 15);

				Label label_8 = new Label(8, 0, "待开目标箱数(标箱)");
				label_8.setCellFormat(cellFormat_top);
				wsheet.addCell(label_8);
				
				Label label_9 = new Label(9, 0, "待开目标金额(元)");
				label_9.setCellFormat(cellFormat_top);
				wsheet.addCell(label_9);
				wsheet.setColumnView(8, 20);
				wsheet.setColumnView(9, 20);
				
				WritableCellFormat format = new WritableCellFormat();
				DecimalFormat df = new DecimalFormat("###,##0.00");
				format.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);// 边框
				format.setAlignment(jxl.write.Alignment.RIGHT);// 对齐方式

			} else {
				//表头
				Label label_0 = new Label(1, 0, "经销商名称");
				label_0.setCellFormat(cellFormat_top);
				wsheet.addCell(label_0);
				Label label_2 = new Label(2, 0, "年");
				label_2.setCellFormat(cellFormat_top);
				wsheet.addCell(label_2);
				Label label_3 = new Label(3, 0, "月");
				label_3.setCellFormat(cellFormat_top);
				wsheet.addCell(label_3);
				Label label_4 = new Label(4, 0, "品牌");
				label_4.setCellFormat(cellFormat_top);
				wsheet.addCell(label_4);
				Label label_5 = new Label(5, 0, "品项or四级科目(SKU) ");
				label_5.setCellFormat(cellFormat_top);
				wsheet.addCell(label_5);
				Label label_6 = new Label(6, 0, "目标箱数(标箱)");
				label_6.setCellFormat(cellFormat_top);
				wsheet.addCell(label_6);
				Label label_7 = new Label(7, 0, "目标金额(元)");
				label_7.setCellFormat(cellFormat_top);
				wsheet.addCell(label_7);
				//列宽
				wsheet.setColumnView(0, 25);
				wsheet.setColumnView(1, 25);
				wsheet.setColumnView(4, 25);
				wsheet.setColumnView(5, 15);
				wsheet.setColumnView(6, 15);
				wsheet.setColumnView(7, 15);
				//表格导出方式
				WritableCellFormat format = new WritableCellFormat();
				DecimalFormat df = new DecimalFormat("###,##0.00");
				format.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);// 边框
				format.setAlignment(jxl.write.Alignment.RIGHT);// 对齐方式
			}
			//写入数据并导出
			ExcelUtil.createExcelWithBook(wbook, props, bctList);
			this.setSuccessMessage("导出成功!");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败,请联系管理员!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 跳转预算物料定价页面(bo报表使用) zhusiliang
	 * 
	 * @return
	 */
	public String toSearchMatterPriceBo() {
		return "toSearchMatterPriceBo";
	}

	/**
	 * 预算物料定价查询(bo报表使用)
	 * 
	 * @return
	 */
	@JsonResult(field = "priceBoList", include = { "year", "mvgr1", "mvgTxt",
			"mvgPrice", "salesPrice", "mvgRatio", "cupId", "cupType","sku","skuText" }, total = "size")
	public String searchMatterPriceBo() {
		priceBo = new MatterPriceBO();
		priceBo.setStart(this.getStart());
		priceBo.setEnd(this.getEnd());
		if (StringUtils.isNotEmpty(fiscalYear)) {
			priceBo.setYear(fiscalYear);
		}
		if (StringUtils.isNotEmpty(mvgr1)) {
			priceBo.setMvgr1(mvgr1);
		}
		if (StringUtils.isNotEmpty(mvgTxt)) {
			priceBo.setMvgTxt(mvgTxt);
		}
		if (StringUtils.isNotEmpty(cupType)) {
			priceBo.setCupType(cupType);
		}
		size = goalService.searchMatterPriceBoCount(priceBo);
		if (size > 0) {
			priceBoList = goalService.searchMatterPriceBoList(priceBo);
		}
		return JSON;
	}

	public String downloadMatterPriceBo() {
		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		priceBo = new MatterPriceBO();
		if (StringUtils.isNotEmpty(fiscalYear)) {
			priceBo.setYear(fiscalYear);
		}
		if (StringUtils.isNotEmpty(mvgr1)) {
			priceBo.setMvgr1(mvgr1);
		}
		if (StringUtils.isNotEmpty(mvgTxt)) {
			priceBo.setMvgTxt(mvgTxt);
		}
		if (StringUtils.isNotEmpty(cupType)) {
			priceBo.setCupType(cupType);
		}
		priceBo.setStart(0);
		priceBo.setEnd(100000);
		priceBoList = goalService.searchMatterPriceBoList(priceBo);
		if (priceBoList.size() == 0) {
			this.setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
		}
		priceBoListToExcel(priceBoList);
		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}

	/**
	 * 预算物料定价(bo报表使用) 导出excel
	 */
	@PermissionSearch
	private void priceBoListToExcel(List<MatterPriceBO> unMiantList) {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "预算物料定价列表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 20);
			wsheet.setColumnView(1, 20);
			wsheet.setColumnView(2, 20);
			wsheet.setColumnView(3, 20);
			wsheet.setColumnView(4, 20);
			wsheet.setColumnView(5, 20);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 20);
			wsheet.setColumnView(8, 20);
			wsheet.addCell(new Label(0, 0, "财年"));
			wsheet.addCell(new Label(1, 0, "四级科目"));
			wsheet.addCell(new Label(2, 0, "四级科目描述"));
			wsheet.addCell(new Label(3, 0, "协议目标打印组"));
			wsheet.addCell(new Label(4, 0, "协议目标打印组描述"));
			wsheet.addCell(new Label(5, 0, "目标量单价(元)"));
			wsheet.addCell(new Label(6, 0, "销售单价(元)"));
			wsheet.addCell(new Label(7, 0, "所标箱系数"));
			wsheet.addCell(new Label(8, 0, "杯种类"));

			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfF = new WritableCellFormat(wfc);
			wcfF.setAlignment(jxl.format.Alignment.CENTRE);
			wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfF.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.HAIR);
			for (int i = 1; i <= unMiantList.size(); i++) {
				if (i >= 65534) {
					break;
				}
				wsheet.addCell(new Label(0, i,
						unMiantList.get(i - 1).getYear(), wcfF));
				wsheet.addCell(new Label(1, i, unMiantList.get(i - 1)
						.getMvgr1(), wcfF));
				wsheet.addCell(new Label(2, i, unMiantList.get(i - 1)
						.getMvgTxt(), wcfF));
				wsheet.addCell(new Label(3, i, unMiantList.get(i - 1)
						.getSku(), wcfF));
				wsheet.addCell(new Label(4, i, unMiantList.get(i - 1)
						.getSkuText(), wcfF));
				
				if (unMiantList.get(i - 1).getMvgPrice() != null) {
					wsheet.addCell(new Number(5, i, unMiantList.get(i - 1)
							.getMvgPrice(), wcfF));
				} else {
					wsheet.addCell(new Label(5, i, "", wcfF));
				}
				if (unMiantList.get(i - 1).getSalesPrice() != null) {
					wsheet.addCell(new Number(6, i, unMiantList.get(i - 1)
							.getSalesPrice(), wcfF));
				} else {
					wsheet.addCell(new Label(6, i, "", wcfF));
				}
				if (unMiantList.get(i - 1).getMvgRatio() != null) {
					wsheet.addCell(new Number(7, i, unMiantList.get(i - 1)
							.getMvgRatio(), wcfF));
				} else {
					wsheet.addCell(new Label(7, i, "", wcfF));
				}
				wsheet.addCell(new Label(8, i, unMiantList.get(i - 1)
						.getCupType(), wcfF));
			}
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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

	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else {
			download = "Yes";
		}
		return JSON;
	}

	/**
	 * 跳转到批量导入页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toImportMvgBo() {
		return "toImportMvgBo";
	}

	/***
	 * 
	 * csv 模板导出
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportToCSV() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter print = null;
		try {
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("预算物料价格导入".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("财年");
			linedata.append(",");
			linedata.append("品项or四级科目(SKU)");
			linedata.append(",");
			linedata.append("品项or四级科目(SKU)名称");
			linedata.append(",");
			linedata.append("协议目标打印组");
			linedata.append(",");
			linedata.append("协议目标打印组描述");
			linedata.append(",");
			linedata.append("目标量单价(元)");
			linedata.append(",");
			linedata.append("销售单价(元)");
			linedata.append(",");
			linedata.append("标箱系数");
			linedata.append(",");
			linedata.append("杯种类ID");
			linedata.append(",");
			linedata.append("杯种类");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (print != null)
				print.close();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 批量导入检查
	 * 
	 * @return
	 */
	public String saveImportBoList() {
		StringBuffer result = new StringBuffer();
		try {
			priceBoList = new ArrayList<MatterPriceBO>();
			if (StringUtils.isNotEmpty(fileContentFileName)) {
				String end = StringUtils.substring(fileContentFileName,
						StringUtils.lastIndexOf(fileContentFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(fileContent.toString())); // 获取String数组
					List<MatterPriceBO> priceBoList = new ArrayList<MatterPriceBO>();// list
					MatterPriceBO priceBo = null;
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						String rcs = "";
						if (s.length == 10) {
							String i0 = s[0];
							String i1 = s[1];
							String i2 = s[2];
							String i3 = s[3];
							String i4 = s[4];
							String i5 = s[5];
							String i6 = s[6];
							String i7 = s[7];
							String i8 = s[8];
							String i9 = s[9];
							priceBo = new MatterPriceBO();
							if (!"".equals(i0) && null != i0) {
								priceBo.setYear(i0);
							} else {
								rcs = rcs + "导入财年有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i1) && null != i1) {
								if(goalService.searchSku(i1)==0){
									rcs = rcs + "品项or四级科目(SKU)不存在：第" + (j + 2) + "行</br>";
								}
								priceBo.setMvgr1(i1);
							} else {
								rcs = rcs + "品项or四级科目(SKU)有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i2) && null != i2) {
								if(goalService.searchSkuText(i2)==0){
									rcs = rcs + "品项or四级科目(SKU)名称不存在：第" + (j + 2) + "行</br>";
								}
								priceBo.setMvgTxt(i2);
							} else {
								rcs = rcs + "品项or四级科目(SKU)名称有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i3) && null != i3) {
								priceBo.setSku(i3);
							} 
							else {
								rcs = rcs + "协议目标打印组有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i4) && null != i4) {
								priceBo.setSkuText(i4);
							} 
							else {
								rcs = rcs + "协议目标打印组描述有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i5) && null != i5) {
								priceBo.setMvgPrice(Double.valueOf(i5));
							} else {
								rcs = rcs + "目标量单价有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i6) && null != i6) {
								priceBo.setSalesPrice(Double.valueOf(i6));
							} else {
								rcs = rcs + "销售单价有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i7) && null != i7) {
								priceBo.setMvgRatio(Double.valueOf(i7));
							} else {
								rcs = rcs + "标箱系数有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i8) && null != i8) {
								priceBo.setCupId(i8);
							} else {
								rcs = rcs + "杯种类ID有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i9) && null != i9) {
								priceBo.setCupType(i9);
							} else {
								rcs = rcs + "杯种类有问题：第" + (j + 2) + "行</br>";
							}
							if (rcs.equals("")) {
								goalService.insertMarPrice(priceBo);
								priceBoList.add(priceBo);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("priceBoList",
								priceBoList);
						this.setSuccessMessage("导入成功！导入数量为:"
								+ priceBoList.size() + "行");
					} else {
						this.setFailMessage("导入失败！  错误信息：</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("这不是CSV文件");
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

	/**
	 * 跳转预算物料定价页面
	 * 
	 * @return
	 */
	public String toSearchMatterPrice() {
		return "toSearchMatterPrice";
	}

	/**
	 * 预算物料定价查询
	 * 
	 * @return
	 */
	@JsonResult(field = "priceObjList", include = { "matPriceId", "matter",
			"bezei", "price", "startDate", "endDate", "remark" }, total = "size")
	public String searchMatterPrice() {
		priceObj = new MatterPriceObject();
		priceObj.setStart(this.getStart());
		priceObj.setEnd(this.getEnd());
		if (StringUtils.isNotEmpty(matter)) {
			priceObj.setMatter(matter);
		}
		if (StringUtils.isNotEmpty(bezei)) {
			priceObj.setBezei(bezei);
		}
		if (StringUtils.isNotEmpty(remark)) {
			priceObj.setRemark(remark);
		}
		size = goalService.searchMatterPriceCount(priceObj);
		if (size > 0) {
			priceObjList = goalService.searchMatterPriceList(priceObj);
		}
		return JSON;
	}

	/**
	 * 跳转到新增或修改物料定价页面
	 * 
	 * @return
	 */
	public String toCreateOrUpdatePrice() {
		if ("update".equals(type)) {
			MatterPriceObject obj = new MatterPriceObject();
			obj.setMatPriceId(id);
			priceObj = goalService.getMatterPriceObject(obj);
		}
		return "toCreateOrUpdatePrice";
	}

	/**
	 * 新增或修改物料定价
	 * 
	 * @return
	 */
	public String createOrUpdatePrice() {
		StringResult result = new StringResult();
		result = goalService.createOrUpdateGoal(priceObj);
		if (goalService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("处理成功!");
		} else {
			this.setFailMessage("处理失败!");
		}
		return RESULT_MESSAGE;
	}

	public String toGoalSalesChange() {
		token = TokenProccessor.getInstance().makeToken();// 创建令牌
		this.getSession().setAttribute("token", token); // 在服务器使用session保存token(令牌)

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		goalSales = new GoalSales();
		goalSales.setUserName(this.getUser().getUserName());
		goalSales.setCreateDate(sdf.format(new Date()));
		orgName = this.getUser().getOrgName();
		orgId = this.getUser().getOrgId();
		return "toGoalSalesChange";
	}

	/**
	 * 分销目标量调整界面增加模块
	 * 
	 * @return
	 */
	public String toDistributionGoalChange() {
		token = TokenProccessor.getInstance().makeToken();// 创建令牌
		this.getSession().setAttribute("token", token); // 在服务器使用session保存token(令牌)

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		goalSales = new GoalSales();
		goalSales.setUserName(this.getUser().getUserName());
		goalSales.setCreateDate(sdf.format(new Date()));
		orgName = this.getUser().getOrgName();
		orgId = this.getUser().getOrgId();
		return "toDistributionGoalChange";
	}

	public String toGoalSalesChangeView() {
		token = TokenProccessor.getInstance().makeToken();// 创建令牌
		this.getSession().setAttribute("token", token); // 在服务器使用session保存token(令牌)

		goalSales = new GoalSales();
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales.setEventId(Long.parseLong(eventId));
		}
		goalSales.setChangeId(changeId);
		goalSales.setPagination("false");
		goalSalesList = goalService.searchGoalSalesChangeList(goalSales);
		if (goalSalesList != null && goalSalesList.size() > 0) {
			goalSales = goalSalesList.get(0);
		}
		return "toGoalSalesChangeView";
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	public String toGoalDistributionChangeView() {
		token = TokenProccessor.getInstance().makeToken();// 创建令牌
		this.getSession().setAttribute("token", token); // 在服务器使用session保存token(令牌)

		goalSales = new GoalSales();
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales.setEventId(Long.parseLong(eventId));
		}
		goalSales.setChangeId(changeId);
		goalSales.setPagination("false");
		goalSalesList = goalService.searchGoalFXChangeList(goalSales);
		if (goalSalesList != null && goalSalesList.size() > 0) {
			goalSales = goalSalesList.get(0);
		}
		return "toGoalDistributionChangeView";
	}

	public String toSearchGoalSalesChange() {
		return "toSearchGoalSalesChange";
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	public String toSearchDistributionGoal() {
		return "toSearchDistributionGoal";
	}

	public String salesChangeModelDownload() {
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "目标量调整模板.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			// cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("销售目标量调整模板", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 15);
			wsheet.setColumnView(2, 30);
			wsheet.setColumnView(3, 15);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 30);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 30);
			wsheet.setColumnView(10, 10);

			wsheet.mergeCells(0, 0, 3, 0);
			wsheet.mergeCells(4, 0, 7, 0);
			wsheet.mergeCells(8, 0, 8, 1);
			wsheet.mergeCells(9, 0, 9, 1);
			wsheet.mergeCells(10, 0, 10, 1);

			Label label_0 = new Label(0, 1, "组织");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 1, "经销商编号");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 1, "经销商名称");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 1, "年月（yyyyMM）");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);

			Label label_4 = new Label(4, 1, "组织");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);

			Label label_5 = new Label(5, 1, "经销商编号");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);

			Label label_6 = new Label(6, 1, "经销商名称");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);

			Label label_7 = new Label(7, 1, "年月（yyyyMM）");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);

			Label label_8 = new Label(8, 0, "品项编号");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);

			Label label_9 = new Label(9, 0, "品项名称");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);

			Label label_10 = new Label(10, 0, "标箱");
			label_10.setCellFormat(cellFormat_top);
			wsheet.addCell(label_10);

			Label label_11 = new Label(0, 0, "调出经销商");
			label_11.setCellFormat(cellFormat_top);
			wsheet.addCell(label_11);

			Label label_12 = new Label(4, 0, "调入经销商");
			label_12.setCellFormat(cellFormat_top);
			wsheet.addCell(label_12);

			wbook.write();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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

		return RESULT_MESSAGE;
	}

	@SuppressWarnings("unchecked")
	public String importGoalSales() {
		setSuccessMessage("");
		setFailMessage("");
		if (StringUtils.isEmpty(fileName)) {
			setFailMessage("导入失败");
			return RESULT_MESSAGE;
		}
		String subname = this.fileName.substring(this.fileName.length() - 3,
				this.fileName.length());
		if ("xls".equalsIgnoreCase(subname)) {
			Map<String, Object> map = goalService.importGoalSalesXls(
					fileContent, this.getUser().getUserId());
			String msg = (String) map.get("resultMessage");
			if (StringUtils.isNotEmpty(msg)) {
				setFailMessage(msg);
			} else {
				goalSalesDetailList = (List<GoalSalesDetail>) map
						.get("goalSalesDetailList");
				this.getSession().setAttribute("goalSalesDetailList",
						goalSalesDetailList);
				setSuccessMessage("明细导入成功！");
			}
		} else {
			setFailMessage("导入文件格式错误！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	public String importdistributionGoal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		goalSalesDetailList = new ArrayList<GoalSalesDetail>();
		if (StringUtils.isNotEmpty(goalSalesDetail.getKunnr())) {
			goalSalesDetail.setChangeOrgId(null);
		}
		if (StringUtils.isNotEmpty(goalSalesDetail.getKunnrTo())) {
			goalSalesDetail.setChangeOrgIdTo(null);
		}

		if (goalSalesDetail != null) {
			bct = new BCustomerTarget();
			if (StringUtils.isEmpty(goalSalesDetail.getKunnr())) {
				bct.setOrgId(goalSalesDetail.getChangeOrgId() + "");
				bct.setStartDate(sdf.format(new Date()));
				bct.setPagination("false");
			} else {
				bct.setCustId(goalSalesDetail.getKunnr());
				bct.setStartDate(sdf.format(new Date()));
				bct.setPagination("false");
			}

			size = goalService.getDistributionGoalTotal(bct);
			if (size != 0) {
				bctList = goalService.getDistributionGoalList(bct);
				if (StringUtils.isEmpty(goalSalesDetail.getKunnr())) {
					// 待开目标=组织目标（业务用语：城市目标）-该组织已有的经销商目标量
					List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
					for (int i = 0; i < bctList.size(); i++) {
						BCustomerTarget c = new BCustomerTarget(); // 组织
						c = bctList.get(i);
						if ("N".equals(c.getMark())) {
							if (StringUtils.isNotEmpty(c.getBox())
									&& Double.parseDouble(c.getBox()) > 0) {
								GoalSalesDetail gsd = new GoalSalesDetail();
								gsd.setOrgId(Long.valueOf(c.getOrgId()));
								gsd.setYear(c.getTheYear() + "");
								gsd.setMonth(c.getTheMonth());
								gsd.setMatter(c.getMatter());
								gsd.setEventState("T");
								Double evNum = goalService
										.getSalesGoalFXDetailSum(gsd);
								Double boxD = Double.valueOf(c.getBoxD());
								if (evNum != null) {
									BigDecimal b1 = new BigDecimal(boxD);
									BigDecimal b2 = new BigDecimal(evNum);
									c.setBox(b1.subtract(b2).toString());
								} else {
									c.setBox(c.getBoxD());
								}
							}
						}

						if (StringUtils.isNotEmpty(c.getBox())
								&& Double.parseDouble(c.getBox()) > 0) {
							list.add(c);
						}
					}
					bctList = list;
				} else {
					List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
					for (int i = 0; i < bctList.size(); i++) {
						if (StringUtils.isNotEmpty(bctList.get(i).getBox())
								&& Double.parseDouble(bctList.get(i).getBox()) != 0) {
							list.add(bctList.get(i));
						}
					}
					bctList = list;
				}
				if (bctList.size() > 0) {
					for (int i = 0; i < bctList.size(); i++) {
						GoalSalesDetail gd = new GoalSalesDetail();
						gd.setKunnr(goalSalesDetail.getKunnr());
						gd.setKunnrTo(goalSalesDetail.getKunnrTo());
						gd.setKunnrName(goalSalesDetail.getKunnrName());
						gd.setKunnrNameTo(goalSalesDetail.getKunnrNameTo());
						gd.setYearMonth(bctList.get(i).getTheYear()
								+ bctList.get(i).getTheMonth());
						gd.setYearMonthTo(bctList.get(i).getTheYear()
								+ bctList.get(i).getTheMonth());
						gd.setMatter(bctList.get(i).getMatter());
						gd.setMatterName(bctList.get(i).getMatter());
						gd.setCtId(bctList.get(i).getCtId());
						System.out.println(bctList.get(i).getBrand());
						gd.setMaktx01(bctList.get(i).getBrand());
						if (goalSalesDetail.getChangeOrgId() != null) {
							gd.setOrgId(goalSalesDetail.getChangeOrgId());
							gd.setOrgName(goalSalesDetail.getChangeOrgName());
							gd.setQuantity(Double.parseDouble(bctList.get(i)
									.getBox()));
						} else {
							gd.setQuantity(Double.parseDouble(bctList.get(i)
									.getBox()));
							gd.setOrgId(goalSalesDetail.getOrgId());
							gd.setOrgName(goalSalesDetail.getOrgName());
						}
						if (goalSalesDetail.getChangeOrgIdTo() != null) {
							gd.setOrgIdTo(goalSalesDetail.getChangeOrgIdTo());
							gd.setOrgNameTo(goalSalesDetail
									.getChangeOrgNameTo());
						} else {
							gd.setOrgIdTo(goalSalesDetail.getOrgIdTo());
							gd.setOrgNameTo(goalSalesDetail.getOrgNameTo());
						}
						goalSalesDetailList.add(gd);
					}
				} else {
					this.setFailMessage("无可用数据！");
				}
			}
			this.getSession().setAttribute("goalSalesDetailList",
					goalSalesDetailList);
			this.setSuccessMessage("明细导入成功！");
		}
		return RESULT_MESSAGE;
	}

	public String importGoalSalesByKunnrs() {
		setSuccessMessage("明细导入成功！");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		goalSalesDetailList = new ArrayList<GoalSalesDetail>();
		if (StringUtils.isNotEmpty(goalSalesDetail.getKunnr())) {
			goalSalesDetail.setChangeOrgId(null);
		}
		if (StringUtils.isNotEmpty(goalSalesDetail.getKunnrTo())) {
			goalSalesDetail.setChangeOrgIdTo(null);
		}

		if (goalSalesDetail != null) {
			bct = new BCustomerTarget();
			if (StringUtils.isEmpty(goalSalesDetail.getKunnr())) {
				bct.setOrgId(goalSalesDetail.getChangeOrgId() + "");
				bct.setStartDate(sdf.format(new Date()));
				bct.setMark("N");
				bct.setKunnrGoalType("B");
				bct.setPagination("false");
			} else {
				bct.setCustId(goalSalesDetail.getKunnr());
				bct.setStartDate(sdf.format(new Date()));
				bct.setMark("Y");
				bct.setKunnrGoalType("B");
				bct.setPagination("false");
			}
			size = goalService.getGoalListCount(bct);
			if (size != 0) {
				bctList = goalService.getGoalList(bct);
				if (StringUtils.isEmpty(goalSalesDetail.getKunnr())) {
					// 待开目标=组织目标（业务用语：城市目标）-该组织已有的经销商目标量
					List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
					for (int i = 0; i < bctList.size(); i++) {
						BCustomerTarget c = new BCustomerTarget(); // 组织
						c = bctList.get(i);
						if ("N".equals(c.getMark())) {
							if (StringUtils.isNotEmpty(c.getBoxD())
									&& Double.parseDouble(c.getBoxD()) > 0) {
								GoalSalesDetail gsd = new GoalSalesDetail();
								gsd.setOrgId(Long.valueOf(c.getOrgId()));
								gsd.setYear(c.getTheYear() + "");
								gsd.setMonth(c.getTheMonth());
								gsd.setMatter(c.getMatter());
								gsd.setEventState("T");
								Double evNum = goalService
										.getSalesGoalCGDetailSum(gsd);
								Double boxD = Double.valueOf(c.getBoxD());
								if (evNum != null) {
									BigDecimal b1 = new BigDecimal(boxD);
									BigDecimal b2 = new BigDecimal(evNum);
									c.setBoxD(b1.subtract(b2).toString());
								}
							}
							// BCustomerTarget c2 = new BCustomerTarget(); //
							// 组织下已有的经销商目标量
							// c2.setOrgId(c.getOrgId());
							// c2.setTheYear(c.getTheYear());
							// c2.setTheMonth(c.getTheMonth());
							// c2.setMatter(c.getMatter());
							// c2.setMark("Y");
							// c2.setKunnrGoalType("B");
							// c2 = goalService.getGoalMessege(c2); //
							// 查待开目标箱数及金额
							// if (null != c2) {
							// c.setBoxD(c2.getBox());
							// BigDecimal b = c2.getTargetNum();
							// c.setTargetNumD(b);
							// }
						}

						if (StringUtils.isNotEmpty(c.getBoxD())
								&& Double.parseDouble(c.getBoxD()) > 0) {
							list.add(c);
						}
					}
					bctList = list;
				} else {
					List<BCustomerTarget> list = new ArrayList<BCustomerTarget>();
					for (int i = 0; i < bctList.size(); i++) {
						if (StringUtils.isNotEmpty(bctList.get(i).getBox())
								&& Double.parseDouble(bctList.get(i).getBox()) != 0) {
							list.add(bctList.get(i));
						}
					}
					bctList = list;
				}
				if (bctList.size() > 0) {
					for (int i = 0; i < bctList.size(); i++) {
						GoalSalesDetail gd = new GoalSalesDetail();
						gd.setBrand(bctList.get(i).getMaktx01());
						gd.setKunnr(goalSalesDetail.getKunnr());
						gd.setKunnrTo(goalSalesDetail.getKunnrTo());
						gd.setKunnrName(goalSalesDetail.getKunnrName());
						gd.setKunnrNameTo(goalSalesDetail.getKunnrNameTo());
						gd.setYearMonth(bctList.get(i).getTheYear()
								+ bctList.get(i).getTheMonth());
						gd.setYearMonthTo(bctList.get(i).getTheYear()
								+ bctList.get(i).getTheMonth());
						gd.setMatter(bctList.get(i).getMatter());
						gd.setMatterName(bctList.get(i).getBezei());
						gd.setCtId(bctList.get(i).getCtId());
						if (goalSalesDetail.getChangeOrgId() != null) {
							gd.setOrgId(goalSalesDetail.getChangeOrgId());
							gd.setOrgName(goalSalesDetail.getChangeOrgName());
							gd.setQuantity(Double.parseDouble(bctList.get(i)
									.getBoxD()));
						} else {
							gd.setQuantity(Double.parseDouble(bctList.get(i)
									.getBox()));
							gd.setOrgId(goalSalesDetail.getOrgId());
							gd.setOrgName(goalSalesDetail.getOrgName());
						}
						if (goalSalesDetail.getChangeOrgIdTo() != null) {
							gd.setOrgIdTo(goalSalesDetail.getChangeOrgIdTo());
							gd.setOrgNameTo(goalSalesDetail
									.getChangeOrgNameTo());
						} else {
							gd.setOrgIdTo(goalSalesDetail.getOrgIdTo());
							gd.setOrgNameTo(goalSalesDetail.getOrgNameTo());
						}
						goalSalesDetailList.add(gd);
					}
				} else {
					this.setFailMessage("无可用数据！");
				}
			}
			this.getSession().setAttribute("goalSalesDetailList",
					goalSalesDetailList);
		}
		return RESULT_MESSAGE;
	}

	@SuppressWarnings("unchecked")
	@JsonResult(field = "goalSalesDetailList", include = { "detailId",
			"detailIdTo", "changeId", "orgId", "orgName", "kunnr", "kunnrName",
			"yearMonth", "orgIdTo", "orgNameTo", "kunnrTo", "kunnrNameTo",
			"yearMonthTo", "matter", "matterName", "quantity","brand","maktx01" })
	public String searchGoalSalesChangeList() {
		goalSalesDetailList = (List<GoalSalesDetail>) this.getSession()
				.getAttribute("goalSalesDetailList");
		return JSON;
	}

	@JsonResult(field = "goalSalesList", include = { "changeId", "userId",
			"userName", "orgName", "createDate", "title", "eventId",
			"eventState", "startDate", "endDate", "curUserName" }, total = "size")
	public String searchGoalSalesChangeListJson() {
		String org = orgServiceHessian.getAllChildOrgidByOrgId("51235");
		// String[] orgs=org.split(",");
		// int index=Arrays.binarySearch(orgs, this.getUser().getOrgId());
		// //使用二分法匹配，效率低
		Pattern p2 = Pattern.compile(".*\\b" + this.getUser().getOrgId()
				+ "\\b.*");
		Matcher matcher = p2.matcher(org);
		if (matcher.matches()) {
			userName = this.getUser().getUserName();
		}
		goalSales = new GoalSales();
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales.setEventId(Long.parseLong(eventId));
		}
		goalSales.setEventState(eventState);
		goalSales.setUserName(userName);
		goalSales.setStartDate(startDate);
		goalSales.setEndDate(endDate);
		goalSales.setStart(getStart());
		goalSales.setEnd(getEnd());
		size = goalService.searchGoalSalesChangeListCount(goalSales);
		if (size > 0) {
			goalSalesList = goalService.searchGoalSalesChangeList(goalSales);
		}
		return JSON;
	}

	/**
	 * 分销目标量调整查询
	 * 
	 * @return
	 */
	@JsonResult(field = "goalSalesList", include = { "changeId", "userId",
			"userName", "orgName", "createDate", "title", "eventId",
			"eventState", "startDate", "endDate", "curUserName" }, total = "size")
	public String searchGoalFXChangeListJson() {
		String org = orgServiceHessian.getAllChildOrgidByOrgId("51235");
		// String[] orgs=org.split(",");
		// int index=Arrays.binarySearch(orgs, this.getUser().getOrgId());
		// //使用二分法匹配，效率低
		Pattern p2 = Pattern.compile(".*\\b" + this.getUser().getOrgId()
				+ "\\b.*");
		Matcher matcher = p2.matcher(org);
		if (matcher.matches()) {
			userName = this.getUser().getUserName();
		}
		goalSales = new GoalSales();
		if (StringUtils.isNotEmpty(eventTitle)) {
			goalSales.setTitle(eventTitle);
		}
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales.setEventId(Long.parseLong(eventId));
		}
		goalSales.setEventState(eventState);
		goalSales.setUserName(userName);
		goalSales.setStartDate(startDate);
		goalSales.setEndDate(endDate);
		goalSales.setStart(getStart());
		goalSales.setEnd(getEnd());
		size = goalService.searchGoalFXChangeListCount(goalSales);
		if (size > 0) {
			goalSalesList = goalService.searchGoalFXChangeList(goalSales);
		}
		return JSON;
	}

	/**
	 * 分销目标量调整导出
	 */
	public void exportForExcelGoalFXChange() {
		try {
			String org = orgServiceHessian.getAllChildOrgidByOrgId("51235");
			Pattern p2 = Pattern.compile(".*\\b" + this.getUser().getOrgId()
					+ "\\b.*");
			Matcher matcher = p2.matcher(org);
			if (matcher.matches()) {
				userName = this.getUser().getUserName();
			}
			goalSalesDetail = new GoalSalesDetail();
			if (StringUtils.isNotEmpty(eventId)) {
				goalSalesDetail.setEventId(Long.parseLong(eventId));
			}
			goalSalesDetail.setEventState(eventState);
			goalSalesDetail.setUserName(userName);
			goalSalesDetail.setStartDate(startDate);
			goalSalesDetail.setEndDate(endDate);
			goalSalesDetailList = goalService
					.searchGoalFXChangeDetailList(goalSalesDetail);

			String[] name = { "事务编号", "标题", "申请年月", "申请人", "事务状态", "调出组织",
					"调出经销商编号", "调出经销商名称", "调出年月", "调入组织", "调入经销商编号", "调入经销商名称",
					"调入年月", "品项", "标箱" };
			String[] field = { "eventId", "title", "createDate", "userName",
					"eventState", "orgName", "kunnr", "kunnrName", "yearMonth",
					"orgNameTo", "kunnrTo", "kunnrNameTo", "yearMonthTo",
					"matter", "quantity" };
			ExportExcelUtil ee = new ExportExcelUtil("分销目标量调整明细", name, field,
					goalSalesDetailList);
			ee.export();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportForExcelGoalSalesChange() {
		try {
			String org = orgServiceHessian.getAllChildOrgidByOrgId("51235");
			Pattern p2 = Pattern.compile(".*\\b" + this.getUser().getOrgId()
					+ "\\b.*");
			Matcher matcher = p2.matcher(org);
			if (matcher.matches()) {
				userName = this.getUser().getUserName();
			}
			goalSalesDetail = new GoalSalesDetail();
			if (StringUtils.isNotEmpty(eventId)) {
				goalSalesDetail.setEventId(Long.parseLong(eventId));
			}
			goalSalesDetail.setEventState(eventState);
			goalSalesDetail.setUserName(userName);
			goalSalesDetail.setStartDate(startDate);
			goalSalesDetail.setEndDate(endDate);
			goalSalesDetailList = goalService
					.searchGoalSalesChangeDetailList(goalSalesDetail);

			String[] name = { "事务编号", "标题", "申请年月", "申请人", "事务状态", "调出组织",
					"调出经销商编号", "调出经销商名称", "调出年月", "调入组织", "调入经销商编号", "调入经销商名称",
					"调入年月", "品项", "标箱" };
			String[] field = { "eventId", "title", "createDate", "userName",
					"eventState", "orgName", "kunnr", "kunnrName", "yearMonth",
					"orgNameTo", "kunnrTo", "kunnrNameTo", "yearMonthTo",
					"matter", "quantity" };
			ExportExcelUtil ee = new ExportExcelUtil("销售目标量调整明细", name, field,
					goalSalesDetailList);
			ee.export();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@JsonResult(field = "goalSalesDetailList", include = { "detailId",
			"detailIdTo", "changeId", "orgId", "orgName", "kunnr", "kunnrName",
			"yearMonth", "orgIdTo", "orgNameTo", "kunnrTo", "kunnrNameTo",
			"yearMonthTo", "matter", "matterName", "quantity","brand" })
	public String searchGoalSalesChangeDetailList() {
		goalSalesDetail = new GoalSalesDetail();
		goalSalesDetail.setChangeId(changeId);
		goalSalesDetailList = goalService
				.searchGoalSalesChangeDetailList(goalSalesDetail);
		return JSON;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	@JsonResult(field = "goalSalesDetailList", include = { "detailId",
			"detailIdTo", "changeId", "orgId", "orgName", "kunnr", "kunnrName",
			"yearMonth", "orgIdTo", "orgNameTo", "kunnrTo", "kunnrNameTo",
			"yearMonthTo", "matter", "matterName", "quantity" ,"maktx01"})
	public String searchGoalFXChangeDetailList() {
		goalSalesDetail = new GoalSalesDetail();
		goalSalesDetail.setChangeId(changeId);
		goalSalesDetailList = goalService
				.searchGoalFXChangeDetailList(goalSalesDetail);
		return JSON;
	}

	/**
	 * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
	 * 
	 * @param request
	 * @return false 用户重复提交了表单 true 用户没有重复提交表单
	 */
	private boolean isRepeatSubmit() {
		String client_token = token;
		// 1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
		if (client_token == null) {
			return false;
		}
		// 取出存储在Session中的token
		String server_token = (String) this.getSession().getAttribute("token");
		// 2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
		if (server_token == null) {
			return false;
		}
		// 3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
		if (!client_token.equals(server_token)) {
			return false;
		}

		return true;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result",
			"reason" })
	public String selectNextUserGoalDistribution() {
		String posId = "cityManager";
		List<AllUsers> users = goalService.getStationIdByUserId(this.getUser()
				.getUserId());
		for (AllUsers user : users) {
			if (user.getPosId().equals("provincesManager")) {
				posId = "provincesManager";
				break;
			}
		}
		String processId = "fix_changeFXGoal";
		Object[] res = new Object[4];
		res[0] = processId;
		res[1] = getUser().getUserId();
		res[2] = "executeAction,refuseAction,role";
		res[3] = (appUrl + "/goal/goalAction!createGoalFXDone.jspa" + ","
				+ appUrl + "/goal/goalAction!createGoalFXRefuse.jspa" + "," + posId);
		userUtil = wfeServiceHessian.startWorkflowFix(res);
		return JSON;
	}

	@SuppressWarnings("unchecked")
	public String createGoalSales() {
		if (isRepeatSubmit()) {
			this.getSession().removeAttribute("token");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			int yearMonthBef = Integer.parseInt(sdf.format(new Date()));
			CmsTbDictType cm = new CmsTbDictType();
			cm.setDictTypeName("销售目标量调整提报年月");
			cm.setPagination("false");
			dictlist = dictServiceHessian.getDictListByDictType(cm);
			if (dictlist != null && dictlist.size() > 0) {
				int num = Integer.parseInt(dictlist.get(0).getItemValue());
				if (num > 0) {
					yearMonthBef = num;
				}
			}

			StringBuffer resultMsg = new StringBuffer();
			Map<String, GoalSalesDetail> map = new HashMap<String, GoalSalesDetail>();
			if (goalSalesDetailList != null && goalSalesDetailList.size() > 0) {
				for (GoalSalesDetail gd : goalSalesDetailList) {
					if (Integer.parseInt(gd.getYearMonth()) < yearMonthBef
							|| Integer.parseInt(gd.getYearMonthTo()) < yearMonthBef) {
						resultMsg.append("无法调整" + yearMonthBef + "之前目标量！</br>");
						break;
					} else if (Integer.parseInt(gd.getYearMonth()) != Integer
							.parseInt(gd.getYearMonthTo())) {
						resultMsg.append("无法跨月调整目标量！</br>");
						break;
					}

					if (StringUtils.isNotEmpty(gd.getKunnr())
							&& StringUtils.isNotEmpty(gd.getKunnrTo())
							&& gd.getKunnr().equals(gd.getKunnrTo())) {
						resultMsg.append("无法调整同一经销商目标量！</br>");
						break;
					} else if (StringUtils.isEmpty(gd.getKunnr())
							&& StringUtils.isEmpty(gd.getKunnrTo())) {
						if (gd.getOrgId().equals(gd.getOrgIdTo())) {
							resultMsg.append("无法调整同一组织目标量！</br>");
							break;
						}
					}

					if (gd.getKunnr() == null) {
						GoalSalesDetail gdMap = gd.clone();
						if (map.get(gd.getOrgId() + "," + gd.getYearMonth()
								+ "," + gd.getMatter()) == null) {
							map.put(gd.getOrgId() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), gdMap);
						} else {
							GoalSalesDetail old = map.get(gd.getOrgId() + ","
									+ gd.getYearMonth() + "," + gd.getMatter());
							BigDecimal b1 = new BigDecimal(old.getQuantity());
							BigDecimal b2 = new BigDecimal(gd.getQuantity());
							old.setQuantity(b1.add(b2).doubleValue());
							map.put(gd.getOrgId() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), old);
						}
					} else {
						GoalSalesDetail gdMap = gd.clone();
						if (map.get(gd.getKunnr() + "," + gd.getYearMonth()
								+ "," + gd.getMatter()) == null) {
							map.put(gd.getKunnr() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), gdMap);
						} else {
							GoalSalesDetail old = map.get(gd.getKunnr() + ","
									+ gd.getYearMonth() + "," + gd.getMatter());
							BigDecimal b1 = new BigDecimal(old.getQuantity());
							BigDecimal b2 = new BigDecimal(gd.getQuantity());
							old.setQuantity(b1.add(b2).doubleValue());
							map.put(gd.getKunnr() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), old);
						}
					}
				}

				for (String key : map.keySet()) {
					GoalSalesDetail gd = map.get(key);
					String year = gd.getYearMonth().substring(0, 4);
					String month = gd.getYearMonth().substring(4, 6);

					bct = new BCustomerTarget();
					if (StringUtils.isNotEmpty(gd.getKunnr())) {
						bct.setCustId(gd.getKunnr());
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(year));
						bct.setTheMonth(month);
						bct.setMark("Y");
						bct.setKunnrGoalType("B");
						bct.setPagination("false");
					} else {
						bct.setOrgId(gd.getOrgId() + "");
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(year));
						bct.setTheMonth(month);
						bct.setMark("N");
						bct.setKunnrGoalType("B");
						bct.setPagination("false");
					}
					int size = goalService.getGoalListCount(bct);
					if (size > 0) {
						bctList = goalService.getGoalList(bct);
						String ctId = bctList.get(0).getCtId();
						String boxNum = bctList.get(0).getBox();
						String boxNumD = bctList.get(0).getBoxD();
						if (StringUtils.isEmpty(gd.getKunnr())) {
							// // 待开目标=组织目标（业务用语：城市目标）-该组织已有的经销商目标量
							// BCustomerTarget c = new BCustomerTarget(); // 组织
							// c = bctList.get(0);
							// BCustomerTarget c2 = new BCustomerTarget(); //
							// 组织下已有的经销商目标量
							// c2.setOrgId(c.getOrgId());
							// c2.setTheYear(c.getTheYear());
							// c2.setTheMonth(c.getTheMonth());
							// c2.setMatter(c.getMatter());
							// c2.setMark("Y");
							// c2.setKunnrGoalType("B");
							// c2 = goalService.getGoalMessege(c2); //
							// 查待开目标箱数及金额
							GoalSalesDetail gsd = new GoalSalesDetail();
							gsd.setOrgId(gd.getOrgId());
							gsd.setYear(year);
							gsd.setMonth(month);
							gsd.setMatter(gd.getMatter());
							gsd.setEventState("T");
							Double evNum = goalService
									.getSalesGoalCGDetailSum(gsd);
							if (null != boxNumD) {
								if (evNum != null) {
									BigDecimal b1 = new BigDecimal(boxNumD);
									BigDecimal b2 = new BigDecimal(evNum);
									boxNumD = b1.subtract(b2).toString();
								}
								if (Double.parseDouble(boxNumD) < gd
										.getQuantity()) {
									resultMsg.append(gd.getYearMonth() + "-"
											+ gd.getOrgName() + "-"
											+ gd.getMatter() + " 目标量不足！</br>");
									continue;
								} else {
									gd.setCtId(ctId);
									gd.setBoxNum(Double.parseDouble(boxNum));
								}
							}
						} else {
							if (Double.parseDouble(boxNum) < gd.getQuantity()) {
								resultMsg.append(gd.getYearMonth() + "-"
										+ gd.getKunnr() + "-" + gd.getMatter()
										+ " 目标量不足！</br>");
								continue;
							} else {
								gd.setCtId(ctId);
								gd.setBoxNum(Double.parseDouble(boxNum));
							}
						}
					} else {
						resultMsg.append(gd.getYearMonth() + "-"
								+ gd.getKunnr() + "-" + gd.getMatter()
								+ " 目标量不足！</br>");
						continue;
					}
				}
			}

			if (resultMsg.length() == 0) {
				String processId = "fix_changeGoal";
				this.setSuccessMessage("");
				this.setFailMessage("");
				Object[] res = new Object[7];
				res[0] = eventId;
				res[1] = this.getUser().getUserId();
				res[2] = nextUserId;
				res[3] = goalSales.getTitle();
				res[4] = appUrl + "/goal/goalAction!toGoalSalesChangeView.jspa";
				res[5] = processId;
				res[6] = "";
				String result = wfeServiceHessian.processWorkflowFix(res);
				if ("success".equals(result)) {
					this.getSession().removeAttribute("token");

					Map<String, Double> boxMap = new HashMap<String, Double>();
					Map<String, BigDecimal> targetPriceMap = new HashMap<String, BigDecimal>();
					Map<String, BigDecimal> priceMap = new HashMap<String, BigDecimal>();
					Map<String, BCustomerTarget> orgMap = new HashMap<String, BCustomerTarget>();

					if (goalSales != null) {
						if (goalSales.getChangeId() == null) {// 直接提交调整目标量
							goalSales.setEventId(Long.parseLong(eventId));
							goalSales.setEventState("T");
							goalSales.setUserId(this.getUser().getUserId());
							Long changeId = goalService
									.createGoalSalesChange(goalSales);

							for (GoalSalesDetail gd : goalSalesDetailList) {
								// 获取物料金额
								BigDecimal targetPrice;
								if (targetPriceMap.get(gd.getMatter()) != null) {
									targetPrice = targetPriceMap.get(gd
											.getMatter());
								} else {
									Materiel mat = new Materiel();
									mat.setMvgr1(gd.getMatter());
									mat.setPagination("false");
									List<Materiel> mList = goalService
											.getMaterielViewList(mat);
									targetPrice = new BigDecimal(mList.get(0)
											.getKbetr());
									targetPriceMap.put(gd.getMatter(),
											targetPrice);
								}

								String year = gd.getYearMonth().substring(0, 4);
								String month = gd.getYearMonth()
										.substring(4, 6);
								String yearTo = gd.getYearMonthTo().substring(
										0, 4);
								String monthTo = gd.getYearMonthTo().substring(
										4, 6);

								// 创建调入明细
								GoalSalesDetail gsdTo = new GoalSalesDetail();
								gsdTo.setChangeId(changeId);
								gsdTo.setYear(yearTo);
								gsdTo.setMonth(monthTo);
								gsdTo.setKunnr(gd.getKunnrTo());
								gsdTo.setOrgId(gd.getOrgIdTo());
								gsdTo.setMatter(gd.getMatter());
								gsdTo.setQuantity(gd.getQuantity());
								Long detailIdTo = goalService
										.createGoalSalesChangeDetail(gsdTo);

								// 创建调出明细
								GoalSalesDetail gsd = new GoalSalesDetail();
								gsd.setChangeId(changeId);
								gsd.setOrgId(gd.getOrgId());
								gsd.setKunnr(gd.getKunnr());
								gsd.setYear(year);
								gsd.setMonth(month);
								gsd.setMatter(gd.getMatter());
								gsd.setQuantity(-gd.getQuantity());
								gsd.setDetailIdTo(detailIdTo);
								goalService.createGoalSalesChangeDetail(gsd);
							}
						} else {// 之前有保存草稿，提交目标量
							goalSales.setEventId(Long.parseLong(eventId));
							goalSales.setEventState("T");
							goalService.updateGoalSalesChange(goalSales);

							for (GoalSalesDetail gd : goalSalesDetailList) {
								BigDecimal targetPrice;
								if (targetPriceMap.get(gd.getMatter()) != null) {
									targetPrice = targetPriceMap.get(gd
											.getMatter());
								} else {
									Materiel mat = new Materiel();
									mat.setMvgr1(gd.getMatter());
									mat.setPagination("false");
									List<Materiel> mList = goalService
											.getMaterielViewList(mat);
									targetPrice = new BigDecimal(mList.get(0)
											.getKbetr());
									targetPriceMap.put(gd.getMatter(),
											targetPrice);
								}
								if (gd.getOrgId().equals(gd.getOrgIdTo())
										&& StringUtils.isEmpty(gd.getKunnr())
										&& StringUtils.isNotEmpty(gd
												.getKunnrTo())) {
									continue;
								} else {
									Double num = boxMap.get(gd.getCtId());
									if (num != null) {
										BigDecimal b1 = new BigDecimal(num);
										BigDecimal b2 = new BigDecimal(
												gd.getQuantity());
										boxMap.put(gd.getCtId(), b1
												.subtract(b2).doubleValue());
									} else {
										BigDecimal b1 = new BigDecimal(
												gd.getBoxNum());
										BigDecimal b2 = new BigDecimal(
												gd.getQuantity());
										boxMap.put(gd.getCtId(), b1
												.subtract(b2).doubleValue());
									}
									priceMap.put(gd.getCtId(), targetPrice);
								}
							}
							// GoalSalesDetail goalSalesDetail = new
							// GoalSalesDetail();
							// goalSalesDetail.setChangeId(goalSales.getChangeId());
							// List<GoalSalesDetail>
							// list=goalService.searchGoalSalesChangeDetailList(goalSalesDetail);
							//
							// int count = 0;
							// long[] ids=new
							// long[list.size()-goalSalesDetailList.size()];
							// for(int i=0;i<list.size();i++){
							// boolean flag=true;
							// long tmp=list.get(i).getChangeId();
							// for(GoalSalesDetail gd:goalSalesDetailList){
							// if(tmp==gd.getChangeId()){
							// flag=false;
							// break;
							// }
							// }
							// if(flag){
							// ids[count++]=tmp;
							// }
							// }

						}

						// 组织调至同组织经销商不扣除组织目标量，其他情况要扣除组织目标量
						for (String key : map.keySet()) {
							GoalSalesDetail gd = map.get(key);
							// 获取物料金额
							BigDecimal targetPrice;
							if (targetPriceMap.get(gd.getMatter()) != null) {
								targetPrice = targetPriceMap
										.get(gd.getMatter());
							} else {
								Materiel mat = new Materiel();
								mat.setMvgr1(gd.getMatter());
								mat.setPagination("false");
								List<Materiel> mList = goalService
										.getMaterielViewList(mat);
								targetPrice = new BigDecimal(mList.get(0)
										.getKbetr());
								targetPriceMap.put(gd.getMatter(), targetPrice);
							}

							if (gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isEmpty(gd.getKunnr())
									&& StringUtils.isNotEmpty(gd.getKunnrTo())) {
								continue;
							} else {
								Double num = boxMap.get(gd.getCtId());
								if (num != null) {
									BigDecimal b1 = new BigDecimal(num);
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									boxMap.put(gd.getCtId(), b1.subtract(b2)
											.doubleValue());
								} else {
									BigDecimal b1 = new BigDecimal(
											gd.getBoxNum());
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									boxMap.put(gd.getCtId(), b1.subtract(b2)
											.doubleValue());
								}
								priceMap.put(gd.getCtId(), targetPrice);
							}

							String year = gd.getYearMonth().substring(0, 4);
							String month = gd.getYearMonth().substring(4, 6);
							// 跨组织调整，扣除经销商目标量的同时，要扣除该组织组织目标量
							// 合并扣除的组织目标量
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())) {
								BCustomerTarget orgTarget = orgMap.get(gd
										.getOrgId()
										+ year
										+ month
										+ gd.getMatter());
								if (orgTarget == null) {
									BCustomerTarget bct = new BCustomerTarget();
									bct.setOrgId(gd.getOrgId() + "");
									bct.setMatter(gd.getMatter());
									bct.setTheYear(Long.parseLong(year));
									bct.setTheMonth(month);
									bct.setMark("N");
									bct.setKunnrGoalType("B");
									bct.setPagination("false");
									bct = goalService.getGoalList(bct).get(0);
									String boxNum = bct.getBox();
									BigDecimal b1 = new BigDecimal(boxNum);
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									bct.setBox(b1.subtract(b2).toString());
									orgMap.put(gd.getOrgId() + year + month
											+ gd.getMatter(), bct);
								} else {
									BigDecimal b1 = new BigDecimal(
											orgTarget.getBox());
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									orgTarget
											.setBox(b1.subtract(b2).toString());
									orgMap.put(gd.getOrgId() + year + month
											+ gd.getMatter(), orgTarget);
								}
							}
						}

						for (String key : orgMap.keySet()) {
							BCustomerTarget orgTarget = orgMap.get(key);
							boxMap.put(orgTarget.getCtId(),
									Double.valueOf(orgTarget.getBox()));
							BigDecimal targetPrice = targetPriceMap
									.get(orgTarget.getMatter());
							priceMap.put(orgTarget.getCtId(), targetPrice);
						}

						for (String key : boxMap.keySet()) {
							double box = boxMap.get(key);
							BigDecimal tartgetBox = new BigDecimal(box);
							BCustomerTarget bct = new BCustomerTarget();
							bct.setCtId(key);
							bct.setBox(boxMap.get(key) + "");
							bct.setTargetNum(priceMap.get(key).multiply(
									tartgetBox));
							goalService.updateGoal(bct);
						}
					}
					this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
				} else {
					this.setFailMessage("启动失败,请重试！");
				}
				this.getSession().removeAttribute("goalSalesDetailList");
			} else {
				this.setFailMessage(resultMsg.toString());
			}
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result",
			"reason" })
	public String selectNextUserGoalSales() {
		String posId = "cityManager";
		List<AllUsers> users = goalService.getStationIdByUserId(this.getUser()
				.getUserId());
		for (AllUsers user : users) {
			if (user.getPosId().equals("provincesManager")) {
				posId = "provincesManager";
				break;
			}
		}
		String processId = "fix_changeGoal";
		Object[] res = new Object[4];
		res[0] = processId;
		res[1] = getUser().getUserId();
		res[2] = "executeAction,refuseAction,role";
		res[3] = (appUrl + "/goal/goalAction!createGoalSalesDone.jspa" + ","
				+ appUrl + "/goal/goalAction!createGoalSalesRefuse.jspa" + "," + posId);
		userUtil = wfeServiceHessian.startWorkflowFix(res);
		return JSON;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createGoalDistribution() {
		if (isRepeatSubmit()) {
			this.getSession().removeAttribute("token");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			int yearMonthBef = Integer.parseInt(sdf.format(new Date()));
			CmsTbDictType cm = new CmsTbDictType();
			cm.setDictTypeName("销售目标量调整提报年月");
			cm.setPagination("false");
			dictlist = dictServiceHessian.getDictListByDictType(cm);
			if (dictlist != null && dictlist.size() > 0) {
				int num = Integer.parseInt(dictlist.get(0).getItemValue());
				if (num > 0) {
					yearMonthBef = num;
				}
			}
			StringBuffer resultMsg = new StringBuffer();
			Map<String, GoalSalesDetail> map = new HashMap<String, GoalSalesDetail>();
			if (goalSalesDetailList != null && goalSalesDetailList.size() > 0) {
				for (GoalSalesDetail gd : goalSalesDetailList) {
					if (Integer.parseInt(gd.getYearMonth()) < yearMonthBef
							|| Integer.parseInt(gd.getYearMonthTo()) < yearMonthBef) {
						resultMsg.append("无法调整" + yearMonthBef + "之前目标量！</br>");
						break;
					} else if (Integer.parseInt(gd.getYearMonth()) != Integer
							.parseInt(gd.getYearMonthTo())) {
						resultMsg.append("无法跨月调整目标量！</br>");
						break;
					}

					if (StringUtils.isNotEmpty(gd.getKunnr())
							&& StringUtils.isNotEmpty(gd.getKunnrTo())
							&& gd.getKunnr().equals(gd.getKunnrTo())) {
						resultMsg.append("无法调整同一经销商目标量！</br>");
						break;
					} else if (StringUtils.isEmpty(gd.getKunnr())
							&& StringUtils.isEmpty(gd.getKunnrTo())) {
						if (gd.getOrgId().equals(gd.getOrgIdTo())) {
							resultMsg.append("无法调整同一组织目标量！</br>");
							break;
						}
					}

					if (gd.getKunnr() == null) {
						GoalSalesDetail gdMap = gd.clone();
						if (map.get(gd.getOrgId() + "," + gd.getYearMonth()
								+ "," + gd.getMatter()) == null) {
							map.put(gd.getOrgId() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), gdMap);
						} else {
							GoalSalesDetail old = map.get(gd.getOrgId() + ","
									+ gd.getYearMonth() + "," + gd.getMatter());
							BigDecimal b1 = new BigDecimal(old.getQuantity());
							BigDecimal b2 = new BigDecimal(gd.getQuantity());
							old.setQuantity(b1.add(b2).doubleValue());
							map.put(gd.getOrgId() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), old);
						}
					} else {
						GoalSalesDetail gdMap = gd.clone();
						if (map.get(gd.getKunnr() + "," + gd.getYearMonth()
								+ "," + gd.getMatter()) == null) {
							map.put(gd.getKunnr() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), gdMap);
						} else {
							GoalSalesDetail old = map.get(gd.getKunnr() + ","
									+ gd.getYearMonth() + "," + gd.getMatter());
							BigDecimal b1 = new BigDecimal(old.getQuantity());
							BigDecimal b2 = new BigDecimal(gd.getQuantity());
							old.setQuantity(b1.add(b2).doubleValue());
							map.put(gd.getKunnr() + "," + gd.getYearMonth()
									+ "," + gd.getMatter(), old);
						}
					}
				}

				for (String key : map.keySet()) {
					GoalSalesDetail gd = map.get(key);
					String year = gd.getYearMonth().substring(0, 4);
					String month = gd.getYearMonth().substring(4, 6);

					bct = new BCustomerTarget();
					if (StringUtils.isNotEmpty(gd.getKunnr())) {
						bct.setCustId(gd.getKunnr());
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(year));
						bct.setTheMonth(month);
						bct.setPagination("false");
					} else {
						bct.setOrgId(gd.getOrgId() + "");
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(year));
						bct.setTheMonth(month);
						bct.setPagination("false");
					}
					int size = goalService.getDistributionGoalTotal(bct);
					if (size > 0) {
						bctList = goalService.getDistributionGoalList(bct);
						String boxNum = bctList.get(0).getBox();
						String boxNumD = bctList.get(0).getBoxD();
						if (StringUtils.isEmpty(gd.getKunnr())) {
							// // 待开目标=组织目标（业务用语：城市目标）-该组织已有的经销商目标量
							// BCustomerTarget c = new BCustomerTarget(); // 组织
							// c = bctList.get(0);
							// BCustomerTarget c2 = new BCustomerTarget(); //
							// 组织下已有的经销商目标量
							// c2.setOrgId(c.getOrgId());
							// c2.setTheYear(c.getTheYear());
							// c2.setTheMonth(c.getTheMonth());
							// c2.setMatter(c.getMatter());
							// c2.setMark("Y");
							// c2.setKunnrGoalType("B");
							// c2 = goalService.getGoalMessege(c2); //
							// 查待开目标箱数及金额
							GoalSalesDetail gsd = new GoalSalesDetail();
							gsd.setOrgId(gd.getOrgId());
							gsd.setYear(year);
							gsd.setMonth(month);
							gsd.setMatter(gd.getMatter());
							gsd.setEventState("T");
							Double evNum = goalService
									.getSalesGoalFXDetailSum(gsd);
							if (null != boxNumD) {

								if (evNum != null) {
									BigDecimal b1 = new BigDecimal(boxNumD);
									BigDecimal b2 = new BigDecimal(evNum);
									boxNumD = b1.subtract(b2).toString();
								}
								System.out.println(boxNum + "-----"
										+ gd.getQuantity());
								if (Double.parseDouble(boxNumD) < gd
										.getQuantity()) {
									resultMsg.append(gd.getYearMonth() + "-"
											+ gd.getOrgName() + "-"
											+ gd.getMatter() + " 目标量不足！.</br>");
									continue;
								} else {
									gd.setBoxNum(Double.parseDouble(boxNum));
								}
							}
						} else {
							System.out.println(boxNum + "-----"
									+ gd.getQuantity());
							if (Double.parseDouble(boxNum) < gd.getQuantity()) {
								resultMsg.append(gd.getYearMonth() + "-"
										+ gd.getKunnr() + "-" + gd.getMatter()
										+ " 目标量不足！!</br>");
								continue;
							} else {
								gd.setBoxNum(Double.parseDouble(boxNum));
							}
						}
					} else {
						resultMsg.append(gd.getYearMonth() + "-"
								+ gd.getKunnr() + "-" + gd.getMatter()
								+ " 目标量不足！?</br>");
						continue;
					}
				}
			}

			if (resultMsg.length() == 0) {
				String processId = "fix_changeFXGoal";
				this.setSuccessMessage("");
				this.setFailMessage("");
				Object[] res = new Object[7];
				res[0] = eventId;
				res[1] = this.getUser().getUserId();
				res[2] = nextUserId;
				res[3] = goalSales.getTitle();
				res[4] = appUrl
						+ "/goal/goalAction!toGoalDistributionChangeView.jspa"; // 改
				res[5] = processId;
				res[6] = "";
				String result = wfeServiceHessian.processWorkflowFix(res);
				if ("success".equals(result)) {
					this.getSession().removeAttribute("token");

					Map<GoalSalesDetail, Double> boxMap = new HashMap<GoalSalesDetail, Double>();
					Map<GoalSalesDetail, BCustomerTarget> orgMap = new HashMap<GoalSalesDetail, BCustomerTarget>();

					if (goalSales != null) {
						if (goalSales.getChangeId() == null) {// 直接提交调整目标量
							goalSales.setEventId(Long.parseLong(eventId));
							goalSales.setEventState("T");
							goalSales.setUserId(this.getUser().getUserId());
							Long changeId = goalService
									.createGoalDistributionChange(goalSales);

							for (GoalSalesDetail gd : goalSalesDetailList) {

								String year = gd.getYearMonth().substring(0, 4);
								String month = gd.getYearMonth()
										.substring(4, 6);
								String yearTo = gd.getYearMonthTo().substring(
										0, 4);
								String monthTo = gd.getYearMonthTo().substring(
										4, 6);

								// 创建调入明细
								GoalSalesDetail gsdTo = new GoalSalesDetail();
								gsdTo.setChangeId(changeId);
								gsdTo.setYear(yearTo);
								gsdTo.setMonth(monthTo);
								gsdTo.setKunnr(gd.getKunnrTo());
								gsdTo.setOrgId(gd.getOrgIdTo());
								gsdTo.setMatter(gd.getMatter());
								gsdTo.setQuantity(gd.getQuantity());
								System.out.println(gd.getQuantity());
								Long detailIdTo = goalService
										.createGoalFXChangeDetail(gsdTo);

								// 创建调出明细
								GoalSalesDetail gsd = new GoalSalesDetail();
								gsd.setChangeId(changeId);
								gsd.setOrgId(gd.getOrgId());
								gsd.setKunnr(gd.getKunnr());
								gsd.setYear(year);
								gsd.setMonth(month);
								gsd.setMatter(gd.getMatter());
								gsd.setQuantity(-gd.getQuantity());
								gsd.setDetailIdTo(detailIdTo);
								goalService.createGoalFXChangeDetail(gsd);
							}
						}

						// 组织调至同组织经销商不扣除组织目标量，其他情况要扣除组织目标量
						for (String key : map.keySet()) {
							GoalSalesDetail gd = map.get(key);
							String year = gd.getYearMonth().substring(0, 4);
							String month = gd.getYearMonth().substring(4, 6);
							if (gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isEmpty(gd.getKunnr())
									&& StringUtils.isNotEmpty(gd.getKunnrTo())) {
								gd.setYear(year);
								gd.setMonth(month);
								continue;
							} else {
								Double num = boxMap.get(gd);
								if (num != null) {
									BigDecimal b1 = new BigDecimal(num);
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									gd.setYear(year);
									gd.setMonth(month);
									boxMap.put(gd, b1.add(b2).doubleValue());
								} else {
									// BigDecimal b1= new
									// BigDecimal(gd.getBoxNum());
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									gd.setYear(year);
									gd.setMonth(month);
									boxMap.put(gd, /* b1.subtract. */
											-(b2).doubleValue());
								}
							}

							// 跨组织调整，扣除经销商目标量的同时，要扣除该组织组织目标量
							// 合并扣除的组织目标量
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())) {
								GoalSalesDetail gsd = new GoalSalesDetail();
								gsd.setKunnr(null);
								gsd.setBoxNum(gd.getBoxNum());
								gsd.setMatter(gd.getMatter());
								gsd.setYear(gd.getYear());
								gsd.setMonth(gd.getMonth());
								gsd.setOrgId(gd.getOrgId());
								BCustomerTarget orgTarget = orgMap.get(gsd);
								if (orgTarget == null) {
									BCustomerTarget bct = new BCustomerTarget();
									bct.setOrgId(gd.getOrgId() + "");
									bct.setMatter(gd.getMatter());
									bct.setTheYear(Long.parseLong(year));
									bct.setTheMonth(month);
									bct.setPagination("false");

									bct = goalService.getDistributionGoalList(
											bct).get(0);
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									// bct.setBox(b1.subtract(b2).toString());
									bct.setBox(new BigDecimal(0).subtract(b2)
											.toString());
									orgMap.put(gsd, bct);
								} else {
									BigDecimal b1 = new BigDecimal(
											orgTarget.getBox());
									BigDecimal b2 = new BigDecimal(
											gd.getQuantity());
									orgTarget
											.setBox(b1.subtract(b2).toString());
									orgMap.put(gsd, orgTarget);
								}
							}
						}
						for (GoalSalesDetail key : orgMap.keySet()) {
							BCustomerTarget orgTarget = orgMap.get(key);
							boxMap.put(key, Double.valueOf(orgTarget.getBox()));
						}

						for (GoalSalesDetail key : boxMap.keySet()) {
							double box = boxMap.get(key);
							GoalSalesDetail bct = key;
							BCustomerTarget bt = new BCustomerTarget();
							bt.setCustId(bct.getKunnr());
							bt.setOrgId(bct.getOrgId() + "");
							// bt.setOrgName(bct.getOrgNameTo());
							bt.setMatter(bct.getMatter());
							bt.setBox(box + "");
							bt.setTheYear(Long.parseLong(bct.getYear()));
							bt.setTheMonth(bct.getMonth());
							if (StringUtils.isNotEmpty(bct.getKunnr())) {
								bt.setMark("Y");
							} else {
								bt.setMark("N");
							}
							bt.setOpId(this.getUser().getUserId());
							System.out.println(bt);
							boolean flag = goalService.insertFXGoal(bt);
							if (flag) {
								this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
							} else {
								this.setFailMessage("启动失败,请重试！");
								this.getSession().removeAttribute(
										"goalSalesDetailList");
								this.setFailMessage(resultMsg.toString());
								return RESULT_MESSAGE;

							}
						}
					}
					this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
				} else {
					this.setFailMessage("启动失败,请重试！");
				}
				this.getSession().removeAttribute("goalSalesDetailList");
			} else {
				this.setFailMessage(resultMsg.toString());
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createGoalFXDone() {
		BooleanResult result;
		result = new BooleanResult();
		String resultMsg = "";
		int failnum = 0;
		try {
			if (StringUtils.isNotEmpty(eventId)) {
				goalSales = new GoalSales();
				goalSales.setEventId(Long.parseLong(eventId));
				goalSales.setPagination("false");
				List<GoalSales> goalSalesChangeList = goalService
						.searchGoalFXChangeList(goalSales);
				Long changeId = goalSalesChangeList.get(0).getChangeId();

				goalSales.setEventState("D");
				goalSales.setChangeId(changeId);
				int count = goalService.updateGoalFXChange(goalSales);
				if (count > 0) {
					result.setResult(true);
					result.setCode("流程审批完成！</br>");

					goalSalesDetail = new GoalSalesDetail();
					goalSalesDetail.setChangeId(changeId);
					goalSalesDetailList = goalService
							.searchGoalFXChangeDetailList(goalSalesDetail);
					for (GoalSalesDetail gd : goalSalesDetailList) {
						// 获取物料金额
						if (gd.getKunnrTo() != null) {// 调整至经销商，经销商加上
							BCustomerTarget bct = new BCustomerTarget();
							bct.setCustId(gd.getKunnrTo());
							bct.setOrgId(gd.getOrgIdTo() + "");
							bct.setMatter(gd.getMatter());
							bct.setTheYear(Long.parseLong(gd.getYearMonthTo()
									.substring(0, 4)));
							bct.setTheMonth(gd.getYearMonthTo().substring(4, 6));
							bct.setMark("Y");
							bct.setBox(gd.getQuantity() + "");
							bct.setPagination("false");
							bct.setOpId(gd.getUserId());
							boolean flag = goalService.insertFXGoal(bct);
							if (!flag) {
								resultMsg = gd.getKunnrTo() + ","
										+ gd.getYearMonthTo() + ","
										+ gd.getMatter() + "</br>";
								failnum++;
								continue;
							}
							// 跨组织调整，增加经销商目标量的同时，要增加该组织组织目标量
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnrTo())) {
								BCustomerTarget bt = new BCustomerTarget();
								bt.setOrgId(gd.getOrgIdTo() + "");
								bt.setOrgName(gd.getOrgNameTo());
								bt.setMatter(gd.getMatter());
								bt.setBox(gd.getQuantity() + "");
								bt.setTheYear(Long.parseLong(gd
										.getYearMonthTo().substring(0, 4)));
								bt.setTheMonth(gd.getYearMonthTo().substring(4,
										6));
								bt.setMark("N");
								bt.setOpId(gd.getUserId());
								flag = goalService.insertFXGoal(bt);
								if (!flag) {
									resultMsg = gd.getOrgId() + ","
											+ gd.getYearMonthTo() + ","
											+ gd.getMatter() + "</br>";
									failnum++;
									continue;
								}

							}
						} else {// 调整至组织
							if (gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())
									&& StringUtils.isEmpty(gd.getKunnrTo())) {// 同组织经销商调整至同组织，组织目标量不变
								continue;
							} else {// 其他情况增加组织的量
								BCustomerTarget bt = new BCustomerTarget();
								bt.setOrgId(gd.getOrgIdTo() + "");
								bt.setMatter(gd.getMatter());
								bt.setBox(gd.getQuantity() + "");
								bt.setTheYear(Long.parseLong(gd
										.getYearMonthTo().substring(0, 4)));
								bt.setTheMonth(gd.getYearMonthTo().substring(4,
										6));
								bt.setMark("N");
								bt.setOpId(gd.getUserId());
								boolean flag = goalService.insertFXGoal(bt);
								if (!flag) {
									resultMsg = gd.getOrgId() + ","
											+ gd.getYearMonthTo() + ","
											+ gd.getMatter() + "</br>";
									failnum++;
									continue;
								}
							}
						}
					}
					if (StringUtils.isNotEmpty(resultMsg)) {
						result.setResult(false);
						result.setCode("目标量保存失败，请联系管理员！</br>数量：" + failnum
								+ "</br>错误信息:" + resultMsg);

					}
				} else {
					result.setResult(false);
					result.setCode("审批流程出错，请联系管理员！</br>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setCode("审批流程异常，请联系管理员！</br>");
		}
		setExecuteResult(result);
		return JSON;
	}

	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createGoalSalesDone() {
		BooleanResult result;
		result = new BooleanResult();
		String resultMsg = "";
		int failnum = 0;
		try {
			if (StringUtils.isNotEmpty(eventId)) {
				goalSales = new GoalSales();
				goalSales.setEventId(Long.parseLong(eventId));
				goalSales.setPagination("false");
				List<GoalSales> goalSalesChangeList = goalService
						.searchGoalSalesChangeList(goalSales);
				Long changeId = goalSalesChangeList.get(0).getChangeId();

				goalSales.setEventState("D");
				goalSales.setChangeId(changeId);
				int count = goalService.updateGoalSalesChange(goalSales);
				if (count > 0) {
					result.setResult(true);
					result.setCode("流程审批完成！</br>");

					goalSalesDetail = new GoalSalesDetail();
					goalSalesDetail.setChangeId(changeId);
					goalSalesDetailList = goalService
							.searchGoalSalesChangeDetailList(goalSalesDetail);
					for (GoalSalesDetail gd : goalSalesDetailList) {
						// 获取物料金额
						Materiel mat = new Materiel();
						mat.setMvgr1(gd.getMatter());
						mat.setPagination("false");
						List<Materiel> mList = goalService
								.getMaterielViewList(mat);
						BigDecimal targetPrice = new BigDecimal(mList.get(0)
								.getKbetr());

						if (gd.getKunnrTo() != null) {// 调整至经销商，调整量加上经销商
							bct = new BCustomerTarget();
							bct.setCustId(gd.getKunnrTo());
							bct.setMatter(gd.getMatter());
							bct.setTheYear(Long.parseLong(gd.getYearMonthTo()
									.substring(0, 4)));
							bct.setTheMonth(gd.getYearMonthTo().substring(4, 6));
							bct.setMark("Y");
							bct.setKunnrGoalType("B");
							bct.setPagination("false");
							int size = goalService.getGoalListCount(bct);
							if (size > 0) {// 将调整量加入经销商
								bctList = goalService.getGoalList(bct);
								String ctId = bctList.get(0).getCtId();
								String boxNum = bctList.get(0).getBox();

								BigDecimal bboxNum = new BigDecimal(boxNum);
								BigDecimal bquantity = new BigDecimal(
										gd.getQuantity());
								BigDecimal tartgetBox = bboxNum.add(bquantity);
								BCustomerTarget bt = new BCustomerTarget();
								bt.setCtId(ctId);
								bt.setBox(tartgetBox.toString());
								bt.setTargetNum(targetPrice
										.multiply(tartgetBox));
								bt.setEventId(eventId);
								boolean flag = goalService.updateGoal(bt);
								if (!flag) {
									resultMsg = gd.getKunnrTo() + ","
											+ gd.getYearMonthTo() + ","
											+ gd.getMatter() + "</br>";
									failnum++;
									continue;
								}
							} else {// 经销商目标量不存在，则新建
								BigDecimal tartgetBox = new BigDecimal(
										gd.getQuantity());

								BCustomerTarget bt = new BCustomerTarget();
								bt.setCustId(gd.getKunnrTo());
								bt.setOrgId(gd.getOrgIdTo() + "");
								bt.setOrgName(gd.getOrgNameTo());
								bt.setMatter(gd.getMatter());
								bt.setBox(gd.getQuantity() + "");
								bt.setTargetNum(targetPrice
										.multiply(tartgetBox));
								bt.setTheYear(Long.parseLong(gd
										.getYearMonthTo().substring(0, 4)));
								bt.setTheMonth(gd.getYearMonthTo().substring(4,
										6));
								bt.setCtState("0");
								bt.setTrFlag("S");
								bt.setKunnrGoalType("B");
								bt.setMark("Y");
								bt.setEventId(eventId);
								BooleanResult flag = goalService.insertGoal(bt);
								if (!flag.getResult()) {
									resultMsg = gd.getKunnrTo() + ","
											+ gd.getYearMonthTo() + ","
											+ gd.getMatter() + "</br>";
									failnum++;
									continue;
								}
							}

							// 跨组织调整，增加经销商目标量的同时，要增加该组织组织目标量
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnrTo())) {
								bct = new BCustomerTarget();
								bct.setOrgId(gd.getOrgIdTo() + "");
								bct.setMatter(gd.getMatter());
								bct.setTheYear(Long.parseLong(gd
										.getYearMonthTo().substring(0, 4)));
								bct.setTheMonth(gd.getYearMonthTo().substring(
										4, 6));
								bct.setMark("N");
								bct.setKunnrGoalType("B");
								bct.setPagination("false");
								size = goalService.getGoalListCount(bct);
								if (size > 0) {// 将调整量加入组织
									bctList = goalService.getGoalList(bct);
									String ctId = bctList.get(0).getCtId();
									String boxNum = bctList.get(0).getBox();

									BigDecimal bboxNum = new BigDecimal(boxNum);
									BigDecimal bquantity = new BigDecimal(
											gd.getQuantity());
									BigDecimal tartgetBox = bboxNum
											.add(bquantity);
									BCustomerTarget bt = new BCustomerTarget();
									bt.setCtId(ctId);
									bt.setBox(tartgetBox.toString());
									bt.setTargetNum(targetPrice
											.multiply(tartgetBox));
									bt.setEventId(eventId);
									goalService.updateGoal(bt);
								} else {// 组织目标量不存在，则新建
									BigDecimal tartgetBox = new BigDecimal(
											gd.getQuantity());

									BCustomerTarget bt = new BCustomerTarget();
									bt.setOrgId(gd.getOrgIdTo() + "");
									bt.setOrgName(gd.getOrgNameTo());
									bt.setMatter(gd.getMatter());
									bt.setBox(gd.getQuantity() + "");
									bt.setTargetNum(targetPrice
											.multiply(tartgetBox));
									bt.setTheYear(Long.parseLong(gd
											.getYearMonthTo().substring(0, 4)));
									bt.setTheMonth(gd.getYearMonthTo()
											.substring(4, 6));
									bt.setCtState("0");
									bt.setTrFlag("S");
									bt.setKunnrGoalType("B");
									bt.setMark("N");
									bt.setEventId(eventId);
									goalService.insertGoal(bt);
								}
							}
						} else {// 调整至组织
							if (gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())
									&& StringUtils.isEmpty(gd.getKunnrTo())) {// 经销商调整至同组织，组织目标量不变
								continue;
							} else {// 其他情况增加组织的量
								bct = new BCustomerTarget();
								bct.setOrgId(gd.getOrgIdTo() + "");
								bct.setMatter(gd.getMatter());
								bct.setTheYear(Long.parseLong(gd
										.getYearMonthTo().substring(0, 4)));
								bct.setTheMonth(gd.getYearMonthTo().substring(
										4, 6));
								bct.setMark("N");
								bct.setKunnrGoalType("B");
								bct.setPagination("false");
								int size = goalService.getGoalListCount(bct);
								if (size > 0) {
									bctList = goalService.getGoalList(bct);
									String ctId = bctList.get(0).getCtId();
									String boxNum = bctList.get(0).getBox();

									BigDecimal bboxNum = new BigDecimal(boxNum);
									BigDecimal bquantity = new BigDecimal(
											gd.getQuantity());
									BigDecimal tartgetBox = bboxNum
											.add(bquantity);
									BCustomerTarget bt = new BCustomerTarget();
									bt.setCtId(ctId);
									bt.setBox(tartgetBox.toString());
									bt.setTargetNum(targetPrice
											.multiply(tartgetBox));
									bt.setEventId(eventId);
									boolean flag = goalService.updateGoal(bt);
									if (!flag) {
										resultMsg = gd.getOrgId() + ","
												+ gd.getYearMonthTo() + ","
												+ gd.getMatter() + "</br>";
										failnum++;
										continue;
									}
								} else {
									BigDecimal tartgetBox = new BigDecimal(
											gd.getQuantity());

									BCustomerTarget bt = new BCustomerTarget();
									bt.setOrgId(gd.getOrgIdTo() + "");
									bt.setOrgName(gd.getOrgNameTo());
									bt.setMatter(gd.getMatter());
									bt.setBox(gd.getQuantity() + "");
									bt.setTargetNum(targetPrice
											.multiply(tartgetBox));
									bt.setTheYear(Long.parseLong(gd
											.getYearMonthTo().substring(0, 4)));
									bt.setTheMonth(gd.getYearMonthTo()
											.substring(4, 6));
									bt.setCtState("0");
									bt.setTrFlag("S");
									bt.setKunnrGoalType("B");
									bt.setMark("N");
									bt.setEventId(eventId);
									BooleanResult flag = goalService
											.insertGoal(bt);
									if (!flag.getResult()) {
										resultMsg = gd.getOrgId() + ","
												+ gd.getYearMonthTo() + ","
												+ gd.getMatter() + "</br>";
										failnum++;
										continue;
									}
								}
							}
						}
					}
					if (StringUtils.isNotEmpty(resultMsg)) {
						result.setResult(false);
						result.setCode("目标量保存失败，请联系管理员！</br>数量：" + failnum
								+ "</br>错误信息:" + resultMsg);

					}
				} else {
					result.setResult(false);
					result.setCode("审批流程出错，请联系管理员！</br>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setCode("审批流程异常，请联系管理员！</br>");
		}
		setExecuteResult(result);
		return JSON;
	}

	/**
	 * 分销目标量
	 * 
	 * @return
	 */
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createGoalFXRefuse() {
		BooleanResult result = new BooleanResult();
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales = new GoalSales();
			goalSales.setEventId(Long.parseLong(eventId));
			goalSales.setPagination("false");
			List<GoalSales> goalSalesChangeList = goalService
					.searchGoalFXChangeList(goalSales);
			Long changeId = goalSalesChangeList.get(0).getChangeId();

			goalSales.setEventState("S");
			goalSales.setChangeId(changeId);
			int count = goalService.updateGoalFXChange(goalSales);
			if (count > 0) {
				result.setResult(true);
				result.setCode("操作成功！</br>");

				goalSalesDetail = new GoalSalesDetail();
				goalSalesDetail.setChangeId(changeId);
				goalSalesDetailList = goalService
						.searchGoalFXChangeDetailList(goalSalesDetail);
				for (GoalSalesDetail gd : goalSalesDetailList) {
					Materiel mat = new Materiel();
					mat.setMvgr1(gd.getMatter());
					mat.setPagination("false");
					bct = new BCustomerTarget();
					if (StringUtils.isNotEmpty(gd.getKunnr())) {
						bct.setCustId(gd.getKunnr());
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(gd.getYearMonth()
								.substring(0, 4)));
						bct.setTheMonth(gd.getYearMonth().substring(4, 6));
						bct.setMark("Y");
						bct.setPagination("false");
					} else {
						bct.setOrgId(gd.getOrgId() + "");
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(gd.getYearMonth()
								.substring(0, 4)));
						bct.setTheMonth(gd.getYearMonth().substring(4, 6));
						bct.setMark("N");
						bct.setPagination("false");
					}
					int size = goalService.getDistributionGoalTotal(bct);
					if (size > 0) {
						if (gd.getOrgId().equals(gd.getOrgIdTo())
								&& StringUtils.isEmpty(gd.getKunnr())
								&& StringUtils.isNotEmpty(gd.getKunnrTo())) {// 组织调整至同组织经销商，目标量不变
							continue;
						} else {
							BCustomerTarget bt = new BCustomerTarget();
							bt.setCustId(gd.getKunnr());
							bt.setOrgId(gd.getOrgId() + "");
							// bt.setOrgName(bct.getOrgNameTo());
							bt.setMatter(gd.getMatter());
							bt.setTheYear(Long.parseLong(gd.getYearMonth()
									.substring(0, 4)));
							bt.setTheMonth(gd.getYearMonth().substring(4, 6));
							if (StringUtils.isNotEmpty(gd.getKunnr())) {
								bt.setMark("Y");
							} else {
								bt.setMark("N");
							}
							bt.setOpId(this.getUser().getUserId());
							bt.setBox(gd.getQuantity() + "");
							boolean flag = goalService.insertFXGoal(bt);
							if (!flag) {
								result.setResult(false);
								result.setCode("目标量返还失败，请联系管理员！</br>");
								break;
							}

							// 返还组织目标量（跨组织调整，扣除经销商目标量的同时，要扣除该组织组织目标量）
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())) {
								bct = new BCustomerTarget();
								bct.setOrgId(gd.getOrgId() + "");
								bct.setMatter(gd.getMatter());
								bct.setTheYear(Long.parseLong(gd.getYearMonth()
										.substring(0, 4)));
								bct.setTheMonth(gd.getYearMonth().substring(4,
										6));
								bct.setMark("N");
								bct.setPagination("false");
								bct.setBox(gd.getQuantity() + "");
								goalService.insertFXGoal(bct);
							}
						}
					} else {
						result.setResult(false);
						result.setCode("目标量返还失败，请联系管理员！</br>");
						break;
					}
				}
			} else {
				result.setResult(false);
				result.setCode("审批流程出错，请联系管理员！</br>");
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createGoalSalesRefuse() {
		BooleanResult result = new BooleanResult();
		if (StringUtils.isNotEmpty(eventId)) {
			goalSales = new GoalSales();
			goalSales.setEventId(Long.parseLong(eventId));
			goalSales.setPagination("false");
			List<GoalSales> goalSalesChangeList = goalService
					.searchGoalSalesChangeList(goalSales);
			Long changeId = goalSalesChangeList.get(0).getChangeId();

			goalSales.setEventState("S");
			goalSales.setChangeId(changeId);
			int count = goalService.updateGoalSalesChange(goalSales);
			if (count > 0) {
				result.setResult(true);
				result.setCode("操作成功！</br>");

				goalSalesDetail = new GoalSalesDetail();
				goalSalesDetail.setChangeId(changeId);
				goalSalesDetailList = goalService
						.searchGoalSalesChangeDetailList(goalSalesDetail);
				for (GoalSalesDetail gd : goalSalesDetailList) {
					Materiel mat = new Materiel();
					mat.setMvgr1(gd.getMatter());
					mat.setPagination("false");
					List<Materiel> mList = goalService.getMaterielViewList(mat);
					BigDecimal targetPrice = new BigDecimal(mList.get(0)
							.getKbetr());

					bct = new BCustomerTarget();
					if (StringUtils.isNotEmpty(gd.getKunnr())) {
						bct.setCustId(gd.getKunnr());
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(gd.getYearMonth()
								.substring(0, 4)));
						bct.setTheMonth(gd.getYearMonth().substring(4, 6));
						bct.setMark("Y");
						bct.setKunnrGoalType("B");
						bct.setPagination("false");
					} else {
						bct.setOrgId(gd.getOrgId() + "");
						bct.setMatter(gd.getMatter());
						bct.setTheYear(Long.parseLong(gd.getYearMonth()
								.substring(0, 4)));
						bct.setTheMonth(gd.getYearMonth().substring(4, 6));
						bct.setMark("N");
						bct.setKunnrGoalType("B");
						bct.setPagination("false");
					}
					int size = goalService.getGoalListCount(bct);
					if (size > 0) {
						if (gd.getOrgId().equals(gd.getOrgIdTo())
								&& StringUtils.isEmpty(gd.getKunnr())
								&& StringUtils.isNotEmpty(gd.getKunnrTo())) {// 组织调整至同组织经销商，目标量不变
							continue;
						} else {
							bctList = goalService.getGoalList(bct);
							String ctId = bctList.get(0).getCtId();
							String boxNum = bctList.get(0).getBox();

							BigDecimal b1 = new BigDecimal(boxNum);
							BigDecimal b2 = new BigDecimal(gd.getQuantity());
							BigDecimal tartgetBox = b1.add(b2);
							BCustomerTarget bt = new BCustomerTarget();
							bt.setCtId(ctId);
							bt.setTargetNum(targetPrice.multiply(tartgetBox));
							bt.setBox(tartgetBox.toString());
							boolean flag = goalService.updateGoal(bt);
							if (!flag) {
								result.setResult(false);
								result.setCode("目标量返还失败，请联系管理员！</br>");
								break;
							}

							// 返还组织目标量（跨组织调整，扣除经销商目标量的同时，要扣除该组织组织目标量）
							if (!gd.getOrgId().equals(gd.getOrgIdTo())
									&& StringUtils.isNotEmpty(gd.getKunnr())) {
								bct = new BCustomerTarget();
								bct.setOrgId(gd.getOrgId() + "");
								bct.setMatter(gd.getMatter());
								bct.setTheYear(Long.parseLong(gd.getYearMonth()
										.substring(0, 4)));
								bct.setTheMonth(gd.getYearMonth().substring(4,
										6));
								bct.setMark("N");
								bct.setKunnrGoalType("B");
								bct.setPagination("false");

								bctList = goalService.getGoalList(bct);
								ctId = bctList.get(0).getCtId();
								boxNum = bctList.get(0).getBox();
								BigDecimal b3 = new BigDecimal(boxNum);
								BigDecimal b4 = new BigDecimal(gd.getQuantity());
								tartgetBox = b3.add(b4);
								bt = new BCustomerTarget();
								bt.setCtId(ctId);
								bt.setTargetNum(targetPrice
										.multiply(tartgetBox));
								bt.setBox(tartgetBox.toString());
								goalService.updateGoal(bt);
							}
						}
					} else {
						result.setResult(false);
						result.setCode("目标量返还失败，请联系管理员！</br>");
						break;
					}
				}
			} else {
				result.setResult(false);
				result.setCode("审批流程出错，请联系管理员！</br>");
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	public String saveGoalSales() {
		Long changeId = null;
		if (goalSales != null) {
			goalSales.setEventState("N");
			goalSales.setUserId(this.getUser().getUserId());
			changeId = goalService.createGoalSalesChange(goalSales);
		}
		if (goalSalesDetailList != null && goalSalesDetailList.size() > 0) {
			for (GoalSalesDetail gd : goalSalesDetailList) {
				String year = gd.getYearMonth().substring(0, 4);
				String month = gd.getYearMonth().substring(4, 6);
				String yearTo = gd.getYearMonthTo().substring(0, 4);
				String monthTo = gd.getYearMonthTo().substring(4, 6);

				GoalSalesDetail gsdTo = new GoalSalesDetail();
				gsdTo.setChangeId(changeId);
				gsdTo.setYear(yearTo);
				gsdTo.setMonth(monthTo);
				gsdTo.setKunnr(gd.getKunnrTo());
				gsdTo.setOrgId(gd.getOrgIdTo());
				gsdTo.setMatter(gd.getMatter());
				gsdTo.setQuantity(gd.getQuantity());
				Long detailIdTo = goalService
						.createGoalSalesChangeDetail(gsdTo);

				GoalSalesDetail gsd = new GoalSalesDetail();
				gsd.setChangeId(changeId);
				gsd.setOrgId(gd.getOrgId());
				gsd.setKunnr(gd.getKunnr());
				gsd.setYear(year);
				gsd.setMonth(month);
				gsd.setMatter(gd.getMatter());
				gsd.setQuantity(-gd.getQuantity());
				gsd.setDetailIdTo(detailIdTo);
				goalService.createGoalSalesChangeDetail(gsd);
			}
		}
		if (changeId != null) {
			this.setSuccessMessage("保存成功！");
		} else {
			this.setFailMessage("保存失败,请重试！");
		}
		this.getSession().removeAttribute("goalSalesDetailList");
		return RESULT_MESSAGE;
	}

	/**
	 * 设置导入数据百分比
	 * 
	 * @return
	 */
	public void setImportPercent(int percent) {
		if (percent == 0) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("importExcel", "Y");
			ServletActionContext.getRequest().getSession()
					.setAttribute("importPercent", percent);
		} else if (percent == 99) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("importPercent", percent);
			ServletActionContext.getRequest().getSession()
					.removeAttribute("importExcel");
		} else {
			ServletActionContext.getRequest().getSession()
					.setAttribute("importPercent", percent);
		}
	}

	public BCustomerTarget getBct() {
		return bct;
	}

	public void setBct(BCustomerTarget bct) {
		this.bct = bct;
	}

	public IWfeService getWfeServiceHessian() {
		return wfeServiceHessian;
	}

	public void setWfeServiceHessian(IWfeService wfeServiceHessian) {
		this.wfeServiceHessian = wfeServiceHessian;
	}

	public IGoalService getGoalService() {
		return goalService;
	}

	public void setGoalService(IGoalService goalService) {
		this.goalService = goalService;
	}

	public List<CmsTbDict> getDictlist() {
		return dictlist;
	}

	public void setDictlist(List<CmsTbDict> dictlist) {
		this.dictlist = dictlist;
	}

	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}

	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFlagTemp() {
		return flagTemp;
	}

	public void setFlagTemp(String flagTemp) {
		this.flagTemp = flagTemp;
	}

	public Kunnr getKunnr() {
		return kunnr;
	}

	public void setKunnr(Kunnr kunnr) {
		this.kunnr = kunnr;
	}

	public List<BCustomerTarget> getBctList() {
		return bctList;
	}

	public void setBctList(List<BCustomerTarget> bctList) {
		this.bctList = bctList;
	}

	public List<Kunnr> getKunnrlist() {
		return kunnrlist;
	}

	public void setKunnrlist(List<Kunnr> kunnrlist) {
		this.kunnrlist = kunnrlist;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public BigDecimal getTargetNum() {
		return targetNum;
	}

	public void setTargetNum(BigDecimal targetNum) {
		this.targetNum = targetNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCtId() {
		return ctId;
	}

	public void setCtId(String ctId) {
		this.ctId = ctId;
	}

	public int getTheYear() {
		return theYear;
	}

	public void setTheYear(int theYear) {
		this.theYear = theYear;
	}

	public String getTheMonth() {
		return theMonth;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public Materiel getMat() {
		return mat;
	}

	public void setMat(Materiel mat) {
		this.mat = mat;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public void setMatList(List<Materiel> matList) {
		this.matList = matList;
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

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public IAllUserService getAllUserServiceHessian() {
		return allUserServiceHessian;
	}

	public void setAllUserServiceHessian(IAllUserService allUserServiceHessian) {
		this.allUserServiceHessian = allUserServiceHessian;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public int getSize1() {
		return size1;
	}

	public void setSize1(int size1) {
		this.size1 = size1;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getDetailJsonStr() {
		return detailJsonStr;
	}

	public void setDetailJsonStr(String detailJsonStr) {
		this.detailJsonStr = detailJsonStr;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public BooleanResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public List<BCustomerTarget> getBctList1() {
		return bctList1;
	}

	public void setBctList1(List<BCustomerTarget> bctList1) {
		this.bctList1 = bctList1;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKunnrGoalType() {
		return kunnrGoalType;
	}

	public void setKunnrGoalType(String kunnrGoalType) {
		this.kunnrGoalType = kunnrGoalType;
	}

	public String getBezei() {
		return bezei;
	}

	public void setBezei(String bezei) {
		this.bezei = bezei;
	}

	public String getMvgr1() {
		return mvgr1;
	}

	public void setMvgr1(String mvgr1) {
		this.mvgr1 = mvgr1;
	}

	public String getTrFlag() {
		return trFlag;
	}

	public void setTrFlag(String trFlag) {
		this.trFlag = trFlag;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public double getCountGoal() {
		return countGoal;
	}

	public void setCountGoal(double countGoal) {
		this.countGoal = countGoal;
	}

	public MatterPriceObject getPriceObj() {
		return priceObj;
	}

	public void setPriceObj(MatterPriceObject priceObj) {
		this.priceObj = priceObj;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<MatterPriceObject> getPriceObjList() {
		return priceObjList;
	}

	public void setPriceObjList(List<MatterPriceObject> priceObjList) {
		this.priceObjList = priceObjList;
	}

	public MatterPriceBO getPriceBo() {
		return priceBo;
	}

	public void setPriceBo(MatterPriceBO priceBo) {
		this.priceBo = priceBo;
	}

	public List<MatterPriceBO> getPriceBoList() {
		return priceBoList;
	}

	public void setPriceBoList(List<MatterPriceBO> priceBoList) {
		this.priceBoList = priceBoList;
	}

	public String getMvgTxt() {
		return mvgTxt;
	}

	public void setMvgTxt(String mvgTxt) {
		this.mvgTxt = mvgTxt;
	}

	public String getCupType() {
		return cupType;
	}

	public void setCupType(String cupType) {
		this.cupType = cupType;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public File getFileContent() {
		return fileContent;
	}

	public void setFileContent(File fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileContentFileName() {
		return fileContentFileName;
	}

	public void setFileContentFileName(String fileContentFileName) {
		this.fileContentFileName = fileContentFileName;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getType_ny() {
		return type_ny;
	}

	public String getFileName() {
		return fileName;
	}

	public void setType_ny(String type_ny) {
		this.type_ny = type_ny;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public GoalSales getGoalSales() {
		return goalSales;
	}

	public void setGoalSales(GoalSales goalSales) {
		this.goalSales = goalSales;
	}

	public List<GoalSales> getGoalSalesList() {
		return goalSalesList;
	}

	public void setGoalSalesList(List<GoalSales> goalSalesList) {
		this.goalSalesList = goalSalesList;
	}

	public GoalSalesDetail getGoalSalesDetail() {
		return goalSalesDetail;
	}

	public void setGoalSalesDetail(GoalSalesDetail goalSalesDetail) {
		this.goalSalesDetail = goalSalesDetail;
	}

	public List<GoalSalesDetail> getGoalSalesDetailList() {
		return goalSalesDetailList;
	}

	public void setGoalSalesDetailList(List<GoalSalesDetail> goalSalesDetailList) {
		this.goalSalesDetailList = goalSalesDetailList;
	}

	public Long getChangeId() {
		return changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConFlag() {
		return conFlag;
	}

	public void setConFlag(String conFlag) {
		this.conFlag = conFlag;
	}

	public String getYearFlag() {
		return yearFlag;
	}

	public void setYearFlag(String yearFlag) {
		this.yearFlag = yearFlag;
	}

	public PrintContractGoalInfo getPrintConGoalInfo() {
		return printConGoalInfo;
	}

	public void setPrintConGoalInfo(PrintContractGoalInfo printConGoalInfo) {
		this.printConGoalInfo = printConGoalInfo;
	}

	public List<PrintContractGoalInfo> getPcgInfoList() {
		return pcgInfoList;
	}

	public void setPcgInfoList(List<PrintContractGoalInfo> pcgInfoList) {
		this.pcgInfoList = pcgInfoList;
	}

	public JSONArray getPcgInfoJson() {
		return pcgInfoJson;
	}

	public void setPcgInfoJson(JSONArray pcgInfoJson) {
		this.pcgInfoJson = pcgInfoJson;
	}

	public List<PrintContractGoalInfo> getPcgInfoList2() {
		return pcgInfoList2;
	}

	public void setPcgInfoList2(List<PrintContractGoalInfo> pcgInfoList2) {
		this.pcgInfoList2 = pcgInfoList2;
	}

	public JSONArray getPcgInfoJson2() {
		return pcgInfoJson2;
	}

	public void setPcgInfoJson2(JSONArray pcgInfoJson2) {
		this.pcgInfoJson2 = pcgInfoJson2;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	public String getMaktx01() {
		return maktx01;
	}

	public void setMaktx01(String maktx01) {
		this.maktx01 = maktx01;
	}

	public String getMatnr01() {
		return matnr01;
	}

	public void setMatnr01(String matnr01) {
		this.matnr01 = matnr01;
	}
	public INewOrgService getNewOrgService() {
		return newOrgService;
	}
	public void setNewOrgService(INewOrgService newOrgService) {
		this.newOrgService = newOrgService;
	}

}
