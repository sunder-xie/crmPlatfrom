package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Pod extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4106184146077862701L;
	
	private String kunnr;
	private String mvgr1;
	private Double lfimg;
	private String vrkme;
	private String createDate;
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMvgr1() {
		return mvgr1;
	}
	public void setMvgr1(String mvgr1) {
		this.mvgr1 = mvgr1;
	}
	public Double getLfimg() {
		return lfimg;
	}
	public void setLfimg(Double lfimg) {
		this.lfimg = lfimg;
	}
	public String getVrkme() {
		return vrkme;
	}
	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	

}
