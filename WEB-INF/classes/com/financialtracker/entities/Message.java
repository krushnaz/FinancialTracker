package com.financialtracker.entities;

public class Message {
	private String Description;
	private String contentType;
	private String CSSType;
	
	public Message(String Description, String contentType, String CSSType) {
		this.Description = Description;
		this.contentType = contentType;
		this.CSSType = CSSType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCSSType() {
		return CSSType;
	}
	public void setCSSType(String cSSType) {
		CSSType = cSSType;
	}

}
