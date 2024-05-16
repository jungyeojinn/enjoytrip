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
	
	public HotplaceDto() {
		super();
	}
	
	//join 없음
	public HotplaceDto(String title, String img, String description, String placeName, BigDecimal latitude,
			BigDecimal longitude, int likeCount) {
		super();
		this.title = title;
		this.img = img;
		this.description = description;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
	}

	// join 있음
	public HotplaceDto(String title, String img, String description, String placeName, BigDecimal latitude,
			BigDecimal longitude, int likeCount, int userId) {
		super();
		this.title = title;
		this.img = img;
		this.description = description;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.userId = userId;
	}

	// join 있음
	public HotplaceDto(String title, String img, String description, String placeName, BigDecimal latitude,
			BigDecimal longitude, int likeCount, int userId, String userUserId, String userNickname) {
		super();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserUserId() {
		return userUserId;
	}

	public void setUserUserId(String userUserId) {
		this.userUserId = userUserId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
}
