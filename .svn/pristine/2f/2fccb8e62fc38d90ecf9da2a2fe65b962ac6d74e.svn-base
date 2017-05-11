package com.kintiger.platform.distributionDataRepMon.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionDataRepMon.dao.IDistributionDataRepMonDao;
import com.kintiger.platform.distributionDataRepMon.pojo.DistributionDataRepMon;

public class DistributionDataRepMonDaoImpl extends BaseDaoImpl implements IDistributionDataRepMonDao{

	public int getDistributionDataRepMonCount(DistributionDataRepMon dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataRepMon.getDistributionDataRepMonCount", dData);
	}
	
	public int getDistributionDataRepMonSize(DistributionDataRepMon dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionDataRepMon.getDistributionDataRepMonSize", dData);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionDataRepMon> getDistributionDataRepMonList(DistributionDataRepMon dData) {
		return (List<DistributionDataRepMon>) getSqlMapClientTemplate().queryForList(
				"distributionDataRepMon.getDistributionDataRepMonList", dData);
	}

	public long insertDistributionDataRepMon(DistributionDataRepMon dData) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionDataRepMon.insertDistributionDataRepMon", dData);
	}

	public int deleteDistributionDataRepMon(DistributionDataRepMon dData) {
		return getSqlMapClientTemplate().delete(
				"distributionDataRepMon.deleteDistributionDataRepMon", dData);
	
	}
	@Override
	public DistributionDataRepMon getOrgByOrgName(String org_city) {
		return  (DistributionDataRepMon) getSqlMapClientTemplate().queryForObject(
				"distributionDataRepMon.getOrgIdByOrgName", org_city);
	}
	public int updateDistributionDataRepMon(DistributionDataRepMon dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionDataRepMon.updateDistributionDataRepMon", dGoal);
	}
}
