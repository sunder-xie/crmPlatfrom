package com.kintiger.platform.distributionInventory.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionInventory.dao.IDistributionInventoryDao;
import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;


public class DistributionInventoryDaoImpl extends BaseDaoImpl implements IDistributionInventoryDao{

	public int getDistributionInventoryCount(DistributionInventory dInventory) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionInventory.getDistributionInventoryCount", dInventory);
	}
	
	public int getDistributionInventorySize(DistributionInventory dInventory) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionInventory.getDistributionInventorySize", dInventory);
	}
	
	@Override
	public DistributionInventory getOrgByOrgName(String org_city) {
		return  (DistributionInventory) getSqlMapClientTemplate().queryForObject(
				"distributionInventory.getOrgIdByOrgName", org_city);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionInventory> getDistributionInventoryList(DistributionInventory dInventory) {
		return (List<DistributionInventory>) getSqlMapClientTemplate().queryForList(
				"distributionInventory.getDistributionInventoryList", dInventory);
	}

	public long insertDistributionInventory(DistributionInventory dInventory) {
		return  (Long) getSqlMapClientTemplate().insert(
				"distributionInventory.insertDistributionInventory", dInventory);
	}

	public int deleteDistributionInventory(DistributionInventory dInventory) {
		return getSqlMapClientTemplate().delete(
				"distributionInventory.deleteDistributionInventory", dInventory);
	
	}
	
	public int updateDistributionInventory(DistributionInventory dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionInventory.updateDistributionInventory", dGoal);
	}
}
