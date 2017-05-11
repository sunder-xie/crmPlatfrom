package com.kintiger.platform.base.pojo;

import java.io.Serializable;

/**
 * SearchInfo
 * 
 * 
 */
public class SearchInfo implements Serializable {

	private static final long serialVersionUID = 235499309845516885L;

	public static final String DIR_DESC = "DESC";

	public static final String DIR_ASC = "ASC";

	/**
	 * �������
	 */
	private String[] codes;

	/**
	 * ����ʹ�õ�����ֵ�������ֶ�
	 */
	private String search;

	/**
	 * �����ֶ�
	 */
	private String sort;

	/**
	 * ��������
	 */
	private String dir;

	/**
	 * ��ǰҳ
	 */
	private int page;

	/**
	 * ÿҳ��ʾ��¼��
	 */
	private int rows;

	/**
	 * ��ʼ��
	 */
	private int start;

	/**
	 * ������
	 */
	private int end;

	/**
	 * �Ƿ�ʹ�÷�ҳ
	 */
	private String pagination;

	public String isPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
