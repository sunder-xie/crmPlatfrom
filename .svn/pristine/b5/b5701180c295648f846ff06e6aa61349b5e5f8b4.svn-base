package com.kintiger.platform.distributionDataRep.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionDataRep.dao.IDistributionDataRepDao;
import com.kintiger.platform.distributionDataRep.pojo.DistributionDataRep;

public class DistributionDataRepDaoImpl extends BaseDaoImpl implements IDistributionDataRepDao{

	public int getDistributionDataRepCount(DistributionDataRep dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataRep.getDistributionDataRepCount", dData);
	}
	
	public int getDistributionDataRepSize(DistributionDataRep dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataRep.getDistributionDataRepSize", dData);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionDataRep> getDistributionDataRepList(DistributionDataRep dData) {
		return (List<DistributionDataRep>) getSqlMapClientTemplate().queryForList(
				"distributionDataRep.getDistributionDataRepList", dData);
	}

	public long insertDistributionDataRep(DistributionDataRep dData) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionDataRep.insertDistributionDataRep", dData);
	}

	public int deleteDistributionDataRep(DistributionDataRep dData) {
		return getSqlMapClientTemplate().delete(
				"distributionDataRep.deleteDistributionDataRep", dData);
	
	}
	@Override
	public DistributionDataRep getOrgByOrgName(String org_city) {
		return  (DistributionDataRep) getSqlMapClientTemplate().queryForObject(
				"distributionDataRep.getOrgIdByOrgName", org_city);
	}
	public int updateDistributionDataRep(DistributionDataRep dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionDataRep.updateDistributionDataRep", dGoal);
	}
	
	public int getDistributionDataRepMatterBoxNum(DistributionDataRep dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataRep.getDistributionDataRepMatterBoxNum", dData);
	}
	
	public DistributionDataRep getDistributionDataRepMaxDateBoxNum(DistributionDataRep dData) {
		return (DistributionDataRep) getSqlMapClientTemplate().queryForObject(
				"distributionDataRep.getDistributionDataRepMaxDateBoxNum", dData);
	}
}
