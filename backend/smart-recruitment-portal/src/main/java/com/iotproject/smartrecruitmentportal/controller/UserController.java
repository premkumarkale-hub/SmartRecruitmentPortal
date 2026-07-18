package com.iotproject.smartrecruitmentportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iotproject.smartrecruitmentportal.dto.request.UserRegistrationRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.UserResponse;
import com.iotproject.smartrecruitmentportal.service.interfaces.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponse>> registerUser(
			@Valid @RequestBody UserRegistrationRequest request) {
		
		UserResponse response = userService.registerUser(request);
		
		return  ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.<UserResponse>builder()
						.success(true)
						.message("User registered successfully.")
						.data(response)
						.build()
				);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(){
		return ResponseEntity.ok(
				ApiResponse.<List<UserResponse>>builder()
				.success(true)
				.message("Users fetched successfully")
				.data(userService.getAllUsers())
				.build()
		);		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> getUserById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(
				ApiResponse.<UserResponse>builder()
				.success(true)
				.message("User fetched successfully")
				.data(userService.getUserById(id))
				.build()
		);
				
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public  ResponseEntity<ApiResponse<String>> deleteUser(
			@PathVariable Long id,
			Authentication authentication){
		
		userService.deleteUser(id, authentication.getName());
		
		return ResponseEntity.ok(
				ApiResponse.<String>builder()
				.success(true)
				.message("User deleted successfully")
				.data(null)
				.build()
		);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> updateUser(
			@PathVariable Long id,
			@RequestBody UserRegistrationRequest request){
		UserResponse response = userService.updateUser(id,request);
		
		return ResponseEntity.ok(
				ApiResponse.<UserResponse>builder()
				.success(true)
				.message("User updated succcessfully")
				.data(response)
				.build());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

