package com.iotproject.smartrecruitmentportal.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "company_name", nullable = false, length = 150)
	private String companyName;
	
	@Column(nullable = false, unique = true,length = 150)
	private String email;
	
	@Column(nullable = false, unique = true, length = 15)
	private String phone;
	
	@Column(length = 255)
	private String website;
	
	@Column(nullable = false, length = 100)
	private String industry;
	
	@Column(nullable = false, length = 200)
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime updatedAt;
}
