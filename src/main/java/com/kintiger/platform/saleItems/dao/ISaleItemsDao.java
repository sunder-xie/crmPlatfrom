package com.kintiger.platform.saleItems.dao;

import java.util.List;

import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;
import com.kintiger.platform.saleItems.pojo.SaleItems;
import com.kintiger.platform.saleItems.pojo.Sku;

public interface ISaleItemsDao {

	/**
	 * ��������id��ȡ������Ϣ
	 * 
	 * @param channelId
	 * @return
	 */
	public String getChannelName(int channelId);
		
	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @param k
	 * @return
	 */
	public Kunnr getKunnrByKunnrId(Kunnr k);
	
	/**
	 * �ж���֯�Ƿ����
	 * @return
	 */
	public Borg gerOrgIsExit(Borg org);

	public long createSaleItems(SaleItems saleItems);

	public int getSaleItemsCount(SaleItems saleItems);

	public List<SaleItems> getSaleItemsList(SaleItems saleItems);

	public int getSkuCount(Sku sku);

	public Sku getSkuNameById(Sku sku);

	public List<Sku> getSkuNameList(Sku sku);

	public int updateSaleItems(SaleItems saleItems);

	public SaleItems getSaleItems(SaleItems saleItems);
	
	public int delSaleItems(SaleItems saleItems);
	
}
