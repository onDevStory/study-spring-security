package com.jhsung.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// 비인증 접근 허용 URL 설정
			// 신규 일반 페이지(/, /login, /logout) 생성 시 추가를 안해주면, 비인증 시 Login 페이지로 Redirect.
            // 신규 보안 페이지 생성 시 추가를 깜빡하더라도 보안상으로는 더 좋음.
		    // 위에서 설정한 URL 외의 모든 요청은 미인증 시 접근 불가
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/logout").permitAll()
			.anyRequest().authenticated();
		
		http
			// 보안 페이지 요청 시, Login 페이지로 Redirect
			// 컨트롤러 매핑을 하지 않으면 기본 제공되는 로그인 페이지가 뜬다.
			.formLogin()
			.loginProcessingUrl("/login");
		
		http
			.logout()
			// /logout 을 호출할 경우 로그아웃
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			// 로그아웃이 성공했을 경우 이동할 페이지
			.logoutSuccessUrl("/");
	}

}
