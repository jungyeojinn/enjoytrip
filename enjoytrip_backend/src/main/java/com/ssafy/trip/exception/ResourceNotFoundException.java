package com.ssafy.trip.exception;

import com.ssafy.trip.exception.util.BaseException;
import com.ssafy.trip.exception.util.BaseResponseCode;

public class ResourceNotFoundException extends BaseException {

	public ResourceNotFoundException(BaseResponseCode baseResponseCode) {
		super(baseResponseCode);
	}

}
