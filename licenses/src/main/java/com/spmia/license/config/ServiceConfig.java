package com.spmia.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
	@Value("${example.property}")
	private String exampleProperty;
	
	//@Value("${signing.key}")
			private String jwtSigningKey = "1593304145";
	
	//@Value("${redis.server}")
	private String redisServer="localhost";

	//@Value("${redis.port}")
	private String redisPort="6370";

	public String getRedisServer() {
		return redisServer;
	}

	public String getRedisPort() {
		return redisPort;
	}

	public String getExampleProperty() {
		return exampleProperty;
	}
	
	

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}
	
	
}
