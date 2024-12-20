package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistRequest {
	private String userId;
	private String nickname;
	private String password;
	private String emailId;
	private String emailDomain;
	private String profileImg;
	
	@Builder
	public UserRegistRequest(String userId, String nickname, String password, String emailId, String emailDomain, String profileImg) {
		this.userId = userId;
		this.nickname = nickname;
		this.password = password;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.profileImg = profileImg;
	}
}
