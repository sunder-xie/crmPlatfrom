package com.kintiger.platform.distributionDataAppendRep.dao;

import java.util.List;

import com.kintiger.platform.distributionDataAppendRep.pojo.DistributionDataAppendRep;


public interface IDistributionDataAppendRepDao {

	public int getDistributionDataAppendRepCount(DistributionDataAppendRep dGoal);
	public int getDistributionDataAppendRepSize(DistributionDataAppendRep dGoal);
	
	public List<DistributionDataAppendRep> getDistributionDataAppendRepList(DistributionDataAppendRep dGoal);
	
	public long insertDistributionDataAppendRep(DistributionDataAppendRep dGoal);
	public int deleteDistributionDataAppendRep(DistributionDataAppendRep dGoal);
	public int updateDistributionDataAppendRep(DistributionDataAppendRep dGoal);
}
