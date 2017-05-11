package com.kintiger.platform.citydict.service.impl;

import java.util.List;

import com.kintiger.platform.citydict.dao.ICityDictDao;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.citydict.service.ICityDictService;

public class CityDictServiceImpl implements ICityDictService {

	private ICityDictDao cityDictDao;

	public Long insertCityDict(CityDict cityDict) {
		try {
			return cityDictDao.insertCityDict(cityDict);
		} catch (Exception e) {
			return 0l;
		}
	}

	public List<CityDict> getCityDictList(CityDict cityDict) {
		try {
			return cityDictDao.getCityDictList(cityDict);
		} catch (Exception e) {
			return null;
		}
	}

	public int getCityDictCount(CityDict cityDict) {
		try {
			return cityDictDao.getCityDictCount(cityDict);
		} catch (Exception e) {
			return 0;
		}
	}
	public List<CityDict> getCityList(CityDict cityDict) {
		try {
			return cityDictDao.getCityList(cityDict);
		} catch (Exception e) {
			return null;
		}
	}
	public List<CityDict> getCityListExcel(CityDict cityDict) {
		try {
			return cityDictDao.getCityListExcel(cityDict);
		} catch (Exception e) {
			return null;
		}
	}
	public List<CityDict> getCity4ListExcel() {
		try {
			return cityDictDao.getCity4ListExcel();
		} catch (Exception e) {
			return null;
		}
	}
	public int deleteCityDict(CityDict cityDict) {
		try {
			return cityDictDao.deleteCityDict(cityDict);
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateCityDict(CityDict cityDict) {
		try {
			return cityDictDao.updateCityDict(cityDict);
		} catch (Exception e) {
			return 0;
		}
	}
	public int updateCityDictDtype(CityDict cityDict) {
		try {
			return cityDictDao.updateCityDictDtype(cityDict);
		} catch (Exception e) {
			return 0;
		}
	}

	public ICityDictDao getCityDictDao() {
		return cityDictDao;
	}

	public void setCityDictDao(ICityDictDao cityDictDao) {
		this.cityDictDao = cityDictDao;
	}



	
	
}
