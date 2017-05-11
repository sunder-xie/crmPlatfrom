package com.kintiger.platform.distributionDataRep.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.kintiger.platform.distributionDataRep.pojo.DistributionDataRep;
import com.kintiger.platform.distributionDataRep.service.IDistributionDataRepService;

import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class DistributionDataRepAction extends BaseAction {

	private static final long serialVersionUID = -532423459213585547L;
	private static Log logger = LogFactory
			.getLog(DistributionDataRepAction.class);
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

	private String startDate;

	private String endDate;
	private int boxNum;
	private String inputDate;
	private String currentYear;
	private String currentMonth;
	private String currentYearMonth;
	private String currentYearMonthDay;
	private String checkName;
	private String trFlag;
	private double aOne;
	private double aTwo;
	private double aThree;
	private double aFour;
	private double aFive;
	private double aSix;
	private double aSeven;
	private double aEight;
	private double bOne;
	private double bSix;
	private double bEight;
	private double cOne;
	private double cSix;
	private double cSeven;
	private double cEight;
	private double dNine;
	private double dTen;
	private double eOne;
	private double hSeven;
	private double aOneC;
	private double aTwoC;
	private double aThreeC;
	private double aFourC;
	private double aFiveC;
	private double aSixC;
	private double aSevenC;
	private double aEightC;
	private double bOneC;
	private double bSixC;
	private double bEightC;
	private double cOneC;
	private double cSixC;
	private double cSevenC;
	private double cEightC;
	private double dNineC;
	private double dTenC;
	private double eOneC;
	private double hSevenC;
	
	private double aOneX;
	private double aTwoX;
	private double aThreeX;
	private double aFourX;
	private double aFiveX;
	private double aSixX;
	private double aSevenX;
	private double aEightX;
	private double bOneX;
	private double bSixX;
	private double bEightX;
	private double cOneX;
	private double cSixX;
	private double cSevenX;
	private double cEightX;
	private double dNineX;
	private double dTenX;
	private double eOneX;
	private double hSevenX;
	
	private String userId;
	private long dgId;
	private String resign_date;

	private String ids;
	private int size;
	private IOrgService orgServiceHessian;
	private IDistributionDataRepService distributionDataRepService;
	private List<DistributionDataRep> distributionDataRepLists;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private DistributionDataRep dGoal;
	private IKunnrService kunnrService;

	public String distributionDataRepSearch() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		return "distributionDataRepSearch";
	}

	/**
	 * 查询目标 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "distributionDataRepLists", total = "size", include = {
			"orgRegion", "orgProvince", "orgCity", "firstUser", "secondUser",
			"thirdUser", "dire_super_name", "dire_super_position", "kunnrName",
			"kunnrId", "inputDate", "aOne","aTwo", "dgId", "aThree", "aFour", "aFive",
			"aSix", "aSeven", "aEight", "bOne","bSix", "bEight", "cOne", "cSix",
			"cSeven", "cEight", "dNine","dTen","eOne", "hSeven", "inputName", "checkName",
			"trFlag", "resign_date", "aOneC","aTwoC", "aThreeC", "aFourC", "aFiveC",
			"aSixC", "aSevenC", "aEightC", "bOneC","bSixC", "bEightC", "cOneC", "cSixC",
			"cSevenC", "cEightC", "dNineC","dTenC","eOneC", "hSevenC","createDate", 
			"aOneX","aTwoX", "aThreeX", "aFourX", "aFiveX",
			"aSixX", "aSevenX", "aEightX", "bOneX","bSixX", "bEightX", "cOneX", "cSixX",
			"cSevenX", "cEightX", "dNineX","dTenX","eOneX", "hSevenX", })
	public String getDistributionDataRepList() {
		distributionDataRepLists = new ArrayList<DistributionDataRep>();
		DistributionDataRep dGoal = new DistributionDataRep();
		DistributionDataRep dDataRep = new DistributionDataRep();
		DistributionDataRep dDataRepInvLast = new DistributionDataRep();
		DistributionDataRep dDataRepInvCurr = new DistributionDataRep();
		List<DistributionDataRep> listRep = new ArrayList<DistributionDataRep>();
		String[] m = { "A1", "A3", "B1", "C1", "A4", "A5", "A6", "C6", "A7","C7",
				"A2", "A8", "B8", "C8", "H7", "D9", "D10", "E1" };

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
			dGoal.setStartDate(startDate);
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dGoal.setEndDate(endDate);
		}
		if (StringUtils.isNotEmpty(trFlag)) {
			dGoal.setTrFlag(trFlag);
		}
		dGoal.setStart(this.getStart());
		dGoal.setEnd(this.getEnd());
		size = distributionDataRepService.getDistributionDataRepCount(dGoal);
		if (size != 0) {
			listRep = distributionDataRepService
					.getDistributionDataRepList(dGoal);
			for (int i = 0; i < listRep.size(); i++) {
				dDataRep = listRep.get(i);
				String date_current = dDataRep.getInputDate();
				//date_current = date_current.replaceAll("\\-", "\\/");
				dDataRep.setInputDate(date_current);
				String[] date_current_arr = date_current.split("\\-");
				String year = date_current_arr[0];
				String month = date_current_arr[1];
				String day =date_current_arr[2];
				dDataRep.setCurrentYear(year);
				dDataRep.setCurrentMonth(month);
				dDataRep.setCurrentYearMonth(year + "-" + month);
				dDataRep = getNumC(dDataRep);//获取sap值
//				String tomonth[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
//				String today[] ={"01","02","03","04","05","06","07","08","09"};
//				String start_sap_date = year + tomonth[Integer.parseInt(month)-1]+"01";
//				String end_sap_date;
//
//				if (Integer.parseInt(day)<10)
//				{
//					 end_sap_date = year + tomonth[Integer.parseInt(month)-1]+today[Integer.parseInt(day)-1];
//				}
//				else
//				{
//					 end_sap_date = year + tomonth[Integer.parseInt(month)-1]+ day ; 			
//				}
//
//
//				distributionDataRepService.synchBoxNum(start_sap_date, end_sap_date, dDataRep);//同步sap到页面
				
				dDataRep = getNewNumC(dDataRep);//获取公式计算值
//				dDataRepInvLast = distributionDataRepService
//						.getDistributionDataRepMaxDateBoxNum(dDataRep);
//				dDataRep.setCurrentYearMonth("");
//				dDataRep.setCurrentYearMonthDay(dDataRep.getInputDate());
//				dDataRepInvCurr = distributionDataRepService
//						.getDistributionDataRepMaxDateBoxNum(dDataRep);
//				
//				if(dDataRepInvLast == null){
//					dDataRepInvLast = new DistributionDataRep();
//					dDataRepInvLast.setaOne(0);
//					dDataRepInvLast.setaTwo(0);
//					dDataRepInvLast.setaThree(0);
//					dDataRepInvLast.setaFour(0);
//					dDataRepInvLast.setaFive(0);
//					dDataRepInvLast.setaSix(0);
//					dDataRepInvLast.setaSeven(0);
//					dDataRepInvLast.setaEight(0);
//					dDataRepInvLast.setbOne(0);
//					dDataRepInvLast.setbSix(0);
//					dDataRepInvLast.setbEight(0);
//					dDataRepInvLast.setcOne(0);
//					dDataRepInvLast.setcSix(0);
//					dDataRepInvLast.setcSeven(0);
//					dDataRepInvLast.setcEight(0);
//					dDataRepInvLast.setdNine(0);
//					dDataRepInvLast.setdTen(0);
//					dDataRepInvLast.seteOne(0);					
//					dDataRepInvLast.sethSeven(0);
//					
//				}
//				
//				if(dDataRepInvCurr == null){
//					dDataRepInvCurr = new DistributionDataRep();
//					dDataRepInvCurr.setaOne(0);
//					dDataRepInvCurr.setaTwo(0);
//					dDataRepInvCurr.setaThree(0);
//					dDataRepInvCurr.setaFour(0);
//					dDataRepInvCurr.setaFive(0);
//					dDataRepInvCurr.setaSix(0);
//					dDataRepInvCurr.setaSeven(0);
//					dDataRepInvCurr.setaEight(0);
//					dDataRepInvCurr.setbOne(0);
//					dDataRepInvCurr.setbSix(0);
//					dDataRepInvCurr.setbEight(0);
//					dDataRepInvCurr.setcOne(0);
//					dDataRepInvCurr.setcSix(0);
//					dDataRepInvCurr.setcSeven(0);
//					dDataRepInvCurr.setcEight(0);
//					dDataRepInvCurr.setdNine(0);
//					dDataRepInvCurr.setdTen(0);
//					dDataRepInvCurr.seteOne(0);
//					dDataRepInvCurr.sethSeven(0);
//					
//				}
//
//					dDataRep.setaOneC(dDataRep.getaOneC()+dDataRepInvLast.getaOne()
//							- dDataRepInvCurr.getaOne());					
//					dDataRep.setaTwoC(dDataRep.getaTwoC()*1.2+dDataRepInvLast.getaTwo()
//							- dDataRepInvCurr.getaTwo());
//					dDataRep.setaThreeC(dDataRep.getaThreeC()+dDataRepInvLast.getaThree()
//							- dDataRepInvCurr.getaThree());
//					dDataRep.setaFourC(dDataRep.getaFourC()*3.2+dDataRepInvLast.getaFour()
//							- dDataRepInvCurr.getaFour());
//					dDataRep.setaFiveC(dDataRep.getaFiveC()*2.67+dDataRepInvLast.getaFive()
//							- dDataRepInvCurr.getaFive());
//					dDataRep.setaSixC(dDataRep.getaSixC()*3.2+dDataRepInvLast.getaSix()
//							- dDataRepInvCurr.getaSix());
//					dDataRep.setaSevenC(dDataRep.getaSevenC()*3+dDataRepInvLast.getaSeven()
//							- dDataRepInvCurr.getaSeven());
//					dDataRep.setaEightC(dDataRep.getaEightC()+dDataRepInvLast.getaEight()
//							- dDataRepInvCurr.getaEight());
//					dDataRep.setbOneC(dDataRep.getbOneC()+dDataRepInvLast.getbOne()
//							- dDataRepInvCurr.getbOne());
//					dDataRep.setbSixC(dDataRep.getbSixC()+dDataRepInvLast.getbSix()
//							- dDataRepInvCurr.getbSix());
//					dDataRep.setbEightC(dDataRep.getbEightC()+dDataRepInvLast.getbEight()
//							- dDataRepInvCurr.getbEight());
//					dDataRep.setcOneC(dDataRep.getcOneC()+dDataRepInvLast.getcOne()
//							- dDataRepInvCurr.getcOne());
//					dDataRep.setcSixC(dDataRep.getcSixC()*3.2+dDataRepInvLast.getcSix()
//							- dDataRepInvCurr.getcSix());
//					dDataRep.setcSevenC(dDataRep.getcSevenC()*3+dDataRepInvLast.getcSeven()
//							- dDataRepInvCurr.getcSeven());
//					dDataRep.setcEightC(dDataRep.getcEightC()+dDataRepInvLast.getcEight()
//							- dDataRepInvCurr.getcEight());
//					dDataRep.setdNineC(dDataRep.getdNineC()+dDataRepInvLast.getdNine()
//							- dDataRepInvCurr.getdNine());
//					dDataRep.setdTenC(dDataRep.getdTenC()*3+dDataRepInvLast.getdTen()
//							- dDataRepInvCurr.getdTen());
//					dDataRep.seteOneC(dDataRep.geteOneC()*2.4+dDataRepInvLast.geteOne()
//							- dDataRepInvCurr.geteOne());
//					dDataRep.sethSevenC(dDataRep.gethSevenC()+dDataRepInvLast.gethSeven()
//							- dDataRepInvCurr.gethSeven());
					
					dDataRep = getNumX(dDataRep);//获取差值
					
//					dDataRep.setaOneX(dDataRep.getaOneC() - dDataRep.getaOne());					
//					dDataRep.setaTwoX(dDataRep.getaTwoC() - dDataRep.getaTwo());
//					dDataRep.setaThreeX(dDataRep.getaThreeC() - dDataRep.getaThree());
//					dDataRep.setaFourX(dDataRep.getaFourC() - dDataRep.getaFour());
//					dDataRep.setaFiveX(dDataRep.getaFiveC() - dDataRep.getaFive());
//					dDataRep.setaSixX(dDataRep.getaSixC() - dDataRep.getaSix());
//					dDataRep.setaSevenX(dDataRep.getaSevenC() - dDataRep.getaSeven());
//					dDataRep.setaEightX(dDataRep.getaEightC() - dDataRep.getaEight());
//					dDataRep.setbOneX(dDataRep.getbOneC() - dDataRep.getbOne());
//					dDataRep.setbSixX(dDataRep.getbSixC() - dDataRep.getbSix());
//					dDataRep.setbEightX(dDataRep.getbEightC() - dDataRep.getbEight());
//					dDataRep.setcOneX(dDataRep.getcOneC() - dDataRep.getcOne());
//					dDataRep.setcSixX(dDataRep.getcSixC() - dDataRep.getcSix());
//					dDataRep.setcSevenX(dDataRep.getcSevenC() - dDataRep.getcSeven());
//					dDataRep.setcEightX(dDataRep.getcEightC() - dDataRep.getcEight());
//					dDataRep.setdNineX(dDataRep.getdNineC() - dDataRep.getdNine());
//					dDataRep.setdTenX(dDataRep.getdTenC() - dDataRep.getdTen());
//					dDataRep.seteOneX(dDataRep.geteOneC() - dDataRep.geteOne());
//					dDataRep.sethSevenX(dDataRep.gethSevenC() - dDataRep.gethSeven());
				
				
				distributionDataRepLists.add(dDataRep);

			}
		}
		return JSON;
	}
	


	private DistributionDataRep getNumX(DistributionDataRep dDataRep) {
		dDataRep.setaOneX(dDataRep.getaOneC() - dDataRep.getaOne());					
		dDataRep.setaTwoX(dDataRep.getaTwoC() - dDataRep.getaTwo());
		dDataRep.setaThreeX(dDataRep.getaThreeC() - dDataRep.getaThree());
		dDataRep.setaFourX(dDataRep.getaFourC() - dDataRep.getaFour());
		dDataRep.setaFiveX(dDataRep.getaFiveC() - dDataRep.getaFive());
		dDataRep.setaSixX(dDataRep.getaSixC() - dDataRep.getaSix());
		dDataRep.setaSevenX(dDataRep.getaSevenC() - dDataRep.getaSeven());
		dDataRep.setaEightX(dDataRep.getaEightC() - dDataRep.getaEight());
		dDataRep.setbOneX(dDataRep.getbOneC() - dDataRep.getbOne());
		dDataRep.setbSixX(dDataRep.getbSixC() - dDataRep.getbSix());
		dDataRep.setbEightX(dDataRep.getbEightC() - dDataRep.getbEight());
		dDataRep.setcOneX(dDataRep.getcOneC() - dDataRep.getcOne());
		dDataRep.setcSixX(dDataRep.getcSixC() - dDataRep.getcSix());
		dDataRep.setcSevenX(dDataRep.getcSevenC() - dDataRep.getcSeven());
		dDataRep.setcEightX(dDataRep.getcEightC() - dDataRep.getcEight());
		dDataRep.setdNineX(dDataRep.getdNineC() - dDataRep.getdNine());
		dDataRep.setdTenX(dDataRep.getdTenC() - dDataRep.getdTen());
		dDataRep.seteOneX(dDataRep.geteOneC() - dDataRep.geteOne());
		dDataRep.sethSevenX(dDataRep.gethSevenC() - dDataRep.gethSeven());
		return dDataRep;
	}

	public DistributionDataRep getNumC(DistributionDataRep distributionDataRep)
	{
		String date_current = distributionDataRep.getInputDate();
		//date_current = date_current.replaceAll("\\-", "\\/");
		distributionDataRep.setInputDate(date_current);
		String[] date_current_arr = date_current.split("\\-");
		String year = date_current_arr[0];
		String month = date_current_arr[1];
		String day =date_current_arr[2];
		distributionDataRep.setCurrentYear(year);
		distributionDataRep.setCurrentMonth(month);
		distributionDataRep.setCurrentYearMonth(year + "-" + month);
		
		String tomonth[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
		String today[] ={"01","02","03","04","05","06","07","08","09"};
		String start_sap_date = year + tomonth[Integer.parseInt(month)-1]+"01";
		String end_sap_date;

		if (Integer.parseInt(day)<10)
		{
			 end_sap_date = year + tomonth[Integer.parseInt(month)-1]+today[Integer.parseInt(day)-1];
		}
		else
		{
			 end_sap_date = year + tomonth[Integer.parseInt(month)-1]+ day ; 			
		}


		return distributionDataRepService.synchBoxNum(start_sap_date, end_sap_date, distributionDataRep);
		
	}

	private DistributionDataRep getNewNumC(DistributionDataRep dDataRep) {
		DistributionDataRep dDataRepInvLast = distributionDataRepService
				.getDistributionDataRepMaxDateBoxNum(dDataRep);
		dDataRep.setCurrentYearMonth("");
		dDataRep.setCurrentYearMonthDay(dDataRep.getInputDate());
		DistributionDataRep dDataRepInvCurr = distributionDataRepService
				.getDistributionDataRepMaxDateBoxNum(dDataRep);
		
		if(dDataRepInvLast == null){
			dDataRepInvLast = new DistributionDataRep();
			dDataRepInvLast.setaOne(0);
			dDataRepInvLast.setaTwo(0);
			dDataRepInvLast.setaThree(0);
			dDataRepInvLast.setaFour(0);
			dDataRepInvLast.setaFive(0);
			dDataRepInvLast.setaSix(0);
			dDataRepInvLast.setaSeven(0);
			dDataRepInvLast.setaEight(0);
			dDataRepInvLast.setbOne(0);
			dDataRepInvLast.setbSix(0);
			dDataRepInvLast.setbEight(0);
			dDataRepInvLast.setcOne(0);
			dDataRepInvLast.setcSix(0);
			dDataRepInvLast.setcSeven(0);
			dDataRepInvLast.setcEight(0);
			dDataRepInvLast.setdNine(0);
			dDataRepInvLast.setdTen(0);
			dDataRepInvLast.seteOne(0);					
			dDataRepInvLast.sethSeven(0);
			
		}
		
		if(dDataRepInvCurr == null){
			dDataRepInvCurr = new DistributionDataRep();
			dDataRepInvCurr.setaOne(0);
			dDataRepInvCurr.setaTwo(0);
			dDataRepInvCurr.setaThree(0);
			dDataRepInvCurr.setaFour(0);
			dDataRepInvCurr.setaFive(0);
			dDataRepInvCurr.setaSix(0);
			dDataRepInvCurr.setaSeven(0);
			dDataRepInvCurr.setaEight(0);
			dDataRepInvCurr.setbOne(0);
			dDataRepInvCurr.setbSix(0);
			dDataRepInvCurr.setbEight(0);
			dDataRepInvCurr.setcOne(0);
			dDataRepInvCurr.setcSix(0);
			dDataRepInvCurr.setcSeven(0);
			dDataRepInvCurr.setcEight(0);
			dDataRepInvCurr.setdNine(0);
			dDataRepInvCurr.setdTen(0);
			dDataRepInvCurr.seteOne(0);
			dDataRepInvCurr.sethSeven(0);
			
		}

			dDataRep.setaOneC(dDataRep.getaOneC()+dDataRepInvLast.getaOne()
					- dDataRepInvCurr.getaOne());					
			dDataRep.setaTwoC(dDataRep.getaTwoC()*1.2+dDataRepInvLast.getaTwo()
					- dDataRepInvCurr.getaTwo());
			dDataRep.setaThreeC(dDataRep.getaThreeC()+dDataRepInvLast.getaThree()
					- dDataRepInvCurr.getaThree());
			dDataRep.setaFourC(dDataRep.getaFourC()*3.2+dDataRepInvLast.getaFour()
					- dDataRepInvCurr.getaFour());
			dDataRep.setaFiveC(dDataRep.getaFiveC()*2.67+dDataRepInvLast.getaFive()
					- dDataRepInvCurr.getaFive());
			dDataRep.setaSixC(dDataRep.getaSixC()*3.2+dDataRepInvLast.getaSix()
					- dDataRepInvCurr.getaSix());
			dDataRep.setaSevenC(dDataRep.getaSevenC()*3+dDataRepInvLast.getaSeven()
					- dDataRepInvCurr.getaSeven());
			dDataRep.setaEightC(dDataRep.getaEightC()+dDataRepInvLast.getaEight()
					- dDataRepInvCurr.getaEight());
			dDataRep.setbOneC(dDataRep.getbOneC()+dDataRepInvLast.getbOne()
					- dDataRepInvCurr.getbOne());
			dDataRep.setbSixC(dDataRep.getbSixC()+dDataRepInvLast.getbSix()
					- dDataRepInvCurr.getbSix());
			dDataRep.setbEightC(dDataRep.getbEightC()+dDataRepInvLast.getbEight()
					- dDataRepInvCurr.getbEight());
			dDataRep.setcOneC(dDataRep.getcOneC()+dDataRepInvLast.getcOne()
					- dDataRepInvCurr.getcOne());
			dDataRep.setcSixC(dDataRep.getcSixC()*3.2+dDataRepInvLast.getcSix()
					- dDataRepInvCurr.getcSix());
			dDataRep.setcSevenC(dDataRep.getcSevenC()*3+dDataRepInvLast.getcSeven()
					- dDataRepInvCurr.getcSeven());
			dDataRep.setcEightC(dDataRep.getcEightC()+dDataRepInvLast.getcEight()
					- dDataRepInvCurr.getcEight());
			dDataRep.setdNineC(dDataRep.getdNineC()+dDataRepInvLast.getdNine()
					- dDataRepInvCurr.getdNine());
			dDataRep.setdTenC(dDataRep.getdTenC()*3+dDataRepInvLast.getdTen()
					- dDataRepInvCurr.getdTen());
			dDataRep.seteOneC(dDataRep.geteOneC()*2.4+dDataRepInvLast.geteOne()
					- dDataRepInvCurr.geteOne());
			dDataRep.sethSevenC(dDataRep.gethSevenC()+dDataRepInvLast.gethSeven()
					- dDataRepInvCurr.gethSeven());
			return dDataRep;
	}

	/**
	 * 页面删除目标add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String deleteDistributionDataRep() {
		DistributionDataRep dGoal = new DistributionDataRep();
		try {
			String[] l = ids.split(",");
			dGoal.setIds(l);

			StringResult result = distributionDataRepService
					.deleteDistributionDataRep(dGoal);

			if (distributionDataRepService.ERROR.equals(result.getCode())) {
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

	

	private void List2Excel(List<DistributionDataRep> unMiantList) {
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
			String fileName = "城市经理分销数据报表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			
			wsheet.setColumnView(0, 13);
			wsheet.setColumnView(1, 13);
			wsheet.setColumnView(2, 9);
			wsheet.setColumnView(3, 9);
			wsheet.setColumnView(4, 13);
			wsheet.setColumnView(5, 12);
			wsheet.setColumnView(6, 30);
			wsheet.setColumnView(7, 11);
			wsheet.setColumnView(8, 11);
			wsheet.setColumnView(9, 11);
			wsheet.setColumnView(10, 10);
			
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 15);
			
			wsheet.setColumnView(14, 15);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 15);

			wsheet.setColumnView(17, 15);
			wsheet.setColumnView(18, 15);
			wsheet.setColumnView(19, 15);
			
			wsheet.setColumnView(20, 15);
			wsheet.setColumnView(21, 15);
			wsheet.setColumnView(22, 15);
			
			wsheet.setColumnView(23, 15);
			wsheet.setColumnView(24, 15);
			wsheet.setColumnView(25, 15);
			
			wsheet.setColumnView(26, 15);
			wsheet.setColumnView(27, 15);
			wsheet.setColumnView(28, 15);
			
			wsheet.setColumnView(29, 15);
			wsheet.setColumnView(30, 15);
			wsheet.setColumnView(31, 15);
			
			wsheet.setColumnView(32, 15);
			wsheet.setColumnView(33, 15);
			wsheet.setColumnView(34, 15);
			
			wsheet.setColumnView(35, 15);
			wsheet.setColumnView(36, 15);
			wsheet.setColumnView(37, 15);
			
			wsheet.setColumnView(38, 15);
			wsheet.setColumnView(39, 15);
			wsheet.setColumnView(40, 15);
			
			wsheet.setColumnView(41, 15);
			wsheet.setColumnView(42, 15);
			wsheet.setColumnView(43, 15);
			
			wsheet.setColumnView(44, 15);
			wsheet.setColumnView(45, 15);
			wsheet.setColumnView(46, 15);
			
			wsheet.setColumnView(47, 15);
			wsheet.setColumnView(48, 15);
			wsheet.setColumnView(49, 15);
			
			wsheet.setColumnView(50, 15);
			wsheet.setColumnView(51, 15);
			wsheet.setColumnView(52, 15);
			
			wsheet.setColumnView(53, 15);
			wsheet.setColumnView(54, 15);
			wsheet.setColumnView(55, 15);
			
			wsheet.setColumnView(56, 15);
			wsheet.setColumnView(57, 15);
			wsheet.setColumnView(58, 15);
			
			wsheet.setColumnView(59, 15);
			wsheet.setColumnView(60, 15);
			wsheet.setColumnView(61, 15);
			
			wsheet.setColumnView(62, 15);
			wsheet.setColumnView(63, 15);
			wsheet.setColumnView(64, 15);
						
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
			
			wsheet.mergeCells(11, 0, 13, 0);
			wsheet.mergeCells(14, 0, 16, 0);
			wsheet.mergeCells(17, 0, 19, 0);
			wsheet.mergeCells(20, 0, 22, 0);
			wsheet.mergeCells(23, 0, 25, 0);
			wsheet.mergeCells(26, 0, 28, 0);
			wsheet.mergeCells(29, 0, 31, 0);
			wsheet.mergeCells(32, 0, 34, 0);
			wsheet.mergeCells(35, 0, 37, 0);
			wsheet.mergeCells(38, 0, 40, 0);
			wsheet.mergeCells(41, 0, 43, 0);
			wsheet.mergeCells(44, 0, 46, 0);
			wsheet.mergeCells(47, 0, 49, 0);
			wsheet.mergeCells(50, 0, 52, 0);
			wsheet.mergeCells(53, 0, 55, 0);
			wsheet.mergeCells(56, 0, 58, 0);
			wsheet.mergeCells(59, 0, 61, 0);
			wsheet.mergeCells(62, 0, 64, 0);
			
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
			wsheet.addCell(new Label(7, 0, "分销日期", wcfFG));
			wsheet.addCell(new Label(8, 0, "城市经理", wcfFG));
			wsheet.addCell(new Label(9, 0, "省级经理", wcfFG));
			wsheet.addCell(new Label(10, 0, "大区经理", wcfFG));
			
			wsheet.addCell(new Label(11, 0, "A1椰果单杯-80g*30", wcfFT));
			wsheet.addCell(new Label(11, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(12, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(13, 1, "差值", wcfFT));
			wsheet.addCell(new Label(14, 0, "A3椰果经典装-80g*30", wcfFT));
			wsheet.addCell(new Label(14, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(15, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(16, 1, "差值", wcfFT));
			wsheet.addCell(new Label(17, 0, "B1红豆单杯-64g*30", wcfFT));
			wsheet.addCell(new Label(17, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(18, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(19, 1, "差值", wcfFT));
			wsheet.addCell(new Label(20, 0, "C1桂圆红枣单杯-65g*30", wcfFT));
			wsheet.addCell(new Label(20, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(21, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(22, 1, "差值", wcfFT));
			wsheet.addCell(new Label(23, 0, "A4椰果礼盒装-80g*12*8", wcfFT));
			wsheet.addCell(new Label(23, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(24, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(25, 1, "差值", wcfFT));
			wsheet.addCell(new Label(26, 0, "A5椰果礼盒装-80g*8*10", wcfFT));
			wsheet.addCell(new Label(26, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(27, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(28, 1, "差值", wcfFT));
			wsheet.addCell(new Label(29, 0, "A6椰果家庭分享装-80g*16*6", wcfFT));
			wsheet.addCell(new Label(29, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(30, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(31, 1, "差值", wcfFT));
			wsheet.addCell(new Label(32, 0, "C6桂圆红枣家庭分享装-65g*16*6", wcfFT));
			wsheet.addCell(new Label(32, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(33, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(34, 1, "差值", wcfFT));
			wsheet.addCell(new Label(35, 0, "A7椰果家庭分享装-80g*15*6", wcfFT));
			wsheet.addCell(new Label(35, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(36, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(37, 1, "差值", wcfFT));
			wsheet.addCell(new Label(38, 0, "C7桂圆红枣家庭分享装-65g*15*6", wcfFT));
			wsheet.addCell(new Label(38, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(39, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(40, 1, "差值", wcfFT));
			wsheet.addCell(new Label(41, 0, "A2椰果联杯装-80g*6*6", wcfFT));
			wsheet.addCell(new Label(41, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(42, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(43, 1, "差值", wcfFT));
			wsheet.addCell(new Label(44, 0, "A8椰果联杯装-80g*3*10", wcfFT));
			wsheet.addCell(new Label(44, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(45, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(46, 1, "差值", wcfFT));
			wsheet.addCell(new Label(47, 0, "B8红豆联杯装-80g*3*10", wcfFT));
			wsheet.addCell(new Label(47, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(48, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(49, 1, "差值", wcfFT));
			wsheet.addCell(new Label(50, 0, "C8桂圆红枣联杯装-65g*3*10", wcfFT));
			wsheet.addCell(new Label(50, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(51, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(52, 1, "差值", wcfFT));
			wsheet.addCell(new Label(53, 0, "H7功夫奶茶单瓶-400ml*15", wcfFT));
			wsheet.addCell(new Label(53, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(54, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(55, 1, "差值", wcfFT));
			wsheet.addCell(new Label(56, 0, "D9超值组合装", wcfFT));
			wsheet.addCell(new Label(56, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(57, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(58, 1, "差值", wcfFT));
			wsheet.addCell(new Label(59, 0, "D10豪华礼盒装-15*6", wcfFT));
			wsheet.addCell(new Label(59, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(60, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(61, 1, "差值", wcfFT));
			wsheet.addCell(new Label(62, 0, "E1豪华礼盒装-12*6", wcfFT));
			wsheet.addCell(new Label(62, 1, "业务录入值", wcfFT));
			wsheet.addCell(new Label(63, 1, "公式计算值", wcfFT));
			wsheet.addCell(new Label(64, 1, "差值", wcfFT));

			

			for (int i = 1; i <= unMiantList.size(); i++) {
				DistributionDataRep DateRep;
				DateRep = getNumC(unMiantList.get(i-1));
				DateRep = getNewNumC(DateRep);
				DateRep = getNumX(DateRep);
				wsheet.addCell(new Label(0, i + 1, DateRep
						.getInputName(), wcfF));
				wsheet.addCell(new Label(1, i + 1, DateRep
						.getCreateDate(), wcfF));
				wsheet.addCell(new Label(2, i + 1, DateRep
						.getOrgRegion(), wcfF));
				wsheet.addCell(new Label(3, i + 1, DateRep
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(4, i + 1, DateRep
						.getOrgCity(), wcfF));
				wsheet.addCell(new Label(5, i + 1, DateRep
						.getKunnrId(), wcfF));
				wsheet.addCell(new Label(6, i + 1, DateRep
						.getKunnrName(), wcfF));
				wsheet.addCell(new Label(7, i + 1, DateRep
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(8, i + 1, DateRep
						.getFirstUser(), wcfF));
				wsheet.addCell(new Label(9, i + 1, DateRep
						.getSecondUser(), wcfF));
				wsheet.addCell(new Label(10, i + 1, DateRep
						.getThirdUser(), wcfF));
				wsheet.addCell(new Number(11, i + 1, DateRep
						.getaOne(), wcfF));
				wsheet.addCell(new Number(12, i + 1, DateRep
						.getaOneC(), wcfF));
				wsheet.addCell(new Number(13, i + 1, DateRep
						.getaOneX(), wcfF));
				wsheet.addCell(new Number(14, i + 1, DateRep
						.getaThree(), wcfF));
				wsheet.addCell(new Number(15, i + 1, DateRep
						.getaThreeC(), wcfF));
				wsheet.addCell(new Number(16, i + 1, DateRep
						.getaThreeX(), wcfF));
				wsheet.addCell(new Number(17, i + 1, DateRep
						.getbOne(), wcfF));
				wsheet.addCell(new Number(18, i + 1, DateRep
						.getbOneC(), wcfF));
				wsheet.addCell(new Number(19, i + 1, DateRep
						.getbOneX(), wcfF));
				wsheet.addCell(new Number(20, i + 1, DateRep
						.getcOne(), wcfF));
				wsheet.addCell(new Number(21, i + 1, DateRep
						.getcOneC(), wcfF));
				wsheet.addCell(new Number(22, i + 1, DateRep
						.getcOneX(), wcfF));
				wsheet.addCell(new Number(23, i + 1, DateRep
						.getaFour(), wcfF));
				wsheet.addCell(new Number(24, i + 1, DateRep
						.getaFourC(), wcfF));
				wsheet.addCell(new Number(25, i + 1, DateRep
						.getaFourX(), wcfF));
				wsheet.addCell(new Number(26, i + 1, DateRep
						.getaFive(), wcfF));
				wsheet.addCell(new Number(27, i + 1, DateRep
						.getaFiveC(), wcfF));
				wsheet.addCell(new Number(28, i + 1, DateRep
						.getaFiveX(), wcfF));
				wsheet.addCell(new Number(29, i + 1, DateRep
						.getaSix(), wcfF));
				wsheet.addCell(new Number(30, i + 1, DateRep
						.getaSixC(), wcfF));
				wsheet.addCell(new Number(31, i + 1, DateRep
						.getaSixX(), wcfF));
				wsheet.addCell(new Number(32, i + 1, DateRep
						.getcSix(), wcfF));
				wsheet.addCell(new Number(33, i + 1, DateRep
						.getcSixC(), wcfF));
				wsheet.addCell(new Number(34, i + 1, DateRep
						.getcSixX(), wcfF));
				wsheet.addCell(new Number(35, i + 1, DateRep
						.getaSeven(), wcfF));
				wsheet.addCell(new Number(36, i + 1, DateRep
						.getaSevenC(), wcfF));
				wsheet.addCell(new Number(37, i + 1, DateRep
						.getaSevenX(), wcfF));
				wsheet.addCell(new Number(38, i + 1, DateRep
						.getcSeven(), wcfF));
				wsheet.addCell(new Number(39, i + 1, DateRep
						.getcSevenC(), wcfF));
				wsheet.addCell(new Number(40, i + 1, DateRep
						.getcSevenX(), wcfF));
				wsheet.addCell(new Number(41, i + 1, DateRep
						.getaTwo(), wcfF));
				wsheet.addCell(new Number(42, i + 1, DateRep
						.getaTwoC(), wcfF));
				wsheet.addCell(new Number(43, i + 1, DateRep
						.getaTwoX(), wcfF));
				wsheet.addCell(new Number(44, i + 1, DateRep
						.getaEight(), wcfF));
				wsheet.addCell(new Number(45, i + 1, DateRep
						.getaEightC(), wcfF));
				wsheet.addCell(new Number(46, i + 1, DateRep
						.getaEightX(), wcfF));
				wsheet.addCell(new Number(47, i + 1, DateRep
						.getbEight(), wcfF));
				wsheet.addCell(new Number(48, i + 1, DateRep
						.getbEightC(), wcfF));
				wsheet.addCell(new Number(49, i + 1, DateRep
						.getbEightX(), wcfF));
				wsheet.addCell(new Number(50, i + 1, DateRep
						.getcEight(), wcfF));
				wsheet.addCell(new Number(51, i + 1, DateRep
						.getcEightC(), wcfF));
				wsheet.addCell(new Number(52, i + 1, DateRep
						.getcEightX(), wcfF));
				wsheet.addCell(new Number(53, i + 1, DateRep
						.gethSeven(), wcfF));
				wsheet.addCell(new Number(54, i + 1, DateRep
						.gethSevenC(), wcfF));
				wsheet.addCell(new Number(55, i + 1, DateRep
						.gethSevenX(), wcfF));
				wsheet.addCell(new Number(56, i + 1, DateRep
						.getdNine(), wcfF));
				wsheet.addCell(new Number(57, i + 1, DateRep
						.getdNineC(), wcfF));
				wsheet.addCell(new Number(58, i + 1, DateRep
						.getdNineX(), wcfF));
				wsheet.addCell(new Number(59, i + 1, DateRep
						.getdTen(), wcfF));
				wsheet.addCell(new Number(60, i + 1, DateRep
						.getdTenC(), wcfF));
				wsheet.addCell(new Number(61, i + 1, DateRep
						.getdTenX(), wcfF));
				wsheet.addCell(new Number(62, i + 1, DateRep
						.geteOne(), wcfF));
				wsheet.addCell(new Number(63, i + 1, DateRep
						.geteOneC(), wcfF));
				wsheet.addCell(new Number(64, i + 1, DateRep
						.geteOneX(), wcfF));
				
			}

			/*wsheet.addCell(new Label(0, 0, "大区", wcfFG));
			wsheet.addCell(new Label(1, 0, "省份", wcfFG));
			wsheet.addCell(new Label(2, 0, "城市", wcfFG));
			wsheet.addCell(new Label(3, 0, "经销商编号", wcfFG));
			wsheet.addCell(new Label(4, 0, "经销商名称 ", wcfFG));
			wsheet.addCell(new Label(5, 0, "分销日期", wcfFG));
			wsheet.addCell(new Label(6, 0, "城市经理", wcfFG));
			wsheet.addCell(new Label(7, 0, "省级经理", wcfFG));
			wsheet.addCell(new Label(8, 0, "大区经理", wcfFG));
			wsheet.addCell(new Label(9, 0, "A1椰果单杯", wcfFT));
			wsheet.addCell(new Label(9, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(10, 0, "A2椰果联杯装", wcfFT));
			wsheet.addCell(new Label(10, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(11, 0, "A3椰果经典装", wcfFT));
			wsheet.addCell(new Label(11, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(12, 0, "A4椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(12, 1, "80g*12*8", wcfFT));
			wsheet.addCell(new Label(13, 0, "A5椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(13, 1, "80g*8*10", wcfFT));
			wsheet.addCell(new Label(14, 0, "A6椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(14, 1, "80g*16*6", wcfFT));
			wsheet.addCell(new Label(15, 0, "A7椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(15, 1, "80g*15*6", wcfFT));
			wsheet.addCell(new Label(16, 0, "A8椰果联杯装", wcfFT));
			wsheet.addCell(new Label(16, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(17, 0, "B1红豆单杯", wcfFT));
			wsheet.addCell(new Label(17, 1, "64g*30", wcfFT));
			wsheet.addCell(new Label(18, 0, "B6红豆家庭分享装", wcfFT));
			wsheet.addCell(new Label(18, 1, "64g*16*6", wcfFT));
			wsheet.addCell(new Label(19, 0, "B8红豆联杯装", wcfFT));
			wsheet.addCell(new Label(19, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(20, 0, "C1桂圆红枣单杯", wcfFT));
			wsheet.addCell(new Label(20, 1, "65g*30", wcfFT));
			wsheet.addCell(new Label(21, 0, "C6桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(21, 1, "65g*16*6", wcfFT));
			wsheet.addCell(new Label(22, 0, "C7桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(22, 1, "65g*15*6", wcfFT));
			wsheet.addCell(new Label(23, 0, "C8桂圆红枣联杯装", wcfFT));
			wsheet.addCell(new Label(23, 1, "65g*3*10", wcfFT));
			wsheet.addCell(new Label(24, 0, "D9超值组合装", wcfFT));
			wsheet.addCell(new Label(25, 0, "D10豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(25, 1, "15*6", wcfFT));
			wsheet.addCell(new Label(26, 0, "E1豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(26, 1, "12*6", wcfFT));
			wsheet.addCell(new Label(27, 0, "H7功夫奶茶单瓶", wcfFT));
			wsheet.addCell(new Label(27, 1, "400ml*15", wcfFT));
			wsheet.addCell(new Label(28, 0, "提交人", wcfFG));

			for (int i = 1; i <= unMiantList.size(); i++) {
				wsheet.addCell(new Label(0, i + 1, DateRep
						.getOrgRegion(), wcfF));
				wsheet.addCell(new Label(1, i + 1, DateRep
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(2, i + 1, DateRep
						.getOrgCity(), wcfF));
				wsheet.addCell(new Label(3, i + 1, DateRep
						.getKunnrId(), wcfF));
				wsheet.addCell(new Label(4, i + 1, DateRep
						.getKunnrName(), wcfF));
				wsheet.addCell(new Label(5, i + 1, DateRep
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(6, i + 1, DateRep
						.getFirstUser(), wcfF));
				wsheet.addCell(new Label(7, i + 1, DateRep
						.getSecondUser(), wcfF));
				wsheet.addCell(new Label(8, i + 1, DateRep
						.getThirdUser(), wcfF));
				wsheet.addCell(new Number(9, i + 1, DateRep
						.getaOne(), wcfF));
				wsheet.addCell(new Number(10, i + 1, DateRep
						.getaTwo(), wcfF));
				wsheet.addCell(new Number(11, i + 1, DateRep
						.getaThree(), wcfF));
				wsheet.addCell(new Number(12, i + 1, DateRep
						.getaFour(), wcfF));
				wsheet.addCell(new Number(13, i + 1, DateRep
						.getaFive(), wcfF));
				wsheet.addCell(new Number(14, i + 1, DateRep
						.getaSix(), wcfF));
				wsheet.addCell(new Number(15, i + 1, DateRep
						.getaSeven(), wcfF));
				wsheet.addCell(new Number(16, i + 1, DateRep
						.getaEight(), wcfF));
				wsheet.addCell(new Number(17, i + 1, DateRep
						.getbOne(), wcfF));
				wsheet.addCell(new Number(18, i + 1, DateRep
						.getbSix(), wcfF));
				wsheet.addCell(new Number(19, i + 1, DateRep
						.getbEight(), wcfF));
				wsheet.addCell(new Number(20, i + 1, DateRep
						.getcOne(), wcfF));
				wsheet.addCell(new Number(21, i + 1, DateRep
						.getcSix(), wcfF));
				wsheet.addCell(new Number(22, i + 1, DateRep
						.getcSeven(), wcfF));
				wsheet.addCell(new Number(23, i + 1, DateRep
						.getcEight(), wcfF));
				wsheet.addCell(new Number(24, i + 1, DateRep
						.getdNine(), wcfF));
				wsheet.addCell(new Number(25, i + 1, DateRep
						.getdTen(), wcfF));
				wsheet.addCell(new Number(26, i + 1, DateRep
						.geteOne(), wcfF));
				wsheet.addCell(new Number(27, i + 1, DateRep
						.gethSeven(), wcfF));
				wsheet.addCell(new Label(28, i + 1, DateRep
						.getInputName(), wcfF));
			}*/
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

	public String exportDistributionDataRep() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		distributionDataRepLists = new ArrayList<DistributionDataRep>();
		DistributionDataRep dbData = new DistributionDataRep();
		dbData.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();

		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		try {
			distributionDataRepLists = new ArrayList<DistributionDataRep>();
			if (StringUtils.isNotEmpty(kunnrId)) {
				dbData.setKunnrId(kunnrId);
			}
			if (StringUtils.isNotEmpty(kunnrName)) {
				dbData.setKunnrName(kunnrName);
			}
			if (StringUtils.isNotEmpty(startDate)) {
				dbData.setStartDate(startDate);
			}
			if (StringUtils.isNotEmpty(endDate)) {
				dbData.setEndDate(endDate);
			}

			if (StringUtils.isNotEmpty(trFlag)) {
				dbData.setTrFlag(trFlag);
			}
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					dbData.setOrgId(str[1]);
				} else {
					dbData.setOrgId(orgId);
				}
			}
			dbData.setStart(0);
			dbData.setEnd(100000000);
			distributionDataRepLists = distributionDataRepService
					.getDistributionDataRepList(dbData);

			if (distributionDataRepLists.size() == 0) {
				this.setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
			}
			List2Excel(distributionDataRepLists);
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

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		DistributionDataRepAction.logger = logger;
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

	public double getaOne() {
		return aOne;
	}

	public void setaOne(double aOne) {
		this.aOne = aOne;
	}

	public double getaTwo() {
		return aTwo;
	}

	public void setaTwo(double aTwo) {
		this.aTwo = aTwo;
	}

	public double getaThree() {
		return aThree;
	}

	public void setaThree(double aThree) {
		this.aThree = aThree;
	}

	public double getaFour() {
		return aFour;
	}

	public void setaFour(double aFour) {
		this.aFour = aFour;
	}

	public double getaFive() {
		return aFive;
	}

	public void setaFive(double aFive) {
		this.aFive = aFive;
	}

	public double getaSix() {
		return aSix;
	}

	public void setaSix(double aSix) {
		this.aSix = aSix;
	}

	public double getaSeven() {
		return aSeven;
	}

	public void setaSeven(double aSeven) {
		this.aSeven = aSeven;
	}

	public double getaEight() {
		return aEight;
	}

	public void setaEight(double aEight) {
		this.aEight = aEight;
	}

	public double getbOne() {
		return bOne;
	}
	
	public void setbOne(double bOne) {
		this.bOne = bOne;
	}
	
	public double getbSix() {
		return bSix;
	}

	public void setbSix(double bSix) {
		this.bSix = bSix;
	}

	

	public double getbEight() {
		return bEight;
	}

	public void setbEight(double bEight) {
		this.bEight = bEight;
	}

	public double getcOne() {
		return cOne;
	}

	public void setcOne(double cOne) {
		this.cOne = cOne;
	}

	public double getcSix() {
		return cSix;
	}

	public void setcSix(double cSix) {
		this.cSix = cSix;
	}

	public double getcSeven() {
		return cSeven;
	}

	public void setcSeven(double cSeven) {
		this.cSeven = cSeven;
	}

	public double getcEight() {
		return cEight;
	}

	public void setcEight(double cEight) {
		this.cEight = cEight;
	}
	
	public double getdNine() {
		return dNine;
	}

	public void setdNine(double dNine) {
		this.dNine = dNine;
	}
	
	public double getdTen() {
		return dTen;
	}

	public void setdTen(double dTen) {
		this.dTen = dTen;
	}

	public double geteOne() {
		return eOne;
	}

	public void seteOne(double eOne) {
		this.eOne = eOne;
	}

	public double gethSeven() {
		return hSeven;
	}

	public void sethSeven(double hSeven) {
		this.hSeven = hSeven;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public IDistributionDataRepService getDistributionDataRepService() {
		return distributionDataRepService;
	}

	public void setDistributionDataRepService(
			IDistributionDataRepService distributionDataRepService) {
		this.distributionDataRepService = distributionDataRepService;
	}

	public void setDistributionDataRepLists(
			List<DistributionDataRep> distributionDataRepLists) {
		this.distributionDataRepLists = distributionDataRepLists;
	}

	public List<DistributionDataRep> getDistributionDataRepLists() {
		return distributionDataRepLists;
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

	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
	}

	public DistributionDataRep getdGoal() {
		return dGoal;
	}

	public void setdGoal(DistributionDataRep dGoal) {
		this.dGoal = dGoal;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	

	

	public double getaOneC() {
		return aOneC;
	}

	public void setaOneC(double aOneC) {
		this.aOneC = aOneC;
	}

	public double getaTwoC() {
		return aTwoC;
	}

	public void setaTwoC(double aTwoC) {
		this.aTwoC = aTwoC;
	}

	public double getaThreeC() {
		return aThreeC;
	}

	public void setaThreeC(double aThreeC) {
		this.aThreeC = aThreeC;
	}

	public double getaFourC() {
		return aFourC;
	}

	public void setaFourC(double aFourC) {
		this.aFourC = aFourC;
	}

	public double getaFiveC() {
		return aFiveC;
	}

	public void setaFiveC(double aFiveC) {
		this.aFiveC = aFiveC;
	}

	public double getaSixC() {
		return aSixC;
	}

	public void setaSixC(double aSixC) {
		this.aSixC = aSixC;
	}

	public double getaSevenC() {
		return aSevenC;
	}

	public void setaSevenC(double aSevenC) {
		this.aSevenC = aSevenC;
	}

	public double getaEightC() {
		return aEightC;
	}

	public void setaEightC(double aEightC) {
		this.aEightC = aEightC;
	}

	public double getbOneC() {
		return bOneC;
	}

	public void setbOneC(double bOneC) {
		this.bOneC = bOneC;
	}

	public double getbSixC() {
		return bSixC;
	}

	public void setbSixC(double bSixC) {
		this.bSixC = bSixC;
	}

	public double getbEightC() {
		return bEightC;
	}

	public void setbEightC(double bEightC) {
		this.bEightC = bEightC;
	}

	public double getcOneC() {
		return cOneC;
	}

	public void setcOneC(double cOneC) {
		this.cOneC = cOneC;
	}

	public double getcSixC() {
		return cSixC;
	}

	public void setcSixC(double cSixC) {
		this.cSixC = cSixC;
	}

	public double getcSevenC() {
		return cSevenC;
	}

	public void setcSevenC(double cSevenC) {
		this.cSevenC = cSevenC;
	}

	public double getcEightC() {
		return cEightC;
	}

	public void setcEightC(double cEightC) {
		this.cEightC = cEightC;
	}

	public double getdNineC() {
		return dNineC;
	}

	public void setdNineC(double dNineC) {
		this.dNineC = dNineC;
	}
	
	

	public double getdTenC() {
		return dTenC;
	}

	public void setdTenC(double dTenC) {
		this.dTenC = dTenC;
	}

	public double geteOneC() {
		return eOneC;
	}

	public void seteOneC(double eOneC) {
		this.eOneC = eOneC;
	}

	public double gethSevenC() {
		return hSevenC;
	}

	public void sethSevenC(double hSevenC) {
		this.hSevenC = hSevenC;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentYearMonth() {
		return currentYearMonth;
	}

	public void setCurrentYearMonth(String currentYearMonth) {
		this.currentYearMonth = currentYearMonth;
	}

	public String getCurrentYearMonthDay() {
		return currentYearMonthDay;
	}

	public void setCurrentYearMonthDay(String currentYearMonthDay) {
		this.currentYearMonthDay = currentYearMonthDay;
	}

	public double getaOneX() {
		return aOneX;
	}

	public void setaOneX(double aOneX) {
		this.aOneX = aOneX;
	}

	public double getaTwoX() {
		return aTwoX;
	}

	public void setaTwoX(double aTwoX) {
		this.aTwoX = aTwoX;
	}

	public double getaThreeX() {
		return aThreeX;
	}

	public void setaThreeX(double aThreeX) {
		this.aThreeX = aThreeX;
	}

	public double getaFourX() {
		return aFourX;
	}

	public void setaFourX(double aFourX) {
		this.aFourX = aFourX;
	}

	public double getaFiveX() {
		return aFiveX;
	}

	public void setaFiveX(double aFiveX) {
		this.aFiveX = aFiveX;
	}

	public double getaSixX() {
		return aSixX;
	}

	public void setaSixX(double aSixX) {
		this.aSixX = aSixX;
	}

	public double getaSevenX() {
		return aSevenX;
	}

	public void setaSevenX(double aSevenX) {
		this.aSevenX = aSevenX;
	}

	public double getaEightX() {
		return aEightX;
	}

	public void setaEightX(double aEightX) {
		this.aEightX = aEightX;
	}

	public double getbOneX() {
		return bOneX;
	}

	public void setbOneX(double bOneX) {
		this.bOneX = bOneX;
	}

	public double getbSixX() {
		return bSixX;
	}

	public void setbSixX(double bSixX) {
		this.bSixX = bSixX;
	}

	public double getbEightX() {
		return bEightX;
	}

	public void setbEightX(double bEightX) {
		this.bEightX = bEightX;
	}

	public double getcOneX() {
		return cOneX;
	}

	public void setcOneX(double cOneX) {
		this.cOneX = cOneX;
	}

	public double getcSixX() {
		return cSixX;
	}

	public void setcSixX(double cSixX) {
		this.cSixX = cSixX;
	}

	public double getcSevenX() {
		return cSevenX;
	}

	public void setcSevenX(double cSevenX) {
		this.cSevenX = cSevenX;
	}

	public double getcEightX() {
		return cEightX;
	}

	public void setcEightX(double cEightX) {
		this.cEightX = cEightX;
	}

	public double getdNineX() {
		return dNineX;
	}

	public void setdNineX(double dNineX) {
		this.dNineX = dNineX;
	}

	public double getdTenX() {
		return dTenX;
	}

	public void setdTenX(double dTenX) {
		this.dTenX = dTenX;
	}

	public double geteOneX() {
		return eOneX;
	}

	public void seteOneX(double eOneX) {
		this.eOneX = eOneX;
	}

	public double gethSevenX() {
		return hSevenX;
	}

	public void sethSevenX(double hSevenX) {
		this.hSevenX = hSevenX;
	}
	
	
	
	

}
