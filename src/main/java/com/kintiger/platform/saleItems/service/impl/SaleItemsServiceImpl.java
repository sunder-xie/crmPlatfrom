package com.kintiger.platform.saleItems.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.citydict.pojo.CityDict;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.saleItems.dao.ISaleItemsDao;
import com.kintiger.platform.saleItems.pojo.SaleItems;
import com.kintiger.platform.saleItems.pojo.Sku;
import com.kintiger.platform.saleItems.service.ISaleItemsService;

public class SaleItemsServiceImpl implements ISaleItemsService {

	private ISaleItemsDao saleItemsDao;
	private TransactionTemplate transactionTemplate;
	private static final Logger logger = Logger
	.getLogger(SaleItemsServiceImpl.class);

	public String getChannelName(int channelId) {
		try {
			return saleItemsDao.getChannelName(channelId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Kunnr getKunnrByKunnrId(Kunnr k) {
		return saleItemsDao.getKunnrByKunnrId(k);
	}	

	public  Borg gerOrgIsExit(Borg org) {
		try {
			return saleItemsDao.gerOrgIsExit(org);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ISaleItemsDao getSaleItemsDao() {
		return saleItemsDao;
	}

	public void setSaleItemsDao(ISaleItemsDao saleItemsDao) {
		this.saleItemsDao = saleItemsDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public BooleanResult createSaleItems(SaleItems saleItems) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long saleItemsId = saleItemsDao.createSaleItems(saleItems);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(saleItemsId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(saleItems), e);
		}

		return booleanResult;
	}

	@Override
	public int getSaleItemsCount(SaleItems saleItems) {
		try{
			return saleItemsDao.getSaleItemsCount(saleItems);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<SaleItems> getSaleItemsList(SaleItems saleItems) {
		try{
			return saleItemsDao.getSaleItemsList(saleItems);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getSkuCount(Sku sku) {
		try{
			return saleItemsDao.getSkuCount(sku);			
		}catch (Exception e ){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Sku getSkuNameById(Sku sku) {
		try{
			return saleItemsDao.getSkuNameById(sku);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		try{
			return saleItemsDao.getSkuNameList(sku);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public BooleanResult updateSaleItems(SaleItems saleItems) {
		BooleanResult booleanResult = new BooleanResult();

		try {		
			int n = saleItemsDao.updateSaleItems(saleItems);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));		
		} catch (Exception e) {		
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(saleItems), e);
		}
		return booleanResult;
	}

	@Override
	public SaleItems getSaleItems(SaleItems saleItems) {
		// TODO Auto-generated method stub
		try {
			return saleItemsDao.getSaleItems(saleItems);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(saleItems), e);
		}

		return null;
	}

	@Override
	public StringResult delSaleItems(SaleItems saleItems) {
		// TODO Auto-generated method stub
		StringResult result = new StringResult();
		result.setCode(ISaleItemsService.ERROR);
		result.setResult(ISaleItemsService.ERROR_MESSAGE);
		try {		
			int n = saleItemsDao.delSaleItems(saleItems);
			result.setCode(String.valueOf(n));
			result.setResult(SUCCESS);			
		} catch (Exception e) {		
			logger.error(LogUtil.parserBean(saleItems), e);
		}
		return result;
	}



}
