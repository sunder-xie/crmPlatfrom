package com.kintiger.platform.kunnr.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;

public interface IKunnrAddressDao {

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrAddressSearchCount(KunnrAddress kunnrAddress);

	/**
	 * 
	 * 经销商列表查询
	 * 
	 * @return
	 */
	public List<KunnrAddress> kunnrAddressSearch(KunnrAddress kunnrAddress);

	public Long createKunnrAddress(KunnrAddress kunnrAddress);

	public Long deleteKunnrAddress(KunnrAddress kunnrAddress);

	public int updateKunnrAddress(KunnrAddress kunnrAddress);

	public KunnrAddress getKunnrAddressById(String kunnrSd);

	/**
	 * 取最大编号送达方
	 * 
	 * @param kunnrId
	 * @return
	 */
	public String getMaxKunnrSd(String kunnrId);

}
