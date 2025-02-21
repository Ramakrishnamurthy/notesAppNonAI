package com.java.noteApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.java.noteApp.exception.ApiResponse;
import com.java.noteApp.exception.GlobalExceptionHandler;
import com.java.noteApp.exception.ResourceNotFoundException;


/*
 * This is GlobalExceptionHandlerTest contain test cases;
 * 
 * @author Shilpi
 * @since 2025-02-18
 */
public class GlobalExceptionHandlerTest {

    public GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

	/* Handles test cases for ResourceNotFoundException */
    @Test
    public void testHandlerResourceNotFoundException() {
        // Mocking ResourceNotFoundException
        ResourceNotFoundException ex = mock(ResourceNotFoundException.class);
        when(ex.getMessage()).thenReturn("Resource not found");

        // Invoking the handler method
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handlerResourceNotFoundException(ex);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getBody().getStatus());
    }

    /* Handles test cases for Exception */
    @Test
    public void testHandleException() {
        // Mocking Exception
        Exception ex = mock(Exception.class);
        when(ex.getMessage()).thenReturn("Internal Server Error");

        // Invoking the handler method
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleException(ex);

        // Assertions
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Internal Server Error", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getBody().getStatus());
    }
}