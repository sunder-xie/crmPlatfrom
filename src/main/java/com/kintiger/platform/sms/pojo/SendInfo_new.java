package com.kintiger.platform.sms.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class SendInfo_new extends SearchInfo{
	private static final long serialVersionUID = -8983827672762566449L;
	private Integer id;
	private String mobile;//Ҫ���͵��ֻ�����
	private String content;//Ҫ���͵Ķ�������
	private String type;//��������
	private String result; //����״̬
	private String submitTime;//����ʱ��
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
}
