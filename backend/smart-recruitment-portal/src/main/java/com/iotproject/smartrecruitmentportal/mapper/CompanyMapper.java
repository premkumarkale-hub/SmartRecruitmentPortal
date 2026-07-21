package com.iotproject.smartrecruitmentportal.mapper;

import org.springframework.stereotype.Component;

import com.iotproject.smartrecruitmentportal.dto.request.CompanyRequest;
import com.iotproject.smartrecruitmentportal.dto.response.CompanyResponse;
import com.iotproject.smartrecruitmentportal.entity.Company;

@Component
public class CompanyMapper {
	public Company toEntity(CompanyRequest request) {
		return Company.builder()
				.companyName(request.getCompanyName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.website(request.getWebsite())
				.industry(request.getIndustry())
				.location(request.getLocation())
				.description(request.getDescription())
				.build();
				
	}
	
	public CompanyResponse toResponse(Company company) {
		return CompanyResponse.builder()
				.id(company.getId())
				.companyName(company.getCompanyName())
				.email(company.getEmail())
				.phone(company.getPhone())
				.website(company.getWebsite())
				.industry(company.getIndustry())
				.location(company.getLocation())
				.description(company.getDescription())
				.createdAt(company.getCreatedAt())
				.updatedAt(company.getUpdatedAt())
				.build();
	}
	
	public void updateEntity(Company company, CompanyRequest request) {
		company.setCompanyName(request.getCompanyName());
		company.setEmail(request.getEmail());
		company.setPhone(request.getPhone());
		company.setWebsite(request.getWebsite());
		company.setIndustry(request.getIndustry());
		company.setLocation(request.getLocation());
		company.setDescription(request.getDescription());
	}
}
