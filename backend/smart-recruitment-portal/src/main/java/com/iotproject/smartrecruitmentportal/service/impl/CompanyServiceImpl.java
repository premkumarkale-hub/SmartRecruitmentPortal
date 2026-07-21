package com.iotproject.smartrecruitmentportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.iotproject.smartrecruitmentportal.dto.request.CompanyRequest;
import com.iotproject.smartrecruitmentportal.dto.response.CompanyResponse;
import com.iotproject.smartrecruitmentportal.entity.Company;
import com.iotproject.smartrecruitmentportal.exception.DuplicateResourceException;
import com.iotproject.smartrecruitmentportal.exception.ResourceNotFoundException;
import com.iotproject.smartrecruitmentportal.mapper.CompanyMapper;
import com.iotproject.smartrecruitmentportal.repository.CompanyRepository;
import com.iotproject.smartrecruitmentportal.service.interfaces.CompanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper companyMapper;
	private final CompanyRepository companyRepository;
	
	@Override
	public CompanyResponse createCompany(CompanyRequest request) {
		if(companyRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Company Email already exists.");
		}
		
		if(companyRepository.existsByPhone(request.getPhone())) {
			throw new DuplicateResourceException("Company phone already exists.");
		}
		
		Company company = companyMapper.toEntity(request);
		
		Company savedCompany = companyRepository.save(company);
		
		return companyMapper.toResponse(savedCompany);
	}

	@Override
	public List<CompanyResponse> getAllCompanies() {

		return companyRepository.findAll()
				.stream()
				.map(companyMapper::toResponse)
				.toList();
	}

	@Override
	public CompanyResponse getCompanyById(Long id) {
		Company company = companyRepository.findById(id)
					.orElseThrow(() ->
							new ResourceNotFoundException("Company not found with id : " + id));
		return companyMapper.toResponse(company);
	}

	@Override
	public CompanyResponse updateCompany(Long id, CompanyRequest request) {
		Company company = companyRepository.findById(id)
					.orElseThrow(() ->
							new ResourceNotFoundException("Company not found with id : " +id));
		
		if(!company.getEmail().equals(request.getEmail())
				&& companyRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Company email already exists.");
		}
		
		if(!company.getPhone().equals(request.getPhone())
				&& companyRepository.existsByPhone(request.getPhone())) {
			throw new DuplicateResourceException("Company phone already exists.");
		}
		
		companyMapper.updateEntity(company, request);
		
		Company updatedCompany = companyRepository.save(company);
		
		return companyMapper.toResponse(updatedCompany);
	}

	@Override
	public void deleteCompany(Long id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() ->
						new ResourceNotFoundException("Company not found with id : " +id));
		
		companyRepository.delete(company);
		
	}

}
