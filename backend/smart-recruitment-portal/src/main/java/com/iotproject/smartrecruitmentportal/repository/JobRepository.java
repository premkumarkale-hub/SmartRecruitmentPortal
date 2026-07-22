package com.iotproject.smartrecruitmentportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iotproject.smartrecruitmentportal.entity.Job;
import com.iotproject.smartrecruitmentportal.util.EmploymentType;
import com.iotproject.smartrecruitmentportal.util.JobStatus;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	boolean existsById(Long id);
	
	boolean existsByTitleAndCompanyId(String title, Long companyId);
	
//	Optional<Job> findByTitle(String title);
//	
//	List<Job> findByCompanyId(Long companyId);
//	
//	List<Job> findByStatus(JobStatus jobStatus);
//	
//	List<Job> findByEmploymentType(EmploymentType employmentType);
	
	
}
