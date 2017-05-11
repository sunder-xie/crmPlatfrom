package com.kintiger.platform.distributionInventory.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;



public interface IDistributionInventoryService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

    public int getDistributionInventoryCount(DistributionInventory dGoal);
    public int getDistributionInventorySize(DistributionInventory dGoal);
    public List<DistributionInventory> getDistributionInventoryList(DistributionInventory dGoal);
    public BooleanResult insertDistributionInventory(DistributionInventory dGoal);
    public StringResult deleteDistributionInventory(DistributionInventory dGoal);
    public DistributionInventory getOrgByOrgName(String org_city);
    public StringResult updateDistributionInventory(DistributionInventory dGoal);
}
