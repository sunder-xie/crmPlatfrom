/********************************************
 * 文件名称: DistributionGoalServiceImpl.java
 * 系统名称: EXP平台 V1.0
 * 模块名称: 经销商分销目标量管理
 * 软件版权: 香飘飘股份有限公司
 * 功能说明: 
 * 系统版本: 1.0.0.1
 * 开发人员: xly
 * 开发时间: 2013-12-21
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.kintiger.platform.distributionGoal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionGoal.dao.IDistributionGoalDao;
import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;
import com.kintiger.platform.distributionGoal.service.IDistributionGoalService;
import com.kintiger.platform.framework.util.LogUtil;



public class DistributionGoalServiceImpl implements IDistributionGoalService {
	private IDistributionGoalDao distributionGoalDao;
	private static final Logger logger = Logger
			.getLogger(DistributionGoalServiceImpl.class);

	public int getDistributionGoalCount(DistributionGoal dGoal) {
		try {
			return distributionGoalDao
					.getDistributionGoalCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	public List<DistributionGoal> getDistributionGoalList(
			DistributionGoal dGoal) {
		List<DistributionGoal> list = null;
		try {
			list = distributionGoalDao
					.getDistributionGoalList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionGoal(
			DistributionGoal dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionGoalId = distributionGoalDao
				.insertDistributionGoal(dGoal);
		if (distributionGoalId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionGoal(DistributionGoal dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalService.ERROR);
		result.setResult(IDistributionGoalService.ERROR_MESSAGE);
		try {
			int c = distributionGoalDao.deleteDistributionGoal(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionGoalDao getDistributionGoalDao() {
		return distributionGoalDao;
	}

	public void setDistributionGoalDao(IDistributionGoalDao distributionGoalDao) {
		this.distributionGoalDao = distributionGoalDao;
	}

	@Override
	public int getDistributionGoalSize(DistributionGoal dGoal) {
		try {
			return distributionGoalDao
					.getDistributionGoalSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}

	
	public StringResult checkDistributionGoal(DistributionGoal dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalService.ERROR);
		result.setResult(IDistributionGoalService.ERROR_MESSAGE);
		try {
			int c = distributionGoalDao.checkDistributionGoal(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}

	@Override
	public DistributionGoal getOrgByOrgName(String org_city) {
		try{
			return distributionGoalDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}

	@Override
	public StringResult updateDistributionGoal(DistributionGoal dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalService.ERROR);
		result.setResult(IDistributionGoalService.ERROR_MESSAGE);
		try {
			int c = distributionGoalDao.updateDistributionGoal(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
}
