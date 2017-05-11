package com.kintiger.platform.distributionDataAppendRepMon.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionDataAppendRepMon.dao.IDistributionDataAppendRepMonDao;
import com.kintiger.platform.distributionDataAppendRepMon.pojo.DistributionDataAppendRepMon;


public class DistributionDataAppendRepMonDaoImpl extends BaseDaoImpl implements IDistributionDataAppendRepMonDao{

	public int getDistributionDataAppendRepMonCount(DistributionDataAppendRepMon dDataAppendRep) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppendRepMon.getDistributionDataAppendRepMonCount", dDataAppendRep);
	}
	
	public int getDistributionDataAppendRepMonSize(DistributionDataAppendRepMon dDataAppendRep) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataAppendRepMon.getDistributionDataAppendRepMonSize", dDataAppendRep);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionDataAppendRepMon> getDistributionDataAppendRepMonList(DistributionDataAppendRepMon dDataAppendRep) {
		return (List<DistributionDataAppendRepMon>) getSqlMapClientTemplate().queryForList(
				"distributionDataAppendRepMon.getDistributionDataAppendRepMonList", dDataAppendRep);
	}

	public long insertDistributionDataAppendRepMon(DistributionDataAppendRepMon dDataAppendRep) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionDataAppendRepMon.insertDistributionDataAppendRepMon", dDataAppendRep);
	}

	public int deleteDistributionDataAppendRepMon(DistributionDataAppendRepMon dDataAppendRep) {
		return getSqlMapClientTemplate().delete(
				"distributionDataAppendRepMon.deleteDistributionDataAppendRepMon", dDataAppendRep);
	
	}

	@Override
	public int updateDistributionDataAppendRepMon(DistributionDataAppendRepMon dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionDataAppendRepMon.updateDistributionDataAppendRepMon", dGoal);
	}

	
	
}
