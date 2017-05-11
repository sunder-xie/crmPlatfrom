package com.kintiger.platform.order.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Account extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7851372986156860890L;
	
	private Long accountId;
	private Long accountRefundId;
	private Double price;
	private Double fee;
	private String accountDesc;
	private String userId;
	private String status;
	private String createDate;
	private Date startDate;
	private Date endDate;
	private String kunnr;
	private String kunnrName;
	private String successNum;
	private String userName;
	private String payType;
	private String cardType;
	private String inSap;
	private Date successDate;
	private Double dk_netwr;
	private Double fy_netwr;
	private Double zk_netwr;
	private String kverm;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAccountDesc() {
		return accountDesc;
	}
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(String successNum) {
		this.successNum = successNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getInSap() {
		return inSap;
	}
	public void setInSap(String inSap) {
		this.inSap = inSap;
	}
	public Date getSuccessDate() {
		return successDate;
	}
	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}
	public Double getDk_netwr() {
		return dk_netwr;
	}
	public void setDk_netwr(Double dk_netwr) {
		this.dk_netwr = dk_netwr;
	}
	public Double getFy_netwr() {
		return fy_netwr;
	}
	public void setFy_netwr(Double fy_netwr) {
		this.fy_netwr = fy_netwr;
	}
	public Double getZk_netwr() {
		return zk_netwr;
	}
	public void setZk_netwr(Double zk_netwr) {
		this.zk_netwr = zk_netwr;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getKverm() {
		return kverm;
	}
	public void setKverm(String kverm) {
		this.kverm = kverm;
	}
	public Long getAccountRefundId() {
		return accountRefundId;
	}
	public void setAccountRefundId(Long accountRefundId) {
		this.accountRefundId = accountRefundId;
	}
	

}
