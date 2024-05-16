package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends BaseException {

	public InvalidInputException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}
