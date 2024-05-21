package com.ssafy.trip.exception.util;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BaseResponseCode {

	AUTHORIZATION_FAILED(HttpStatus.BAD_REQUEST, "사용자가 해당 리소스에 접근권한이 없습니다."),
	
	DUPLICATE_USER(HttpStatus.BAD_REQUEST, "이미 사용중인 아이디 입니다."),
	
	RESOURCE_NOT_FOUND(HttpStatus.BAD_REQUEST, "리소스를 찾을 수 없습니다."),
	
	TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."),
	
	INVALID_INPUT(HttpStatus.BAD_REQUEST, "올바르지 않은 입력값입니다."),
	
	DATABASE_REQUEST_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "요청 처리 중 에러가 발생했습니다.");
	
	
	private final HttpStatus status;
	private final String message;
	
	private BaseResponseCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}