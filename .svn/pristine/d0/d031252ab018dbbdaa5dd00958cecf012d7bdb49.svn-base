package com.kintiger.platform.distributionDataAppendRep.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataAppendRep.dao.IDistributionDataAppendRepDao;
import com.kintiger.platform.distributionDataAppendRep.pojo.DistributionDataAppendRep;
import com.kintiger.platform.distributionDataAppendRep.service.IDistributionDataAppendRepService;
import com.kintiger.platform.distributionGoal.service.IDistributionGoalService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionDataAppendRepServiceImpl implements IDistributionDataAppendRepService {
	private IDistributionDataAppendRepDao distributionDataAppendRepDao;
	private static final Logger logger = Logger
			.getLogger(DistributionDataAppendRepServiceImpl.class);

	public int getDistributionDataAppendRepCount(DistributionDataAppendRep dGoal) {
		try {
			return distributionDataAppendRepDao
					.getDistributionDataAppendRepCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataAppendRepSize(DistributionDataAppendRep dGoal) {
		try {
			return distributionDataAppendRepDao
					.getDistributionDataAppendRepSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	public List<DistributionDataAppendRep> getDistributionDataAppendRepList(
			DistributionDataAppendRep dGoal) {
		List<DistributionDataAppendRep> list = null;
		try {
			list = distributionDataAppendRepDao
					.getDistributionDataAppendRepList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionDataAppendRep(
			DistributionDataAppendRep dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataAppendRepId = distributionDataAppendRepDao
				.insertDistributionDataAppendRep(dGoal);
		if (distributionDataAppendRepId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionDataAppendRep(DistributionDataAppendRep dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendRepService.ERROR);
		result.setResult(IDistributionDataAppendRepService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendRepDao.deleteDistributionDataAppendRep(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataAppendRepService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataAppendRepDao getDistributionDataAppendRepDao() {
		return distributionDataAppendRepDao;
	}

	public void setDistributionDataAppendRepDao(IDistributionDataAppendRepDao distributionDataAppendRepDao) {
		this.distributionDataAppendRepDao = distributionDataAppendRepDao;
	}

	@Override
	public StringResult updateDistributionDataAppendRep(
			DistributionDataAppendRep dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendRepService.ERROR);
		result.setResult(IDistributionDataAppendRepService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendRepDao.updateDistributionDataAppendRep(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
