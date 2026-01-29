package embarkx.jobms.job.mapper;

import java.util.List;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;

public class JobMapper {
	
	public static JobDto mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews) {
		
		JobDto jobDto = new JobDto();
		
		jobDto.setDescription(job.getDescription());
		jobDto.setId(job.getId());
		jobDto.setLocation(job.getLocation());
		jobDto.setMaxSalary(job.getMaxSalary());
		jobDto.setMaxSalary(job.getMinSalary());
		jobDto.setTitle(job.getTitle());		
		jobDto.setCompany(company);
		jobDto.setReviews(reviews);
		
		
		return jobDto;
	}
}
