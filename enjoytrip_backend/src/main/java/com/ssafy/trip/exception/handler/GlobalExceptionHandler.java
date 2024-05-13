package com.ssafy.trip.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.exception.AuthorizationFailedException;
import com.ssafy.trip.exception.DuplicateUserException;
import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.TokenExpiredException;
import com.ssafy.trip.exception.dto.BaseResponseDto;
import com.ssafy.trip.exception.util.BaseResponseCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AuthorizationFailedException.class)
    public ResponseEntity<BaseResponseDto> handleAuthorizationFailedException(AuthorizationFailedException e) {
        log.error("AuthorizationFailedException handle");
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponseDto(BaseResponseCode.AUTHORIZATION_FAILED));
    }
	
	@ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<BaseResponseDto> handleDuplicateUserException(DuplicateUserException e) {
        log.error("DuplicateUserException handle");
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponseDto(BaseResponseCode.DUPLICATE_USER));
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleResourceNotFoundException(ResourceNotFoundException e) {
		log.error("ResourceNotFoundException handle");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponseDto(BaseResponseCode.RESOURCE_NOT_FOUND));
    }
	
	@ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<BaseResponseDto> handleTokenExpiredException(TokenExpiredException e) {
		log.error("TokenExpiredException handle");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponseDto(BaseResponseCode.TOKEN_EXPIRED));
    }
	
	@ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<BaseResponseDto> handleInvalidInputException(InvalidInputException e) {
		log.error("InvalidInputException handle");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponseDto(BaseResponseCode.INVALID_INPUT));
    }
}
