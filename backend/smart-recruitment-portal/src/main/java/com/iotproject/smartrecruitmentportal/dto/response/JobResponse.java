package com.iotproject.smartrecruitmentportal.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.iotproject.smartrecruitmentportal.util.EmploymentType;
import com.iotproject.smartrecruitmentportal.util.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
	private Long id;
	private String title;
	private String description;
	private String location;
	private Integer experience;
	private EmploymentType employmentType;
	private BigDecimal salary;
	private LocalDate lastDateToApply;
	private JobStatus jobStatus;
	private Long companyId;
	private String companyName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
