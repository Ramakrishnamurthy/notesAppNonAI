package com.java.noteApp.exception;


/*
 * This is ResourceNotFoundException  class extending RuntimeException ,
 * represents a custom exception that is thrown a resource/note is not-found.
 * @Param the constructor accepts a String message .
 * 
 * @author Shilpi
 * @since 2025-02-18
 */

public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException() {
		super("Note Not found ");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	
}
