package com.kintiger.platform.distributionDataAppend.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataAppend.dao.IDistributionDataAppendDao;
import com.kintiger.platform.distributionDataAppend.pojo.DistributionDataAppend;
import com.kintiger.platform.distributionDataAppend.service.IDistributionDataAppendService;
import com.kintiger.platform.distributionGoal.service.IDistributionGoalService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionDataAppendServiceImpl implements IDistributionDataAppendService {
	private IDistributionDataAppendDao distributionDataAppendDao;
	private static final Logger logger = Logger
			.getLogger(DistributionDataAppendServiceImpl.class);

	public int getDistributionDataAppendCount(DistributionDataAppend dGoal) {
		try {
			return distributionDataAppendDao
					.getDistributionDataAppendCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataAppendSize(DistributionDataAppend dGoal) {
		try {
			return distributionDataAppendDao
					.getDistributionDataAppendSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	public List<DistributionDataAppend> getDistributionDataAppendList(
			DistributionDataAppend dGoal) {
		List<DistributionDataAppend> list = null;
		try {
			list = distributionDataAppendDao
					.getDistributionDataAppendList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionDataAppend(
			DistributionDataAppend dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataAppendId = distributionDataAppendDao
				.insertDistributionDataAppend(dGoal);
		if (distributionDataAppendId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionDataAppend(DistributionDataAppend dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendService.ERROR);
		result.setResult(IDistributionDataAppendService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendDao.deleteDistributionDataAppend(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataAppendService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataAppendDao getDistributionDataAppendDao() {
		return distributionDataAppendDao;
	}

	public void setDistributionDataAppendDao(IDistributionDataAppendDao distributionDataAppendDao) {
		this.distributionDataAppendDao = distributionDataAppendDao;
	}

	@Override
	public StringResult updateDistributionDataAppend(
			DistributionDataAppend dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataAppendService.ERROR);
		result.setResult(IDistributionDataAppendService.ERROR_MESSAGE);
		try {
			int c = distributionDataAppendDao.updateDistributionDataAppend(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
