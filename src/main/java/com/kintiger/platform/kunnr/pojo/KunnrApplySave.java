package com.kintiger.platform.kunnr.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 经销商开户保存
 * 
 * @author ljwang
 * 
 */
public class KunnrApplySave extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private Long id;
	private String xmlId;             //ids
	private String xmlPath;           //路径
	private String subfolders;          //文件夹
	private String createDate;     //创建时间
	private String modifyDate;      //时间戳
	private String xmlName;
	private String empId;
	private String empName;
	private String orgId;
	private String orgName;
	private String eventId;
	private String state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getXmlId() {
		return xmlId;
	}
	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public String getSubfolders() {
		return subfolders;
	}
	public void setSubfolders(String subfolders) {
		this.subfolders = subfolders;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getXmlName() {
		return xmlName;
	}
	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
