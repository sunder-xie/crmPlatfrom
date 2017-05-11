package com.kintiger.platform.distributionDataRepMon.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataRepMon.dao.IDistributionDataRepMonDao;
import com.kintiger.platform.distributionDataRepMon.pojo.DistributionDataRepMon;
import com.kintiger.platform.distributionDataRepMon.service.IDistributionDataRepMonService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionDataRepMonServiceImpl implements IDistributionDataRepMonService {
	private IDistributionDataRepMonDao distributionDataRepMonDao;
	private static final Logger logger = Logger
			.getLogger(DistributionDataRepMonServiceImpl.class);

	public int getDistributionDataRepMonCount(DistributionDataRepMon dGoal) {
		try {
			return distributionDataRepMonDao
					.getDistributionDataRepMonCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataRepMonSize(DistributionDataRepMon dGoal) {
		try {
			return distributionDataRepMonDao
					.getDistributionDataRepMonSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	@Override
	public DistributionDataRepMon getOrgByOrgName(String org_city) {
		try{
			return distributionDataRepMonDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}
	
	public List<DistributionDataRepMon> getDistributionDataRepMonList(
			DistributionDataRepMon dGoal) {
		List<DistributionDataRepMon> list = null;
		try {
			list = distributionDataRepMonDao
					.getDistributionDataRepMonList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionDataRepMon(
			DistributionDataRepMon dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataId = distributionDataRepMonDao
				.insertDistributionDataRepMon(dGoal);
		if (distributionDataId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionDataRepMon(DistributionDataRepMon dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataRepMonService.ERROR);
		result.setResult(IDistributionDataRepMonService.ERROR_MESSAGE);
		try {
			int c = distributionDataRepMonDao.deleteDistributionDataRepMon(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataRepMonService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataRepMonDao getDistributionDataRepMonDao() {
		return distributionDataRepMonDao;
	}

	public void setDistributionDataRepMonDao(IDistributionDataRepMonDao distributionDataRepMonDao) {
		this.distributionDataRepMonDao = distributionDataRepMonDao;
	}
	public StringResult updateDistributionDataRepMon(DistributionDataRepMon dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataRepMonService.ERROR);
		result.setResult(IDistributionDataRepMonService.ERROR_MESSAGE);
		try {
			int c = distributionDataRepMonDao.updateDistributionDataRepMon(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataRepMonService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
