package com.learning.springboot.ws.ui.model.response;

import java.util.Date;

public class ErrorObject {
	
	private Date timestamp;
	private String message;
	
	
	
	public ErrorObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ErrorObject(Date date, String message) {
		this.timestamp=date;
		this.message=message;
	}
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
