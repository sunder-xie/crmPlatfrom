package com.kintiger.platform.complaintReport.service;

import java.util.List;

import com.kintiger.platform.complaintReport.pojo.ComplaintGoal;
import com.kintiger.platform.complaintReport.pojo.ComplaintReport;
import com.kintiger.platform.complaintReport.pojo.ProductOutput;

public interface IComplaintReportService {
	public List<ComplaintReport> getComplaintReport(ComplaintReport com);
	public List<ProductOutput> getOutput(ProductOutput output);
	
	public int getComplaintGoalCount(ComplaintGoal com);
	public List<ComplaintGoal> getComplaintGoal(ComplaintGoal com);
	public int updateComplaintGoal(ComplaintGoal com);
	public int createGoal(ComplaintGoal com);

}
