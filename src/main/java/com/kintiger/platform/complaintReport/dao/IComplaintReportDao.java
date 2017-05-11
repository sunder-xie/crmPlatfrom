package com.kintiger.platform.complaintReport.dao;

import java.util.List;

import com.kintiger.platform.complaintReport.pojo.ComplaintGoal;
import com.kintiger.platform.complaintReport.pojo.ComplaintReport;

public interface IComplaintReportDao {
	public List<ComplaintReport> getComplaintReport(ComplaintReport com);
	public int getComplaintGoalCount(ComplaintGoal com);
	public List<ComplaintGoal> getComplaintGoal(ComplaintGoal com);
	public int updateComplaintGoal(ComplaintGoal com);
	public int createGoal(ComplaintGoal com);

}
