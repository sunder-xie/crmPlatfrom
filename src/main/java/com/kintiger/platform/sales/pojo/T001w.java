package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ����ʵ����
 *
 */
public class T001w extends SearchInfo {
	private static final long serialVersionUID = 6567422016918444767L;
	
	private String mandt;// �ͻ���
	
	private String werks;// ����
	
	private String name1;// ����

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

}
