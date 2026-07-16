package com.iotproject.smartrecruitmentportal.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	
	private String token;
	
	@Builder.Default
	private String type="Bearer";
	
	private Long userId;
	
	private String fullName;
	
	private String email;
	
	private String role;
}
