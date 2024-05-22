package com.ssafy.trip.user.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.user.model.LoginResponse;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserProfileResponse;
import com.ssafy.trip.user.model.UserRegistRequest;
import com.ssafy.trip.user.model.UserUpdateRequest;

public interface UserService {
	void regist(UserRegistRequest user);
	LoginResponse login(UserDto user);
	LoginResponse loginNaver(UserDto user);
	public void logout(String userId);
	UserDto getUser(String userId);
	void updateUser(String userId, UserUpdateRequest user, MultipartFile img);
	void deleteUser(String userId);
	UserProfileResponse getUserProfile(String userId);
	String getPassword(String userId);
	String reAccessToken(String userId, String refreshToken);
}
