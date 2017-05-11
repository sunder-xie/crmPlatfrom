package com.kintiger.platform.citydict.dao;

import java.util.List;

import com.kintiger.platform.citydict.pojo.CityDict;

public interface ICityDictDao {
	/**
	 * 插入行政区划
	 * 
	 * @param detail
	 * @return
	 */
	public Long insertCityDict(CityDict cityDict);

	/**
	 * 查询行政区划
	 * 
	 * @param detail
	 * @return
	 */
	public List<CityDict> getCityDictList(CityDict cityDict);
	public List<CityDict> getCityList(CityDict cityDict);
	public int getCityDictCount(CityDict cityDict);
	public int deleteCityDict(CityDict cityDict);
	public int updateCityDictDtype(CityDict cityDict);
	public int updateCityDict(CityDict cityDict);
	public List<CityDict> getCityListExcel(CityDict cityDict);
	public List<CityDict> getCity4ListExcel();

	

}
