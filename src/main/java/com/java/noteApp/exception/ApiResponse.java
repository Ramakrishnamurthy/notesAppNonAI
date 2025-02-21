package com.java.noteApp.exception;

import org.springframework.http.HttpStatus;

/*
 * This is  ApiResponse class which return the error response in case of exceptions.
 * It has message and status field representing the error message and http status in case of exception
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

public class ApiResponse {
	private String message;
	private HttpStatus status;

	public ApiResponse() {
		super();
	}

	public ApiResponse(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
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
