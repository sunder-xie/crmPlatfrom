package com.kintiger.platform.qualityChecking.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * @Description:��Ʒ����ʵ���࣬�����ʼ챨��
 * @author:xg.chen
 * @time:2017��5��9�� ����1:59:40
 * @version:1.0
 */
public class Materiel extends SearchInfo{
	private static final long serialVersionUID = 8021376406680909295L;
	
	//���ϴ��룬�����Ϸ����)
	private String matnr;
	//��������
	private String maktx;
	
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getMaktx() {
		return maktx;
	}
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	@Override
	public String toString() {
		return "Materiel [matnr=" + matnr + ", maktx=" + maktx + "]";
	}
	
	

}
