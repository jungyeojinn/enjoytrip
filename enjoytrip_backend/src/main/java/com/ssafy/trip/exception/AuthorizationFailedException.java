package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class AuthorizationFailedException extends BaseException {

	public AuthorizationFailedException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}
