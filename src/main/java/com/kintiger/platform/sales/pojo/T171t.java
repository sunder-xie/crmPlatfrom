package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ���۴���ʵ����
 *
 */
public class T171t extends SearchInfo {
	private static final long serialVersionUID = 6934127280010231555L;
	
	private String mandt;// �ͻ���
	
	private String spras;// ���Դ���
	
	private String bzirk;// ���۴���
	
	private String bztxt;// ����

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
