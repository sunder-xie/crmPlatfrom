package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Kunag extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7193816263327305530L;
	
	private Long orgId;
	private String orgName;
	private String kunnr;
	private String name1;
	private String kunag;
	private String name2;
	private String isMain;
	private String isImportant;
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getKunag() {
		return kunag;
	}
	public void setKunag(String kunag) {
		this.kunag = kunag;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getIsImportant() {
		return isImportant;
	}
	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}
	
	

}
