package com.ssafy.trip.hotplace.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WriteHotplaceRequest {
	private String title;
	private String img;
	private String description;
	private String placeName;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private int likeCount;
	
	private String userId;
	
	@Builder
	public WriteHotplaceRequest(String title, String img, String description, String placeName,
			BigDecimal latitude, BigDecimal longitude, int likeCount, String userId) {
		this.title = title;
		this.img = img;
		this.description = description;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.userId = userId;
	}
}
