package com.embarkx.jobms.job.dto;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.external.Company;

public class JobWithCompanyDto {
	private Long id;
    private String title;
    private String description;
    private String location;
    private double maxSalary;
    private double minSalary;	
	private Company company;	
	
	public JobWithCompanyDto(Long id, String title, String description, String location, double maxSalary,
			double minSalary, Company company) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public Company getCompany() {
		return company;
	}
	
	public JobWithCompanyDto() {
		
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
