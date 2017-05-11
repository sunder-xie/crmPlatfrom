package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class StockManagerSap extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2955018944703637180L;
	
	private String kunnr;
	private String matterNum;
	private String createDate;
	private Double onWayNum; //������;
	private Double outPutNotNum; //δ�Ų�����
	private Double outPutNotCheck; //�Ų�δ����
	private Double stockPod; //����ᱨ�ڼ��POD����
	
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMatterNum() {
		return matterNum;
	}
	public void setMatterNum(String matterNum) {
		this.matterNum = matterNum;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Double getOnWayNum() {
		return onWayNum;
	}
	public void setOnWayNum(Double onWayNum) {
		this.onWayNum = onWayNum;
	}
	public Double getOutPutNotNum() {
		return outPutNotNum;
	}
	public void setOutPutNotNum(Double outPutNotNum) {
		this.outPutNotNum = outPutNotNum;
	}
	public Double getOutPutNotCheck() {
		return outPutNotCheck;
	}
	public void setOutPutNotCheck(Double outPutNotCheck) {
		this.outPutNotCheck = outPutNotCheck;
	}
	public Double getStockPod() {
		return stockPod;
	}
	public void setStockPod(Double stockPod) {
		this.stockPod = stockPod;
	}
	
	

}
