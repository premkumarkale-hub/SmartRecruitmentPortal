package com.iotproject.smartrecruitmentportal.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
	@NotBlank(message = "Full name is required")
	@Size(min = 3, max = 100)
	private String fullName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email")
	private String email;

	@NotBlank(message = "Password is requried")
	@Size(min = 8, max = 20)
	private String password;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
	private String phone;

	@NotBlank(message = "Role is required")
	private String role;

}
