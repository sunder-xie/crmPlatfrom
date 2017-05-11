package com.kintiger.platform.distributionData.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionData.pojo.DistributionData;



public interface IDistributionDataService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionDataCount(DistributionData dGoal);
    public int getDistributionDataSize(DistributionData dGoal);
    public List<DistributionData> getDistributionDataList(DistributionData dGoal);
    public BooleanResult insertDistributionData(DistributionData dGoal);
    public StringResult deleteDistributionData(DistributionData dGoal);
    public DistributionData getOrgByOrgName(String org_city);
    public StringResult updateDistributionData(DistributionData dGoal);
}
