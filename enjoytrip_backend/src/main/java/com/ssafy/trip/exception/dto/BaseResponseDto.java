package com.ssafy.trip.exception.dto;

import org.springframework.http.HttpStatus;
import com.ssafy.trip.exception.util.BaseResponseCode;

import lombok.Getter;

@Getter
public class BaseResponseDto {
	private final HttpStatus status;
	private final String message;

	public BaseResponseDto(BaseResponseCode baseResponseCode) {
		this.status = baseResponseCode.getStatus();
		this.message = baseResponseCode.getMessage();
	}

	public BaseResponseDto(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}