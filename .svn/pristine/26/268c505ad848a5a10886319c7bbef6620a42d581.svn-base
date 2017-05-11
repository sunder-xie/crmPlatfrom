package com.kintiger.platform.kunnrManager.dao;

import java.util.List;

import com.kintiger.platform.kunnrManager.pojo.DateControl;
import com.kintiger.platform.kunnrManager.pojo.KunnrManager;
import com.kintiger.platform.kunnrManager.pojo.Sku;
import com.kintiger.platform.kunnrManager.pojo.StockSafety;

public interface IKunnrManagerDao {
	public int searchKunnrManagerListCount(KunnrManager kunnrManager);
	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager);
	public List<Sku> searchSkuList(Sku sku);
	public int searchKunnrManagerDateCount(DateControl dateControl);
	public int updateKunnrManager(KunnrManager kunnrManager);
	public int deleteKunnrManager(KunnrManager kunnrManager);
	public int createKunnrManager(KunnrManager kunnrManager);
	public int updateKunnrImportant(KunnrManager kunnrManager);
	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager);
	
	public int searchStockSafetyListCount(StockSafety stockSafety);
	public List<StockSafety> searchStockSafetyList(StockSafety stockSafety);
	public int createStockSafety(StockSafety stockSafety);
	public int updateStockSafety(StockSafety stockSafety);
	public String getKunagByKunnr(String kunnr);
	public int searchProductionPlanListCount(KunnrManager kunnrManager);
	public List<KunnrManager> searchProductionPlanList(KunnrManager kunnrManager);
	public int searchProductionNeedListCount(KunnrManager kunnrManager);
	public List<KunnrManager> searchProductionNeedList(KunnrManager kunnrManager);
	public Double getKunnrManagerYear(KunnrManager kunnrManager);
	public Double getSalesYear(KunnrManager kunnrManager);

}
