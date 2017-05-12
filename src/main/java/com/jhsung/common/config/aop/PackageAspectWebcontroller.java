package com.jhsung.common.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.jhsung.common.util.RequestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class PackageAspectWebcontroller {

	@Pointcut("within(com.jhsung.webcontroller.*)")
	public void webcontrollerCommon() {
	}

	@Before("webcontrollerCommon() &&" + "args(.., bindingResult)")
	public void checkBindingResult(BindingResult bindingResult) {
		RequestUtil.checkBindingResult(bindingResult);
	}

	// sample
	@Around("webcontrollerCommon()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.debug("~~~Aop around - before proceed");
		Object result = proceedingJoinPoint.proceed();
		log.debug("~~~Aop around - after proceed");
		return result;
	}

}
