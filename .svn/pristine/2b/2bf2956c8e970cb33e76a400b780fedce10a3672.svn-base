package com.kintiger.platform.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.dao.IOrderDao;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;

@SuppressWarnings("rawtypes")
public class OrderDaoImpl extends BaseDaoImpl implements IOrderDao {


	public int searchOrderCount(XppOrder order) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.searchOrderCount", order);
	}
	
	public int searchOrderDetailCount(OrderDetail orderDetail) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.searchOrderDetailCount", orderDetail);
	}

	@SuppressWarnings("unchecked")
	public List<XppOrder> searchOrderList(XppOrder order) {
		return this.getSqlMapClientTemplate().queryForList("order.searchOrderList", order);
	}
	@SuppressWarnings("unchecked")
	public List<XppOrder> orderExport(XppOrder order) {
		return this.getSqlMapClientTemplate().queryForList("order.orderExport", order);
	}
	
	public XppOrder searchOrder(XppOrder order) {
		return (XppOrder)this.getSqlMapClientTemplate().queryForObject("order.searchOrder", order);
	}
	
	public int updateOrder(XppOrder order) {
		return this.getSqlMapClientTemplate().update("order.updateOrder",order);
	}
	
	public int updateOrderDetail(OrderDetail orderDetail) {
		return this.getSqlMapClientTemplate().update("order.updateOrderDetail",orderDetail);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail){
		return this.getSqlMapClientTemplate().queryForList("order.searchOrderDetailList", orderDetail);
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
    	return (Long) getSqlMapClientTemplate().queryForObject("order.getOrderId");
    }
	
	public long createOrder(XppOrder order){
		return (Long)getSqlMapClientTemplate().insert("order.createOrder",order);
	}
	
	public long createOrderDetail(OrderDetail orderDetail){
		return (Long)getSqlMapClientTemplate().insert("order.createOrderDetail",orderDetail);
	}
	
	public XppOrder getOrder(XppOrder order) {
		return (XppOrder)this.getSqlMapClientTemplate().queryForObject("order.getOrder", order);
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
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("order.getOrderDetailAllListCount", orderInfo);
    }
	
	@SuppressWarnings("unchecked")
	public List<OrderInfo> getOrderInfoList(OrderInfo orderInfo){
		return (List<OrderInfo>)this.getSqlMapClientTemplate().queryForList("order.getOrderDetailAllList", orderInfo);
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
	
	
}
