package com.iotproject.smartrecruitmentportal.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.iotproject.smartrecruitmentportal.dto.request.LoginRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.LoginResponse;
import com.iotproject.smartrecruitmentportal.entity.User;
import com.iotproject.smartrecruitmentportal.exception.ResourceNotFoundException;
import com.iotproject.smartrecruitmentportal.repository.UserRepository;
import com.iotproject.smartrecruitmentportal.security.jwt.JwtService;
import com.iotproject.smartrecruitmentportal.service.interfaces.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserRepository userRepository;
	
	@Override
	public ApiResponse<LoginResponse> login(LoginRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() ->
						new ResourceNotFoundException("User not found with email : " + request.getEmail()));
		
		String token = jwtService.generateToken(userDetails);
		
		LoginResponse  response = LoginResponse.builder()
									.token(token)
									.userId(user.getId())
									.fullName(user.getFullName())
									.email(user.getEmail())
									.role(user.getRole().getName())
									.build();
		
		return ApiResponse.<LoginResponse>builder()
									.success(true)
									.message("Login successful")
									.data(response)
									.build();
	}

}
