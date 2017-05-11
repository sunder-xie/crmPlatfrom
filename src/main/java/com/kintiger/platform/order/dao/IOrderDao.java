package com.kintiger.platform.order.dao;

import java.util.List;

import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;


public interface IOrderDao {


	/**
	 * 查询订单数量
	 * 
	 * @param customer
	 * @return
	 */
	int searchOrderCount(XppOrder order);
	
	/**
	 * 查询订单明细数量
	 * 
	 * @param customer
	 * @return
	 */
	int searchOrderDetailCount(OrderDetail orderDetail);
	

	/**
	 * 查询订单
	 * 
	 * @param customer
	 * @return
	 */
	List<XppOrder> searchOrderList(XppOrder order);
	
	List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail);
	
	List<XppOrder> orderExport(XppOrder order);
	

	/**
	 * 查询订单明细
	 * 
	 * @param customer
	 * @return
	 */
	public XppOrder searchOrder(XppOrder order);
	
	
	public int updateOrder(XppOrder order);
	
	public int updateOrderDetail(OrderDetail orderDetail);
	
	public int getSkuCount(Sku sku);

	public List<Sku> getSkuNameList(Sku sku);
	
	public long getOrderId();
	
	public long createOrder(XppOrder order);
	
	public long createOrderDetail(OrderDetail orderDetail);
	
	public XppOrder getOrder(XppOrder order);
	
	public String getKunnrEmpLifnrByEmpCode(String empCode);
	
	public List<String> getKunnrByEmpId(Long userId);
	
	public int getOrderInfoListCount(OrderInfo orderInfo);
	
	public List<OrderInfo> getOrderInfoList(OrderInfo orderInfo);

	public int getKunnrListCount(String kunnr,String kunnrName,String orgId);
	
	public List<Kunnr> getKunnrList(String kunnr,String kunnrName,String orgId);
}
