package com.iotproject.smartrecruitmentportal.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iotproject.smartrecruitmentportal.dto.request.UserRegistrationRequest;
import com.iotproject.smartrecruitmentportal.dto.response.UserResponse;
import com.iotproject.smartrecruitmentportal.entity.Role;
import com.iotproject.smartrecruitmentportal.entity.User;
import com.iotproject.smartrecruitmentportal.exception.DuplicateResourceException;
import com.iotproject.smartrecruitmentportal.exception.ResourceNotFoundException;
import com.iotproject.smartrecruitmentportal.mapper.UserMapper;
import com.iotproject.smartrecruitmentportal.repository.RoleRepository;
import com.iotproject.smartrecruitmentportal.repository.UserRepository;
import com.iotproject.smartrecruitmentportal.service.interfaces.UserService;
import com.iotproject.smartrecruitmentportal.util.RoleType;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper,
			PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public UserResponse registerUser(UserRegistrationRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Email already exists.");
		}
		if (userRepository.existsByPhone(request.getPhone())) {
			throw new DuplicateResourceException("Phone number already exists.");
		}
		
		RoleType roleType;
		
		try{
			roleType = RoleType.valueOf(request.getRole().toUpperCase());
		} 
		catch (IllegalArgumentException ex) {
			throw new ResourceNotFoundException("Invalid role: " + request.getRole());
		}
		Role role = roleRepository.findByName(roleType.name())
				.orElseThrow(() -> 
					new ResourceNotFoundException("Role not found: " + roleType.name()));

		User user = userMapper.toEntity(request, role);

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		User savedUser = userRepository.save(user);

		return userMapper.toResponse(savedUser);

	}

}
