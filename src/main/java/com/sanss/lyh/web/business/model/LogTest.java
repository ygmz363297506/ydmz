package com.sanss.lyh.web.business.model;

public class LogTest {
	private int id;
	//被叫
	private String[] du;
	//主叫
	private String ou;
	private String text;
	private String logtime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getDu() {
		return du;
	}
	public void setDu(String[] du) {
		this.du = du;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
}
