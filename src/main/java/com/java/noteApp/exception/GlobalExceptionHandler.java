package com.java.noteApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * This is GlobalExceptionHandler  class contains logic  for handling exception.
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

/*
 * @ControllerAdvice annotation is used to handle exceptions globally
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	
	/* 
	 * @ExceptionHandler annotation is used to handle ResourceNotFoundException 
	 */
	@ExceptionHandler
	private ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){ 
		  String message=ex.getMessage(); 
		  ApiResponse response=new ApiResponse();
		  response.setMessage(message);	 
		  response.setStatus(HttpStatus.NOT_FOUND);
		  
		  return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);  // Response with HTTP 404 status
	  }
	 
}
