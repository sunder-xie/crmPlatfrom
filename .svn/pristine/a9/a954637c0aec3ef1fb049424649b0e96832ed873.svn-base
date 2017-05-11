package com.kintiger.platform.distributionDataRep.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataRep.pojo.DistributionDataRep;



public interface IDistributionDataRepService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionDataRepCount(DistributionDataRep dGoal);
    public int getDistributionDataRepSize(DistributionDataRep dGoal);
    public List<DistributionDataRep> getDistributionDataRepList(DistributionDataRep dGoal);
    public BooleanResult insertDistributionDataRep(DistributionDataRep dGoal);
    public StringResult deleteDistributionDataRep(DistributionDataRep dGoal);
    public DistributionDataRep getOrgByOrgName(String org_city);
    public StringResult updateDistributionDataRep(DistributionDataRep dGoal);
    public int getDistributionDataRepMatterBoxNum(DistributionDataRep dGoal);
    public DistributionDataRep getDistributionDataRepMaxDateBoxNum(DistributionDataRep dGoal);
	public DistributionDataRep synchBoxNum(String start_sap_date, String end_sap_date,
			DistributionDataRep dDataRep);
}
