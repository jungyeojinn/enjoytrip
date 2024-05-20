package com.ssafy.trip.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	private final String salt;
	private final int accessTokenExpireMinutes;
	private final int refreshTokenExpireMinutes;
}
