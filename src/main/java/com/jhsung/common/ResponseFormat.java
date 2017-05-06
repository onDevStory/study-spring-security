package com.jhsung.common;

import javax.servlet.http.HttpServletRequest;

import com.jhsung.common.exception.DebugInfo;

import lombok.Data;

@Data
public class ResponseFormat {
	private Boolean error;
	private String errorMsg;
	private Object result;
	private DebugInfo debugInfo;

	// Success
	public ResponseFormat(Object result) {
		this.result = result;
	}

	// Exception
	public ResponseFormat(HttpServletRequest request, Exception e) {
		this.error = true;
		this.errorMsg = "???";
		// TODO check authority of debugging
		this.debugInfo = true ? new DebugInfo(request, e) : null;
	}
}