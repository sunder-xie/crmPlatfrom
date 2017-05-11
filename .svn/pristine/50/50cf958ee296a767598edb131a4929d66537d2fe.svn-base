package com.kintiger.platform.sms.action;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.customer.action.CustomerAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.sales.pojo.Tvko;
import com.kintiger.platform.sales.service.ISalesService;
import com.kintiger.platform.sms.pojo.PaymentNoticeInfo;
import com.kintiger.platform.sms.pojo.SendInfo;
import com.kintiger.platform.sms.pojo.Sms;
import com.kintiger.platform.sms.pojo.Station;
import com.kintiger.platform.sms.service.ISmsInterfaceService;
import com.kintiger.platform.sms.service.ISmsService;


public class SmsAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log logger = LogFactory.getLog(CustomerAction.class);
	private ISmsService smsService;
	private ISalesService salesService;
	private int total = 0;
	private List<Sms> smsList;
	private String orgId;
	private String bhxjFlag;
	private String stationId;
	private List<Station> stationList;
	private String isOffice;
	private List<SendInfo> sendInfoList;
	private ISmsInterfaceService smsServiceHessian;
	private	 List<PaymentNoticeInfo> paymentNoticeInfoList;
	private	 List<PaymentNoticeInfo> paymentIdList;
	private String status;
	private String kunnrId;
	private @Decode
	String name1;
	private Date startDate;
	private Date endDate;	
	private List<Tvko> tvkoList;
	private String synchPayment;
	private @Decode
	String userName;
	private String download;
	private String fileName;
	private File fileContent;
	private String isPubGroup;
	private Integer opUserId;
	private List<Sms> groupList;
	private @Decode
	String groupName;
	private String selectGroupName;
	private String selectIsPubGroup;

	
	
	
	
	@PermissionSearch
	public String toSms() {
		opUserId = Integer.parseInt(getUser().getUserId());
		return "toSms";
	}
	
	@JsonResult(field = "smsList", include = { "userName", "mobile", "orgId",
			"orgName", "stationId", "stationName", "isOffice",
			"bus_mobilephone", "pri_mobilephone", "kunnr","userId" }, total = "total")
	public String searchEmpInfo(){
		Sms sms = new Sms();
		sms.setStart(getStart());
		int n = getEnd();
		if(n==0){
			sms.setEnd(100000);
		}else{
			sms.setEnd(getEnd());
		}
		if (StringUtils.isNotEmpty(stationId)&&
				StringUtils.isNotEmpty(stationId.trim())){
			sms.setStationId(stationId);
		}
		if (StringUtils.isNotEmpty(userName)&&
			StringUtils.isNotEmpty(userName.trim())){
			sms.setUserName(userName);
		}
		if (StringUtils.isNotEmpty(isOffice)){
			if("N".equals(isOffice)){
				sms.setIsOffice(isOffice);
			}
		}	
		
		if (StringUtils.isNotEmpty(orgId)) {
			String[] str = orgId.split(", ");
			if (str.length > 1) {
				sms.setOrgId(str[1]);
			} else {
				sms.setOrgId(orgId);
			}
		}
		if (StringUtils.isNotEmpty(bhxjFlag)) {
			sms.setBhxjFlag(bhxjFlag);
		}		
		total=smsService.getEmpInfoCount(sms);
		if(total!=0){
			smsList=smsService.getEmpInfoList(sms);
		}
		return JSON;
	}
	
	/**
	 * 根据人员组织，找到该组织下所有的岗位
	 * 
	 * @return 
	 */
	@JsonResult(field = "stationList", include = { "id", "stationId",
			"stationName", "orgName", "userName" }, total = "total")
	@PermissionSearch
	public String getSelectedStationsJSON() {
		Station station = new Station();
		station = getSearchInfo(station);
		station.setStart(getStart());
		station.setEnd(getEnd());
		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			try {
				stationId = new String(this.getStationId().getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			station.setStationId(stationId.trim());
			station.setStationName(stationId.trim());
		}		
		if (StringUtils.isNotEmpty(orgId)
				&& StringUtils.isNotEmpty(orgId.trim())) {
			station.setOrgId(Long.valueOf(orgId));
		}
		total = smsService.getStationJsonListCountForSelect(station);
		if (total != 0) {
			stationList = smsService.getStationJsonListForSelect(station);
		} else {
			stationList = null;
		}
		return JSON;
	}
	
	
	public String sendMessage(){
		BooleanResult booleanResult = smsServiceHessian.sendMessage(sendInfoList);
		if(booleanResult.getResult()){
			this.setSuccessMessage(booleanResult.getCode());
		}else{
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 跳转货款到账通知页面
	 * 
	 * @return 
	 */
	@PermissionSearch
	public String toPaymentNotice() {
		return "toPaymentNotice";
	}	
	
	public String toSynchPayment() {
		paymentNoticeInfoList = new ArrayList<PaymentNoticeInfo>();
		tvkoList = new ArrayList<Tvko>();
		Tvko tvko = new Tvko();
		tvko.setStart(0);
		tvko.setEnd(10000);
		tvkoList = salesService.getTvkoList(tvko);
		for(Tvko tvko2 : tvkoList){
			PaymentNoticeInfo paymentNoticeInfo = new PaymentNoticeInfo();
			paymentNoticeInfo.setBukrs(tvko2.getBukrs());
			paymentNoticeInfo=smsService.getMaxBelnrByPayment(paymentNoticeInfo);
			if(paymentNoticeInfo.getBank_etydat() == null||
				paymentNoticeInfo.getBank_belnr() == null){
				continue;
			}
			paymentNoticeInfo.setBukrs(tvko2.getBukrs());
			paymentNoticeInfoList.add(paymentNoticeInfo);
		}
		StringResult stringResult = smsService.synchPayment(paymentNoticeInfoList);
		synchPayment = stringResult.getCode();
		return JSON;
	}		
	
	@PermissionSearch
	@JsonResult(field = "paymentNoticeInfoList", include = { "kunnr", "name1", "name3",
			"mobNumber","businessManager","bukrs", "businessCompetent","orgId", "orgName",
			"managerMobile","competentMobile","bank_etydat","bank_trsamtd","bank_belnr","send_date",
			"salesman","salesmanMoblie","paymentId"}, total = "total")	
	public String searchPaymentNoticeInfo(){
		PaymentNoticeInfo paymentNoticeInfo = new PaymentNoticeInfo();
		paymentNoticeInfo.setStart(getStart());
		int n = getEnd();
		if(n==0){
			paymentNoticeInfo.setEnd(100000);
		}else{
			paymentNoticeInfo.setEnd(getEnd());
		}
		if (StringUtils.isNotEmpty(kunnrId)) {
			paymentNoticeInfo.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(name1)) {
			paymentNoticeInfo.setName1(name1);
		}		
		if (StringUtils.isNotEmpty(orgId)) {
			String[] str = orgId.split(", ");
			if (str.length > 1) {
				paymentNoticeInfo.setOrgId(str[1]);
			} else {
				paymentNoticeInfo.setOrgId(orgId);
			}
		}
		if (StringUtils.isNotEmpty(bhxjFlag)) {
			paymentNoticeInfo.setBhxjFlag(bhxjFlag);
		}
		if (StringUtils.isNotEmpty(status)) {
			paymentNoticeInfo.setStatus(status);
			
		}
		if (startDate != null) {
			paymentNoticeInfo.setStartDate(startDate);
		}
		if (endDate != null) {
			paymentNoticeInfo.setEndDate(endDate);
		}		
		total=smsService.getPaymentNoticeInfoCount(paymentNoticeInfo);
		if(total!=0){
			paymentNoticeInfoList=smsService.getPaymentNoticeInfoList(paymentNoticeInfo);
			
			for(int i=0;i<paymentNoticeInfoList.size();i++){
				List<KunnrBusiness> k=smsService.getKunnrBusinessByKunnrId(paymentNoticeInfoList.get(i).getKunnr());
				if(k!=null && k.size()==1){
					if(!k.get(0).getHeadMobile().equals("无")){
						paymentNoticeInfoList.get(i).setSalesman(k.get(0).getBusinessHead());
						paymentNoticeInfoList.get(i).setSalesmanMoblie(k.get(0).getHeadMobile());
					}
				}else if(k.size()>1){
					String head="";
					String mobile="";
					int num=0;
					for(int j=0;j<k.size();j++){
						if(j<k.size()-1){
							head+=k.get(j).getBusinessHead()+"/";
							mobile+=k.get(j).getHeadMobile()+"/";
						}else{
							head+=k.get(j).getBusinessHead();
							mobile+=k.get(j).getHeadMobile();
						}
						if(k.get(j).getHeadMobile().equals("无")){
							num++;
						}
					}
					if(num!=k.size()){
						paymentNoticeInfoList.get(i).setSalesman(head);
						paymentNoticeInfoList.get(i).setSalesmanMoblie(mobile);
					}
				}
			}
		}
		return JSON;
	}
	
	public String sendPaymentNotice(){
		for(int i=0;i<sendInfoList.size();i++){
			String[] phones=sendInfoList.get(i).getMobile().split("/");
			if(phones.length>1){
				List<String> phone = new ArrayList<String>();
				for(int j=0;j<phones.length;j++){
					if(!phones[j].equals("无")){
						phone.add(phones[j]);
					}
				}
				sendInfoList.get(i).setMobile(phone.get(0));
				for(int j=1;j<phone.size();j++){
					SendInfo si=new SendInfo();
					si.setContent(sendInfoList.get(i).getContent());
					si.setMobile(phone.get(j));
					sendInfoList.add(si);
				}
			}
		}
		
		BooleanResult booleanResult = smsServiceHessian.sendMessage(sendInfoList);
		if(booleanResult.getResult()){
			BooleanResult booleanResult2 = smsService.updatePaymentSendDate(paymentIdList);
			if(booleanResult2.getResult()){
				this.setSuccessMessage(booleanResult2.getCode());
			}else{
				this.setFailMessage(booleanResult2.getCode());
			}
		}else{
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 客户信息导出
	 * 
	 * @return
	 */
	public String paymentNoticeInfoExport() {
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		PaymentNoticeInfo paymentNoticeInfo = new PaymentNoticeInfo();
		paymentNoticeInfo.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(kunnrId)) {
				paymentNoticeInfo.setKunnr(kunnrId);
			}
			if (StringUtils.isNotEmpty(name1)) {
				paymentNoticeInfo.setName1(name1);
			}		
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					paymentNoticeInfo.setOrgId(str[1]);
				} else {
					paymentNoticeInfo.setOrgId(orgId);
				}
			}
			if (StringUtils.isNotEmpty(bhxjFlag)) {
				paymentNoticeInfo.setBhxjFlag(bhxjFlag);
			}
			if (StringUtils.isNotEmpty(status)) {
				paymentNoticeInfo.setStatus(status);
				
			}
			if (startDate != null) {
				paymentNoticeInfo.setStartDate(startDate);
			}
			if (endDate != null) {
				paymentNoticeInfo.setEndDate(endDate);
			}		
			total=smsService.getPaymentNoticeInfoCount(paymentNoticeInfo);
			if(total!=0){
				paymentNoticeInfoList=smsService.getPaymentNoticeInfoList(paymentNoticeInfo);
			}
			props.add("kunnr");
			props.add("name1");
			props.add("orgName");
			props.add("name3");
			props.add("mobNumber");
			props.add("salesman");
			props.add("salesmanMoblie");
			props.add("bank_etydat");
			props.add("bank_trsamtd");
			props.add("bank_belnr");
			props.add("send_date");
	
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("财务—货款到账数据导出表".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("财务—货款到账数据", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 31);
			wsheet.setColumnView(2, 15);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 15);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 15);
			wsheet.setColumnView(10, 15);


			Label label_0 = new Label(0, 0, "SAP代码");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "经分销商名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "所属组织");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "法人");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "法人手机号码");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			Label label_5 = new Label(5, 0, "业务员");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			Label label_6 = new Label(6, 0, "业务员手机号码");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);
			Label label_7 = new Label(7, 0, "付款日期");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);
			Label label_8 = new Label(8, 0, "收到货款金额");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);
			Label label_9 = new Label(9, 0, "会计凭证");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);
			Label label_10 = new Label(10, 0, "发送日期");
			label_10.setCellFormat(cellFormat_top);
			wsheet.addCell(label_10);
			ExcelUtil.createExcelWithBook(wbook, props, paymentNoticeInfoList);
			ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
			this.setSuccessMessage("导出成功！");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败！");
		}
		return RESULT_MESSAGE;
	}	
	
	
	/**
	 * 跳转修改员工及经销商手机号码页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateMobile() {
		return "toUpdateMobile";
	}
	
	/**
	 * 导出员工模板
	 * 
	 * @return
	 */
	public String exportEmpModel(){
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		Sms sms = new Sms();
		sms.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(stationId)&&
					StringUtils.isNotEmpty(stationId.trim())){
				sms.setStationId(stationId);
			}
			if (StringUtils.isNotEmpty(userName)&&
				StringUtils.isNotEmpty(userName.trim())){
				sms.setUserName(userName);
			}
			if (StringUtils.isNotEmpty(isOffice)){
				if("N".equals(isOffice)){
					sms.setIsOffice(isOffice);
				}
			}	
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					sms.setOrgId(str[1]);
				} else {
					sms.setOrgId(orgId);
				}
			}
			if (StringUtils.isNotEmpty(bhxjFlag)) {
				sms.setBhxjFlag(bhxjFlag);
			}		
			total=smsService.getEmpInfoCount(sms);
			if(total!=0){
				smsList=smsService.getEmpInfoList(sms);
			}
			props.add("orgName");
			props.add("stationName");
			props.add("userId");
			props.add("userName");
			props.add("bus_mobilephone");
			props.add("pri_mobilephone");
	
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("员工手机号修改模板".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("员工手机号修改模板", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wsheet.setColumnView(0, 20);
			wsheet.setColumnView(1, 20);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 15);
			wsheet.setColumnView(5, 15);


			Label label_0 = new Label(0, 0, "组织");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "岗位");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "员工ID");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "员工名称");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "公司手机号码");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			Label label_5 = new Label(5, 0, "私人手机号码");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			ExcelUtil.createExcelWithBook(wbook, props, smsList);
			ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
			this.setSuccessMessage("导出成功！");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败！");
		}
		return RESULT_MESSAGE;		
	}
	
	/**
	 * 校验数据是否下载完成
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else {
			download = "Yes";
		}
		return JSON;
	}
	
	/**
	 * 导出经销商模板
	 * 
	 * @return
	 */
	public String exportKunnrModel(){
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		Sms sms = new Sms();
		sms.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(userName)&&
				StringUtils.isNotEmpty(userName.trim())){
				sms.setUserName(userName);
			}
			if (StringUtils.isNotEmpty(isOffice)){
				if("N".equals(isOffice)){
					sms.setIsOffice(isOffice);
				}
			}	
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					sms.setOrgId(str[1]);
				} else {
					sms.setOrgId(orgId);
				}
			}
			if (StringUtils.isNotEmpty(bhxjFlag)) {
				sms.setBhxjFlag(bhxjFlag);
			}		
			total=smsService.getEmpInfoCount(sms);
			if(total!=0){
				smsList=smsService.getEmpInfoList(sms);
			}
			props.add("kunnr");
			props.add("userName");
			props.add("mobile");
	
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("经销商手机号修改模板".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("经销商手机号修改模板", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wsheet.setColumnView(0, 15);
			wsheet.setColumnView(1, 35);
			wsheet.setColumnView(2, 15);


			Label label_0 = new Label(0, 0, "客户代码");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "客户名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "联系手机号码");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			ExcelUtil.createExcelWithBook(wbook, props, smsList);
			ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
			this.setSuccessMessage("导出成功！");
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("导出失败！");
		}
		return RESULT_MESSAGE;		
	}
	
	public String importEmpMobile(){
		setSuccessMessage("");
		setFailMessage("");
		if ((this.fileName == null) || ("".equals(this.fileName))) {
			setFailMessage("导入失败");
			return "resultMessage";
		}
		StringResult stringResult = smsService.importEmpMobile(fileName,
				fileContent);	
		if ("success".equals(stringResult.getCode())) {
			setSuccessMessage("导入成功!");
		} else {
			setFailMessage("导入失败！" + stringResult.getResult());
		}
		return RESULT_MESSAGE;	
	}
	
	
	
	@JsonResult(field = "groupList", include = {"groupName"}, total = "total")
	public String getGroups(){
		Sms group = new Sms();
		if("MY".equals(isPubGroup)){
			group.setOpUserId(Integer.parseInt(getUser().getUserId()));
			group.setIsPubGroup("Y");
		}else if("Y".equals(isPubGroup)){
			group.setIsPubGroup(isPubGroup);
		}else if("N".equals(isPubGroup)){
			group.setOpUserId(Integer.parseInt(getUser().getUserId()));
			group.setIsPubGroup(isPubGroup);
		}
		groupList = smsService.getGroups(group);
		return JSON;
	}
	
	
//	/**
//	 * 校验编组名称是否重复
//	 * 
//	 * @return
//	 */
//	@PermissionSearch
//	@JsonResult(field = "download")
//	public String checkGroupName() {
//		Object obj = ServletActionContext.getRequest().getSession().getAttribute("DownLoad");
//		if (obj == null || "Ing".equals(obj)) {
//			download = "No";
//		} else {
//			download = "Yes";
//		}
//		return JSON;
//	}
	
	
	public String saveGroup(){
		setSuccessMessage("");
		setFailMessage("");
		Sms group = new Sms();
		groupName = groupList.get(0).getGroupName();
		isPubGroup = groupList.get(0).getIsPubGroup();
		group.setGroupName(groupName);
		group.setIsPubGroup(isPubGroup);
		if("N".equals(isPubGroup)){
			group.setOpUserId(Integer.parseInt(getUser().getUserId()));
		}
		StringResult checkResult = smsService.checkGroupName(group);
		if (ISmsService.ERROR.equals(checkResult.getCode())) {
			if (ISmsService.ERROR.equals(checkResult.getResult())) {
				setFailMessage("验证编组名称失败！");
			} else {
				if ("N".equals(isPubGroup)) {
					setFailMessage("此编组名称在您的个人编组中已存在！");
				} else {
					setFailMessage("此编组名称在公共编组中已存在！");
				}
			}
			return RESULT_MESSAGE;
		}
		StringResult stringResult = smsService.saveGroup(groupList);
		if ("success".equals(stringResult.getCode())) {
			setSuccessMessage("保存成功!");
		} else {
			setFailMessage("保存失败!");
		}
		return RESULT_MESSAGE;
	}
	
	
	@JsonResult(field = "smsList", include = { "userName", "mobile", "orgId",
			"orgName", "stationId", "stationName", "isOffice",
			"bus_mobilephone", "pri_mobilephone", "kunnr", "userId" })
	public String getSelectedGroupInfo() {
		Sms group = new Sms();
		group.setGroupName(groupName);
		if("MY".equals(isPubGroup)){
			group.setOpUserId(Integer.parseInt(getUser().getUserId()));
			group.setIsPubGroup("Y");
		}else if("Y".equals(isPubGroup)){
			group.setIsPubGroup(isPubGroup);
		}else if("N".equals(isPubGroup)){
			group.setOpUserId(Integer.parseInt(getUser().getUserId()));
			group.setIsPubGroup(isPubGroup);
		}
		smsList = smsService.getSelectedGroupInfo(group);
		return JSON;
	}
	
	
	public String selectGroup(){
		return "toSelectGroup";
	}
	
	public String deleteGroup(){
		setSuccessMessage("");
		setFailMessage("");
		Sms group = new Sms();
		group.setGroupName(selectGroupName);
		group.setIsPubGroup(selectIsPubGroup);
		group.setOpUserId(Integer.parseInt(getUser().getUserId()));
		StringResult stringResult = smsService.deleteGroup(group);
		if ("success".equals(stringResult.getCode())) {
			setSuccessMessage("删除成功!");
		} else {
			setFailMessage("删除失败!");
		}
		return RESULT_MESSAGE;
	}
	
	
	public String modifyGroup(){
		setSuccessMessage("");
		setFailMessage("");
		Sms group = new Sms();
		group.setGroupName(selectGroupName);
		group.setIsPubGroup(selectIsPubGroup);
		group.setOpUserId(Integer.parseInt(getUser().getUserId()));
		StringResult stringResult = smsService.modifyGroup(group,groupList);
		if ("success".equals(stringResult.getCode())) {
			setSuccessMessage("修改成功!");
		} else {
			setFailMessage("修改失败!");
		}
		return RESULT_MESSAGE;
	}
	
	
	
	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public ISmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Sms> getSmsList() {
		return smsList;
	}

	public void setSmsList(List<Sms> smsList) {
		this.smsList = smsList;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public String getIsOffice() {
		return isOffice;
	}

	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}

	public List<SendInfo> getSendInfoList() {
		return sendInfoList;
	}

	public void setSendInfoList(List<SendInfo> sendInfoList) {
		this.sendInfoList = sendInfoList;
	}

	public ISmsInterfaceService getSmsServiceHessian() {
		return smsServiceHessian;
	}

	public void setSmsServiceHessian(ISmsInterfaceService smsServiceHessian) {
		this.smsServiceHessian = smsServiceHessian;
	}

	public List<PaymentNoticeInfo> getPaymentNoticeInfoList() {
		return paymentNoticeInfoList;
	}

	public void setPaymentNoticeInfoList(
			List<PaymentNoticeInfo> paymentNoticeInfoList) {
		this.paymentNoticeInfoList = paymentNoticeInfoList;
	}

	public List<PaymentNoticeInfo> getPaymentIdList() {
		return paymentIdList;
	}

	public void setPaymentIdList(List<PaymentNoticeInfo> paymentIdList) {
		this.paymentIdList = paymentIdList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ISalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(ISalesService salesService) {
		this.salesService = salesService;
	}

	public List<Tvko> getTvkoList() {
		return tvkoList;
	}

	public void setTvkoList(List<Tvko> tvkoList) {
		this.tvkoList = tvkoList;
	}

	public String getSynchPayment() {
		return synchPayment;
	}

	public void setSynchPayment(String synchPayment) {
		this.synchPayment = synchPayment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		SmsAction.logger = logger;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFileContent() {
		return fileContent;
	}

	public void setFileContent(File fileContent) {
		this.fileContent = fileContent;
	}

	public String getIsPubGroup() {
		return isPubGroup;
	}

	public void setIsPubGroup(String isPubGroup) {
		this.isPubGroup = isPubGroup;
	}

	public Integer getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(Integer opUserId) {
		this.opUserId = opUserId;
	}

	public List<Sms> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Sms> groupList) {
		this.groupList = groupList;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSelectGroupName() {
		return selectGroupName;
	}

	public void setSelectGroupName(String selectGroupName) {
		this.selectGroupName = selectGroupName;
	}

	public String getSelectIsPubGroup() {
		return selectIsPubGroup;
	}

	public void setSelectIsPubGroup(String selectIsPubGroup) {
		this.selectIsPubGroup = selectIsPubGroup;
	}















	
	
	
}