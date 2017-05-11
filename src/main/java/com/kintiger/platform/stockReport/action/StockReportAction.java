package com.kintiger.platform.stockReport.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.framework.util.ExportExcelUtil;
import com.kintiger.platform.framework.util.JavaBeanXMLUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.framework.util.XMLInfo;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.kunnrBusinessContact.service.IKunnrBusinessService;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.question.service.IQuestionService;
import com.kintiger.platform.sms.pojo.SendInfo;
import com.kintiger.platform.sms.service.ISmsInterfaceService;
import com.kintiger.platform.stockReport.pojo.AllUsers;
import com.kintiger.platform.stockReport.pojo.Category;
import com.kintiger.platform.stockReport.pojo.Goal;
import com.kintiger.platform.stockReport.pojo.Kunag;
import com.kintiger.platform.stockReport.pojo.KunnrInType;
import com.kintiger.platform.stockReport.pojo.KunnrManager;
import com.kintiger.platform.stockReport.pojo.OrderCheck;
import com.kintiger.platform.stockReport.pojo.OrderFollow;
import com.kintiger.platform.stockReport.pojo.SkuDistribution;
import com.kintiger.platform.stockReport.pojo.SkuPercent;
import com.kintiger.platform.stockReport.pojo.SkuUnit;
import com.kintiger.platform.stockReport.pojo.StockDate;
import com.kintiger.platform.stockReport.pojo.StockManager;
import com.kintiger.platform.stockReport.pojo.StockReport;
import com.kintiger.platform.stockReport.pojo.StockSafety;
import com.kintiger.platform.stockReport.service.IStockReportService;
import com.kintiger.platform.wfe.pojo.UserUtil;
import com.kintiger.platform.wfe.service.IWfeService;

public class StockReportAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 836529252712197603L;
	
	private IStockReportService stockReportService;
	private IQuestionService questionService;
	private IOrgService orgServiceHessian;
	private ICustomerService customerService;
	private IDictService iDictService;
	private IGoalService goalService;
	private String brand;//品牌
	private Long orgId;
	private String orgIds;//多个组织id
	private String orgName;
	private String kunnr;
	private String kunnrCode;
	private Long skuId;
	private Long categoryId;
	private String startDate;
	private String endDate;
	private String productionStartDate;
	private String productionEndDate;
	private String productionDate;
	private String flag;
	private StockReport stockReport;
	private List<StockReport> stockReportList;
	private int total;
	private String loginId;
	private Integer stockId;
	private Double quantity;
	private String deleteStockIds;
	
	private @Decode String skuName;
	private @Decode String categoryName;
	private Sku sku;
	private List<Sku> skuList;
	private Category category;
	private List<Category> categoryList;
	private int skuSize;
	private int categorySize;
	
	private List<StockDate> stockDateList;
	private StockDate stockDate;
	private List<StringResult> theYearList;
	private List<StringResult> theMonthList;
	private Integer id;
	private String year;
	private String month;
	private String week;
	private String state;
	private Date time;
	
	private Date[] startDates;
	private Date[] endDates;
	private Date[] checkTimes;
	
	private String type;
	private String stockDay;
	private String leastOrder;
	private String handleCycle;
	
	private List<KunnrInType> kunnrInTypeList;
	private @Decode String kunnrName;
	private KunnrInType kunnrInType;
	
	private String uploadFileFileName;
	private File uploadFile;
	private IKunnrService kunnrService;
	
	private SkuPercent skuPercent;
	private List<SkuPercent> skuPercentList;
	private Integer cgId;
	private Integer percent;
	
	private String download;
	
	private List<Goal> goalList;
	private Goal goal;
	private String goalType;
	
	private List<StockManager> stockManagerList;
	private StockManager stockManager;
	private String stockState;
	private String isDD;
	private String userType;
	@Decode
	private String userName;
	private String province;
	private String city;
	private String stockFlag;
	private String isNotice;
	private Date kunnrStartDate;
	private Date kunnrEndDate;
	private String checkTime;
	private String status;
	private String isImportant;
	
	private List<Kunag> kunagList;
	private Kunag kunag;
	private String kunnrId;
	private String kunagId;
	private @Decode String name1;
	private @Decode String name2;
	
	private String goalMark;
	private String matterNum;
	
	private Long custId;
	private Long channelId;
	
	private List<OrderCheck> orderCheckList;
	private OrderCheck orderCheck;
	
	private List<KunnrManager> kunnrManagerList;
	private KunnrManager kunnrManager;
	
	private List<StockSafety> stockSafetyList;
	private StockSafety stockSafety;
	private @Decode String update;
	
	private SkuUnit skuUnit;
	private List<SkuUnit> skuUnitList;
	
	private OrderFollow orderFollow;
	private List<OrderFollow> orderFollowList;
	
	private IKunnrBusinessService kunnrBusinessService;
	
	private ISmsInterfaceService smsServiceHessian;
	private String otherIp;//外网获取的ip  搜狐
	
	public String toWeekReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setCheckTime("Y");
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
		}else{
			state="2";
		}
		return "toWeekReport";
	}
	
	public String toStockReportKunnr(){
		orgId=Long.parseLong("51235");
		orgName = orgServiceHessian
				.getOrgNameByOrgid("50919");
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
			if(stockDateList.size()>1){
				flag="both";
				for(int i=1;i<stockDateList.size();i++){
					productionDate+=","+stockDateList.get(i).getCheckTime();
				}
			}else if(stockDateList.get(0).getId()!=null && stockDateList.get(0).getId()==6){
				flag="kunnr_month";
			}else{
				flag="kunnr_week";
			}
		}else{
			state="2";
		}
		return "toStockReportKunnr";
	}
	
	public String toMonthReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setId(6);
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
		}else{
			state="2";
		}
		return "toMonthReport";
	}
	
	public String toMonthReportDD(){
		orgId=Long.parseLong("51235");
		orgName = orgServiceHessian
				.getOrgNameByOrgid("50919");
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
			if(stockDateList.size()>1){
				flag="both";
				for(int i=1;i<stockDateList.size();i++){
					productionDate+=","+stockDateList.get(i).getCheckTime();
				}
			}else if(stockDateList.get(0).getId()!=null && stockDateList.get(0).getId()==6){
				flag="kunnr_month";
			}else{
				flag="kunnr_week";
			}
		}else{
			state="2";
		}
		return "toMonthReportDD";
	}
	
	public String toMonthReportCW(){
		orgId=Long.parseLong("51235");
		orgName = orgServiceHessian
				.getOrgNameByOrgid("50919");
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setId(6);
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
		}else{
			state="2";
		}
		return "toMonthReportCW";
	}
	
	public String toDayReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		loginId=this.getUser().getUserId();
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setCheckTime("Y");
		stockDateList = stockReportService.getStockDate(stockDate);
		if(stockDateList!=null && stockDateList.size()>0){
			state="1";
			productionDate = stockDateList.get(0).getCheckTime();
		}else{
			state="2";
		}
		return "toDayReport";
	}
	
	public String toCreateStockReportWeek(){
		String isOffice=this.getUser().getIsOffice();
		kunnr="";
		if(StringUtils.isNotEmpty(isOffice)){
			List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}else{
			List<String> kunnrStrs=kunnrBusinessService.getKunnrIdByKunnrBusinessByKunag(this.getUser().getUserId());
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setCheckTime("Y");
		stockDate=stockReportService.getStockDate(stockDate).get(0);
		stockReport = new StockReport();
		stockReport.setCheckTime(stockDate.getCheckTime());
		return "toCreateStockReportWeek";
	}
	
	public String toCreateStockReportMonth(){
		String isOffice=this.getUser().getIsOffice();
		kunnr="";
		if(StringUtils.isNotEmpty(isOffice)){
			List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}else{
			List<String> kunnrStrs=kunnrBusinessService.getKunnrIdByKunnrBusinessByKunag(this.getUser().getUserId());
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}
		stockDate = new StockDate();
		stockDate.setModifyDate(new Date());
		stockDate.setId(6);
		stockDate=stockReportService.getStockDate(stockDate).get(0);
		stockReport = new StockReport();
		stockReport.setCheckTime(stockDate.getCheckTime());
		return "toCreateStockReportMonth";
	}
	
	public String toCreateStockReportSales(){
		String isOffice=this.getUser().getIsOffice();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stockDay=sdf.format(stockReportService.getSysdate());
		kunnr="";
		if(StringUtils.isNotEmpty(isOffice)){
			userType="K";
			List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}else{
			userType="A";
			List<String> kunnrStrs=kunnrBusinessService.getKunnrIdByKunnrBusinessByKunag(this.getUser().getUserId());
			if(kunnrStrs!=null && kunnrStrs.size()>0){
				for(int i=0;i<kunnrStrs.size();i++){
					if(i<kunnrStrs.size()-1){
						kunnr+=kunnrStrs.get(i)+",";
					}else{
						kunnr+=kunnrStrs.get(i);
					}
				}
			}
		}
		return "toCreateStockReportSales";
	}
	
	public String toSalesReport(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		stockDay=sdf.format(stockReportService.getSysdate());
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		loginId=this.getUser().getUserId();
		return "toSalesReport";
	}
	
	public String toWeekDetailCGReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toWeekDetailCGReport";
	}
	
	public String toWeekDetailSkuReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toWeekDetailSkuReport";
	}
	
	public String toWeekDetailSalesReport(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toWeekDetailSalesReport";
	}
	
	public String toKunnrPlanReport(){
		return "toKunnrPlanReport";
	}
	
	public String toKunnrType(){
		return "toKunnrType";
	}
	
	public String toOrderCheck(){
		kunnrId=this.getUser().getIsOffice();
		orgId=Long.parseLong(this.getUser().getOrgId());
		orgName=this.getUser().getOrgName();
		return "toOrderCheck";
	}
	
	public String toKunnrInType(){
		return "toKunnrInType";
	}
	
	public String toSkuPercent(){
		return "toSkuPercent";
	}
	
	public String toSalesGoalCG(){
		return "toSalesGoalCG";
	}
	
	public String toSalesGoalCGDetail(){
		return "toSalesGoalCGDetail";
	}
	
	public String toSalesGoalCGDetailView(){
		return "toSalesGoalCGDetailView";
	}
	
	public String toSalesGoalChallenge(){
		return"toSalesGoalChallenge";
	}
	
	public String toStockManager(){
		return "toStockManager";
	}
	
	public String toStockManagerReport(){
		return "toStockManagerReport";
	}
	
	public String toSearchKunag(){
		return "toSearchKunag";
	}

	public String toStockCheck(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toStockCheck";
	}
	
	public String toStockReportSalesByCG(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		return "toStockReportSalesByCG";
	}
	
	public String toStockDate(){
		Calendar cal = Calendar.getInstance();
		theYearList = new ArrayList<StringResult>();
		theMonthList = new ArrayList<StringResult>();
		for (int i = -1; i < 2; i++) {// 获取当前年份的前后1年
			StringResult stringResult = new StringResult();
			stringResult.setResult(String.valueOf(cal.get(Calendar.YEAR) + i));
			if (cal.get(Calendar.YEAR) + i == cal.get(Calendar.YEAR)) {
				stringResult.setCode("1");
			}
			theYearList.add(stringResult);
		}
		for (int i = 1; i < 13; i++) {// 获取月份
			StringResult stringResult = new StringResult();
			if (cal.get(Calendar.MONTH) + 1 == i) {
				stringResult.setCode("1");
			}
			stringResult.setResult(String.valueOf(i).length() == 1 ? "0"
					+ String.valueOf(i) : String.valueOf(i));
			theMonthList.add(stringResult);
		}
		return "toStockDate";
	}
	
	public String toStockTotal(){
		return "toStockTotal";
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String searchStockReportCount(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setCheckTime(checkTime);
		stockReport.setProductionDate(productionDate);
		stockReport.setFlag(flag);
		stockReport.setUserType(userType);
		total = stockReportService.getStockReportListCount(stockReport);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","skuCode",
			"categoryId","categoryName","quantity","unitDesc","flag","kunag","kunagName","brand","brandName" },total = "total")
	public String searchStockReportWeekList(){
		String[] kunnrs;
		stockReport = new StockReport();
		if(kunnr==null || "".equals(kunnr)){
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null || kunnrTmp.size()==0){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			if(kunnrTmp!=null && kunnrTmp.size()!=0){
				if(kunnrTmp.size()==1){
					kunnr=kunnrTmp.get(0);
				}else{
					kunnrs=new String[kunnrTmp.size()];
					for(int i=0;i<kunnrTmp.size();i++){
						kunnrs[i]=kunnrTmp.get(i);
					}
					stockReport.setKunnrs(kunnrs);
				}
			}
		}
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setBrand(brand);
		stockReport.setFlag("kunnr_week");
		if("K".equals(userType) || "A".equals(userType)){
			String[] userTypes = {"A","K"};
			stockReport.setUserTypes(userTypes);
		}else{
			stockReport.setUserType(userType);
		}
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.getStockReportList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","productionDate",
			"categoryId","categoryName","quantity","unitDesc","flag","kunag","kunagName","skuCode","brand","brandName" },total = "total")
	public String searchStockReportMonthList() throws ParseException{
		String[] kunnrs;
		stockReport = new StockReport();
		if(kunnr==null || "".equals(kunnr)){
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null || kunnrTmp.size()==0){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			if(kunnrTmp!=null && kunnrTmp.size()!=0){
				if(kunnrTmp.size()==1){
					kunnr=kunnrTmp.get(0);
				}else{
					kunnrs=new String[kunnrTmp.size()];
					for(int i=0;i<kunnrTmp.size();i++){
						kunnrs[i]=kunnrTmp.get(i);
					}
					stockReport.setKunnrs(kunnrs);
				}
			}
		}
		if(StringUtils.isNotEmpty(userType) && (userType.equals("A") || userType.equals("C"))){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(productionEndDate!=null && !"".equals(productionEndDate)){
				Date date=sdf.parse(productionEndDate+"-01");
				Calendar cal=Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				productionEndDate = sdf.format(cal.getTime());
			}
		}
		stockReport.setUserName(userName);
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setFlag(flag);
		stockReport.setBrand(brand);
		if("K".equals(userType) || "A".equals(userType)){
			String[] userTypes = {"A","K"};
			stockReport.setUserTypes(userTypes);
		}else{
			stockReport.setUserType(userType);
		}
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.getStockReportList(stockReport);
			for(int i=0;i<stockReportList.size();i++){
				StockReport s =stockReportList.get(i);
				if(StringUtils.isNotEmpty(s.getProductionDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=sdf.parse(s.getProductionDate());
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.MONTH, 1);
					cal.add(Calendar.DATE, -1);
					date=cal.getTime();
					Date date1=sdf1.parse(s.getCreateDate().substring(0, s.getCreateDate().length()-2));
					long diff = date1.getTime() - date.getTime();
				    double months = ((double)diff) / (1000 * 60 * 60 * 24);
				    if(months>=370){
				    	stockReportList.get(i).setProductionDate("已过期");
				    }
				}
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","productionDate",
			"categoryId","categoryName","quantity","unitDesc","flag","kunag","kunagName","skuCode","brand","brandName" },total = "total")
	public String searchStockReportListByKunnrSales() throws ParseException{
		stockReport = new StockReport();
		String orgStrs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgStr = orgStrs.split(",");
		if(ArrayUtils.contains(orgStr,this.getUser().getOrgId())){
			String[] kunnrs={""};
			String isOffice=this.getUser().getIsOffice();
			if(StringUtils.isNotEmpty(isOffice)){
				List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
				if(kunnrStrs!=null && kunnrStrs.size()>0){
					kunnrs=(String[])kunnrStrs.toArray(new String[kunnrStrs.size()]);
				}
			}
			stockReport.setKunnrs(kunnrs);
		}
		stockReport.setOrgId(orgId);
		stockReport.setUserName(userName);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setBrand(brand);
		stockReport.setFlag(flag);
		if("K".equals(userType) || "A".equals(userType)){
			String[] userTypes = {"A","K"};
			stockReport.setUserTypes(userTypes);
		}else{
			stockReport.setUserType(userType);
		}
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.getStockReportList(stockReport);
			for(int i=0;i<stockReportList.size();i++){
				StockReport s =stockReportList.get(i);
				if(StringUtils.isNotEmpty(s.getProductionDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=sdf.parse(s.getProductionDate());
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.MONTH, 1);
					cal.add(Calendar.DATE, -1);
					date=cal.getTime();
					Date date1=sdf1.parse(s.getCreateDate().substring(0, s.getCreateDate().length()-2));
					long diff = date1.getTime() - date.getTime();
				    double months = ((double)diff) / (1000 * 60 * 60 * 24);
				    if(months>=370){
				    	stockReportList.get(i).setProductionDate("已过期");
				    }
				}
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","skuCode",
			"categoryId","categoryName","quantity","unitDesc","flag","kunag","kunagName" },total = "total")
	public String searchStockReportDayList(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setFlag("kunnr_day");
		stockReport.setUserType(userType);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.getStockReportList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime",
			"categoryId","categoryName","quantity","unitDesc","flag","productionDate" },total = "total")
	public String searchStockTotalList(){
		try {
			String[] kunnrs;
			stockReport = new StockReport();
			if(kunnr==null || "".equals(kunnr)){
				List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
				if(kunnrTmp==null || kunnrTmp.size()==0){
					kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
				}
				if(kunnrTmp!=null && kunnrTmp.size()!=0){
					if(kunnrTmp.size()==1){
						kunnr=kunnrTmp.get(0);
					}else{
						kunnrs=new String[kunnrTmp.size()];
						for(int i=0;i<kunnrTmp.size();i++){
							kunnrs[i]=kunnrTmp.get(i);
						}
						stockReport.setKunnrs(kunnrs);
					}
				}
			}
			stockReport.setOrgId(orgId);
			stockReport.setKunnr(kunnr);
			stockReport.setSkuId(skuId);
			stockReport.setUserName(userName);
			stockReport.setCategoryId(categoryId);
			stockReport.setStartDate(startDate);
			stockReport.setEndDate(endDate);
			stockReport.setProductionStartDate(productionStartDate);
			stockReport.setProductionEndDate(productionEndDate);
			stockReport.setStart(getStart());
			stockReport.setEnd(getEnd());
			total = stockReportService.getStockTotalListCount(stockReport);
			if(total>0){
				stockReportList = stockReportService.getStockTotalList(stockReport);
				for(int i=0;i<stockReportList.size();i++){
					StockReport s =stockReportList.get(i);
					if(StringUtils.isNotEmpty(s.getProductionDate())){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date=sdf.parse(s.getProductionDate());
						Calendar cal=Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.MONTH, 1);
						cal.add(Calendar.DATE, -1);
						date=cal.getTime();
						Date date1=sdf1.parse(s.getCreateDate().substring(0, s.getCreateDate().length()-2));
						long diff = date1.getTime() - date.getTime();
					    double months = ((double)diff) / (1000 * 60 * 60 * 24);
					    if(months>=370){
					    	stockReportList.get(i).setProductionDate("已过期");
					    }
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReport",include = {"kunag","flag"})
	public String getStockReportKunnrAndKunagSum(){
		try {
//			String[] kunnrs;
			stockReport = new StockReport();
			if(kunnr==null || "".equals(kunnr)){
				List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
				if(kunnrTmp==null || kunnrTmp.size()==0){
					kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
				}
				if(kunnrTmp!=null && kunnrTmp.size()!=0){
					if(kunnrTmp.size()==1){
						kunnr=kunnrTmp.get(0);
					}else{
//						kunnrs=new String[kunnrTmp.size()];
//						for(int i=0;i<kunnrTmp.size();i++){
//							kunnrs[i]=kunnrTmp.get(i);
//						}
//						stockReport.setKunnrs(kunnrs);
					}
				}
			}
			StockReport sr=new StockReport();
			if(StringUtils.isNotEmpty(kunnr) && StringUtils.isNotEmpty(productionStartDate)
					&&StringUtils.isNotEmpty(productionEndDate)){
				if(flag!=null && "kunnr_month".equals(flag) && "A".equals(userType)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(productionEndDate!=null && !"".equals(productionEndDate)){
						Date date=sdf.parse(productionEndDate+"-01");
						Calendar cal=Calendar.getInstance();
						cal.setTime(date);
						cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						productionEndDate = sdf.format(cal.getTime());
					}
				}
				Kunag kunag=new Kunag();
				kunag.setKunnr(kunnr);
				kunag.setStart(0);
				kunag.setEnd(99999);
				List<Kunag> kList=stockReportService.searchKunag(kunag);
				if(kList!=null && StringUtils.isNotEmpty(kList.get(0).getKunag())){
					sr.setKunag(kList.get(0).getKunag());
					stockReport.setKunag(kList.get(0).getName2());
				}else{
					sr.setKunnr(kunnr);
					stockReport.setKunag(kList.get(0).getName1());
				}
				sr.setProductionStartDate(productionStartDate);
				sr.setProductionEndDate(productionEndDate);
				sr.setFlag(flag);
				sr.setUserType(userType);
				matterNum = stockReportService.getStockReportKunnrAndKunagSum(sr);
				stockReport.setFlag(matterNum);
			}else if(userType!=null && userType.equals("C")){
				if(flag!=null && "kunnr_month".equals(flag)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(productionEndDate!=null && !"".equals(productionEndDate)){
						Date date=sdf.parse(productionEndDate+"-01");
						Calendar cal=Calendar.getInstance();
						cal.setTime(date);
						cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						productionEndDate = sdf.format(cal.getTime());
					}
				}
				sr.setKunnr(kunnr);
				sr.setProductionStartDate(productionStartDate);
				sr.setProductionEndDate(productionEndDate);
				sr.setFlag(flag);
				sr.setUserType(userType);
				matterNum = stockReportService.getStockReportKunnrAndKunagSum(sr);
				stockReport.setFlag(matterNum);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String updateStockReport(){
		stockReport = new StockReport();
		stockReport.setStockId(stockId);
		stockReport.setQuantity(quantity);
		total = stockReportService.updateStockReport(stockReport);
		return JSON;
	}
	
	public String createStockReport(){
		this.setSuccessMessage("操作成功！");
		stockReport.setUserId(Long.parseLong(this.getUser().getUserId()));
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51237");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				stockReport.setUserType("D");
				break;
			}
		}
		if(userType!=null){
			stockReport.setUserType(userType);
		}
		if(stockReport.getUserType()==null){
			stockReport.setUserType("A");
		}
		if(StringUtils.isEmpty(stockReport.getProductionDate())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			stockReport.setProductionDate(sdf.format(new Date()));
		}
		Long categoryId=stockReportService.getCategoryBySku(stockReport.getSkuId());
		stockReport.setCategoryId(categoryId);
		
		stockReportService.createStockReport(stockReport);
		
		return RESULT_MESSAGE;
	}
	
	public String deleteStock() {
		this.setSuccessMessage("操作成功！");
		String[] ids = deleteStockIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			stockReport = new StockReport();
			stockReport.setStockId(Integer.valueOf(ids[i]));
			stockReport.setStatus("D");
			total = stockReportService.updateStockReport(stockReport);
		}
		
		List<StockReport> list1=new ArrayList<StockReport>();
		StockReport s1=new StockReport();
		s1.setStockIds(ids);
		list1=stockReportService.getKunnrByStockIds(s1);
		if(list1.size()!=0){
			for(int i=0;i<list1.size();i++){
				Kunag kunag=new Kunag();
				kunag.setKunnr(list1.get(i).getKunnr());
				kunag.setStart(0);
				kunag.setEnd(99999);
				List<Kunag> kList=stockReportService.searchKunag(kunag);
				StockReport s2=new StockReport();
				if(kList!=null && kList.size()!=0 && StringUtils.isNotEmpty(kList.get(0).getKunag())){
    				s2.setKunag(kList.get(0).getKunag());
    			}else{
    				s2.setKunnr(list1.get(i).getKunnr());
    			}
				s2.setCheckTime(list1.get(i).getCheckTime());
				s2.setFlag(list1.get(i).getFlag());
				s2.setStatus("U");
				if(list1.get(i).getUserType().equals("C")){
					s2.setUserType("C");
					int num=stockReportService.getStockReportListCountTotal(s2);
					if(num==0){
						s2.setStatus("F");
						s2.setUserType("D");
						int num1=stockReportService.getStockReportListCountTotal(s2);
						if(num1==0){
							s2.setUserType("A");
							s2.setStatus("U");
							s2.setStatusBefore("F");
							stockReportService.updateStockReport(s2);
						}else{
							s2.setStatus("U");
							s2.setStatusBefore("F");
							stockReportService.updateStockReport(s2);
						}
					}
				}else if(list1.get(i).getUserType().equals("D")){
					s2.setUserType("D");
					int num=stockReportService.getStockReportListCountTotal(s2);
					if(num==0){
						s2.setUserType("A");
						s2.setStatus("U");
						s2.setStatusBefore("F");
						stockReportService.updateStockReport(s2);
					}
				}
			}
		}
		
		if (total==0) {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","productionDate",
			"categoryId","categoryName","quantity","unitDesc","flag","skuCode","brand","brandName" },total = "total")
	public String searchSalesReportList(){
		stockReport = new StockReport();
		
		//销售部下面的人员进行经销商客户关系权限控制
		String orgStrs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgStr = orgStrs.split(",");
		if(ArrayUtils.contains(orgStr,this.getUser().getOrgId())){
			String[] kunnrs={""};
			String isOffice=this.getUser().getIsOffice();
			if(StringUtils.isNotEmpty(isOffice)){
				List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
				if(kunnrStrs!=null && kunnrStrs.size()>0){
					kunnrs=(String[])kunnrStrs.toArray(new String[kunnrStrs.size()]);
				}
			}else{
				List<String> kunnrStrs=kunnrBusinessService.getKunnrIdByKunnrBusiness(this.getUser().getUserId());
				if(kunnrStrs!=null && kunnrStrs.size()>0){
					kunnrs=(String[])kunnrStrs.toArray(new String[kunnrStrs.size()]);
				}
			}
			if(kunnrs.length>0 && kunnrs[0]!=""){
				stockReport.setKunnrs(kunnrs);
			}
		}
		if(orgId==null){
			orgId=Long.parseLong(this.getUser().getOrgId());
		}
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setBrand(brand);
		stockReport.setFlag("sales_day");
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.getStockReportList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","categoryId","categoryName","quantity",
			"unitDesc","checkTime","flag","cg1","cg2","cg3","cg4","cg5","cg6","cg7","cg8","cg9","cg10",
			"cg11","cg12","cg13","cg14","cg15","cg16","cg17","cg18","cg19","cg20",
			"cg21","cg22","cg23","cg24","cg25","cg26","cg27","cg28","cg29","cg30"},total = "total")
	public String searchStockReportCGList(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setCategoryId(categoryId);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportByCGListCount(stockReport);
		if(total!=0){
			stockReportList = stockReportService.getStockReportByCGList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","categoryId","categoryName","quantity",
			"unitDesc","checkTime","flag","sku1","sku2","sku3","sku4","sku5","sku6","sku7","sku8","sku9","sku10",
			"sku11","sku12","sku13","sku14","sku15","sku16","sku17","sku18","sku19","sku20",
			"sku21","sku22","sku23","sku24","sku25","sku26","sku27","sku28","sku29","sku30",
			"sku31","sku32","sku33","sku34","sku35","sku36","sku37","sku38","sku39","sku40",
			"sku41","sku42","sku43","sku44","sku45","sku46","sku47","sku48","sku49","sku50",
			"sku51","sku52","sku53","sku54","sku55","sku56","sku57","sku58","sku59","sku60"},total = "total")
	public String searchStockReportSkuList(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportBySkuListCount(stockReport);
		if(total!=0){
			stockReportList = stockReportService.getStockReportBySkuList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","categoryId","categoryName","quantity",
			"unitDesc","flag","sku1","sku2","sku3","sku4","sku5","sku6","sku7","sku8","sku9","sku10",
			"sku11","sku12","sku13","sku14","sku15","sku16","sku17","sku18","sku19","sku20","checkTime"},total = "total")
	public String searchStockReportSalesList(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportBySalesListCount(stockReport);
		if(total!=0){
			stockReportList = stockReportService.getStockReportBySalesList(stockReport);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","categoryId","categoryName","quantity","checkTime",
			"unitDesc","flag","cg1","cg2","cg3","cg4","cg5","cg6","cg7","cg8","cg9","cg10",
			"cg11","cg12","cg13","cg14","cg15","cg16","cg17","cg18","cg19","cg20",
			"pod1","pod2","pod3","pod4","pod5","pod6","pod7","pod8","pod9","pod10",
			"pod11","pod12","pod13","pod14","pod15","pod16","pod17","pod18","pod19","pod20",
			"lastStock1","lastStock2","lastStock3","lastStock4","lastStock5","lastStock6","lastStock7","lastStock8","lastStock9","lastStock10",
			"lastStock11","lastStock12","lastStock13","lastStock14","lastStock15","lastStock16","lastStock17","lastStock18","lastStock19","lastStock20",
			"total1","total2","total3","total4","total5","total6","total7","total8","total9","total10",
			"total11","total12","total13","total14","total15","total16","total17","total18","total19","total20"},total = "total")
	public String searchStockReportSalesByCGList(){
		if(productionDate == null || productionDate.equals("")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			productionDate = sdf.format(new Date());
		}
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setProductionDate(productionDate);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.getStockReportSalesByCGListCount(stockReport);
		if(total!=0){
			stockReportList = stockReportService.getStockReportSalesByCGList(stockReport);
		}
		return JSON;
	}
	
	public void exportForExcelStockTotal() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		String[] kunnrs;
		stockReport = new StockReport();
		if(kunnr==null || "".equals(kunnr)){
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null || kunnrTmp.size()==0){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			if(kunnrTmp!=null && kunnrTmp.size()!=0){
				if(kunnrTmp.size()==1){
					kunnr=kunnrTmp.get(0);
				}else{
					kunnrs=new String[kunnrTmp.size()];
					for(int i=0;i<kunnrTmp.size();i++){
						kunnrs[i]=kunnrTmp.get(i);
					}
					stockReport.setKunnrs(kunnrs);
				}
			}
		}
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(0);
		stockReport.setEnd(999999);
		stockReportList = stockReportService.getStockTotalList(stockReport);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "库存信息.xls";
			if("sales_day".equals(flag)){
				fileName = "分销量信息.xls";
			}
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 30);
			wsheet.setColumnView(10, 30);
			wsheet.setColumnView(11, 30);
			wsheet.setColumnView(12, 10);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
			wsheet.addCell(new Label(5, 0, "提报人"));
            wsheet.addCell(new Label(6, 0, "数量"));
			wsheet.addCell(new Label(7, 0, "单位"));
			if(!"sales_day".equals(flag)){
				wsheet.addCell(new Label(8, 0, "库存所属日期"));
			}else{
				wsheet.addCell(new Label(8, 0, "分销量所属日期"));
			}
			wsheet.addCell(new Label(9, 0, "提报日期"));
			wsheet.addCell(new Label(10, 0, "品类"));
			wsheet.addCell(new Label(11, 0, "品项"));
			wsheet.addCell(new Label(12, 0, "货龄"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockReportList.size(); i++) {
				StockReport s =stockReportList.get(i-1);
				if(StringUtils.isNotEmpty(s.getProductionDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=sdf.parse(s.getProductionDate());
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.MONTH, 1);
					cal.add(Calendar.DATE, -1);
					date=cal.getTime();
					Date date1=sdf1.parse(s.getCreateDate().substring(0, s.getCreateDate().length()-2));
					long diff = date1.getTime() - date.getTime();
					double months = ((double)diff) / (1000 * 60 * 60 * 24);
					if(months>=370){
						stockReportList.get(i-1).setProductionDate("已过期");
					}
				}
				
				if(stockReportList.get(i-1).getQuantity()==null){
					stockReportList.get(i-1).setQuantity(0.0);
				}
				wsheet.addCell(new Label(0,i,stockReportList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockReportList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockReportList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockReportList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockReportList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockReportList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Number(6,i,stockReportList.get(i-1).getQuantity(),wcfF));
				wsheet.addCell(new Label(7,i,stockReportList.get(i-1).getUnitDesc(),wcfF));
				wsheet.addCell(new Label(8,i,stockReportList.get(i-1).getCheckTime(),wcfF));
				wsheet.addCell(new Label(9,i,stockReportList.get(i-1).getCreateDate(),wcfF));
				wsheet.addCell(new Label(10,i,stockReportList.get(i-1).getCategoryName(),wcfF));
				wsheet.addCell(new Label(11,i,stockReportList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Label(12,i,stockReportList.get(i-1).getProductionDate(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportForExcelSku(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(0);
		stockReport.setEnd(10000);
		stockReportList = stockReportService.getStockReportBySkuList(stockReport);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "库存报表.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 20);
			wsheet.setColumnView(8, 20);
			wsheet.setColumnView(9, 20);
			wsheet.setColumnView(10, 20);
			wsheet.setColumnView(11, 20);
            wsheet.setColumnView(12, 20);
			wsheet.setColumnView(13, 20);
			wsheet.setColumnView(14, 20);
			wsheet.setColumnView(15, 20);
			wsheet.setColumnView(16, 20);
			wsheet.setColumnView(17, 20);
			wsheet.setColumnView(18, 20);
			wsheet.setColumnView(19, 20);
			wsheet.setColumnView(20, 20);
			wsheet.setColumnView(21, 20);
			wsheet.setColumnView(22, 20);
			wsheet.setColumnView(23, 20);
			wsheet.setColumnView(24, 20);
			wsheet.setColumnView(25, 20);
			wsheet.setColumnView(26, 20);
			wsheet.setColumnView(27, 20);
			wsheet.setColumnView(28, 20);
			wsheet.setColumnView(29, 20);
			wsheet.setColumnView(30, 20);
			wsheet.setColumnView(31, 20);
			wsheet.setColumnView(32, 20);
			wsheet.setColumnView(33, 20);
			wsheet.setColumnView(34, 20);
			wsheet.setColumnView(35, 20);
			wsheet.setColumnView(36, 20);
			wsheet.setColumnView(37, 20);
			wsheet.setColumnView(38, 20);
			wsheet.setColumnView(39, 20);
			wsheet.setColumnView(40, 20);
			wsheet.setColumnView(41, 20);
			wsheet.setColumnView(42, 20);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
            wsheet.addCell(new Label(5, 0, "所属日期"));//
			wsheet.addCell(new Label(6, 0, "单位"));
			wsheet.addCell(new Label(7, 0, "椰果原味奶茶1*30"));
			wsheet.addCell(new Label(8, 0, "椰果香芋奶茶1*30"));
			wsheet.addCell(new Label(9, 0, "椰果麦香奶茶1*30"));
			wsheet.addCell(new Label(10, 0, "椰果草莓奶茶1*30 "));
			wsheet.addCell(new Label(11, 0, "椰果巧克力奶茶1*30"));
			wsheet.addCell(new Label(12, 0, "椰果咖啡奶茶1*30"));
			wsheet.addCell(new Label(13, 0, "椰果绿茶奶茶1*30"));
			wsheet.addCell(new Label(14, 0, "经典组合装奶茶(手提)1*30"));
			wsheet.addCell(new Label(15, 0, "椰果家庭分享装1*96"));
			wsheet.addCell(new Label(16, 0, "椰果家庭分享装1*90"));
			wsheet.addCell(new Label(17, 0, "奶茶礼盒大箱1*8"));
			wsheet.addCell(new Label(18, 0, "红豆奶茶1*30"));
			wsheet.addCell(new Label(19, 0, "红豆家庭分享装1*96"));
			wsheet.addCell(new Label(20, 0, "桂圆红枣奶茶1*30"));
			wsheet.addCell(new Label(21, 0, "15杯红豆家庭装"));
			wsheet.addCell(new Label(22, 0, "功夫奶茶-400ML*15"));
			wsheet.addCell(new Label(23, 0, "三连杯椰果原味"));
			wsheet.addCell(new Label(24, 0, "三连杯椰果香芋"));
			wsheet.addCell(new Label(25, 0, "三连杯红豆"));
			wsheet.addCell(new Label(26, 0, "三连杯桂圆红枣"));
			wsheet.addCell(new Label(27, 0, "红豆、桂圆红枣家庭分享装1*90"));
			wsheet.addCell(new Label(28, 0, "红豆、桂圆红枣家庭分享装1*96'"));
			wsheet.addCell(new Label(29, 0, "红豆、椰果经典组合装1*30"));
			wsheet.addCell(new Label(30, 0, "椰果原味六连杯奶茶1*36"));
			wsheet.addCell(new Label(31, 0, "椰果香芋六连杯奶茶1*36"));
			wsheet.addCell(new Label(32, 0, "椰果巧克力六连杯奶茶1*36"));
			wsheet.addCell(new Label(33, 0, "超值组合装1*30"));
			wsheet.addCell(new Label(34, 0, "奶茶礼盒大箱1*12"));
			wsheet.addCell(new Label(35, 0, "豪华礼盒装6*12"));
			wsheet.addCell(new Label(36, 0, "豪华礼盒装6*15"));
			wsheet.addCell(new Label(37, 0, "芝士燕麦奶茶1*30"));
			wsheet.addCell(new Label(38, 0, "黑米椰浆奶茶1*30"));
			wsheet.addCell(new Label(39, 0, "焦糖仙草奶茶1*30"));
			wsheet.addCell(new Label(40, 0, "芝士燕麦三连杯奶茶10*3"));
			wsheet.addCell(new Label(41, 0, "黑米椰浆三连杯奶茶10*3"));
			wsheet.addCell(new Label(42, 0, "焦糖仙草三连杯奶茶10*3"));
			
			
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockReportList.size(); i++) {
				if(stockReportList.get(i-1).getOrgRegion()==null){
					stockReportList.get(i-1).setOrgRegion("");
				}
				if(stockReportList.get(i-1).getOrgProvince()==null){
					stockReportList.get(i-1).setOrgProvince("");
				}
				if(stockReportList.get(i-1).getOrgCity()==null){
					stockReportList.get(i-1).setOrgCity("");
				}
				if(stockReportList.get(i-1).getKunnr()==null){
					stockReportList.get(i-1).setKunnr("");
				}
				if(stockReportList.get(i-1).getKunnrName()==null){
					stockReportList.get(i-1).setKunnrName("");
				}
				if(stockReportList.get(i-1).getSku1()==null){
					stockReportList.get(i-1).setSku1(0.0);
				}
				if(stockReportList.get(i-1).getSku2()==null){
					stockReportList.get(i-1).setSku2(0.0);
				}
				if(stockReportList.get(i-1).getSku3()==null){
					stockReportList.get(i-1).setSku3(0.0);
				}
				if(stockReportList.get(i-1).getSku4()==null){
					stockReportList.get(i-1).setSku4(0.0);
				}
				if(stockReportList.get(i-1).getSku5()==null){
					stockReportList.get(i-1).setSku5(0.0);
				}
				if(stockReportList.get(i-1).getSku6()==null){
					stockReportList.get(i-1).setSku6(0.0);
				}
				if(stockReportList.get(i-1).getSku7()==null){
					stockReportList.get(i-1).setSku7(0.0);
				}
				if(stockReportList.get(i-1).getSku8()==null){
					stockReportList.get(i-1).setSku8(0.0);
				}
				if(stockReportList.get(i-1).getSku9()==null){
					stockReportList.get(i-1).setSku9(0.0);
				}
				if(stockReportList.get(i-1).getSku10()==null){
					stockReportList.get(i-1).setSku10(0.0);
				}
				if(stockReportList.get(i-1).getSku11()==null){
					stockReportList.get(i-1).setSku11(0.0);
				}
				if(stockReportList.get(i-1).getSku12()==null){
					stockReportList.get(i-1).setSku12(0.0);
				}
				if(stockReportList.get(i-1).getSku13()==null){
					stockReportList.get(i-1).setSku13(0.0);
				}
				if(stockReportList.get(i-1).getSku14()==null){
					stockReportList.get(i-1).setSku14(0.0);
				}
				if(stockReportList.get(i-1).getSku15()==null){
					stockReportList.get(i-1).setSku15(0.0);
				}
				if(stockReportList.get(i-1).getSku16()==null){
					stockReportList.get(i-1).setSku16(0.0);
				}
				if(stockReportList.get(i-1).getSku17()==null){
					stockReportList.get(i-1).setSku17(0.0);
				}
				if(stockReportList.get(i-1).getSku18()==null){
					stockReportList.get(i-1).setSku18(0.0);
				}
				if(stockReportList.get(i-1).getSku19()==null){
					stockReportList.get(i-1).setSku19(0.0);
				}
				if(stockReportList.get(i-1).getSku20()==null){
					stockReportList.get(i-1).setSku20(0.0);
				}
				if(stockReportList.get(i-1).getSku21()==null){
					stockReportList.get(i-1).setSku21(0.0);
				}
				if(stockReportList.get(i-1).getSku22()==null){
					stockReportList.get(i-1).setSku22(0.0);
				}
				if(stockReportList.get(i-1).getSku23()==null){
					stockReportList.get(i-1).setSku23(0.0);
				}
				if(stockReportList.get(i-1).getSku24()==null){
					stockReportList.get(i-1).setSku24(0.0);
				}
				if(stockReportList.get(i-1).getSku25()==null){
					stockReportList.get(i-1).setSku25(0.0);
				}
				if(stockReportList.get(i-1).getSku26()==null){
					stockReportList.get(i-1).setSku26(0.0);
				}
				if(stockReportList.get(i-1).getSku27()==null){
					stockReportList.get(i-1).setSku27(0.0);
				}
				if(stockReportList.get(i-1).getSku28()==null){
					stockReportList.get(i-1).setSku28(0.0);
				}
				if(stockReportList.get(i-1).getSku29()==null){
					stockReportList.get(i-1).setSku29(0.0);
				}
				if(stockReportList.get(i-1).getSku30()==null){
					stockReportList.get(i-1).setSku30(0.0);
				}
				if(stockReportList.get(i-1).getSku31()==null){
					stockReportList.get(i-1).setSku31(0.0);
				}
				if(stockReportList.get(i-1).getSku32()==null){
					stockReportList.get(i-1).setSku32(0.0);
				}
				if(stockReportList.get(i-1).getSku33()==null){
					stockReportList.get(i-1).setSku33(0.0);
				}
				if(stockReportList.get(i-1).getSku34()==null){
					stockReportList.get(i-1).setSku34(0.0);
				}
				if(stockReportList.get(i-1).getSku35()==null){
					stockReportList.get(i-1).setSku35(0.0);
				}
				if(stockReportList.get(i-1).getSku36()==null){
					stockReportList.get(i-1).setSku36(0.0);
				}
				wsheet.addCell(new Label(0,i,stockReportList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockReportList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockReportList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockReportList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockReportList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockReportList.get(i-1).getCheckTime(),wcfF));
				wsheet.addCell(new Label(6,i,stockReportList.get(i-1).getUnitDesc(),wcfF));
				wsheet.addCell(new Number(7,i,stockReportList.get(i-1).getSku1(),wcfF));
				wsheet.addCell(new Number(8,i,stockReportList.get(i-1).getSku2(),wcfF));
				wsheet.addCell(new Number(9,i,stockReportList.get(i-1).getSku3(),wcfF));
				wsheet.addCell(new Number(10,i,stockReportList.get(i-1).getSku4(),wcfF));
				wsheet.addCell(new Number(11,i,stockReportList.get(i-1).getSku5(),wcfF));
				wsheet.addCell(new Number(12,i,stockReportList.get(i-1).getSku6(),wcfF));
				wsheet.addCell(new Number(13,i,stockReportList.get(i-1).getSku7(),wcfF));
				wsheet.addCell(new Number(14,i,stockReportList.get(i-1).getSku8(),wcfF));
				wsheet.addCell(new Number(15,i,stockReportList.get(i-1).getSku9(),wcfF));
				wsheet.addCell(new Number(16,i,stockReportList.get(i-1).getSku10(),wcfF));
				wsheet.addCell(new Number(17,i,stockReportList.get(i-1).getSku11(),wcfF));
				wsheet.addCell(new Number(18,i,stockReportList.get(i-1).getSku12(),wcfF));
				wsheet.addCell(new Number(19,i,stockReportList.get(i-1).getSku13(),wcfF));
				wsheet.addCell(new Number(20,i,stockReportList.get(i-1).getSku14(),wcfF));
				wsheet.addCell(new Number(21,i,stockReportList.get(i-1).getSku15(),wcfF));
				wsheet.addCell(new Number(22,i,stockReportList.get(i-1).getSku16(),wcfF));
				wsheet.addCell(new Number(23,i,stockReportList.get(i-1).getSku17(),wcfF));
				wsheet.addCell(new Number(24,i,stockReportList.get(i-1).getSku18(),wcfF));
				wsheet.addCell(new Number(25,i,stockReportList.get(i-1).getSku19(),wcfF));
				wsheet.addCell(new Number(26,i,stockReportList.get(i-1).getSku20(),wcfF));
				wsheet.addCell(new Number(27,i,stockReportList.get(i-1).getSku21(),wcfF));
				wsheet.addCell(new Number(28,i,stockReportList.get(i-1).getSku22(),wcfF));
				wsheet.addCell(new Number(29,i,stockReportList.get(i-1).getSku23(),wcfF));
				wsheet.addCell(new Number(30,i,stockReportList.get(i-1).getSku24(),wcfF));
				wsheet.addCell(new Number(31,i,stockReportList.get(i-1).getSku25(),wcfF));
				wsheet.addCell(new Number(32,i,stockReportList.get(i-1).getSku26(),wcfF));
				wsheet.addCell(new Number(33,i,stockReportList.get(i-1).getSku27(),wcfF));
				wsheet.addCell(new Number(34,i,stockReportList.get(i-1).getSku28(),wcfF));
				wsheet.addCell(new Number(35,i,stockReportList.get(i-1).getSku29(),wcfF));
				wsheet.addCell(new Number(36,i,stockReportList.get(i-1).getSku30(),wcfF));
				wsheet.addCell(new Number(37,i,stockReportList.get(i-1).getSku31(),wcfF));
				wsheet.addCell(new Number(38,i,stockReportList.get(i-1).getSku32(),wcfF));
				wsheet.addCell(new Number(39,i,stockReportList.get(i-1).getSku33(),wcfF));
				wsheet.addCell(new Number(40,i,stockReportList.get(i-1).getSku34(),wcfF));
				wsheet.addCell(new Number(41,i,stockReportList.get(i-1).getSku35(),wcfF));
				wsheet.addCell(new Number(42,i,stockReportList.get(i-1).getSku36(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportForExcelCG(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(0);
		stockReport.setEnd(10000);
		stockReportList = stockReportService.getStockReportByCGList(stockReport);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "库存报表.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 20);
			wsheet.setColumnView(8, 20);
			wsheet.setColumnView(9, 20);
			wsheet.setColumnView(10, 20);
			wsheet.setColumnView(11, 20);
            wsheet.setColumnView(12, 20);
			wsheet.setColumnView(13, 20);
			wsheet.setColumnView(14, 20);
			wsheet.setColumnView(15, 20);
			wsheet.setColumnView(16, 20);
			wsheet.setColumnView(17, 20);
			wsheet.setColumnView(18, 20);
			wsheet.setColumnView(19, 20);
			wsheet.setColumnView(20, 20);
			wsheet.setColumnView(21, 20);
			wsheet.setColumnView(22, 20);
			wsheet.setColumnView(23, 20);
			wsheet.setColumnView(24, 20);
			wsheet.setColumnView(25, 20);
			wsheet.setColumnView(26, 20);
			wsheet.setColumnView(27, 20);
			wsheet.setColumnView(28, 20);
			wsheet.setColumnView(29, 20);
			wsheet.setColumnView(30, 20);
			wsheet.setColumnView(31, 20);
			wsheet.setColumnView(32, 20);
			wsheet.setColumnView(33, 20);
			wsheet.setColumnView(34, 20);
			wsheet.setColumnView(35, 20);
			wsheet.setColumnView(36, 20);
			wsheet.setColumnView(37, 20);
			wsheet.setColumnView(38, 20);
			wsheet.setColumnView(39, 20);
			wsheet.setColumnView(40, 20);
			wsheet.setColumnView(41, 20);
			wsheet.setColumnView(42, 20);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
            wsheet.addCell(new Label(5, 0, "所属日期"));//
			wsheet.addCell(new Label(6, 0, "单位"));
			wsheet.addCell(new Label(7, 0, "椰果单杯-30"));
			wsheet.addCell(new Label(8, 0, "椰果联杯装-6*6"));
			wsheet.addCell(new Label(9, 0, "椰果经典装-30"));
			wsheet.addCell(new Label(10, 0, "椰果礼盒装-12"));
			wsheet.addCell(new Label(11, 0, "椰果礼盒装-10"));
			wsheet.addCell(new Label(12, 0, "椰果家庭分享装-16"));
			wsheet.addCell(new Label(13, 0, "椰果家庭分享装-15"));
			wsheet.addCell(new Label(14, 0, "椰果联杯装-4*10"));
			wsheet.addCell(new Label(15, 0, "红豆单杯-30"));
			wsheet.addCell(new Label(16, 0, "红豆、椰果经典装-30"));
			wsheet.addCell(new Label(17, 0, "红豆家庭分享装-16"));
			wsheet.addCell(new Label(18, 0, "红豆联杯装-3*10"));
			wsheet.addCell(new Label(19, 0, "桂圆红枣单杯-30"));
			wsheet.addCell(new Label(20, 0, "红豆、桂圆红枣分享装-16"));
			wsheet.addCell(new Label(21, 0, "红豆、桂圆红枣分享装-15"));
			wsheet.addCell(new Label(22, 0, "桂圆红枣联杯装-3*10"));
			wsheet.addCell(new Label(23, 0, "豪华礼盒装15*6"));
			wsheet.addCell(new Label(24, 0, "超值组合装"));
			wsheet.addCell(new Label(25, 0, "豪华礼盒装6*12"));
			wsheet.addCell(new Label(26, 0, "功夫奶茶单瓶"));
			
			
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockReportList.size(); i++) {
				if(stockReportList.get(i-1).getOrgRegion()==null){
					stockReportList.get(i-1).setOrgRegion("");
				}
				if(stockReportList.get(i-1).getOrgProvince()==null){
					stockReportList.get(i-1).setOrgProvince("");
				}
				if(stockReportList.get(i-1).getOrgCity()==null){
					stockReportList.get(i-1).setOrgCity("");
				}
				if(stockReportList.get(i-1).getKunnr()==null){
					stockReportList.get(i-1).setKunnr("");
				}
				if(stockReportList.get(i-1).getKunnrName()==null){
					stockReportList.get(i-1).setKunnrName("");
				}
				if(stockReportList.get(i-1).getCg1()==null){
					stockReportList.get(i-1).setCg1(0.0);
				}
				if(stockReportList.get(i-1).getCg2()==null){
					stockReportList.get(i-1).setCg2(0.0);
				}
				if(stockReportList.get(i-1).getCg3()==null){
					stockReportList.get(i-1).setCg3(0.0);
				}
				if(stockReportList.get(i-1).getCg4()==null){
					stockReportList.get(i-1).setCg4(0.0);
				}
				if(stockReportList.get(i-1).getCg5()==null){
					stockReportList.get(i-1).setCg5(0.0);
				}
				if(stockReportList.get(i-1).getCg6()==null){
					stockReportList.get(i-1).setCg6(0.0);
				}
				if(stockReportList.get(i-1).getCg7()==null){
					stockReportList.get(i-1).setCg7(0.0);
				}
				if(stockReportList.get(i-1).getCg8()==null){
					stockReportList.get(i-1).setCg8(0.0);
				}
				if(stockReportList.get(i-1).getCg9()==null){
					stockReportList.get(i-1).setCg9(0.0);
				}
				if(stockReportList.get(i-1).getCg10()==null){
					stockReportList.get(i-1).setCg10(0.0);
				}
				if(stockReportList.get(i-1).getCg11()==null){
					stockReportList.get(i-1).setCg11(0.0);
				}
				if(stockReportList.get(i-1).getCg12()==null){
					stockReportList.get(i-1).setCg12(0.0);
				}
				if(stockReportList.get(i-1).getCg13()==null){
					stockReportList.get(i-1).setCg13(0.0);
				}
				if(stockReportList.get(i-1).getCg14()==null){
					stockReportList.get(i-1).setCg14(0.0);
				}
				if(stockReportList.get(i-1).getCg15()==null){
					stockReportList.get(i-1).setCg15(0.0);
				}
				if(stockReportList.get(i-1).getCg16()==null){
					stockReportList.get(i-1).setCg16(0.0);
				}
				if(stockReportList.get(i-1).getCg17()==null){
					stockReportList.get(i-1).setCg17(0.0);
				}
				if(stockReportList.get(i-1).getCg18()==null){
					stockReportList.get(i-1).setCg18(0.0);
				}
				if(stockReportList.get(i-1).getCg19()==null){
					stockReportList.get(i-1).setCg19(0.0);
				}
				if(stockReportList.get(i-1).getCg20()==null){
					stockReportList.get(i-1).setCg20(0.0);
				}
				wsheet.addCell(new Label(0,i,stockReportList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockReportList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockReportList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockReportList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockReportList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockReportList.get(i-1).getCheckTime(),wcfF));
				wsheet.addCell(new Label(6,i,stockReportList.get(i-1).getUnitDesc(),wcfF));
				wsheet.addCell(new Number(7,i,stockReportList.get(i-1).getCg1(),wcfF));
				wsheet.addCell(new Number(8,i,stockReportList.get(i-1).getCg2(),wcfF));
				wsheet.addCell(new Number(9,i,stockReportList.get(i-1).getCg3(),wcfF));
				wsheet.addCell(new Number(10,i,stockReportList.get(i-1).getCg4(),wcfF));
				wsheet.addCell(new Number(11,i,stockReportList.get(i-1).getCg5(),wcfF));
				wsheet.addCell(new Number(12,i,stockReportList.get(i-1).getCg6(),wcfF));
				wsheet.addCell(new Number(13,i,stockReportList.get(i-1).getCg7(),wcfF));
				wsheet.addCell(new Number(14,i,stockReportList.get(i-1).getCg8(),wcfF));
				wsheet.addCell(new Number(15,i,stockReportList.get(i-1).getCg9(),wcfF));
				wsheet.addCell(new Number(16,i,stockReportList.get(i-1).getCg10(),wcfF));
				wsheet.addCell(new Number(17,i,stockReportList.get(i-1).getCg11(),wcfF));
				wsheet.addCell(new Number(18,i,stockReportList.get(i-1).getCg12(),wcfF));
				wsheet.addCell(new Number(19,i,stockReportList.get(i-1).getCg13(),wcfF));
				wsheet.addCell(new Number(20,i,stockReportList.get(i-1).getCg14(),wcfF));
				wsheet.addCell(new Number(21,i,stockReportList.get(i-1).getCg15(),wcfF));
				wsheet.addCell(new Number(22,i,stockReportList.get(i-1).getCg16(),wcfF));
				wsheet.addCell(new Number(23,i,stockReportList.get(i-1).getCg17(),wcfF));
				wsheet.addCell(new Number(24,i,stockReportList.get(i-1).getCg18(),wcfF));
				wsheet.addCell(new Number(25,i,stockReportList.get(i-1).getCg19(),wcfF));
				wsheet.addCell(new Number(26,i,stockReportList.get(i-1).getCg20(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	@PermissionSearch
	@JsonResult(field = "stockDateList",include = { "id","checkTime","startDateStr",
			"endDateStr","modifyDate"})
	public String searchStockDateList(){
		stockDateList = new ArrayList<StockDate>();
		stockDate = new StockDate();
		stockDateList = stockReportService.getStockDate(stockDate);
		return JSON;
	}

	public String updateStockDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(checkTimes!=null && checkTimes.length!=0){
			for(int i=0;i<checkTimes.length;i++){
				String checkTime="";
				if(checkTimes[i]!=null){
					checkTime=sdf.format(checkTimes[i]);
				}
				stockDate = new StockDate();
				stockDate.setId(i+1);
				stockDate.setCheckTime(checkTime);
				stockDate.setStartDate(startDates[i]);
				stockDate.setEndDate(endDates[i]);
				total = stockReportService.updateStockDate(stockDate);
			}
		}
		this.setSuccessMessage("修改成功!");
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrInTypeList",include = { "type","kunnr","kunnrName",
			"orgName","isNotice","kunnrStartDate","telNumber","faxNumber","mobNumber"},total="total")
	public String searchKunnrInTypeList(){
		kunnrInTypeList = new ArrayList<KunnrInType>();
		kunnrInType = new KunnrInType();
		kunnrInType.setOrgId(orgId);
		kunnrInType.setKunnr(kunnr);
		kunnrInType.setIsNotice(isNotice);
		kunnrInType.setStartDate(kunnrStartDate);
		kunnrInType.setEndDate(kunnrEndDate);
		kunnrInType.setStart(getStart());
		kunnrInType.setEnd(getEnd());
		total=stockReportService.getKunnrInTypeCount(kunnrInType);
		if(total>0){
			kunnrInTypeList = stockReportService.getKunnrInType(kunnrInType);
		}
		return JSON;
	}
	@PermissionSearch
	@JsonResult(field = "total")
	public String updateKunnrInType(){
		kunnrInType = new KunnrInType();
		kunnrInType.setType(type);
		kunnrInType.setKunnr(kunnr);
		kunnrInType.setKunnrName(kunnrName);
		kunnrInType.setIsNotice(isNotice);
		total = stockReportService.updateKunnrInType(kunnrInType);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "goalList",include = { "orgRegion","orgProvince","orgCity","kunnr","orgName",
			"kunnrName","goalYear","goalMonth","userId","createDate","firstGoal","secondGoal","thirdGoal",
			"countGoal","goalType","cgA1","cgA2","cgA3","cgA4","cgA5","cgA6","cgA7","cgA8",
			"cgB1","cgB3","cgB6","cgB8","cgC1","cgC6","cgC7","cgC8",
			"cgD10","cgD9","cgE1","cgH7","cgF1","cgF8","cgG1","cgG8","cgJ1","cgJ8","cgK1","cgK8","cgD3"},total="total")
	public String searchSalesGoalCGList(){
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		goal.setKunnr(kunnr);
		goal.setGoalType(goalType);
		goal.setStart(getStart());
		goal.setEnd(getEnd());
		total=stockReportService.searchSalesGoalCGListCount(goal);
		if(total>0){
			goalList = stockReportService.searchSalesGoalCGList(goal);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "goalList",include = {"kunnr","orgName","orgId","matterName","userName",
			"kunnrName","goalYear","goalMonth","userId","createDate","boxNum","goalType","brand",
			"boxNumD","matterNum","goalMark"},total="total")
	public String searchSalesGoalCGDetailList(){
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setFlag(flag);
		goal.setKunnr(kunnr);
		goal.setGoalType("A1");
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setGoalMark(goalMark);
		goal.setMatterNum(matterNum);
		goal.setStart(getStart());
		goal.setEnd(getEnd());
		if(StringUtils.isNotEmpty(brand)){
			goal.setBrand(brand);
		}
		total=stockReportService.searchSalesGoalCGDetailListCount(goal);
		if(total>0){
			goalList = stockReportService.searchSalesGoalCGDetailList(goal);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "matterNum")
	public String searchSalesGoalCGDetailListSum(){
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setFlag(flag);
		goal.setKunnr(kunnr);
		goal.setGoalType("A1");
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setGoalMark(goalMark);
		goal.setMatterNum(matterNum);
		goal.setBrand(brand);
		matterNum=stockReportService.searchSalesGoalCGDetailListSum(goal);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String updateGoalCGDetail(){
		goal = new Goal();
		goal.setKunnr(kunnr);
		goal.setGoalMark(goalMark);
		goal.setMatterNum(matterNum);
		goal.setGoalYear(year);
		goal.setGoalMonth(month);
		goal.setBoxNum(quantity);
		goal.setOrgId(orgId+"");
		total=stockReportService.updateGoalCGDetail(goal);
		return JSON;
	}
	/**
	 * MethodsTitle: 批量删除
	 * @author: xg.chen
	 * @date:2016年12月27日 上午10:09:26
	 * @version 1.1	添加操作人和操作时间
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "total")
	public String deleteGoalCGDetail(){
		Gson gson = new Gson();
 		if(update!=null){
			List<Goal> deleteList = gson.fromJson(update, new TypeToken<List<Goal>>(){}.getType());
			for(Goal s:deleteList){
				if(StringUtils.isNotEmpty(s.getOrgId()) && StringUtils.isNotEmpty(s.getMatterNum()) 
						&& StringUtils.isNotEmpty(s.getGoalMark()) && StringUtils.isNotEmpty(s.getGoalYear()) 
						&& StringUtils.isNotEmpty(s.getGoalMonth())){
					goal = new Goal();
					goal.setOrgId(s.getOrgId());
					goal.setKunnr(s.getKunnr());
					goal.setGoalMark(s.getGoalMark());
					goal.setMatterNum(s.getMatterNum());
					goal.setGoalYear(s.getGoalYear());
					goal.setGoalMonth(s.getGoalMonth());
					if(!this.getUser().getUserId().equals("88647")){
						goal.setUserId(this.getUser().getUserId());
					}
					total=stockReportService.deleteGoalCGDetail(goal);
					//在更新删除状态的时候没有添加操作人，而且在测试机中没有88647 admin 用户创建的数据
					//因而在测试和正式数据库中也存在这样的问题，所以在删除完成以后再将操作用户更这条数据上
					//这样做有点多余，而且读写频繁，建议后期删除
					if(total>0){
						Goal goal1=new Goal();
						goal1.setOrgId(s.getOrgId());
						goal1.setKunnr(s.getKunnr());
						goal1.setGoalMark(s.getGoalMark());
						goal1.setMatterNum(s.getMatterNum());
						goal1.setGoalYear(s.getGoalYear());
						goal1.setGoalMonth(s.getGoalMonth());
						goal1.setUserId(this.getUser().getUserId());
						stockReportService.updateOperationUser(goal1);
					}
				}
				total++;
			}
 		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "goalList",include = {"kunnr","orgName","orgId","matterName","userName",
			"kunnrName","goalYear","goalMonth","userId","createDate","boxNum","goalType",
			"boxNumD","matterNum"},total="total")
	public String searchSalesGoalChallengeList(){
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setFlag(flag);
		goal.setKunnr(kunnr);
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setMatterNum(matterNum);
		goal.setStart(getStart());
		goal.setEnd(getEnd());
		total=stockReportService.searchSalesGoalChallengeListCount(goal);
		if(total>0){
			goalList = stockReportService.searchSalesGoalChallengeList(goal);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "matterNum")
	public String searchSalesGoalChallengeListSum(){
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setFlag(flag);
		goal.setKunnr(kunnr);
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setMatterNum(matterNum);
		matterNum=stockReportService.searchSalesGoalChallengeListSum(goal);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String updateGoalChallenge(){
		goal = new Goal();
		goal.setKunnr(kunnr);
		goal.setMatterNum(matterNum);
		goal.setGoalYear(year);
		goal.setGoalMonth(month);
		goal.setBoxNum(quantity);
		goal.setOrgId(orgId+"");
		total=stockReportService.updateGoalChallenge(goal);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockManagerList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","year","month","skuName","lastStock","onWayNum","salesDayAvg","salesDayNum",
			"stockStatus","salesGoalA1","nextSalesGoalA1","stockSafe","outPutNotNum","outPutNotCheck","stockPod",
			"outPutNeeds","takeOrderNeedNum","stockSafe1","stockNotSafe","stockSafeFlag","stockSafeExpect",
			"stockNotSafeExpect","stockRealExpect"},total="total")
	public String searchStockManagerList(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if(StringUtils.isEmpty(checkTime)){
			checkTime = sdf.format(new Date());
			stockDate=new StockDate();
			stockDate.setModifyDate(new Date());
			checkTime=stockReportService.getLastStockDate(stockDate);
		}
		
		if(StringUtils.isEmpty(productionDate)){
			productionDate=sdf.format(new Date());
		}
		
		stockManager = new StockManager();
		stockManagerList = new ArrayList<StockManager>();
		stockManager.setKunnr(kunnr);
		stockManager.setCheckTime(checkTime);
		stockManager.setOrgId(orgId);
		stockManager.setSkuId(skuId);
		stockManager.setDateNow(productionDate);
		stockManager.setYear(Integer.parseInt(sdf.format(new Date()).substring(0, 4)));
		stockManager.setMonth(Integer.parseInt(sdf.format(new Date()).substring(5, 7)));
		stockManager.setStart(getStart());
		stockManager.setEnd(getEnd());
		total=stockReportService.searchStockManagerCount(stockManager);
		if(total>0){
			stockManagerList = stockReportService.searchStockManagerList(stockManager);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockManagerList",include = { "orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","year","month","skuName","lastStock","onWayNum","salesDayAvg","salesDayNum",
			"stockStatus","salesGoalA1","nextSalesGoalA1","stockSafe","outPutNotNum","outPutNotCheck","stockPod",
			"outPutNeeds","takeOrderNeedNum","stockSafe1","stockNotSafe","stockSafeFlag","stockSafeExpect",
			"stockNotSafeExpect","stockRealExpect"},total="total")
	public String searchStockManagerReportList(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if(StringUtils.isEmpty(checkTime)){
			checkTime = sdf.format(new Date());
			stockDate=new StockDate();
			stockDate.setModifyDate(new Date());
			checkTime=stockReportService.getLastStockDate(stockDate);
		}
		
		if(StringUtils.isEmpty(productionDate)){
			productionDate=sdf.format(new Date());
		}
		
		stockManager = new StockManager();
		stockManagerList = new ArrayList<StockManager>();
		stockManager.setKunnr(kunnr);
		stockManager.setCheckTime(checkTime);
		stockManager.setOrgId(orgId);
		stockManager.setSkuId(skuId);
		stockManager.setDateNow(productionDate);
		stockManager.setYear(Integer.parseInt(sdf.format(new Date()).substring(0, 4)));
		stockManager.setMonth(Integer.parseInt(sdf.format(new Date()).substring(5, 7)));
		stockManager.setStart(getStart());
		stockManager.setEnd(getEnd());
		total=stockReportService.searchStockManagerReportCount(stockManager);
		if(total>0){
			stockManagerList = stockReportService.searchStockManagerReportList(stockManager);
		}
		return JSON;
	}
	
	public void exportForExcelStockManager() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
        if(StringUtils.isEmpty(checkTime)){
			checkTime = sdf.format(new Date());
			stockDate=new StockDate();
			stockDate.setModifyDate(new Date());
			checkTime=stockReportService.getLastStockDate(stockDate);
		}
		
		if(StringUtils.isEmpty(productionDate)){
			productionDate=sdf.format(new Date());
		}
		
		stockManager = new StockManager();
		stockManagerList = new ArrayList<StockManager>();
		stockManager.setKunnr(kunnr);
		stockManager.setCheckTime(checkTime);
		stockManager.setOrgId(orgId);
		stockManager.setSkuId(skuId);
		stockManager.setDateNow(productionDate);
		stockManager.setYear(Integer.parseInt(sdf.format(new Date()).substring(0, 4)));
		stockManager.setMonth(Integer.parseInt(sdf.format(new Date()).substring(5, 7)));
		stockManager.setStart(0);
		stockManager.setEnd(999999);
		stockManagerList = stockReportService.searchStockManagerList(stockManager);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "经销商管理平台.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 30);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 15);
			wsheet.setColumnView(10, 15);
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 15);
			wsheet.setColumnView(14, 15);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 15);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商名称"));
			wsheet.addCell(new Label(5, 0, "产品名称"));
            wsheet.addCell(new Label(6, 0, "上期库存"));
			wsheet.addCell(new Label(7, 0, "运输在途"));
			wsheet.addCell(new Label(8, 0, "本月日均分销目标"));
			wsheet.addCell(new Label(9, 0, "可销售天数"));
			wsheet.addCell(new Label(10, 0, "库存标记"));
			wsheet.addCell(new Label(11, 0, "本月分销目标"));
			wsheet.addCell(new Label(12, 0, "本月底安全库存"));
			wsheet.addCell(new Label(13, 0, "次月分销目标"));
			wsheet.addCell(new Label(14, 0, "未排产订单"));
			wsheet.addCell(new Label(15, 0, "排产需求"));
			wsheet.addCell(new Label(16, 0, "上单量"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockManagerList.size(); i++) {
				if(stockManagerList.get(i-1).getOrgRegion()==null){
					stockManagerList.get(i-1).setOrgRegion("");
				}
				if(stockManagerList.get(i-1).getOrgProvince()==null){
					stockManagerList.get(i-1).setOrgProvince("");
				}
				if(stockManagerList.get(i-1).getOrgCity()==null){
					stockManagerList.get(i-1).setOrgCity("");
				}
				if(stockManagerList.get(i-1).getKunnr()==null){
					stockManagerList.get(i-1).setKunnr("");
				}
				if(stockManagerList.get(i-1).getKunnrName()==null){
					stockManagerList.get(i-1).setKunnrName("");
				}
				if(stockManagerList.get(i-1).getSkuName()==null){
					stockManagerList.get(i-1).setSkuName("");
				}
				if(stockManagerList.get(i-1).getStockStatus()==null){
					stockManagerList.get(i-1).setStockStatus("");
				}
				if(stockManagerList.get(i-1).getOutPutNeeds()==null){
					stockManagerList.get(i-1).setOutPutNeeds("");
				}
				
				if(stockManagerList.get(i-1).getLastStock()==null){
					stockManagerList.get(i-1).setLastStock(0.0);
				}
				if(stockManagerList.get(i-1).getOnWayNum()==null){
					stockManagerList.get(i-1).setOnWayNum(0.0);
				}
				if(stockManagerList.get(i-1).getSalesDayAvg()==null){
					stockManagerList.get(i-1).setSalesDayAvg(0.0);
				}
				if(stockManagerList.get(i-1).getSalesDayNum()==null){
					stockManagerList.get(i-1).setSalesDayNum(0.0);
				}
				if(stockManagerList.get(i-1).getOutPutNotNum()==null){
					stockManagerList.get(i-1).setOutPutNotNum(0.0);
				}
				if(stockManagerList.get(i-1).getSalesGoalA1()==null){
					stockManagerList.get(i-1).setSalesGoalA1(0.0);
				}
				if(stockManagerList.get(i-1).getStockSafe()==null){
					stockManagerList.get(i-1).setStockSafe(0.0);
				}
				if(stockManagerList.get(i-1).getNextSalesGoalA1()==null){
					stockManagerList.get(i-1).setNextSalesGoalA1(0.0);
				}
				if(stockManagerList.get(i-1).getTakeOrderNeedNum()==null){
					stockManagerList.get(i-1).setTakeOrderNeedNum(0.0);
				}
				wsheet.addCell(new Label(0,i,stockManagerList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockManagerList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockManagerList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockManagerList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockManagerList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockManagerList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Number(6,i,stockManagerList.get(i-1).getLastStock(),wcfF));
				wsheet.addCell(new Number(7,i,stockManagerList.get(i-1).getOnWayNum(),wcfF));
				wsheet.addCell(new Number(8,i,stockManagerList.get(i-1).getSalesDayAvg(),wcfF));
				wsheet.addCell(new Number(9,i,stockManagerList.get(i-1).getSalesDayNum(),wcfF));
				wsheet.addCell(new Label(10,i,stockManagerList.get(i-1).getStockStatus(),wcfF));
				wsheet.addCell(new Number(11,i,stockManagerList.get(i-1).getSalesGoalA1(),wcfF));
				wsheet.addCell(new Number(12,i,stockManagerList.get(i-1).getStockSafe(),wcfF));
				wsheet.addCell(new Number(13,i,stockManagerList.get(i-1).getNextSalesGoalA1(),wcfF));
				wsheet.addCell(new Number(14,i,stockManagerList.get(i-1).getOutPutNotNum(),wcfF));
				wsheet.addCell(new Label(15,i,stockManagerList.get(i-1).getOutPutNeeds(),wcfF));
				wsheet.addCell(new Number(16,i,stockManagerList.get(i-1).getTakeOrderNeedNum(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportForExcelSalesGoalCG() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setFlag(flag);
		goal.setKunnr(kunnr);
		goal.setGoalType("A1");
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setGoalMark(goalMark);
		goal.setMatterNum(matterNum);
		goal.setStart(0);
		goal.setEnd(999999);
		if(StringUtils.isNotEmpty(brand)){
			goal.setBrand(brand);
		}
		goalList = stockReportService.searchSalesGoalCGDetailList(goal);
		
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "分销目标.xls";
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
			wsheet.setColumnView(0, 10);
			
			
			wsheet.addCell(new Label(0, 0, "组织"));
			if(goal.getGoalMark().equals("N")){
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 30);
				wsheet.setColumnView(4, 30);
				wsheet.setColumnView(5, 10);
				wsheet.setColumnView(6, 10);
				wsheet.addCell(new Label(1, 0, "目标年"));
				wsheet.addCell(new Label(2, 0, "目标月"));
				wsheet.addCell(new Label(3, 0, "品牌"));
				wsheet.addCell(new Label(4, 0, "品项or四级科目(SKU)名称"));
				wsheet.addCell(new Label(5, 0, "箱数"));
				wsheet.addCell(new Label(6, 0, "待开箱数"));
			}else{
				wsheet.setColumnView(1, 30);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 10);
				wsheet.setColumnView(4, 30);
				wsheet.setColumnView(5, 30);
				wsheet.setColumnView(6, 10);
				wsheet.addCell(new Label(1, 0, "经销商名称"));
				wsheet.addCell(new Label(2, 0, "目标年"));
				wsheet.addCell(new Label(3, 0, "目标月"));
				wsheet.addCell(new Label(4, 0, "品牌"));
				wsheet.addCell(new Label(5, 0, "品项or四级科目(SKU)名称"));
				wsheet.addCell(new Label(6, 0, "箱数"));
			}
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= goalList.size(); i++) {
				if(goalList.get(i-1).getBoxNumD()==null){
					goalList.get(i-1).setBoxNumD(0.0);
				}
				if(goalList.get(i-1).getBoxNum()==null){
					goalList.get(i-1).setBoxNum(0.0);
				}
				wsheet.addCell(new Label(0,i,goalList.get(i-1).getOrgName(),wcfF));
				if(goal.getGoalMark().equals("N")){
					wsheet.addCell(new Label(1,i,goalList.get(i-1).getGoalYear(),wcfF));
					wsheet.addCell(new Label(2,i,goalList.get(i-1).getGoalMonth(),wcfF));
					wsheet.addCell(new Label(3,i,goalList.get(i-1).getBrand(),wcfF));
					wsheet.addCell(new Label(4,i,goalList.get(i-1).getMatterName(),wcfF));
					wsheet.addCell(new Number(5,i,goalList.get(i-1).getBoxNum(),wcfF));
					wsheet.addCell(new Number(6,i,goalList.get(i-1).getBoxNumD(),wcfF));
				}else{
					if(goalList.get(i-1).getKunnr().length()<8){
						goalList.get(i-1).setKunnrName("客户开户中");
					}
					wsheet.addCell(new Label(1,i,goalList.get(i-1).getKunnrName(),wcfF));
					wsheet.addCell(new Label(2,i,goalList.get(i-1).getGoalYear(),wcfF));
					wsheet.addCell(new Label(3,i,goalList.get(i-1).getGoalMonth(),wcfF));
					wsheet.addCell(new Label(4,i,goalList.get(i-1).getBrand(),wcfF));
					wsheet.addCell(new Label(5,i,goalList.get(i-1).getMatterName(),wcfF));
					wsheet.addCell(new Number(6,i,goalList.get(i-1).getBoxNum(),wcfF));
				}
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportForExcelSalesGoalChallenge() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		goalList = new ArrayList<Goal>();
		goal = new Goal();
		if(orgId!=null){
			goal.setOrgId(orgId+"");
		}
		goal.setKunnr(kunnr);
		goal.setStartDate(startDate);
		goal.setEndDate(endDate);
		goal.setMatterNum(matterNum);
		goal.setStart(0);
		goal.setEnd(999999);
		goalList = stockReportService.searchSalesGoalChallengeList(goal);
		
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "挑战分销目标.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 30);
			
			
			wsheet.addCell(new Label(0, 0, "组织"));
			wsheet.addCell(new Label(1, 0, "目标年"));
			wsheet.addCell(new Label(2, 0, "目标月"));
			wsheet.addCell(new Label(3, 0, "物料编号"));
			wsheet.addCell(new Label(4, 0, "物料名称"));
			wsheet.addCell(new Label(5, 0, "箱数"));
			wsheet.addCell(new Label(6, 0, "经销商编号"));
			wsheet.addCell(new Label(7, 0, "经销商名称"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= goalList.size(); i++) {
				if(goalList.get(i-1).getBoxNumD()==null){
					goalList.get(i-1).setBoxNumD(0.0);
				}
				if(goalList.get(i-1).getBoxNum()==null){
					goalList.get(i-1).setBoxNum(0.0);
				}
				wsheet.addCell(new Label(0,i,goalList.get(i-1).getOrgName(),wcfF));
				wsheet.addCell(new Label(1,i,goalList.get(i-1).getGoalYear(),wcfF));
				wsheet.addCell(new Label(2,i,goalList.get(i-1).getGoalMonth(),wcfF));
				wsheet.addCell(new Label(3,i,goalList.get(i-1).getMatterNum(),wcfF));
				wsheet.addCell(new Label(4,i,goalList.get(i-1).getMatterName(),wcfF));
				wsheet.addCell(new Number(5,i,goalList.get(i-1).getBoxNum(),wcfF));
				wsheet.addCell(new Label(6,i,goalList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(7,i,goalList.get(i-1).getKunnrName(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	@PermissionSearch
	public void exportMouldCsv() {
		OutputStream os = null;
		String report_name = "经销商分类导入模板";
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
			linedata.append("分类(A/B/C/D)");
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
	}
	
	@PermissionSearch
	public String exportGoalCsv() {
		OutputStream os = null;
		String report_name = "分销数据导入模板";
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
			linedata.append("财年");
			linedata.append(",");
			linedata.append("月度");
			linedata.append(",");
			linedata.append("椰果单杯(原味)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(香芋)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(麦香)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(草莓)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(巧克力)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(咖啡)-80g*30");
			linedata.append(",");
			linedata.append("椰果单杯(绿茶)-80g*30");
			linedata.append(",");
			linedata.append("椰果经典组合装-80g*30");
			linedata.append(",");
			linedata.append("红豆、椰果经典组合装-80g*30");
			linedata.append(",");
			linedata.append("红豆单杯-64g*30");
			linedata.append(",");
			linedata.append("桂圆红枣单杯-65g*30");
			linedata.append(",");
			linedata.append("椰果礼盒装-80g*8*10");
			linedata.append(",");
			linedata.append("椰果礼盒装-80g*8*12");
			linedata.append(",");
			linedata.append("椰果家庭分享装-80g*16*6");
			linedata.append(",");
			linedata.append("桂圆红枣家庭分享装-65g*16*6");
			linedata.append(",");
			linedata.append("椰果家庭分享装-80g*15*6");
			linedata.append(",");
			linedata.append("桂圆红枣家庭分享装-65g*15*6");
			linedata.append(",");
			linedata.append("椰果六联杯装-80g*6*6");
			linedata.append(",");
			linedata.append("椰果三联杯装-80g*3*10");
			linedata.append(",");
			linedata.append("红豆三联杯装-80g*3*10");
			linedata.append(",");
			linedata.append("桂圆红枣三联杯装-65g*3*10");
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
	 * MethodsTitle: 分销目标导入模板下载
	 * @author: xg.chen
	 * @date:2016年12月26日 下午4:18:48
	 * @version 1.1 组织和经销商分开验证
	 * @return
	 */
	@PermissionSearch
	public String exportGoalCGCsv() {
		OutputStream os = null;
		String report_name = "分销目标导入模板";
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
	
	@PermissionSearch
	public String exportStockCsv() {
		OutputStream os = null;
		String report_name = "库存导入模板";
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
			linedata.append("sku编号");
			linedata.append(",");
			linedata.append("sku名称");
			linedata.append(",");
			linedata.append("库存时间(yyyy/mm/dd)");
			linedata.append(",");
			linedata.append("数量");
			linedata.append(",");
			linedata.append("库存类型(周库存：kunnr_week/月库存：kunnr_month/分销量：sales_day/日库存：kunnr_day)");
			linedata.append(",");
			linedata.append("货龄(yyyy-mm)");
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
	
	@PermissionSearch
	public String importKunnrPlanCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		
		try {
			String rcs = "";
			String rcs2 = "";
			kunnrInTypeList = new ArrayList<KunnrInType>();
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
							if(s.length>2){
								this.setFailMessage("第" + (j + 2) + "列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0].length()<8){
								int length=8-s[0].length();
								for(int x=0;x<length;x++){
									s[0]="0"+s[0];
								}
							}
							Kunnr kunnr=new Kunnr();
							kunnr.setKunnr(s[0]);
							int num=kunnrService.kunnrSearchCount(kunnr);
							if(num==0){
								rcs = rcs + "第" + (j + 2) + "行经销商编号不正确.</br>";
							}
							if(!"A".equals(s[1]) && !"B".equals(s[1]) && !"C".equals(s[1]) && !"D".equals(s[1])){
								rcs = rcs + "第" + (j + 2) + "分类格式不正确." + "</br>";
							}
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
							kunnrInType = new KunnrInType();
							kunnrInType.setKunnr(s[0]);
							kunnrInType.setType(s[1]);
							kunnrInTypeList.add(kunnrInType);
						}else{
							rcs = rcs + "第" + (j + 2)
									+ "列数有问题.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
					if (kunnrInTypeList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<kunnrInTypeList.size();j++){
							try {
								rcs2 = "";
								stockReportService.updateKunnrInType(kunnrInTypeList.get(j));
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
								+ kunnrInTypeList.size() + "行");
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
	
	@PermissionSearch
	public String importSalesGoalCGApplyCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		
		try {
			String rcs = "";
			String rcs2 = "";
			List<Goal> goalList = new ArrayList<Goal>();
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
							if(s.length>5){
								this.setFailMessage("第" + (j + 2) + "列列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0]==null || s[0]==""){
								rcs = rcs + "第" + (j + 2) + "行财年为空.</br>";
							}
							if(s[1]==null || s[1]==""){
								rcs = rcs + "第" + (j + 2) + "行月份为空.</br>";
							}else{
								if(s[1].length()==1){
									s[1]="0"+s[1];
								}
							}
							
							String[] m_num = {"A1","A2","A3","A4","A5","A6",
									"A7","A8","B1","B3","B6","B8","C1",
									"C6","C7","C8","D10","D9","E1","H7",
									"F1","F8","G1","G8","J1","J8","K1","K8","D3"
							};
							Goal goal1=new Goal();
							goal1.setKunnr(kunnr);
							goal1.setOrgId(orgId+"");
							goal1.setGoalMark("Y");
							goal1.setGoalYear(s[0]);
							goal1.setGoalMonth(s[1]);
							goal1.setUserId(this.getUser().getUserId());
							goal1.setMatterNum(s[2]);
							goal1.setGoalType("A1");
							String box_num = s[4];
							if (null != box_num) {
								Pattern p = Pattern.compile("^\\-?\\d+(\\.\\d+)?$");//正则表达式判断是否数字
	        					Matcher matcher = p.matcher(box_num);
								if (matcher.matches()){
									String sum="";
									if("S".equals(flag)){ //经销商开户退回修改，已导入目标量不计入待开
										sum=stockReportService.searchSalesGoalCGDetailListSum(goal1);
									}
									goal1.setBoxNum(Double.parseDouble(box_num));
									Double orgBoxNumD = stockReportService.getGoalCGOrgBoxNumD(goal1);
									if(StringUtils.isNotEmpty(sum) && orgBoxNumD!=null){
										BigDecimal bg1 = new BigDecimal(sum);
										BigDecimal bg2 = new BigDecimal(orgBoxNumD);
										orgBoxNumD=bg1.add(bg2).doubleValue();
									}
									if(orgBoxNumD==null || (orgBoxNumD+0.1)<goal1.getBoxNum()){
										rcs = rcs + "第" + (j + 2) + "行组织待开目标量不足</br>";
									}else{
										goalList.add(goal1);
									}							
								}else{
									rcs = rcs + "第" + (j + 2) + "行目标箱数为非数字的值</br>";
								}
							} else {
								rcs = rcs + "第" + (j + 2) + "行目标箱数为空</br>";
							}
								
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "第" + (j + 2)
									+ "行列数有问题.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
//					@SuppressWarnings("unchecked")
//					List<Goal> bSalesTargetList=(List<Goal>)this.getSession().
//							getAttribute(kunnr+ "kunnrApplySalesGoalList");
//					if(bSalesTargetList!=null && bSalesTargetList.size()>0){
//						result.append("已存在导入数据，请勿重复提交！</br>");
//					}
					if(goalList.size() != 0
							&& result.toString().equals("")) {
						if(this.getSession().getAttribute(goalList.get(0).getKunnr()+
								"kunnrApplySalesGoalList")!=null){
							this.getSession().removeAttribute(goalList.get(0).getKunnr()+
									"kunnrApplySalesGoalList");
						}
						this.getSession().setAttribute(goalList.get(0).getKunnr()+
								"kunnrApplySalesGoalList", goalList);
						result1.setResult(true);
//						for(int j=0;j<goalList.size();j++){
//							try {
//								rcs2 = "";
//								stockReportService.createSalesGoal(goalList.get(j));
//								result.append(rcs2.toString() + "</br>");
//							} catch (Exception e) {
//								rcs2 = rcs2 + "第" + (j + 2)
//										+ "条数据保存数据库失败.请联系系统管理员.</br>";
//								result.append(rcs2.toString() + "</br>");
//							}
//						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("分销目标量导入成功!");
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
	 * MethodsTitle: 分销目标量批量导入验证
	 * @author: xg.chen/b.wang
	 * @date:2016年12月26日 下午4:23:53/2017.02.17
	 * @version 1.1 组织和经销商分开验证
	 * @return
	 */
	@PermissionSearch
	public String importSalesGoalCGCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap = new HashMap<String,String>();
		Map<String,String> orgMap = new HashMap<String,String>();
		Map<String,String> matterMap = new HashMap<String,String>();
		Map<String,Goal> goalMap = new HashMap<String,Goal>();
		Map<String,BigDecimal> boxDMap = new HashMap<String,BigDecimal>();
		setImportPercent(0);
		try {
			String rcs = "";
			String rcs2 = "";
			List<Goal> goalList = new ArrayList<Goal>();
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
						int percent=(int)Math.floor((j+1)/(double)content.size()*100/4);
						setImportPercent(percent);
						
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>8){
								this.setFailMessage("第" + (j + 2) + "列,列数不正确.");
								return RESULT_MESSAGE;
							}
							//目标年月
							Goal goal =new Goal();
							goal.setGoalYear(s[4]);
							goal.setGoalMonth(s[5]);
							goal.setGoalType(goalType);
							//组织经销商验证
							if("Y".equals(goalMark)){
								if(StringUtils.isNotEmpty(s[1]) && StringUtils.isNotEmpty(s[2])){
									//判断导入的kunnrId是否正确
									if(StringUtils.isNotEmpty(s[1])){
										if(s[1].length()<8){
											s[1]="0"+s[1];
										}
									}
									String kunnrInfo=kunnrMap.get(s[1]);
									if(StringUtils.isEmpty(kunnrInfo)){
										Kunnr kunnr=new Kunnr();
										kunnr.setKunnr(s[1]);
										kunnr.setName1(s[2]);
										kunnr.setPagination("false");
										List<Kunnr> kunnrList=kunnrService.kunnrSearch(kunnr);
										if(kunnrList==null || kunnrList.size()==0){
											rcs = rcs + "第" + (j + 2) + "行经销商名称不正确.</br>";
										}else if(kunnrList.size()>=1){
											int count=0;
											for(Kunnr k:kunnrList){
												if(k.getName1().equals(s[2])){
													goal.setKunnr(k.getKunnr());
													goal.setOrgId(k.getOrgId());
													kunnrMap.put(s[1], k.getName1()+","+k.getOrgId());
													count=1;
													break;
												}
											}
											if(count==0){
												rcs = rcs + "第" + (j + 2) + "行经销商名称不正确.</br>";
											}
										}
									}else{
										String name1=kunnrInfo.split(",")[0];
										String orgId=kunnrInfo.split(",")[1];
										if(!name1.equals(s[2])){
											rcs = rcs + "第" + (j + 2) + "行经销商名称不正确.</br>";
										}else{
											goal.setKunnr(s[1]);
											goal.setOrgId(orgId);
										}
									}
								}else{
									rcs = rcs + "第" + (j + 2) + "行经销商不能为空.</br>";
								}
							}else if("N".equals(goalMark)){
								if(s[0]!=null){
									String orgId=orgMap.get(s[0]);
									if(StringUtils.isNotEmpty(orgId)){
										goal.setOrgId(orgId);
									}else{
										Kunnr kunnr=new Kunnr();
										kunnr.setOrgName(s[0]);
										List<OrgHelps> orgs = goalService.getOrgIsExit(kunnr);
										if(null != orgs && orgs.size() != 0){
											goal.setOrgId(orgs.get(0).getOrgId());
											orgMap.put(s[0], orgs.get(0).getOrgId());
										}else{
											rcs = rcs + "第" + (j + 2) + "行组织不存在.</br>";
										}
									}
								} else {
									rcs = rcs + "第" + (j + 2) + "行组织不能为空.</br>";
								}
							}
							//目标年月验证
							if(StringUtils.isEmpty(goal.getGoalYear())){
								rcs = rcs + "第" + (j + 2) + "行 年份为空.</br>";
							}else{
								Pattern p = Pattern.compile("^[2]\\d{3}$");//正则表达式判断年份
	        					Matcher matcher = p.matcher(goal.getGoalYear());
	        					if(!matcher.matches()){
	        						rcs = rcs + "第" + (j + 2) + "行 年份格式错误</br>";
									break;
	        					}
							}
							if(StringUtils.isEmpty(goal.getGoalMonth())){
								rcs = rcs + "第" + (j + 2) + "行月份为空.</br>";
							}else{
								Pattern p = Pattern.compile("^[0]?[1-9]|[1][0-2]$");//正则表达式判断月份
	        					Matcher matcher = p.matcher(goal.getGoalMonth());
	        					if(!matcher.matches()){
	        						rcs = rcs + "第" + (j + 2) + "行 月份格式错误</br>";
									break;
	        					}else{
	        						if(goal.getGoalMonth().length()==1){
	        							goal.setGoalMonth("0"+goal.getGoalMonth());
	        						}
	        					}
							}
							//品项以及品项名称验证
							if(StringUtils.isEmpty(s[6]) || StringUtils.isEmpty(s[7])){
								rcs = rcs + "第" + (j + 2) + "行预算口径代码或物料组描述为空.</br>";
							}else{
								String matterName=matterMap.get(s[6]);
								if(StringUtils.isNotEmpty(matterName)){
									if(matterName.equals(s[7])){
										goal.setMatterNum(s[6]);
										goal.setMatterName(s[7]);
									}else{
										rcs = rcs + "第" + (j + 2) + "行预算口径代码或物料组描述不正确.</br>";
									}
								}else{
									Materiel mat = new Materiel();
									mat.setMvgr1(s[6]);
									mat.setBezei(s[7]);
									mat.setPagination("false");
									int count = goalService.getMaterielViewListCount(mat);
									if(count>0){
										goal.setMatterNum(s[6]);
										goal.setMatterName(s[7]);
										matterMap.put(s[6],s[7]);
									}else{
										rcs = rcs + "第" + (j + 2) + "行预算口径代码或物料组描述不正确.</br>";
									}
								}
							}
							
							StringBuilder str = new StringBuilder();
							str.append(s[0]).append(s[4]).append(s[5]).append(s[6]);
							if(goalMap.get(str.toString())==null){
								goalMap.put(str.toString(), goal);
							}else{
								rcs = rcs + "第" + (j + 2) + "行数据在该导入文件中存在重复.</br>";
							}
							
							Goal goalIn = new Goal();
							if("Y".equals(goalMark)){
								goalIn.setKunnr(goal.getKunnr());
								goalIn.setOrgId(goal.getOrgId());
								goalIn.setGoalMark("Y");
							}else if("N".equals(goalMark)){
								goalIn.setKunnr("");
								goalIn.setOrgId(goal.getOrgId());
								goalIn.setGoalMark("N");
							}else{
								break;
							}
							goalIn.setGoalYear(goal.getGoalYear());
							goalIn.setGoalMonth(goal.getGoalMonth());
							goalIn.setUserId(this.getUser().getUserId());
							goalIn.setMatterNum(goal.getMatterNum());
							goalIn.setGoalType(goalType);
							//判断目标箱数
							if (StringUtils.isNotEmpty(s[3])) {
								Pattern p = Pattern.compile("^\\-?\\d+(\\.\\d+)?$");//判断是否数字
	        					Matcher matcher = p.matcher(s[3]);
								if (matcher.matches()){
									if(Double.parseDouble(s[3])==0){
										continue;
									}else{
										goalIn.setBoxNum(Double.parseDouble(s[3]));
									}
								}else{
									rcs = rcs + "第" + (j + 2) + "行目标箱数为非数字的值</br>";
								}
							} else {
								continue;
							}
							goalList.add(goalIn);
							
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
					if(goalList.size() != 0
							&& result.toString().equals("")){
						for(int j=0;j<goalList.size();j++){
							int percent=(int)Math.floor((j+1)/(double)goalList.size()*100/4)+25;
                    		setImportPercent(percent);
                    		
							Goal goal = goalList.get(j);
							if(goal.getGoalMark().equals("Y")){
								Goal g = new Goal();
								BigDecimal boxD = boxDMap.get(goal.getOrgId() + goal.getGoalYear()
										+ goal.getGoalMonth() + goal.getMatterNum());
								if(boxD!=null){
									BigDecimal b2 = new BigDecimal(goal.getBoxNum());
									if(boxD.compareTo(b2)!=-1){
										boxDMap.put(goal.getOrgId() + goal.getGoalYear()
												+ goal.getGoalMonth() + goal.getMatterNum(), boxD.subtract(b2));
									}else{
										rcs = rcs + "第" + (j + 2) + "行 组织待开目标量不足.</br>";
									}
								}else{
									g.setOrgId(goal.getOrgId());
									g.setGoalYear(goal.getGoalYear());
									g.setGoalMonth(goal.getGoalMonth());
									g.setMatterNum(goal.getMatterNum());
									g.setGoalType("A1");
									g.setGoalMark("N");
									g.setPagination("false");
									List<Goal> list = stockReportService.searchSalesGoalCGDetailList(g);
									if(list!=null && list.size()>0){
										double orgBox=list.get(0).getBoxNumD();
										BigDecimal b1 = new BigDecimal(orgBox);
										BigDecimal b2 = new BigDecimal(goal.getBoxNum());
										if(b1.compareTo(b2)!=-1){
											boxDMap.put(goal.getOrgId() + goal.getGoalYear()
													+ goal.getGoalMonth() + goal.getMatterNum(), b1.subtract(b2));
										}else{
											rcs = rcs + "第" + (j + 2) + "行 组织待开目标量不足.</br>";
										}
									}else{
										rcs = rcs + "第" + (j + 2) + "行 组织目标量不存在，请先维护组织目标量.</br>";
									}
								}
							}
							
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}
					}
					
					if (goalList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<goalList.size();j++){
							try {
                        		int percent=(int)Math.floor((j+1)/(double)goalList.size()*100/2)+50;
                        		setImportPercent(percent);
								
								rcs2 = "";
								stockReportService.createSalesGoal(goalList.get(j));
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
			this.setFailMessage("导入出错！");
			e.printStackTrace();
		}
		setImportPercent(99);
		
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	public String importSalesGoalChallengeCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		
		try {
			String rcs = "";
			String rcs2 = "";
			List<Goal> goalList = new ArrayList<Goal>();
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
							if(s.length>32){
								this.setFailMessage("第" + (j + 2) + "列列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0].length()<8 && s[0].length()>5){
								for(int x=0;x<8-s[0].length();x++){
									s[0]="0"+s[0];
								}
							}
							Goal goal =new Goal();
							goal.setKunnr(s[0]);
							goal.setGoalYear(s[1]);
							goal.setGoalMonth(s[2]);
							int num=0;
							if(s[0].length()==8){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0]);
								num=kunnrService.kunnrSearchCount(kunnr);
							}
							if(num==0){
								rcs = rcs + "第" + (j + 2) + "行经销商编号不正确.</br>";
							}
							if((goal.getKunnr()!=null) && (goal.getGoalYear()!=null)
									&&(goal.getGoalMonth()!=null)){
								if(s[0].length()==8){
									int size = stockReportService.searchSalesGoalChallengeListCount(goal);
									if(size != 0){
										rcs = rcs + "第" + (j + 2) + "行数据已经导入过,请勿重复提交.</br>";
									}
								}
							}
							if(goal.getGoalYear()==null || goal.getGoalYear()==""){
								rcs = rcs + "第" + (j + 2) + "行 年份为空.</br>";
							}else{
								for (int m_digit = 0; m_digit < goal.getGoalYear()
										.length(); m_digit++) {
									char c = goal.getGoalYear().charAt(m_digit);
									if ((c < '0' || c > '9')) {
											rcs = rcs + "第" + (j + 2) + "行 年份为非数字的值</br>";
											break;
									}
								}
							}
							if(goal.getGoalMonth()==null || goal.getGoalMonth()==""){
								rcs = rcs + "第" + (j + 2) + "行月份为空.</br>";
							}else{
								for (int m_digit = 0; m_digit < goal.getGoalMonth()
										.length(); m_digit++) {
									char c = goal.getGoalMonth().charAt(m_digit);
									if ((c < '0' || c > '9')) {
											rcs = rcs + "第" + (j + 2) + "行 月份为非数字的值</br>";
											break;
									}
								}
							}
							String[] m_num = {"A1","A2","A3","A4","A5","A6",
									"A7","A8","B1","B3","B6","B8","C1",
									"C6","C7","C8","D10","D9","E1","H7",
									"F1","F8","G1","G8","J1","J8","K1","K8","D3"
							};
							String orgId ="";
							if(s[0].length()==8 && num!=0){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0]);
								orgId=kunnrService.getKunnrEntity(kunnr).getOrgId();
							}
							for(int i=3;i<header.length;i++){
								Goal goal1=new Goal();
								goal1.setKunnr(s[0]);
								goal1.setOrgId(orgId);
								goal1.setGoalYear(s[1]);
								goal1.setGoalMonth(s[2]);
								goal1.setUserId(this.getUser().getUserId());
								goal1.setMatterNum(m_num[i-3]);
								String box_num = s[i];
								if (StringUtils.isNotEmpty(box_num)) {
									int k = 0;
									for (int m_digit = 0; m_digit < box_num
											.length(); m_digit++) {
										char c = box_num.charAt(m_digit);
										if ((c < '0' || c > '9')) {
											if(c!='.' && c!='-'){
												k = 1;
												rcs = rcs + "第" + (j + 2) + "行"
														+ header[i]
																+ ": 为非数字的值</br>";
												break;
											}
										}
									}
									if (k == 0)
										if(Double.parseDouble(box_num)==0){
											continue;
										}else{
											goal1.setBoxNum(Double.parseDouble(box_num));
										}
								} else {
//									rcs = rcs + "第" + (j + 2) + "行" + header[i]
//											+ ": 为空,若无数据填0</br>";
									continue;
								}
								goalList.add(goal1);
								
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
					if (goalList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<goalList.size();j++){
							try {
								rcs2 = "";
								stockReportService.createSalesGoalChallenge(goalList.get(j));
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
	
	@PermissionSearch
	public String importStockCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap = new HashMap<String,String>();
		Map<String,String> skuMap = new HashMap<String,String>();
		Map<String,Double> map = new HashMap<String,Double>();
		try {
			String rcs = "";
			String rcs2 = "";
			List<StockReport> stockList = new ArrayList<StockReport>();
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
							if(s.length>8){
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0]==null || "".equals(s[0])){
								rcs = rcs + "第" + (j + 2) + "行经销商编号为空.</br>";
							}else{
								if(s[0].length()<8){
									int length=8-s[0].length();
									for(int x=0;x<length;x++){
										s[0]="0"+s[0];
									}
								}
							}
							StockReport stock = new StockReport();
							stock.setKunnr(s[0]);
							stock.setSkuId(Long.parseLong(s[2]));
							stock.setUserId(Long.parseLong(this.getUser().getUserId()));
							if(StringUtils.isNotEmpty(userType)){
								stock.setUserType(userType);
							}else{
								stock.setUserType("A");
							}
							
							//将ID及名称放入MAP,加快验证速度
							String kunnrName=kunnrMap.get(s[0]);
							if(kunnrName==null){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0].trim());
								kunnr.setName1(s[1].trim());
								kunnr.setPagination("false");
								int num=kunnrService.kunnrSearchCount(kunnr);
								if(num==0){
									rcs = rcs + "第" + (j + 2) + "行经销商编号或名称不正确.</br>";
								}else{
									kunnrMap.put(s[0], s[1]);
								}
							}else{
								if(!kunnrName.equals(s[1])){
									rcs = rcs + "第" + (j + 2) + "行经销商编号或名称不正确.</br>";
								}
							}
							
							if(StringUtils.isEmpty(s[2])){
								rcs = rcs + "第" + (j + 2) + "行sku编号为空.</br>";
							}else{
								String skuName=skuMap.get(s[2]);
								if(skuName==null){
									Sku sku = new Sku();
									sku.setSkuId(Long.valueOf(s[2]));
									sku.setSkuName(s[3]);
									sku.setPagination("false");
									List<Sku> skuList=stockReportService.getSkuNameList(sku);
									if(skuList==null || skuList.size()==0){
										rcs = rcs + "第" + (j + 2) + "行sku编号或名称不正确.</br>";
									}else{
										skuMap.put(s[2], skuList.get(0).getSkuName()+","+skuList.get(0).getCgId());
										stock.setCategoryId(skuList.get(0).getCgId());
									}
								}else{
									if(!skuName.split(",")[0].equals(s[3])){
										rcs = rcs + "第" + (j + 2) + "行sku编号或名称不正确.</br>";
									}else{
										stock.setCategoryId(Long.valueOf(skuName.split(",")[1]));
									}
								}
							}
							
							if(s[4]!=null){
								if(s[4].length()<8 || s[4].length()>10 || s[4].indexOf("/")==-1){
									rcs = rcs + "第" + (j + 2) + "行 库存日期请按照yyyy/mm/dd填写.</br>";
								}else{
									String[] dates=s[4].split("/");
									if(dates.length!=3){
										rcs = rcs + "第" + (j + 2) + "行 库存日期请按照yyyy/mm/dd填写.</br>";
									}else{
										if(dates[1].length()==1){
											dates[1]="0"+dates[1];
										}
										if(dates[2].length()==1){
											dates[2]="0"+dates[2];
										}
										s[4]=dates[0]+"-"+dates[1]+"-"+dates[2];
										stock.setCheckTime(s[4]);
									}
								}
							}else{
								rcs = rcs + "第" + (j + 2) + "行 库存日期为空.</br>";
							}
							
							if(s[6]!=null){
								if(s[6].equals("kunnr_week") || s[6].equals("kunnr_month") 
										|| s[6].equals("sales_day") || s[6].equals("kunnr_day")){
									stock.setFlag(s[6]);
								}else{
									rcs = rcs + "第" + (j + 2) + "行 库存类型请填写kunnr_week或者kunnr_month或者sales_day.</br>";
								}
								if(s[6].equals("kunnr_month")){
									if(StringUtils.isEmpty(s[6])){
										rcs = rcs + "第" + (j + 2) + "行 月库存提报货龄不能为空.</br>";
									}
								}
							}else{
								rcs = rcs + "第" + (j + 2) + "行 库存类型为空.</br>";
							}
							if(s[7]!=null){
								String[] dates=s[7].split("-");
								int k=0;
								if(dates.length>1){
									if(dates[0].length()==4){
										for(int i=0;i<dates[0].length();i++){
											char c = dates[0].charAt(i);
											if(c < '0' || c > '9'){
												k=1;
												break;
											}
										}
									}else{
										k=1;
									}
									if(dates[1].length()==1 || dates[1].length()==2){
										for(int i=0;i<dates[1].length();i++){
											char c = dates[1].charAt(i);
											if(c < '0' || c > '9'){
												k=1;
												break;
											}
										}
									}else{
										k=1;
									}
								}else{
									k=1;
								}
								if(k==1){
									rcs = rcs + "第" + (j + 2) + "行 货龄请按照yyyy-mm填写.</br>";
								}else{
									stock.setProductionDate(s[7]);
								}
							}
								
							if (null != s[5]) {
								Pattern p = Pattern.compile("^-?\\d+(\\.\\d+)?$");//正则表达式判断是否数字
	        					Matcher matcher = p.matcher(s[5]);
								if (matcher.matches()){
									stock.setQuantity(Double.parseDouble(s[5]));
								}else{
									rcs = rcs + "第" + (j + 2) + "行"
											+ header[5]
													+ ": 为非数字的值</br>";
								}
							} else {
									rcs = rcs + "第" + (j + 2) + "行" + header[5]
											+ ": 为空</br>";
							}
							stockList.add(stock);
								
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
					
					if (stockList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<stockList.size();j++){
							StockReport sr=stockList.get(j);
							Double box=map.get(Integer.valueOf(sr.getKunnr())+","+sr.getSkuId()+","+sr.getCheckTime()+","+sr.getProductionDate());
							if(box==null){
								map.put(Integer.valueOf(sr.getKunnr())+","+sr.getSkuId()+","+sr.getCheckTime()+","+sr.getProductionDate(),sr.getQuantity());
								int count=stockReportService.getStockReportListCount(sr);
								if(count>0){
									result.append("第"+(j+2)+"条数据已存在！" + "</br>");
								}
							}else{
								result.append("第"+(j+2)+"条数据在此模板中存在重复！" + "</br>");
							}
						}
					}
					
					if (stockList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<stockList.size();j++){
							try {
								rcs2 = "";
								stockReportService.createStockReport(stockList.get(j));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	public String importSalesGoalCGCsvForUpdate() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		
		try {
			String rcs = "";
			String rcs2 = "";
			List<Goal> goalList = new ArrayList<Goal>();
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
							if(s.length>32){
								this.setFailMessage("第" + (j + 2) + "列列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0].length()<8 && s[0].length()>5){
								for(int x=0;x<8-s[0].length();x++){
									s[0]="0"+s[0];
								}
							}
							Goal goal =new Goal();
							if(s[0].length()==8){
								goal.setKunnr(s[0]);
							}else if(s[0].length()==5){
								goal.setOrgId(s[0]);
							}
							goal.setGoalYear(s[1]);
							goal.setGoalMonth(s[2]);
							goal.setGoalType(goalType);
							int num=0;
							if(s[0].length()==8){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0]);
								num=kunnrService.kunnrSearchCount(kunnr);
							}else if(s[0].length()==5){
//								String orgNum=orgServiceHessian.getOrgNameByOrgid(s[0]);
//								if(orgNum!=null && !orgNum.equals("")){
									num=1;
//								}
							}
							if(num==0){
								rcs = rcs + "第" + (j + 2) + "行经销商或组织编号不正确.</br>";
							}
							if((s[0]!=null) && (goal.getGoalYear()!=null)
									&&(goal.getGoalMonth()!=null)){
//								if(s[0].length()==8){
									int size = stockReportService.searchSalesGoalCGDetailListCount(goal);
									if(size == 0){
										rcs = rcs + "第" + (j + 2) + "行数据从未导入过,请先导入数据.</br>";
									}
//								}
							}
							if(goal.getGoalYear()==null || goal.getGoalYear()==""){
								rcs = rcs + "第" + (j + 2) + "行 年份为空.</br>";
							}else{
								for (int m_digit = 0; m_digit < goal.getGoalYear()
										.length(); m_digit++) {
									char c = goal.getGoalYear().charAt(m_digit);
									if ((c < '0' || c > '9')) {
											rcs = rcs + "第" + (j + 2) + "行 年份为非数字的值</br>";
											break;
									}
								}
							}
							if(goal.getGoalMonth()==null || goal.getGoalMonth()==""){
								rcs = rcs + "第" + (j + 2) + "行月份为空.</br>";
							}else{
								for (int m_digit = 0; m_digit < goal.getGoalMonth()
										.length(); m_digit++) {
									char c = goal.getGoalMonth().charAt(m_digit);
									if ((c < '0' || c > '9')) {
											rcs = rcs + "第" + (j + 2) + "行 月份为非数字的值</br>";
											break;
									}
								}
							}
							String[] m_num = {"A1","A2","A3","A4","A5","A6",
									"A7","A8","B1","B3","B6","B8","C1",
									"C6","C7","C8","D10","D9","E1","H7",
									"F1","F8","G1","G8","J1","J8","K1","K8","D3"
							};
							String orgId ="";
							if(s[0].length()==8 && num!=0){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0]);
								orgId=kunnrService.getKunnrEntity(kunnr).getOrgId();
							}
							for(int i=3;i<header.length;i++){
								Goal goal1=new Goal();
								if(s[0].length()==8 && num!=0){
									goal1.setKunnr(s[0]);
									goal1.setOrgId(orgId);
									goal1.setGoalMark("Y");
								}else if(s[0].length()==5 && num!=0){
									goal1.setKunnr("");
									goal1.setOrgId(s[0]);
									goal1.setGoalMark("N");
								}
								goal1.setGoalYear(s[1]);
								goal1.setGoalMonth(s[2]);
								goal1.setUserId(this.getUser().getUserId());
								goal1.setMatterNum(m_num[i-3]);
								goal1.setGoalType(goalType);
								String box_num = s[i];
								if (null != box_num) {
									int k = 0;
									for (int m_digit = 0; m_digit < box_num
											.length(); m_digit++) {
										char c = box_num.charAt(m_digit);
										if ((c < '0' || c > '9')) {
											if(c!='.' && c!='-'){
												k = 1;
												rcs = rcs + "第" + (j + 2) + "行"
														+ header[i]
																+ ": 为非数字的值</br>";
												break;
											}
										}
									}
									if (k == 0)
										if(Double.parseDouble(box_num)==0){
											continue;
										}else{
											goal1.setBoxNum(Double.parseDouble(box_num));
										}
								} else {
//									rcs = rcs + "第" + (j + 2) + "行" + header[i]
//											+ ": 为空,若无数据填0</br>";
									continue;
								}
								goalList.add(goal1);
								
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
					if (goalList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<goalList.size();j++){
							try {
								rcs2 = "";
								stockReportService.updateGoalCGDetail(goalList.get(j));
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
	
	@PermissionSearch
	@JsonResult(field = "categoryList",include = { "categoryId",
			"categoryName" },total = "categorySize")
	public String searchCategory(){
		if (category == null) {
			category = new Category();
		}
		if (StringUtils.isNotEmpty(categoryName)) {
			category.setCategoryName(categoryName);
		}
		
		category.setStart(getStart());
		category.setEnd(getEnd());
		categorySize = stockReportService.getCategoryCount(category);
		if (categorySize != 0) {
			categoryList = stockReportService.getCategoryNameList(category);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuList",include = { "skuId",
			"skuName" },total = "skuSize")
	public String searchSku(){
		if (sku == null) {
			sku = new Sku();
		}
		if (StringUtils.isNotEmpty(skuName)) {
			sku.setSkuName(skuName);
		}
		
		sku.setStart(getStart());
		sku.setEnd(getEnd());
		skuSize = stockReportService.getSkuCount(sku);
		if (skuSize != 0) {
			skuList = stockReportService.getSkuNameList(sku);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuPercentList",include = { "skuId",
			"skuName","cgName","skuPercent","cgId" },total = "total")
	public String searchSkuPercentList(){
		skuPercent = new SkuPercent();
		if (StringUtils.isNotEmpty(skuName)) {
			skuPercent.setSkuName(skuName);
		}
		if (StringUtils.isNotEmpty(categoryName)) {
			skuPercent.setCgName(categoryName);
		}
		skuPercent.setStart(getStart());
		skuPercent.setEnd(getEnd());
		total = stockReportService.searchSkuPercentListCount(skuPercent);
		if (total != 0) {
			skuPercentList = stockReportService.searchSkuPercentList(skuPercent);
		}
		return JSON;
	}
	
	public void updateSkuPercent(){
		skuPercent = new SkuPercent();
		skuPercent.setSkuId(skuId);
		skuPercent.setSkuPercent(percent);
		stockReportService.updateSkuPercent(skuPercent);
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String searchSkuPercentSum(){
		skuPercent = new SkuPercent();
		if (categoryId!=null) {
			skuPercent.setCgId(categoryId);
		}
		if (skuId!=null) {
			skuPercent.setSkuId(skuId);
		}
		skuPercent = stockReportService.searchSkuPercentSum(skuPercent);
		total = skuPercent.getSkuPercent();
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "kunnr","kunnrName",
			"orgRegion","orgProvince","orgCity","quantity","checkTime","kunnrStatus","flag" },total = "total")
	public String searchStockCheck(){
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setFlag(flag);
		stockReport.setStockFlag(stockFlag);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.searchStockCheckCount(stockReport);
		if(total!=0){
			stockReportList = stockReportService.searchStockCheck(stockReport);
		}
		return JSON;
	}
	
	public void exportForExcelCheck() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		stockReport = new StockReport();
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setFlag(flag);
		stockReport.setStockFlag(stockFlag);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setStart(0);
		stockReport.setEnd(100000);
	    stockReportList = stockReportService.searchStockCheck(stockReport);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "库存信息.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 15);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
			wsheet.addCell(new Label(5, 0, "所属日期"));
			wsheet.addCell(new Label(6, 0, "库存类型"));
			wsheet.addCell(new Label(7, 0, "客户状态"));
			wsheet.addCell(new Label(8, 0, "库存提报"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockReportList.size(); i++) {
				wsheet.addCell(new Label(0,i,stockReportList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockReportList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockReportList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockReportList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockReportList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockReportList.get(i-1).getCheckTime(),wcfF));
				if(stockReportList.get(i-1).getFlag()==null){
					stockReportList.get(i-1).setFlag("");
				}else if(stockReportList.get(i-1).getFlag().equals("kunnr_week")){
					stockReportList.get(i-1).setFlag("周库存");
				}else if(stockReportList.get(i-1).getFlag().equals("kunnr_month")){
					stockReportList.get(i-1).setFlag("月库存");
				}
				wsheet.addCell(new Label(6,i,stockReportList.get(i-1).getFlag(),wcfF));
				if(stockReportList.get(i-1).getKunnrStatus().equals("1")){
					stockReportList.get(i-1).setKunnrStatus("正常");
				}else if(stockReportList.get(i-1).getKunnrStatus().equals("2")){
					stockReportList.get(i-1).setKunnrStatus("关户");
				}
				wsheet.addCell(new Label(7,i,stockReportList.get(i-1).getKunnrStatus(),wcfF));
				String check="";
				if(stockReportList.get(i-1).getQuantity()==null){
					check="否";
				}else{
					check="是";
				}
				wsheet.addCell(new Label(8,i,check,wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportSalesGoalForKunnrApply(){
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		HttpServletResponse response = getServletResponse();
		Goal goal = new Goal();
		goal.setKunnr(kunnrCode);
		goal.setGoalType("A1");
		goal.setGoalMark("Y");
		goal.setStart(0);
		goal.setEnd(99999);
		List<Goal> list = stockReportService.searchSalesGoalCGDetailList(goal);
		if(list==null || list.size()==0){
			goal.setKunnr(kunnr);
			list = stockReportService.searchSalesGoalCGDetailList(goal);
		}
		try {
			props.add("goalYear");
			props.add("goalMonth");
			props.add("matterNum");
			props.add("matterName");
			props.add("boxNum");
			os = response.getOutputStream();
			response.reset();
			String b = "经销商分销目标量信息查看";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(b.getBytes("GBK"), ("ISO8859-1")) + ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("经销商分销目标量数据", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_0 = new Label(0, 0, "年份");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "月份");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "对应预算口径代码");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "物料描述");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "目标箱数(标箱)");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			ExcelUtil.createExcelWithBook(wbook, props, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验数据是否下载完成
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else if("Over".equals(obj)){
			download = "Yes";
		} else if(!"".equals(obj)){
			download = obj.toString();
		}
		return JSON;
	}
	
	/**
	 * 获取导入数据百分比
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkImportPercent() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("importPercent");
		if (obj != null) {
			download = obj.toString();
		}
		return JSON;
	}
	
	/**
	 * 设置导入数据百分比
	 * 
	 * @return
	 */
	public void setImportPercent(int percent){
		if(percent==0){
			ServletActionContext.getRequest().getSession().setAttribute("importExcel","Y");
			ServletActionContext.getRequest().getSession().setAttribute("importPercent",percent);
		}else if(percent==99){
			ServletActionContext.getRequest().getSession().setAttribute("importPercent",percent);
			ServletActionContext.getRequest().getSession().removeAttribute("importExcel");
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("importPercent",percent);
		}
	}
	
	
	@PermissionSearch
	@JsonResult(field = "kunagList",include = {},total="total")
	public String searchKunag(){
		kunag = new Kunag();
		kunag.setOrgId(orgId);
		kunag.setKunnr(kunnrId);
		kunag.setKunag(kunagId);
		kunag.setName1(name1);
		kunag.setName2(name2);
		kunag.setIsImportant(isImportant);
		kunag.setStart(getStart());
		kunag.setEnd(getEnd());
		total = stockReportService.searchKunagCount(kunag);
		if(total>0){
			kunagList = stockReportService.searchKunag(kunag);
		}
		return JSON;
	}
	
	public void exportForExcelKunag() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		kunag = new Kunag();
		kunag.setOrgId(orgId);
		kunag.setKunnr(kunnrId);
		kunag.setKunag(kunagId);
		kunag.setName1(name1);
		kunag.setName2(name2);
		kunag.setStart(0);
		kunag.setEnd(999999);
		kunagList = stockReportService.searchKunag(kunag);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "经销商主分户信息.xls";
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
			wsheet.setColumnView(1, 50);
			wsheet.setColumnView(2, 20);
			wsheet.setColumnView(3, 50);
			
			
			wsheet.addCell(new Label(0, 0, "分户编号"));
			wsheet.addCell(new Label(1, 0, "分户名称"));
			wsheet.addCell(new Label(2, 0, "主户编号"));
			wsheet.addCell(new Label(3, 0, "主户名称"));
			wsheet.addCell(new Label(4, 0, "是否重点经销商"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= kunagList.size(); i++) {
				wsheet.addCell(new Label(0,i,kunagList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(1,i,kunagList.get(i-1).getName1(),wcfF));
				wsheet.addCell(new Label(2,i,kunagList.get(i-1).getKunag(),wcfF));
				wsheet.addCell(new Label(3,i,kunagList.get(i-1).getName2(),wcfF));
				wsheet.addCell(new Label(4,i,kunagList.get(i-1).getIsImportant(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public String createKunag(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		stockReportService.deleteKunag(kunag);
		stockReportService.createKunag();
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		this.setSuccessMessage("操作成功！");
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "orderCheckList",include = {"kunnr","checkMonth","checkYear",
			"state","checkDesc","createDate","kunnrName","lastCheckDate","kunnrStatus",
			"orgRegion","orgProvince","orgCity"},total = "total")
	public String searchOrderCheck(){
		List<OrderCheck> stations=stockReportService.getStationIdByUserId(this.getUser().getUserId());
		orderCheck = new OrderCheck();
		if(orgIds!=null){
			orderCheck.setOrgIds(orgIds);
		}
//		if(orgId!=null){
//			orderCheck.setOrgId(orgId);
//		}
//		else{
//			for(OrderCheck station : stations){
//				if("fykj_xn".equals(station.getStationId())){
//					orderCheck.setOrgId(Long.parseLong(this.getUser().getOrgId()));
//					break;
//				}
//			}
//		}
		orderCheck.setKunnr(kunnr);
		orderCheck.setState(state);
		orderCheck.setLastCheckDate(flag);
		orderCheck.setStartDate(startDate);
		orderCheck.setEndDate(endDate);
		orderCheck.setStart(getStart());
		orderCheck.setEnd(getEnd());
		total = stockReportService.searchOrderCheckListCount(orderCheck);
		if(total!=0){
			orderCheckList = stockReportService.searchOrderCheckList(orderCheck);
			for(OrderCheck station : stations){
				if("h_jxsfwz_kj_xn".equals(station.getStationId())){
					for(OrderCheck oc : orderCheckList){
						if(oc.getCheckUser()!=null && oc.getCheckUser().equals("0")){
							oc.setCheckUser("1");
							stockReportService.updateOrderCheck(oc);
						}
					}
				}
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "orderCheckList")
	public String searchOrderCheckLastCheckDate(){
		orderCheck = new OrderCheck();
		if(StringUtils.isNotEmpty(startDate)){
			String[] dates=startDate.split("-");
			orderCheck.setCheckYear(Integer.parseInt(dates[0]));
			orderCheck.setCheckMonth(Integer.parseInt(dates[1]));
		}
		if(orgId!=null){
			orderCheck.setOrgId(orgId);
		}
		orderCheck.setKunnr(kunnr);
		orderCheck.setState(state);
		orderCheck.setLastCheckDate("Y");
		orderCheck.setStart(0);
		orderCheck.setEnd(10);
		orderCheckList = stockReportService.searchOrderCheckList(orderCheck);
		return JSON;
	}
	
	public String createOrderCheck(){
		String ip=getIpAddr(this.getServletRequest());
		OrderCheck oc= new OrderCheck();
	    int year=0;
	    int month=0;
		if(StringUtils.isNotEmpty(startDate)){
			String[] dates=startDate.split("-");
			year=Integer.parseInt(dates[0]);
			month=Integer.parseInt(dates[1]);
		}
		oc.setCheckYear(year);
		oc.setCheckMonth(month);
		oc.setKunnr(this.getUser().getIsOffice());
		oc.setStart(0);
		oc.setEnd(99999);
		orderCheckList=stockReportService.searchOrderCheckList(oc);
		
		if(orderCheck!=null && StringUtils.isNotEmpty(orderCheck.getState())){
			if(orderCheckList!=null &&orderCheckList.size()>0){
				if(StringUtils.isNotEmpty(orderCheckList.get(0).getState())){
					if(orderCheck.getState().equals("A")){
						int flag=0;
						for(OrderCheck result:orderCheckList){
							if("A".equals(result.getState())){
								flag=1;
								this.setFailMessage("本月已完成反馈，无需再次提交！");
								break;
							}
						}
						if(flag==0){
							this.setSuccessMessage("操作成功！");
							orderCheck.setCheckId(orderCheckList.get(0).getCheckId());
							orderCheck.setCheckUser("0");
							orderCheck.setCheckDesc(orderCheckList.get(0).getCheckDesc()+"/"+orderCheck.getCheckDesc());
							orderCheck.setIp(ip);
							orderCheck.setOtherIp(otherIp);
							stockReportService.updateOrderCheck(orderCheck);
						}
					}else{
						this.setSuccessMessage("操作成功！");
						orderCheck.setCheckId(orderCheckList.get(0).getCheckId());
						orderCheck.setCheckUser("0");
						orderCheck.setCheckDesc(orderCheckList.get(0).getCheckDesc()+"/"+orderCheck.getCheckDesc());
						orderCheck.setIp(ip);
						orderCheck.setOtherIp(otherIp);
						stockReportService.updateOrderCheck(orderCheck);
					}
				}else{
					this.setSuccessMessage("操作成功！");
					orderCheck.setCheckMonth(month);
					orderCheck.setCheckYear(year);
					orderCheck.setKunnr(this.getUser().getIsOffice());
					orderCheck.setCheckUser("0");
					orderCheck.setIp(ip);
					orderCheck.setOtherIp(otherIp);
					stockReportService.createOrderCheck(orderCheck);
				}
			}
		}
		return RESULT_MESSAGE;
	}
	
	public void exportForExcelOrderCheck() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		List<OrderCheck> stations=stockReportService.getStationIdByUserId(this.getUser().getUserId());
		orderCheck = new OrderCheck();
		if(orgIds!=null){
			orderCheck.setOrgIds(orgIds);
		}
//		if(orgId!=null){
//			orderCheck.setOrgId(orgId);
//		}else{
//			for(OrderCheck station : stations){
//				if("fykj_xn".equals(station.getStationId())){
//					orderCheck.setOrgId(Long.parseLong(this.getUser().getOrgId()));
//					break;
//				}
//			}
//		}
		
		orderCheck.setKunnr(kunnr);
		orderCheck.setState(state);
		orderCheck.setStartDate(startDate);
		orderCheck.setEndDate(endDate);
		orderCheck.setLastCheckDate(flag);
		orderCheck.setStart(0);
		orderCheck.setEnd(99999);
		orderCheckList = stockReportService.searchOrderCheckList(orderCheck);
		for(OrderCheck station : stations){
			if("fykj_xn".equals(station.getStationId())){
				for(OrderCheck oc : orderCheckList){
					if(oc.getCheckUser()!=null && oc.getCheckUser().equals("0")){
						oc.setCheckUser("1");
						stockReportService.updateOrderCheck(oc);
					}
				}
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
			String fileName = "对账单反馈信息.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 30);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 50);
			wsheet.setColumnView(10, 30);
		
			wsheet.addCell(new Label(0, 0, "大区"));
            wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商名称"));
			wsheet.addCell(new Label(5, 0, "账单年份"));
			wsheet.addCell(new Label(6, 0, "账单月份"));
			wsheet.addCell(new Label(7, 0, "当月最后查看时间"));
			wsheet.addCell(new Label(8, 0, "反馈意见"));
			wsheet.addCell(new Label(9, 0, "详细描述"));
			wsheet.addCell(new Label(10, 0, "提交时间"));
			boolean isIpDown=false;//ip下载权限
			if("88647".equals(this.getUser().getUserId())||"51240".equals(this.getUser().getOrgId())||"52166".equals(this.getUser().getOrgId())){//admin或者营销财务部,经销商服务组
				isIpDown=true;
			}
			if(isIpDown){
				wsheet.setColumnView(11, 20);//sl.zhu
				wsheet.addCell(new Label(11, 0, "ip地址"));
			}
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= orderCheckList.size(); i++) {
				if(orderCheckList.get(i-1).getKunnr()==null){
					orderCheckList.get(i-1).setKunnr("");
				}
				if(orderCheckList.get(i-1).getKunnrName()==null){
					orderCheckList.get(i-1).setKunnrName("");
				}
				if(orderCheckList.get(i-1).getKunnr()==null){
					orderCheckList.get(i-1).setKunnr("");
				}
				if(orderCheckList.get(i-1).getLastCheckDate()==null){
					orderCheckList.get(i-1).setLastCheckDate("");
				}
				if(orderCheckList.get(i-1).getState()==null){
					orderCheckList.get(i-1).setState("");
				}else if(orderCheckList.get(i-1).getState().equals("A")){
					orderCheckList.get(i-1).setState("确认无误");
				}else if(orderCheckList.get(i-1).getState().equals("B")){
					orderCheckList.get(i-1).setState("确认有差异");
				}else if(orderCheckList.get(i-1).getState().equals("C")){
					orderCheckList.get(i-1).setState("看不懂无法确认");
				}
				if(orderCheckList.get(i-1).getCheckDesc()==null){
					orderCheckList.get(i-1).setCheckDesc("");
				}
				if(orderCheckList.get(i-1).getCreateDate()==null){
					orderCheckList.get(i-1).setCreateDate("");
				}
				String year="";
				String month="";
				if(orderCheckList.get(i-1).getCheckYear()!=null &&
						orderCheckList.get(i-1).getCheckYear()!=0){
					year=orderCheckList.get(i-1).getCheckYear()+"";
				}
				if(orderCheckList.get(i-1).getCheckMonth()!=null &&
						orderCheckList.get(i-1).getCheckMonth()!=0){
					month=orderCheckList.get(i-1).getCheckMonth()+"";
				}
				
				wsheet.addCell(new Label(0,i,orderCheckList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,orderCheckList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,orderCheckList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,orderCheckList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,orderCheckList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,year,wcfF));
				wsheet.addCell(new Label(6,i,month,wcfF));
				wsheet.addCell(new Label(7,i,orderCheckList.get(i-1).getLastCheckDate(),wcfF));
				wsheet.addCell(new Label(8,i,orderCheckList.get(i-1).getState(),wcfF));
				wsheet.addCell(new Label(9,i,orderCheckList.get(i-1).getCheckDesc(),wcfF));
				wsheet.addCell(new Label(10,i,orderCheckList.get(i-1).getCreateDate(),wcfF));
				
				if(isIpDown){//ip下载权限
					if(orderCheckList.get(i-1).getIp()==null){
						orderCheckList.get(i-1).setIp("");
					}
					wsheet.addCell(new Label(11,i,orderCheckList.get(i-1).getIp(),wcfF));
				}
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	
	public String toKunnrManager(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toKunnrManager";
	}
	
	public String toKunnrManagerKunnrSum(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toKunnrManagerKunnrSum";
	}
	
	public String toKunnrManagerSku(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toKunnrManagerSku";
	}
	
	public String toStockSafety(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toStockSafety";
	}
	
	public String toSearchStockStateManager(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toSearchStockStateManager";
	}
	
	public String toSearchOrderFollowByKunnr(){
		try {
			kunnrName = new String(kunnrName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		System.out.println(kunnrName);
		return "toSearchOrderFollowByKunnr";
	}
	public String toSearchStockWarning(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		return "toSearchStockWarning";
	}
	public String toSkuUnitCoefficient(){
		return "toSkuUnitCoefficient";
	}
	
	public String searchStockStateManagerList() throws ParseException{
		stockReport = new StockReport();
		//stockReport.setUserName(userName);
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setCategoryId(categoryId);
		//stockReport.setKunnrName(kunnrName);
		//stockReport.setSkuName(skuName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(checkTime!=null && checkTime!=""){
		    stockReport.setEndDate(checkTime);
		    //System.out.println(checkTime);
		    java.sql.Date dd=java.sql.Date.valueOf(checkTime);
		    Calendar date = Calendar.getInstance();
		    date.setTime(dd);
		    stockReport.setSearchYear(date.get(Calendar.YEAR));
		    stockReport.setSearchMonth(date.get(Calendar.MONTH)+1);
		   // System.out.println(stockReport.getSearchYear()+":"+stockReport.getSearchMonth());
		}else{
			stockReport.setEndDate(sdf.format(new Date()));
			Calendar date = Calendar.getInstance();
		    stockReport.setSearchYear(date.get(Calendar.YEAR));
		    stockReport.setSearchMonth(date.get(Calendar.MONTH)+1);
		   // System.out.println(stockReport.getSearchYear()+":"+stockReport.getSearchMonth());
		}
		//stockReport.setFlag(flag);
		//stockReport.setUserType(userType);
		
		int total1 = stockReportService.getStockStateManagerCount(stockReport);
		if(total1>0){
			List<StockReport> resultList = stockReportService.getStockStateManagerList(stockReport);
			List<StockReport> resultList1=stockReportService.getStockListFromSAP(resultList);
			stockReportService.createStockStateManager(resultList1);
			//stockReportList=stockReportService.getStockListFromSAP(resultList);
		}/**
		stockReport.setStart(getStart());
		stockReport.setEnd(10);
	    total = stockReportService.searchStockStateManagerCount(stockReport);
		if(total>0){
			stockReportList=stockReportService.searchStockStateManagerList(stockReport);
		}*/
		this.setSuccessMessage("操作成功！");
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"orgRegion","orgProvince","orgCity","orgRegionId","orgProvinceId","orgCityId","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","searchYear","searchMonth","coefficient",
			"categoryId","categoryName","quantity","safeQuantityMax","safeQuantityMin","onWayNum","flag","planNotSend","notPlan","needPlan","needOrder" },total = "total")
	public String searchStockStateManagerList1() throws ParseException{
		stockReport = new StockReport();
		//stockReport.setUserName(userName);
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setCategoryId(categoryId);
		
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
	    total = stockReportService.searchStockStateManagerCount(stockReport);
	    if(total>0){
			stockReportList=stockReportService.searchStockStateManagerList(stockReport);
			StockReport sr=stockReportService.searchStockStateManagerTotal(stockReport);
			sr.setOrgRegion("标箱合计： ");
			stockReportList.add(0, sr);
		}
		return JSON;
	}
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = {"orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate",
			"categoryId","categoryName","quantity","flag","planNotSend","endDate","orderDate","needOrder","orderDate","factOrder" },total = "total")
	public String searchStockWarningList() throws ParseException{
		stockReport = new StockReport();
		stockReport.setUserName(userName);
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setCategoryId(categoryId);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(endDate!=null && endDate!=""){
		    stockReport.setEndDate(endDate);
		}else{
			
			stockReport.setEndDate(sdf1.format(new Date()));
		}
		stockReport.setFlag("S");
		//stockReport.setUserType(userType);
		//stockReport.setStart(getStart());
		//stockReport.setEnd(getEnd());
		total = stockReportService.getStockWarningListCount(stockReport);
		if(total>0){
			//List<StockReport> resultList = stockReportService.getStockWarningList(stockReport);
			stockReportList = stockReportService.getStockWarningList(stockReport);
			
			//stockReportList=stockReportService.getOrderQuantityFromSAP(resultList);
			List<StockReport> resultList = stockReportService.getOrderQuantityFromSAP(stockReportList);
			String sapKunnr="0000000000";
			for(StockReport s:stockReportList){
				Date matchedDate=null;
				Double matchedQuantity=0.0;
				String expKunnr=sapKunnr.substring(s.getKunnr().length())+s.getKunnr();
				for (StockReport ss:resultList) {
					if(ss.getKunnr().equals(expKunnr)){
						Double q=ss.getFactOrder();
						Date d=sdf1.parse(ss.getOrderDate());
						if(q!=null){
							matchedQuantity+=q;
						}
						if(matchedDate==null){
							matchedDate=d;
						}else{
							//System.out.println(matchedDate.getTime()+"***"+d.getTime());
							matchedDate=(matchedDate.getTime()>d.getTime()?matchedDate:d);
						}
					}
				}
				//System.out.println(s.getEndDate());
				s.setFactOrder(matchedQuantity/30.0);
				s.setOrderDate(matchedDate==null?"":sdf1.format(matchedDate).toString());
				
			}
		}
		
		return JSON;
	}
	
	public String setStockWarningList(){
		stockReport=new StockReport();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sysDate=new Date();
		String endDate=sdf1.format(sysDate);
		stockReport.setFlag("S");
		stockReport.setEndDate(sdf1.format(sysDate));
		//List<StockReport> resultList = stockReportService.getStockWarningList(stockReport);
		stockReportList = stockReportService.getStockWarningDetailList(stockReport);
		if(stockReportList.size()>0){
			List<StockReport> resultList1=stockReportService.getOrderQuantityFromSAP(stockReportList);
			String sapKunnr="0000000000";
				String sapSku="000000000000000000";
				for(StockReport s:stockReportList){
					s.setFlag("H");
					String expKunnr=sapKunnr.substring(s.getKunnr().length())+s.getKunnr();
					String expSku=sapSku.substring(s.getSkuSapCode().length())+s.getSkuSapCode();
					for(StockReport ss:resultList1){
						if(expKunnr.equals(ss.getKunnr()) && expSku.equals(ss.getSkuSapCode())){
							s.setFactOrder(ss.getFactOrder());
							s.setOrderDate(ss.getOrderDate());
							break;
						}
					}
				}
			
			stockReportService.modifyStockWarning(stockReportList);
		}
		
		Calendar date = Calendar.getInstance();
		date.setTime(sysDate);
		stockReport=new StockReport();
	    stockReport.setSearchYear(date.get(Calendar.YEAR));
	    stockReport.setSearchMonth(date.get(Calendar.MONTH)+1);
		stockReport.setEndDate(endDate);
		total = stockReportService.getStockStateManagerCount(stockReport);
		if(total>0){
			stockReport.setStart(getStart());
			stockReport.setEnd(total);
			List<StockReport> resultList = stockReportService.getStockStateManagerList(stockReport);
			stockReportList=stockReportService.getStockListFromSAP(resultList);
			//List<StockReport> orderList=stockReportService.get
		}
		for(StockReport s:stockReportList){
			s.setEndDate(endDate);
			s.setUserId(Long.parseLong(this.getUser().getUserId()));
			s.setUserName(this.getUser().getUserName());
			if(s.getNeedOrder()!=null && s.getCoefficient()!=null){
				s.setNeedOrder(s.getNeedOrder()*(s.getCoefficient()));
			}
		}
		
		stockReportService.createStockWarning(stockReportList);
		this.setSuccessMessage("操作成功！");
		return RESULT_MESSAGE;
	}
	public String sendStockWarningMessage(){
		String msg1="你好：\r\n        截止";//+年月日
		String msg2="， 你所辖范围内\"";//+经销商名称
		String msg3="\"的上期需求上单量";//+上期需求
		String msg4=", 上期实际上单量";//+上期实际
		String msg5=", 完成率";//+百分比
		String msg6="%; 本期需上单量为";//+本期需求
		String msg7="。明细请查询EXP—>经销商管理平台—>经销商库存管理。请通过经销商管理平台实时关注经销商对应库存和上单情况。谢谢配合！（以上单位均为标箱）";
		String str=",split,";
		stockReport=new StockReport();
		List<SendInfo> sendInfoList=new ArrayList<SendInfo>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("##0.00");  
		Date sysDate=new Date();
		//String endDate=sdf1.format(sysDate);
		stockReport.setFlag("H");
		stockReport.setEndDate(sdf1.format(sysDate));
		//查上期
		List<StockReport> result1 = stockReportService.getStockWarningList(stockReport);
		
		//查本期及业务管理人员信息
		stockReport.setFlag("S");
		List<StockReport> result2 = stockReportService.getStockWarningList(stockReport);
		
		if(result2.size()>0){
			List<StockReport> result3=stockReportService.searchKunnrManagerMessage(result2);
			for(StockReport s:result3){
				String mMobile=s.getManagerMobile();
				String pMobile=s.getProvinceMobile();
				String msg="\r\n发送对象：";
				if((mMobile !=null && mMobile!="") || (pMobile !="" && pMobile!=null)){
					SendInfo si=new SendInfo();
					boolean isMatched=false;
					for(StockReport ss:result1){
						if(s.getKunnr().equals(ss.getKunnr())){
//							si.setContent(msg1+sdf2.format(sysDate)+msg2+s.getKunnrName()
//									+msg3+ss.getNeedOrder()+msg4+ss.getFactOrder()+
//									msg5+(!(ss.getNeedOrder()>0)?0.0:df.format(3500/(ss.getNeedOrder())*100.0))+
//											msg6+s.getNeedOrder()+msg7);
							si.setContent(msg1+sdf2.format(sysDate)+msg2+s.getKunnrName()
									+msg3+ss.getNeedOrder()+msg4+ss.getFactOrder()+
									msg5+(!(ss.getNeedOrder()>0)?"0.00":df.format(ss.getFactOrder()/(ss.getNeedOrder())*100.0))+
											msg6+s.getNeedOrder()+msg7);
							isMatched=true;
							break;
						}
					}
					if(!isMatched){
						si.setContent(msg1+sdf2.format(sysDate)+msg2+s.getKunnrName()
								+msg3+"0"+msg4+"0"+msg5+"0"+msg6+s.getNeedOrder()+msg7);
					}
					si.setMobile((pMobile !="" && pMobile!=null)?pMobile:mMobile);
					//System.out.println(si.getContent());
					/**
					if(mMobile !=null && mMobile!=""){
						msg+=s.getBusinessManager();
						msg+="，手机：";
						msg+=s.getManagerMobile();
						msg+=",\r\n发送内容：\r\n";
						msg+=si.getContent();
					}else{
						msg+=s.getProvinceManager();
						msg+="，手机：";
						msg+=s.getManagerMobile();
						msg+=",\r\n发送内容：\r\n";
						msg+=si.getContent();
					}
					str+=msg;
					str+=",split,";*/
					sendInfoList.add(si);
				   }
				}
			}
			BooleanResult booleanResult = smsServiceHessian.sendMessage(sendInfoList);
			if(booleanResult.getResult()){
					this.setSuccessMessage(booleanResult.getCode());
				}else{
				this.setFailMessage(booleanResult.getCode());
			}
		return RESULT_MESSAGE; 
	}
	public String toSearchOrderFollow(){
		return "toSearchOrderFollow";
	}
	
	public ISmsInterfaceService getSmsServiceHessian() {
		return smsServiceHessian;
	}

	public void setSmsServiceHessian(ISmsInterfaceService smsServiceHessian) {
		this.smsServiceHessian = smsServiceHessian;
	}

	public String toKunnrManagerDate(){
		return "toKunnrManagerDate";
	}
	
	public String toStockWarning(){
		return "toStockWarning";
	}
	
	public String toKunnrSkuPercent(){
		return "toKunnrSkuPercent";
	}
	
	@PermissionSearch
	@JsonResult(field = "skuUnitList",include = {"skuId","skuName",
			"cgId","matterNum","unitCoefficient","categoryName","status"},total="total")
	public String searchSkuUnitCoefficientList(){
		skuUnit=new SkuUnit();
		skuUnit.setSkuId(skuId);
		skuUnit.setSkuName(skuName);
		skuUnit.setCgId(categoryId);
		skuUnit.setCategoryName(categoryName);
		skuUnit.setStatus(status);
		skuUnit.setStart(getStart());
		skuUnit.setEnd(getEnd());
		total=stockReportService.searchSkuUnitCoefficientListCount(skuUnit);
		if(total>0){
			skuUnitList = stockReportService.searchSkuUnitCoefficientList(skuUnit);
		}
		return JSON;
	}
	
	public String createSkuUnitCoefficient(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		stockReportService.createUnitCoefficientRFC();
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		this.setSuccessMessage("操作成功！");
		return RESULT_MESSAGE;
	}
	
	public void exportForExcelSkuUnitCoefficient() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		skuUnit=new SkuUnit();
		skuUnit.setSkuId(skuId);
		skuUnit.setSkuName(skuName);
		skuUnit.setCgId(categoryId);
		skuUnit.setCategoryName(categoryName);
		skuUnit.setStatus(status);
		skuUnit.setStart(0);
		skuUnit.setEnd(999999);
		skuUnitList = stockReportService.searchSkuUnitCoefficientList(skuUnit);
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "sku品类对照表.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 30);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			
			
			wsheet.addCell(new Label(0, 0, "skuId"));
			wsheet.addCell(new Label(1, 0, "sku名称"));
			wsheet.addCell(new Label(2, 0, "品类Id"));
			wsheet.addCell(new Label(3, 0, "品类名称"));
			wsheet.addCell(new Label(4, 0, "物料号"));
			wsheet.addCell(new Label(5, 0, "标箱系数"));
			wsheet.addCell(new Label(6, 0, "状态"));
			
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= skuUnitList.size(); i++) {
				String unitCoefficient = null;
				if(skuUnitList.get(i-1).getUnitCoefficient()!=null){
					unitCoefficient = skuUnitList.get(i-1).getUnitCoefficient().toString();
				}
				String status = null;
				if(skuUnitList.get(i-1).getStatus()!=null){
					if("U".equals(skuUnitList.get(i-1).getStatus().toString())){
						status = "正常";
					}else{
						status = "已禁用";
					}
				}
				wsheet.addCell(new Label(0,i,skuUnitList.get(i-1).getSkuId().toString(),wcfF));
				wsheet.addCell(new Label(1,i,skuUnitList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Label(2,i,skuUnitList.get(i-1).getCgId().toString(),wcfF));
				wsheet.addCell(new Label(3,i,skuUnitList.get(i-1).getCategoryName(),wcfF));
				wsheet.addCell(new Label(4,i,skuUnitList.get(i-1).getMatterNum(),wcfF));
				wsheet.addCell(new Label(5,i,unitCoefficient,wcfF));
				wsheet.addCell(new Label(6,i,status,wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	@PermissionSearch
	@JsonResult(field = "stockDateList",include = { "id","checkTime","startDateStr",
			"endDateStr","modifyDate"})
	public String searchKunnrManagerDateList(){
		stockDateList = new ArrayList<StockDate>();
		stockDate = new StockDate();
		stockDateList = stockReportService.searchKunnrManagerDate(stockDate);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String searchKunnrManagerDateCount(){
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		stockDate = new StockDate();
		stockDate.setId(Integer.parseInt(sdf.format(new Date())));
		stockDate.setStartDate(new Date());
		total = stockReportService.searchKunnrManagerDateCount(stockDate);
		return JSON;
	}

	public String updateKunnrManagerDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(checkTimes!=null && checkTimes.length!=0){
			for(int i=0;i<checkTimes.length;i++){
				String checkTime="";
				if(checkTimes[i]!=null){
					checkTime=sdf.format(checkTimes[i]);
				}
				stockDate = new StockDate();
				stockDate.setId(i+1);
				stockDate.setCheckTime(checkTime);
				total = stockReportService.updateKunnrManagerDate(stockDate);
			}
		}
		this.setSuccessMessage("修改成功!");
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgRegion","orgProvince","orgCity","orgId","kunnr",
			"kunnrName","year","month","skuId","skuName","cgId","categoryName","salesPlan","salesPlanNext",
			"salesPlanLast","lastEstimateStock","stockSafety","stockSafetyNext","productionPlan","productionPlanNext",
			"salesReal","salesPlanFinal","salesPlanPercent","salesLastYearLast","salesLastYear","salesLastYearNext",
			"salesLastMonth","salesLastMonthFinal","salesLastMonthPercent","salesYear","salesYearGoalEstimate",
			"salesYearGoal","salesYearPercent","stockSafetyLimit","stockSafetyNextLimit","productionPlanLast"},total="total")
	public String searchKunnrManagerList(){
		kunnrManagerList = new ArrayList<KunnrManager>();
		kunnrManager = new KunnrManager();
		if(orgId!=50919 || StringUtils.isNotEmpty(kunnr)){
			if(orgId!=null){
				kunnrManager.setOrgId(orgId);
			}
			if(StringUtils.isNotEmpty(startDate)){
				kunnrManager.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				kunnrManager.setEndDate(endDate);
			}
			kunnrManager.setKunnr(kunnr);
			kunnrManager.setSkuId(skuId);
			kunnrManager.setCgId(categoryId);
			kunnrManager.setStart(getStart());
			kunnrManager.setEnd(getEnd());
			total=stockReportService.searchKunnrManagerListCount(kunnrManager);
			if(total>0){
				List<KunnrManager> list = stockReportService.searchKunnrManagerList(kunnrManager);
				KunnrManager km = stockReportService.getKunnrManagerListSum(kunnrManager);
				km.setKunnrName("合计（标箱）");
				kunnrManagerList.add(km);
				for(KunnrManager kmList : list){
					kunnrManagerList.add(kmList);
				}
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgRegion","orgProvince","orgCity","orgId","kunnr",
			"kunnrName","year","month","skuId","skuName","cgId","categoryName","salesPlan","salesPlanNext",
			"salesPlanLast","lastEstimateStock","stockSafety","stockSafetyNext","productionPlan","productionPlanNext",
			"salesReal","salesPlanFinal","salesPlanPercent","salesLastYearLast","salesLastYear","salesLastYearNext",
			"salesLastMonth","salesLastMonthFinal","salesLastMonthPercent","salesYear","salesYearGoalEstimate",
			"salesYearGoal","salesYearPercent","stockSafetyLimit","stockSafetyNextLimit","productionPlanLast"},total="total")
	public String searchKunnrManagerKunnrSumList(){
		if(orgId!=50919 || StringUtils.isNotEmpty(kunnr)){
			kunnrManagerList = new ArrayList<KunnrManager>();
			kunnrManager = new KunnrManager();
			String sql="";
			if(orgId!=null && StringUtils.isEmpty(kunnr)){
				int level=stockReportService.getOrgCityByOrgId(orgId);
				if(level==4){
					sql="I";
				}else if(level==3){
					sql="D";
				}else if(level<3){
					sql="C";
				}
			}
			if(orgId!=null){
				kunnrManager.setOrgId(orgId);
			}
			if(StringUtils.isNotEmpty(startDate)){
				kunnrManager.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				kunnrManager.setEndDate(endDate);
			}
			kunnrManager.setCgId(categoryId);
			kunnrManager.setStatus(stockFlag);
			kunnrManager.setKunnr(kunnr);
			kunnrManager.setStart(getStart());
			kunnrManager.setEnd(getEnd());
			if(StringUtils.isNotEmpty(sql)){
				kunnrManager.setSql(sql);
				total=stockReportService.searchKunnrManagerKunnrByOrgSumListCount(kunnrManager);
				if(total>0){
					List<KunnrManager> list = stockReportService.searchKunnrManagerKunnrByOrgSumList(kunnrManager);
					KunnrManager km = stockReportService.getKunnrManagerListSum(kunnrManager);
					km.setKunnrName("合计（标箱）");
					kunnrManagerList.add(km);
					for(KunnrManager kmList : list){
						kunnrManagerList.add(kmList);
					}
				}
			}else{
				total=stockReportService.searchKunnrManagerKunnrSumListCount(kunnrManager);
				if(total>0){
					List<KunnrManager> list = stockReportService.searchKunnrManagerKunnrSumList(kunnrManager);				
					KunnrManager km = stockReportService.getKunnrManagerListSum(kunnrManager);
					km.setKunnrName("合计（标箱）");
					kunnrManagerList.add(km);
					for(KunnrManager kmList : list){
						kunnrManagerList.add(kmList);
					}
				}
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "orderFollowList",include = {"orgRegion","orgProvince","orgCity",
			"orgId","kunnr","kunnrName","skuId","skuName","categoryId","categoryName","MATNR",
			"ERDAT","AUDAT","POSNR1","KWMENG","PEDTR","VBELN2","TKNUM","WADAT_IST","PODAT",
			"VBELN3","WBSTK","VBELN1","POSNR2","POSNR3","PDSTK","outOfDate"},total="total")
	public String searchOrderFollowList(){
		if(StringUtils.isNotEmpty(kunagId)||((orgId!=null || StringUtils.isNotEmpty(kunnr)) && 
				(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)))){
			orderFollowList = new ArrayList<OrderFollow>();
			orderFollow = new OrderFollow();
			orderFollow.setOrgId(orgId);
			orderFollow.setKunnr(kunnr);
			orderFollow.setKunag(kunagId);
			orderFollow.setSkuId(skuId);
			orderFollow.setCategoryId(categoryId);
			orderFollow.setOrderState(state);
			if(StringUtils.isNotEmpty(startDate)){
				startDate=startDate.replace("-", "");
				orderFollow.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				endDate=endDate.replace("-", "");
				orderFollow.setEndDate(endDate);
			}
			orderFollow.setStart(getStart());
			orderFollow.setEnd(getEnd());
			total=stockReportService.searchOrderFollowListCount(orderFollow);
			if(total>0){
				orderFollowList = stockReportService.searchOrderFollowList(orderFollow);
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "orderFollow",include = {"POSNR1","POSNR2"})
	public String getOrderFollowSum(){
		if(StringUtils.isNotEmpty(kunagId)||((orgId!=null || StringUtils.isNotEmpty(kunnr)) && 
				(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)))){
			orderFollowList = new ArrayList<OrderFollow>();
			orderFollow = new OrderFollow();
			orderFollow.setOrgId(orgId);
			orderFollow.setKunnr(kunnr);
			orderFollow.setKunag(kunagId);
			orderFollow.setSkuId(skuId);
			orderFollow.setCategoryId(categoryId);
			orderFollow.setOrderState(state);
			orderFollow.setStartDate(startDate);
			orderFollow.setEndDate(endDate);
			orderFollow=stockReportService.getOrderFollowSum(orderFollow);
		}
		return JSON;
	}
	
	public String exportKunnrManagerCsv() {
		OutputStream os = null;
		String report_name = "经销商分销预估导入模板";
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
			linedata.append("品类编号");
			linedata.append(",");
			linedata.append("年份");
			linedata.append(",");
			linedata.append("月份");
			linedata.append(",");
			linedata.append("当月分销预估（终板）");
			linedata.append(",");
			linedata.append("次月分销预估（初版）");
			linedata.append(",");
			linedata.append("本月安全库存（上限）");
			linedata.append(",");
			linedata.append("本月安全库存（下限）");
			linedata.append(",");
			linedata.append("次月安全库存（上限）");
			linedata.append(",");
			linedata.append("次月安全库存（下限）");
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
	
	public String exportProductionPlanCsv() {
		OutputStream os = null;
		String report_name = "经销商要货计划导入模板";
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
			linedata.append("品类编号");
			linedata.append(",");
			linedata.append("年份");
			linedata.append(",");
			linedata.append("月份");
			linedata.append(",");
			linedata.append("当月要货计划（终板）");
			linedata.append(",");
			linedata.append("次月要货计划（初版）");
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
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgRegion","orgProvince","orgCity","orgId","kunnr",
			"kunnrName","year","month","skuId","skuName","cgId","categoryName","salesPlan","salesPlanNext",
			"salesPlanLast","lastEstimateStock","stockSafety","stockSafetyNext","productionPlan","productionPlanNext",
			"salesReal","salesPlanFinal","salesPlanPercent","salesLastYearLast","salesLastYear","salesLastYearNext",
			"salesLastMonth","salesLastMonthFinal","salesLastMonthPercent","salesYear","salesYearGoalEstimate",
			"salesYearGoal","salesYearPercent","stockSafetyLimit","stockSafetyNextLimit","productionPlanLast"},total="total")
	public String searchKunnrManagerSkuList(){
		if(StringUtils.isNotEmpty(kunnr)){
			kunnrManagerList = new ArrayList<KunnrManager>();
			kunnrManager = new KunnrManager();
			if(orgId!=null){
				kunnrManager.setOrgId(orgId);
			}
			if(StringUtils.isNotEmpty(startDate)){
				kunnrManager.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				kunnrManager.setEndDate(endDate);
			}
			kunnrManager.setKunnr(kunnr);
			kunnrManager.setSkuId(skuId);
			kunnrManager.setCgId(categoryId);
			kunnrManager.setStart(getStart());
			kunnrManager.setEnd(getEnd());
			total=stockReportService.searchKunnrManagerSkuListCount(kunnrManager);
			if(total>0){
				kunnrManagerList = stockReportService.searchKunnrManagerSkuList(kunnrManager);
			}
		}else if(orgId!=null){
		}
		return JSON;
	}
	
	public void exportForExcelKunnrManager() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		kunnrManagerList = new ArrayList<KunnrManager>();
		kunnrManager = new KunnrManager();
		if(orgId!=null){
			kunnrManager.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(startDate)){
			kunnrManager.setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			kunnrManager.setEndDate(endDate);
		}
		kunnrManager.setKunnr(kunnr);
		kunnrManager.setSkuId(skuId);
		kunnrManager.setCgId(categoryId);
		kunnrManager.setStart(0);
		kunnrManager.setEnd(999999);
		kunnrManagerList = stockReportService.searchKunnrManagerListForExcel(kunnrManager);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "分销预估.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 20);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 10);
			wsheet.setColumnView(10, 10);
			wsheet.setColumnView(11, 10);
			wsheet.setColumnView(12, 10);
			wsheet.setColumnView(13, 10);
			wsheet.setColumnView(14, 10);
			wsheet.setColumnView(15, 10);
			wsheet.setColumnView(16, 10);
			wsheet.setColumnView(17, 10);
			wsheet.setColumnView(18, 10);
//			wsheet.setColumnView(19, 10);
//			wsheet.setColumnView(20, 10);
//			wsheet.setColumnView(21, 10);
//			wsheet.setColumnView(22, 10);
//			wsheet.setColumnView(23, 10);
//			wsheet.setColumnView(24, 10);
//			wsheet.setColumnView(25, 10);
//			wsheet.setColumnView(26, 10);
//			wsheet.setColumnView(27, 10);
//			wsheet.setColumnView(28, 10);
//			wsheet.setColumnView(29, 10);
//			wsheet.setColumnView(30, 10);
			
			for(int i=0;i<9;i++){
				wsheet.mergeCells(i, 0, i, 1);
			}
			wsheet.mergeCells(9, 0, 11, 0);
			wsheet.mergeCells(13, 0, 16, 0);
			wsheet.mergeCells(17, 0, 18, 0);
//			wsheet.mergeCells(19, 0, 21, 0);
//			wsheet.mergeCells(22, 0, 24, 0);
//			wsheet.mergeCells(25, 0, 27, 0);
//			wsheet.mergeCells(28, 0, 30, 0);
			wsheet.addCell(new Label(9, 0, "目标提报"));
			wsheet.addCell(new Label(12, 0, "预估库存"));
			wsheet.addCell(new Label(13, 0, "安全库存"));
			wsheet.addCell(new Label(17, 0, "要货计划"));
//			wsheet.addCell(new Label(19, 0, "当月达成"));
//			wsheet.addCell(new Label(22, 0, "同期分销"));
//			wsheet.addCell(new Label(25, 0, "上月分销"));
//			wsheet.addCell(new Label(28, 0, "年累计分销"));
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商名称"));
			wsheet.addCell(new Label(5, 0, "品类"));
            wsheet.addCell(new Label(6, 0, "品项"));
			wsheet.addCell(new Label(7, 0, "年"));
			wsheet.addCell(new Label(8, 0, "月"));
			wsheet.addCell(new Label(9, 1, "当月分销预估（终版）"));
			wsheet.addCell(new Label(10, 1, "次月分销预估（初版）"));
			wsheet.addCell(new Label(11, 1, "当月分销预估（初版展示）"));
			wsheet.addCell(new Label(12, 1, "预估上月底库存"));
			wsheet.addCell(new Label(13, 1, "本月上限"));
			wsheet.addCell(new Label(14, 1, "本月下限"));
			wsheet.addCell(new Label(15, 1, "次月上限"));
			wsheet.addCell(new Label(16, 1, "次月下限"));
			wsheet.addCell(new Label(17, 1, "当月要货计划"));
			wsheet.addCell(new Label(18, 1, "次月要货计划"));
//			wsheet.addCell(new Label(19, 1, "当月实际分销量"));
//			wsheet.addCell(new Label(20, 1, "当月最终分销预估"));
//			wsheet.addCell(new Label(21, 1, "当月分销达成"));
//			wsheet.addCell(new Label(22, 1, "同期上个月实际分销"));
//			wsheet.addCell(new Label(23, 1, "同期当月实际分销"));
//			wsheet.addCell(new Label(24, 1, "同期次月实际分销"));
//			wsheet.addCell(new Label(25, 1, "上月实际分销"));
//			wsheet.addCell(new Label(26, 1, "上月最终分销预估"));
//			wsheet.addCell(new Label(27, 1, "上月达成"));
//			wsheet.addCell(new Label(28, 1, "年累计分销量"));
//			wsheet.addCell(new Label(29, 1, "年累计分销目标"));
//			wsheet.addCell(new Label(30, 1, "年累计达成%"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= kunnrManagerList.size(); i++) {
				
				String salesPlan="";  //当月分销计划(终版)
				String salesPlanNext="";  //次月分销计划(初版)
				String salesPlanLast="";  //当月分销计划(初版展示)
				String lastEstimateStock="";  //预估上月底库存
				String stockSafetyLimit="";  //本月安全库存(上限)
				String stockSafety="";  //本月安全库存
				String stockSafetyNextLimit="";  //次月安全库存(上限)
				String stockSafetyNext="";  //次月安全库存
				String productionPlan="";  //当月要货计划
				String productionPlanNext="";  //次月要货计划
//				String salesReal="";  //当月实际分销量
//				String salesPlanFinal="";  //当月最终分销预估
//				String salesPlanPercent="";  //当月分销达成
//				String salesLastYearLast="";  //同期上一个月实际分销
//				String salesLastYear="";  //同期当月实际分销
//				String salesLastYearNext="";  //同期次月实际分销
//				String salesLastMonth="";  //上月实际分销
//				String salesLastMonthFinal="";  //上月最终分销预估
//				String salesLastMonthPercent="";  //上月达成
//				String salesYear="";  //年累计分销量
//				String salesYearGoalEstimate="";  //年累计分销目标(滚动终版目标)
//				String salesYearPercent="";  //年累计达成
				if(kunnrManagerList.get(i-1).getSalesPlan()!=null){
					salesPlan=kunnrManagerList.get(i-1).getSalesPlan().toString();
				}
				if(kunnrManagerList.get(i-1).getSalesPlanNext()!=null){
					salesPlanNext=kunnrManagerList.get(i-1).getSalesPlanNext().toString();
				}
				if(kunnrManagerList.get(i-1).getSalesPlanLast()!=null){
					salesPlanLast=kunnrManagerList.get(i-1).getSalesPlanLast().toString();
				}
				if(kunnrManagerList.get(i-1).getLastEstimateStock()!=null){
					lastEstimateStock=kunnrManagerList.get(i-1).getLastEstimateStock().toString();
				}
				if(kunnrManagerList.get(i-1).getStockSafetyLimit()!=null){
					stockSafetyLimit=kunnrManagerList.get(i-1).getStockSafetyLimit().toString();
				}
				if(kunnrManagerList.get(i-1).getStockSafety()!=null){
					stockSafety=kunnrManagerList.get(i-1).getStockSafety().toString();
				}
				if(kunnrManagerList.get(i-1).getStockSafetyNextLimit()!=null){
					stockSafetyNextLimit=kunnrManagerList.get(i-1).getStockSafetyNextLimit().toString();
				}
				if(kunnrManagerList.get(i-1).getStockSafetyNext()!=null){
					stockSafetyNext=kunnrManagerList.get(i-1).getStockSafetyNext().toString();
				}
				if(kunnrManagerList.get(i-1).getProductionPlan()!=null){
					productionPlan=kunnrManagerList.get(i-1).getProductionPlan().toString();
				}
				if(kunnrManagerList.get(i-1).getProductionPlanNext()!=null){
					productionPlanNext=kunnrManagerList.get(i-1).getProductionPlanNext().toString();
				}
//				if(kunnrManagerList.get(i-1).getSalesReal()!=null){
//					salesReal=kunnrManagerList.get(i-1).getSalesReal().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesPlanFinal()!=null){
//					salesPlanFinal=kunnrManagerList.get(i-1).getSalesPlanFinal().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesPlanPercent()!=null){
//					salesPlanPercent=kunnrManagerList.get(i-1).getSalesPlanPercent().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastYearLast()!=null){
//					salesLastYearLast=kunnrManagerList.get(i-1).getSalesLastYearLast().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastYear()!=null){
//					salesLastYear=kunnrManagerList.get(i-1).getSalesLastYear().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastYearNext()!=null){
//					salesLastYearNext=kunnrManagerList.get(i-1).getSalesLastYearNext().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastMonth()!=null){
//					salesLastMonth=kunnrManagerList.get(i-1).getSalesLastMonth().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastMonthFinal()!=null){
//					salesLastMonthFinal=kunnrManagerList.get(i-1).getSalesLastMonthFinal().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesLastMonthPercent()!=null){
//					salesLastMonthPercent=kunnrManagerList.get(i-1).getSalesLastMonthPercent().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesYear()!=null){
//					salesYear=kunnrManagerList.get(i-1).getSalesYear().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesYearGoalEstimate()!=null){
//					salesYearGoalEstimate=kunnrManagerList.get(i-1).getSalesYearGoalEstimate().toString();
//				}
//				if(kunnrManagerList.get(i-1).getSalesYearPercent()!=null){
//					salesYearPercent=kunnrManagerList.get(i-1).getSalesYearPercent().toString();
//				}
				
				
				wsheet.addCell(new Label(0,i+1,kunnrManagerList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i+1,kunnrManagerList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i+1,kunnrManagerList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i+1,kunnrManagerList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i+1,kunnrManagerList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i+1,kunnrManagerList.get(i-1).getCategoryName(),wcfF));
				wsheet.addCell(new Label(6,i+1,kunnrManagerList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Label(7,i+1,kunnrManagerList.get(i-1).getYear().toString(),wcfF));
				wsheet.addCell(new Label(8,i+1,kunnrManagerList.get(i-1).getMonth().toString(),wcfF));
				wsheet.addCell(new Label(9,i+1,salesPlan,wcfF));
				wsheet.addCell(new Label(10,i+1,salesPlanNext,wcfF));
				wsheet.addCell(new Label(11,i+1,salesPlanLast,wcfF));
				wsheet.addCell(new Label(12,i+1,lastEstimateStock,wcfF));
				wsheet.addCell(new Label(13,i+1,stockSafetyLimit,wcfF));
				wsheet.addCell(new Label(14,i+1,stockSafety,wcfF));
				wsheet.addCell(new Label(15,i+1,stockSafetyNextLimit,wcfF));
				wsheet.addCell(new Label(16,i+1,stockSafetyNext,wcfF));
				wsheet.addCell(new Label(17,i+1,productionPlan,wcfF));
				wsheet.addCell(new Label(18,i+1,productionPlanNext,wcfF));
//				wsheet.addCell(new Label(19,i+1,salesReal,wcfF));
//				wsheet.addCell(new Label(20,i+1,salesPlanFinal,wcfF));
//				wsheet.addCell(new Label(21,i+1,salesPlanPercent,wcfF));
//				wsheet.addCell(new Label(22,i+1,salesLastYearLast,wcfF));
//				wsheet.addCell(new Label(23,i+1,salesLastYear,wcfF));
//				wsheet.addCell(new Label(24,i+1,salesLastYearNext,wcfF));
//				wsheet.addCell(new Label(25,i+1,salesLastMonth,wcfF));
//				wsheet.addCell(new Label(26,i+1,salesLastMonthFinal,wcfF));
//				wsheet.addCell(new Label(27,i+1,salesLastMonthPercent,wcfF));
//				wsheet.addCell(new Label(28,i+1,salesYear,wcfF));
//				wsheet.addCell(new Label(29,i+1,salesYearGoalEstimate,wcfF));
//				wsheet.addCell(new Label(30,i+1,salesYearPercent,wcfF));
			}
			wbook.write();
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					e.printStackTrace();				
					}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();				
					}
				os = null;
			}
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		}
	}
	
	

	private String appUrl;
	private UserUtil userUtil;
	private IWfeService wfeServiceHessian;
	private SkuDistribution skuDistribution;
	private List<SkuDistribution> skuDistributionList;
	private File[] upload;
	private String[] uploadFileName;
	private Long eventId;
	private Long nextUserId;
	private String eventTitle;
	private String xmlFilePath;
	private String crm_xmlFilePath;
	private String userList;
	private BooleanResult executeResult;// 事务处理结束返回信息
	private String subFolders;
	private String modifyFlag;
	private String curStaId;
	
	private Long[] skuIds;
	private String[] kunnrs;
	private Integer[] years;
	private Integer[] months;
	private String[] kunnrTos;
	private Integer[] yearTos;
	private Integer[] monthTos;
	private Double[] quantitys;
	
	private Long detailId;
	private Long detailIdTo;
	
	public String toSalesPlanChange(){
		return "toSalesPlanChange";
	}
	
	public String toSalesPlanChangeView(){
		try {
			if (eventId!=null
					&& StringUtils.isNotEmpty(subFolders)) {
				String pathFile = xmlFilePath + File.separator + subFolders
						+ File.separator + eventId + ".xml";
				XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, new SkuDistribution());
				if (info != null) {
					skuDistribution=(SkuDistribution)info.getObject();
					String link=questionService.getFileByFileId(eventId);
					List<AllUsers> users=stockReportService.getWorkFlowStation(eventId);
					if(users!=null && users.size()>0){
						skuDistribution.setUserName(users.get(0).getUserName());
						skuDistribution.setOrgName(users.get(0).getOrgName());
						skuDistribution.setLink(link);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter(); 
            e.printStackTrace(new PrintWriter(sw, true)); 

			Category ct=new Category();
			ct.setCategoryName(sw.toString());
			JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + "_error.xml", ct, getUser().getUserId(), getUser()
					.getUserName(), null);
		}
		return "toSalesPlanChangeView";
	}
	
	public String toSearchSalesPlanChange(){
		return "toSearchSalesPlanChange";
	}
	
	public String toSemiAutomatic() {
		return "toSemiAutomatic";
	}
	
	public String createSalesPlanChange(){
		try {
			if(skuDistribution!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				skuDistribution.setCreateDate(sdf.format(new Date()));
			}
			
			Object[] res = new Object[8];
			res[0] = "semiautomatic";
			res[1] = this.getUser().getUserId();
			res[2] = skuDistribution.getTitle();
			res[3] = userList;
			res[4] = appUrl + "/stockReportAction!toSalesPlanChangeView.jspa";
			res[5] = "";
			res[6] = "executeAction,refuseAction";
			res[7] = (appUrl + "/stockReportAction!createSalesPlanChangeDone.jspa"+","
					+ appUrl + "/stockReportAction!createSalesPlanChangeRefuse.jspa");
			String eId=wfeServiceHessian.startSemiAutomaticWorkflow(res);
			eventId = Long.valueOf(eId);
			
				if(!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", skuDistribution, getUser().getUserId(), getUser()
						.getUserName(), null)){
					this.setFailMessage("信息写入XML文件出错,请重试");
				}
				if (upload != null && upload.length > 0) {
					questionService.processAttachments(upload, uploadFileName,
							eventId, String.valueOf(new Date().getTime()),
							this.getUser().getUserId());
				}
				
				for(int i=0;i<skuIds.length;i++){
					detailId=stockReportService.getSkuDistributionDetailId();
					detailIdTo=stockReportService.getSkuDistributionDetailId();
					SkuDistribution sd=new SkuDistribution();
					sd.setKunnr(kunnrs[i]);
					sd.setYear(years[i]);
					sd.setMonth(months[i]);
					sd.setUserId(Long.parseLong(this.getUser().getUserId()));
					sd.setQuantity(quantitys[i]);
					sd.setSkuId(skuIds[i]);
					sd.setDetailId(detailId);
					sd.setEventId(eventId);
					stockReportService.createSalesPlanChangeDetail(sd);
					
					SkuDistribution sd1=new SkuDistribution();
					sd1.setKunnr(kunnrTos[i]);
					sd1.setYear(yearTos[i]);
					sd1.setMonth(monthTos[i]);
					sd1.setUserId(Long.parseLong(this.getUser().getUserId()));
					sd1.setQuantity(quantitys[i]*-1);
					sd1.setSkuId(skuIds[i]);
					sd1.setDetailIdTo(detailId);
					sd1.setDetailId(detailIdTo);
					sd1.setEventId(eventId);
					stockReportService.createSalesPlanChangeDetail(sd1);
				}
				this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
				return RESULT_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			this.setFailMessage("事务启动失败，请联系管理员");
			return RESULT_MESSAGE;
		}
	}
	
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createSalesPlanChangeDone(){
		List<AllUsers> users=stockReportService.getWorkFlowStation(eventId);
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode("写入失败，流程节点错误！");
		if(users!=null && users.size()>0){
			for(AllUsers user:users){
				if("fxltz_cw".equals(user.getRoleIds())){
					SkuDistribution sd=new SkuDistribution();
					sd.setEventId(eventId);
					sd.setFlag("Y");
					stockReportService.updateSalesPlanChangeDetail(sd);
					result.setResult(true);
					result.setCode("操作成功");
					break;
				}
			}
		}
		setExecuteResult(result);
		return JSON;
	}
	
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createSalesPlanChangeRefuse(){
		BooleanResult result = new BooleanResult();
		SkuDistribution sd=new SkuDistribution();
		sd.setEventId(eventId);
		sd.setFlag("S");
		stockReportService.updateSalesPlanChangeDetail(sd);
		
		result.setResult(true);
		result.setCode("操作成功");
		setExecuteResult(result);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuDistributionList",include = {"detailId","kunnrTo","kunnrNameTo",
			"orgNameTo","skuId","yearTo","monthTo","userId","detailIdTo","createDate",
			"flag","quantity","kunnr","kunnrName","orgName","year","month","userName",
			"orgNameUser","skuName"})
	public String getSalesPlanChangeDetail(){
		skuDistribution=new SkuDistribution();
		skuDistribution.setEventId(eventId);
		skuDistribution.setStart(0);
		skuDistribution.setEnd(999999);
		skuDistributionList=stockReportService.searchSalesPlanChangeDetail(skuDistribution);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuDistributionList",include = {"detailId","kunnrTo","kunnrNameTo",
			"orgNameTo","skuId","yearTo","monthTo","userId","detailIdTo","createDate",
			"flag","quantity","kunnr","kunnrName","orgName","year","month","userName",
			"orgNameUser","skuName","eventId"},total="total")
	public String searchSalesPlanChangeDetail(){
		skuDistribution=new SkuDistribution();
		skuDistribution.setOrgId(orgId);
		skuDistribution.setKunnr(kunnr);
		skuDistribution.setSkuId(skuId);
		skuDistribution.setStartDate(startDate);
		skuDistribution.setEndDate(endDate);
		skuDistribution.setEventId(eventId);
		skuDistribution.setFlag(flag);
		skuDistribution.setStart(getStart());
		skuDistribution.setEnd(getEnd());
		total = stockReportService.searchSalesPlanChangeDetailCount(skuDistribution);
		if(total>0){
			skuDistributionList=stockReportService.searchSalesPlanChangeDetail(skuDistribution);
		}
		return JSON;
	}
	
	public void exportForExcelSalesPlanChangeDetail() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		skuDistribution=new SkuDistribution();
		skuDistribution.setOrgId(orgId);
		skuDistribution.setKunnr(kunnr);
		skuDistribution.setSkuId(skuId);
		skuDistribution.setStartDate(startDate);
		skuDistribution.setEndDate(endDate);
		skuDistribution.setEventId(eventId);
		skuDistribution.setFlag(flag);
		skuDistribution.setStart(0);
		skuDistribution.setEnd(999999);
		skuDistributionList=stockReportService.searchSalesPlanChangeDetail(skuDistribution);
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "分销量调整表.xls";
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 30);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 30);
			wsheet.setColumnView(9, 10);
			wsheet.setColumnView(10, 10);
			wsheet.setColumnView(11, 10);
			wsheet.setColumnView(12, 10);
			
			wsheet.mergeCells(0, 0, 3, 0);
			wsheet.mergeCells(4, 0, 7, 0);
			wsheet.mergeCells(8, 0, 8, 1);
			wsheet.mergeCells(9, 0, 9, 1);
			wsheet.mergeCells(10, 0, 10, 1);
			wsheet.mergeCells(11, 0, 11, 1);
			wsheet.mergeCells(12, 0, 12, 1);
			
			wsheet.addCell(new Label(0, 0, "转出经销商"));
			wsheet.addCell(new Label(4, 0, "转入经销商"));
			wsheet.addCell(new Label(0, 1, "经销商编号"));
			wsheet.addCell(new Label(1, 1, "经销商名称"));
			wsheet.addCell(new Label(2, 1, "年"));
			wsheet.addCell(new Label(3, 1, "月"));
			wsheet.addCell(new Label(4, 1, "经销商编号"));
			wsheet.addCell(new Label(5, 1, "经销商名称"));
			wsheet.addCell(new Label(6, 1, "年"));
			wsheet.addCell(new Label(7, 1, "月"));
			wsheet.addCell(new Label(8, 0, "品项"));
			wsheet.addCell(new Label(9, 0, "数量"));
			wsheet.addCell(new Label(10, 0, "事务编号"));
			wsheet.addCell(new Label(11, 0, "流程状态"));
			wsheet.addCell(new Label(12, 0, "提报人"));
			
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= skuDistributionList.size(); i++) {
				String eventId="";
				if(skuDistributionList.get(i-1).getEventId()!=null){
					eventId=skuDistributionList.get(i-1).getEventId().toString();
				}
				String flag="";
				if(skuDistributionList.get(i-1).getFlag()!=null){
					if("Y".equals(skuDistributionList.get(i-1).getFlag())){
						flag="已完成";
					}else if("S".equals(skuDistributionList.get(i-1).getFlag())){
						flag="已拒绝";
					}else if("T".equals(skuDistributionList.get(i-1).getFlag())){
						flag="流程中";
					}
				}
				wsheet.addCell(new Label(0,i+1,skuDistributionList.get(i-1).getKunnrTo(),wcfF));
				wsheet.addCell(new Label(1,i+1,skuDistributionList.get(i-1).getKunnrNameTo(),wcfF));
				wsheet.addCell(new Number(2,i+1,skuDistributionList.get(i-1).getYearTo(),wcfF));
				wsheet.addCell(new Number(3,i+1,skuDistributionList.get(i-1).getMonthTo(),wcfF));
				wsheet.addCell(new Label(4,i+1,skuDistributionList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(5,i+1,skuDistributionList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Number(6,i+1,skuDistributionList.get(i-1).getYear(),wcfF));
				wsheet.addCell(new Number(7,i+1,skuDistributionList.get(i-1).getMonth(),wcfF));
				wsheet.addCell(new Label(8,i+1,skuDistributionList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Number(9,i+1,skuDistributionList.get(i-1).getQuantity(),wcfF));
				wsheet.addCell(new Label(10,i+1,eventId,wcfF));
				wsheet.addCell(new Label(11,i+1,flag,wcfF));
				wsheet.addCell(new Label(12,i+1,skuDistributionList.get(i-1).getUserName(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	public void exportForExcel() throws ParseException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		stockReport = new StockReport();
		String orgStrs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgStr = orgStrs.split(",");
		if(ArrayUtils.contains(orgStr,this.getUser().getOrgId())){
			String[] kunnrs={""};
			String isOffice=this.getUser().getIsOffice();
			if(StringUtils.isNotEmpty(isOffice)){
				List<String> kunnrStrs=stockReportService.getKunnrIdByKunag(isOffice);
				if(kunnrStrs!=null && kunnrStrs.size()>0){
					kunnrs=(String[])kunnrStrs.toArray(new String[kunnrStrs.size()]);
				}
			}else{
				List<String> kunnrStrs=kunnrBusinessService.getKunnrIdByKunnrBusiness(this.getUser().getUserId());
				if(kunnrStrs!=null && kunnrStrs.size()>0){
					kunnrs=(String[])kunnrStrs.toArray(new String[kunnrStrs.size()]);
				}
			}
			if(kunnrs.length>0 && kunnrs[0]!=""){
				stockReport.setKunnrs(kunnrs);
			}
		}
		
		if("kunnr_month".equals(flag) && (userType.equals("A")||userType.equals("C"))){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(productionEndDate!=null && !"".equals(productionEndDate)){
				Date date=sdf.parse(productionEndDate+"-01");
				Calendar cal=Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				productionEndDate = sdf.format(cal.getTime());
			}
		}
		if("K".equals(userType) || "A".equals(userType)){
			String[] userTypes = {"A","K"};
			stockReport.setUserTypes(userTypes);
		}else{
			stockReport.setUserType(userType);
		}
		stockReport.setOrgId(orgId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		if(!"both".equals(flag)){
			stockReport.setFlag(flag);
		}
		stockReport.setStart(0);
		stockReport.setEnd(65000);
		stockReportList = stockReportService.getStockReportList(stockReport);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "库存信息.xls";
			if("sales_day".equals(flag)){
				fileName = "分销量信息.xls";
			}
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
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 30);
			wsheet.setColumnView(10, 30);
			wsheet.setColumnView(11, 30);
			wsheet.setColumnView(12, 10);
			wsheet.setColumnView(13, 15);
			wsheet.setColumnView(14, 30);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
			wsheet.addCell(new Label(5, 0, "提报人"));
            wsheet.addCell(new Label(6, 0, "数量"));
			wsheet.addCell(new Label(7, 0, "单位"));
			if(!"sales_day".equals(flag)){
				wsheet.addCell(new Label(8, 0, "库存所属日期"));
			}else{
				wsheet.addCell(new Label(8, 0, "分销量所属日期"));
			}
			wsheet.addCell(new Label(9, 0, "提报日期"));
			wsheet.addCell(new Label(10, 0, "品类"));
			wsheet.addCell(new Label(11, 0, "品项"));
			wsheet.addCell(new Label(12, 0, "货龄"));
			wsheet.addCell(new Label(13, 0, "条码"));
			wsheet.addCell(new Label(14, 0, "主户经销商"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= stockReportList.size(); i++) {
				StockReport s =stockReportList.get(i-1);
				if(StringUtils.isNotEmpty(s.getProductionDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=sdf.parse(s.getProductionDate());
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.MONTH, 1);
					cal.add(Calendar.DATE, -1);
					date=cal.getTime();
					Date date1=sdf1.parse(s.getCreateDate().substring(0, s.getCreateDate().length()-2));
					long diff = date1.getTime() - date.getTime();
				    double months = ((double)diff) / (1000 * 60 * 60 * 24);
				    if(months>=370){
				    	stockReportList.get(i-1).setProductionDate("已过期");
				    }
				}
				
				if(stockReportList.get(i-1).getQuantity()==null){
					stockReportList.get(i-1).setQuantity(0.0);
				}
				wsheet.addCell(new Label(0,i,stockReportList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,stockReportList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,stockReportList.get(i-1).getOrgCity(),wcfF));
				wsheet.addCell(new Label(3,i,stockReportList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,stockReportList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,stockReportList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Number(6,i,stockReportList.get(i-1).getQuantity(),wcfF));
				wsheet.addCell(new Label(7,i,stockReportList.get(i-1).getUnitDesc(),wcfF));
				wsheet.addCell(new Label(8,i,stockReportList.get(i-1).getCheckTime(),wcfF));
				wsheet.addCell(new Label(9,i,stockReportList.get(i-1).getCreateDate(),wcfF));
				wsheet.addCell(new Label(10,i,stockReportList.get(i-1).getCategoryName(),wcfF));
				wsheet.addCell(new Label(11,i,stockReportList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Label(12,i,stockReportList.get(i-1).getProductionDate(),wcfF));
				wsheet.addCell(new Label(13,i,stockReportList.get(i-1).getSkuCode(),wcfF));
				wsheet.addCell(new Label(14,i,stockReportList.get(i-1).getKunagName(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}

	public  String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
         if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
           return ip;
        }
       ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
           int index = ip.indexOf(',');
           if (index != -1) {
                 return ip.substring(0, index);
             } else {
               return ip;
           }
        } else {
            return request.getRemoteAddr();
         }
    }
	
	public String toCustomerStock(){
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=Long.parseLong(this.getUser().getOrgId());
				break;
			}
		}
		if(orgId!=null){
			orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		}else{
			orgId = Long.parseLong("50919");
			orgName = orgServiceHessian.getOrgNameByOrgid("50919");
		}
		loginId=this.getUser().getUserId();
		return "toCustomerStock";
	}
	
	public String toCreateCustomerStock(){
		return "toCreateCustomerStock";
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","productionDate",
			"categoryId","categoryName","quantity","unitDesc","flag","custId","custName","channelName" },total = "total")
	public String searchCustomerSalesList(){
		stockReport = new StockReport();
		if(orgId==null){
			orgId=Long.parseLong(this.getUser().getOrgId());
		}
		stockReport.setOrgId(orgId);
		stockReport.setCustId(custId);
		stockReport.setChannelId(channelId);
		stockReport.setKunnr(kunnr);
		stockReport.setSkuId(skuId);
		stockReport.setUserName(userName);
		stockReport.setCategoryId(categoryId);
		stockReport.setStartDate(startDate);
		stockReport.setEndDate(endDate);
		stockReport.setProductionStartDate(productionStartDate);
		stockReport.setProductionEndDate(productionEndDate);
		stockReport.setFlag("sales_day");
		stockReport.setStart(getStart());
		stockReport.setEnd(getEnd());
		total = stockReportService.searchCustomerStockListCount(stockReport);
		if(total>0){
			stockReportList = stockReportService.searchCustomerStockList(stockReport);
		}
		return JSON;
	}
	
	public String createCustomerStock(){
		skuUnit = new SkuUnit();
		skuUnit.setSkuId(stockReport.getSkuId());
		skuUnit.setStart(0);
		skuUnit.setEnd(10);
		skuUnitList=stockReportService.searchSkuUnitList(skuUnit);
		if(skuUnitList!=null && skuUnitList.size()>0 && 
				StringUtils.isNotEmpty(skuUnitList.get(0).getSkuUnit())){
			stockReport.setUnitDesc(skuUnitList.get(0).getSkuUnit());
			stockReport.setUserId(Long.parseLong(this.getUser().getUserId()));
			if(stockReport.getUserType()==null){
				stockReport.setUserType("A");
			}
			if(StringUtils.isEmpty(stockReport.getProductionDate())){
				stockReport.setProductionDate(stockReport.getCheckTime().substring(0, 7));
			}
			Long categoryId=stockReportService.getCategoryBySku(stockReport.getSkuId());
			stockReport.setCategoryId(categoryId);
			stockReportService.createCustomerStock(stockReport);
			this.setSuccessMessage("操作成功！");
		}else{
			this.setFailMessage("操作失败，请同步物料单位配置表！");
		}
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String updateCustomerStock(){
		if(checkTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("MM");
			int monthNow=Integer.parseInt(sdf.format(new Date()));
			int month=Integer.parseInt(checkTime.substring(5,7));
			if(monthNow==month){
				stockReport = new StockReport();
				stockReport.setStockId(stockId);
				stockReport.setQuantity(quantity);
				total = stockReportService.updateCustomerStock(stockReport);
			}else{
				total=-1;
			}
		}
		return JSON;
	}
	
	public String deleteCustomerStock() {
		this.setSuccessMessage("操作成功！");
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		int monthNow=Integer.parseInt(sdf.format(new Date()));
		
		String[] ids = deleteStockIds.split(",");
		String[] times = checkTime.split(",");
		for (int i = 0; i < ids.length; i++) {
			int month=Integer.parseInt(times[i].substring(5,7));
			if(monthNow==month || this.getUser().getUserId().equals("95507")
					|| this.getUser().getUserId().equals("88647")){
				stockReport = new StockReport();
				stockReport.setStockId(Integer.valueOf(ids[i]));
				stockReport.setStatus("D");
				total = stockReportService.updateCustomerStock(stockReport);
			}else{
				this.setFailMessage("只能删除当月数据!");
				return RESULT_MESSAGE;
			}
		}
		
		if (total==0) {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}
	
	public String toSkuUnit(){
		return "toSkuUnit";
	}
	
	@PermissionSearch
	@JsonResult(field = "skuUnitList",include = {"skuId","skuName",
			"cgId","matterNum","unitCoefficient","categoryName","skuUnit"},total="total")
	public String searchSkuUnitList(){
		skuUnit=new SkuUnit();
		skuUnit.setSkuId(skuId);
		skuUnit.setSkuName(skuName);
		skuUnit.setCgId(categoryId);
		skuUnit.setCategoryName(categoryName);
		if("W".equals(flag)){
			skuUnit.setStart(0);
			skuUnit.setEnd(10);
		}else{
			skuUnit.setStart(getStart());
			skuUnit.setEnd(getEnd());
		}
		total=stockReportService.searchSkuUnitListCount(skuUnit);
		if(total>0){
			skuUnitList = stockReportService.searchSkuUnitList(skuUnit);
		}
		return JSON;
	}
	
	public String createSkuUnit(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		stockReportService.createUnitSapRFC();
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		this.setSuccessMessage("操作成功！");
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuUnitList",include = {"skuId","MEINH","unitCoefficient"})
	public String searchSkuUnitSap(){
		skuUnit=new SkuUnit();
		skuUnit.setSkuId(skuId);
		skuUnit.setSkuName(skuName);
		skuUnit.setCgId(categoryId);
		skuUnit.setCategoryName(categoryName);
		skuUnitList=stockReportService.searchSkuUnitSap(skuUnit);
		return JSON;
	}
	
	public String toUpdateSkuUnit(){
		if(skuId!=null){
			skuUnit=new SkuUnit(); 
			skuUnit.setSkuId(skuId);
		}
		return "toUpdateSkuUnit";
	}
	
	public String updateSkuUnit(){
		DecimalFormat df = new DecimalFormat("#.000");
		if(skuUnit!=null){
			SkuUnit su = new SkuUnit();
			su.setSkuId(skuUnit.getSkuId());
			su.setSkuUnit("标箱");
			List<SkuUnit> suList=stockReportService.searchSkuUnitSap(su);
			if(suList!=null && suList.size()>0){
				double num=suList.get(0).getUnitCoefficient();
				skuUnit.setUnitCoefficient(Double.parseDouble(df.format(skuUnit.getUnitCoefficient()/num)));
				stockReportService.updateSkuUnit(skuUnit);
				this.setSuccessMessage("修改成功！");
			}else{
				this.setFailMessage("修改失败，请同步SAP数据！");
			}
		}
		return RESULT_MESSAGE;
	}
	
	public String exportCustomerStockTotalCsv() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		OutputStream os = null;
		String report_name = "门店零售量导入模板";
		PrintWriter print = null;
		
		skuUnit=new SkuUnit();
		skuUnit.setStart(0);
		skuUnit.setEnd(9999);
		skuUnitList = stockReportService.searchSkuUnitList(skuUnit);
			
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
			linedata.append("门店编号");
			linedata.append(",");
			linedata.append("门店名称");
			linedata.append(",");
			linedata.append("sku编号");
			linedata.append(",");
			linedata.append("sku名称");
			linedata.append(",");
			linedata.append("单位（供参考，修改无效）");
			linedata.append(",");
			linedata.append("条码（供参考，修改无效）");
			linedata.append(",");
			linedata.append("年月（yyyyMM）");
			linedata.append(",");
			linedata.append("1日");
			linedata.append(",");
			linedata.append("2日");
			linedata.append(",");
			linedata.append("3日");
			linedata.append(",");
			linedata.append("4日");
			linedata.append(",");
			linedata.append("5日");
			linedata.append(",");
			linedata.append("6日");
			linedata.append(",");
			linedata.append("7日");
			linedata.append(",");
			linedata.append("8日");
			linedata.append(",");
			linedata.append("9日");
			linedata.append(",");
			linedata.append("10日");
			linedata.append(",");
			linedata.append("11日");
			linedata.append(",");
			linedata.append("12日");
			linedata.append(",");
			linedata.append("13日");
			linedata.append(",");
			linedata.append("14日");
			linedata.append(",");
			linedata.append("15日");
			linedata.append(",");
			linedata.append("16日");
			linedata.append(",");
			linedata.append("17日");
			linedata.append(",");
			linedata.append("18日");
			linedata.append(",");
			linedata.append("19日");
			linedata.append(",");
			linedata.append("20日");
			linedata.append(",");
			linedata.append("21日");
			linedata.append(",");
			linedata.append("22日");
			linedata.append(",");
			linedata.append("23日");
			linedata.append(",");
			linedata.append("24日");
			linedata.append(",");
			linedata.append("25日");
			linedata.append(",");
			linedata.append("26日");
			linedata.append(",");
			linedata.append("27日");
			linedata.append(",");
			linedata.append("28日");
			linedata.append(",");
			linedata.append("29日");
			linedata.append(",");
			linedata.append("30日");
			linedata.append(",");
			linedata.append("31日");
			linedata.append("\n");
			for(SkuUnit su: skuUnitList){
				linedata.append(",,"+su.getSkuId()+","+su.getSkuName()+","
			                        +su.getSkuUnit()+","+su.getSkuCode()+","+sdf.format(new Date()));
				linedata.append("\n");
			}
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
	
	@SuppressWarnings("unchecked")
	@PermissionSearch
	public String importCustomerStockTotalCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> custMap=new HashMap<String,String>();
		Map<Long,String> skuMap=new HashMap<Long,String>();
		Map<Long,String> unitMap=new HashMap<Long,String>();
		Map<Long,Long> cgMap=new HashMap<Long,Long>();

		int maxDate=0;
		CmsTbDictType dict = new CmsTbDictType();
		dict.setDictTypeName("每月门店分销上报截止日期");
		dict.setStart(0);
		dict.setEnd(10);
		List<CmsTbDict> dictList=iDictService.getDictListByDictType(dict);
		for(CmsTbDict dt:dictList){
			if(dt.getItemValue().equals("custfxdate")){
				maxDate=Integer.parseInt(dt.getItemDescription());
			}
		}
		int sFlag=0;
		List<String> roles =stockReportService.getRoleIdByUserId(this.getUser().getUserId());
		for(String role : roles){
			if("mdlsltbqgqx".equals(role) || this.getUser().getUserId().equals("88647")){
				sFlag=1;
				break;
			}
		}
		try {
			String rcs = "";
			String rcs2 = "";
			List<StockReport> stockList = new ArrayList<StockReport>();
			if("Y".equals(ServletActionContext.getRequest().getSession().getAttribute("importExcel"))){
				this.setFailMessage("有文件正在导入，请等待导入结束！");
			}else{
				setImportPercent(0);
				
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
							int percent=(int)Math.floor((j+1)/(double)content.size()*100/2);
							setImportPercent(percent);
							
							String[] s=content.get(j);
							if(s.length<=header.length && header.length==38){
								if(s.length>38){
									this.setFailMessage("第" + (j + 2) + "行列数不正确.");
									return RESULT_MESSAGE;
								}
								if(s[0]==null || "".equals(s[0])){
									rcs = rcs + "第" + (j + 2) + "行门店编号为空.</br>";
									continue;
								}else{
									//将ID及名称放入MAP,加快验证速度
									String custName=custMap.get(s[0]);
									if(custName==null){
										Customer cust=new Customer();
										cust.setCustNumber(s[0]);
										cust.setCustName(s[1]);
										int num=customerService.getCustomerListCount(cust);
										if(num==0){
											rcs = rcs + "第" + (j + 2) + "行门店编号或名称不正确.</br>";
										}else{
											custMap.put(s[0], s[1]);
										}
									}else{
										if(!custName.equals(s[1])){
											rcs = rcs + "第" + (j + 2) + "行门店编号或名称不正确.</br>";
										}
									}
								}
								
								if(s[2]==null || "".equals(s[2])){
									rcs = rcs + "第" + (j + 2) + "行sku编号为空.</br>";
								}else{
									String skuName=skuMap.get(Long.parseLong(s[2]));
									if(skuName==null){
										Sku sku = new Sku();
										sku.setSkuId(Long.parseLong(s[2]));
										sku.setSkuName(s[3]);
										int num2=stockReportService.getSkuCount(sku);
										if(num2==0){
											rcs = rcs + "第" + (j + 2) + "行sku编号或名称不正确.</br>";
										}else{
											skuMap.put(Long.parseLong(s[2]), s[3]);
										}
									}else{
										if(!skuName.equals(s[3])){
											rcs = rcs + "第" + (j + 2) + "行sku编号或名称不正确.</br>";
										}
									}
								}
								
								StockReport stock = new StockReport();
								
								String unit=unitMap.get(Long.parseLong(s[2]));
								if(unit==null){
									skuUnit = new SkuUnit();
									skuUnit.setSkuId(Long.parseLong(s[2]));
									skuUnit.setStart(0);
									skuUnit.setEnd(10);
									skuUnitList=stockReportService.searchSkuUnitList(skuUnit);
									if(skuUnitList!=null && skuUnitList.size()>0 
											&& StringUtils.isNotEmpty(skuUnitList.get(0).getSkuUnit())){
										stock.setUnitDesc(skuUnitList.get(0).getSkuUnit());
										unitMap.put(Long.parseLong(s[2]), skuUnitList.get(0).getSkuUnit());
									}else{
										rcs = rcs + "第" + (j + 2) + "行sku单位未维护.</br>";
									}
								}else{
									stock.setUnitDesc(unit);
								}
								
								Long cgId=cgMap.get(Long.parseLong(s[2]));
								if(cgId==null){
									Long categoryId=stockReportService.getCategoryBySku(Long.parseLong(s[2]));
									stock.setCategoryId(categoryId);
									cgMap.put(Long.parseLong(s[2]), categoryId);
								}else{
									stock.setCategoryId(cgId);
								}
								int imMonth=-1;
								String imMonthStr="";
								String imYearStr="";
								if(s[6]!=null){
									Pattern p = Pattern.compile("^[2]\\d{3}([0][1-9]|[1][0-2])$");//正则表达式判断
		        					Matcher matcher = p.matcher(s[6]);
		        					
									if(matcher.matches()){
										imYearStr=s[6].substring(0, 4);
										imMonthStr=s[6].substring(4, 6);
										imMonth=Integer.parseInt(imMonthStr);
									}else{
										rcs = rcs + "第" + (j + 2) + "行 年月请按照yyyyMM格式填写.</br>";
									}
								}else{
									rcs = rcs + "第" + (j + 2) + "行年月不能为空.</br>";
								}
								
								SimpleDateFormat sdd=new SimpleDateFormat("dd");
								SimpleDateFormat sdm=new SimpleDateFormat("MM");
								int dayNow=Integer.parseInt(sdd.format(new Date()));
								int monthNow=Integer.parseInt(sdm.format(new Date()));
								int imMonthNext=imMonth+1;
								if(imMonthNext==13){
									imMonthNext=1;
								}
								
								if(imMonth>0){
									if((dayNow<maxDate && imMonthNext==monthNow) || (dayNow>=maxDate && imMonth==monthNow)
											|| (dayNow<maxDate && imMonth==monthNow) || sFlag==1){
										int number=0;
										if(imMonth==monthNow){
											number=dayNow+6;
										}else{
											number=37;
										} 
										for(int i=7;i<=number;i++){
											if(s[i]!=null){
												Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");//正则表达式判断是否数字
					        					Matcher matcher = p.matcher(s[i]);
					        					
												if (matcher.matches()){
													StockReport st = new StockReport();
													st.setCustId(Long.parseLong(s[0]));
													st.setSkuId(Long.parseLong(s[2]));
													st.setProductionDate(imYearStr+"-"+imMonthStr);
													st.setFlag("sales_day");
													st.setUserId(Long.parseLong(this.getUser().getUserId()));
													st.setUnitDesc(stock.getUnitDesc());
													st.setCategoryId(stock.getCategoryId());
													st.setUserType("A");
													int day=i-6;
													if(day<10){
														String checkTime=imYearStr+"-"+imMonthStr+"-0"+day;
														st.setCheckTime(checkTime);
														st.setProductionStartDate(checkTime);
														st.setProductionEndDate(checkTime);
													}else{
														String checkTime=imYearStr+"-"+imMonthStr+"-"+day;
														st.setCheckTime(checkTime);
														st.setProductionStartDate(checkTime);
														st.setProductionEndDate(checkTime);
													}
													st.setQuantity(Double.parseDouble(s[i]));
													stockList.add(st);
												}else{
													rcs = rcs + "第" + (j + 2) + "行"
															+ header[i]
																	+ ": 为非数字的值</br>";
												}
											}
										}
									}else{
										rcs = rcs + "第" + (j + 2) + "行 无法导入该年月数据.</br>";
									}
								}
							}else{
								rcs = rcs + "第" + (j + 2)
										+ "行列数有问题.</br>";
								
							}
						}
						if (!"".equals(rcs)) {
							result.append(rcs.toString() + "</br>");
							rcs = "";
						}
						if (stockList.size() != 0
								&& result.toString().equals("")) {
							result1.setResult(true);
							for(int j=0;j<stockList.size();j++){
								try {
									rcs2 = "";
									stockReportService.createOrUpdateCustomerStock(stockList.get(j));

									if(j==stockList.size()-1){
										System.out.println(j);
									}
									int percent=(int)Math.floor((j+1)/(double)stockList.size()*100/2)+50;
									setImportPercent(percent);
								} catch (Exception e) {
									rcs2 = rcs2 + "第" + (j + 2)
											+ "行数据保存数据库失败.请联系系统管理员.</br>";
									result.append(rcs2.toString() + "</br>");
									result1.setResult(false);
									break;
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
						setImportPercent(99);
					}
				}else{
					this.setFailMessage("文件不存在");
					return RESULT_MESSAGE;
				}
			}
			return RESULT_MESSAGE;
		} catch (Exception e) {
			this.setFailMessage("导入失败，请检查模板格式！");
			e.printStackTrace();
			return RESULT_MESSAGE;
		}finally{
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		}
	}
	
	public String toSearchCustomerStockByDate(){
		orgId = Long.parseLong(this.getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toSearchCustomerStockByDate";
	}
	
	@PermissionSearch
	@JsonResult(field = "stockReportList",include = { "stockId","orgRegion","orgProvince","orgCity","kunnr",
			"kunnrName","skuId","skuName","userId","userName","createDate","checkTime","productionDate",
			"categoryId","categoryName","quantity","unitDesc","flag","custId","custName","channelName",
			"day1","day2","day3","day4","day5","day6","day7","day8","day9","day10","day11","day12",
			"day13","day14","day15","day16","day17","day18","day19","day20","day21","day22","day23",
			"day24","day25","day26","day27","day28","day29","day30","day31","custAddress","custSystem","daySum"},total = "total")
	public String searchCustomerSalesByDateList(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
			if(StringUtils.isEmpty(month)){
				month=sdf.format(new Date());
			}
			Calendar cal = Calendar.getInstance();
			Date time=sdf.parse(month);
			cal.setTime(time);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			String beginDate=sdf2.format(time);
			String endDate=sdf2.format(cal.getTime());
			
			stockReport = new StockReport();
			if(orgId==null){
				orgId=Long.parseLong(this.getUser().getOrgId());
			}
			stockReport.setOrgId(orgId);
			stockReport.setCustId(custId);
			stockReport.setChannelId(channelId);
			stockReport.setKunnr(kunnr);
			stockReport.setSkuId(skuId);
			stockReport.setCategoryId(categoryId);
			stockReport.setProductionStartDate(beginDate);
			stockReport.setProductionEndDate(endDate);
			stockReport.setFlag("sales_day");
			stockReport.setStart(getStart());
			stockReport.setEnd(getEnd());
			total = stockReportService.searchCustomerStockByDateListCount(stockReport);
			if(total>0){
				if(StringUtils.isEmpty(flag)){
					stockReportList = stockReportService.searchCustomerStockByDateList(stockReport);
				}else{
					stockReportList = stockReportService.searchCustomerStockByDateDescList(stockReport);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	public void exportForCustomerSalesByDate(){
		try {
			String msg="";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
			if(StringUtils.isEmpty(month)){
				month=sdf.format(new Date());
			}
			Calendar cal = Calendar.getInstance();
			Date time=sdf.parse(month);
			cal.setTime(time);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			String beginDate=sdf2.format(time);
			String endDate=sdf2.format(cal.getTime());
			
			stockReport = new StockReport();
			if(orgId==null){
				orgId=Long.parseLong(this.getUser().getOrgId());
			}
			stockReport.setOrgId(orgId);
			stockReport.setCustId(custId);
			stockReport.setChannelId(channelId);
			stockReport.setKunnr(kunnr);
			stockReport.setSkuId(skuId);
			stockReport.setCategoryId(categoryId);
			stockReport.setProductionStartDate(beginDate);
			stockReport.setProductionEndDate(endDate);
			stockReport.setFlag("sales_day");
			stockReport.setStart(0);
			stockReport.setEnd(99999);
			if(StringUtils.isEmpty(flag)){
				stockReportList = stockReportService.searchCustomerStockByDateList(stockReport);
			}else{
				stockReportList = stockReportService.searchCustomerStockByDateDescList(stockReport);
			}
			
			String[] rowName={"大区", "省份", "城市", "门店编号", "门店名称", "门店地址", "所属系统",
					"经销商","品项","单位", "汇总",
					"1日","2日","3日","4日","5日","6日","7日","8日","9日","10日",
					"11日","12日","13日","14日","15日","16日","17日","18日","19日","20日",
					"21日","22日","23日","24日","25日","26日","27日","28日","29日","30日","31日"};
			String[] colNames={"orgRegion","orgProvince","orgCity","custId","custName","custAddress",
					"custSystem","kunnr","skuName","unitDesc","daySum",
					"day1","day2","day3","day4","day5","day6","day7","day8","day9","day10",
					"day11","day12","day13","day14","day15","day16","day17","day18","day19","day20",
					"day21","day22","day23","day24","day25","day26","day27","day28","day29","day30","day31"};
			
			ExportExcelUtil ee=new ExportExcelUtil("门店日零售量统计_"+month+"月", rowName, colNames, stockReportList);
			msg=ee.export();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportForExcelCustomerSales() throws Exception{
		String msg="";
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
    	
		if("Y".equals(ServletActionContext.getRequest().getSession().getAttribute("exportExcel"))){
			msg = "有文件正在导出，请等待导出结束！";
    	}else{
    		ServletActionContext.getRequest().getSession().setAttribute("exportExcel", "Y");
    		stockReport = new StockReport();
    		if(orgId==null){
    			orgId=Long.parseLong(this.getUser().getOrgId());
    		}
    		stockReport.setOrgId(orgId);
    		stockReport.setCustId(custId);
    		stockReport.setChannelId(channelId);
    		stockReport.setKunnr(kunnr);
    		stockReport.setSkuId(skuId);
    		stockReport.setUserName(userName);
    		stockReport.setCategoryId(categoryId);
    		stockReport.setStartDate(startDate);
    		stockReport.setEndDate(endDate);
    		stockReport.setProductionStartDate(productionStartDate);
    		stockReport.setProductionEndDate(productionEndDate);
    		stockReport.setFlag("sales_day");
    		stockReport.setStart(getStart());
    		stockReport.setEnd(99999999);
    		stockReportList = stockReportService.searchCustomerStockList(stockReport);
    		
    		String[] rowName={"大区", "省份", "城市", "门店编号", "门店名称", "提报人", "品类",
    				"品项", "数量","单位", "分销量所属日期", "提报日期", "货龄"};
    		String[] colNames={"orgRegion","orgProvince","orgCity","custId","custName","userName",
    				"categoryName","skuName","quantity","unitDesc","checkTime","createDate","productionDate"};
    		
    		ExportExcelUtil ee=new ExportExcelUtil("分销量信息", rowName, colNames, stockReportList);
    		msg=ee.export();
    	}

		ServletActionContext.getRequest().getSession().removeAttribute("exportExcel");
	    ServletActionContext.getRequest().getSession().setAttribute("DownLoad", msg);
	}
	
	@PermissionSearch
	@JsonResult(field = "total")
	public String getKunnrImportant(){
		if(StringUtils.isNotEmpty(kunnr)){
			if("A".equals(userType) || "K".equals(userType)){
				String is=stockReportService.getKunnrImportant(kunnr);
				if("X".equals(is) && StringUtils.isNotEmpty(this.getUser().getIsOffice())){
					total=1;
				}else if(StringUtils.isEmpty(is) && StringUtils.isEmpty(this.getUser().getIsOffice())){
					total=1;
				}else{
					total=0;
				}
			}else{
				total=1;
			}
			
		}
		return JSON;
	}
	
	@PermissionSearch
	public String exportSalesPlanChangeCsv() {
		OutputStream os = null;
		String report_name = "分销量调整模板";
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
			linedata.append("转出经销商代码");
			linedata.append(",");
			linedata.append("转出经销商名称");
			linedata.append(",");
			linedata.append("转出年份");
			linedata.append(",");
			linedata.append("转出月份");
			linedata.append(",");
			linedata.append("转入经销商代码");
			linedata.append(",");
			linedata.append("转入经销商名称");
			linedata.append(",");
			linedata.append("转入年份");
			linedata.append(",");
			linedata.append("转入月份");
			linedata.append(",");
			linedata.append("品项（skuID）");
			linedata.append(",");
			linedata.append("调整数量");
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
	
	@PermissionSearch
	public String importSalesPlanChangeCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap = new HashMap<String,String>();
		Map<String,String> kunnrOrgMap = new HashMap<String,String>();
		Map<String,String> skuMap = new HashMap<String,String>();
		try {
			String rcs = "";
			String rcs2 = "";
			String orgId="";
			String orgName="";
			String orgIdTo="";
			String orgNameTo="";
			List<SkuDistribution> sdList = new ArrayList<SkuDistribution>();
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
							if(s.length>10){
								this.setFailMessage("第" + (j + 2) + "行列数不正确.");
								return RESULT_MESSAGE;
							}
							if(s[0]==null || "".equals(s[0])){
								rcs = rcs + "第" + (j + 2) + "行转出经销商编号为空.</br>";
							}else{
								if(s[0].length()<8){
									int length=8-s[0].length();
									for(int x=0;x<length;x++){
										s[0]="0"+s[0];
									}
								}
							}
							if(s[4]==null || "".equals(s[4])){
								rcs = rcs + "第" + (j + 2) + "行转入经销商编号为空.</br>";
							}else{
								if(s[4].length()<8){
									int length=8-s[4].length();
									for(int x=0;x<length;x++){
										s[4]="0"+s[4];
									}
								}
							}
							
							//将ID及名称放入MAP,加快验证速度
							
							String kunnrNameTo=kunnrMap.get(s[0]);
							if(kunnrNameTo==null){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[0].trim());
								kunnr.setName1(s[1].trim());
								kunnr.setPagination("false");
								List<Kunnr> list=kunnrService.kunnrSearch(kunnr);
								if(list==null || list.size()==0){
									rcs = rcs + "第" + (j + 2) + "行转出经销商编号或名称不正确.</br>";
								}else{
									orgIdTo=list.get(0).getOrgId();
									orgNameTo=list.get(0).getOrgName();
									kunnrMap.put(s[0], s[1]);
									kunnrOrgMap.put(s[0], orgIdTo+","+orgNameTo);
								}
							}else{
								if(!kunnrNameTo.equals(s[1])){
									rcs = rcs + "第" + (j + 2) + "行转出经销商编号或名称不正确.</br>";
								}else{
									String[] orgs=kunnrOrgMap.get(s[0]).split(",");
									orgIdTo=orgs[0];
									orgNameTo=orgs[1];
								}
							}
							
							
							String kunnrName=kunnrMap.get(s[4]);
							if(kunnrName==null){
								Kunnr kunnr=new Kunnr();
								kunnr.setKunnr(s[4].trim());
								kunnr.setName1(s[5].trim());
								kunnr.setPagination("false");
								List<Kunnr> list=kunnrService.kunnrSearch(kunnr);
								if(list==null || list.size()==0){
									rcs = rcs + "第" + (j + 2) + "行转入经销商编号或名称不正确.</br>";
								}else{
									orgId=list.get(0).getOrgId();
									orgName=list.get(0).getOrgName();
									kunnrMap.put(s[4], s[5]);
									kunnrOrgMap.put(s[4], orgId+","+orgName);
								}
							}else{
								if(!kunnrName.equals(s[5])){
									rcs = rcs + "第" + (j + 2) + "行转入经销商编号或名称不正确.</br>";
								}else{
									String[] orgs=kunnrOrgMap.get(s[4]).split(",");
									orgId=orgs[0];
									orgName=orgs[1];
								}
							}
							
							if(StringUtils.isEmpty(s[8])){
								rcs = rcs + "第" + (j + 2) + "行sku编号为空.</br>";
							}else{
								String skuName=skuMap.get(s[8]);
								if(skuName==null){
									Sku sku = new Sku();
									sku.setSkuId(Long.valueOf(s[8]));
									sku.setPagination("false");
									List<Sku> skuList=stockReportService.getSkuNameList(sku);
									if(skuList==null || skuList.size()==0){
										rcs = rcs + "第" + (j + 2) + "行sku编号或名称不正确.</br>";
									}else{
										skuMap.put(s[8], skuList.get(0).getSkuName());
									}
								}
							}
							
							if(StringUtils.isNotEmpty(s[2]) && StringUtils.isNotEmpty(s[3])
									&& StringUtils.isNotEmpty(s[6]) && StringUtils.isNotEmpty(s[7])){
								Pattern p = Pattern.compile("^20\\d{2}$");//正则表达式判断年份
	        					Matcher matcher1 = p.matcher(s[2]);
	        					Matcher matcher2 = p.matcher(s[6]);
	        					if(!matcher1.matches() || !matcher2.matches()){
	        						rcs = rcs + "第" + (j + 2) + "行 年份格式错误.</br>";
	        					}
	        					
	        					Pattern p1 = Pattern.compile("^0?[1-9]|1[012]$");//正则表达式判断月份
	        					Matcher matcher3 = p1.matcher(s[3]);
	        					Matcher matcher4 = p1.matcher(s[7]);
	        					if(!matcher3.matches() || !matcher4.matches()){
	        						rcs = rcs + "第" + (j + 2) + "行 月份格式错误.</br>";
	        					}
							}else{
								rcs = rcs + "第" + (j + 2) + "行 年月不能为空.</br>";
							}
								
							if (null != s[9]) {
								Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");//正则表达式判断是否数字
	        					Matcher matcher = p.matcher(s[9]);
								if (!matcher.matches()){
									rcs = rcs + "第" + (j + 2) + "行 调整数量格式有误.</br>";
								}
							} else {
									rcs = rcs + "第" + (j + 2) + "行 调整数量不能为空</br>";
							}
							SkuDistribution sd=new SkuDistribution();
							sd.setKunnrTo(s[0]);
							sd.setOrgIdTo(Long.valueOf(orgIdTo));
							sd.setOrgNameTo(orgNameTo);
							sd.setYearTo(Integer.valueOf(s[2]));
							sd.setMonthTo(Integer.valueOf(s[3]));
							sd.setKunnr(s[4]);
							sd.setOrgId(Long.valueOf(orgId));
							sd.setOrgName(orgName);
							sd.setYear(Integer.valueOf(s[6]));
							sd.setMonth(Integer.valueOf(s[7]));
							sd.setSkuId(Long.valueOf(s[8]));
							sd.setQuantity(Double.valueOf(s[9]));
							sdList.add(sd);
								
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
					
					if (sdList.size() != 0
							&& result.toString().equals("")) {
						result1.setResult(true);
						this.getSession().setAttribute("salesPlanChangeList", sdList);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	
	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "skuDistributionList",include = {"detailId","kunnrTo","kunnrNameTo",
			"orgNameTo","skuId","yearTo","monthTo","userId","detailIdTo","createDate",
			"flag","quantity","kunnr","kunnrName","orgName","year","month","userName",
			"orgNameUser","skuName","orgId","orgIdTo"})
	public String getImportSalesPlanChangeDetail(){
		skuDistributionList=(List<SkuDistribution>)this.getSession().getAttribute("salesPlanChangeList");
		return JSON;
	}
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<StockReport> getStockReportList() {
		return stockReportList;
	}

	public void setStockReportList(List<StockReport> stockReportList) {
		this.stockReportList = stockReportList;
	}

	public void setStockReport(StockReport stockReport) {
		this.stockReport = stockReport;
	}

	public StockReport getStockReport() {
		return stockReport;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public String getProductionStartDate() {
		return productionStartDate;
	}

	public void setProductionStartDate(String productionStartDate) {
		this.productionStartDate = productionStartDate;
	}

	public String getProductionEndDate() {
		return productionEndDate;
	}

	public void setProductionEndDate(String productionEndDate) {
		this.productionEndDate = productionEndDate;
	}

	public IStockReportService getStockReportService() {
		return stockReportService;
	}

	public void setStockReportService(IStockReportService stockReportService) {
		this.stockReportService = stockReportService;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public int getSkuSize() {
		return skuSize;
	}

	public void setSkuSize(int skuSize) {
		this.skuSize = skuSize;
	}

	public int getCategorySize() {
		return categorySize;
	}

	public void setCategorySize(int categorySize) {
		this.categorySize = categorySize;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Sku getSku() {
		return sku;
	}

	public List<StockDate> getStockDateList() {
		return stockDateList;
	}

	public void setStockDateList(List<StockDate> stockDateList) {
		this.stockDateList = stockDateList;
	}

	public StockDate getStockDate() {
		return stockDate;
	}

	public void setStockDate(StockDate stockDate) {
		this.stockDate = stockDate;
	}

	public List<StringResult> getTheYearList() {
		return theYearList;
	}

	public void setTheYearList(List<StringResult> theYearList) {
		this.theYearList = theYearList;
	}

	public List<StringResult> getTheMonthList() {
		return theMonthList;
	}

	public void setTheMonthList(List<StringResult> theMonthList) {
		this.theMonthList = theMonthList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStockDay() {
		return stockDay;
	}

	public void setStockDay(String stockDay) {
		this.stockDay = stockDay;
	}

	public String getLeastOrder() {
		return leastOrder;
	}

	public void setLeastOrder(String leastOrder) {
		this.leastOrder = leastOrder;
	}

	public String getHandleCycle() {
		return handleCycle;
	}

	public void setHandleCycle(String handleCycle) {
		this.handleCycle = handleCycle;
	}

	public List<KunnrInType> getKunnrInTypeList() {
		return kunnrInTypeList;
	}

	public void setKunnrInTypeList(List<KunnrInType> kunnrInTypeList) {
		this.kunnrInTypeList = kunnrInTypeList;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public KunnrInType getKunnrInType() {
		return kunnrInType;
	}

	public void setKunnrInType(KunnrInType kunnrInType) {
		this.kunnrInType = kunnrInType;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public SkuPercent getSkuPercent() {
		return skuPercent;
	}

	public void setSkuPercent(SkuPercent skuPercent) {
		this.skuPercent = skuPercent;
	}

	public Integer getCgId() {
		return cgId;
	}

	public void setCgId(Integer cgId) {
		this.cgId = cgId;
	}

	public List<SkuPercent> getSkuPercentList() {
		return skuPercentList;
	}

	public void setSkuPercentList(List<SkuPercent> skuPercentList) {
		this.skuPercentList = skuPercentList;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getGoalType() {
		return goalType;
	}

	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}

	public List<Goal> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<Goal> goalList) {
		this.goalList = goalList;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public IKunnrBusinessService getKunnrBusinessService() {
		return kunnrBusinessService;
	}

	public void setKunnrBusinessService(IKunnrBusinessService kunnrBusinessService) {
		this.kunnrBusinessService = kunnrBusinessService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStockState() {
		return stockState;
	}

	public void setStockState(String stockState) {
		this.stockState = stockState;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getDeleteStockIds() {
		return deleteStockIds;
	}

	public void setDeleteStockIds(String deleteStockIds) {
		this.deleteStockIds = deleteStockIds;
	}

	public String getIsDD() {
		return isDD;
	}

	public void setIsDD(String isDD) {
		this.isDD = isDD;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStockFlag() {
		return stockFlag;
	}

	public void setStockFlag(String stockFlag) {
		this.stockFlag = stockFlag;
	}

	public String getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

	public Date getKunnrStartDate() {
		return kunnrStartDate;
	}

	public void setKunnrStartDate(Date kunnrStartDate) {
		this.kunnrStartDate = kunnrStartDate;
	}

	public Date getKunnrEndDate() {
		return kunnrEndDate;
	}

	public void setKunnrEndDate(Date kunnrEndDate) {
		this.kunnrEndDate = kunnrEndDate;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public List<Kunag> getKunagList() {
		return kunagList;
	}

	public void setKunagList(List<Kunag> kunagList) {
		this.kunagList = kunagList;
	}

	public Kunag getKunag() {
		return kunag;
	}

	public void setKunag(Kunag kunag) {
		this.kunag = kunag;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getKunagId() {
		return kunagId;
	}

	public void setKunagId(String kunagId) {
		this.kunagId = kunagId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<StockManager> getStockManagerList() {
		return stockManagerList;
	}

	public void setStockManagerList(List<StockManager> stockManagerList) {
		this.stockManagerList = stockManagerList;
	}

	public StockManager getStockManager() {
		return stockManager;
	}

	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}

	public String getGoalMark() {
		return goalMark;
	}

	public void setGoalMark(String goalMark) {
		this.goalMark = goalMark;
	}

	public String getMatterNum() {
		return matterNum;
	}

	public void setMatterNum(String matterNum) {
		this.matterNum = matterNum;
	}

	public List<OrderCheck> getOrderCheckList() {
		return orderCheckList;
	}

	public void setOrderCheckList(List<OrderCheck> orderCheckList) {
		this.orderCheckList = orderCheckList;
	}

	public OrderCheck getOrderCheck() {
		return orderCheck;
	}

	public void setOrderCheck(OrderCheck orderCheck) {
		this.orderCheck = orderCheck;
	}

	public Date[] getStartDates() {
		return startDates;
	}

	public void setStartDates(Date[] startDates) {
		this.startDates = startDates;
	}

	public Date[] getEndDates() {
		return endDates;
	}

	public void setEndDates(Date[] endDates) {
		this.endDates = endDates;
	}

	public Date[] getCheckTimes() {
		return checkTimes;
	}

	public void setCheckTimes(Date[] checkTimes) {
		this.checkTimes = checkTimes;
	}

	public List<KunnrManager> getKunnrManagerList() {
		return kunnrManagerList;
	}

	public void setKunnrManagerList(List<KunnrManager> kunnrManagerList) {
		this.kunnrManagerList = kunnrManagerList;
	}

	public KunnrManager getKunnrManager() {
		return kunnrManager;
	}

	public void setKunnrManager(KunnrManager kunnrManager) {
		this.kunnrManager = kunnrManager;
	}

	public List<StockSafety> getStockSafetyList() {
		return stockSafetyList;
	}

	public void setStockSafetyList(List<StockSafety> stockSafetyList) {
		this.stockSafetyList = stockSafetyList;
	}

	public StockSafety getStockSafety() {
		return stockSafety;
	}

	public void setStockSafety(StockSafety stockSafety) {
		this.stockSafety = stockSafety;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public SkuUnit getSkuUnit() {
		return skuUnit;
	}

	public void setSkuUnit(SkuUnit skuUnit) {
		this.skuUnit = skuUnit;
	}

	public List<SkuUnit> getSkuUnitList() {
		return skuUnitList;
	}

	public void setSkuUnitList(List<SkuUnit> skuUnitList) {
		this.skuUnitList = skuUnitList;
	}

	public OrderFollow getOrderFollow() {
		return orderFollow;
	}

	public void setOrderFollow(OrderFollow orderFollow) {
		this.orderFollow = orderFollow;
	}

	public List<OrderFollow> getOrderFollowList() {
		return orderFollowList;
	}

	public void setOrderFollowList(List<OrderFollow> orderFollowList) {
		this.orderFollowList = orderFollowList;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public IWfeService getWfeServiceHessian() {
		return wfeServiceHessian;
	}

	public void setWfeServiceHessian(IWfeService wfeServiceHessian) {
		this.wfeServiceHessian = wfeServiceHessian;
	}

	public SkuDistribution getSkuDistribution() {
		return skuDistribution;
	}

	public void setSkuDistribution(SkuDistribution skuDistribution) {
		this.skuDistribution = skuDistribution;
	}

	public List<SkuDistribution> getSkuDistributionList() {
		return skuDistributionList;
	}

	public void setSkuDistributionList(List<SkuDistribution> skuDistributionList) {
		this.skuDistributionList = skuDistributionList;
	}

	public Long[] getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(Long[] skuIds) {
		this.skuIds = skuIds;
	}

	public String[] getKunnrs() {
		return kunnrs;
	}

	public void setKunnrs(String[] kunnrs) {
		this.kunnrs = kunnrs;
	}

	public Integer[] getYears() {
		return years;
	}

	public void setYears(Integer[] years) {
		this.years = years;
	}

	public Integer[] getMonths() {
		return months;
	}

	public void setMonths(Integer[] months) {
		this.months = months;
	}

	public String[] getKunnrTos() {
		return kunnrTos;
	}

	public void setKunnrTos(String[] kunnrTos) {
		this.kunnrTos = kunnrTos;
	}

	public Integer[] getYearTos() {
		return yearTos;
	}

	public void setYearTos(Integer[] yearTos) {
		this.yearTos = yearTos;
	}

	public Integer[] getMonthTos() {
		return monthTos;
	}

	public void setMonthTos(Integer[] monthTos) {
		this.monthTos = monthTos;
	}

	public Double[] getQuantitys() {
		return quantitys;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setQuantitys(Double[] quantitys) {
		this.quantitys = quantitys;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getDetailIdTo() {
		return detailIdTo;
	}

	public void setDetailIdTo(Long detailIdTo) {
		this.detailIdTo = detailIdTo;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(Long nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public String getCrm_xmlFilePath() {
		return crm_xmlFilePath;
	}

	public void setCrm_xmlFilePath(String crm_xmlFilePath) {
		this.crm_xmlFilePath = crm_xmlFilePath;
	}

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
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

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IDictService getiDictService() {
		return iDictService;
	}

	public void setiDictService(IDictService iDictService) {
		this.iDictService = iDictService;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public IGoalService getGoalService() {
		return goalService;
	}

	public void setGoalService(IGoalService goalService) {
		this.goalService = goalService;
	}

	public String getKunnrCode() {
		return kunnrCode;
	}

	public void setKunnrCode(String kunnrCode) {
		this.kunnrCode = kunnrCode;
	}

	public String getOtherIp() {
		return otherIp;
	}

	public void setOtherIp(String otherIp) {
		this.otherIp = otherIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}

//	public void addActionError(String anErrorMessage){
//	 String s=anErrorMessage;
//	 System.out.println(s);
//	 }
//	 public void addActionMessage(String aMessage){
//	 String s=aMessage;
//	 System.out.println(s);
//	
//	 }
//	 public void addFieldError(String fieldName, String errorMessage){
//	 String s=errorMessage;
//	 String f=fieldName;
//	 System.out.println(s);
//	 System.out.println(f);
//	
//	 }
	 
}
