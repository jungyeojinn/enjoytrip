package com.ssafy.trip.exception.dto;

import org.springframework.http.HttpStatus;

import com.ssafy.trip.exception.util.BaseResponseCode;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class BaseResponseDto {
	
	@ApiModelProperty(example = "OK")
	private final HttpStatus status;
	@ApiModelProperty(example = "요청 성공하였습니다.")
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