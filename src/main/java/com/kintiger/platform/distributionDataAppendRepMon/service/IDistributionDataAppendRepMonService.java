package com.kintiger.platform.distributionDataAppendRepMon.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataAppendRepMon.pojo.DistributionDataAppendRepMon;



public interface IDistributionDataAppendRepMonService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionDataAppendRepMonCount(DistributionDataAppendRepMon dGoal);
    public int getDistributionDataAppendRepMonSize(DistributionDataAppendRepMon dGoal);
    public List<DistributionDataAppendRepMon> getDistributionDataAppendRepMonList(DistributionDataAppendRepMon dGoal);
    public BooleanResult insertDistributionDataAppendRepMon(DistributionDataAppendRepMon dGoal);
    public StringResult deleteDistributionDataAppendRepMon(DistributionDataAppendRepMon dGoal);
    public StringResult updateDistributionDataAppendRepMon(DistributionDataAppendRepMon dGoal);
}
