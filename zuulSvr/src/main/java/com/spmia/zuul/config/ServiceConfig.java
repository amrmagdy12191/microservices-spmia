package com.spmia.zuul.config;

import org.springframework.stereotype.Component;

@Component
//@Configuration
public class ServiceConfig {
	//@Value("${signing.key}")
	private String jwtSigningKey = "345345fsdfsf5345";

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}
}
