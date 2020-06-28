package com.spmia.license;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import com.spmia.license.utils.UserContextInterceptor;

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
	
//	@LoadBalanced //enable Ribbon load balancing (used with service discovery server (Eureka) -> 'configured in application.yml' )
//	@Bean // enable restTemplate to get service from service discovery server (Eureka) or outside service "outside Eureka"(outside URL)
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

	//not working ignore it, this Ffor your refrence only
//	@Bean
//	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext auth2ClientContext,
//			OAuth2ProtectedResourceDetails auth2ProtectedResourceDetails) {
//		return new OAuth2RestTemplate(auth2ProtectedResourceDetails, auth2ClientContext);
//	}
	
	/**
	 * To ensure Oauth2 token is propogated
	 * @return
	 */
	@Bean
	@Primary
	public RestTemplate getCustomRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if(interceptors == null) {
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}

}
