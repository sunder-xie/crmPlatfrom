package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 产品组实体类
 *
 */
public class Tspa extends SearchInfo {
	private static final long serialVersionUID = -4961115354981853619L;
	
	private String mandt;// 客户端
	
	private String spart;// 产品组

	private String vtext;// 描述
	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public String getVtext() {
		return vtext;
	}

	public void setVtext(String vtext) {
		this.vtext = vtext;
	}
	

}
