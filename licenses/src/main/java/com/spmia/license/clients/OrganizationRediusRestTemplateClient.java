package com.spmia.license.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spmia.license.model.Organization;
import com.spmia.license.model.redius.OrganizationRedisRepository;
import com.spmia.license.utils.UserContextHolder;


@Component
public class OrganizationRediusRestTemplateClient {
	
	@Autowired
	OAuth2RestTemplate restTemplate;
	
	@Autowired
	OrganizationRedisRepository organizationRedisRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRediusRestTemplateClient.class);

    public Organization getOrganization(String organizationId){
        logger.debug("In Licensing Service.getOrganization: {}", UserContextHolder.getContext().getCorrelationId());
        
        Organization org = checkRedisCache(organizationId);
        if (org!=null){
          logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
          return org;
        }
        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
        
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://localhost:8762/api/organizationservice/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        
        org = restExchange.getBody();
        
        if(org != null) {
        	cacheOrganizationObject(org);
        }

        return org;
    }
    
    private Organization checkRedisCache(String organizationId) {
    	try {
    		return organizationRedisRepository.findOrganization(organizationId);
    	}catch (Exception e) {
    		logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.Exception {}", organizationId, e);
		}
    	return null;
    }
    
    private void cacheOrganizationObject(Organization organization) {
    	try {
    		organizationRedisRepository.saveOrganization(organization);
    	}catch (Exception e) {
    		logger.error("Unable to cache organization {} in Redis.Exception {}", organization.getId(), e);
    		 
		}
    }

}
