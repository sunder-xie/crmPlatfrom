package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;
import com.kintiger.platform.base.pojo.SearchInfo;

public class StockManager extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -557085066350367929L;
	
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private Long orgId;
	private String kunnr;
	private String kunnrName;
	private int year;
	private int month;
	private Long skuId;
	private String matterNum;
	private String skuName;
	private String checkTime;
	private String lastCheckTime;
	private int quantity;
	private String flag;
	private String productionStartDate;
	private String productionEndDate;
	private Double lastStock; //���ڿ��
	private Double onWayNum; //������;
	private Double salesDayAvg;  //�����վ�������
	private Double salesDayNum;  //����������
	private String stockStatus;  //�����
	private Double salesGoalA1;  //����Ŀ��
	private Double nextSalesGoalA1;  //���·���Ŀ��
	private Double stockSafe;  //���µװ�ȫ���
	private Double outPutNotNum; //δ�Ų�����
	private String outPutNeeds; //�Ų�����
	private Double takeOrderNeedNum; //���ϵ���
	private String dateNow;
	private Double outPutNotCheck; //�Ų�δ����
	private Double stockPod; //����ᱨ�ڼ��POD����
	
	private Double stockSafe1;  //��ȫ���
	private Double stockNotSafe;  //ʵ�ʿ��-��ȫ���
	private String stockSafeFlag;  //���
	private Double stockSafeExpect;  // չ����ȫ���
	private Double stockRealExpect;  // չ��ʵ�ʿ��
	private Double stockNotSafeExpect;  // չ������
	
	public String getOrgRegion() {
		return orgRegion;
	}
	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}
	public String getOrgProvince() {
		return orgProvince;
	}
	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getLastCheckTime() {
		return lastCheckTime;
	}
	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProductionStartDate() {
		return productionStartDate;
	}
	public void setProductionStartDate(String productionStartDate) {
		this.productionStartDate = productionStartDate;
	}
	public String getProductionEndDate() {
		return productionEndDate;
	}
	public void setProductionEndDate(String productionEndDate) {
		this.productionEndDate = productionEndDate;
	}
	public Double getLastStock() {
		return lastStock;
	}
	public void setLastStock(Double lastStock) {
		this.lastStock = lastStock;
	}
	public Double getOnWayNum() {
		return onWayNum;
	}
	public void setOnWayNum(Double onWayNum) {
		this.onWayNum = onWayNum;
	}
	public Double getSalesDayAvg() {
		return salesDayAvg;
	}
	public void setSalesDayAvg(Double salesDayAvg) {
		this.salesDayAvg = salesDayAvg;
	}
	public Double getSalesDayNum() {
		return salesDayNum;
	}
	public void setSalesDayNum(Double salesDayNum) {
		this.salesDayNum = salesDayNum;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public Double getSalesGoalA1() {
		return salesGoalA1;
	}
	public void setSalesGoalA1(Double salesGoalA1) {
		this.salesGoalA1 = salesGoalA1;
	}
	public Double getStockSafe() {
		return stockSafe;
	}
	public void setStockSafe(Double stockSafe) {
		this.stockSafe = stockSafe;
	}
	public Double getOutPutNotNum() {
		return outPutNotNum;
	}
	public void setOutPutNotNum(Double outPutNotNum) {
		this.outPutNotNum = outPutNotNum;
	}
	public String getOutPutNeeds() {
		return outPutNeeds;
	}
	public void setOutPutNeeds(String outPutNeeds) {
		this.outPutNeeds = outPutNeeds;
	}
	public Double getTakeOrderNeedNum() {
		return takeOrderNeedNum;
	}
	public void setTakeOrderNeedNum(Double takeOrderNeedNum) {
		this.takeOrderNeedNum = takeOrderNeedNum;
	}
	public String getMatterNum() {
		return matterNum;
	}
	public void setMatterNum(String matterNum) {
		this.matterNum = matterNum;
	}
	public Double getNextSalesGoalA1() {
		return nextSalesGoalA1;
	}
	public void setNextSalesGoalA1(Double nextSalesGoalA1) {
		this.nextSalesGoalA1 = nextSalesGoalA1;
	}
	public String getDateNow() {
		return dateNow;
	}
	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
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
	public Double getStockSafe1() {
		return stockSafe1;
	}
	public void setStockSafe1(Double stockSafe1) {
		this.stockSafe1 = stockSafe1;
	}
	public Double getStockNotSafe() {
		return stockNotSafe;
	}
	public void setStockNotSafe(Double stockNotSafe) {
		this.stockNotSafe = stockNotSafe;
	}
	public String getStockSafeFlag() {
		return stockSafeFlag;
	}
	public void setStockSafeFlag(String stockSafeFlag) {
		this.stockSafeFlag = stockSafeFlag;
	}
	public Double getStockSafeExpect() {
		return stockSafeExpect;
	}
	public void setStockSafeExpect(Double stockSafeExpect) {
		this.stockSafeExpect = stockSafeExpect;
	}
	public Double getStockRealExpect() {
		return stockRealExpect;
	}
	public void setStockRealExpect(Double stockRealExpect) {
		this.stockRealExpect = stockRealExpect;
	}
	public Double getStockNotSafeExpect() {
		return stockNotSafeExpect;
	}
	public void setStockNotSafeExpect(Double stockNotSafeExpect) {
		this.stockNotSafeExpect = stockNotSafeExpect;
	}
	
	

}
