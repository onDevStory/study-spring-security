package com.jhsung.common.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jhsung.common.util.ExceptionUtil;
import com.jhsung.common.util.RequestUtil;

import lombok.Data;

@Data
public class DebugInfo {

	private String path;
	private Object parameter;
	private String exceptionInfo;
	private List<String> stackTrace;

	public DebugInfo(HttpServletRequest request, Exception e) {
		this.path = String.format("[%s] %s", request.getMethod(), request.getRequestURI());
		this.parameter = RequestUtil.getParameter(request);
		this.exceptionInfo = ExceptionUtil.getSimpleInfo(e);
		this.stackTrace = ExceptionUtil.getSimpleStackTrace(e.getStackTrace());
	}

}
