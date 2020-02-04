package com.spmia.license.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spmia.license.model.License;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
	
	@RequestMapping(value="/{licenseId}", method = RequestMethod.GET)
	public License getLicense(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		License license = new License();
		license.setId(licenseId);
		license.setLicenseType("test type");
		license.setProductName("test product");
		license.setOrganizationId(organizationId);
		return license;
	}
 
}
