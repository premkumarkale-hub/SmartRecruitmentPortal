package com.iotproject.smartrecruitmentportal.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iotproject.smartrecruitmentportal.entity.Role;
import com.iotproject.smartrecruitmentportal.repository.RoleRepository;
import com.iotproject.smartrecruitmentportal.util.RoleType;

@Configuration
public class RoleDataInitializer {
	
	@Bean
	CommandLineRunner initializeRoles(RoleRepository roleRepository) {
		return args -> {
			for(RoleType roleType : RoleType.values()) {
				if(!roleRepository.existsByName(roleType.name())) {
					roleRepository.save(
							Role.builder()
								.name(roleType.name())
								.build()
					);
				}
			}
		};
	}
}
