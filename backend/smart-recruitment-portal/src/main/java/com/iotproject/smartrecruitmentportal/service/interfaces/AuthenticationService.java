package com.iotproject.smartrecruitmentportal.service.interfaces;

import com.iotproject.smartrecruitmentportal.dto.request.LoginRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.LoginResponse;

public interface AuthenticationService {
	ApiResponse<LoginResponse> login(LoginRequest request);
}
