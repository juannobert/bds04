package com.devsuperior.bds04.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	private static final String[] PUBLIC = {"/oauth/token","/events/**","/cities/**"};
	private static final String[] CLIENT = {"/events/**"};
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) { // Se for perfil de teste
			http.headers().frameOptions().disable(); // Liberar H2 console
		}
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,PUBLIC).permitAll()
		.antMatchers(HttpMethod.POST,CLIENT).hasAnyRole("CLIENT","ADMIN")
		.anyRequest().hasAnyRole("ADMIN");
	}
	
	
}
