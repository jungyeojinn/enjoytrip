package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistRequest {
	private String userId;
	private String nickName;
	private String password;
	private String emailId;
	private String emailDomain;
	
	@Builder
	public UserRegistRequest(String userId, String nickName, String password, String emailId, String emailDomain) {
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
	}
}
