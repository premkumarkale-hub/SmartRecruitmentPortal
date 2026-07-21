package com.iotproject.smartrecruitmentportal.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {
	private Long id;
	private String companyName;
	private String email;
	private String phone;
	private String website;
	private String industry;
	private String location;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
