package embarkx.jobms.job.mapper;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.dto.JobWithCompanyDto;
import com.embarkx.jobms.job.external.Company;

public class JobMapper {
	
	public static JobWithCompanyDto mapToJobWithCompanyDto(Job job, Company company) {
		
		JobWithCompanyDto jobWithCompany = new JobWithCompanyDto();
		
		jobWithCompany.setDescription(job.getDescription());
		jobWithCompany.setId(job.getId());
		jobWithCompany.setLocation(job.getLocation());
		jobWithCompany.setMaxSalary(job.getMaxSalary());
		jobWithCompany.setMaxSalary(job.getMinSalary());
		jobWithCompany.setTitle(job.getTitle());
		jobWithCompany.setCompany(company);
		
		return jobWithCompany;
	}
}
