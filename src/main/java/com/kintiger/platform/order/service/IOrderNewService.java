package com.kintiger.platform.order.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.pojo.Account;
import com.kintiger.platform.order.pojo.PrintFormat;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;


public interface IOrderNewService {

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
	
	public Long getMaxId();
	
	public int searchAccountCount(Account account);
	
	public List<Account> searchAccountList(Account account);
	
	public Account searchAccount(Account account);

	public int createAccount(Account account);
	
	public int updateAccount(Account account);
	
	public List<Account> getBalance(List<String> kunnr);
	
	public String OrderSuccessRFC(Account account);

	List<PrintFormat> getPrintFormatList(PrintFormat printFormat);

	List<PrintFormat> getPropertiesJSON();

	List<Kunnr> kunnrSearch(Kunnr kunnr);

	int getPrintFormatListCount(PrintFormat printFormat);

	StringResult modifyFormat(PrintFormat printFormat);

	StringResult createFormat(PrintFormat printFormat);
	
	public int getUserStationBySjjlCount(String userId);

}