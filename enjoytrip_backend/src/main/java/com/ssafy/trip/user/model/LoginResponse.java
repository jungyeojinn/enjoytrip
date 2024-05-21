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
	private String profileImg;
	private String accessToken;
	private String refreshToken;
	
	@Builder
	public LoginResponse(int id, String userId, String nickname, String profileImg, String accessToken, String refreshToken) {
		super();
		this.id = id;
		this.userId = userId;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
