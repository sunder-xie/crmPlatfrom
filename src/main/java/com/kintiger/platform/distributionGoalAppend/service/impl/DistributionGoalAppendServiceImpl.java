package com.kintiger.platform.distributionGoalAppend.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionGoalAppend.dao.IDistributionGoalAppendDao;
import com.kintiger.platform.distributionGoalAppend.pojo.DistributionGoalAppend;
import com.kintiger.platform.distributionGoalAppend.service.IDistributionGoalAppendService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionGoalAppendServiceImpl implements IDistributionGoalAppendService {
	private IDistributionGoalAppendDao distributionGoalAppendDao;
	private static final Logger logger = Logger
			.getLogger(DistributionGoalAppendServiceImpl.class);

	public int getDistributionGoalAppendCount(DistributionGoalAppend dGoal) {
		try {
			return distributionGoalAppendDao
					.getDistributionGoalAppendCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionGoalAppendSize(DistributionGoalAppend dGoal) {
		try {
			return distributionGoalAppendDao
					.getDistributionGoalAppendSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	public List<DistributionGoalAppend> getDistributionGoalAppendList(
			DistributionGoalAppend dGoal) {
		List<DistributionGoalAppend> list = null;
		try {
			list = distributionGoalAppendDao
					.getDistributionGoalAppendList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionGoalAppend(
			DistributionGoalAppend dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionGoalAppendId = distributionGoalAppendDao
				.insertDistributionGoalAppend(dGoal);
		if (distributionGoalAppendId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionGoalAppend(DistributionGoalAppend dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalAppendService.ERROR);
		result.setResult(IDistributionGoalAppendService.ERROR_MESSAGE);
		try {
			int c = distributionGoalAppendDao.deleteDistributionGoalAppend(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalAppendService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionGoalAppendDao getDistributionGoalAppendDao() {
		return distributionGoalAppendDao;
	}

	public void setDistributionGoalAppendDao(IDistributionGoalAppendDao distributionGoalAppendDao) {
		this.distributionGoalAppendDao = distributionGoalAppendDao;
	}

	@Override
	public StringResult updateDistributionGoalAppend(
			DistributionGoalAppend dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalAppendService.ERROR);
		result.setResult(IDistributionGoalAppendService.ERROR_MESSAGE);
		try {
			int c = distributionGoalAppendDao.updateDistributionGoalAppend(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalAppendService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
