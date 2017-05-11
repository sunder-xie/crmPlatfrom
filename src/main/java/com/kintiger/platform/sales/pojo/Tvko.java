package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 销售组织实体类
 *
 */
public class Tvko extends SearchInfo {
	private static final long serialVersionUID = 8692372888231671074L;
	
	private String mandt;// 客户端
	
	private String vkorg;// 销售组织代码
	
	private String bukrs;// 销售机构的公司代码

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getVkorg() {
		return vkorg;
	}

	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	

}
