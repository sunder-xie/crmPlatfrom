package com.kintiger.platform.distributionGoalAppend.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionGoalAppend.dao.IDistributionGoalAppendDao;
import com.kintiger.platform.distributionGoalAppend.pojo.DistributionGoalAppend;


public class DistributionGoalAppendDaoImpl extends BaseDaoImpl implements IDistributionGoalAppendDao{

	public int getDistributionGoalAppendCount(DistributionGoalAppend dDataAppend) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionGoalAppend.getDistributionGoalAppendCount", dDataAppend);
	}
	
	public int getDistributionGoalAppendSize(DistributionGoalAppend dDataAppend) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionGoalAppend.getDistributionGoalAppendSize", dDataAppend);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionGoalAppend> getDistributionGoalAppendList(DistributionGoalAppend dDataAppend) {
		return (List<DistributionGoalAppend>) getSqlMapClientTemplate().queryForList(
				"distributionGoalAppend.getDistributionGoalAppendList", dDataAppend);
	}

	public long insertDistributionGoalAppend(DistributionGoalAppend dDataAppend) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionGoalAppend.insertDistributionGoalAppend", dDataAppend);
	}

	public int deleteDistributionGoalAppend(DistributionGoalAppend dDataAppend) {
		return getSqlMapClientTemplate().delete(
				"distributionGoalAppend.deleteDistributionGoalAppend", dDataAppend);
	
	}

	@Override
	public int updateDistributionGoalAppend(DistributionGoalAppend dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionGoalAppend.updateDistributionGoalAppend", dGoal);
	}
	
}
