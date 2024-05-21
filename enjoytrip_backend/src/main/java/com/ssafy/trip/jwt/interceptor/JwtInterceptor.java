package com.ssafy.trip.jwt.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.trip.exception.AuthorizationFailedException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import com.ssafy.trip.jwt.model.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(request.getRequestURI());
		if (request.getMethod().equals("POST") && request.getRequestURI().equals("/trip/user/")) {
			return true;
		}
		final String token = request.getHeader(HEADER_AUTH);
		String jwt = null;
		if (token == null || !token.startsWith("Bearer ")) {
			throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
		}
		logger.info(token);
		jwt = token.substring(7);
		logger.info(jwt);
		if (jwtService.checkToken(jwt)) {
			logger.info("토큰 사용 가능 : {}", token);
			return true;
		} else {
			logger.info("토큰 사용 불가능 : {}", token);
			throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
		}

	}

}
