package com.kintiger.platform.custvist.pojo;



import java.io.Serializable;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;



public class BCustVwDetail extends SearchInfo implements Serializable {

	private static final long serialVersionUID = -7785837765565726918L;
	private String custDetailId;		//客户明细id
	private String posId;				//职位ID
	private String orgId;				//组织ID
	private String empName;			//业代姓名
	private String custNumber;		//客户代码
	private String custNameZH;		//客户名称
	private String custAddress;		//客户地址
	private String channelName;		//渠道名称
	private String itemName;		//拜访频次
	private String weekDay;			//拜访日程(1-6对应星期一到星期六)
	private String visitTime;		//拜访时间
	private String visitOrder;		//拜访次序
	private String posName;
	private String custOrgName;
	private String contactPhone;
	private String contactName;
	private String custLevelName;   //客户等级
	
	
	public String getCustDetailId() {
		return custDetailId;
	}
	public void setCustDetailId(String custDetailId) {
		this.custDetailId = custDetailId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustNameZH() {
		return custNameZH;
	}
	public void setCustNameZH(String custNameZH) {
		this.custNameZH = custNameZH;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getVisitOrder() {
		return visitOrder;
	}
	public void setVisitOrder(String visitOrder) {
		this.visitOrder = visitOrder;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public String getCustOrgName() {
		return custOrgName;
	}
	public void setCustOrgName(String custOrgName) {
		this.custOrgName = custOrgName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCustLevelName() {
		return custLevelName;
	}
	public void setCustLevelName(String custLevelName) {
		this.custLevelName = custLevelName;
	}
	
}
