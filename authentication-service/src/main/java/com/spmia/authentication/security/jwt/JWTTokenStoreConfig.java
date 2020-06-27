package com.spmia.authentication.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.spmia.authentication.config.ServiceConfig;

//@Configuration
public class JWTTokenStoreConfig {
	
	/*@Autowired
	private ServiceConfig serviceConfig;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenAccessConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtTokenAccessConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey(serviceConfig.getJwtSigningKey());
		return accessTokenConverter;
	}
	
	@Bean
	@Primary
	public TokenEnhancer tokenEnhancer() {
		return new JWTTokenEnhancer();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}*/
}
