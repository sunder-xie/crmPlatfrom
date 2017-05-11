package com.kintiger.platform.distributionData.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionData.dao.IDistributionDataDao;
import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.distributionData.service.IDistributionDataService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionDataServiceImpl implements IDistributionDataService {
	private IDistributionDataDao distributionDataDao;
	private static final Logger logger = Logger
			.getLogger(DistributionDataServiceImpl.class);

	public int getDistributionDataCount(DistributionData dGoal) {
		try {
			return distributionDataDao
					.getDistributionDataCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataSize(DistributionData dGoal) {
		try {
			return distributionDataDao
					.getDistributionDataSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	@Override
	public DistributionData getOrgByOrgName(String org_city) {
		try{
			return distributionDataDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}
	
	public List<DistributionData> getDistributionDataList(
			DistributionData dGoal) {
		List<DistributionData> list = null;
		try {
			list = distributionDataDao
					.getDistributionDataList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionData(
			DistributionData dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataId = distributionDataDao
				.insertDistributionData(dGoal);
		if (distributionDataId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionData(DistributionData dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataService.ERROR);
		result.setResult(IDistributionDataService.ERROR_MESSAGE);
		try {
			int c = distributionDataDao.deleteDistributionData(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataDao getDistributionDataDao() {
		return distributionDataDao;
	}

	public void setDistributionDataDao(IDistributionDataDao distributionDataDao) {
		this.distributionDataDao = distributionDataDao;
	}
	public StringResult updateDistributionData(DistributionData dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataService.ERROR);
		result.setResult(IDistributionDataService.ERROR_MESSAGE);
		try {
			int c = distributionDataDao.updateDistributionData(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
