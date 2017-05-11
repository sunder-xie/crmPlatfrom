package com.kintiger.platform.distributionDataAppendRep.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionDataAppendRep.dao.IDistributionDataAppendRepDao;
import com.kintiger.platform.distributionDataAppendRep.pojo.DistributionDataAppendRep;


public class DistributionDataAppendRepDaoImpl extends BaseDaoImpl implements IDistributionDataAppendRepDao{

	public int getDistributionDataAppendRepCount(DistributionDataAppendRep dDataAppendRep) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppendRep.getDistributionDataAppendRepCount", dDataAppendRep);
	}
	
	public int getDistributionDataAppendRepSize(DistributionDataAppendRep dDataAppendRep) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppendRep.getDistributionDataAppendRepSize", dDataAppendRep);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionDataAppendRep> getDistributionDataAppendRepList(DistributionDataAppendRep dDataAppendRep) {
		return (List<DistributionDataAppendRep>) getSqlMapClientTemplate().queryForList(
				"distributionDataAppendRep.getDistributionDataAppendRepList", dDataAppendRep);
	}

	public long insertDistributionDataAppendRep(DistributionDataAppendRep dDataAppendRep) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionDataAppendRep.insertDistributionDataAppendRep", dDataAppendRep);
	}

	public int deleteDistributionDataAppendRep(DistributionDataAppendRep dDataAppendRep) {
		return getSqlMapClientTemplate().delete(
				"distributionDataAppendRep.deleteDistributionDataAppendRep", dDataAppendRep);
	
	}

	@Override
	public int updateDistributionDataAppendRep(DistributionDataAppendRep dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionDataAppendRep.updateDistributionDataAppendRep", dGoal);
	}

	
	
}
