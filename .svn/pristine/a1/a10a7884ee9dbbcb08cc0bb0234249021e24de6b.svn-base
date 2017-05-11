package com.kintiger.platform.kunnrManager.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnrManager.dao.IKunnrManagerDao;
import com.kintiger.platform.kunnrManager.pojo.DateControl;
import com.kintiger.platform.kunnrManager.pojo.KunnrManager;
import com.kintiger.platform.kunnrManager.pojo.Sku;
import com.kintiger.platform.kunnrManager.pojo.StockSafety;

@SuppressWarnings("rawtypes")
public class KunnrManagerDaoImpl extends BaseDaoImpl implements IKunnrManagerDao{

	@Override
	public int searchKunnrManagerListCount(KunnrManager kunnrManager) {
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("kunnrManager.searchKunnrManagerListCount", kunnrManager);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager) {
    	return (List<KunnrManager>)this.getSqlMapClientTemplate().queryForList("kunnrManager.searchKunnrManagerList", kunnrManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sku> searchSkuList(Sku sku) {
    	return (List<Sku>)this.getSqlMapClientTemplate().queryForList("kunnrManager.searchSkuList", sku);
	}

	@Override
	public int searchKunnrManagerDateCount(DateControl dateControl) {
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("kunnrManager.searchKunnrManagerDateCount", dateControl);
	}

	@Override
	public int updateKunnrManager(KunnrManager kunnrManager) {
    	return this.getSqlMapClientTemplate().update("kunnrManager.updateKunnrManager", kunnrManager);
	}
	
	@Override
	public int deleteKunnrManager(KunnrManager kunnrManager) {
    	return this.getSqlMapClientTemplate().update("kunnrManager.deleteKunnrManager", kunnrManager);
	}

	@Override
	public int createKunnrManager(KunnrManager kunnrManager) {
    	this.getSqlMapClientTemplate().insert("kunnrManager.createKunnrManager", kunnrManager);
    	return 1;
	}

	@Override
	public int updateKunnrImportant(KunnrManager kunnrManager) {
    	return this.getSqlMapClientTemplate().update("kunnrManager.updateKunnrImportant", kunnrManager);
    }

	@Override
	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager) {
    	return (KunnrManager)this.getSqlMapClientTemplate().queryForObject("kunnrManager.getKunnrManagerListSum", kunnrManager);
	}

	@Override
	public int searchStockSafetyListCount(StockSafety stockSafety) {
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("kunnrManager.searchStockSafetyListCount", stockSafety);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockSafety> searchStockSafetyList(StockSafety stockSafety) {
    	return (List<StockSafety>)this.getSqlMapClientTemplate().queryForList("kunnrManager.searchStockSafetyList", stockSafety);
	}

	@Override
	public int createStockSafety(StockSafety stockSafety) {
    	this.getSqlMapClientTemplate().insert("kunnrManager.createStockSafety", stockSafety);
    	return 1;
    }

	@Override
	public int updateStockSafety(StockSafety stockSafety) {
    	return (Integer)this.getSqlMapClientTemplate().update("kunnrManager.updateStockSafety", stockSafety);
	}
	
	@Override
	public String getKunagByKunnr(String kunnr) {
    	return (String)this.getSqlMapClientTemplate().queryForObject("kunnrManager.getKunagByKunnr", kunnr);
	}

	@Override
	public int searchProductionPlanListCount(KunnrManager kunnrManager) {
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("kunnrManager.searchProductionPlanListCount", kunnrManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KunnrManager> searchProductionPlanList(KunnrManager kunnrManager) {
    	return (List<KunnrManager>)this.getSqlMapClientTemplate().queryForList("kunnrManager.searchProductionPlanList", kunnrManager);
	}

	@Override
	public int searchProductionNeedListCount(KunnrManager kunnrManager) {
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("kunnrManager.searchProductionNeedListCount", kunnrManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KunnrManager> searchProductionNeedList(KunnrManager kunnrManager) {
    	return (List<KunnrManager>)this.getSqlMapClientTemplate().queryForList("kunnrManager.searchProductionNeedList", kunnrManager);
	}

	@Override
	public Double getKunnrManagerYear(KunnrManager kunnrManager) {
    	return (Double)this.getSqlMapClientTemplate().queryForObject("kunnrManager.getKunnrManagerYear", kunnrManager);
	}

	@Override
	public Double getSalesYear(KunnrManager kunnrManager) {
    	return (Double)this.getSqlMapClientTemplate().queryForObject("kunnrManager.getSalesYear", kunnrManager);
	}

}
