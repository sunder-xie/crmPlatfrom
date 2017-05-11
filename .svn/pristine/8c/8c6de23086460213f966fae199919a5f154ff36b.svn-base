package com.kintiger.platform.cuanhuoQuery.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.cuanhuoQuery.dao.ICuanhuoQueryDao;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoQuery;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoSKU;

public class CuanhuoQueryDaoImpl extends BaseDaoImpl implements ICuanhuoQueryDao {

	public List<CuanhuoQuery> getCuanhuoSKUs(CuanhuoQuery cuanhuoQuery) {
		return (List<CuanhuoQuery>) getSqlMapClientTemplate().queryForList(
				"cuanhuoQuery.getCuanhuoSKUs",cuanhuoQuery);
	}

	public int getCuanhuoSKUCount(CuanhuoQuery cuanhuoQuery) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"cuanhuoQuery.getCuanhuoSKUCount",cuanhuoQuery);
	}

}
