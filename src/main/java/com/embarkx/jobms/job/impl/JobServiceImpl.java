package com.embarkx.jobms.job.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.JobRepository;
import com.embarkx.jobms.job.JobService;
import com.embarkx.jobms.job.dto.JobWithCompanyDto;
import com.embarkx.jobms.job.external.Company;

import embarkx.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {

    
	private final JobRepository jobRepository;
	
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
	public List<JobWithCompanyDto> getAllJobs() {
		
		List<Job> jobs = jobRepository.findAll();		
		
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private JobWithCompanyDto convertToDto(Job job) {		
		if(job == null) {
			return null;
		}				
		Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8082/companies/" + job.getCompanyId(), Company.class);		
		JobWithCompanyDto jobWithCompany = JobMapper.mapToJobWithCompanyDto(job, company);
		return jobWithCompany;
	}
	
	@Override
	public JobWithCompanyDto getJobById(Long id) { 
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
