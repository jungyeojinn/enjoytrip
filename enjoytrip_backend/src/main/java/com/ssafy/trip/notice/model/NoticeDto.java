package com.ssafy.trip.notice.model;

public class NoticeDto {
	private int noticeId;
	private String subject;
	private String content;
	private String registerTime;

	public NoticeDto() {
		super();
	}

	public NoticeDto(String subject, String content) {
		super();
		this.subject = subject;
		this.content = content;
	}

	public NoticeDto(int noticeId, String subject, String content, String registerTime) {
		super();
		this.noticeId = noticeId;
		this.subject = subject;
		this.content = content;
		this.registerTime = registerTime;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
