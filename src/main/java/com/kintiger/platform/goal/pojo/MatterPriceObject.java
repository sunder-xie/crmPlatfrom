package com.kintiger.platform.goal.pojo;

import java.io.Serializable;
import java.math.BigDecimal;


import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * Ŀ�������϶���ʵ��
 * @author ljwang
 *
 */
public class MatterPriceObject extends SearchInfo implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1433108064453765669L;
	private String    matPriceId;           //����
	private String  matter;           //Ԥ��ھ�����
	private String bezei;                //Ԥ��ھ�����
	private BigDecimal    price;             //����
	private String startDate;            //��Ч��ʼʱ��
	private String endDate;              //��Ч��ֹʱ��
	private String remark;                //��ע
	public String getMatPriceId() {
		return matPriceId;
	}
	public void setMatPriceId(String matPriceId) {
		this.matPriceId = matPriceId;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getBezei() {
		return bezei;
	}
	public void setBezei(String bezei) {
		this.bezei = bezei;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
