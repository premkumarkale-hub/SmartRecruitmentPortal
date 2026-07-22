package com.iotproject.smartrecruitmentportal.mapper;

import org.springframework.stereotype.Component;

import com.iotproject.smartrecruitmentportal.dto.request.JobRequest;
import com.iotproject.smartrecruitmentportal.dto.response.JobResponse;
import com.iotproject.smartrecruitmentportal.entity.Job;


@Component
public class JobMapper {
	public Job toEntity(JobRequest jobRequest) {
		return Job.builder()
				.title(jobRequest.getTitle())
				.description(jobRequest.getDescription())
				.location(jobRequest.getLocation())
				.experience(jobRequest.getExperience())
				.employmentType(jobRequest.getEmploymentType())
				.salary(jobRequest.getSalary())
				.lastDateToApply(jobRequest.getLastDateToApply())
				.build();
	}
	
	public JobResponse toResponse(Job job) {
		return JobResponse.builder()
				.id(job.getId())
				.title(job.getTitle())
				.companyId(job.getCompany().getId())
				.companyName(job.getCompany().getCompanyName())
				.description(job.getDescription())
				.location(job.getLocation())
				.jobStatus(job.getJobStatus())
				.experience(job.getExperience())
				.employmentType(job.getEmploymentType())
				.salary(job.getSalary())
				.lastDateToApply(job.getLastDateToApply())
				.createdAt(job.getCreatedAt())
				.updatedAt(job.getUpdatedAt())
				.build();
	}
	
	public void updateJobEntity(Job job , JobRequest jobRequest) {
		job.setTitle(jobRequest.getTitle());
		job.setDescription(job.getDescription());
		job.setLocation(jobRequest.getLocation());
		job.setExperience(jobRequest.getExperience());
		job.setEmploymentType(jobRequest.getEmploymentType());
		job.setSalary(jobRequest.getSalary());
		job.setLastDateToApply(jobRequest.getLastDateToApply());
	}
}
