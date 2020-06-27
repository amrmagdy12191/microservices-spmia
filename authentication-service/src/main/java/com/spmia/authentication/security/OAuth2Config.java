package com.spmia.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 
 * @author amrmagdy
 * register services
 *
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// register clients(applications or services) allowed to access protected applications(services)
	// you need to register all services and front end application as well
	//using in memory for testing only
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
        .withClient("eagleeye") //name
        .secret("{noop}thisissecret") //password
        .authorizedGrantTypes("refresh_token", "password", "client_credentials") // grant type : how(which needed) to validate
        .scopes("webclient", "mobileclient"); // allowed access from web and phones
	}
	
	// define autherization manager (using default)
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
	}
}
