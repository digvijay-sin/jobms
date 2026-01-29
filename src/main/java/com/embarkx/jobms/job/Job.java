package com.embarkx.jobms.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job_table")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String title;
    private String description;
    private String location;
    private double maxSalary;
    private double minSalary;
    
//    @JsonIgnoreProperties("jobs")
//    @ManyToOne
    private Long companyId;
    
    
	public Job() {
		
	}

	public Job(Long id, String title, String description, String location, double maxSalary, double minSalary, Long company) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.companyId = company;
	}
	
	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
    
    
}
