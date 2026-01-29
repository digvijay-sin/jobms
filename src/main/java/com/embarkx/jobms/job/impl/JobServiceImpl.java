package com.embarkx.jobms.job.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.JobRepository;
import com.embarkx.jobms.job.JobService;
import com.embarkx.jobms.job.client.CompanyClient;
import com.embarkx.jobms.job.client.ReviewClient;
import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;

import embarkx.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {

    
	private final JobRepository jobRepository;
	
	@Autowired
	private CompanyClient companyClient;
	
	@Autowired
	private ReviewClient reviewClient;
	
	@Autowired
	private RestTemplate restTemplate;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;		
	}

	@Override
	public Job createJob(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public List<JobDto> getAllJobs() {
		
		List<Job> jobs = jobRepository.findAll();		
		
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private JobDto convertToDto(Job job) {	
		
		
//		if(job == null) {
//			return null;
//		}
		
//		Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8082/companies/" + job.getCompanyId(), Company.class);
		
		Company company = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		
		
//		ResponseEntity<List<Review>> responseReview = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//		});
//		
//		List<Review> reviews = responseReview.getBody();
		
		JobDto jobDto = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
		
		return jobDto;
	}
	
	@Override
	public JobDto getJobById(Long id) { 
		Job job = jobRepository.findById(id).orElse(null);		
		return convertToDto(job);				
	}

	@Override
	public Job updateJob(Long id, Job updatedJob) {
		
		return jobRepository.findById(id).map(existing -> {
			existing.setTitle(updatedJob.getTitle());
			existing.setDescription(updatedJob.getDescription());
			existing.setLocation(updatedJob.getLocation());
			existing.setMaxSalary(updatedJob.getMaxSalary());
			existing.setMinSalary(updatedJob.getMinSalary());
			return jobRepository.save(existing);
		}).orElse(null);
	}

	@Override
	public boolean deleteJob(Long id) {
		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
