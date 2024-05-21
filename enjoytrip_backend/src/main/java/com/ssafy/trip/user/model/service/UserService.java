package com.ssafy.trip.user.model.service;

import com.ssafy.trip.user.model.LoginResponse;
import com.ssafy.trip.user.model.UserDto;

public interface UserService {
	void regi(UserDto user);
	LoginResponse login(UserDto user);
	UserDto loginNaver(UserDto user);
	public void logout(String userId);
	UserDto getUser(String userId);
	void updateUser(UserDto user);
	void deleteUser(String userId);
	String getPassword(String userId);
	String reAccessToken(String userId, String refreshToken);
}
