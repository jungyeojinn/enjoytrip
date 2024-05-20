package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
	private int id;
	private String userId;
	private String nickname;
	private String accessToken;
	private String refreshToken;
	
	@Builder
	public LoginResponse(int id, String userId, String nickname, String accessToken, String refreshToken) {
		super();
		this.id = id;
		this.userId = userId;
		this.nickname = nickname;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
