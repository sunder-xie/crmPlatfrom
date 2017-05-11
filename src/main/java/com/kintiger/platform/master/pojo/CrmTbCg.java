package com.kintiger.platform.master.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class CrmTbCg extends SearchInfo {

	
	
	/**
	 * @author lc
	 */
	private static final long serialVersionUID = 1L;

	private Long categoryId;// ID 主键
	private String cateId;
	private String categoryName;// 类别名称
	private String companyId;// 公司类型
	private String createUser;// 创建者
	private String createUserName;// 创建人姓名
	private Date createDate;// 创建时间
	private String status;
	private String cloudid;// 经销商编号
	private String title;
	private String sapCode;//口径编码
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCloudid() {
		return cloudid;
	}
	public void setCloudid(String cloudid) {
		this.cloudid = cloudid;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	
}
