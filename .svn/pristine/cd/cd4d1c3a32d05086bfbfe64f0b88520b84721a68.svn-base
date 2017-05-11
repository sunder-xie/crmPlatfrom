package com.kintiger.platform.crmdict.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.crmdict.dao.ICrmDictDao;
import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.crmdict.pojo.CrmDictType;



public class CrmDictDaoImpl extends BaseDaoImpl implements ICrmDictDao {

	public int getCrmDictCount(CrmDict crmdict) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"crmdict.getCrmDictCount", crmdict);
	}

	@SuppressWarnings("unchecked")
	public List<CrmDict> getCrmDictList(CrmDict crmdict) {
		return (List<CrmDict>) getSqlMapClientTemplate().queryForList(
				"crmdict.getCrmDictList", crmdict);
	}

	public int getDictCount(CrmDict crmdict) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"crmdict.getDictCount", crmdict);
	}

	@SuppressWarnings("unchecked")
	public List<CrmDict> getDictList(CrmDict crmdict) {
		return (List<CrmDict>) getSqlMapClientTemplate().queryForList(
				"crmdict.getDictList", crmdict);
	}

	public int getCrmDictTypeCount(CrmDictType crmdictType) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"crmdict.getCrmDictTypeCount", crmdictType);
	}

	@SuppressWarnings("unchecked")
	public List<CrmDictType> getCrmDictTypeList(CrmDictType crmdictType) {
		return (List<CrmDictType>) getSqlMapClientTemplate().queryForList(
				"crmdict.getCrmDictTypeList", crmdictType);
	}

	public Long CreateDict(CrmDict crmdict) {
		return (Long) getSqlMapClientTemplate().insert("crmdict.createDict",
				crmdict);
	}

	public Long CreateDictType(CrmDictType crmdictType) {
		return (Long) getSqlMapClientTemplate().insert("crmdict.createDictType",
				crmdictType);
	}

	public int updateDict(CrmDict crmdict) {
		return getSqlMapClientTemplate().update("crmdict.updateDict", crmdict);
	}

	public int updateDictType(CrmDictType crmdictType) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("crmdict.updateDictType",
				crmdictType);
	}

	public CrmDict getCrmDict(CrmDict crmdict) {
		// TODO Auto-generated method stub
		return (CrmDict) getSqlMapClientTemplate().queryForObject(
				"crmdict.getCrmDict", crmdict);
	}

	public CrmDictType getCrmDictType(CrmDictType crmdictType) {
		// TODO Auto-generated method stub
		return (CrmDictType) getSqlMapClientTemplate().queryForObject(
				"crmdict.getCrmDictType", crmdictType);
	} 

	@SuppressWarnings("unchecked")
	public List<CrmDict> getCrmDictByType(CrmDict crmdict) {
		return (List<CrmDict>) getSqlMapClientTemplate().queryForList(
				"crmdict.getCrmDictListByType", crmdict);
	}
	@SuppressWarnings("unchecked")
	public List<CrmDict> getByCrmDictList(CrmDict crmdict) {
		return (List<CrmDict>) getSqlMapClientTemplate().queryForList(
				"crmdict.getByCrmDictList", crmdict);
	}
	@SuppressWarnings("unchecked")
	public List<CrmDictType> getCrmDictTypeListJson() {
		return (List<CrmDictType>) getSqlMapClientTemplate().queryForList(
				"crmdict.getCrmDictTypeListjosn");
	}

}
