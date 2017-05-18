package com.jhsung.common.config.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.jhsung.common.config.URL;
import com.jhsung.common.exception.ErrorName;
import com.jhsung.common.util.RequestUtil;

public class AccessDeniedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(URL.EX_CONTROL);
		RequestUtil.setErrorName(request, ErrorName.MUST_LOGIN);
		dispatcher.forward(request, response);
	}

}
