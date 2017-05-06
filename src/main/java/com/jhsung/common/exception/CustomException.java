package com.jhsung.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;
}