package com.iotproject.smartrecruitmentportal.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iotproject.smartrecruitmentportal.entity.Role;
import com.iotproject.smartrecruitmentportal.repository.RoleRepository;

@Configuration
public class RoleDataInitializer {
	
	@Bean
	CommandLineRunner initializeRoles(RoleRepository roleRepository) {
		return args -> {
			List<String> roles = List.of(
					"ADMIN",
					"RECRUITER",
					"CANDIDATE"
			);
			
			for(String roleName : roles) {
				if(!roleRepository.existsByName(roleName)) {
					roleRepository.save(
							Role.builder()
								.name(roleName)
								.build()
					);
				}
			}
		};
	}
}
