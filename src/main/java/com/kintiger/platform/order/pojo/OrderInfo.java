package com.kintiger.platform.order.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class OrderInfo extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3869041420315152115L;
	
	private String totalPrice;
	private String quantity;
	private String realQuantity;
	private String kunnr;
	private String kunnrName;
	private String skuName;
	private String skuId;
	private String[] orgIds;
	private String isOffice;
	private String[] custKunnrs;
	private String unitDesc;
	private Date startDate;
	private Date endDate;
	private String userId;
	private String userName;
	private String stationName;
	private String skuCode;
	private String custName;
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRealQuantity() {
		return realQuantity;
	}
	public void setRealQuantity(String realQuantity) {
		this.realQuantity = realQuantity;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String[] getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}
	public String getIsOffice() {
		return isOffice;
	}
	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}
	public String[] getCustKunnrs() {
		return custKunnrs;
	}
	public void setCustKunnrs(String[] custKunnrs) {
		this.custKunnrs = custKunnrs;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	

}
