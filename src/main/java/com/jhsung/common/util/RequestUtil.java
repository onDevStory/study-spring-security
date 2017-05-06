package com.jhsung.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

@Component
public class RequestUtil {

	private static final String GET = "GET";
	private static final String REQ_ATTR_PARAM_KEY = "bindingObject";

	public static void setParameter(WebDataBinder binder, HttpServletRequest request) {
		request.setAttribute(REQ_ATTR_PARAM_KEY, binder.getTarget());
	}

	public static Object getParameter(HttpServletRequest request) {
		return request.getMethod().equals(GET) ? getGetParam(request) : getPostParam(request);
	}

	public static Map<String, String[]> getGetParam(HttpServletRequest request) {
		return request.getParameterMap();
	}

	public static String getPostParam(HttpServletRequest request) {
		return request.getAttribute(REQ_ATTR_PARAM_KEY).toString();
	}

}
