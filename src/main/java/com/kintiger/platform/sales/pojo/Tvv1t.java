package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ����ʵ����
 *
 */
public class Tvv1t extends SearchInfo {
	private static final long serialVersionUID = 1678544069291188969L;
	
	private String mandt;// �ͻ���
	
	private String spras;// ���Դ���
	
	private String kvgr1;// ����
	
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

	public String getKvgr1() {
		return kvgr1;
	}

	public void setKvgr1(String kvgr1) {
		this.kvgr1 = kvgr1;
	}

	public String getBezei() {
		return bezei;
	}

	public void setBezei(String bezei) {
		this.bezei = bezei;
	}

}
