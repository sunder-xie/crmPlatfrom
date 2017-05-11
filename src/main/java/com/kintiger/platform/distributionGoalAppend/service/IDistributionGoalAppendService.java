package com.kintiger.platform.distributionGoalAppend.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionGoalAppend.pojo.DistributionGoalAppend;



public interface IDistributionGoalAppendService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionGoalAppendCount(DistributionGoalAppend dGoal);
    public int getDistributionGoalAppendSize(DistributionGoalAppend dGoal);
    public List<DistributionGoalAppend> getDistributionGoalAppendList(DistributionGoalAppend dGoal);
    public BooleanResult insertDistributionGoalAppend(DistributionGoalAppend dGoal);
    public StringResult deleteDistributionGoalAppend(DistributionGoalAppend dGoal);
    public StringResult updateDistributionGoalAppend(DistributionGoalAppend dGoal);
}
