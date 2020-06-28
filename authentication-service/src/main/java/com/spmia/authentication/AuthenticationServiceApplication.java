package com.spmia.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spmia.authentication.config.ServiceConfig;

// token testing url : http://localhost:8901/auth/oauth/token
// testing accesing user  http://localhost:8901/auth/user     [(Header) Authorization : Bearer [generatedToken from above url]]

// decode token : https://www.jsonwebtoken.io/

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class AuthenticationServiceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}
	
	@RequestMapping(value = "/user", produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user){
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("autherities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userInfo;
	}

}
