package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ���۲���ʵ����
 *
 */
public class Tvkbt extends SearchInfo {
	private static final long serialVersionUID = -435015062754854393L;
	
	private String mandt;// �ͻ���
	
	private String spras;// ���Դ���
	
	private String vkbur;// ���۲���
	
	private String bezei;// ����

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

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getBezei() {
		return bezei;
	}

	public void setBezei(String bezei) {
		this.bezei = bezei;
	}
	

}
