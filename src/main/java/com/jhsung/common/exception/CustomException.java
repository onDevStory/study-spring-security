package com.jhsung.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public CustomException(String message) {
		this.message = message;
	}

	public CustomException(ExceptionMsg exceptionMsg) {
		this(exceptionMsg.getExceptionMsg());
	}

}