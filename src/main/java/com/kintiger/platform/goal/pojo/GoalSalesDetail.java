package com.kintiger.platform.goal.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class GoalSalesDetail extends SearchInfo implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5588441001017023455L;
    
	private Long detailId;
	private Long detailIdTo;
	private Long changeId;
	private Long orgId;
	private String orgName;
	private String kunnr;
	private String kunnrName;
	private String yearMonth;
	private String year;
	private String month;
	private Long orgIdTo;
	private String orgNameTo;
	private String kunnrTo;
	private String kunnrNameTo;
	private String yearMonthTo;
	private Long cgId;
	private String cgName;
	private String matter;
    private String matterName;
	private Double quantity;
	private Long changeOrgId;
	private String changeOrgName;
	private Long changeOrgIdTo;
	private String changeOrgNameTo;
	private String ctId;
	private Double boxNum;
	
	private Long eventId;
	private String eventState;
	private String title;
	private String userName;
	private String curUserName;
	private String startDate;
	private String endDate;
	private String createDate;
	private String userId;
	private String brand;
	private String maktx01;//Æ·ÅÆ
	public GoalSalesDetail clone(){ 
		GoalSalesDetail o = null; 
		try{ 
			o = (GoalSalesDetail)super.clone(); 
		}catch(CloneNotSupportedException e){ 
			e.printStackTrace(); 
		} 
		return o; 
	} 
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getOrgNameTo() {
		return orgNameTo;
	}
	public void setOrgNameTo(String orgNameTo) {
		this.orgNameTo = orgNameTo;
	}
	public String getKunnrTo() {
		return kunnrTo;
	}
	public void setKunnrTo(String kunnrTo) {
		this.kunnrTo = kunnrTo;
	}
	public String getKunnrNameTo() {
		return kunnrNameTo;
	}
	public void setKunnrNameTo(String kunnrNameTo) {
		this.kunnrNameTo = kunnrNameTo;
	}
	public String getYearMonthTo() {
		return yearMonthTo;
	}
	public void setYearMonthTo(String yearMonthTo) {
		this.yearMonthTo = yearMonthTo;
	}
	public Long getCgId() {
		return cgId;
	}
	public void setCgId(Long cgId) {
		this.cgId = cgId;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Long getChangeId() {
		return changeId;
	}
	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public Long getDetailIdTo() {
		return detailIdTo;
	}
	public void setDetailIdTo(Long detailIdTo) {
		this.detailIdTo = detailIdTo;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getOrgIdTo() {
		return orgIdTo;
	}
	public void setOrgIdTo(Long orgIdTo) {
		this.orgIdTo = orgIdTo;
	}
	public String getCgName() {
		return cgName;
	}
	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	public Long getChangeOrgId() {
		return changeOrgId;
	}
	public void setChangeOrgId(Long changeOrgId) {
		this.changeOrgId = changeOrgId;
	}
	public String getChangeOrgName() {
		return changeOrgName;
	}
	public void setChangeOrgName(String changeOrgName) {
		this.changeOrgName = changeOrgName;
	}
	public String getCtId() {
		return ctId;
	}
	public void setCtId(String ctId) {
		this.ctId = ctId;
	}
	public Double getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Double boxNum) {
		this.boxNum = boxNum;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCurUserName() {
		return curUserName;
	}
	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}
	public Long getChangeOrgIdTo() {
		return changeOrgIdTo;
	}
	public void setChangeOrgIdTo(Long changeOrgIdTo) {
		this.changeOrgIdTo = changeOrgIdTo;
	}
	public String getChangeOrgNameTo() {
		return changeOrgNameTo;
	}
	public void setChangeOrgNameTo(String changeOrgNameTo) {
		this.changeOrgNameTo = changeOrgNameTo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "GoalSalesDetail [detailId=" + detailId + ", detailIdTo="
				+ detailIdTo + ", changeId=" + changeId + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", kunnr=" + kunnr + ", kunnrName="
				+ kunnrName + ", yearMonth=" + yearMonth + ", year=" + year
				+ ", month=" + month + ", orgIdTo=" + orgIdTo + ", orgNameTo="
				+ orgNameTo + ", kunnrTo=" + kunnrTo + ", kunnrNameTo="
				+ kunnrNameTo + ", yearMonthTo=" + yearMonthTo + ", cgId="
				+ cgId + ", cgName=" + cgName + ", matter=" + matter
				+ ", matterName=" + matterName + ", quantity=" + quantity
				+ ", changeOrgId=" + changeOrgId + ", changeOrgName="
				+ changeOrgName + ", changeOrgIdTo=" + changeOrgIdTo
				+ ", changeOrgNameTo=" + changeOrgNameTo + ", ctId=" + ctId
				+ ", boxNum=" + boxNum + ", eventId=" + eventId
				+ ", eventState=" + eventState + ", title=" + title
				+ ", userName=" + userName + ", curUserName=" + curUserName
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", createDate=" + createDate + ", userId=" + userId + "]";
	}
	public String getMaktx01() {
		return maktx01;
	}

	public void setMaktx01(String maktx01) {
		this.maktx01 = maktx01;
	}

	
}
