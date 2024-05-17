package com.ssafy.trip.attrplan.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttrplanDto {
	private int plans_id;
	private String title;
	private String start_date;
	private String end_date;
	private byte[] img;

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

	public AttrplanDto(String title, String start_date, String end_date, byte[] img, int user_id) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
		this.user_id = user_id;
	}

	public AttrplanDto(int plans_id, String title, String start_date, String end_date, byte[] img, int user_id) {
		this.plans_id = plans_id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
		this.user_id = user_id;
	}

	public AttrplanDto(String title, String start_date, String end_date, byte[] img) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.img = img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "AttrplanDto{" +
				"plans_id=" + plans_id +
				", title='" + title + '\'' +
				", start_date='" + start_date + '\'' +
				", end_date='" + end_date + '\'' +
				", img=" + img +
				", user_id=" + user_id +
				'}';
	}
}