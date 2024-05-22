package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
	private String userId;
	private String nickname;
	private String emailId;
	private String emailDomain;
	private String profileImg;
	
	@Builder
	public UserUpdateRequest(String userId, String nickname, String emailId, String emailDomain, String profileImg) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.profileImg = profileImg;
	}
}
