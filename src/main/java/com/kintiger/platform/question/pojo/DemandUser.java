package com.kintiger.platform.question.pojo;


import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class DemandUser extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 309380545868675471L;
	private Long userId;
	private Long demandId;
	private String userName;
	private String orgName;
	private String state;
	private String expectDate;
	private Date createDate;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExpectDate() {
		return expectDate;
	}
	public void setExpectDate(String expectDate) {
		this.expectDate = expectDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getDemandId() {
		return demandId;
	}
	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}
	
	
	
	
	
	
	
}
