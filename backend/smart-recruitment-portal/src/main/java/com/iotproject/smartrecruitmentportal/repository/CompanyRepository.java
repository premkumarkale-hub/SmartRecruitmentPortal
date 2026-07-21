package com.iotproject.smartrecruitmentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotproject.smartrecruitmentportal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	boolean existsByEmail(String email);
	
	boolean existsByPhone(String phone);
	
	Optional<Company> findByEmail(String email);
	
	Optional<Company> findByCompanyName(String companyName);
	
}
