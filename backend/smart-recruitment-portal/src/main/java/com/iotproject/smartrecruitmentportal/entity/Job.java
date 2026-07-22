package com.iotproject.smartrecruitmentportal.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.iotproject.smartrecruitmentportal.util.EmploymentType;
import com.iotproject.smartrecruitmentportal.util.JobStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column(nullable = false, length = 150)
	private String location;
	
	@Column(nullable = false)
	private Integer experience;
	
	@Column(nullable = false)
	private BigDecimal salary;
	
	@Column(nullable = false)
	private LocalDate lastDateToApply;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EmploymentType employmentType;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private JobStatus jobStatus;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recruiter_id", nullable = false)
	private User recruiter;
	
}
