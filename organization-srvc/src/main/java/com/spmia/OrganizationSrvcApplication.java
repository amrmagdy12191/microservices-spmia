package com.spmia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.spmia.organization.events.model.Greetings;
import com.spmia.organization.events.source.GreetingsStreams;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker // circuit breaker
@EnableResourceServer // service protected by spring security oauth2
@EnableBinding(GreetingsStreams.class) // Kafka
public class OrganizationSrvcApplication {
	
	Logger log = LoggerFactory.getLogger(OrganizationSrvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrganizationSrvcApplication.class, args); 
	}
	
//	@StreamListener(GreetingsStreams.INPUT)
//    public void handleGreetings(@Payload Greetings greetings) {
//        log.info("Received greetings: {}", greetings);
//    }

}
