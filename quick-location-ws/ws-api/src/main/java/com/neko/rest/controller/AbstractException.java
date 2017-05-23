package com.neko.rest.controller;

public class AbstractException{
	
	
	private String type;
	private String message;


	public AbstractException(String code, String message) {
		this.type = code;
		this.message = message;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
