package com.spmia.license.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.ws.FaultAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spmia.license.clients.OrganizationFeignClient;
import com.spmia.license.clients.OrganizationRestTemplateClient;
import com.spmia.license.config.ServiceConfig;
import com.spmia.license.model.License;
import com.spmia.license.model.Organization;
import com.spmia.license.repository.LicenseRepository;


@Service
//@DefaultProperties(commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="10000")})
public class LicenseService {
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private ServiceConfig serviceConfig;
	
//	@Autowired
//	private OrganizationDiscoveryClient organizationClient;
	
	@Autowired
	private OrganizationRestTemplateClient organizationRestTemplateClient;
	
	@Autowired
	private OrganizationFeignClient organizationFeignClient;
	
	public License getLicense(String organizationId, String licenseId) {
		License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		return license.withComment(serviceConfig.getExampleProperty());
	}
	
	public List<License> getLicensesByOrganizationId(String organizationId){
		return licenseRepository.findByOrganizationId(organizationId);
	}
	
	// be carefaul using @HystrixCommand without parameter because it will use same thread cause problems
	@HystrixCommand(fallbackMethod = "buildFallbackLicenseList", // circuit breaker -- interrupt calls more than 1000 ms
			threadPoolKey = "LicenseThreadPool", // mak different thread pool for every or some services that may take longer time
			threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "30"),
									@HystrixProperty(name = "maxQueueSize", value = "10")},
			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
								@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
								@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
								@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
								@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
								@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")})
	public List<License> getLicensesByOrg(String organizationId){
		//randomlyRunLong(); // for testing circuit breaker
		
		return licenseRepository.findByOrganizationId(organizationId);
	}
	
	// Circuit breaker alternat action (fallback method) -- same signature of original method
	private List<License> buildFallbackLicenseList(String organizationId) {
		List<License> fallbackList = new ArrayList<>();
		License license = new License();
		license.withId("0000-00-0000")
		.withProductName("sorry, No License List found")
		.withOrganizationId(organizationId);
		fallbackList.add(license);
		return fallbackList;
	}
	
	public void saveLicense(License license) {
		license.setLicenseId(UUID.randomUUID().toString());
		licenseRepository.save(license);
	}
	
	public License getLicense(String organizationId, String licenseId, String clientType) {
		License licencse = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		Organization organization = retrieveInfo(organizationId, clientType);
		
		return licencse.withOrganizationName(organization.getName())
				.withContactName(organization.getContactName())
				.withContactEmail(organization.getContactEmail())
				.withContactPhone(organization.getContactPhone())
				.withComment(serviceConfig.getExampleProperty());
		
	}
	
	private Organization retrieveInfo(String organizationId, String clientType) {
		//return organizationClient.getOrganization(organizationId);
//		return organizationRestTemplateClient.getOrganization(organizationId);
		return organizationFeignClient.getOrganization(organizationId);
	}
	
	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum= rand.nextInt((3-1)+1);
		
//		if (randomNum ==3) {
			try {
				Thread.sleep(11000);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
	}
	
	

}
