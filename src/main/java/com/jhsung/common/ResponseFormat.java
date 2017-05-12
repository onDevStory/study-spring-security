package com.jhsung.common;

import javax.servlet.http.HttpServletRequest;

import com.jhsung.common.exception.DebugInfo;

import lombok.Data;

@Data
public class ResponseFormat {
	private Boolean success;
	private Object response;
	private String errorMsg;
	private DebugInfo debugInfo;

	// Success
	public ResponseFormat(Object response) {
		this.success = true;
		this.response = response;
	}

	// Exception
	public ResponseFormat(HttpServletRequest request, Exception e) {
		this.success = false;
		this.errorMsg = e.getMessage();
		// TODO check authority of debugging
		this.debugInfo = true ? new DebugInfo(request, e) : null;
	}
}