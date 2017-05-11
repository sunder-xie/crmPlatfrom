package com.kintiger.platform.stock.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 经销商库存表
 * 
 * @author xxping
 * 
 */
public class Stock extends SearchInfo {

	private static final long serialVersionUID = -3357240225892671720L;

	private Long stock_id;
	private String cust_id;
	private String custName;
	private String stock_stock_place;
	private String stock_stock_area;
	private String stock_stock_gargo;
	private String batch;
	private String category_id;
	private String categoryName;
	private Date stock_modify_date;
	private String stock_operator;
	private String stock_flag;
	private int stock_quantity;
	private int stock_differ;// 库存差异
	private int stock_pandian;// 盘点数量
	private String atId;
	private String instock_total_id;

	public int getStock_pandian() {
		return stock_pandian;
	}

	public void setStock_pandian(int stock_pandian) {
		this.stock_pandian = stock_pandian;
	}

	public int getStock_differ() {
		return stock_differ;
	}

	public void setStock_differ(int stock_differ) {
		this.stock_differ = stock_differ;
	}

	public String getStock_stock_place() {
		return stock_stock_place;
	}

	public void setStock_stock_place(String stock_stock_place) {
		this.stock_stock_place = stock_stock_place;
	}

	public Long getStock_id() {
		return stock_id;
	}

	public void setStock_id(Long stock_id) {
		this.stock_id = stock_id;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getStock_stock_area() {
		return stock_stock_area;
	}

	public void setStock_stock_area(String stock_stock_area) {
		this.stock_stock_area = stock_stock_area;
	}

	public String getStock_stock_gargo() {
		return stock_stock_gargo;
	}

	public void setStock_stock_gargo(String stock_stock_gargo) {
		this.stock_stock_gargo = stock_stock_gargo;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public Date getStock_modify_date() {
		return stock_modify_date;
	}

	public void setStock_modify_date(Date stock_modify_date) {
		this.stock_modify_date = stock_modify_date;
	}

	public String getStock_operator() {
		return stock_operator;
	}

	public void setStock_operator(String stock_operator) {
		this.stock_operator = stock_operator;
	}

	public String getStock_flag() {
		return stock_flag;
	}

	public void setStock_flag(String stock_flag) {
		this.stock_flag = stock_flag;
	}

	public String getAtId() {
		return atId;
	}

	public void setAtId(String atId) {
		this.atId = atId;
	}

	public String getInstock_total_id() {
		return instock_total_id;
	}

	public void setInstock_total_id(String instock_total_id) {
		this.instock_total_id = instock_total_id;
	}

}
