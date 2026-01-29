package com.embarkx.jobms.job;

import java.util.List;

import com.embarkx.jobms.job.dto.JobDto;

public interface JobService {
	Job createJob(Job job);
    List<JobDto> getAllJobs();
    JobDto getJobById(Long id);
    Job updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
