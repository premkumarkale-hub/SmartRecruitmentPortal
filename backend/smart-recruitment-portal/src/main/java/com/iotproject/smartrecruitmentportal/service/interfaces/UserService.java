package com.iotproject.smartrecruitmentportal.service.interfaces;
import java.util.List;

import com.iotproject.smartrecruitmentportal.dto.request.UserRegistrationRequest;
import com.iotproject.smartrecruitmentportal.dto.response.UserResponse;

public interface UserService {
	UserResponse registerUser(UserRegistrationRequest request);

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long id);

	void deleteUser(Long id, String loggedInUserEmail);

	UserResponse updateUser(Long id, UserRegistrationRequest request);
}

