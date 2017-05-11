package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 销售大区实体类
 *
 */
public class T171t extends SearchInfo {
	private static final long serialVersionUID = 6934127280010231555L;
	
	private String mandt;// 客户端
	
	private String spras;// 语言代码
	
	private String bzirk;// 销售大区
	
	private String bztxt;// 区名

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getSpras() {
		return spras;
	}

	public void setSpras(String spras) {
		this.spras = spras;
	}

	public String getBzirk() {
		return bzirk;
	}

	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}

	public String getBztxt() {
		return bztxt;
	}

	public void setBztxt(String bztxt) {
		this.bztxt = bztxt;
	}

}
