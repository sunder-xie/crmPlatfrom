package com.kintiger.platform.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.dao.IOrderNewDao;
import com.kintiger.platform.order.pojo.Account;
import com.kintiger.platform.order.pojo.PrintFormat;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;

@SuppressWarnings("rawtypes")
public class OrderNewDaoImpl extends BaseDaoImpl implements IOrderNewDao {


	public int searchOrderCount(XppOrder order) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.searchOrderNewCount", order);
	}
	
	public int searchOrderDetailCount(OrderDetail orderDetail) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.searchOrderNewDetailCount", orderDetail);
	}

	@SuppressWarnings("unchecked")
	public List<XppOrder> searchOrderList(XppOrder order) {
		return this.getSqlMapClientTemplate().queryForList("order.searchOrderNewList", order);
	}
	@SuppressWarnings("unchecked")
	public List<XppOrder> orderExport(XppOrder order) {
		return this.getSqlMapClientTemplate().queryForList("order.orderNewExport", order);
	}
	
	public XppOrder searchOrder(XppOrder order) {
		return (XppOrder)this.getSqlMapClientTemplate().queryForObject("order.searchOrderNew", order);
	}
	
	public int updateOrder(XppOrder order) {
		return this.getSqlMapClientTemplate().update("order.updateOrderNew",order);
	}
	
	public int updateOrderDetail(OrderDetail orderDetail) {
		return this.getSqlMapClientTemplate().update("order.updateOrderNewDetail",orderDetail);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail){
		return this.getSqlMapClientTemplate().queryForList("order.searchOrderNewDetailList", orderDetail);
	}
	
	@Override
	public int getSkuCount(Sku sku) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("order.getSkuCount",sku);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		// TODO Auto-generated method stub
		return (List<Sku>) getSqlMapClientTemplate().queryForList("order.getSkuNameList", sku);
	}
	
    public long getOrderId(){
    	return (Long) getSqlMapClientTemplate().queryForObject("order.getOrderNewId");
    }
	
	public long createOrder(XppOrder order){
		getSqlMapClientTemplate().insert("order.createOrderNew",order);
		return 1;
	}
	
	public long createOrderDetail(OrderDetail orderDetail){
		return (Long)getSqlMapClientTemplate().insert("order.createOrderNewDetail",orderDetail);
	}
	
	public XppOrder getOrder(XppOrder order) {
		return (XppOrder)this.getSqlMapClientTemplate().queryForObject("order.getOrderNew", order);
	}

	@Override
	public String getKunnrEmpLifnrByEmpCode(String empCode) {
		return (String)this.getSqlMapClientTemplate().queryForObject("order.getKunnrEmpLifnrByEmpCode", empCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getKunnrByEmpId(Long userId){
		return (List<String>)this.getSqlMapClientTemplate().queryForList("order.getKunnrByEmpId", userId);
	}
	
    public int getOrderInfoListCount(OrderInfo orderInfo){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("order.getOrderNewDetailAllListCount", orderInfo);
    }
	
	@SuppressWarnings("unchecked")
	public List<OrderInfo> getOrderInfoList(OrderInfo orderInfo){
		return (List<OrderInfo>)this.getSqlMapClientTemplate().queryForList("order.getOrderNewDetailAllList", orderInfo);
	}

	public int getKunnrListCount(String kunnr,String kunnrName,String orgId){
		Map<String,String> map=new HashMap<String,String>();
		map.put("kunnr", kunnr);
		map.put("kunnrName", kunnrName);
		map.put("orgId", orgId);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("order.kunnrSearchCount", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Kunnr> getKunnrList(String kunnr,String kunnrName,String orgId){
		Map<String,String> map=new HashMap<String,String>();
		map.put("kunnr", kunnr);
		map.put("kunnrName", kunnrName);
		map.put("orgId", orgId);
		return (List<Kunnr>)this.getSqlMapClientTemplate().queryForList("order.kunnrSearch", map);
	}
	
	public Long getMaxId(){
		return (Long)this.getSqlMapClientTemplate().queryForObject("order.getMaxId");
	}
	
    public int searchAccountCount(Account account){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("order.searchAccountCount", account);
    }
	
	@SuppressWarnings("unchecked")
	public List<Account> searchAccountList(Account account){
		return (List<Account>)this.getSqlMapClientTemplate().queryForList("order.searchAccountList", account);
	}
	
	public Account searchAccount(Account account){
		return (Account)this.getSqlMapClientTemplate().queryForObject("order.searchAccount", account);
	}
	

	public int createAccount(Account account){
		this.getSqlMapClientTemplate().insert("order.createAccount", account);
		return 1;
	}
	
	public int updateAccount(Account account){
		return (Integer)this.getSqlMapClientTemplate().update("order.updateAccount", account);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrintFormat> getPrintFormatList(PrintFormat printFormat) {
		return this.getSqlMapClientTemplate().queryForList("order.getPrintFormatList", printFormat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrintFormat> getPropertiesJSON() {
		return this.getSqlMapClientTemplate().queryForList("order.getPropertiesJSON");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		return this.getSqlMapClientTemplate().queryForList("order.kunnrSearch", kunnr);
	}

	@Override
	public int getPrintFormatListCount(PrintFormat printFormat) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.getPrintFormatListCount", printFormat);
	}

	@Override
	public Integer modifyFormat(PrintFormat printFormat) {
		return (Integer)this.getSqlMapClientTemplate().update("order.modifyFormat", printFormat);
	}

	@Override
	public Integer createFormat(PrintFormat printFormat) {
		return (Integer)this.getSqlMapClientTemplate().insert("order.createFormat",printFormat);
	}
	
	public int getUserStationBySjjlCount(String userId){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("order.getUserStationBySjjlCount",userId);
	}

}
