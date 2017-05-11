package com.kintiger.platform.master.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.dao.MasterDao;
import com.kintiger.platform.master.pojo.CrmTbCg;
import com.kintiger.platform.master.pojo.CrmTbSku;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.master.pojo.SupervisorLineCheckItem;

@SuppressWarnings("rawtypes")
public class MasterDaoImpl  extends BaseDaoImpl implements MasterDao{
	
	
	/**
	 * 删除物料表
	 * @return
	 */
	public void deleteMateriel(){
		this.getSqlMapClientTemplate().delete("master.deleteMateriel");
	}
	/**
	 * 创建物料
	 * @param materiel
	 */
	public void createMateriel(Materiel materiel){
		this.getSqlMapClientTemplate().insert("master.createMateriel",materiel);
	}
	/**
	 * 获取物料列表总数
	 * @param materiel
	 * @return 列表总数
	 */
	public int getMaterielCount(Materiel materiel){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("master.getMaterielCount", materiel);
	}
	/**
	 * 获取物料列表
	 * @param materiel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Materiel> getMaterielList(Materiel materiel){
		return  this.getSqlMapClientTemplate().queryForList("master.getMaterielList", materiel);
	}
	@SuppressWarnings("unchecked")
	public List<CrmTbCg> getSkuCategoryIdByName(CrmTbCg cg) {
		try {
			return (List<CrmTbCg>)getSqlMapClientTemplate().queryForList("master.getSkuCategoryIdByName", cg);
		} catch (DataAccessException e) {
			logger.error(e);
			return null;
		}
	}
	public Long createCrmTbCg(CrmTbCg cg) {
		return (Long)getSqlMapClientTemplate().insert("master.createCrmTbCg",cg);
		
	}
	@SuppressWarnings("unchecked")
	public List<CrmTbSku> getSkuIdByNameAndCompany(CrmTbSku sku) {
		try {
			return (List<CrmTbSku>)getSqlMapClientTemplate().queryForList("master.getSkuIdByNameAndCompany", sku);
		} catch (DataAccessException e) {
			logger.error(e);
			return null;
		}
		
	}
	public Long createCrmTbSku(CrmTbSku sku) {
		return (Long)getSqlMapClientTemplate().insert("master.createCrmTbSku",sku);
		
	}
	public void updateCrmTbcg(CrmTbCg cg) {
		getSqlMapClientTemplate().update("master.updateCrmTbcg",cg);
	}
	public void updateCrmTbSku(CrmTbSku sku) {
		getSqlMapClientTemplate().update("master.updateCrmTbSku",sku);
		
	}
	public Customer validateChanelAndCust(String custName,
			String channelName) {
		try {
			
			Customer cust = new Customer();
			cust.setCustName(custName);
			cust.setChannelName(channelName);
		return (Customer) getSqlMapClientTemplate().queryForObject("master.validateChanelAndCust", cust);
					
		
			
		} catch (DataAccessException e) {
			logger.error(e);
			return null;
		}
	}
	
	public Long saveSupervisorCheckItem(SupervisorCheckItem supervisorCheckItem) {
		return (Long)getSqlMapClientTemplate().insert("master.saveSupervisorCheckItem",supervisorCheckItem);
	}
	public int getSupervisorItemsCount(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		return (Integer)getSqlMapClientTemplate().queryForObject("master.getSupervisorItemsCount", supervisorLineCheckItem);
		
	}
	public List<SupervisorCheckItem> getSupervisorItems(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		try {
			return (List<SupervisorCheckItem>)getSqlMapClientTemplate().queryForList("master.getSupervisorItems", supervisorLineCheckItem);
		} catch (DataAccessException e) {
			logger.error(e);
			return null;
		}
	}
	public void clearItems() {
		getSqlMapClientTemplate().delete("master.clearItems");
		
	}
	public void saveChagCheckItem(SupervisorCheckItem supervisorCheckItem) {
		getSqlMapClientTemplate().update("master.saveChagCheckItem",supervisorCheckItem);
	}

	public int kunnrSearchCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"master.kunnrSearchCount", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		return ((List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"master.kunnrSearchFromMaster", kunnr));
	}
	public Customer validateCustId(String custId) {
		return ((Customer) getSqlMapClientTemplate().queryForObject("master.validateCustId", custId));
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getExportMouldCsvCust(Customer c) {
		return ((List<Customer>) getSqlMapClientTemplate().queryForList(
				"master.getExportMouldCsvCust", c));
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getExportMouldCsvCustWithCons(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		return ((List<Customer>) getSqlMapClientTemplate().queryForList(
				"master.getExportMouldCsvCustWithCons", supervisorLineCheckItem));
	
	}
	@SuppressWarnings("unchecked")
	public List<Materiel> getMasterCols(Materiel materiel) {
		return ((List<Materiel>) getSqlMapClientTemplate().queryForList(
				"master.getMasterCols", materiel));
	}
	public int getSupervisorItemsColsCount(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"master.getSupervisorItemsColsCount", supervisorLineCheckItem);
	}
	public List<Customer> getCustomerListCols(SupervisorLineCheckItem supervisorLineCheckItem) {
		return ((List<Customer>) getSqlMapClientTemplate().queryForList(
				"master.getCustomerListCols", supervisorLineCheckItem));
	}
	public int validateCustIdExist(String custId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"master.validateCustIdExist", custId);
	}
	public void delSupervisorCheckItemByCustId(String custId) {
		getSqlMapClientTemplate().delete("master.delSupervisorCheckItemByCustId",custId);
	}
	public List<SupervisorCheckItem> getSupervisorItemsByCustId(String custId) {
		return ((List<SupervisorCheckItem>) getSqlMapClientTemplate().queryForList(
				"master.getSupervisorItemsByCustId", custId));
	}
	
	
}
