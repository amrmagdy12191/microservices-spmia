package com.spmia.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
	@Value("${example.property}")
	private String exampleProperty;

	public String getExampleProperty() {
		return exampleProperty;
	}
	
	//@Value("${signing.key}")
		private String jwtSigningKey = "1593304145";

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}
	
	
}
