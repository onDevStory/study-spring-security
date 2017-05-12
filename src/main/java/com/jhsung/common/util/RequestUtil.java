package com.jhsung.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;

import com.jhsung.common.exception.CustomException;
import com.jhsung.common.exception.ExceptionMsg;

public class RequestUtil {

	private static final String GET = "GET";
	private static final String REQ_ATTR_PARAM_KEY = "bindingObject";
	private static final String FORMAT_INVALID_EX = ExceptionMsg.INVALID_PARAMETER.getExceptionMsg() + " - %s";

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

	public static void checkBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				sb.append(fieldError.getField() + ", ");
			}
			if (sb.length() != 0) {
				sb.delete(sb.lastIndexOf(", "), sb.length());
			}
			throw new CustomException(String.format(FORMAT_INVALID_EX, sb.toString()));
		}
	}

}
