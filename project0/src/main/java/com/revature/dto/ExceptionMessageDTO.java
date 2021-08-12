package com.revature.dto;

import com.revature.controller.Controller;

import io.javalin.Javalin;

public class ExceptionMessageDTO  {

	private String message;
	
	public ExceptionMessageDTO() {
		super();
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
}
