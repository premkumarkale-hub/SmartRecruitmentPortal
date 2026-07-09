package com.iotproject.smartrecruitmentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iotproject.smartrecruitmentportal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<User> findByPhone(String phone);

	boolean existsByPhone(String phone);

}
