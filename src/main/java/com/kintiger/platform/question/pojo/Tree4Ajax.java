package com.kintiger.platform.question.pojo;

import java.io.Serializable;

public class Tree4Ajax implements Serializable {

	private static final long serialVersionUID = -8283009769200561139L;

	private String id;

	private String text;

	private String state;

	private String target;

	private String attributes;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

}