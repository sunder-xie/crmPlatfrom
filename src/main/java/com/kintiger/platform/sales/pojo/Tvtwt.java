package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ��������ʵ����
 *
 */
public class Tvtwt extends SearchInfo {
	private static final long serialVersionUID = -2683969302569341268L;
	
	private String mandt;// �ͻ���
	
	private String spras;// ���Դ���
	
	private String vtweg;// ��������
	
	private String vtext;// ����

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
