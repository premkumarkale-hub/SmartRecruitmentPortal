package com.iotproject.smartrecruitmentportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
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
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ApiResponse<Object>> invalidRequest(InvalidRequestException ex){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.builder()
						.success(false)
						.message(ex.getMessage())
						.data(null)
						.build()
				);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    String message = ex.getBindingResult()
	            .getFieldError()
	            .getDefaultMessage();

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(ApiResponse.builder()
	                    .success(false)
	                    .message(message)
	                    .data(null)
	                    .build());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(
	        AccessDeniedException ex) {

	    return ResponseEntity.status(HttpStatus.FORBIDDEN)
	            .body(ApiResponse.builder()
	                    .success(false)
	                    .message("You are not authorized to perform this action.")
	                    .data(null)
	                    .build());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleServerErrorException(
	        Exception ex) {

	    ex.printStackTrace(); // Helpful during development

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(ApiResponse.builder()
	                    .success(false)
	                    .message("An unexpected error occurred. Please try again later.")
	                    .data(null)
	                    .build());
	}
	
	
	
	
	
	
	
	
	
	
}
