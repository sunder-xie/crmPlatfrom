package com.kintiger.platform.distributionDataAppend.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionDataAppend.dao.IDistributionDataAppendDao;
import com.kintiger.platform.distributionDataAppend.pojo.DistributionDataAppend;


public class DistributionDataAppendDaoImpl extends BaseDaoImpl implements IDistributionDataAppendDao{

	public int getDistributionDataAppendCount(DistributionDataAppend dDataAppend) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppend.getDistributionDataAppendCount", dDataAppend);
	}
	
	public int getDistributionDataAppendSize(DistributionDataAppend dDataAppend) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppend.getDistributionDataAppendSize", dDataAppend);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionDataAppend> getDistributionDataAppendList(DistributionDataAppend dDataAppend) {
		return (List<DistributionDataAppend>) getSqlMapClientTemplate().queryForList(
				"distributionDataAppend.getDistributionDataAppendList", dDataAppend);
	}

	public long insertDistributionDataAppend(DistributionDataAppend dDataAppend) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionDataAppend.insertDistributionDataAppend", dDataAppend);
	}

	public int deleteDistributionDataAppend(DistributionDataAppend dDataAppend) {
		return getSqlMapClientTemplate().delete(
				"distributionDataAppend.deleteDistributionDataAppend", dDataAppend);
	
	}

	@Override
	public int updateDistributionDataAppend(DistributionDataAppend dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionDataAppend.updateDistributionDataAppend", dGoal);
	}
	
}
