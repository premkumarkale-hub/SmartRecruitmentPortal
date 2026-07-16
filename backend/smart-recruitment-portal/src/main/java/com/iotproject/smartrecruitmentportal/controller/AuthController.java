package com.iotproject.smartrecruitmentportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iotproject.smartrecruitmentportal.dto.request.LoginRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.LoginResponse;
import com.iotproject.smartrecruitmentportal.service.interfaces.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
			@Valid @RequestBody LoginRequest request){
		
		return ResponseEntity.ok(authenticationService.login(request));
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
