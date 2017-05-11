package com.kintiger.platform.citydict.service;

import java.util.List;

import com.kintiger.platform.citydict.pojo.CityDict;

public interface ICityDictService {

	public List<CityDict> getCityDictList(CityDict cityDict);
	/**
	 * 添加区划信息
	 * @param cityDict
	 * @return
	 */
	public Long insertCityDict(CityDict cityDict);
	public int getCityDictCount(CityDict cityDict);
	/***
	 * 
	 * 查询区划信息
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityList(CityDict cityDict);
	
	/***
	 * 
	 * 查询区划信息
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityListExcel(CityDict cityDict);
	public List<CityDict> getCity4ListExcel();

	/**
	 * 
	 * 删除区划
	 * @param cityDict
	 * @return
	 */
	public int deleteCityDict(CityDict cityDict) ;
	public int updateCityDict(CityDict cityDict) ;
	/**
	 * 
	 * 改变区划是否分配状态
	 * @param cityDict
	 * @return
	 */
	public int updateCityDictDtype(CityDict cityDict);

}
