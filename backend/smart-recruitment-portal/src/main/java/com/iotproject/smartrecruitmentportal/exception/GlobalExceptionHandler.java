package com.iotproject.smartrecruitmentportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse<Object>> handleDuplicateResource(
			DuplicateResourceException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.builder()
						.success(false)
						.message(ex.getMessage())
						.data(null)
						.build()
				);
						
				
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(
			ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.builder()
						.success(false)
						.message(ex.getMessage())
						.data(null)
						.build()
				);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleException(
			Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.builder()
						.success(false)
						.message(ex.getMessage())
						.data(null)
						.build()
				);
				
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex){
		
		ApiResponse<Object> response = ApiResponse.builder()
				.success(false)
				.message("Invalid email or password")
				.data(null)
				.build();
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
