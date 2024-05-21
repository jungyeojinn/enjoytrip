package com.ssafy.trip.user.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.user.model.LoginResponse;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserProfileResponse;

public interface UserService {
	void regi(UserDto user);
	LoginResponse login(UserDto user);
	UserDto loginNaver(UserDto user);
	public void logout(String userId);
	UserDto getUser(String userId);
	void updateUser(String userId, UserDto user, MultipartFile img);
	void deleteUser(String userId);
	UserProfileResponse getUserProfile(String userId);
	String getPassword(String userId);
	String reAccessToken(String userId, String refreshToken);
}
