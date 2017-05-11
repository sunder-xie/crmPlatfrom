package com.kintiger.platform.complaintReport.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.complaintReport.dao.IComplaintReportDao;
import com.kintiger.platform.complaintReport.pojo.ComplaintGoal;
import com.kintiger.platform.complaintReport.pojo.ComplaintReport;

public class ComplaintReportDaoImpl extends BaseDaoImpl implements IComplaintReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ComplaintReport> getComplaintReport(ComplaintReport com) {
		return (List<ComplaintReport>) getSqlMapClientTemplate().queryForList("complaintReport.getComplaintReport", com);
	}
	
	public int getComplaintGoalCount(ComplaintGoal com){
		return (Integer) getSqlMapClientTemplate().queryForObject("complaintReport.getComplaintGoalCount", com);
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintGoal> getComplaintGoal(ComplaintGoal com){
		return (List<ComplaintGoal>) getSqlMapClientTemplate().queryForList("complaintReport.getComplaintGoal", com);
	}
	
	public int updateComplaintGoal(ComplaintGoal com){
		return (Integer)getSqlMapClientTemplate().update("complaintReport.updateComplaintGoal", com);
	}
	
	public int createGoal(ComplaintGoal com){
		return (Integer)getSqlMapClientTemplate().insert("complaintReport.createGoal", com);
	}

}
