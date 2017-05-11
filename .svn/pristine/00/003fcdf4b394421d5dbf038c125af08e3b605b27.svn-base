package com.kintiger.platform.complaintReport.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.complaintReport.pojo.ComplaintGoal;
import com.kintiger.platform.complaintReport.pojo.ComplaintReport;
import com.kintiger.platform.complaintReport.pojo.ProductOutput;
import com.kintiger.platform.complaintReport.service.IComplaintReportService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class ComplaintReportAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378847097565494238L;
	
	private Date startDate1;
	private Date startDate2;
	private Date endDate1;
	private Date endDate2;
	
	private Date outputStartDate1;
	private Date outputStartDate2;
	private Date outputEndDate1;
	private Date outputEndDate2;
	
	private String startMonth;
	private String endMonth;
	private String productPlace;
	private List<ProductOutput> productOutput;
	private List<ComplaintReport>complaintReportList;
	private String data;
	
	private File file;
	
	private IComplaintReportService complaintReportService;
	
	private String download;
	
	public String toWorkShop(){
		return "toWorkShop";
	}
	
    public void download(){
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "车间目标导入模板.xls";
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
			wsheet.setColumnView(2, 20);
			wsheet.setColumnView(3, 20);
			wsheet.setColumnView(4, 10);
			
			
			wsheet.addCell(new Label(0, 0, "年份"));
			wsheet.addCell(new Label(1, 0, "月份"));
			wsheet.addCell(new Label(2, 0, "工厂（如：湖州工厂）"));
			wsheet.addCell(new Label(3, 0, "车间（如：组装车间）"));
			wsheet.addCell(new Label(4, 0, "目标"));
			
			wbook.write();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
    }
	 
    public void createGoal(){
    	ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
    	List<ComplaintGoal> list = new ArrayList<ComplaintGoal>();
        Workbook wb = null;
        
        try {
			InputStream is = new FileInputStream(file);
			wb = Workbook.getWorkbook(is);
			Sheet sheet = wb.getSheet(0);
			for(int i=1; i<sheet.getRows(); i++){
				ComplaintGoal cg=new ComplaintGoal();
				String year=sheet.getCell(0, i).getContents().trim();
				String month=sheet.getCell(1, i).getContents().trim();
				String factory=sheet.getCell(2, i).getContents().trim();
				String workshop=sheet.getCell(3, i).getContents().trim();
				double goal=Double.parseDouble(sheet.getCell(4, i).getContents().trim());
				
				int monthInt=Integer.parseInt(month);
				if(monthInt<10){
					month="0"+monthInt;
				}
				
				cg.setgMonth(Integer.parseInt(year+month));
				cg.setgFactory(factory);
				cg.setgWorkShop(workshop);
				cg.setgGoal(goal);
				
			    list.add(cg);
			}
			
			int num=0;
			for(int i=0;i<list.size();i++){
				ComplaintGoal cg=list.get(i);
				int count=complaintReportService.getComplaintGoalCount(cg);
				if(count>0){
					num=num+complaintReportService.updateComplaintGoal(cg);
				}else{
					num=num+complaintReportService.createGoal(cg);
				}
			}
			if(num==list.size()){
				ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Over");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * 校验数据是否导入完成
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
//			if("Over_s".equals(obj)){
//				this.setSuccessMessage("导入成功");
//			}else{
//				this.setFailMessage("导入失败");
//			}
		}
		return JSON;
	}
    

	public IComplaintReportService getComplaintReportService() {
		return complaintReportService;
	}
	public void setComplaintReportService(
			IComplaintReportService complaintReportService) {
		this.complaintReportService = complaintReportService;
	}
	public Date getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	public Date getStartDate2() {
		return startDate2;
	}
	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}
	public Date getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	public Date getEndDate2() {
		return endDate2;
	}
	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getProductPlace() {
		return productPlace;
	}
	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<ProductOutput> getProductOutput() {
		return productOutput;
	}
	public void setProductOutput(List<ProductOutput> productOutput) {
		this.productOutput = productOutput;
	}
	public Date getOutputStartDate1() {
		return outputStartDate1;
	}
	public void setOutputStartDate1(Date outputStartDate1) {
		this.outputStartDate1 = outputStartDate1;
	}
	public Date getOutputStartDate2() {
		return outputStartDate2;
	}
	public void setOutputStartDate2(Date outputStartDate2) {
		this.outputStartDate2 = outputStartDate2;
	}
	public Date getOutputEndDate1() {
		return outputEndDate1;
	}
	public void setOutputEndDate1(Date outputEndDate1) {
		this.outputEndDate1 = outputEndDate1;
	}
	public Date getOutputEndDate2() {
		return outputEndDate2;
	}
	public void setOutputEndDate2(Date outputEndDate2) {
		this.outputEndDate2 = outputEndDate2;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<ComplaintReport> getComplaintReportList() {
		return complaintReportList;
	}
	public void setComplaintReportList(List<ComplaintReport> complaintReportList) {
		this.complaintReportList = complaintReportList;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public void addActionError(String anErrorMessage){
		 String s=anErrorMessage;
		 System.out.println(s);
		 }
		 public void addActionMessage(String aMessage){
		 String s=aMessage;
		 System.out.println(s);
		
		 }
		 public void addFieldError(String fieldName, String errorMessage){
		 String s=errorMessage;
		 String f=fieldName;
		 System.out.println(s);
		 System.out.println(f);
		
		 }
	
	

}
