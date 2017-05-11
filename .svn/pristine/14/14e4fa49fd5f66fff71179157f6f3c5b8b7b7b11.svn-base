package com.kintiger.platform.distributionInventory.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionInventory.dao.IDistributionInventoryDao;
import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;
import com.kintiger.platform.distributionInventory.service.IDistributionInventoryService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionInventoryServiceImpl implements IDistributionInventoryService {
	private IDistributionInventoryDao distributionInventoryDao;
	private static final Logger logger = Logger
			.getLogger(DistributionInventoryServiceImpl.class);

	public int getDistributionInventoryCount(DistributionInventory dGoal) {
		try {
			return distributionInventoryDao
					.getDistributionInventoryCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionInventorySize(DistributionInventory dGoal) {
		try {
			return distributionInventoryDao
					.getDistributionInventorySize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	@Override
	public DistributionInventory getOrgByOrgName(String org_city) {
		try{
			return distributionInventoryDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}
	
	public List<DistributionInventory> getDistributionInventoryList(
			DistributionInventory dGoal) {
		List<DistributionInventory> list = null;
		try {
			list = distributionInventoryDao
					.getDistributionInventoryList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionInventory(
			DistributionInventory dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionInventoryId = distributionInventoryDao
				.insertDistributionInventory(dGoal);
		if (distributionInventoryId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionInventory(DistributionInventory dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionInventoryService.ERROR);
		result.setResult(IDistributionInventoryService.ERROR_MESSAGE);
		try {
			int c = distributionInventoryDao.deleteDistributionInventory(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionInventoryService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	
	public StringResult updateDistributionInventory(DistributionInventory dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionInventoryService.ERROR);
		result.setResult(IDistributionInventoryService.ERROR_MESSAGE);
		try {
			int c = distributionInventoryDao.updateDistributionInventory(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionInventoryService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	
	public IDistributionInventoryDao getDistributionInventoryDao() {
		return distributionInventoryDao;
	}

	public void setDistributionInventoryDao(IDistributionInventoryDao distributionInventoryDao) {
		this.distributionInventoryDao = distributionInventoryDao;
	}
}
