package com.jhsung.common.util;

import java.util.List;

import com.google.common.collect.Lists;

public class ExceptionUtil {

	private static final String rootPackage = "com.jhsung";

	/**
	 * @return exceptionName : exceptionMessage
	 */
	public static String getSimpleInfo(Exception e) {
		return String.format("%s : %s", e.getClass().getSimpleName(), e.getMessage());
	}

	public static List<String> getSimpleStackTrace(StackTraceElement[] stackTraceElements) {
		List<String> stackTraceList = Lists.newArrayList();
		for (StackTraceElement item : stackTraceElements) {
			if (item.getClassName().contains(rootPackage)) {
				stackTraceList.add(makeStackTraceFormat(item));
			}
		}
		return stackTraceList;
	}

	public static String makeStackTraceFormat(StackTraceElement item) {
		String className = item.getClassName();
		String methodName = item.getMethodName();
		int lineNumber = item.getLineNumber();

		return String.format("%s.java [%s : %d]", className, methodName, lineNumber);
	}

}
