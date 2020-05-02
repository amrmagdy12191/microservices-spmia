package com.spmia.license.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spmia.license.config.ServiceConfig;
import com.spmia.license.model.License;
import com.spmia.license.repository.LicenseRepository;

@Service
public class LicenseService {
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private ServiceConfig serviceConfig;
	
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

}
