package com.java.noteApp.exception;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	private String message;
	private HttpStatus status;
	//private boolean success;
	
	public ApiResponse() {
		super();
	}
	public ApiResponse(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
		//this.success = success;
	}
	public final String getMessage() {
		return message;
	}
	public final void setMessage(String message) {
		this.message = message;
	}
	public final HttpStatus getStatus() {
		return status;
	}
	public final void setStatus(HttpStatus status) {
		this.status = status;
	}
}
