package com.kintiger.platform.question.pojo;


import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Communit extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 309380545868675471L;
	private long cId;
	private long qId;
	private String content;
	private String author;
	private Date createTime;
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	public long getqId() {
		return qId;
	}
	public void setqId(long qId) {
		this.qId = qId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
}
