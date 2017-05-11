package com.kintiger.platform.order.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.order.pojo.XppOrder;


public interface IOrderService {

	int searchOrderCount(XppOrder order);
	
	int searchOrderDetailCount(OrderDetail orderDetail);
	
	List<XppOrder> searchOrderList(XppOrder order);

	List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail);
	
	List<XppOrder> orderExport(XppOrder order);

	XppOrder searchOrder(XppOrder order);
	
	BooleanResult updateOrder(XppOrder order,List<OrderDetail> orderDetailList);
	
	boolean exportOrder(HttpServletResponse response,List<XppOrder> orderList);
	
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