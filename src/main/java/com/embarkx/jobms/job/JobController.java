package com.embarkx.jobms.job;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.embarkx.jobms.job.dto.JobDto;

@RestController
@RequestMapping("/jobs") // - sets the base URL for all the methods in this class, when used at class level
public class JobController {
	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}


	@PostMapping
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		return ResponseEntity.ok(jobService.createJob(job));
	}


	@GetMapping
	public ResponseEntity<List<JobDto>> getAllJobs() {
		return ResponseEntity.ok(jobService.getAllJobs());
	}


	//@GetMapping("/{id}")
	@RequestMapping(value="/{id}" ,method = RequestMethod.GET) //-- at method level it just act as mapper for the particular request 
	public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
		JobDto jobDto = jobService.getJobById(id);
		if(jobDto != null) {
			return ResponseEntity.ok(jobDto);	
			
		}
		return ResponseEntity.notFound().build();
	}


	@PutMapping("/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
		Job updated = jobService.updateJob(id, job);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
		if (jobService.deleteJob(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
