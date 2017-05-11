package com.kintiger.platform.citydict.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.citydict.dao.ICityDictDao;
import com.kintiger.platform.citydict.pojo.CityDict;

public class CityDictDaoImpl  extends BaseDaoImpl implements ICityDictDao{

	public Long insertCityDict(CityDict cityDict) {
		return (Long) getSqlMapClientTemplate().insert("city.insertCityDict", cityDict);

	}
	
	@SuppressWarnings("unchecked")
	public List<CityDict> getCityDictList(CityDict cityDict) {
		return getSqlMapClientTemplate().queryForList("city.getCityDictList",
				cityDict);
	}

	public int getCityDictCount(CityDict cityDict) {
		return (Integer)getSqlMapClientTemplate().queryForObject("city.getCityDictCount", cityDict);

	}
	@SuppressWarnings("unchecked")
	public List<CityDict> getCityList(CityDict cityDict) {
		return getSqlMapClientTemplate().queryForList("city.getCityList",
				cityDict);
	}

	public int deleteCityDict(CityDict cityDict) {
		return getSqlMapClientTemplate().update("city.deleteCityDict", cityDict);

	}

	public int updateCityDict(CityDict cityDict) {
		return getSqlMapClientTemplate().update("city.updateCityDict", cityDict);

	}

	public int updateCityDictDtype(CityDict cityDict) {
		return getSqlMapClientTemplate().update("city.updateCityDictDtype", cityDict);
	}
	@SuppressWarnings("unchecked")
	public List<CityDict> getCityListExcel(CityDict cityDict) {
		return getSqlMapClientTemplate().queryForList("city.getCityListExcel",
				cityDict);
	}
	@SuppressWarnings("unchecked")
	public List<CityDict> getCity4ListExcel() {
		return getSqlMapClientTemplate().queryForList("city.getCity4ListExcel"
				);
	}

}
