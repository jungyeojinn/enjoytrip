package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class DatabaseRequestFailedException extends BaseException {
	public DatabaseRequestFailedException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}
}