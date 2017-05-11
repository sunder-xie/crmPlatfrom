package com.kintiger.platform.order.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class OrderDetail  extends SearchInfo  {

/**
	 * 
	 */
	private static final long serialVersionUID = 8630134975962086156L;
	//	private String orderId;
//	private String custId;
//	private String userId;
//    private String totalPrice;
//    private String totalPricaeUniteCode;
//    private String totalPricaeUniteDesc;
//    private String orderDesc;
//    private String ordetrQuntity;
//    private String cust_id;
//    private String org_id;
//    private String status;
    private String modifyDate;
    private Long orderId;
    private String plannedDeliveryDate;
    private String realDeliveryDate;
    private String lastModifyUser ;
    private String remark;
    private Long orderDetailId;
    private String quantity;
    private String realQuantity;
    private String unitCode;
    private String unitDesc;
    private String totalPrice;
    private String DtotalPrice;
    private String priceUnitCode;
    private String priceUnitDesc;
    private String price;  
    private String orderDetailStatus;
    private String type;
    private String orderType;
    private String skuId;
    private String skuName;
    private String skuCode;
    
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(String plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}
	public String getRealDeliveryDate() {
		return realDeliveryDate;
	}
	public void setRealDeliveryDate(String realDeliveryDate) {
		this.realDeliveryDate = realDeliveryDate;
	}
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public String getPriceUnitCode() {
		return priceUnitCode;
	}
	public void setPriceUnitCode(String priceUnitCode) {
		this.priceUnitCode = priceUnitCode;
	}
	public String getPriceUnitDesc() {
		return priceUnitDesc;
	}
	public void setPriceUnitDesc(String priceUnitDesc) {
		this.priceUnitDesc = priceUnitDesc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrderDetailStatus() {
		return orderDetailStatus;
	}
	public void setOrderDetailStatus(String orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(String realQuantity) {
		this.realQuantity = realQuantity;
	}
	public String getDtotalPrice() {
		return DtotalPrice;
	}
	public void setDtotalPrice(String dtotalPrice) {
		DtotalPrice = dtotalPrice;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
}
