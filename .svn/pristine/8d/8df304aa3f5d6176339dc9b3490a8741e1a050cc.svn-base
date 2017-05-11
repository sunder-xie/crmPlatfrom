package com.kintiger.platform.stock.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.stock.dao.IInStockDao;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;


public class InStockDaoImpl extends BaseDaoImpl implements IInStockDao{

	public int getInStockCount(Instock instock) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"instock.getInStockCount", instock);
	}	
	
	public int getInStockOnly(Instock instock) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"instock.getInStockOnly", instock);
	}	
	public int getInStockDetailListByTotalIdCount(Instock_det instockdet){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"instock.getInStockDetailListByTotalIdCount", instockdet);
	}
	@SuppressWarnings("unchecked")
	public List<Instock>getInStockList(Instock instock){
		return (List<Instock>) getSqlMapClientTemplate().queryForList(
				"instock.getInStockList", instock);
	}
	@SuppressWarnings("unchecked")
	public List<Stock>getStockList(Stock stock){
		return (List<Stock>) getSqlMapClientTemplate().queryForList(
				"instock.getStockList", stock);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Instock_det>getInStockDetailListByTotalId(Instock_det instockdet){
		return (List<Instock_det>) getSqlMapClientTemplate().queryForList(
				"instock.getInStockDetailListByTotalId", instockdet);
	}
	
	
	public String insertInstock(Instock instock){
		return (String) getSqlMapClientTemplate().insert(
				"instock.insertInstock", instock);
	}
	
	public String insetrInstockdet(Instock_det instockdet){
		return (String) getSqlMapClientTemplate().insert(
				"instock.insetrInstockdet", instockdet);
	}
	
	public String  insertStock(Stock  stock){
	return (String) getSqlMapClientTemplate().insert(
				"instock.insertStock", stock);
	}
	
	public  Instock getStockById(String  instock_total_id,String type){
		Instock instock =new Instock();
		instock.setInstock_total_id(instock_total_id);
		instock.setInstock_type(type);
		return (Instock) getSqlMapClientTemplate().queryForObject(
				"instock.getStockById", instock);
	}
	
	
	public int updateStock(Instock instock){
		return  getSqlMapClientTemplate().update(
				"instock.updateStock", instock);
	}
	
	 public int updateStockTotal(Stock stock){
		 return  getSqlMapClientTemplate().update(
					"instock.updateStockTotal", stock);
	 }
	
	
	
}
