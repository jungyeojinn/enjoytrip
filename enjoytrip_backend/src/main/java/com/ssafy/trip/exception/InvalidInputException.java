package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class InvalidInputException extends BaseException {

	public InvalidInputException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}
