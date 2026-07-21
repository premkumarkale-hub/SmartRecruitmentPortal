package com.iotproject.smartrecruitmentportal.dto.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CompanyRequest {
	@NotBlank(message = "Company name is required")
	@Size(max = 150)
	private String companyName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
	private String phone;
	
	@Size(max = 150)
	@URL
	private String website;
	
	@Size(max = 150)
	@NotBlank(message = "Industry is required")
	private String industry;
	
	@Size(max = 150)
	@NotBlank(message = "Location is required")
	private String location;
	
	@Size(max = 1000)
	@NotBlank(message = "Description is required")
	private String description;
}
