package com.iotproject.smartrecruitmentportal.controller;

/*
 * API's
 * POST : http:localhost:9090/api/companies			  -----> Create new company
 * GET : http:localhost:9090/api/companies		   	  -----> Get all companies
 * GET : http:localhost:9090/api/companies/{id}	      -----> Get company by id
 * DELETE : http:localhost:9090/api/companies/{id}	  -----> Delete company by id
 * PUT : http:localhost:9090/api/companies/{id}	  	  -----> Update company by id
 * */


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iotproject.smartrecruitmentportal.dto.request.CompanyRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.CompanyResponse;
import com.iotproject.smartrecruitmentportal.service.interfaces.CompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;

	
	@PostMapping
	public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
			@Valid @RequestBody CompanyRequest companyRequest) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.<CompanyResponse>builder()
						.success(true)
						.message("Company registered successfully")
						.data(companyService.createCompany(companyRequest))
						.build()
				);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAllCompaines(){
		return ResponseEntity.ok(
				ApiResponse.<List<CompanyResponse>>builder()
					.success(true)
					.message("All companies fetched successfully")
					.data(companyService.getAllCompanies())
					.build()
		);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(
				ApiResponse.<CompanyResponse>builder()
				.success(true)
				.message("Company fetched successfully")
				.data(companyService.getCompanyById(id))
				.build()
		);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
			@PathVariable Long id,
			@Valid
			@RequestBody CompanyRequest companyRequest){
		
		return ResponseEntity.ok(
				ApiResponse.<CompanyResponse>builder()
				.success(true)
				.message("Company updated successfully")
				.data(companyService.updateCompany(id, companyRequest))
				.build()
		);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteCompany(
			@PathVariable Long id){
		
		companyService.deleteCompany(id);
		
		return ResponseEntity.ok(
				ApiResponse.<String>builder()
				.success(true)
				.message("Company deleted successfully")
				.data(null)
				.build()
		);
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}