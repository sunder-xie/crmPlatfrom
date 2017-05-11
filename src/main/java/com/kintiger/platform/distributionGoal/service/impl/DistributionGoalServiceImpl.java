/********************************************
 * �ļ�����: DistributionGoalServiceImpl.java
 * ϵͳ����: EXPƽ̨ V1.0
 * ģ������: �����̷���Ŀ��������
 * �����Ȩ: ��ƮƮ�ɷ����޹�˾
 * ����˵��: 
 * ϵͳ�汾: 1.0.0.1
 * ������Ա: xly
 * ����ʱ��: 2013-12-21
 * �����Ա:
 * ����ĵ�:
 * �޸ļ�¼: �޸�����    �޸���Ա    �޸�˵��
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
			result.setCode("���ݱ������ݿ�ɹ�");
		} else {
			result.setResult(false);
			result.setCode("���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա");
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
