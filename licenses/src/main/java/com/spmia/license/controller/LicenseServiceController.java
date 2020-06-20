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
	
	//test url : localhost:8181/v1/organizations/442adb6e-fa58-47f3-9ca2-ed1fecdfe86c/licenses/08dbe05-606e-4dad-9d33-90ef10e334f9
	@RequestMapping(value="/{licenseId}", method = RequestMethod.GET)
	public License getLicense(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
//		License license = new License();
//		license.setLicenseId(licenseId);
//		license.setLicenseType("test type");
//		license.setProductName("test product");
//		license.setOrganizationId(organizationId);
		License license = licenseService.getLicense(organizationId, licenseId, "test");
		return license;
	}
	
	// test url : localhost:8181/v1/organizations/442adb6e-fa58-47f3-9ca2-ed1fecdfe86c/licenses/08dbe05-606e-4dad-9d33-90ef10e334f9/test
	@RequestMapping(value="/{licenseId}/{clientType}")
	public License getLicenceWithClient(@PathVariable String licenseId,
			@PathVariable String clientType,
			@PathVariable String organizationId) {
//		return licenseService.getLicense(organizationId, licenseId, clientType);
		return licenseService.getLicensesByOrg(organizationId).get(0);
	}
 
}
