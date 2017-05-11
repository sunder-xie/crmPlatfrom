package com.kintiger.platform.order.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.kunnrBusinessContact.service.IKunnrBusinessService;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.service.IOrderService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.Tree4Ajax;
import com.kintiger.platform.org.service.IOrgService;



/**
 * 照片页面
 * 
 * @author allen.yue
 * 
 */
public class OrderAction extends BaseAction {
	
	private static final long serialVersionUID = 6554056382220537831L;
	
	private XppOrder order;
	private OrderDetail orderDetail;
	private BooleanResult resultMsg;
	private int total;
	private List<XppOrder> orderList;
	private List<OrderDetail> orderDetailList;
	private List<AllUsers> empList;
	private IOrderService orderService;
	private IKunnrBusinessService kunnrBusinessService;

	private String orgId;
	private String zpGpsFiledownload;
	private int pictureTotalCount;
	private int pictureCount;
	private String beginDate;
	private String endDate;
	private String beginDate1;
	private String endDate1;
	private String custNumber;
	private String custId;
	@Decode
	private String custNameZh;
	@Decode
	private String custName;
	@Decode
	private String empName;
	private String flags;
	private String custState;
	private String custType;
	@Decode
	private String contactName;
	private List<Tree4Ajax> treeList;
	private List<Borg> companyList;
	private IOrgService orgServiceHessian;
	private IAllUserService allUserServiceHessian;
	private String node;
	private String flag;
	private String activityId;
	@Decode
	private String userName;
	private String orderId;
    private String orgName;
    private String isOffice;
    private String data;
    
    private Long orderDetailId;
    
    private Sku sku;
    private @Decode String skuName;
    private int skuSize;
    private List<Sku> skuList;
    
    private String orderDesc;
    private String[] skuIds;
    private String[] prices;
    private String[] quantitys;
    private String[] orderTypes;
    private String[] unitDescs;
    private double[] totalPriceDetails;
    private Long[] orderDetailIds;
    private String[] realQuantitys;
    private String custKunnr;
    private String loginId;
    
    private String download; // 下载是否现在完成标识
    
    private OrderInfo orderInfo;
    private List<OrderInfo> orderInfoList;
    private String skuId;
    private String kunnrs;
    private Date startTime;
    private Date endTime;
    private String kunnr;
    private String stationName;
     

	/**
	 * 订单查询
	 * 
	 * @return
	 */
	public String searchOrderPre() {
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		loginId = this.getUser().getLoginId();
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		
		return "searchOrder";
	}
	

	/**
	 * 订单查询
	 * 
	 * @return
	 */
	@JsonResult(field = "orderList", include = { "orderId","userName", "custName", "orgName", "totalPrice", "orderQuntity","status",
		"createDate", "modifyDate", "orderCreateDate", "plannedDeliveryDate", "realDeliveryDate","orderDesc",
		"orderFundsStatus","orderStatus","orderDetailId","skuName","quantity","realQuantity","price","orderType", "totalPriceDetail"}, total = "total")
	public String searchOrder() {
		order= new XppOrder();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
				order.setOrgIds(orgids.split(","));
		}
		order.setCustName(custName);
		order.setCustId(custId);
		order.setCustState(custState);
		order.setCustType(custType); 
		order.setContactName(contactName);
		order.setBeginDate(beginDate);
		order.setEndDate(endDate);
		order.setUserName(userName);
		order.setStart(getStart());
		order.setEnd(getEnd());
	//	order.setOrgId(orgId);
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			order.setIsOffice(isOffice);
			order.setOrgIds(null);
		}else{
			List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
			if(custKunnrs!=null){
				String[] kunnrs = new String[custKunnrs.size()];
				for(int i=0;i<custKunnrs.size();i++){
					kunnrs[i]=custKunnrs.get(i);
				}
				
				order.setCustKunnrs(kunnrs);
			}
		}
		total = orderService.searchOrderCount(order);
		if (total > 0) {
			orderList = orderService.searchOrderList(order);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "skuList",include = { "skuId",
			"skuName","skuUnit" },total = "skuSize")
	public String getSkuName(){
		if (sku == null) {
			sku = new Sku();
		}
		if (StringUtils.isNotEmpty(skuName)) {
			sku.setSkuName(skuName);
		}
		sku.setSkuCompany(custKunnr);
		sku.setStart(getStart());
		sku.setEnd(getEnd());
		skuSize = orderService.getSkuCount(sku);
		if (skuSize != 0) {
			skuList = orderService.getSkuNameList(sku);
		}
		return JSON;
	}
	
	@JsonResult(field = "orderDetailList", include = {"orderDetailId","skuName","quantity",
			"realQuantity","price","orderType","totalPrice","unitDesc"})
		public String searchOrderDetailList() {
			orderDetail = new OrderDetail();
			orderDetail.setOrderId(Long.valueOf(orderId));
			orderDetailList = orderService.searchOrderDetailList(orderDetail);
			return JSON;
		}
	
	@JsonResult(field = "order", include = { "orderId","userName", "custName", "orgName", "totalPrice", "orderQuntity","status",
			"createDate", "modifyDate", "orderCreateDate", "plannedDeliveryDate", "realDeliveryDate","orderDesc",
			"orderFundsStatus","orderStatus","orderDetailId","skuName","quantity","realQuantity","price","orderType"}, total = "total")
		public String searchOrderDetail() {
			order = new XppOrder();
			order.setOrderDetailId(orderDetailId);
			total=1;
			order = orderService.searchOrder(order);
			return JSON;
		}

	public String updateOrder() {
		BooleanResult booleanResult=new BooleanResult();
		AllUsers user=this.getUser();
		this.setSuccessMessage("更新成功");
		
		double count=0;
    	for(int i=0;i<totalPriceDetails.length;i++){
    		count=count+totalPriceDetails[i];
    	}
    	order.setTotalPrice(count+"");
    	order.setLastModifyUser(user.getUserId());
		
//		if(order.getTotalPriceDetail()!=null){
//			Order tmp=new Order();
//			tmp.setOrderDetailId(order.getOrderDetailId());
//			tmp=orderService.searchOrder(tmp);
//			double num=Double.parseDouble(order.getTotalPriceDetail())-Double.parseDouble(tmp.getTotalPriceDetail());
//			order.setTotalPrice(Double.parseDouble(tmp.getTotalPrice())+num+"");
//		}
    	
    	List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
    	for(int i=0;i<orderDetailIds.length;i++){
    		OrderDetail orderDetail = new OrderDetail();
    		orderDetail.setOrderDetailId(orderDetailIds[i]);
    		orderDetail.setSkuId(skuIds[i]);
    		orderDetail.setPrice(prices[i]);
    		orderDetail.setQuantity(quantitys[i]);
    		orderDetail.setTotalPrice(totalPriceDetails[i]+"");
    		orderDetail.setOrderType(orderTypes[i]);
    		orderDetail.setRealQuantity(realQuantitys[i]);
    		orderDetail.setUnitDesc(unitDescs[i]);
    		orderDetailList.add(orderDetail);
    	}
		booleanResult=orderService.updateOrder(order,orderDetailList);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	
	public String toUpdateOrder(){
		AllUsers user=this.getUser();
		order = new XppOrder();
		order.setOrderId(Long.valueOf(orderId));
		order = orderService.searchOrder(order);
		order.setOrgId(user.getOrgId());
		if(user.getLoginId().substring(0, 1).equals("0") || user.getLoginId().equals("admin")){
			return "toUpdateOrder";
		}else{
			return "toUpdateOrderEmp";
		}
		
	}
	
	public String toCreateOrder(){
		order = new XppOrder();
		AllUsers user=this.getUser();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
		}else{
				isOffice="";
		}
		order.setUserName(user.getUserName());
		order.setOrgName(orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId()));
		order.setOrgId(user.getOrgId());
		return "toCreateOrder";
	}
	
    public String createOrder(){
    	this.setSuccessMessage("创建成功");
    	AllUsers user=this.getUser();
    	double count=0;
    	for(int i=0;i<totalPriceDetails.length;i++){
    		count=count+totalPriceDetails[i];
    	}
    	order = new XppOrder();
    	order.setCustId(custId);
    	order.setOrgId(user.getOrgId());
    	order.setUserId(user.getUserId());
    	order.setOrderDesc(orderDesc);
    	order.setTotalPrice(count+"");
    	order.setIsOffice(isOffice);
    	long num1=orderService.createOrder(order);
    	long num2=0;
    	long orderId=orderService.getOrderId();
    	for(int i=0;i<skuIds.length;i++){
    		OrderDetail orderDetail = new OrderDetail();
    		orderDetail.setOrderId(orderId);
    		orderDetail.setSkuId(skuIds[i]);
    		orderDetail.setPrice(prices[i]);
    		orderDetail.setQuantity(quantitys[i]);
    		orderDetail.setTotalPrice(totalPriceDetails[i]+"");
    		orderDetail.setOrderType(orderTypes[i]);
    		orderDetail.setUnitDesc(unitDescs[i]);
    		num2=num2+orderService.createOrderDetail(orderDetail);
    	}
    	if(num1==0){
    		this.setFailMessage("创建失败,请联系管理员");
    	}
    	
		return RESULT_MESSAGE;
	}
    
 // 组织树
// 	public String orgTreePage() {
// 		return "orgTree";
// 	}
 	
	/**
	 * 跳转订单信息打印页面
	 * @return
	 */
	@PermissionSearch
	public String toPrintOrder() {
		order = new XppOrder();
		order.setOrderId(Long.valueOf(orderId));
		order = orderService.getOrder(order);
		
		
		orderDetail=new OrderDetail();
		orderDetail.setOrderId(Long.valueOf(orderId));
		orderDetailList = orderService.searchOrderDetailList(orderDetail);
		
		int num=0;
		for(int i=0;i<orderDetailList.size();i++){
			num+=Integer.parseInt(orderDetailList.get(i).getQuantity());
		}
		order.setTotalNum(num+"");
		
		DecimalFormat df = new DecimalFormat("#.00");
		order.setTotalPrice(df.format(Double.parseDouble(order.getTotalPrice())));
		return "toPrintOrder";
	}
	
	
	/**
	 * 批量导出
	 * 
	 * @return
	 */
	public String exportOrder() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
	    
//		order= new XppOrder();
//		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
//		if (StringUtils.isNotEmpty(orgids)) {
//				order.setOrgIds(orgids.split(","));
//		}
//		order.setCustName(custName);
//		order.setCustId(custId);
//		order.setCustState(custState);
//		order.setCustType(custType); 
//		order.setContactName(contactName);
//		order.setBeginDate(beginDate);
//		order.setEndDate(endDate);
//		order.setUserName(userName);
//		order.setStart(0);
//		order.setEnd(99999);
//	//	order.setOrgId(orgId);
//		isOffice=this.getUser().getIsOffice();
//		if(!("").equals(isOffice) && isOffice!=null){
//			order.setIsOffice(isOffice);
//		}else{
//			List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
//			if(custKunnrs!=null){
//				String[] kunnrs = new String[custKunnrs.size()];
//				for(int i=0;i<custKunnrs.size();i++){
//					kunnrs[i]=custKunnrs.get(i);
//				}
//				
//				order.setCustKunnrs(kunnrs);
//			}
//		}
//		
//		orderList = orderService.searchOrderList(order);
		
		orderInfo= new OrderInfo();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			orderInfo.setOrgIds(orgids.split(","));
		}
		orderInfo.setSkuId(skuId);
		orderInfo.setStartDate(startTime);
		orderInfo.setEndDate(endTime);
		orderInfo.setStationName(stationName);
		orderInfo.setStart(0);
		orderInfo.setEnd(99999);
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			orderInfo.setIsOffice(isOffice);
		}else{
//			List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
//			if(custKunnrs!=null){
//				String[] kunnrs = new String[custKunnrs.size()];
//				for(int i=0;i<custKunnrs.size();i++){
//					kunnrs[i]=custKunnrs.get(i);
//				}
//				orderInfo.setOrgIds(null);
//				orderInfo.setCustKunnrs(kunnrs);
//			}
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			if(kunnrTmp!=null && kunnrTmp.size()!=0){
				if(kunnrTmp.size()==1){
					kunnr=kunnrTmp.get(0);
				}else{
					String[] kunnrs=new String[kunnrTmp.size()];
					for(int i=0;i<kunnrTmp.size();i++){
						kunnrs[i]=kunnrTmp.get(i);
					}
					orderInfo.setCustKunnrs(kunnrs);
				}
				orderInfo.setOrgIds(null);
			}
		}
		if(StringUtils.isNotEmpty(kunnr)){
			orderInfo.setIsOffice(kunnr);
		}
		orderInfoList = orderService.getOrderInfoList(orderInfo);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "订单信息.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 30);
			wsheet.setColumnView(3, 30);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 10);
			wsheet.setColumnView(10, 15);
			
			
			wsheet.addCell(new Label(0, 0, "经销商编号"));
			wsheet.addCell(new Label(1, 0, "经销商"));
			wsheet.addCell(new Label(2, 0, "门店名称"));
			wsheet.addCell(new Label(3, 0, "产品编码"));
			wsheet.addCell(new Label(4, 0, "产品名称 "));
			wsheet.addCell(new Label(5, 0, "单位"));
            wsheet.addCell(new Label(6, 0, "订单数量 "));
			wsheet.addCell(new Label(7, 0, "实际数量 "));
            wsheet.addCell(new Label(8, 0, "金额"));
			wsheet.addCell(new Label(9, 0, "提报人"));
			wsheet.addCell(new Label(10, 0, "提报人岗位"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= orderInfoList.size(); i++) {
				wsheet.addCell(new Label(0,i,orderInfoList.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(1,i,orderInfoList.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(2,i,orderInfoList.get(i-1).getCustName(),wcfF));
				wsheet.addCell(new Label(3,i,orderInfoList.get(i-1).getSkuCode(),wcfF));
				wsheet.addCell(new Label(4,i,orderInfoList.get(i-1).getSkuName(),wcfF));
				wsheet.addCell(new Label(5,i,orderInfoList.get(i-1).getUnitDesc(),wcfF));
				wsheet.addCell(new Label(6,i,orderInfoList.get(i-1).getQuantity(),wcfF));
				wsheet.addCell(new Label(7,i,orderInfoList.get(i-1).getRealQuantity(),wcfF));
				wsheet.addCell(new Label(8,i,orderInfoList.get(i-1).getTotalPrice(),wcfF));
				wsheet.addCell(new Label(9,i,orderInfoList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Label(10,i,orderInfoList.get(i-1).getStationName(),wcfF));
			}
			wbook.write();
		
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return SUCCESS;
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 批量导出
	 * 
	 * @return
	 */
	public String exportForExcel() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
	    
		order= new XppOrder();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			order.setOrgIds(orgids.split(","));
		}
		order.setCustName(custName);
		order.setCustId(custId);
		order.setCustState(custState);
		order.setCustType(custType); 
		order.setContactName(contactName);
		order.setBeginDate(beginDate);
		order.setEndDate(endDate);
		order.setUserName(userName);
		order.setStart(0);
		order.setEnd(99999);
	//	order.setOrgId(orgId);
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			order.setIsOffice(isOffice);
		}else{
			List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
			if(custKunnrs!=null){
				String[] kunnrs = new String[custKunnrs.size()];
				for(int i=0;i<custKunnrs.size();i++){
					kunnrs[i]=custKunnrs.get(i);
				}
				
				order.setCustKunnrs(kunnrs);
			}
		}
		
		orderList = orderService.searchOrderList(order);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "订单信息.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 30);
			wsheet.setColumnView(8, 30);
			
			
			wsheet.addCell(new Label(0, 0, "订单号"));
			wsheet.addCell(new Label(1, 0, "客户名称"));
			wsheet.addCell(new Label(2, 0, "下单人"));
			wsheet.addCell(new Label(3, 0, "下单人组织"));
			wsheet.addCell(new Label(4, 0, "订单状态 "));
			wsheet.addCell(new Label(5, 0, "是否收货"));
            wsheet.addCell(new Label(6, 0, "是否收款 "));
			wsheet.addCell(new Label(7, 0, "订单描述 "));
            wsheet.addCell(new Label(8, 0, "订单创建时间"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= orderList.size(); i++) {
				String status="";
				if("U".equals(orderList.get(i-1).getStatus())){
					status="正常";
				}else{
					status="作废";
				}
				String orderStatus="";
				if("Y".equals(orderList.get(i-1).getOrderStatus())){
					orderStatus="已送";
				}else{
					orderStatus="未送";
				}
				String orderFundsStatus="";
				if("Y".equals(orderList.get(i-1).getOrderFundsStatus())){
					orderFundsStatus="已打款";
				}else{
					orderFundsStatus="未打款";
				}
				wsheet.addCell(new Number(0,i,orderList.get(i-1).getOrderId(),wcfF));
				wsheet.addCell(new Label(1,i,orderList.get(i-1).getCustName(),wcfF));
				wsheet.addCell(new Label(2,i,orderList.get(i-1).getUserName(),wcfF));
				wsheet.addCell(new Label(3,i,orderList.get(i-1).getOrgName(),wcfF));
				wsheet.addCell(new Label(4,i,status,wcfF));
				wsheet.addCell(new Label(5,i,orderStatus,wcfF));
				wsheet.addCell(new Label(6,i,orderFundsStatus,wcfF));
				wsheet.addCell(new Label(7,i,orderList.get(i-1).getOrderDesc(),wcfF));
				wsheet.addCell(new Label(8,i,orderList.get(i-1).getCreateDate(),wcfF));
			}
			wbook.write();
		
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return SUCCESS;
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 校验数据是否下载完成
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else {
			download = "Yes";
		}
		return JSON;
	}
	
	public String searchOrderInfoPre() {
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		loginId = this.getUser().getLoginId();
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
//		List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
//		if(custKunnrs!=null){
//			for(int i=0;i<custKunnrs.size();i++){
//				if(i==0){
//					kunnrs=custKunnrs.get(i);
//				}else{
//					kunnrs=kunnrs+","+custKunnrs.get(i);
//				}
//			}
//		}
		return "searchOrderInfo";
	}
	
	/**
	 * 订单报表
	 * 
	 * @return
	 */
	@JsonResult(field = "orderInfoList", include = { "totalPrice","quantity", "realQuantity",
			"kunnr", "kunnrName", "skuName","unitDesc","userId","userName","stationName"}, total = "total")
	public String searchOrderInfo() {
		orderInfo= new OrderInfo();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			orderInfo.setOrgIds(orgids.split(","));
		}
		orderInfo.setSkuId(skuId);
		orderInfo.setStartDate(startTime);
		orderInfo.setEndDate(endTime);
		orderInfo.setStationName(stationName);
		orderInfo.setStart(getStart());
		orderInfo.setEnd(getEnd());
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			orderInfo.setIsOffice(isOffice);
		}else{
//			List<String> custKunnrs = orderService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
//			if(custKunnrs!=null){
//				String[] kunnrs = new String[custKunnrs.size()];
//				for(int i=0;i<custKunnrs.size();i++){
//					kunnrs[i]=custKunnrs.get(i);
//				}
//				orderInfo.setOrgIds(null);
//				orderInfo.setCustKunnrs(kunnrs);
//			}
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			if(kunnrTmp!=null && kunnrTmp.size()!=0){
				if(kunnrTmp.size()==1){
					kunnr=kunnrTmp.get(0);
				}else{
					String[] kunnrs=new String[kunnrTmp.size()];
					for(int i=0;i<kunnrTmp.size();i++){
						kunnrs[i]=kunnrTmp.get(i);
					}
					orderInfo.setCustKunnrs(kunnrs);
				}
				orderInfo.setOrgIds(null);
			}
		}
		if(StringUtils.isNotEmpty(kunnr)){
			orderInfo.setIsOffice(kunnr);
		}
		total = orderService.getOrderInfoListCount(orderInfo);
		if (total > 0) {
			orderInfoList = orderService.getOrderInfoList(orderInfo);
		}
		return JSON;
	}
	
	private void setResultMsg(BooleanResult resultMsg) {
		this.resultMsg = resultMsg;

	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getZpGpsFiledownload() {
		return zpGpsFiledownload;
	}

	public void setZpGpsFiledownload(String zpGpsFiledownload) {
		this.zpGpsFiledownload = zpGpsFiledownload;
	}

	public int getPictureTotalCount() {
		return pictureTotalCount;
	}

	public void setPictureTotalCount(int pictureTotalCount) {
		this.pictureTotalCount = pictureTotalCount;
	}

	public int getPictureCount() {
		return pictureCount;
	}

	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustNameZh() {
		return custNameZh;
	}

	public void setCustNameZh(String custNameZh) {
		this.custNameZh = custNameZh;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getBeginDate1() {
		return beginDate1;
	}

	public void setBeginDate1(String beginDate1) {
		this.beginDate1 = beginDate1;
	}

	public String getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<Borg> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public IAllUserService getAllUserServiceHessian() {
		return allUserServiceHessian;
	}

	public void setAllUserServiceHessian(IAllUserService allUserServiceHessian) {
		this.allUserServiceHessian = allUserServiceHessian;
	}

	public List<AllUsers> getEmpList() {
		return empList;
	}

	public void setEmpList(List<AllUsers> empList) {
		this.empList = empList;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public XppOrder getOrder() {
		return order;
	}

	public void setOrder(XppOrder order) {
		this.order = order;
	}

	public List<XppOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<XppOrder> orderList) {
		this.orderList = orderList;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIsOffice() {
		return isOffice;
	}

	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BooleanResult getResultMsg() {
		return resultMsg;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public int getSkuSize() {
		return skuSize;
	}

	public void setSkuSize(int skuSize) {
		this.skuSize = skuSize;
	}

	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String[] getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String[] skuIds) {
		this.skuIds = skuIds;
	}

	public String[] getPrices() {
		return prices;
	}

	public void setPrices(String[] prices) {
		this.prices = prices;
	}

	public String[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(String[] quantitys) {
		this.quantitys = quantitys;
	}

	public String[] getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(String[] orderTypes) {
		this.orderTypes = orderTypes;
	}

	public double[] getTotalPriceDetails() {
		return totalPriceDetails;
	}

	public void setTotalPriceDetails(double[] totalPriceDetails) {
		this.totalPriceDetails = totalPriceDetails;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String[] getUnitDescs() {
		return unitDescs;
	}

	public void setUnitDescs(String[] unitDescs) {
		this.unitDescs = unitDescs;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public Long[] getOrderDetailIds() {
		return orderDetailIds;
	}

	public void setOrderDetailIds(Long[] orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}

	public String[] getRealQuantitys() {
		return realQuantitys;
	}

	public void setRealQuantitys(String[] realQuantitys) {
		this.realQuantitys = realQuantitys;
	}
	public String getCustKunnr() {
		return custKunnr;
	}
	public void setCustKunnr(String custKunnr) {
		this.custKunnr = custKunnr;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public OrderInfo getOrderInfo() {
		return orderInfo;
	}


	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}


	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}


	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}


	public String getSkuId() {
		return skuId;
	}


	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}


	public String getKunnrs() {
		return kunnrs;
	}


	public void setKunnrs(String kunnrs) {
		this.kunnrs = kunnrs;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getKunnr() {
		return kunnr;
	}


	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}


	public String getStationName() {
		return stationName;
	}


	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	public IKunnrBusinessService getKunnrBusinessService() {
		return kunnrBusinessService;
	}


	public void setKunnrBusinessService(IKunnrBusinessService kunnrBusinessService) {
		this.kunnrBusinessService = kunnrBusinessService;
	}


 





}
