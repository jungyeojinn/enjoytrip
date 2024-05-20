package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenDto {
	private int userId;
	private String refreshToken;
	
	@Builder
	public RefreshTokenDto(int userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
}
