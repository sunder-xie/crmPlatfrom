package com.kintiger.platform.pos.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.distributionData.service.IDistributionDataService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.pos.dao.IPosDao;
import com.kintiger.platform.pos.pojo.Pos;
import com.kintiger.platform.pos.service.IPosService;

public class PosServiceImpl implements IPosService{

	
	private IPosDao posDao;
	private static final Logger logger = Logger
			.getLogger(PosServiceImpl.class);


	@Override
	public int getPosSize(Pos pGoal) {
		try {
			return posDao
					.getPosSize(pGoal);
		} catch (Exception e) {
			logger.error(pGoal, e);
		}
		return 0;
	}



	public IPosDao getPosDao() {
		return posDao;
	}
	public void setPosDao(IPosDao posDao) {
		this.posDao = posDao;
	}

	@Override
	public Pos getOrgByOrgName(String org_city) {
		try{
			return posDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}

	@Override
	public BooleanResult insertPosData(Pos pos) {
		BooleanResult result = new BooleanResult();

		long distributionDataId = posDao
				.insertPosData(pos);
		if (distributionDataId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}



	@Override
	public Pos getSystemBySystemName(String systemName) {
		try{
			return posDao.getSystemBySystemName(systemName);
		}catch(Exception e){
			logger.error(systemName, e);
		}
		return null;
	}



	@Override
	public int getPosCount(Pos dGoal) {
		try {
			return posDao
					.getPosCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}



	@Override
	public List<Pos> getPosList(Pos dGoal) {
		List<Pos> list = null;
		try {
			list = posDao
					.getPosList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;
	}



	@Override
	public StringResult deletePos(Pos dGoal) {
		StringResult result = new StringResult();
		result.setCode(IPosService.ERROR);
		result.setResult(IPosService.ERROR_MESSAGE);
		try {
			int c = posDao.deletePos(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IPosService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}



	@Override
	public BooleanResult updatePos(Pos pos) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = posDao.updatePos(pos);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(pos), e);
		}

		return booleanResult;
	}
}
