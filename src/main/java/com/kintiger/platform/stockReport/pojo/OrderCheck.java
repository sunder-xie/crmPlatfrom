package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class OrderCheck extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7193816263327305530L;
	
	private Integer checkId;
	private String kunnr;
	private String kunnrName;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private String kunnrStatus;
	private Long orgId;
	private String orgIds;//多个组织id
	private Integer checkMonth;
	private Integer checkYear;
	private String checkDesc;
	private String state;
	private String createDate;
	private String checkUser;
	private String stationId;
	private String lastCheckDate;
	private String startDate;
	private String endDate;
	private String ip;//登录的ip
	private String otherIp;//调用 搜狐网络获取的ip
	
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public Integer getCheckMonth() {
		return checkMonth;
	}
	public void setCheckMonth(Integer checkMonth) {
		this.checkMonth = checkMonth;
	}
	public Integer getCheckYear() {
		return checkYear;
	}
	public void setCheckYear(Integer checkYear) {
		this.checkYear = checkYear;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(String lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	public String getKunnrStatus() {
		return kunnrStatus;
	}
	public void setKunnrStatus(String kunnrStatus) {
		this.kunnrStatus = kunnrStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrgRegion() {
		return orgRegion;
	}
	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}
	public String getOrgProvince() {
		return orgProvince;
	}
	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getOtherIp() {
		return otherIp;
	}
	public void setOtherIp(String otherIp) {
		this.otherIp = otherIp;
	}

}
