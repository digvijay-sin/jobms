package com.embarkx.jobms.job.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	@LoadBalanced // necessary to use as it allows to use spring cloud and eureka services
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
