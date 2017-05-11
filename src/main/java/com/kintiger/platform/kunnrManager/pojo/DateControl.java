package com.kintiger.platform.kunnrManager.pojo;

import java.io.Serializable;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class DateControl extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7314514559454904122L;
	
	private Integer id;
	private String checkTime;
	private Date startDate;
	private Date endDate;
	private Date modifyDate;
	private String startDateStr;
	private String endDateStr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
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
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	

}
