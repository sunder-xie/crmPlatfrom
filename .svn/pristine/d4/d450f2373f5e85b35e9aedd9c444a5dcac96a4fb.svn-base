package com.kintiger.platform.crmdict.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.crmdict.dao.ICrmDictDao;
import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.crmdict.pojo.CrmDictType;
import com.kintiger.platform.crmdict.service.ICrmDictService;
import com.kintiger.platform.framework.util.LogUtil;

public class CrmDictServiceImpl implements ICrmDictService {

	private static final Log logger = LogFactory
			.getLog(CrmDictServiceImpl.class);

	private ICrmDictDao crmdictDao;

	public int getCrmDictCount(CrmDict crmdict) {
		try {
			return crmdictDao.getCrmDictCount(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return 0;
	}

	public List<CrmDict> getCrmDictList(CrmDict crmdict) {
		try {
			return crmdictDao.getCrmDictList(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return null;
	}

	public int getDictCount(CrmDict crmdict) {
		try {
			return crmdictDao.getDictCount(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return 0;
	}

	public List<CrmDict> getDictList(CrmDict crmdict) {
		try {
			return crmdictDao.getDictList(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return null;
	}

	public int getCrmDictTypeCount(CrmDictType crmdictType) {
		try {
			return crmdictDao.getCrmDictTypeCount(crmdictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdictType), e);
		}

		return 0;
	}

	public List<CrmDictType> getCrmDictTypeList(CrmDictType crmdictType) {
		try {
			return crmdictDao.getCrmDictTypeList(crmdictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdictType), e);
		}

		return null;
	}

	public BooleanResult createDict(CrmDict crmdict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long itemId = crmdictDao.CreateDict(crmdict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(itemId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return booleanResult;
	}

	public BooleanResult createDictType(CrmDictType crmdictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long dictTypeId = crmdictDao.CreateDictType(crmdictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(dictTypeId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(crmdictType), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDict(CrmDict crmdict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = crmdictDao.updateDict(crmdict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDictType(CrmDictType crmdictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = crmdictDao.updateDictType(crmdictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(crmdictType), e);
		}

		return booleanResult;
	}

	public CrmDict getCrmDict(CrmDict crmdict) {
		try {
			return crmdictDao.getCrmDict(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return null;
	}

	public List<CrmDictType> getCrmDictTypeListJson() {
		try {
			return crmdictDao.getCrmDictTypeListJson();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(""), e);
		}

		return null;
	}

	public CrmDictType getCrmDictType(CrmDictType crmdictType) {
		try {
			return crmdictDao.getCrmDictType(crmdictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdictType), e);
		}

		return null;
	}

	public List<CrmDict> getCrmDictByType(CrmDict crmdict) {
		try {
			return crmdictDao.getCrmDictByType(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return null;
	}

	public List<CrmDict> getByCrmDictList(CrmDict crmdict) {
		try {
			return crmdictDao.getByCrmDictList(crmdict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(crmdict), e);
		}

		return null;
	}

	public ICrmDictDao getCrmdictDao() {
		return crmdictDao;
	}

	public void setCrmdictDao(ICrmDictDao crmdictDao) {
		this.crmdictDao = crmdictDao;
	}

}
