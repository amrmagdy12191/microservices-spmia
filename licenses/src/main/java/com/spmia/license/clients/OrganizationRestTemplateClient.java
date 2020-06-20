package com.spmia.license.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spmia.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {
	@Autowired
	RestTemplate restTemplate;
	
//	@HystrixCommand // circuit breaker
//	(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")})
	public Organization getOrganization(String organizationId) {
//		try {
//			Thread.sleep(11000); // test circuit breaker
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		ResponseEntity<Organization> responseEntity = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", HttpMethod.GET, null ,Organization.class, organizationId);
		return responseEntity.getBody();
	}
	
	
}
