package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ��˾����ʵ����
 *
 */
public class T001 extends SearchInfo {
	private static final long serialVersionUID = -1417675423966089278L;
	
	private String mandt;// �ͻ���
	
	private String bukrs;// ��˾����
	
	private String butxt;// ��˾�����˾������

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getButxt() {
		return butxt;
	}

	public void setButxt(String butxt) {
		this.butxt = butxt;
	}
	

}
