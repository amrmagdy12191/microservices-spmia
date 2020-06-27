package com.spmia.authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
 * @author amrmagdy
 * register users
 *
 */
@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	
	// register users, their passwords and their roles
	//using in memory for testing 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("john.carnell")
			.password("{noop}password1")
			.roles("USER")
		.and()
			.withUser("william.woodward")
			.password("{noop}password2")
			.roles("USER","ADMIN");
		
	}
}
