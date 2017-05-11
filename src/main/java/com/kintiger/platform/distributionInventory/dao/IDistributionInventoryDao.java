package com.kintiger.platform.distributionInventory.dao;

import java.util.List;

import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;


public interface IDistributionInventoryDao {

	public int getDistributionInventoryCount(DistributionInventory dGoal);
	public int getDistributionInventorySize(DistributionInventory dGoal);
	
	public List<DistributionInventory> getDistributionInventoryList(DistributionInventory dGoal);
	public DistributionInventory getOrgByOrgName(String org_city);
	public long insertDistributionInventory(DistributionInventory dGoal);
	public int deleteDistributionInventory(DistributionInventory dGoal);
	public int updateDistributionInventory(DistributionInventory dGoal);
}
