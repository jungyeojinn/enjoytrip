package com.ssafy.trip.board.model;

public class BoardDto {
	private int id;
	private int type;
	private String title;
	private String content;
	private String img;
	private int views;
	private String created_at;
	private String modified_at;
	
	// join
	private int user_id;

	public BoardDto() {
		super();
	}

	public BoardDto(int id, int type, String title, String content, String img, int views, String created_at,
			String modified_at, int user_id) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.img = img;
		this.views = views;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.user_id = user_id;
	}

	public BoardDto(int type, String title, String content, String img, int views) {
		super();
		this.type = type;
		this.title = title;
		this.content = content;
		this.img = img;
		this.views = views;
	}
	

	public BoardDto(int id, int type, String title, String content, String img, int views, String created_at,
			String modified_at) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.img = img;
		this.views = views;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public int getUsers_id() {
		return user_id;
	}

	public void setUsers_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", type=" + type + ", title=" + title + ", content=" + content + ", img=" + img
				+ ", views=" + views + ", created_at=" + created_at + ", modified_at=" + modified_at + ", user_id="
				+ user_id + "]";
	}
	
}
/*
`article_no` INT NOT NULL AUTO_INCREMENT,
`user_id` VARCHAR(16) NULL DEFAULT NULL,
`subject` VARCHAR(100) NULL DEFAULT NULL,
`content` VARCHAR(2000) NULL DEFAULT NULL,
`hit` INT NULL DEFAULT 0,
`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
*/