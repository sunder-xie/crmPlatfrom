package com.kintiger.platform.stockReport.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class OrderFollow extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7193816263327305530L;
	
	private String kunnr;
	private String kunag;
	private String kunnrName;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private Long orgId;
	private Long categoryId;
	private Long skuId;
	private String skuName;
	private String MATNR;
	private String ERDAT;  //订单日期
	private String AUDAT;  //单据日期
	private String POSNR1;  //销售订单行项目
	private Double KWMENG;  //数量
	private String PEDTR;  //计划日期
	private String VBELN2;  //交货单号
	private String TKNUM;  //运单号
	private String WADAT_IST;  //出库日期
	private String PODAT;  //POD日期
	private String VBELN3;  //发票号
	private String WBSTK;  //发货状态
	private String outOfDate; //逾期天数，当前日期-排产日期
	
	private String VBELN1;  //订单号
	private String POSNR2;  //交货单行项目
	private String POSNR3;  //发票行项目
	private String PDSTK;  //POD状态
	
	private String createDateStr;
	private String orderState;
	private String startDate;
	private String endDate;
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
	public String getMATNR() {
		return MATNR;
	}
	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getERDAT() {
		return ERDAT;
	}
	public void setERDAT(String eRDAT) {
		ERDAT = eRDAT;
	}
	public String getAUDAT() {
		return AUDAT;
	}
	public void setAUDAT(String aUDAT) {
		AUDAT = aUDAT;
	}
	public String getPOSNR1() {
		return POSNR1;
	}
	public void setPOSNR1(String pOSNR1) {
		POSNR1 = pOSNR1;
	}
	public Double getKWMENG() {
		return KWMENG;
	}
	public void setKWMENG(Double kWMENG) {
		KWMENG = kWMENG;
	}
	public String getPEDTR() {
		return PEDTR;
	}
	public void setPEDTR(String pEDTR) {
		PEDTR = pEDTR;
	}
	public String getVBELN2() {
		return VBELN2;
	}
	public void setVBELN2(String vBELN2) {
		VBELN2 = vBELN2;
	}
	public String getTKNUM() {
		return TKNUM;
	}
	public void setTKNUM(String tKNUM) {
		TKNUM = tKNUM;
	}
	public String getWADAT_IST() {
		return WADAT_IST;
	}
	public void setWADAT_IST(String wADAT_IST) {
		WADAT_IST = wADAT_IST;
	}
	public String getPODAT() {
		return PODAT;
	}
	public void setPODAT(String pODAT) {
		PODAT = pODAT;
	}
	public String getVBELN3() {
		return VBELN3;
	}
	public void setVBELN3(String vBELN3) {
		VBELN3 = vBELN3;
	}
	public String getWBSTK() {
		return WBSTK;
	}
	public void setWBSTK(String wBSTK) {
		WBSTK = wBSTK;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getVBELN1() {
		return VBELN1;
	}
	public void setVBELN1(String vBELN1) {
		VBELN1 = vBELN1;
	}
	public String getPOSNR2() {
		return POSNR2;
	}
	public void setPOSNR2(String pOSNR2) {
		POSNR2 = pOSNR2;
	}
	public String getPOSNR3() {
		return POSNR3;
	}
	public void setPOSNR3(String pOSNR3) {
		POSNR3 = pOSNR3;
	}
	public String getPDSTK() {
		return PDSTK;
	}
	public void setPDSTK(String pDSTK) {
		PDSTK = pDSTK;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
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
	public String getKunag() {
		return kunag;
	}
	public void setKunag(String kunag) {
		this.kunag = kunag;
	}
	public String getOutOfDate() {
		return outOfDate;
	}
	public void setOutOfDate(String outOfDate) {
		this.outOfDate = outOfDate;
	}
	

}
