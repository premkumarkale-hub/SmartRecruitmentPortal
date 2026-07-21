package com.iotproject.smartrecruitmentportal.service.interfaces;

import java.util.List;

import com.iotproject.smartrecruitmentportal.dto.request.CompanyRequest;
import com.iotproject.smartrecruitmentportal.dto.response.CompanyResponse;

public interface CompanyService {
	CompanyResponse createCompany(CompanyRequest request);
	
	List<CompanyResponse> getAllCompanies();
	
	CompanyResponse getCompanyById(Long id);
	
	CompanyResponse updateCompany(Long id, CompanyRequest request);
	
	void deleteCompany(Long id);
}
