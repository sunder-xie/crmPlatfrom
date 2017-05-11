package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 分销渠道实体类
 *
 */
public class Tvtwt extends SearchInfo {
	private static final long serialVersionUID = -2683969302569341268L;
	
	private String mandt;// 客户端
	
	private String spras;// 语言代码
	
	private String vtweg;// 分销渠道
	
	private String vtext;// 名称

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

	public String getVtweg() {
		return vtweg;
	}

	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}

	public String getVtext() {
		return vtext;
	}

	public void setVtext(String vtext) {
		this.vtext = vtext;
	}
	

}
