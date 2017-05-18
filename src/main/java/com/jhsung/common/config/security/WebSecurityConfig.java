package com.jhsung.common.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.jhsung.common.config.URL;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements URL{

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity
			.formLogin();
		
		httpSecurity
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, MAIL_CHECK, JOIN, LOGIN, LOGOUT).permitAll()
				.anyRequest().authenticated();
		
		httpSecurity
			.exceptionHandling()
				.authenticationEntryPoint(new AccessDeniedEntryPoint());
	}

}
