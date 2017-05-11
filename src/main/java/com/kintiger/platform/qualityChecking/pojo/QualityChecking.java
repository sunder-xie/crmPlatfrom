package com.kintiger.platform.qualityChecking.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * @Description:质检报告之实体类
 * @author:xg.chen
 * @time:2017年5月8日 下午2:22:06
 * @version:1.0
 */
public class QualityChecking extends SearchInfo{
	
	private static final long serialVersionUID = -595881442647847540L;
	
	//ID
	private String id;
	//质检编号
	private String qualityCheckingId;
	//生产日期
	private String dateOfManufacture;
	//时间从
	private String productionTimeStart;
	//时间至
	private String productionTimeEnd;
	//物料Id
	private String materielId;
	//物料描述
	private String materielName;
	//报告预览
	private String qualityChecking;
	//开始时间
	private String dateOfManufactureStart;
	//结束时间
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
