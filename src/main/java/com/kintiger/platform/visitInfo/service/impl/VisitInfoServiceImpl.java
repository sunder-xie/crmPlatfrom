package com.kintiger.platform.visitInfo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.visitInfo.dao.IVisitInfoDao;
import com.kintiger.platform.visitInfo.pojo.Stock;
import com.kintiger.platform.visitInfo.pojo.VisitInfo;
import com.kintiger.platform.visitInfo.service.IVisitInfoService;

public class VisitInfoServiceImpl implements IVisitInfoService {

	IVisitInfoDao visitInfoDao;
	private static Log logger = LogFactory.getLog(VisitInfoServiceImpl.class);
	
	@Override
	public List<VisitInfo> getVisitInfo(VisitInfo visitInfo) {
		try{
			return visitInfoDao.getVisitInfo(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public int getVisitInfoCount(VisitInfo visitInfo){
		try{
			return visitInfoDao.getVisitInfoCount(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public VisitInfo getVisitInfoDetail(VisitInfo visitInfo) {
		try{
			return visitInfoDao.getVisitInfoDetail(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return null;
		}
	}
	
    public int getStockCount(Stock stock){
    	try{
			return visitInfoDao.getStockCount(stock);
		}catch (Exception e) {
			logger.error(stock, e);
			e.printStackTrace();
			return 0;
		}
    }
	
	public List<Stock> getStockList(Stock stock){
		try{
			return visitInfoDao.getStockList(stock);
		}catch (Exception e) {
			logger.error(stock, e);
			e.printStackTrace();
			return null;
		}
	}
	
    public int getSkuCount(Sku sku){
    	try{
			return visitInfoDao.getSkuCount(sku);
		}catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return 0;
		}
    }
	
	public List<Sku> getSkuNameList(Sku sku){
		try{
			return visitInfoDao.getSkuNameList(sku);
		}catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return null;
		}
	}
	
    public List<VisitInfo> getOrderInfo(VisitInfo visitInfo){
    	try{
			return visitInfoDao.getOrderInfo(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return null;
		}
    }
	
	public int getOrderInfoCount(VisitInfo visitInfo){
		try{
			return visitInfoDao.getOrderInfoCount(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return 0;
		}
	}
	
    public List<VisitInfo> getVisitInfoUser(VisitInfo visitInfo){
    	try{
			return visitInfoDao.getVisitInfoUser(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return null;
		}
    }
	
	public int getVisitInfoUserCount(VisitInfo visitInfo){
		try{
			return visitInfoDao.getVisitInfoUserCount(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<VisitInfo> getVisitInfoTotal(VisitInfo visitInfo){
		try{
			return visitInfoDao.getVisitInfoTotal(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer getVisitInfoTotalLineNum(VisitInfo visitInfo){
		try{
			return visitInfoDao.getVisitInfoTotalLineNum(visitInfo);
		}catch (Exception e) {
			logger.error(visitInfo, e);
			e.printStackTrace();
			return 0;
		}
	}

	public IVisitInfoDao getVisitInfoDao() {
		return visitInfoDao;
	}

	public void setVisitInfoDao(IVisitInfoDao visitInfoDao) {
		this.visitInfoDao = visitInfoDao;
	}
	
	

}
