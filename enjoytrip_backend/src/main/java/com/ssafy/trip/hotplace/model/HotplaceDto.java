package com.ssafy.trip.hotplace.model;

import java.math.BigDecimal;

public class HotplaceDto {
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
}
