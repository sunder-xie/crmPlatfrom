package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Tvbvk extends SearchInfo {
	private static final long serialVersionUID = 2525949563141333781L;
	
	private String mandt;// 客户
	
	private String vkbur;// 销售部门
	
	private String vkgrp;// 销售省份

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getVkgrp() {
		return vkgrp;
	}

	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}

}
