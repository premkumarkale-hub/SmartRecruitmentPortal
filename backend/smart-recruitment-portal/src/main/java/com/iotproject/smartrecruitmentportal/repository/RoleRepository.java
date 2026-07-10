package com.iotproject.smartrecruitmentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iotproject.smartrecruitmentportal.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);

	boolean existsByName(String name);
}
