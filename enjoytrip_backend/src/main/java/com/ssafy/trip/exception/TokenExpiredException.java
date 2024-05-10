package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class TokenExpiredException extends BaseException {

	public TokenExpiredException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}
