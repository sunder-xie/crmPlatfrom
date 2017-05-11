package com.kintiger.platform.distributionData.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionData.dao.IDistributionDataDao;
import com.kintiger.platform.distributionData.pojo.DistributionData;

public class DistributionDataDaoImpl extends BaseDaoImpl implements IDistributionDataDao{

	public int getDistributionDataCount(DistributionData dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionData.getDistributionDataCount", dData);
	}
	
	public int getDistributionDataSize(DistributionData dData) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionData.getDistributionDataSize", dData);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionData> getDistributionDataList(DistributionData dData) {
		return (List<DistributionData>) getSqlMapClientTemplate().queryForList(
				"distributionData.getDistributionDataList", dData);
	}

	public long insertDistributionData(DistributionData dData) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionData.insertDistributionData", dData);
	}

	public int deleteDistributionData(DistributionData dData) {
		return getSqlMapClientTemplate().delete(
				"distributionData.deleteDistributionData", dData);
	
	}
	@Override
	public DistributionData getOrgByOrgName(String org_city) {
		return  (DistributionData) getSqlMapClientTemplate().queryForObject(
				"distributionData.getOrgIdByOrgName", org_city);
	}
	public int updateDistributionData(DistributionData dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionData.updateDistributionData", dGoal);
	}
}
