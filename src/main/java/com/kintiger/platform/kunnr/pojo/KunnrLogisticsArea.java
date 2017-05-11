package com.kintiger.platform.kunnr.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 物流区域
 * 
 * @author ljwang
 * 
 */
public class KunnrLogisticsArea extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private String kunnr;
	private String vkgrp;        //大区
	private String vkgrpTxt;         
	private String bzirk;       //省份
	private String bzirkTxt;           
	private String vkbur;        //销售部门
	private String vkburTxt;
	private Kunnr kunnrObject;
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getVkgrp() {
		return vkgrp;
	}
	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}
	public String getVkgrpTxt() {
		return vkgrpTxt;
	}
	public void setVkgrpTxt(String vkgrpTxt) {
		this.vkgrpTxt = vkgrpTxt;
	}
	public String getBzirk() {
		return bzirk;
	}
	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}
	public String getBzirkTxt() {
		return bzirkTxt;
	}
	public void setBzirkTxt(String bzirkTxt) {
		this.bzirkTxt = bzirkTxt;
	}
	public String getVkbur() {
		return vkbur;
	}
	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}
	public String getVkburTxt() {
		return vkburTxt;
	}
	public void setVkburTxt(String vkburTxt) {
		this.vkburTxt = vkburTxt;
	}
	public Kunnr getKunnrObject() {
		return kunnrObject;
	}
	public void setKunnrObject(Kunnr kunnrObject) {
		this.kunnrObject = kunnrObject;
	}         
	
	



}
