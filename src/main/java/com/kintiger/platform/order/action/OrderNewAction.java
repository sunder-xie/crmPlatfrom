package com.kintiger.platform.order.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.abc.pay.client.Base64;
import com.abc.pay.client.TrxException;
import com.abc.pay.client.TrxResponse;
import com.abc.pay.client.ebus.PaymentRequest;
import com.abc.pay.client.ebus.PaymentResult;
import com.abc.pay.client.ebus.QueryOrderRequest;
import com.abc.pay.client.ebus.RefundRequest;
import com.hitrust.b2b.trustpay.client.b2b.FundTransferRequest;
import com.hitrust.b2b.trustpay.client.b2b.TrnxInfo;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItem;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItems;
import com.hitrust.b2b.trustpay.client.b2b.TrnxRemark;
import com.hitrust.b2b.trustpay.client.b2b.TrnxRemarks;
import com.hitrust.b2b.trustpay.client.b2b.TrnxResult;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.goal.action.GoalAction;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.kunnrBusinessContact.service.IKunnrBusinessService;
import com.kintiger.platform.order.pojo.Account;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.order.pojo.PrintFormat;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.service.IOrderNewService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.Tree4Ajax;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.sms.service.ISmsService;



/**
 * 照片页面
 * 
 * @author allen.yue
 * 
 */
public class OrderNewAction extends BaseAction{
	
	private static final long serialVersionUID = 6554056382220537831L;
	
	private static Log logger = LogFactory.getLog(GoalAction.class);
	private XppOrder order;
	private OrderDetail orderDetail;
	private BooleanResult resultMsg;
	private int total;
	private List<XppOrder> orderList;
	private List<OrderDetail> orderDetailList;
	private List<AllUsers> empList;
	private IOrderNewService orderNewService;
	private IKunnrBusinessService kunnrBusinessService;

	private String orgId;
	private String beginDate;
	private String endDate;
	private String custId;
	@Decode
	private String custName;
	private String flags;
	private String custState;
	private String custType;
	@Decode
	private String contactName;
	private List<Tree4Ajax> treeList;
	private List<Borg> companyList;
	private IOrgService orgServiceHessian;
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
    
    private String kunnrId;
    @Decode
    private String name2;
    private String mobile;
    @Decode
    private String carType;
    @Decode
    private String address;
    
    private String MSG;
    private String result;
    private String payType;
    private String cardType;
    private String totalPrice;
    
    private Account account;
    private List<Account> accountList;
    private String userId;
    private IKunnrService kunnrService;

	private Long accountId;
	private String errorMessage;
	private String status;
	private String accountIds;
    private List<PrintFormat> printFormatList;
    private PrintFormat printFormat;
    private Integer format_id;
    private List<Kunnr> kunnrList;
    private String bhxjFlag;
	@Decode
	private String value;
	@Decode
	private String kunnr_name;
	private String ids;
	private String out;
    
	/**
	 * 订单查询
	 * 
	 * @return
	 */
	public String searchOrderNewPre() {
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		loginId = this.getUser().getLoginId();
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		return "searchOrderNew";
	}
	
	public String toSearchAccount() {
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		loginId = this.getUser().getLoginId();
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		isOffice = this.getUser().getIsOffice();
		return "toSearchAccount";
	}
	
	public String toAccountPayPage() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		AllUsers user = this.getUser();
		Kunnr kunnr = new Kunnr();
		kunnr.setKunnr(user.getIsOffice());
		kunnr = kunnrService.getKunnrEntity(kunnr);
		account = new Account();
		account.setAccountId(Long.parseLong(sdf.format(new Date())+"01"));
		account.setUserId(user.getUserId());
		account.setKunnr(user.getIsOffice());
		account.setKunnrName(kunnr.getName1());
		account.setKverm(kunnr.getKverm());
		return "toAccountPayPage";
	}
	
	public String toSearchKunnrBalance() {
		return "toSearchKunnrBalance";
	}

	/**
	 * 订单查询
	 * 
	 * @return
	 */
	@JsonResult(field = "orderList", include = { "orderId","userName", "kunnrName", "orgName", "totalPrice", "orderQuntity","status",
		"createDate", "modifyDate", "orderCreateDate", "plannedDeliveryDate", "realDeliveryDate","orderDesc",
		"orderFundsStatus","orderStatus","orderDetailId","skuName","quantity","realQuantity","price","orderType", "totalPriceDetail"}, total = "total")
	public String searchOrderNew() {
		order= new XppOrder();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
				order.setOrgIds(orgids.split(","));
		}
		order.setKunnrName(custName);
		order.setKunnr(kunnr);
		order.setContactName(name2);
		order.setBeginDate(beginDate);
		order.setEndDate(endDate);
		order.setUserName(userName);
		order.setStart(getStart());
		order.setEnd(getEnd());
	//	order.setOrgId(orgId);
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			order.setIsOffice(isOffice);
		}else{
			List<String> custKunnrs = orderNewService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
			if(custKunnrs!=null){
				String[] kunnrs = new String[custKunnrs.size()];
				for(int i=0;i<custKunnrs.size();i++){
					kunnrs[i]=custKunnrs.get(i);
				}
				order.setOrgIds(null);
				order.setCustKunnrs(kunnrs);
			}
		}
		total = orderNewService.searchOrderCount(order);
		if (total > 0) {
			orderList = orderNewService.searchOrderList(order);
		}
		return JSON;
	}
	
	@JsonResult(field = "accountList", include = {"accountId","accountDesc","userId","price","fee",
			"createDate","status","userName","kunnrName","successNum","successDate","inSap","payType",
			"cardType"}, total = "total")
		public String searchAccount() {
			account = new Account();
			account.setUserId(userId);
			account.setStartDate(startTime);
			account.setEndDate(endTime);
			account.setStatus(status);
			account.setKunnr(this.getUser().getIsOffice());
			account.setStart(getStart());
			account.setEnd(getEnd());
			total = orderNewService.searchAccountCount(account);
			if(total>0){
				List<Account> list=orderNewService.searchAccountList(account);
				checkOrderResult(list);
				accountList = orderNewService.searchAccountList(account);
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
		
		sku.setStart(getStart());
		sku.setEnd(getEnd());
		skuSize = orderNewService.getSkuCount(sku);
		if (skuSize != 0) {
			skuList = orderNewService.getSkuNameList(sku);
		}
		return JSON;
	}
	
	@JsonResult(field = "orderDetailList", include = {"orderDetailId","skuName","quantity","realQuantity","price","orderType","totalPrice"})
		public String searchOrderNewDetailList() {
			orderDetail = new OrderDetail();
			orderDetail.setOrderId(Long.valueOf(orderId));
			orderDetailList = orderNewService.searchOrderDetailList(orderDetail);
			return JSON;
		}
	
	@JsonResult(field = "order", include = { "orderId","userName", "custName", "orgName", "totalPrice", "orderQuntity","status",
			"createDate", "modifyDate", "orderCreateDate", "plannedDeliveryDate", "realDeliveryDate","orderDesc",
			"orderFundsStatus","orderStatus","orderDetailId","skuName","quantity","realQuantity","price","orderType"}, total = "total")
		public String searchOrderNewDetail() {
			order = new XppOrder();
			order.setOrderDetailId(orderDetailId);
			total=1;
			order = orderNewService.searchOrder(order);
			return JSON;
		}

	public String updateOrderNew() {
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
//			tmp=orderNewService.searchOrder(tmp);
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
    		orderDetail.setLastModifyUser(user.getUserId());
    		orderDetailList.add(orderDetail);
    	}
    	System.out.println("order="+order);
		booleanResult=orderNewService.updateOrder(order,orderDetailList);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	
	public String toUpdateOrderNew(){
		AllUsers user=this.getUser();
		order = new XppOrder();
		order.setOrderId(Long.valueOf(orderId));
		order = orderNewService.searchOrder(order);
		order.setOrgId(user.getOrgId());
		if(user.getLoginId().substring(0, 1).equals("0") || user.getLoginId().equals("admin")){
			return "toUpdateOrderNew";
		}else{
			return "toUpdateOrderNewEmp";
		}
		
	}
	
	public String toCreateOrderNew(){
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
		return "toCreateOrderNew";
	}
	
	public Long createId() {

		// 获取当前日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String tmp2 = sdf.format(new Date());
        return Long.parseLong(tmp2);
	}
	
    public String createOrderNew(){
    	this.setSuccessMessage("创建成功");
    	AllUsers user=this.getUser();
    	double count=0;
    	for(int i=0;i<totalPriceDetails.length;i++){
    		count=count+totalPriceDetails[i];
    	}
    	order = new XppOrder();
    	order.setOrderId(createId());
    	order.setKunnr(kunnrId);
    	order.setOrgId(user.getOrgId());
    	order.setUserId(user.getUserId());
    	order.setOrderDesc(orderDesc);
    	order.setTotalPrice(count+"");
    	order.setIsOffice(isOffice);
    	order.setName2(name2);
    	order.setAddress(address);
    	order.setCarType(carType);
    	order.setMobile(mobile);
    	long num1=orderNewService.createOrder(order);
    	long num2=0;
    	long orderId=order.getOrderId();
    	for(int i=0;i<skuIds.length;i++){
    		OrderDetail orderDetail = new OrderDetail();
    		orderDetail.setOrderId(orderId);
    		orderDetail.setSkuId(skuIds[i]);
    		orderDetail.setPrice(prices[i]);
    		orderDetail.setQuantity(quantitys[i]);
    		orderDetail.setTotalPrice(totalPriceDetails[i]+"");
    		orderDetail.setOrderType(orderTypes[i]);
    		orderDetail.setUnitDesc(unitDescs[i]);
    		num2=num2+orderNewService.createOrderDetail(orderDetail);
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
	public String toPrintOrderNew() {
		order = new XppOrder();
		order.setOrderId(Long.valueOf(orderId));
		order = orderNewService.searchOrder(order);
		
		orderDetail=new OrderDetail();
		orderDetail.setOrderId(Long.valueOf(orderId));
		orderDetailList = orderNewService.searchOrderDetailList(orderDetail);
		return "toPrintOrderNew";
	}
	
	
	/**
	 * 批量导出
	 * 
	 * @return
	 */
	public String exportOrderNew() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		order= new XppOrder();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
				order.setOrgIds(orgids.split(","));
		}
		order.setCustName(custName);
		order.setCustId(custId);
		if("null".equals(custState)){
		order.setCustState("");
		}else{
		order.setCustState(custState);
		}
		if("null".equals(custType)){
		order.setCustType("");
		}else{
		order.setCustType(custType);	
		}
		order.setContactName(contactName);
		order.setBeginDate(beginDate);
		order.setEndDate(endDate);
		order.setUserName(userName);
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice)){
			order.setIsOffice(isOffice);
			if (!this.getUser().getLoginId().equals(isOffice)) {
				order.setUserId(this.getUser().getUserId());
			}
		}else{
			List<String> custKunnrs = orderNewService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
			if(custKunnrs!=null){
				String[] kunnrs = new String[custKunnrs.size()];
				for(int i=0;i<custKunnrs.size();i++){
					kunnrs[i]=custKunnrs.get(i);
				}
				order.setOrgIds(null);
				order.setCustKunnrs(kunnrs);
			}
		}
	//	order.setOrgId(orgId);
		
		total = orderNewService.searchOrderCount(order);
		if (total > 0) {
			orderList = orderNewService.orderExport(order);
		}
		if(null!=orderList){
			orderNewService.exportOrder(this.getServletResponse(),orderList);
		}else{
			
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
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
	
	public String searchOrderNewInfoPre() {
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		loginId = this.getUser().getLoginId();
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		List<String> custKunnrs = orderNewService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
		if(custKunnrs!=null){
			for(int i=0;i<custKunnrs.size();i++){
				if(i==0){
					kunnrs=custKunnrs.get(i);
				}else{
					kunnrs=kunnrs+","+custKunnrs.get(i);
				}
			}
		}
		return "searchOrderNewInfo";
	}
	
	/**
	 * 订单报表
	 * 
	 * @return
	 */
	@JsonResult(field = "orderInfoList", include = { "totalPrice","quantity", "realQuantity", "kunnr", "kunnrName", "skuName","unitDesc"}, total = "total")
	public String searchOrderNewInfo() {
		orderInfo= new OrderInfo();
		String orgids = orgServiceHessian.getAllChildOrgidByOrgId(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			orderInfo.setOrgIds(orgids.split(","));
		}
		orderInfo.setSkuId(skuId);
		orderInfo.setStartDate(startTime);
		orderInfo.setEndDate(endTime);
		orderInfo.setStart(getStart());
		orderInfo.setEnd(getEnd());
		isOffice=this.getUser().getIsOffice();
		if(!("").equals(isOffice) && isOffice!=null){
			orderInfo.setIsOffice(isOffice);
		}else{
			List<String> custKunnrs = orderNewService.getKunnrByEmpId(Long.valueOf(this.getUser().getUserId()));
			if(custKunnrs!=null){
				String[] kunnrs = new String[custKunnrs.size()];
				for(int i=0;i<custKunnrs.size();i++){
					kunnrs[i]=custKunnrs.get(i);
				}
				orderInfo.setOrgIds(null);
				orderInfo.setCustKunnrs(kunnrs);
			}
		}
		if(StringUtils.isNotEmpty(kunnr)){
			orderInfo.setIsOffice(kunnr);
		}
		total = orderNewService.getOrderInfoListCount(orderInfo);
		if (total > 0) {
			orderInfoList = orderNewService.getOrderInfoList(orderInfo);
		}
		return JSON;
	}
	
	public String toPay(){
		order = new XppOrder();
		order.setOrderId(Long.parseLong(orderId));
		order = orderNewService.searchOrder(order);
		return "toPay";
	}
	
	@PermissionSearch
	@JsonResult(field = "result")
	public String pay(){
		order = new XppOrder();
		order.setOrderId(Long.parseLong(orderId));
		order = orderNewService.searchOrder(order);
		order.setTotalPrice(totalPrice);
		if("A".equals(payType)){
			result = toB2C(order,cardType);
		}else if("B".equals(payType)){
			result = toB2B(order);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "result")
	public String accountPay(){
		if(account!=null){
			account.setPayType(payType);
			account.setCardType(cardType);
			if("6".equals(cardType)){
				account.setStatus("F");
			}else{
				account.setStatus("N");
			}
			orderNewService.createAccount(account);
			accountId=account.getAccountId();
		}
		Account account1=new Account();
		account1.setAccountId(accountId);
		Account account = orderNewService.searchAccount(account1);
		payType = account.getPayType();
		cardType = account.getCardType();
		if("A".equals(payType)){
			result = toAccountB2C(account,cardType);
		}else if("B".equals(payType)){
			result = toAccountB2C(account,"7");
		}
		return JSON;
	}
	
	public String toB2C(XppOrder order,String cardType){
//		Order tOrder=new Order();
		String result="";
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
//		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
//		String date=sdf1.format(order.getOrderCreateDate());
//		String time=sdf2.format(order.getOrderCreateDate());
//		tOrder.setOrderNo(order.getOrderId().toString());
//		tOrder.setExpiredDate(60);
//		if(order.getOrderDesc()==null){
//			tOrder.setOrderDesc("");
//		}else{
//			tOrder.setOrderDesc(order.getOrderDesc());
//		}
//		tOrder.setOrderDate(date);
//		tOrder.setOrderTime(time);
//		tOrder.setOrderAmount(Double.parseDouble(order.getTotalPrice()));
//		
//		//4、生成支付请求对象
//		PaymentRequest tPaymentRequest = new PaymentRequest();
//		tPaymentRequest.setOrder      (tOrder); //设定支付请求的订单 （必要信息）
//		tPaymentRequest.setProductType("1"); //设定商品种类 （必要信息）
//		                                              //PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、...
//		                                              //PaymentRequest.PRD_TYPE_TWO：实体商品
//		tPaymentRequest.setPaymentType(cardType); //设定支付类型
//		                                              //PaymentRequest.PAY_TYPE_ABC：农行卡支付
//		                                              //PaymentRequest.PAY_TYPE_INT：国际卡支付
//		tPaymentRequest.setNotifyType("1");   //设定商户通知方式
//		                                              //0：URL页面通知
//		                                              //1：服务器通知
////		tPaymentRequest.setResultNotifyURL("http://exptest.zjxpp.com:7186/crmPlatform/orderNewAction!getB2cMsg.jspa"); //设定支付结果回传网址 （必要信息）
//		tPaymentRequest.setResultNotifyURL("http://crm.zjxpp.com:8186/crmPlatform/orderNewAction!getB2cMsg.jspa");
//		tPaymentRequest.setPaymentLinkType("1");//设定支付接入方式
//		//5、传送支付请求并取得支付网址
//		//TrxResponse tTrxResponse = tPaymentRequest.postRequest();
//		TrxResponse tTrxResponse = tPaymentRequest.extendPostRequest(1);
//		if (tTrxResponse.isSuccess()) {
//			   //6、支付请求提交成功，将客户端导向支付页面
//			   result=tTrxResponse.getValue("PaymentURL");
//		}else{
//			result=tTrxResponse.getErrorMessage();
//		}
		return result;
	}
	
	public String toB2B(XppOrder order){
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderId(order.getOrderId());
		orderDetailList = orderNewService.searchOrderDetailList(orderDetail);
		String result;
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
		String date=sdf1.format(order.getOrderCreateDate());
		String time=sdf2.format(order.getOrderCreateDate());
		
		//2、生成TrnxInfo对象
		TrnxItems tTrnxItems = new TrnxItems();  //订单明细
//		tTrnxItems.addTrnxItem(new TrnxItem("0001",     "显",         600.00f,  5));
		for(OrderDetail detail:orderDetailList){
			tTrnxItems.addTrnxItem(new TrnxItem(detail.getOrderDetailId().toString(),detail.getSkuName(),Double.valueOf(detail.getTotalPrice()),Integer.valueOf(detail.getQuantity())));
		}
		
		TrnxRemarks tTrnxRemarks = new TrnxRemarks();  //订单备注
//		tTrnxRemarks.addTrnxRemark(new TrnxRemark("合同号",  "555000000"));
//		tTrnxRemarks.addTrnxRemark(new TrnxRemark("采购时间","2003/11/12 14:23:34"));
		
		TrnxInfo tTrnxInfo = new TrnxInfo();
		tTrnxInfo.setTrnxOpr(order.getUserName());
		tTrnxInfo.setTrnxRemarks(tTrnxRemarks);
		tTrnxInfo.setTrnxItems(tTrnxItems);
		
		//3、生成直接支付请求对象
		FundTransferRequest tFundTransferRequest = new FundTransferRequest();
		tFundTransferRequest.setTrnxInfo(tTrnxInfo);                           //设定交易细项        （必要信息）
		tFundTransferRequest.setMerchantTrnxNo(order.getOrderId().toString());               //设定商户交易编号    （必要信息）
		tFundTransferRequest.setTrnxAmount(Double.parseDouble(order.getTotalPrice()));                       //设定交易金额        （必要信息）
		tFundTransferRequest.setTrnxDate(date);                           //设定交易日期        （必要信息）
		tFundTransferRequest.setTrnxTime(time);                           //设定交易时间        （必要信息）
		tFundTransferRequest.setAccountDBNo("19105101040227050");                     //设定收款方账号      （必要信息）
		tFundTransferRequest.setAccountDBName("香飘飘食品股份有限公司");                 //设定收款方账户名    （必要信息）
		tFundTransferRequest.setAccountDBBank("191051");                 //设定收款方账户开户行联行号（必要信息）
//		tFundTransferRequest.setResultNotifyURL("http://exptest.zjxpp.com:7186/crmPlatform/orderResultAction!getB2bMsg.jspa");             //设定交易结果回传网址（必要信息）
		tFundTransferRequest.setResultNotifyURL("http://crm.zjxpp.com:8186/crmPlatform/orderResultAction!getB2bMsg.jspa");
		tFundTransferRequest.setMerchantRemarks("香飘飘食品股份有限公司");             //设定商户备注信息
		
		//4、传送直接支付请求并取得支付网址
		com.hitrust.b2b.trustpay.client.TrxResponse tTrxResponse = tFundTransferRequest.postRequest();
		if (tTrxResponse.isSuccess()) {
			//5、直接支付请求提交成功,将客户端导向出示买方企业客户证书页面（下面注释的4行程序的参数值商户仍然可以取到）
			//out.println("TrnxType       = [" + tTrxResponse.getValue("TrnxType"  )     + "]<br>");
			//out.println("TrnxAMT        = [" + tTrxResponse.getValue("TrnxAMT"  )      + "]<br>");
			//out.println("MerchantID     = [" + tTrxResponse.getValue("MerchantID"  )   + "]<br>");
			//out.println("MerchantTrnxNo = [" + tTrxResponse.getValue("MerchantTrnxNo") + "]<br>");
			result=tTrxResponse.getValue("PaymentURL");
		}else {
			//6、直接支付请求提交失败，商户自定后续动作
			System.out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
			System.out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
			result=tTrxResponse.getErrorMessage();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String toAccountB2C(Account account,String cardType){
		String result;
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
		String date=sdf1.format(new Date());
		String time=sdf2.format(new Date());
		
		PaymentRequest tPaymentRequest = new PaymentRequest();
		
		tPaymentRequest.dicOrder.put("PayTypeID","ImmediatePay"); //交易类型    ImmediatePay：直接支付  PreAuthPay：预授权支付  DividedPay：分期支付
		tPaymentRequest.dicOrder.put("OrderDate",date);  //订单日期
		tPaymentRequest.dicOrder.put("OrderTime",time);  //订单时间
		tPaymentRequest.dicOrder.put("CurrencyCode","156");  //交易币种   156：人民币
		tPaymentRequest.dicOrder.put("OrderNo",account.getAccountId().toString());  //订单编号
		tPaymentRequest.dicOrder.put("OrderAmount",account.getPrice()+account.getFee());  //交易金额
		tPaymentRequest.dicOrder.put("InstallmentMark","0");  //分期标识  1：分期；0：不分期
		tPaymentRequest.dicOrder.put("CommodityType","0202");  //商品种类    0101:支付账户充值
		tPaymentRequest.dicOrder.put("OrderDesc",account.getKunnr());  //订单说明
		tPaymentRequest.dicOrder.put("ProductExpiredDate","60");  //订单有效期
		
		//生成支付请求对象
		tPaymentRequest.dicRequest.put("PaymentType",cardType);  //支付类型    1：农行卡支付    2：国际卡支付    3：农行贷记卡支付    5：基于第三方的跨行支付    6：银联跨行支付    7：对公户     A:支付方式合并
		tPaymentRequest.dicRequest.put("PaymentLinkType","1");  //交易渠道    1：internet网络接入    2：手机网络接入    3：数字电视网络接入    4：智能客户端
		tPaymentRequest.dicRequest.put("NotifyType","1");  //通知方式    0：URL页面通知    1：服务器通知
		tPaymentRequest.dicRequest.put("IsBreakAccount","0");  //交易是否分账     0:否；1:是
//		tPaymentRequest.dicRequest.put("ResultNotifyURL","http://exptest.zjxpp.com:7186/crmPlatform/orderNewAction!getAccountB2cMsg.jspa");  //通知URL地址
		tPaymentRequest.dicRequest.put("ResultNotifyURL","http://crm.zjxpp.com:8186/crmPlatform/orderNewAction!getAccountB2cMsg.jspa");
		
		//传送支付请求并取得支付网址
		System.out.println(tPaymentRequest.dicRequest.get("NotifyType"));
		com.abc.pay.client.JSON json = tPaymentRequest.postRequest();
		if ("0000".equals(json.GetKeyValue("ReturnCode"))) {
			   //6、支付请求提交成功，将客户端导向支付页面
			   result=json.GetKeyValue("PaymentURL");
		}else{
			result=json.GetKeyValue("ErrorMessage");
			System.out.println(result);
		}
		return result;
	}
	
	public String toAccountB2B(Account account){
		String result;
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
		String date=sdf1.format(new Date());
		String time=sdf2.format(new Date());
		
		//2、生成TrnxInfo对象
		TrnxItems tTrnxItems = new TrnxItems();  //订单明细
//		tTrnxItems.addTrnxItem(new TrnxItem("0001",     "显",         600.00f,  5));
		
		TrnxRemarks tTrnxRemarks = new TrnxRemarks();  //订单备注
		tTrnxRemarks.addTrnxRemark(new TrnxRemark("经销商编号", account.getKunnr()));
//		tTrnxRemarks.addTrnxRemark(new TrnxRemark("合同号",  "555000000"));
//		tTrnxRemarks.addTrnxRemark(new TrnxRemark("采购时间","2003/11/12 14:23:34"));
		
		TrnxInfo tTrnxInfo = new TrnxInfo();
		tTrnxInfo.setTrnxOpr(account.getUserId());
		tTrnxInfo.setTrnxRemarks(tTrnxRemarks);
		tTrnxInfo.setTrnxItems(tTrnxItems);
		
		//3、生成直接支付请求对象
		FundTransferRequest tFundTransferRequest = new FundTransferRequest();
		tFundTransferRequest.setTrnxInfo(tTrnxInfo);                           //设定交易细项        （必要信息）
		tFundTransferRequest.setMerchantTrnxNo(account.getAccountId().toString());               //设定商户交易编号    （必要信息）
		tFundTransferRequest.setTrnxAmount(account.getPrice()+account.getFee());                       //设定交易金额        （必要信息）
		tFundTransferRequest.setTrnxDate(date);                           //设定交易日期        （必要信息）
		tFundTransferRequest.setTrnxTime(time);                           //设定交易时间        （必要信息）
		tFundTransferRequest.setAccountDBNo("19105101040227050");                     //设定收款方账号      （必要信息）
		tFundTransferRequest.setAccountDBName("香飘飘食品股份有限公司");                 //设定收款方账户名    （必要信息）
		tFundTransferRequest.setAccountDBBank("191051");                 //设定收款方账户开户行联行号（必要信息）
//		tFundTransferRequest.setResultNotifyURL("http://exptest.zjxpp.com:7186/crmPlatform/orderResultAction!getAccountB2bMsg.jspa");             //设定交易结果回传网址（必要信息）
		tFundTransferRequest.setResultNotifyURL("http://crm.zjxpp.com:8186/crmPlatform/orderResultAction!getAccountB2bMsg.jspa");
		tFundTransferRequest.setMerchantRemarks("EXP");             //设定商户备注信息
		
		//4、传送直接支付请求并取得支付网址
		com.hitrust.b2b.trustpay.client.TrxResponse tTrxResponse = tFundTransferRequest.postRequest();
		if (tTrxResponse.isSuccess()) {
			//5、直接支付请求提交成功,将客户端导向出示买方企业客户证书页面（下面注释的4行程序的参数值商户仍然可以取到）
			//out.println("TrnxType       = [" + tTrxResponse.getValue("TrnxType"  )     + "]<br>");
			//out.println("TrnxAMT        = [" + tTrxResponse.getValue("TrnxAMT"  )      + "]<br>");
			//out.println("MerchantID     = [" + tTrxResponse.getValue("MerchantID"  )   + "]<br>");
			//out.println("MerchantTrnxNo = [" + tTrxResponse.getValue("MerchantTrnxNo") + "]<br>");
			result=tTrxResponse.getValue("PaymentURL");
		}else {
			//6、直接支付请求提交失败，商户自定后续动作
			System.out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
			System.out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
			result=tTrxResponse.getErrorMessage();
		}
		return result;
	}
	
	public String getB2cMsg(){
		try {
			order = new XppOrder();
			System.out.println("MSG="+MSG);
			PaymentResult tResult=new PaymentResult(MSG);
			if (tResult.isSuccess()) {
				System.out.println("支付成功");
//				this.setSuccessMessage("支付成功");
				order.setOrderId(Long.parseLong(tResult.getValue("OrderNo")));
				System.out.println("交易号:"+order.getOrderId());
				order.setOrderFundsStatus("Y");
				orderNewService.updateOrder(order, orderDetailList);
			}
			else {
				System.out.println(tResult.getErrorMessage());
				this.setFailMessage("支付失败,"+tResult.getErrorMessage());
			}
			return RESULT_MESSAGE;
		} catch (TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String getAccountB2cMsg(){
		try {
			account = new Account();
			System.out.println("MSG="+MSG);
			PaymentResult tResult=new PaymentResult(MSG);
			if (tResult.isSuccess()) {
				account.setAccountId((Long.parseLong(tResult.getValue("OrderNo"))));
				account = orderNewService.searchAccount(account);
				account.setStatus("Y");
				account.setSuccessNum(tResult.getValue("iRspRef"));
				account.setSuccessDate(new Date());
//				if(StringUtils.isEmpty(account.getInSap())){
//					String inSap = orderNewService.OrderSuccessRFC(account);
//					account.setInSap(inSap);
//				}
				orderNewService.updateAccount(account);
				return "orderSuccess";
			}
			else {
				errorMessage = tResult.getErrorMessage();
				return "orderError";
			}
		} catch (TrxException e) {
			e.printStackTrace();
			return "orderError";
		}
		
	}
	
	@JsonResult(field = "account", include = { "dk_netwr","fy_netwr", "zk_netwr"})
	public String getBalance(){
		List<String> kunnrTmp=new ArrayList<String>();
		kunnrTmp.add(this.getUser().getIsOffice());
		accountList = orderNewService.getBalance(kunnrTmp);
		if(accountList!=null && accountList.size()>0){
			account=accountList.get(0);
		}
		return JSON;
	}
	
	@JsonResult(field = "accountList", include = {"kunnr", "kunnrName",
			"dk_netwr","fy_netwr", "zk_netwr"}, total = "total")
	public String searchKunnrBalance() {
		int count=orderNewService.getUserStationBySjjlCount(this.getUser().getUserId());
		accountList=new ArrayList<Account>();
		if(count>0){
			List<String> kunnrTmp = kunnrBusinessService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
			if(kunnrTmp==null || kunnrTmp.size()==0){
				kunnrTmp = kunnrBusinessService.getKunnrIdByCompetent(this.getUser().getUserId());
			}
			total=kunnrTmp.size();
			accountList=orderNewService.getBalance(kunnrTmp);
			for(Account acc:accountList){
				Kunnr k=new Kunnr();
				k.setKunnr(acc.getKunnr());
				k.setStart(0);
				k.setEnd(10);
				k=kunnrService.kunnrSearch(k).get(0);
				acc.setKunnrName(k.getName1());
			}
		}else{
			Kunnr k=new Kunnr();
			if(StringUtils.isEmpty(orgId)){
				k.setOrgId(this.getUser().getOrgId());
			}else{
				k.setOrgId(orgId);
			}
			k.setBhxjFlag("Y");
			k.setKunnr(custId);
			k.setName1(custName);
			k.setStart(getStart());
			k.setEnd(getEnd());
			total=kunnrService.kunnrSearchCount(k);
			if(total>0){
				kunnrList=kunnrService.kunnrSearch(k);
				List<String> kunnrTmp=new ArrayList<String>();
				for(Kunnr kunnr:kunnrList){
					kunnrTmp.add(kunnr.getKunnr());
				}
				accountList=orderNewService.getBalance(kunnrTmp);
				for(Account acc:accountList){
					for(Kunnr kunnr:kunnrList){
						if(acc.getKunnr().equals(kunnr.getKunnr())){
							acc.setKunnrName(kunnr.getName1());
							continue;
						}
					}
				}
			}
		}
		return JSON;
	}
	
    @SuppressWarnings("unchecked")
	public void checkOrderResult(List<Account> list1){
    	for(int i=0;i<list1.size();i++){
    		if(("N".equals(list1.get(i).getStatus()) || "F".equals(list1.get(i).getStatus()))
    				&& StringUtils.isEmpty(list1.get(i).getInSap()) 
    				&& "A".equals(list1.get(i).getPayType())){
    			QueryOrderRequest tRequest = new QueryOrderRequest();
    			tRequest.queryRequest.put("PayTypeID", "ImmediatePay");
    			tRequest.queryRequest.put("OrderNo", list1.get(i).getAccountId()+"");
    			tRequest.queryRequest.put("QueryDetail", "0");
    			
    			com.abc.pay.client.JSON json = tRequest.postRequest();
    			
    			if("0000".equals(json.GetKeyValue("ReturnCode"))){
    				String orderInfo = json.GetKeyValue("Order");
    				Base64 tBase64 = new Base64();
    		  		String orderDetail = new String(tBase64.decode(orderInfo));
    		  		json.setJsonString(orderDetail);
    		  		String status=json.GetKeyValue("Status");
    		  		if("04".equals(status)){
    		  			list1.get(i).setStatus("Y");
    		  			list1.get(i).setSuccessDate(new Date());
//    		  			String inSap = orderNewService.OrderSuccessRFC(list1.get(i));
//    		  			list1.get(i).setInSap(inSap);
    		  			orderNewService.updateAccount(list1.get(i));
    		  		}
    			}
    		}
    	}
	}
    
    public String refundAccount(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
    	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
    	SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
    	String refundId=sdf.format(new Date())+"02";
    	RefundRequest rRequest=new RefundRequest();
    	
    	rRequest.dicRequest.put("OrderDate", sdf1.format(new Date()));  //订单日期（必要信息）
        rRequest.dicRequest.put("OrderTime", sdf2.format(new Date())); //订单时间（必要信息）
        rRequest.dicRequest.put("OrderNo", accountId+""); //原交易编号（必要信息）
        rRequest.dicRequest.put("NewOrderNo", refundId); //交易编号（必要信息）
        rRequest.dicRequest.put("CurrencyCode", "156"); //交易币种（必要信息）
        rRequest.dicRequest.put("TrxAmount", totalPrice); //退货金额 （必要信息）
    	
        com.abc.pay.client.JSON json = rRequest.postRequest();
    	
    	if("0000".equals(json.GetKeyValue("ReturnCode"))){
    		this.setSuccessMessage("退款成功");
    		account = new Account();
    		account.setAccountId(accountId);
    		account.setAccountRefundId(Long.parseLong(refundId));
    		account.setStatus("R");
    		orderNewService.updateAccount(account);
    	}else{
    		System.out.println(json.GetKeyValue("ErrorMessage"));
    		this.setFailMessage("退款失败<br>错误信息："+json.GetKeyValue("ErrorMessage"));
    	}
    	
    	return RESULT_MESSAGE;
    	
    }

	public String getB2bMsg(){
		try {
			System.out.println("MSG="+MSG);
			String msg=ServletActionContext.getRequest().getParameter("MSG");
			MSG=msg;
			System.out.println("MSG="+MSG);
			TrnxResult tResult=new TrnxResult(MSG);
			if (tResult.isSuccess()) {
				System.out.println("支付成功");
//				this.setSuccessMessage("支付成功");
				order.setOrderId(Long.parseLong(tResult.getValue("MerchantTrnxNo")));
				System.out.println(order.getOrderId());
				order.setOrderFundsStatus("Y");
				orderNewService.updateOrder(order, orderDetailList);
			}
			else {
				System.out.println(tResult.getErrorMessage());
				this.setFailMessage("支付失败,"+tResult.getErrorMessage());
			}
			return "getB2bMsg";
		} catch (com.hitrust.b2b.trustpay.client.TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String toPrintAccount() {
		String[] ids = accountIds.split(",");
		accountList=new ArrayList<Account>();
		for (int i = 0; i < ids.length; i++) {
			Account ac=new Account();
			account =new Account();
			account.setAccountId(Long.parseLong(ids[i]));
			ac=orderNewService.searchAccount(account);
			accountList.add(ac);
		}
		return "toPrintAccount";
	}
	
	public String toPrintFormatList(){
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toPrintFormatList";
	}
	
	
	
	
	@JsonResult(field = "printFormatList", include = {"format_id","kunnr"
			,"properties_code","type","order_desc","operator"
			,"create_date","modify_date","properties_name","kunnr_name","operator_name","memo"}, total = "total")	
	public String getPrintFormatInfo(){
		PrintFormat printFormat = new PrintFormat();
		printFormat.setStart(getStart());
		printFormat.setEnd(getEnd());
		if(null!=this.getUser().getIsOffice()){
			if(StringUtils.isNotEmpty(kunnr)){
				printFormat.setKunnr(kunnr);
			}
		}else{
			if(StringUtils.isNotEmpty(orgId)){
				printFormat.setOrgId(orgId);
			}
			if(StringUtils.isNotEmpty(kunnr)){
				printFormat.setKunnr(kunnr);
			}
			if(StringUtils.isNotEmpty(kunnr_name)){
				printFormat.setKunnr_name(kunnr_name);
			}
			if (StringUtils.isNotEmpty(bhxjFlag)) {
				printFormat.setBhxjFlag(bhxjFlag);
			}
		}
		total = orderNewService.getPrintFormatListCount(printFormat);
		if (total != 0) {
			printFormatList = orderNewService.getPrintFormatList(printFormat);
		}
		return JSON;
	}
	
	
	public String toModifyFormatPre(){
		PrintFormat IprintFormat = new PrintFormat();
		if(format_id!=null){
			IprintFormat.setStart(0);
			IprintFormat.setEnd(100);
			IprintFormat.setFormat_id(format_id);
			printFormatList = orderNewService.getPrintFormatList(IprintFormat);
			printFormat = printFormatList.get(0);	
		}
		orgId = this.getUser().getOrgId();
		if(null!=this.getUser().getIsOffice()){
			isOffice=this.getUser().getIsOffice();
			}else{
				isOffice="";
			}
		orgName = orgServiceHessian
				.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toModifyFormatPre";
	}
	
	
	@JsonResult(field = "printFormatList", include = {"properties_code","properties_name"})
	public String getPropertiesJSON(){
		printFormatList = orderNewService.getPropertiesJSON();
		return JSON;
	}
	
	
	/**
	 * 
	 * 經銷商查询结果
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "kunnrList", include = { "kunnr", "name1","orgId", "orgName" }, total = "total")
	public String kunnrSearch() {
		Kunnr kunnr = new Kunnr();
		kunnr.setStart(getStart());
		kunnr.setEnd(getEnd());
		if (StringUtils.isNotEmpty(orgId)) {
				kunnr.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(bhxjFlag)) {
			kunnr.setBhxjFlag(bhxjFlag);
		}
		if (StringUtils.isNotEmpty(status)) {
			kunnr.setStatus(status);
		}
		try {
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				value = new String(this.getValue().getBytes("ISO8859-1"),
						"utf-8");
				// String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				boolean isNum = value
						.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");// 判断是否为汉字
				if (isNum) {
					kunnr.setName1(value.trim());
				} else {
					kunnr.setKunnr(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		total = kunnrService.kunnrSearchCount(kunnr);
		if (total != 0) {
			kunnrList = orderNewService.kunnrSearch(kunnr);
		}
		return JSON;
	}
	
	public String modifyFormat(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		if(printFormat==null){
			this.setFailMessage("修改失败！");
		}
		//验证单据显示内容是否重复
		PrintFormat IprintFormat = new PrintFormat();
		IprintFormat.setStart(0);
		IprintFormat.setEnd(100);
		IprintFormat.setKunnr(printFormat.getKunnr());
		IprintFormat.setProperties_code(printFormat.getProperties_code());
		List<PrintFormat> printFormatList = orderNewService.getPrintFormatList(IprintFormat);
		if(printFormatList!=null&&printFormatList.size()>0){
			if(!printFormatList.get(0).getFormat_id().equals(printFormat.getFormat_id())){
				this.setFailMessage("单据显示内容不得重复！");
				return RESULT_MESSAGE;
			}
		}
		
		printFormat.setOperator(this.getUser().getUserId());
		StringResult stringResult = orderNewService.modifyFormat(printFormat);
		if("success".equals(stringResult.getCode())){
			this.setSuccessMessage("修改成功！");
		}else{
			this.setFailMessage("修改失败！");
		}
		return RESULT_MESSAGE;
	}
	
	public String createFormat(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		if(printFormat==null){
			this.setFailMessage("新增失败！");
		}
		//验证单据显示内容是否重复
		PrintFormat IprintFormat = new PrintFormat();
		IprintFormat.setKunnr(printFormat.getKunnr());
		IprintFormat.setProperties_code(printFormat.getProperties_code());
		Integer count = orderNewService.getPrintFormatListCount(IprintFormat);
		if(count>0){
			this.setFailMessage("单据显示内容不得重复！");
			return RESULT_MESSAGE;
		}
		
		printFormat.setStatus("U");
		printFormat.setOperator(this.getUser().getUserId());
		StringResult stringResult = orderNewService.createFormat(printFormat);
		if("success".equals(stringResult.getCode())){
			this.setSuccessMessage("新增成功！");
		}else{
			this.setFailMessage("新增失败！");
		}
		return RESULT_MESSAGE;
	}
	
	
	/**
	 * 批量删除打印格式
	 * @return
	 */
	public String deleteFormat() {
		setSuccessMessage("");
		setFailMessage("");
		PrintFormat printFormat = new PrintFormat();
		String[] l = this.ids.split(",");
		printFormat.setFormat_ids(l);
		printFormat.setStatus("N");
		StringResult stringResult = orderNewService.modifyFormat(printFormat);
		if ("success".equals(stringResult.getCode()))
			setSuccessMessage("已成功删除" + stringResult.getResult() + "条数据!");
		else {
			setFailMessage("批量删除失败！");
		}
		return "resultMessage";
	}
	
	public String toFormatView() {
		PrintFormat IprintFormat = new PrintFormat();
		IprintFormat.setStart(0);
		IprintFormat.setEnd(100);
		IprintFormat.setFormat_id(format_id);
		printFormatList = orderNewService.getPrintFormatList(IprintFormat);
		printFormat = printFormatList.get(0);
		IprintFormat = new PrintFormat();
		IprintFormat.setStart(0);
		IprintFormat.setEnd(100);
		IprintFormat.setKunnr(printFormat.getKunnr());
		printFormatList = orderNewService.getPrintFormatList(IprintFormat);
		PrintFormat price = null;
		for(PrintFormat OprintFormat:printFormatList){
			if("list_skuPrice".equals(OprintFormat.getProperties_code())){
				price = OprintFormat;
			}
		}
		out = "";
		boolean flagT = true;
		boolean flagH = true;
		boolean flagL = true;
		boolean flagB = true;
		for(PrintFormat OprintFormat:printFormatList){
			if("T".equals(OprintFormat.getType())){
				flagT = false;
				out+="<h3 align='center'>"+OprintFormat.getRemark()+"</h3><ul style='list-style:none'>";
			}
			if(flagT){
				out="<h3 align='center'>主题类型必须排第一位！</h3>";
				return "toFormatView";
			}
			if("H".equals(OprintFormat.getType())){
				if(flagH){
					out+="<li>"+OprintFormat.getProperties_name()+":"+OprintFormat.getRemark()+"</li>";
				}else{
					out="<h3 align='center'>抬头类型排序只能低于主题类型！</h3>";
					return "toFormatView";
				}
			}
			if("L".equals(OprintFormat.getType())){
				if(flagH){
					out+="<li>--------------------------------------------</li>";
				}
				flagH = false;
				if(flagL){
					if("list_quantity".equals(OprintFormat.getProperties_code())){
						String p = "";
						if(price!=null){
							p = price.getRemark();
						}
						out+="<li>"+OprintFormat.getRemark()+"×"+p+"</li>";
					}else if("list_skuPrice".equals(OprintFormat.getProperties_code())){
						
					}else{
						out+="<li>"+OprintFormat.getProperties_name()+":"+OprintFormat.getRemark()+"</li>";
					}
				}else{
					out="<h3 align='center'>SKU清单类型排序要高于合计及页脚类型！</h3>";
					return "toFormatView";
				}
			}
			if("B".equals(OprintFormat.getType())){
				if(flagL){
					out+="<li>--------------------------------------------</li>";
				}
				flagH = false;
				flagL = false;
				if(flagB){
					out+="<li>"+OprintFormat.getProperties_name()+":"+OprintFormat.getRemark()+"</li>";
				}else{
					out="<h3 align='center'>合计类型排序要高于页脚类型！</h3>";
					return "toFormatView";
				}
			}
			if("F".equals(OprintFormat.getType())){
				flagH = false;
				flagL = false;
				flagB = false;
				out+="<br><li><div style='width:360px;'>"+OprintFormat.getMemo()+"</div></li>";
			}
			
		}
		return "toFormatView";
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

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
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

	public List<AllUsers> getEmpList() {
		return empList;
	}

	public void setEmpList(List<AllUsers> empList) {
		this.empList = empList;
	}
	public IOrderNewService getOrderNewService() {
		return orderNewService;
	}


	public void setOrderNewService(IOrderNewService orderNewService) {
		this.orderNewService = orderNewService;
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


	public String getMSG() {
		return MSG;
	}


	public void setMSG(String mSG) {
		MSG = mSG;
	}


	public String getResult() {
		return result;
	}


	public String getKunnrId() {
		return kunnrId;
	}


	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}


	public String getName2() {
		return name2;
	}


	public void setName2(String name2) {
		this.name2 = name2;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getCarType() {
		return carType;
	}


	public void setCarType(String carType) {
		this.carType = carType;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getPayType() {
		return payType;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public List<Account> getAccountList() {
		return accountList;
	}


	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IKunnrBusinessService getKunnrBusinessService() {
		return kunnrBusinessService;
	}

	public void setKunnrBusinessService(IKunnrBusinessService kunnrBusinessService) {
		this.kunnrBusinessService = kunnrBusinessService;
	}

	public String getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(String accountIds) {
		this.accountIds = accountIds;
	}

	public void setPrintFormatList(List<PrintFormat> printFormatList) {
		this.printFormatList = printFormatList;
	}

	public Integer getFormat_id() {
		return format_id;
	}

	public void setFormat_id(Integer format_id) {
		this.format_id = format_id;
	}

	public List<PrintFormat> getPrintFormatList() {
		return printFormatList;
	}

	public PrintFormat getPrintFormat() {
		return printFormat;
	}

	public void setPrintFormat(PrintFormat printFormat) {
		this.printFormat = printFormat;
	}

	public List<Kunnr> getKunnrList() {
		return kunnrList;
	}

	public void setKunnrList(List<Kunnr> kunnrList) {
		this.kunnrList = kunnrList;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKunnr_name() {
		return kunnr_name;
	}

	public void setKunnr_name(String kunnr_name) {
		this.kunnr_name = kunnr_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}





}
