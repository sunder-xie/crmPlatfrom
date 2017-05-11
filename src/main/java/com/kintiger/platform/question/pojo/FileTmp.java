package com.kintiger.platform.question.pojo;

import java.io.Serializable;
import java.util.Date;

public class FileTmp implements Serializable {

	private static final long serialVersionUID = 8836334327874883396L;
	
	private Long fileId;

	private String fileName;
	
	private String creator;

	private Date createDate;

	private String fileNameNew;

	private Long questionKey;

	private String subFolders;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileNameNew() {
		return fileNameNew;
	}

	public void setFileNameNew(String fileNameNew) {
		this.fileNameNew = fileNameNew;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public Long getQuestionKey() {
		return questionKey;
	}

	public void setQuestionKey(Long questionKey) {
		this.questionKey = questionKey;
	}

}
