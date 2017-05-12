package com.jhsung.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMsg {

	ALREADY_JOIN_EMAIL("이미 가입된 이메일 주소입니다. 다른 이메일을 입력하여 주세요."),
	INVALID_PARAMETER("전달받은 정보가 유효하지 않습니다."),
	INVALID_URI("유효하지 않은 요청(URI)입니다.");

	private String exceptionMsg;

	ExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
