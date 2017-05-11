package com.kintiger.platform.distributionDataAppendRep.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataAppendRep.pojo.DistributionDataAppendRep;



public interface IDistributionDataAppendRepService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionDataAppendRepCount(DistributionDataAppendRep dGoal);
    public int getDistributionDataAppendRepSize(DistributionDataAppendRep dGoal);
    public List<DistributionDataAppendRep> getDistributionDataAppendRepList(DistributionDataAppendRep dGoal);
    public BooleanResult insertDistributionDataAppendRep(DistributionDataAppendRep dGoal);
    public StringResult deleteDistributionDataAppendRep(DistributionDataAppendRep dGoal);
    public StringResult updateDistributionDataAppendRep(DistributionDataAppendRep dGoal);
}
