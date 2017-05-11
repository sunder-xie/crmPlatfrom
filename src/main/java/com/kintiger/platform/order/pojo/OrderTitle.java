package com.kintiger.platform.order.pojo;

import java.math.BigDecimal;

import com.kintiger.platform.base.pojo.SearchInfo;

public class OrderTitle extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -29548704181138641L;

	private Long order_id;

	// 订单号
	private String order_num;

	// 订单状态(A未提交,B未审核,C已审核,E部分排产,F完成排产;D已删除)
	private String order_state;

	// 经销商编码
	private String kunnr_num;

	// 经销商名称
	private String kunnr_name;

	// 订单日期
	private String order_date;

	// 订单数量
	private String order_count;
	//订单总金额
	private String orderTotalMoney;

	// 预打款金额
	private String order_up_fund;

	// 搭赠金额
	private String order_give_fund;

	// 使用费用金额
	private String order_use_fund;

	// 经销商ID
	private String kunnr_id;

	// 创建人
	private Long user_id;

	// 修改时间
	private String alter_date;

	// 审核人
	private Long alter_id;
	/**
	 * 可用预打款金额
	 */
	private BigDecimal usedPay;
	// 可兑现折扣金额
	private BigDecimal discountAmount;
	/**
	 * 政策通知内容
	 */
	private String msg;//通知
	/**
	 * 订单信息
	 */
	private String memo;
	/**
	 * 行政区划
	 */
	private String provinceArea;
	private String province;
	private String city;
	// 状态多选
	private String[] states;
	// 时间段
	private String startDate;
	private String endDate;
	private String orgId;//组织

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getKunnr_num() {
		return kunnr_num;
	}

	public void setKunnr_num(String kunnr_num) {
		this.kunnr_num = kunnr_num;
	}

	public String getKunnr_name() {
		return kunnr_name;
	}

	public void setKunnr_name(String kunnr_name) {
		this.kunnr_name = kunnr_name;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_count() {
		return order_count;
	}

	public void setOrder_count(String order_count) {
		this.order_count = order_count;
	}

	public String getOrder_up_fund() {
		return order_up_fund;
	}

	public void setOrder_up_fund(String order_up_fund) {
		this.order_up_fund = order_up_fund;
	}

	public String getOrder_give_fund() {
		return order_give_fund;
	}

	public void setOrder_give_fund(String order_give_fund) {
		this.order_give_fund = order_give_fund;
	}

	public String getOrder_use_fund() {
		return order_use_fund;
	}

	public void setOrder_use_fund(String order_use_fund) {
		this.order_use_fund = order_use_fund;
	}

	public String getAlter_date() {
		return alter_date;
	}

	public void setAlter_date(String alter_date) {
		this.alter_date = alter_date;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setKunnr_id(String kunnr_id) {
		this.kunnr_id = kunnr_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getAlter_id() {
		return alter_id;
	}

	public void setAlter_id(Long alter_id) {
		this.alter_id = alter_id;
	}

	public BigDecimal getUsedPay() {
		return usedPay;
	}

	public void setUsedPay(BigDecimal usedPay) {
		this.usedPay = usedPay;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getKunnr_id() {
		return kunnr_id;
	}

	public String getProvinceArea() {
		return provinceArea;
	}

	public void setProvinceArea(String provinceArea) {
		this.provinceArea = provinceArea;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String[] getStates() {
		return states;
	}

	public void setStates(String[] states) {
		this.states = states;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOrderTotalMoney() {
		return orderTotalMoney;
	}

	public void setOrderTotalMoney(String orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
