package com.kintiger.platform.crmdict.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;



/**
 * �ֵ䌦��
 * 
 */
public class CrmDict extends SearchInfo {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7866224128890359334L;

	/**
	 * �ֵ���ID
	 */
	private Long itemId;

	/**
	 * �ֵ�����ID
	 */
	private Long dictTypeId;

	/**
	 * �ֵ����ID
	 */
	private Long parentItemId;
	/**
	 * �ֵ��������
	 */
	private String parentItemName;

	/**
	 * �ֵ�������
	 */
	private String itemName;

	/**
	 * �ֵ�������
	 */
	private String itemDescription;

	/**
	 * ֵ
	 */
	private String itemValue;

	/**
	 * ��ע
	 */
	private String remark;

	/**
	 * ״̬
	 */
	private String itemState;

	private Date lastModify;

	/**
	 * �ƷѶ�ӦID
	 */
	private Long chargeId;

	/**
	 * Ӧ�ö�����ȼ�
	 */
	private Long appobjectLevel;

	private Date modifyDate;

	private CrmDictType cmsTbDictType;

	// 2012-03-16 anwang���5���ֶ�����ְλ����
	/**
	 * �涨�ܴ���
	 */
	private String tot;

	/**
	 * Э�ô���
	 */
	private String xf;

	/**
	 * ��ͳ������
	 */
	private String ct;

	/**
	 * KA������
	 */
	private String ka;

	/**
	 * �ֵ���������
	 */
	private String dictTypeName;

	private String dictTypeValue;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(Long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public Long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getAppobjectLevel() {
		return appobjectLevel;
	}

	public void setAppobjectLevel(Long appobjectLevel) {
		this.appobjectLevel = appobjectLevel;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public CrmDictType getCmsTbDictType() {
		return cmsTbDictType;
	}

	public void setCmsTbDictType(CrmDictType cmsTbDictType) {
		this.cmsTbDictType = cmsTbDictType;
	}

	public String getTot() {
		return tot;
	}

	public void setTot(String tot) {
		this.tot = tot;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getKa() {
		return ka;
	}

	public void setKa(String ka) {
		this.ka = ka;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public String getParentItemName() {
		return parentItemName;
	}

	public void setParentItemName(String parentItemName) {
		this.parentItemName = parentItemName;
	}

	

}