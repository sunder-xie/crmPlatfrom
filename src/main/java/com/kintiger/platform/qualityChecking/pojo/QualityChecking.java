package com.kintiger.platform.qualityChecking.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * @Description:�ʼ챨��֮ʵ����
 * @author:xg.chen
 * @time:2017��5��8�� ����2:22:06
 * @version:1.0
 */
public class QualityChecking extends SearchInfo{
	
	private static final long serialVersionUID = -595881442647847540L;
	
	//ID
	private String id;
	//�ʼ���
	private String qualityCheckingId;
	//��������
	private String dateOfManufacture;
	//ʱ���
	private String productionTimeStart;
	//ʱ����
	private String productionTimeEnd;
	//����Id
	private String materielId;
	//��������
	private String materielName;
	//����Ԥ��
	private String qualityChecking;
	//��ʼʱ��
	private String dateOfManufactureStart;
	//����ʱ��
	private String dateOfManufactureEnd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQualityCheckingId() {
		return qualityCheckingId;
	}
	public void setQualityCheckingId(String qualityCheckingId) {
		this.qualityCheckingId = qualityCheckingId;
	}
	public String getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	public String getProductionTimeStart() {
		return productionTimeStart;
	}
	public void setProductionTimeStart(String productionTimeStart) {
		this.productionTimeStart = productionTimeStart;
	}
	public String getProductionTimeEnd() {
		return productionTimeEnd;
	}
	public void setProductionTimeEnd(String productionTimeEnd) {
		this.productionTimeEnd = productionTimeEnd;
	}
	public String getMaterielId() {
		return materielId;
	}
	public void setMaterielId(String materielId) {
		this.materielId = materielId;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public String getQualityChecking() {
		return qualityChecking;
	}
	public void setQualityChecking(String qualityChecking) {
		this.qualityChecking = qualityChecking;
	}
	
	public String getDateOfManufactureStart() {
		return dateOfManufactureStart;
	}
	public void setDateOfManufactureStart(String dateOfManufactureStart) {
		this.dateOfManufactureStart = dateOfManufactureStart;
	}
	public String getDateOfManufactureEnd() {
		return dateOfManufactureEnd;
	}
	public void setDateOfManufactureEnd(String dateOfManufactureEnd) {
		this.dateOfManufactureEnd = dateOfManufactureEnd;
	}
	
	@Override
	public String toString() {
		return "QualityChecking [id=" + id + ", qualityCheckingId="
				+ qualityCheckingId + ", dateOfManufacture="
				+ dateOfManufacture + ", productionTimeStart="
				+ productionTimeStart + ", productionTimeEnd="
				+ productionTimeEnd + ", materielId=" + materielId
				+ ", materielName=" + materielName + ", qualityChecking="
				+ qualityChecking + ", dateOfManufactureStart="
				+ dateOfManufactureStart + ", dateOfManufactureEnd="
				+ dateOfManufactureEnd + "]";
	}
	

}
