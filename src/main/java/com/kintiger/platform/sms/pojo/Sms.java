package com.kintiger.platform.sms.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Sms extends SearchInfo{

	private static final long serialVersionUID = -3585696598781280339L;
	private String userName;
	private String mobile;
	private String orgId;
	private String orgName;
	private String stationId;
	private String stationName;
	private String isOffice;
	private String bhxjFlag;
	private Integer userId;
	private String bus_mobilephone;
	private String pri_mobilephone;
	private String kunnr;
	private String groupName;
	private Integer opUserId;
	private String isPubGroup;
	

	

	public String getBhxjFlag() {
		return bhxjFlag;
	}
	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getIsOffice() {
		return isOffice;
	}
	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBus_mobilephone() {
		return bus_mobilephone;
	}
	public void setBus_mobilephone(String bus_mobilephone) {
		this.bus_mobilephone = bus_mobilephone;
	}
	public String getPri_mobilephone() {
		return pri_mobilephone;
	}
	public void setPri_mobilephone(String pri_mobilephone) {
		this.pri_mobilephone = pri_mobilephone;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(Integer opUserId) {
		this.opUserId = opUserId;
	}
	public String getIsPubGroup() {
		return isPubGroup;
	}
	public void setIsPubGroup(String isPubGroup) {
		this.isPubGroup = isPubGroup;
	}


	
}
