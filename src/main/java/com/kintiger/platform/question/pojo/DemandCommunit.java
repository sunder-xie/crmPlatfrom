package com.kintiger.platform.question.pojo;


import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class DemandCommunit extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 309380545868675471L;
	
	private Long demandId;
	private Long communitId;
	private Long userId;
	private String userName;
	private String content;
	private String orgName;
	private Date createDate;
	public Long getDemandId() {
		return demandId;
	}
	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}
	public Long getCommunitId() {
		return communitId;
	}
	public void setCommunitId(Long communitId) {
		this.communitId = communitId;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
}
