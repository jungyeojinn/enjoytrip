package com.ssafy.trip.user.model;

public class UserDto {
	private int id;
	private String userId;
	private String nickName;
	private String password;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	private int authoritiesId;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(String userId, String nickName, String password, String emailId, String emailDomain,
			String joinDate, int authoritiesId) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
		this.authoritiesId = authoritiesId;
	}

	public UserDto(String userId, String nickName, String password, String emailId, String emailDomain,
			int authoritiesId) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.authoritiesId = authoritiesId;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", emailId="
				+ emailId + ", emailDomain=" + emailDomain + ", joinDate=" + joinDate + ", authoritiesId="
				+ authoritiesId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getAuthoritiesId() {
		return authoritiesId;
	}

	public void setAuthoritiesId(int authoritiesId) {
		this.authoritiesId = authoritiesId;
	}

}
