package com.iotproject.smartrecruitmentportal.mapper;

import org.springframework.stereotype.Component;

import com.iotproject.smartrecruitmentportal.dto.request.UserRegistrationRequest;
import com.iotproject.smartrecruitmentportal.dto.response.UserResponse;
import com.iotproject.smartrecruitmentportal.entity.Role;
import com.iotproject.smartrecruitmentportal.entity.User;

@Component
public class UserMapper {
	public User toEntity(UserRegistrationRequest request, Role role) {
		return User.builder()
				.fullName(request.getFullName())
				.email(request.getEmail())
				.password(request.getPassword())
				.phone(request.getPhone())
				.role(role)
				.build();
	}
	
	public UserResponse toResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.fullName(user.getFullName())
				.email(user.getEmail())
				.phone(user.getPhone())
				.role(user.getRole().getName())
				.createdAt(user.getCreatedAt())
				.build();
	}
}
