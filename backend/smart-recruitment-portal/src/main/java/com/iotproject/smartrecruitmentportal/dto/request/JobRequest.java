package com.iotproject.smartrecruitmentportal.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.iotproject.smartrecruitmentportal.util.EmploymentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
	
	@NotBlank(message = "Job title is required")
	@Size(min = 3, max = 100)
	private String title;
	
	@NotBlank(message = "Job description is required")
	@Size(max = 1000)
	private String description;
	
	@NotBlank(message = "Job location is required")
	@Size(max = 150)
	private String location;
	
	@NotNull(message = "Job experience is requried")
	@PositiveOrZero(message = "Experience cannot be negative")
	private Integer experience;
	
	@NotNull(message = "Job employment type is required")
	private EmploymentType employmentType;
	
	@NotNull(message = "Job salary is required")
	@Positive(message = "Salary must be greater than zero")
	private BigDecimal salary;
	
	@NotNull(message = "Job last date to apply is required")
	private LocalDate lastDateToApply;
	
	@NotNull(message = "Company id is required")
	private Long companyId;
}
