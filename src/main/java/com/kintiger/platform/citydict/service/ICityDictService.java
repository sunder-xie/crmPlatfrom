package com.kintiger.platform.citydict.service;

import java.util.List;

import com.kintiger.platform.citydict.pojo.CityDict;

public interface ICityDictService {

	public List<CityDict> getCityDictList(CityDict cityDict);
	/**
	 * ���������Ϣ
	 * @param cityDict
	 * @return
	 */
	public Long insertCityDict(CityDict cityDict);
	public int getCityDictCount(CityDict cityDict);
	/***
	 * 
	 * ��ѯ������Ϣ
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityList(CityDict cityDict);
	
	/***
	 * 
	 * ��ѯ������Ϣ
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityListExcel(CityDict cityDict);
	public List<CityDict> getCity4ListExcel();

	/**
	 * 
	 * ɾ������
	 * @param cityDict
	 * @return
	 */
	public int deleteCityDict(CityDict cityDict) ;
	public int updateCityDict(CityDict cityDict) ;
	/**
	 * 
	 * �ı������Ƿ����״̬
	 * @param cityDict
	 * @return
	 */
	public int updateCityDictDtype(CityDict cityDict);

}
