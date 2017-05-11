package com.kintiger.platform.distributionDataAppendRepMon.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataAppendRepMon.dao.IDistributionDataAppendRepMonDao;
import com.kintiger.platform.distributionDataAppendRepMon.pojo.DistributionDataAppendRepMon;
import com.kintiger.platform.distributionDataAppendRepMon.service.IDistributionDataAppendRepMonService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionDataAppendRepMonServiceImpl implements IDistributionDataAppendRepMonService {
	private IDistributionDataAppendRepMonDao distributionDataAppendRepMonDao;
	private static final Logger logger = Logger
			.getLogger(DistributionDataAppendRepMonServiceImpl.class);

	public int getDistributionDataAppendRepMonCount(DistributionDataAppendRepMon dGoal) {
		try {
			return distributionDataAppendRepMonDao
					.getDistributionDataAppendRepMonCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataAppendRepMonSize(DistributionDataAppendRepMon dGoal) {
		try {
			return distributionDataAppendRepMonDao
					.getDistributionDataAppendRepMonSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	public List<DistributionDataAppendRepMon> getDistributionDataAppendRepMonList(
			DistributionDataAppendRepMon dGoal) {
		List<DistributionDataAppendRepMon> list = null;
		try {
			list = distributionDataAppendRepMonDao
					.getDistributionDataAppendRepMonList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionDataAppendRepMon(
			DistributionDataAppendRepMon dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataAppendRepId = distributionDataAppendRepMonDao
				.insertDistributionDataAppendRepMon(dGoal);
		if (distributionDataAppendRepId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionDataAppendRepMon(DistributionDataAppendRepMon dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendRepMonService.ERROR);
		result.setResult(IDistributionDataAppendRepMonService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendRepMonDao.deleteDistributionDataAppendRepMon(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataAppendRepMonService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataAppendRepMonDao getDistributionDataAppendRepMonDao() {
		return distributionDataAppendRepMonDao;
	}

	public void setDistributionDataAppendRepMonDao(IDistributionDataAppendRepMonDao distributionDataAppendRepMonDao) {
		this.distributionDataAppendRepMonDao = distributionDataAppendRepMonDao;
	}

	@Override
	public StringResult updateDistributionDataAppendRepMon(
			DistributionDataAppendRepMon dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendRepMonService.ERROR);
		result.setResult(IDistributionDataAppendRepMonService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendRepMonDao.updateDistributionDataAppendRepMon(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataAppendRepMonService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
