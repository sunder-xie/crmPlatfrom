package com.kintiger.platform.order.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.order.dao.IOrderNewDao;
import com.kintiger.platform.order.pojo.Account;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.PrintFormat;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.service.IOrderNewService;
import com.kintiger.platform.sms.service.ISmsService;
import com.sap.mw.jco.JCO;

public class OrderNewServiceImpl implements IOrderNewService {

	private IOrderNewDao orderNewDao;
	private SAPConnectionBean sapConnection;
	private static Log logger = LogFactory.getLog(OrderNewServiceImpl.class);
	
	public int searchOrderCount(XppOrder order) {
		try {
			return orderNewDao.searchOrderCount(order);
		} catch (Exception e) {
			logger.error(order, e);
			return 0;
		}
	}
	
	public int searchOrderDetailCount(OrderDetail orderDetail) {
		try {
			return orderNewDao.searchOrderDetailCount(orderDetail);
		} catch (Exception e) {
			logger.error(orderDetail, e);
			return 0;
		}
	}

	public List<XppOrder> searchOrderList(XppOrder order) {
		try {
			return orderNewDao.searchOrderList(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<OrderDetail> searchOrderDetailList(OrderDetail orderDetail){
		try {
			return orderNewDao.searchOrderDetailList(orderDetail);
		} catch (Exception e) {
			logger.error(orderDetail, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<XppOrder>orderExport(XppOrder order){
		try {
			return orderNewDao.orderExport(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}	
	}
	
	public XppOrder searchOrder(XppOrder order) {
		try {
			return orderNewDao.searchOrder(order);
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
						int s = orderNewDao.updateOrder(order);
						int y=0;
						if(orderDetailList!=null){
							for(int i=0;i<orderDetailList.size();i++){
								try {
									y= y + orderNewDao.updateOrderDetail(orderDetailList.get(i));
								} catch (Exception e) {
									logger.error(orderDetailList, e);
									e.printStackTrace();
								}
							}
							if(s==0 && 0==y){
								flag ="N";
							}
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
			return orderNewDao.getSkuCount(sku);			
		}catch (Exception e ){
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		try{
			return orderNewDao.getSkuNameList(sku);
		}catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return null;
		}

	}
	
    public long getOrderId(){
    	try{
			return orderNewDao.getOrderId();
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
	
	public long createOrder(XppOrder order){
		try{
			return orderNewDao.createOrder(order);
		}catch (Exception e) {
            logger.error(order, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public long createOrderDetail(OrderDetail orderDetail){
		try{
			return orderNewDao.createOrderDetail(orderDetail);
		}catch (Exception e) {
			logger.error(orderDetail, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public XppOrder getOrder(XppOrder order) {
		try {
			return orderNewDao.getOrder(order);
		} catch (Exception e) {
			logger.error(order, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public String getKunnrEmpLifnrByEmpCode(String empCode){
		try {
			return orderNewDao.getKunnrEmpLifnrByEmpCode(empCode);
		} catch (Exception e) {
			logger.error(empCode, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getKunnrByEmpId(Long userId){
		try {
			return orderNewDao.getKunnrByEmpId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			e.printStackTrace();
			return null;
		}
	}
	
    public int getOrderInfoListCount(OrderInfo orderInfo){
    	try {
			return orderNewDao.getOrderInfoListCount(orderInfo);
		} catch (Exception e) {
			logger.error(orderInfo, e);
			e.printStackTrace();
			return 0;
		}
    }
	
	public List<OrderInfo> getOrderInfoList(OrderInfo orderInfo){
		try {
			return orderNewDao.getOrderInfoList(orderInfo);
		} catch (Exception e) {
			logger.error(orderInfo, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getKunnrListCount(String kunnr,String kunnrName,String orgId){
		try {
			return orderNewDao.getKunnrListCount(kunnr,kunnrName,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Kunnr> getKunnrList(String kunnr,String kunnrName,String orgId){
		try {
			return orderNewDao.getKunnrList(kunnr,kunnrName,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Long getMaxId(){
		try {
			return orderNewDao.getMaxId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public int searchAccountCount(Account account){
    	try {
			return orderNewDao.searchAccountCount(account);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
	
	public List<Account> searchAccountList(Account account){
		try {
			return orderNewDao.searchAccountList(account);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Account searchAccount(Account account){
		try {
			return orderNewDao.searchAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public int createAccount(Account account){
		try {
			return orderNewDao.createAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateAccount(Account account){
		try {
			return orderNewDao.updateAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Account> getBalance(List<String> kunnrList){
		try {
			List<Account> accountList = new ArrayList<Account>();
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_CUSTOMER_FUNDS");		
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();
			for(String kunnr:kunnrList){
				Account account = new Account();
				account.setKunnr(kunnr);
				if(kunnr.length()==8){
					kunnr = "00"+kunnr;
				}
				input.getField("KUNNR").setValue(kunnr);
				client.execute(func);
				JCO.Table exportTable = func.getTableParameterList().getTable(
						"CUSTOMER_FUNDS");
				for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
						.nextRow()) {
					Double dk=Double.parseDouble(exportTable.getValue("DK_NETWR").toString());
					Double fy=Double.parseDouble(exportTable.getValue("FY_NETWR").toString());
					Double zk=Double.parseDouble(exportTable.getValue("ZK_NETWR").toString());
					account.setDk_netwr(dk);
					account.setFy_netwr(fy);
					account.setZk_netwr(zk);
				}
				accountList.add(account);
			}
			return accountList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String OrderSuccessRFC(Account account){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		JCO.Client client = null;
		String inSap="";
		client = sapConnection.getSAPClientFromPool();
		sapConnection.setFuncName("ZRFC_REMITTANCE");		
		JCO.Function func = sapConnection.getFunction(client);
		JCO.ParameterList input = func.getImportParameterList();
		String date = sdf.format(account.getSuccessDate());
		String payType="";
		if(account.getPayType().equals("A")){
			payType="B2C";
		}else{
			payType="B2B";
		}
		input.getField("KUNNR").setValue(account.getKunnr());
		input.getField("DDRQ").setValue(date);
		input.getField("DDJE").setValue(account.getPrice());
		input.getField("DDLX").setValue(payType);
		client.execute(func);
		JCO.Table exportTable = func.getTableParameterList().getTable(
				"STATUS");
		System.out.println(exportTable.getNumRows());
		for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
				.nextRow()) {
			inSap=exportTable.getValue("BELNR").toString();
			String status=exportTable.getValue("STATUS").toString();
		}
		return inSap;
	}

	public IOrderNewDao getOrderNewDao() {
		return orderNewDao;
	}

	public void setOrderNewDao(IOrderNewDao orderNewDao) {
		this.orderNewDao = orderNewDao;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	@Override
	public List<PrintFormat> getPrintFormatList(PrintFormat printFormat) {
		try {
			return orderNewDao.getPrintFormatList(printFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PrintFormat> getPropertiesJSON() {
		try {
			return orderNewDao.getPropertiesJSON();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		try {
			return orderNewDao.kunnrSearch(kunnr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getPrintFormatListCount(PrintFormat printFormat) {
		try {
			return orderNewDao.getPrintFormatListCount(printFormat);
		} catch (Exception e) {
			logger.error(printFormat, e);
			return 0;
		}
	}

	@Override
	public StringResult modifyFormat(PrintFormat printFormat) {
		StringResult stringResult = new StringResult();
		try {
			Integer i = orderNewDao.modifyFormat(printFormat);
			stringResult.setCode("success");
			stringResult.setResult(i.toString());
		} catch (Exception e) {
			e.printStackTrace();
			stringResult.setCode("error");
		}
		return stringResult;
	}

	@Override
	public StringResult createFormat(PrintFormat printFormat) {
		StringResult stringResult = new StringResult();
		try {
			orderNewDao.createFormat(printFormat);
			stringResult.setCode("success");
		} catch (Exception e) {
			e.printStackTrace();
			stringResult.setCode("error");
		}
		return stringResult;
	}
	
	public int getUserStationBySjjlCount(String userId){
		try {
			return orderNewDao.getUserStationBySjjlCount(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
