package com.ssafy.trip.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.RefreshTokenDto;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserProfileResponse;

@Mapper
public interface UserMapper {
	int insertUser(UserDto user);
	UserDto login(UserDto user);
	UserDto loginNaver(UserDto user);
	UserDto getUser(String userId);
	UserDto getUserById(int userId);
	int getIdByUserId(String userId);
	void updateUser(UserDto user);
	void deleteUser(String userId);
	String getPassword(String userId);
	String getProfileImgByUserId(String userId);
	UserProfileResponse getUserProfileByUserId(String userId);
	
	boolean checkAlreadyExists(String userId);
	//JWT
	int saveRefreshToken(RefreshTokenDto refreshToken);
	String getRefreshToken(int userId);
	int deleteRefreshToken(int userId);
}
