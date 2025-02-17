package com.java.noteApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	

	@ExceptionHandler
	private ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){ 
		  String message=ex.getMessage(); 
		  ApiResponse response=new ApiResponse();
		  response.setMessage(message);	 
		  response.setStatus(HttpStatus.NOT_FOUND);
		  
		  return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	  }
	 
}
