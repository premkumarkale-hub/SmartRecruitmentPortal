package com.iotproject.smartrecruitmentportal.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.iotproject.smartrecruitmentportal.dto.request.JobRequest;
import com.iotproject.smartrecruitmentportal.dto.response.JobResponse;
import com.iotproject.smartrecruitmentportal.entity.Company;
import com.iotproject.smartrecruitmentportal.entity.Job;
import com.iotproject.smartrecruitmentportal.entity.User;
import com.iotproject.smartrecruitmentportal.exception.DuplicateResourceException;
import com.iotproject.smartrecruitmentportal.exception.InvalidRequestException;
import com.iotproject.smartrecruitmentportal.exception.ResourceNotFoundException;
import com.iotproject.smartrecruitmentportal.mapper.JobMapper;
import com.iotproject.smartrecruitmentportal.repository.CompanyRepository;
import com.iotproject.smartrecruitmentportal.repository.JobRepository;
import com.iotproject.smartrecruitmentportal.repository.UserRepository;
import com.iotproject.smartrecruitmentportal.service.interfaces.JobService;
import com.iotproject.smartrecruitmentportal.util.JobStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
	
	private final JobRepository jobRepository;
	private final CompanyRepository companyRepository;
	private final JobMapper jobMapper;
	private final UserRepository userRepository;
	
	@Override
	public JobResponse createJob(JobRequest jobRequest) {
		if (jobRequest.getLastDateToApply().isBefore(LocalDate.now())) {
		    throw new InvalidRequestException(
		            "Last date to apply cannot be in the past.");
		}
		
		if(jobRepository.existsByTitleAndCompanyId(
				jobRequest.getTitle(), 
				jobRequest.getCompanyId())) {
			
			throw new DuplicateResourceException("Job already exists for this company.");
		}
		
		if (jobRequest.getLastDateToApply().isBefore(LocalDate.now())) {
		    throw new InvalidRequestException(
		            "Last date to apply cannot be in the past.");
		}
		
		Company company = companyRepository.findById(jobRequest.getCompanyId())
				.orElseThrow(() -> 
						new ResourceNotFoundException("Company not found with ID : " + jobRequest.getCompanyId()));
		
		Job job = jobMapper.toEntity(jobRequest);
		
		job.setCompany(company);
		
		job.setJobStatus(JobStatus.OPEN);
		
		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User recruiter = userRepository.findByEmail(email)
		        .orElseThrow(() ->
		                new ResourceNotFoundException("Recruiter not found"));

		job.setRecruiter(recruiter);
		
		Job savedJob = jobRepository.save(job);
		
		return jobMapper.toResponse(savedJob);
	}

	@Override
	public List<JobResponse> getAllJobs() {
		
		return jobRepository.findAll()
				.stream()
				.map(jobMapper::toResponse)
				.toList();
	}

	@Override
	public JobResponse getJobById(Long id) {
		Job job = jobRepository.findById(id)
				.orElseThrow(() ->
						new ResourceNotFoundException("Job not found with ID : " + id));
		return jobMapper.toResponse(job);
	}

	@Override
	public JobResponse updateJob(Long id, JobRequest jobRequest) {
		Job job = jobRepository.findById(id)
				.orElseThrow(() ->
						new ResourceNotFoundException("Job not found with ID : " + id));
		
		if(!job.getTitle().equals(jobRequest.getTitle())
				&& jobRepository.existsByTitleAndCompanyId(jobRequest.getTitle(),jobRequest.getCompanyId())) {
			throw new DuplicateResourceException("Job title and company id already exists.");
		}
		
		if (jobRequest.getLastDateToApply().isBefore(LocalDate.now())) {
		    throw new InvalidRequestException(
		            "Last date to apply cannot be in the past.");
		}
		
		jobMapper.updateJobEntity(job, jobRequest);
		
		Job updatedJob = jobRepository.save(job);
		
		return jobMapper.toResponse(updatedJob);
	}

	@Override
	public void deleteJob(Long id) {
		Job job = jobRepository.findById(id)
				.orElseThrow(() ->
						new ResourceNotFoundException("Job not found with ID : " + id));
		
		jobRepository.delete(job);
		
	}

}
