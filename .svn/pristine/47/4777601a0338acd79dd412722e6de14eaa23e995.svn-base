package com.kintiger.platform.kunnr.service;

import java.io.File;
import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;

/**
 * 经销商
 * 
 * @author xxping
 * 
 */
public interface IKunnrAddressService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrAddressSearchCount(KunnrAddress kunnrAddress);

	/**
	 * 
	 * 送达方列表查询
	 * 
	 * @return
	 */
	public List<KunnrAddress> kunnrAddressSearch(KunnrAddress kunnrAddress);

	public BooleanResult createKunnrAddress(KunnrAddress kunnrAddress);

	public StringResult deleteKunnrAddress(KunnrAddress kunnrAddress);

	public BooleanResult updateKunnrAddress(KunnrAddress kunnrAddress);

	public KunnrAddress getKunnrAddressById(String kunnrSd);

	/**
	 * 取最大编号送达方
	 * 
	 * @param kunnrId
	 * @return
	 */
	public String getMaxKunnrSd(String kunnrId);

}
