package com.ssafy.trip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
	private int id;
	private String userId;
	private String nickname;
	private String password;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	private String profileImg;
	private int authoritiesId;
	private boolean isDeleted;
	@Builder
	public UserDto(int id, String userId, String nickname, String password, String emailId, String emailDomain,
			String joinDate, String profileImg, int authoritiesId, boolean isDeleted) {
		super();
		this.id = id;
		this.userId = userId;
		this.nickname = nickname;
		this.password = password;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
		this.profileImg = profileImg;
		this.authoritiesId = authoritiesId;
		this.isDeleted = isDeleted;
	}
	
	public void updateProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
}
