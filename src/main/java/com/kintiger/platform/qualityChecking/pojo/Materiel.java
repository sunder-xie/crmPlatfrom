package com.kintiger.platform.qualityChecking.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * @Description:成品物料实体类，用于质检报告
 * @author:xg.chen
 * @time:2017年5月9日 下午1:59:40
 * @version:1.0
 */
public class Materiel extends SearchInfo{
	private static final long serialVersionUID = 8021376406680909295L;
	
	//物料代码，（物料服务号)
	private String matnr;
	//物料描述
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
