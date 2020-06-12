package com.spmia.license.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spmia.license.clients.OrganizationFeignClient;
import com.spmia.license.clients.OrganizationRestTemplateClient;
import com.spmia.license.config.ServiceConfig;
import com.spmia.license.model.License;
import com.spmia.license.model.Organization;
import com.spmia.license.repository.LicenseRepository;

@Service
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

}
