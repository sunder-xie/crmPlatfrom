package com.kintiger.platform.distributionDataAppend.dao;

import java.util.List;

import com.kintiger.platform.distributionDataAppend.pojo.DistributionDataAppend;


public interface IDistributionDataAppendDao {

	public int getDistributionDataAppendCount(DistributionDataAppend dGoal);
	public int getDistributionDataAppendSize(DistributionDataAppend dGoal);
	
	public List<DistributionDataAppend> getDistributionDataAppendList(DistributionDataAppend dGoal);
	
	public long insertDistributionDataAppend(DistributionDataAppend dGoal);
	public int deleteDistributionDataAppend(DistributionDataAppend dGoal);
	public int updateDistributionDataAppend(DistributionDataAppend dGoal);
}
