package com.embarkx.jobms.job;

import java.util.List;
import java.util.Optional;

import com.embarkx.jobms.job.dto.JobWithCompanyDto;

public interface JobService {
	Job createJob(Job job);
    List<JobWithCompanyDto> getAllJobs();
    JobWithCompanyDto getJobById(Long id);
    Job updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
