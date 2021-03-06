package com.spmia.license.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spmia.license.model.Organization;
import com.spmia.license.utils.UserContext;
import com.spmia.license.utils.UserContextHolder;

@Component
public class OraganizationOauth2RestTemplateClient {
	 	@Autowired
	    RestTemplate restTemplate;
	 	

	    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

	    public Organization getOrganization(String organizationId){
	        logger.debug("In Licensing Service.getOrganization: {}", UserContextHolder.getContext().getCorrelationId());

//	        ResponseEntity<Organization> restExchange =
//	                restTemplate.exchange(
//	                        "http://zuulserver:8761/api/organization/v1/organizations/{organizationId}",
//	                        HttpMethod.GET,
//	                        null, Organization.class, organizationId);
	        
	        ResponseEntity<Organization> restExchange =
	                restTemplate.exchange(
	                        "http://organizationservice/v1/organizations/{organizationId}",
	                        HttpMethod.GET,
	                        null, Organization.class, organizationId);

	        return restExchange.getBody();
	    }
}
