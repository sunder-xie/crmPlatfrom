package com.kintiger.platform.sales.pojo;

public class Region {
	private String id;
	private String text;
	private String pid;
	// 传入SQL的列名
	private String idColumn;
	private String textColumn;
	private String paraColumn;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getTextColumn() {
		return textColumn;
	}

	public void setTextColumn(String textColumn) {
		this.textColumn = textColumn;
	}

	public String getParaColumn() {
		return paraColumn;
	}

	public void setParaColumn(String paraColumn) {
		this.paraColumn = paraColumn;
	}

}
