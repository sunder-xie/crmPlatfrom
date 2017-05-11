package com.kintiger.platform.visitInfo.dao;

import java.util.List;

import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.visitInfo.pojo.Stock;
import com.kintiger.platform.visitInfo.pojo.VisitInfo;

public interface IVisitInfoDao {
	
	public List<VisitInfo> getVisitInfo(VisitInfo visitInfo);
	
	public int getVisitInfoCount(VisitInfo visitInfo);
	
	public VisitInfo getVisitInfoDetail(VisitInfo visitInfo);
	
	public int getStockCount(Stock stock);
	
	public List<Stock> getStockList(Stock stock);
	
	public int getSkuCount(Sku sku);
	
	public List<Sku> getSkuNameList(Sku sku);
	
    public List<VisitInfo> getOrderInfo(VisitInfo visitInfo);
	
	public int getOrderInfoCount(VisitInfo visitInfo);
	
    public List<VisitInfo> getVisitInfoUser(VisitInfo visitInfo);
	
	public int getVisitInfoUserCount(VisitInfo visitInfo);
	
	public List<VisitInfo> getVisitInfoTotal(VisitInfo visitInfo);
	
	public Integer getVisitInfoTotalLineNum(VisitInfo visitInfo);

}
