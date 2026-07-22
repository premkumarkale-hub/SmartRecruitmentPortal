package com.iotproject.smartrecruitmentportal.controller;

/*
 * API's
 * POST : http:localhost:9090/api/jobs 	  		  -----> Create new job
 * GET : http:localhost:9090/api/jobs		   	  -----> Get all jobs
 * GET : http:localhost:9090/api/jobs/{id}	      -----> Get job by id
 * DELETE : http:localhost:9090/api/jobs/{id}	  -----> Delete job by id
 * PUT : http:localhost:9090/api/jobs/{id}	  	  -----> Update job by id
 * */

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iotproject.smartrecruitmentportal.dto.request.JobRequest;
import com.iotproject.smartrecruitmentportal.dto.response.ApiResponse;
import com.iotproject.smartrecruitmentportal.dto.response.JobResponse;
import com.iotproject.smartrecruitmentportal.service.interfaces.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
	
	private final JobService jobService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<JobResponse>> createJob(
			@Valid @RequestBody JobRequest jobRequest) {
		
		return ResponseEntity.ok(
				ApiResponse.<JobResponse>builder()
				.success(true)
				.message("Job created successfully")
				.data(jobService.createJob(jobRequest))
				.build()
		);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<JobResponse>>> getAllJobs(){
		return ResponseEntity.ok(
				ApiResponse.<List<JobResponse>>builder()
				.success(true)
				.message("All jobs fetched successfully")
				.data(jobService.getAllJobs())
				.build()
		);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<JobResponse>> getJobById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(
				ApiResponse.<JobResponse>builder()
				.success(true)
				.message("Job fetched successfully")
				.data(jobService.getJobById(id))
				.build()
		);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<JobResponse>> updateJob(
			@PathVariable Long id,
			@Valid
			@RequestBody JobRequest jobRequest){
		
		return ResponseEntity.ok(
				ApiResponse.<JobResponse>builder()
				.success(true)
				.message("Job updated successfully")
				.data(jobService.updateJob(id, jobRequest))
				.build()
		);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<JobResponse>> deleteJob(
			@PathVariable Long id){
		
		jobService.deleteJob(id);
		
		return ResponseEntity.ok(
				ApiResponse.<JobResponse>builder()
				.success(true)
				.message("Job deleted successfully")
				.data(null)
				.build()
		);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
