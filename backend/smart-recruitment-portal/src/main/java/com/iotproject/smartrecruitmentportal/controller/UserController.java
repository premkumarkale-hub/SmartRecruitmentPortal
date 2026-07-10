package com.iotproject.smartrecruitmentportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
}

