package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class DuplicateUserException extends BaseException {

	public DuplicateUserException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}