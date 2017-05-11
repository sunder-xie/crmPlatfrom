package com.kintiger.platform.order.pojo;

import java.math.BigDecimal;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 考核返还清单
 * 
 * @author judy
 *
 */
public class AssesReturnList extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -29548704181138641L;

	private Long relistId; // 主键：seq:crm.crm_seq_return_list

	// 订单号
	private String orderId;

	// 物料编码
	private String materielNum;

	// 物料描述
	private String materielName;

	// 品相编码
	private String cataId;

	// 品相名称
	private String cataName;

	// 财年打款价
	private BigDecimal yearPayPrice;

	// 考核总奖金
	private BigDecimal totalBouns;

	// 月度考核
	private BigDecimal monthAsses;

	// 季度考核
	private BigDecimal quarterAsses;

	// 年度考核
	private BigDecimal yearAsses;

	// 年裸价
	private BigDecimal yearNakedPrice;
	// 状态(U有效，D删除)
	private String status;
	// 操做人
	private String userId;
	//经销商编码
	private String kunnrNum;
	//经销商名称
	private String kunnrName;

	public Long getRelistId() {
		return relistId;
	}

	public void setRelistId(long relistId) {
		this.relistId = relistId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMaterielNum() {
		return materielNum;
	}

	public void setMaterielNum(String materielNum) {
		this.materielNum = materielNum;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getCataId() {
		return cataId;
	}

	public void setCataId(String cataId) {
		this.cataId = cataId;
	}

	public String getCataName() {
		return cataName;
	}

	public void setCataName(String cataName) {
		this.cataName = cataName;
	}

	public BigDecimal getYearPayPrice() {
		return yearPayPrice;
	}

	public void setYearPayPrice(BigDecimal yearPayPrice) {
		this.yearPayPrice = yearPayPrice;
	}

	public BigDecimal getTotalBouns() {
		return totalBouns;
	}

	public void setTotalBouns(BigDecimal totalBouns) {
		this.totalBouns = totalBouns;
	}

	public BigDecimal getMonthAsses() {
		return monthAsses;
	}

	public void setMonthAsses(BigDecimal monthAsses) {
		this.monthAsses = monthAsses;
	}

	public BigDecimal getQuarterAsses() {
		return quarterAsses;
	}

	public void setQuarterAsses(BigDecimal quarterAsses) {
		this.quarterAsses = quarterAsses;
	}

	public BigDecimal getYearAsses() {
		return yearAsses;
	}

	public void setYearAsses(BigDecimal yearAsses) {
		this.yearAsses = yearAsses;
	}

	public BigDecimal getYearNakedPrice() {
		return yearNakedPrice;
	}

	public void setYearNakedPrice(BigDecimal yearNakedPrice) {
		this.yearNakedPrice = yearNakedPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKunnrNum() {
		return kunnrNum;
	}

	public void setKunnrNum(String kunnrNum) {
		this.kunnrNum = kunnrNum;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public void setRelistId(Long relistId) {
		this.relistId = relistId;
	}

}
