package com.kintiger.platform.complaintReport.pojo;

import java.util.Date;

public class ProductOutput {
	private String series;
	private String factory;
	private double output;
	private String unit;
	private Date start_sap_date;
	private Date end_sap_date;
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public double getOutput() {
		return output;
	}
	public void setOutput(double output) {
		this.output = output;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getStart_sap_date() {
		return start_sap_date;
	}
	public void setStart_sap_date(Date start_sap_date) {
		this.start_sap_date = start_sap_date;
	}
	public Date getEnd_sap_date() {
		return end_sap_date;
	}
	public void setEnd_sap_date(Date end_sap_date) {
		this.end_sap_date = end_sap_date;
	}
	
	

}
