package com.kintiger.platform.busPhone.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.busPhone.pojo.BusPhone;
import com.kintiger.platform.busPhone.service.IBusPhoneService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.service.IOrgService;

public class BusPhoneAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4909933963909683950L;
	
	private IBusPhoneService busPhoneService;
	private IOrgService orgServiceHessian;
	private String orgId;
	private String orgName;
	private @Decode String userName;
	private String busPhone;
	private String busPhoneSimple;
	private Date phoneStartDate;
	private Date phoneEndDate;
	private String phoneState;
	private @Decode String phoneRemark;
	private String userId;
	private String kunnrId;
	private Date startDate;
	private Date endDate;
	private String userState;
	
	private List<BusPhone> busPhoneList;
	private BusPhone phone;
	private int total;
	private String download;
	private String loginId;
	
	public String toEmpList(){
		orgId=this.getUser().getOrgId();
		orgName=orgServiceHessian.getOrgNameByOrgid(orgId);
		loginId=this.getUser().getLoginId();
		return "toEmpList";
	}
	
	@PermissionSearch
	@JsonResult(field = "busPhoneList", include = { "userId","orgName","orgProvince","orgRegion", "userName", "userStation",
			"busPhone", "busPhoneSimple", "userStartDate", "userEndDate", "userState",
			"phoneStartDate","phoneEndDate","phoneState","phoneRemark","userIdCard"},total="total")
	public String getEmpList(){
		phone=new BusPhone();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotEmpty(busPhone)){
			phone.setBusPhone(busPhone);
		}
		if(StringUtils.isNotEmpty(orgId)){
			phone.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(userName)){
			phone.setUserName(userName);
		}
		if(startDate!=null){
			phone.setStartDate(sdf.format(startDate));
		}
		if(endDate!=null){
			phone.setEndDate(sdf.format(endDate));
		}
		if(StringUtils.isNotEmpty(userState)){
			phone.setUserState(userState);
		}
		if(StringUtils.isNotEmpty(phoneState)){
			phone.setPhoneState(phoneState);
		}
		
		phone.setStart(getStart());
		phone.setEnd(getEnd());
		total = busPhoneService.getEmpListCount(phone);
		if(total!=0){
			busPhoneList=busPhoneService.getEmpList(phone);
		}
		return JSON;
	}
	
	public String toKunnrEmpList(){
		orgId=this.getUser().getOrgId();
		orgName=orgServiceHessian.getOrgNameByOrgid(orgId);
		loginId=this.getUser().getLoginId();
		return "toKunnrEmpList";
	}
	
	@PermissionSearch
	@JsonResult(field = "busPhoneList", include = { "userId","orgName","orgProvince","orgRegion","kunnrName", "userName","busPhone", 
			"busPhoneSimple", "userState","phoneStartDate","phoneEndDate","phoneState","phoneRemark"},total="total")
	public String getKunnrEmpList(){
		phone=new BusPhone();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotEmpty(busPhone)){
			phone.setBusPhone(busPhone);
		}
		if(StringUtils.isNotEmpty(orgId)){
			phone.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(userName)){
			phone.setUserName(userName);
		}
		if(StringUtils.isNotEmpty(kunnrId)){
			phone.setKunnrId(kunnrId);
		}
		if(startDate!=null){
			phone.setStartDate(sdf.format(startDate));
		}
		if(endDate!=null){
			phone.setEndDate(sdf.format(endDate));
		}
		if(StringUtils.isNotEmpty(phoneState)){
			phone.setPhoneState(phoneState);
		}
		
		phone.setStart(getStart());
		phone.setEnd(getEnd());
		total = busPhoneService.getKunnrEmpListCount(phone);
		if(total!=0){
			busPhoneList=busPhoneService.getKunnrEmpList(phone);
		}
		return JSON;
	}
	
	public void updateEmp(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		phone = new BusPhone();
		phone.setBusPhone(busPhone);
		phone.setBusPhoneSimple(busPhoneSimple);
		if(phoneStartDate!=null){
			phone.setPhoneStartDate(sf.format(phoneStartDate));
		}
		if(phoneEndDate!=null){
			phone.setPhoneEndDate(sf.format(phoneEndDate));
		}
		phone.setPhoneState(phoneState);
		phone.setPhoneRemark(phoneRemark);
		phone.setUserId(userId);
		busPhoneService.updateEmp(phone);
	}
	
	public void updateKunnrEmp(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		phone = new BusPhone();
		phone.setBusPhone(busPhone);
		phone.setBusPhoneSimple(busPhoneSimple);
		if(phoneStartDate!=null){
			phone.setPhoneStartDate(sf.format(phoneStartDate));
		}
		if(phoneEndDate!=null){
			phone.setPhoneEndDate(sf.format(phoneEndDate));
		}
		phone.setPhoneState(phoneState);
		phone.setPhoneRemark(phoneRemark);
		phone.setUserId(userId);
		busPhoneService.updateKunnrEmp(phone);
	}
	
	public void deleteEmp(){
		phone = new BusPhone();
		phone.setUserId(userId);
		busPhoneService.deleteEmp(phone);
	}
	
	public void deleteKunnrEmp(){
		phone = new BusPhone();
		phone.setUserId(userId);
		busPhoneService.deleteKunnrEmp(phone);
	}
	
	public void exportEmpToExcel(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		phone = new BusPhone();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotEmpty(busPhone)){
			phone.setBusPhone(busPhone);
		}
		if(StringUtils.isNotEmpty(orgId)){
			phone.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(userName)){
			phone.setUserName(userName);
		}
		if(startDate!=null){
			phone.setStartDate(sdf.format(startDate));
		}
		if(endDate!=null){
			phone.setEndDate(sdf.format(endDate));
		}
		if(StringUtils.isNotEmpty(userState)){
			phone.setUserState(userState);
		}
		if(StringUtils.isNotEmpty(phoneState)){
			phone.setPhoneState(phoneState);
		}
		phone.setStart(0);
		phone.setEnd(65000);
		busPhoneList=busPhoneService.getEmpList(phone);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "公务手机信息.xls";
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
			wsheet.setColumnView(0, 15);
			wsheet.setColumnView(1, 15);
			wsheet.setColumnView(2, 15);
			wsheet.setColumnView(3, 15);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 20);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 15);
			wsheet.setColumnView(10, 15);
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 10);
			wsheet.setColumnView(14, 50);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "姓名"));
			wsheet.addCell(new Label(4, 0, "身份证"));
			wsheet.addCell(new Label(5, 0, "职务"));
			wsheet.addCell(new Label(6, 0, "入职时间"));
            wsheet.addCell(new Label(7, 0, "离职时间"));
			wsheet.addCell(new Label(8, 0, "人事状态"));
			wsheet.addCell(new Label(9, 0, "公务手机号"));
			wsheet.addCell(new Label(10, 0, "公务手机短号"));
            wsheet.addCell(new Label(11, 0, "开始使用日期"));
			wsheet.addCell(new Label(12, 0, "结束使用日期"));
			wsheet.addCell(new Label(13, 0, "使用状态"));
			wsheet.addCell(new Label(14, 0, "备注"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= busPhoneList.size(); i++) {
				wsheet.addCell(new Label(0,i,busPhoneList.get(i-1).getOrgRegion(),wcfF));
				wsheet.addCell(new Label(1,i,busPhoneList.get(i-1).getOrgProvince(),wcfF));
				wsheet.addCell(new Label(2,i,busPhoneList.get(i-1).getOrgName(),wcfF));
				wsheet.addCell(new Label(3,i,busPhoneList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Label(4,i,busPhoneList.get(i-1).getUserIdCard(),wcfF));
				wsheet.addCell(new Label(5,i,busPhoneList.get(i-1).getUserStation(),wcfF));
				wsheet.addCell(new Label(6,i,busPhoneList.get(i-1).getUserStartDate(),wcfF));
				wsheet.addCell(new Label(7,i,busPhoneList.get(i-1).getUserEndDate(),wcfF));
				String userState="";
				if("Y".equals(busPhoneList.get(i-1).getUserState())){
					userState="在职";
				}else if("N".equals(busPhoneList.get(i-1).getUserState())){
					userState="离职";
				}else if("S".equals(busPhoneList.get(i-1).getUserState())){
					userState="试用期";
				}else if("W".equals(busPhoneList.get(i-1).getUserState())){
					userState="待入职";
				}else if("R".equals(busPhoneList.get(i-1).getUserState())){
					userState="人才库";
				}else if("D".equals(busPhoneList.get(i-1).getUserState())){
					userState="删除";
				}
				wsheet.addCell(new Label(8,i,userState,wcfF));
				wsheet.addCell(new Label(9,i,busPhoneList.get(i-1).getBusPhone(),wcfF));
				wsheet.addCell(new Label(10,i,busPhoneList.get(i-1).getBusPhoneSimple(),wcfF));
				wsheet.addCell(new Label(11,i,busPhoneList.get(i-1).getPhoneStartDate(),wcfF));
				wsheet.addCell(new Label(12,i,busPhoneList.get(i-1).getPhoneEndDate(),wcfF));
				String phoneState="";
				if("A".equals(busPhoneList.get(i-1).getPhoneState())){
					phoneState="在用";
				}else if("B".equals(busPhoneList.get(i-1).getPhoneState())){
					phoneState="停用";
				}
				wsheet.addCell(new Label(13,i,phoneState,wcfF));
				wsheet.addCell(new Label(14,i,busPhoneList.get(i-1).getPhoneRemark(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
				}
				os = null;
			}
		}
}

	public void exportKunnrEmpToExcel(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		phone=new BusPhone();
		if(StringUtils.isNotEmpty(busPhone)){
			phone.setBusPhone(busPhone);
		}
		if(StringUtils.isNotEmpty(orgId)){
			phone.setOrgId(orgId);
		}
		if(StringUtils.isNotEmpty(userName)){
			phone.setUserName(userName);
		}
		if(StringUtils.isNotEmpty(kunnrId)){
			phone.setKunnrId(kunnrId);
		}
		phone.setStart(0);
		phone.setEnd(65000);
		busPhoneList=busPhoneService.getKunnrEmpList(phone);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "经销商公务手机信息.xls";
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
			wsheet.setColumnView(0, 15);
			wsheet.setColumnView(1, 15);
			wsheet.setColumnView(2, 25);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 15);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 50);
			
			
			wsheet.addCell(new Label(0, 0, "组织"));
			wsheet.addCell(new Label(1, 0, "姓名"));
			wsheet.addCell(new Label(2, 0, "经销商"));
			wsheet.addCell(new Label(3, 0, "人事状态"));
			wsheet.addCell(new Label(4, 0, "公务手机号"));
			wsheet.addCell(new Label(5, 0, "公务手机短号"));
            wsheet.addCell(new Label(6, 0, "开始使用日期"));
			wsheet.addCell(new Label(7, 0, "结束使用日期"));
			wsheet.addCell(new Label(8, 0, "使用状态"));
			wsheet.addCell(new Label(9, 0, "备注"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= busPhoneList.size(); i++) {
				wsheet.addCell(new Label(0,i,busPhoneList.get(i-1).getOrgName(),wcfF));
				wsheet.addCell(new Label(1,i,busPhoneList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Label(2,i,busPhoneList.get(i-1).getKunnrName(),wcfF));
				String userState="";
				if("Y".equals(busPhoneList.get(i-1).getUserState())){
					userState="在职";
				}else if("N".equals(busPhoneList.get(i-1).getUserState())){
					userState="离职";
				}else if("S".equals(busPhoneList.get(i-1).getUserState())){
					userState="试用期";
				}else if("W".equals(busPhoneList.get(i-1).getUserState())){
					userState="待入职";
				}else if("R".equals(busPhoneList.get(i-1).getUserState())){
					userState="人才库";
				}else if("D".equals(busPhoneList.get(i-1).getUserState())){
					userState="删除";
				}
				wsheet.addCell(new Label(3,i,userState,wcfF));
				wsheet.addCell(new Label(4,i,busPhoneList.get(i-1).getBusPhone(),wcfF));
				wsheet.addCell(new Label(5,i,busPhoneList.get(i-1).getBusPhoneSimple(),wcfF));
				wsheet.addCell(new Label(6,i,busPhoneList.get(i-1).getPhoneStartDate(),wcfF));
				wsheet.addCell(new Label(7,i,busPhoneList.get(i-1).getPhoneEndDate(),wcfF));
				String phoneState="";
				if("A".equals(busPhoneList.get(i-1).getPhoneState())){
					phoneState="在用";
				}else if("B".equals(busPhoneList.get(i-1).getPhoneState())){
					phoneState="停用";
				}
				wsheet.addCell(new Label(8,i,phoneState,wcfF));
				wsheet.addCell(new Label(9,i,busPhoneList.get(i-1).getPhoneRemark(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
				}
				os = null;
			}
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
		} else {
			download = "Yes";
		}
		return JSON;
	}
	
	public IBusPhoneService getBusPhoneService() {
		return busPhoneService;
	}

	public void setBusPhoneService(IBusPhoneService busPhoneService) {
		this.busPhoneService = busPhoneService;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public List<BusPhone> getBusPhoneList() {
		return busPhoneList;
	}

	public void setBusPhoneList(List<BusPhone> busPhoneList) {
		this.busPhoneList = busPhoneList;
	}

	public BusPhone getPhone() {
		return phone;
	}

	public void setPhone(BusPhone phone) {
		this.phone = phone;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getBusPhoneSimple() {
		return busPhoneSimple;
	}

	public void setBusPhoneSimple(String busPhoneSimple) {
		this.busPhoneSimple = busPhoneSimple;
	}

	public Date getPhoneStartDate() {
		return phoneStartDate;
	}

	public void setPhoneStartDate(Date phoneStartDate) {
		this.phoneStartDate = phoneStartDate;
	}

	public Date getPhoneEndDate() {
		return phoneEndDate;
	}

	public void setPhoneEndDate(Date phoneEndDate) {
		this.phoneEndDate = phoneEndDate;
	}

	public String getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(String phoneState) {
		this.phoneState = phoneState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneRemark() {
		return phoneRemark;
	}

	public void setPhoneRemark(String phoneRemark) {
		this.phoneRemark = phoneRemark;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	

}
