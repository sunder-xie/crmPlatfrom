package com.kintiger.platform.cuanhuoQuery.dao;

import java.util.List;

import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoQuery;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoSKU;

public interface ICuanhuoQueryDao {
	public List<CuanhuoQuery> getCuanhuoSKUs(CuanhuoQuery cuanhuoQuery);
	public int getCuanhuoSKUCount(CuanhuoQuery cuanhuoQuery);
}
