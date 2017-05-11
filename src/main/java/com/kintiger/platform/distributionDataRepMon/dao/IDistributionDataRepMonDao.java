package com.kintiger.platform.distributionDataRepMon.dao;

import java.util.List;

import com.kintiger.platform.distributionDataRepMon.pojo.DistributionDataRepMon;


public interface IDistributionDataRepMonDao {

	public int getDistributionDataRepMonCount(DistributionDataRepMon dGoal);
	public int getDistributionDataRepMonSize(DistributionDataRepMon dGoal);
	
	public List<DistributionDataRepMon> getDistributionDataRepMonList(DistributionDataRepMon dGoal);
	public DistributionDataRepMon getOrgByOrgName(String org_city);
	public long insertDistributionDataRepMon(DistributionDataRepMon dGoal);
	public int deleteDistributionDataRepMon(DistributionDataRepMon dGoal);
	public int updateDistributionDataRepMon(DistributionDataRepMon dGoal);
}
