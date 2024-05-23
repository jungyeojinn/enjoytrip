package com.ssafy.trip.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardWithUserDto {
	private int id;
	private int type;
	private String title;
	private String content;
	private String img;
	private int views;
	private String createdAt;
	private String modifiedAt;
	private int userId;
	private String userUserId;
	private String nickname;
	
	@Builder
	public BoardWithUserDto(int id, int type, String title, String content, String img, int views, String createdAt,
			String modifiedAt, int userId, String userUserId, String nickname) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.img = img;
		this.views = views;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.userId = userId;
		this.userUserId = userUserId;
		this.nickname = nickname;
	}
}
