package com.product.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	private static final org.slf4j.Logger log =LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		 log.error("Resource not found: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
		 log.error("Unexpected error occurred at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	        errorResponse.setMessage(ex.getMessage());
	        errorResponse.setPath(request.getRequestURI());
	        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	        
	 }

}
