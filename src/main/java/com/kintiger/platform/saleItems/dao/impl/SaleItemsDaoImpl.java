package com.kintiger.platform.saleItems.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.crmdict.pojo.CrmDictType;
import com.kintiger.platform.saleItems.dao.ISaleItemsDao;
import com.kintiger.platform.saleItems.pojo.SaleItems;
import com.kintiger.platform.saleItems.pojo.Sku;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

@SuppressWarnings("rawtypes")
public class SaleItemsDaoImpl  extends BaseDaoImpl implements ISaleItemsDao {

	public String getChannelName(int channelId) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getChannelName",
				channelId);
	}
	
	public Kunnr getKunnrByKunnrId(Kunnr k) {
		return (Kunnr) getSqlMapClientTemplate().queryForObject("customer.getKunnrByKunnrId",k);
	}	

	public Borg gerOrgIsExit(Borg org) {
		return (Borg) getSqlMapClientTemplate().queryForObject("customer.gerOrgCountIsExit",org);
	}

	@Override
	public long createSaleItems(SaleItems saleItems) {
		return (Long) getSqlMapClientTemplate().insert("saleItems.createSaleItems",
				saleItems);
	}

	@Override
	public int getSaleItemsCount(SaleItems saleItems) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("saleItems.getSaleItemsCount", saleItems);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleItems> getSaleItemsList(SaleItems saleItems) {
		// TODO Auto-generated method stub
		return (List<SaleItems>) getSqlMapClientTemplate().queryForList("saleItems.getSaleItemsList", saleItems);
	}

	@Override
	public int getSkuCount(Sku sku) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("saleItems.getSkuCount",sku);
	}

	@Override
	public Sku getSkuNameById(Sku sku) {
		// TODO Auto-generated method stub
		return (Sku) getSqlMapClientTemplate().queryForObject("saleItems.getSkuNameById", sku);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		// TODO Auto-generated method stub
		return (List<Sku>) getSqlMapClientTemplate().queryForList("saleItems.getSkuNameList", sku);
	}

	@Override
	public int updateSaleItems(SaleItems saleItems) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("saleItems.updateSaleItems", saleItems);
	}

	
	@Override
	public SaleItems getSaleItems(SaleItems saleItems) {
		// TODO Auto-generated method stub
		return (SaleItems) getSqlMapClientTemplate().queryForObject(
				"saleItems.getSaleItems", saleItems);
	}

	@Override
	public int delSaleItems(SaleItems saleItems) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("saleItems.delSaleItems", saleItems);
	}
	

}
