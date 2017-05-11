package com.kintiger.platform.goal.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * Ŀ�������϶���ʵ��(BO����ʹ��)
 * @author zhusiliang
 */
public class MatterPriceBO extends SearchInfo implements Serializable{

	private static final long serialVersionUID = 925354072619212738L;

	private String  year; // ����
	private String  mvgr1; //Ԥ��ھ����� ��Ϊ�ļ���Ŀ
	private String  mvgTxt;//Ԥ��ھ����� ��Ϊ�ļ���Ŀ����
	private Double  mvgPrice;//Ŀ��������
	private Double  salesPrice;//���۵���
	private Double  mvgRatio;//����ϵ��
	private String  cupType;//��������
	private String cupId;//��������ID
	private String sku;//ԭԤ��ھ�����
	private String skuText;//ԭԤ��ھ�����
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getSkuText() {
		return skuText;
	}
	public void setSkuText(String skuText) {
		this.skuText = skuText;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMvgr1() {
		return mvgr1;
	}
	public void setMvgr1(String mvgr1) {
		this.mvgr1 = mvgr1;
	}
	public String getMvgTxt() {
		return mvgTxt;
	}
	public void setMvgTxt(String mvgTxt) {
		this.mvgTxt = mvgTxt;
	}
	public Double getMvgPrice() {
		return mvgPrice;
	}
	public void setMvgPrice(Double mvgPrice) {
		this.mvgPrice = mvgPrice;
	}
	public Double getMvgRatio() {
		return mvgRatio;
	}
	public void setMvgRatio(Double mvgRatio) {
		this.mvgRatio = mvgRatio;
	}
	public String getCupType() {
		return cupType;
	}
	public void setCupType(String cupType) {
		this.cupType = cupType;
	}
	public Double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getCupId() {
		return cupId;
	}
	public void setCupId(String cupId) {
		this.cupId = cupId;
	}
	
}
