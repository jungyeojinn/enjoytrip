package com.ssafy.trip.attrplan.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttrplanDto {
	private int id;
	private String title;
	private String start_date;
	private String end_date;
	private byte[] img;
	private int like_count;

	// join
	private int user_id;

	public AttrplanDto() {
		super();
	}

	public AttrplanDto(String title, String start_date, String end_date, int user_id) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_id = user_id;
	}

	public AttrplanDto(String title, String start_date, String end_date, byte[] img, int like_count, int user_id) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
		this.like_count = like_count;
		this.user_id = user_id;
	}

	public AttrplanDto(int id, String title, String start_date, String end_date, byte[] img, int like_count, int user_id) {
		this.id = id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
		this.like_count = like_count;
		this.user_id = user_id;
	}

	public AttrplanDto(String title, String start_date, String end_date,  byte[] img, int like_count) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
		this.like_count = like_count;
	}

	@Override
	public String toString() {
		return "AttrplanDto{" +
				"plans_id=" + id +
				", title='" + title + '\'' +
				", start_date='" + start_date + '\'' +
				", end_date='" + end_date + '\'' +
				", img=" + img +
				", user_id=" + user_id +
				", like_cnt=" + like_count +
				'}';
	}
}