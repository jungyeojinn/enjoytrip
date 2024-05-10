package com.ssafy.trip.exception.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
	private final BaseResponseCode baseResponseCode;
}
