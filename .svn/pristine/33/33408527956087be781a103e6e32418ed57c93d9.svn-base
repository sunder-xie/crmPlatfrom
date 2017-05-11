package com.kintiger.platform.saleItems.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.saleItems.pojo.SaleItems;
import com.kintiger.platform.saleItems.pojo.Sku;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

public interface ISaleItemsService {
	
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	public static final String ERROR_MESSAGE = "操作失败";
	/**
	 * 根据渠道id获取渠道信息
	 * 
	 * @param channelId
	 * @return
	 */
	public String getChannelName(int channelId);

	/**
	 * 获取经销商信息
	 * 
	 * @param k
	 * @return
	 */
	public Kunnr getKunnrByKunnrId(Kunnr k);


	/**
	 * 判断组织是否存在
	 * @return
	 */
	public Borg gerOrgIsExit(Borg org);

	public BooleanResult createSaleItems(SaleItems saleItems);

	public int getSaleItemsCount(SaleItems saleItems);

	public List<SaleItems> getSaleItemsList(SaleItems saleItems);

	public int getSkuCount(Sku sku);

	public Sku getSkuNameById(Sku sku);
	
	public List<Sku> getSkuNameList(Sku sku);

	public BooleanResult updateSaleItems(SaleItems SaleItems);

	public SaleItems getSaleItems(SaleItems searhSaleItems);
	
	public StringResult delSaleItems(SaleItems saleItems);

}
