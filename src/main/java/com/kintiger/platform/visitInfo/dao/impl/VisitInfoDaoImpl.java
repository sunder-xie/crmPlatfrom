package com.kintiger.platform.visitInfo.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.visitInfo.dao.IVisitInfoDao;
import com.kintiger.platform.visitInfo.pojo.Stock;
import com.kintiger.platform.visitInfo.pojo.VisitInfo;

@SuppressWarnings("rawtypes")
public class VisitInfoDaoImpl extends BaseDaoImpl implements IVisitInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<VisitInfo> getVisitInfo(VisitInfo visitInfo) {
		return (List<VisitInfo>)getSqlMapClientTemplate().queryForList("visitInfo.getVisitInfo", visitInfo);
	}
	
	public int getVisitInfoCount(VisitInfo visitInfo){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("visitInfo.getVisitInfoCount", visitInfo);
	}
	
	public VisitInfo getVisitInfoDetail(VisitInfo visitInfo){
		return (VisitInfo)this.getSqlMapClientTemplate().queryForObject("visitInfo.getVisitInfoDetail", visitInfo);
	}
	
    public int getStockCount(Stock stock){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("visitInfo.getStockCount", stock);
    }
	
	@SuppressWarnings("unchecked")
	public List<Stock> getStockList(Stock stock){
		return this.getSqlMapClientTemplate().queryForList("visitInfo.getStockList", stock);
	}
	
	@Override
	public int getSkuCount(Sku sku) {
		return (Integer) getSqlMapClientTemplate().queryForObject("visitInfo.getSkuCount",sku);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		return (List<Sku>) getSqlMapClientTemplate().queryForList("visitInfo.getSkuNameList", sku);
	}
	
    @SuppressWarnings("unchecked")
	public List<VisitInfo> getOrderInfo(VisitInfo visitInfo){
    	return (List<VisitInfo>)getSqlMapClientTemplate().queryForList("visitInfo.getOrderInfo", visitInfo);
    }
	
	public int getOrderInfoCount(VisitInfo visitInfo){
		return (Integer) getSqlMapClientTemplate().queryForObject("visitInfo.getOrderInfoCount",visitInfo);
	}
	
    @SuppressWarnings("unchecked")
	public List<VisitInfo> getVisitInfoUser(VisitInfo visitInfo){
    	return (List<VisitInfo>)getSqlMapClientTemplate().queryForList("visitInfo.getVisitInfoUser", visitInfo);
    }
	
	public int getVisitInfoUserCount(VisitInfo visitInfo){
		return (Integer) getSqlMapClientTemplate().queryForObject("visitInfo.getVisitInfoUserCount",visitInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<VisitInfo> getVisitInfoTotal(VisitInfo visitInfo){
    	return (List<VisitInfo>)getSqlMapClientTemplate().queryForList("visitInfo.getVisitInfoTotal", visitInfo);
	}
	
	public Integer getVisitInfoTotalLineNum(VisitInfo visitInfo){
		return (Integer) getSqlMapClientTemplate().queryForObject("visitInfo.getVisitInfoTotalLineNum",visitInfo);
	}

}
