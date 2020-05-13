package com.spmia.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spmia.license.model.License;
import com.spmia.license.services.LicenseService;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
	
	@Autowired
	LicenseService licenseService; 
	
	@RequestMapping(value="/{licenseId}", method = RequestMethod.GET)
	public License getLicense(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
//		License license = new License();
//		license.setLicenseId(licenseId);
//		license.setLicenseType("test type");
//		license.setProductName("test product");
//		license.setOrganizationId(organizationId);
		License license = licenseService.getLicense(organizationId, licenseId);
		return license;
	}
	
	@RequestMapping(value="/{licenseId}/{client}")
	public License getLicenceWithClient(@PathVariable String licenseId,
			@PathVariable String clientType,
			@PathVariable String organizationId) {
		licenseService.getLicense(organizationId, licenseId, clientType);
		return null;
	}
 
}
