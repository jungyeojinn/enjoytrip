package com.ssafy.trip.hotplace.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HotplaceDto {
	@Override
	public String toString() {
		return "HotplaceDto [id=" + id + ", title=" + title + ", img=" + img + ", description=" + description
				+ ", placeName=" + placeName + ", latitude=" + latitude + ", longitude=" + longitude + ", likeCount="
				+ likeCount + ", userId=" + userId + ", userUserId=" + userUserId + ", userNickname=" + userNickname
				+ "]";
	}

	private int id;
	private String title;
	private String img;
	private String description;
	private String placeName;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private int likeCount;
	
//	---- join
	private int userId;
	private String userUserId;
	private String userNickname;
	
	@Builder
	public HotplaceDto(int id, String title, String img, String description, String placeName, BigDecimal latitude,
			BigDecimal longitude, int likeCount, int userId, String userUserId, String userNickname) {
		super();
		this.id = id;
		this.title = title;
		this.img = img;
		this.description = description;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.userId = userId;
		this.userUserId = userUserId;
		this.userNickname = userNickname;
	}
}
