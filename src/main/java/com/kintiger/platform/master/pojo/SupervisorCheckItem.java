package com.kintiger.platform.master.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class SupervisorCheckItem extends SearchInfo{
	
	private Long checkId;
	private String custName;
	private String channelName;
	private String custId;
	private String orgName;
	private String channelId;
	private String createUserId;
	private String createName;
	private String createTime;
	
	private String matId;
	private String matName;
	private String matType;
	
	private String checkValue;
	private String minPrice;
	private String maxPrice;


	public SupervisorCheckItem() {
	}
	public SupervisorCheckItem(Long checkId, String checkValue,String minPrice,String maxPrice) {
		this.checkId = checkId;
		this.checkValue = checkValue;
	this.minPrice = minPrice;
	this.maxPrice = maxPrice;
	}


	public Long getCheckId() {
		return checkId;
	}


	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getChannelName() {
		return channelName;
	}


	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}


	public String getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}


	public String getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	public String getCreateName() {
		return createName;
	}


	public void setCreateName(String createName) {
		this.createName = createName;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getMatId() {
		return matId;
	}


	public void setMatId(String matId) {
		this.matId = matId;
	}


	public String getMatName() {
		return matName;
	}


	public void setMatName(String matName) {
		this.matName = matName;
	}


	public String getCheckValue() {
		return checkValue;
	}


	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}


	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getChannelId() {
		return channelId;
	}


	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	public String getMatType() {
		return matType;
	}


	public void setMatType(String matType) {
		this.matType = matType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
