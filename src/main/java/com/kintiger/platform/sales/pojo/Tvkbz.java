package com.kintiger.platform.sales.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * ������֯-��������-��Ʒ��-���۲��Ź�ϵʵ����
 *
 */
public class Tvkbz extends SearchInfo {
	private static final long serialVersionUID = 5964601380805809805L;
	
	private String mandt;// �ͻ���
	
	private String vkorg;// ������֯
	
	private String vkorgTxt;  //������֯����
	
	private String vtweg;// ��������
	private String vtwegTxt;    //������������
	
	private String spart;// ��Ʒ��
	
	private String spartTxt;// ��Ʒ������
	
	private String vkbur;// ���۲���
	private String vkburTxt;// ���۲�������

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

	public String getVtweg() {
		return vtweg;
	}

	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}

	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getVkorgTxt() {
		return vkorgTxt;
	}

	public void setVkorgTxt(String vkorgTxt) {
		this.vkorgTxt = vkorgTxt;
	}

	public String getVtwegTxt() {
		return vtwegTxt;
	}

	public void setVtwegTxt(String vtwegTxt) {
		this.vtwegTxt = vtwegTxt;
	}

	public String getSpartTxt() {
		return spartTxt;
	}

	public void setSpartTxt(String spartTxt) {
		this.spartTxt = spartTxt;
	}

	public String getVkburTxt() {
		return vkburTxt;
	}

	public void setVkburTxt(String vkburTxt) {
		this.vkburTxt = vkburTxt;
	}

}
