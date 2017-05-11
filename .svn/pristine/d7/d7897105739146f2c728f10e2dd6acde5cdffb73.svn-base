package com.kintiger.platform.crmdict.dao;

import java.util.List;

import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.crmdict.pojo.CrmDictType;



public interface ICrmDictDao {

	/**
	 * 
	 * @param crmdict
	 * @return
	 */
	public int getCrmDictCount(CrmDict crmdict);

	/**
	 * 
	 * @param crmdict
	 * @return
	 */
	public List<CrmDict> getCrmDictList(CrmDict crmdict);

	/**
	 * 关联 dict type 查询 dict
	 * 
	 * @param crmdict
	 * @return
	 */
	public int getDictCount(CrmDict crmdict);

	/**
	 * 关联 dict type 查询 dict
	 * 
	 * @param crmdict
	 * @return
	 */
	public List<CrmDict> getDictList(CrmDict crmdict);

	/**
	 * 
	 * @param crmdictType
	 * @return
	 */
	public int getCrmDictTypeCount(CrmDictType crmdictType);

	/**
	 * 
	 * @param crmdictType
	 * @return
	 */
	public List<CrmDictType> getCrmDictTypeList(CrmDictType crmdictType);
	public List<CrmDictType> getCrmDictTypeListJson();


	/**
	 * 
	 * @param crmdictType
	 * @return
	 */
	public Long CreateDictType(CrmDictType crmdictType);

	/**
	 * 
	 * @param crmdict
	 * @return
	 */
	public Long CreateDict(CrmDict crmdict);

	/**
	 * 
	 * @param crmdict
	 * @return
	 */
	public int updateDict(CrmDict crmdict);

	/**
	 * 
	 * @param crmdictType
	 * @return
	 */
	public int updateDictType(CrmDictType crmdictType);

	public CrmDictType getCrmDictType(CrmDictType crmdictType);

	public CrmDict getCrmDict(CrmDict crmdict);

	public List<CrmDict> getCrmDictByType(CrmDict crmdict);
	public List<CrmDict> getByCrmDictList(CrmDict crmdict);
	


}
