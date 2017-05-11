package com.kintiger.platform.crmdict.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;


/**
 * ◊÷µ‰Óê–Õå¶œÛ
 * 
 */
public class CrmDictType extends SearchInfo {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1113209067055277716L;
	private Long dictTypeId;
	private Long parentDictTypeId;
	private String dictTypeName;
	private String dictTypeValue;
	private String remark;
	private String dictTypeState;
	private Date lastModify;
	private Date modifyDate;

	public Long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(Long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public Long getParentDictTypeId() {
		return parentDictTypeId;
	}

	public void setParentDictTypeId(Long parentDictTypeId) {
		this.parentDictTypeId = parentDictTypeId;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeState() {
		return dictTypeState;
	}

	public void setDictTypeState(String dictTypeState) {
		this.dictTypeState = dictTypeState;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}