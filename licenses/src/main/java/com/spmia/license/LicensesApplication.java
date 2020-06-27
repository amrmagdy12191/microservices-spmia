package com.spmia.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
//@EnableDiscoveryClient //enable eureka service discovery without Ribbon -- DiscoveryClient (auto wired)
@EnableFeignClients // enable feign client if it is used instead of rest template
//@EnableEurekaClient //enable eureka client
@EnableCircuitBreaker // circuit breaker
@EnableResourceServer // service protected by spring security oauth2
public class LicensesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensesApplication.class, args);
	}
	
	@LoadBalanced //enable Ribbon load balancing (used with service discovery server (Eureka) -> 'configured in application.yml' )
	@Bean // enable restTemplate to get service from service discovery server (Eureka) or outside service "outside Eureka"(outside URL)
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	


}
