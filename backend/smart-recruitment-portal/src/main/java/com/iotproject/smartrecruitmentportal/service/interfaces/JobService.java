package com.iotproject.smartrecruitmentportal.service.interfaces;

import java.util.List;

import com.iotproject.smartrecruitmentportal.dto.request.JobRequest;
import com.iotproject.smartrecruitmentportal.dto.response.JobResponse;

public interface JobService {
	public JobResponse createJob(JobRequest jobRequest);
	
	public List<JobResponse> getAllJobs();
	
	public JobResponse getJobById(Long id);
	
	public JobResponse updateJob(Long id, JobRequest jobRequest);
	
	public void deleteJob(Long id);
}
