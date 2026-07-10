package com.iotproject.smartrecruitmentportal.service.interfaces;
import com.iotproject.smartrecruitmentportal.dto.request.UserRegistrationRequest;
import com.iotproject.smartrecruitmentportal.dto.response.UserResponse;

public interface UserService {
	UserResponse registerUser(UserRegistrationRequest request);
}

