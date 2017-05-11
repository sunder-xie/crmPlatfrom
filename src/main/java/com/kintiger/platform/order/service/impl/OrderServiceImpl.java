package com.kintiger.platform.order.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.dao.IOrderDao;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.service.IOrderService;
import com.kintiger.platform.order.pojo.Sku;

public class OrderServiceImpl implements IOrderService {

	private IOrderDao orderDao;
	private static Log logger = LogFactory.getLog(OrderServiceImpl.class);
	
	public int searchOrderCount(XppOrder order) {
		try {
			return orderDao.searchOrderCount(order);
		} catch (Exception e) {
			logger.error(order, e);
			return 0;
		}
	}
	
	public int searchOrderDetailCount(OrderDetail orderDetail) {
		try {
			return orderDao.searchOrderDetailCount(orderDetail);
		} catch (Exception e) {
			logger.error(orderDetail, e);
			return 0;
		}
	}

	public List<XppOrder> searchOrderList(XppOrder order) {
		try {
			return orderDao.searchOrderList(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail){
		try {
			return orderDao.searchOrderDetailList(orderDetail);
		} catch (Exception e) {
			logger.error(orderDetail, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<XppOrder>orderExport(XppOrder order){
		try {
			return orderDao.orderExport(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}	
	}
	
	public XppOrder searchOrder(XppOrder order) {
		try {
			return orderDao.searchOrder(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}
	}
	public BooleanResult updateOrder(XppOrder order,List<OrderDetail> orderDetailList) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);
				// 插入订单
				try {
					String flag = "Y";
					if(order!=null){
						Long s = (long) orderDao.updateOrder(order);
						long y=0;
						for(int i=0;i<orderDetailList.size();i++){
							try {
								y =y + (long) orderDao.updateOrderDetail(orderDetailList.get(i));
							} catch (Exception e) {
								logger.error(orderDetailList, e);
								e.printStackTrace();
							}
						}
                        if(null==s && 0==y){
                        	flag ="N";
                        }
					}
					if(("Y").equals(flag)){
	                   	result.setResult(true);
                    	result.setCode("维护成功");
					}else{
	                   	result.setResult(false);
                    	result.setCode("维护失败");	
					}
				} catch (Exception e) {
		        	result.setResult(false);
		        	result.setCode("维护失败");
				}
		return result;
	}
	
	public boolean exportOrder(HttpServletResponse response,List<XppOrder> orderList) {   
		try { 
		       OutputStream os = response.getOutputStream();// 取得输出流   
		        response.reset();// 清空输出流   
		        response.setHeader("Content-disposition", "attachment; filename=exportOrder.xls");// 设定输出文件头   
		        response.setContentType("application/msexcel");// 定义输出类型 	        
		        WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件   
		        String tmptitle = "订单导出"; // 标题   
		        WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称  	        
		        // 设置excel标题   
		        WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD, 
		                       false,UnderlineStyle.NO_UNDERLINE,Colour.GRAY_80);   
		        WritableCellFormat wcfFC = new WritableCellFormat(wfont); 
		        wcfFC.setBackground(Colour.AQUA); 
		        wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));   
		        wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, 
		                   false, UnderlineStyle.NO_UNDERLINE,Colour.GRAY_80); 
		        wcfFC = new WritableCellFormat(wfont);  
		        // 开始生成主体内容                   	
		        wsheet.addCell(new Label(0, 0, "明细ID",wcfFC));
		        wsheet.addCell(new Label(1, 0, "订单类型",wcfFC));
		        wsheet.addCell(new Label(2, 0, "品项",wcfFC));
		        wsheet.addCell(new Label(3, 0, "明细总价",wcfFC)); 
		        wsheet.addCell(new Label(4, 0, "单价",wcfFC));   
		        wsheet.addCell(new Label(5, 0, "数量",wcfFC)); 
		        wsheet.addCell(new Label(6, 0, "实际数量",wcfFC));   
		        
		        wsheet.addCell(new Label(7, 0, "客户名称",wcfFC)); 
		        wsheet.addCell(new Label(8, 0, "总单号",wcfFC)); 
		        wsheet.addCell(new Label(9, 0, "总金额",wcfFC)); 
		        wsheet.addCell(new Label(10, 0, "下单人",wcfFC)); 
		        wsheet.addCell(new Label(11, 0, "下单人组织",wcfFC));
		        wsheet.addCell(new Label(12, 0, "是否收款",wcfFC));
		        wsheet.addCell(new Label(13, 0, "是否送货",wcfFC));
		        wsheet.addCell(new Label(14, 0, "订单状态",wcfFC));
		        wsheet.addCell(new Label(15, 0, "订单描述",wcfFC));
		        wsheet.addCell(new Label(16, 0, "订单创建时间",wcfFC)); 
		    for(int i=0;i<orderList.size();i++){
		    	  wsheet.setColumnView(i, 17);
		    	  wsheet.addCell(new Label(0, i+1, orderList.get(i).getOrderDetailId()+"")); 
                   if(null!=orderList.get(i).getOrderType()){
			    	  String state = null;	
					    if("A".equals(orderList.get(i).getOrderType())){
					    	state = "本品";
					    }else {
					    	state = "赠品";
					    }
			        wsheet.addCell(new Label(1, i+1, state));
                   }else{
				    wsheet.addCell(new Label(1, i+1, " ".toString())); 
			    }    
                   wsheet.addCell(new Label(2, i+1, orderList.get(i).getSkuName())); 
				   wsheet.addCell(new Label(3, i+1, orderList.get(i).getDtotalPrice().toString()));
				   wsheet.addCell(new Label(4, i+1, orderList.get(i).getPrice().toString())); 
				   wsheet.addCell(new Label(5, i+1, orderList.get(i).getQuantity().toString()));
				   if(null!=orderList.get(i).getRealQuantity()){
					 wsheet.addCell(new Label(6, i+1, orderList.get(i).getRealQuantity().toString()));
				   }else{
					 wsheet.addCell(new Label(6, i+1, ""));
				   }
				   wsheet.addCell(new Label(7, i+1, orderList.get(i).getCustName()));
				   wsheet.addCell(new Label(8, i+1, orderList.get(i).getOrderId()+""));
				   wsheet.addCell(new Label(9, i+1, orderList.get(i).getTotalPrice()));
				   wsheet.addCell(new Label(10, i+1, orderList.get(i).getUserName()));
				   wsheet.addCell(new Label(11, i+1, orderList.get(i).getOrgName()));
				   if(null!= orderList.get(i).getOrderFundsStatus()){
					   String fun = null;
					   if("Y".equals(orderList.get(i).getOrderFundsStatus())){
						   fun = "已打款";
					   }else{
						   fun = "未打款";
					   }
				       wsheet.addCell(new Label(12, i+1, fun));
				   }else{
					   wsheet.addCell(new Label(12, i+1, ""));   
				   }
				   if(null!= orderList.get(i).getOrderStatus()){
					   String sta=null;
					   if("Y".equals(orderList.get(i).getOrderStatus())){
						   sta = "已送货";
					   }else{
						   sta = "未送货";
					   }
					   wsheet.addCell(new Label(13, i+1, sta));
				   }else{
					   wsheet.addCell(new Label(13, i+1, ""));  
				   }
				   if(null!= orderList.get(i).getStatus()){
					   String sta=null;
					   if("U".equals(orderList.get(i).getStatus())){
						   sta = "正常";
					   }else{
						   sta = "作废";
					   }
					   wsheet.addCell(new Label(14, i+1, sta));
				   }else{
					   wsheet.addCell(new Label(14, i+1, ""));  
				   }
				   wsheet.addCell(new Label(15, i+1, orderList.get(i).getOrderDesc()));
				   wsheet.addCell(new Label(16, i+1, orderList.get(i).getCreateDate()));
				   
		}           
		// 主体内容生成结束           
		wbook.write(); // 写入文件   
		wbook.close();  
		os.close(); // 关闭流
		return true; 
		} 
		catch(Exception ex) 
		{ 
		ex.printStackTrace(); 
		return false; 
		} 
	 }  
	
	@Override
	public int getSkuCount(Sku sku) {
		try{
			return orderDao.getSkuCount(sku);			
		}catch (Exception e ){
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		try{
			return orderDao.getSkuNameList(sku);
		}catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return null;
		}

	}
	
    public long getOrderId(){
    	try{
			return orderDao.getOrderId();
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
	
	public long createOrder(XppOrder order){
		try{
			return orderDao.createOrder(order);
		}catch (Exception e) {
            logger.error(order, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public long createOrderDetail(OrderDetail orderDetail){
		try{
			return orderDao.createOrderDetail(orderDetail);
		}catch (Exception e) {
			logger.error(orderDetail, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public XppOrder getOrder(XppOrder order) {
		try {
			return orderDao.getOrder(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public String getKunnrEmpLifnrByEmpCode(String empCode){
		try {
			return orderDao.getKunnrEmpLifnrByEmpCode(empCode);
		} catch (Exception e) {
			logger.error(empCode, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getKunnrByEmpId(Long userId){
		try {
			return orderDao.getKunnrByEmpId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			e.printStackTrace();
			return null;
		}
	}
	
    public int getOrderInfoListCount(OrderInfo orderInfo){
    	try {
			return orderDao.getOrderInfoListCount(orderInfo);
		} catch (Exception e) {
			logger.error(orderInfo, e);
			e.printStackTrace();
			return 0;
		}
    }
	
	public List<OrderInfo> getOrderInfoList(OrderInfo orderInfo){
		try {
			return orderDao.getOrderInfoList(orderInfo);
		} catch (Exception e) {
			logger.error(orderInfo, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getKunnrListCount(String kunnr,String kunnrName,String orgId){
		try {
			return orderDao.getKunnrListCount(kunnr,kunnrName,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Kunnr> getKunnrList(String kunnr,String kunnrName,String orgId){
		try {
			return orderDao.getKunnrList(kunnr,kunnrName,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public IOrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}


}
