package com.java.noteApp.exception;


public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException() {
		super("Note Not found ");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	
}
