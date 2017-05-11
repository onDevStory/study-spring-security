package com.jhsung.common.config.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jhsung.common.ResponseFormat;
import com.jhsung.common.util.RequestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice("com.jhsung")
public class PureControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		log.debug("~~~PureControllerAdvice initBinder");
		RequestUtil.setParameter(binder, request);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseFormat handleCustomException(HttpServletRequest request, Exception e) {
		// TODO exceptionLog(request, e);
		log.debug("~~~PureControllerAdvice handleCustomException");
		return new ResponseFormat(request, e);
	}

	public void exceptionLog(HttpServletRequest request, Exception e) {
		log.info("EXCEPTION LOG");
	}

}
