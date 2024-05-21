package com.ssafy.trip.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.RefreshTokenDto;
import com.ssafy.trip.user.model.UserDto;

@Mapper
public interface UserMapper {
	int regi(UserDto user);
	UserDto login(UserDto user);
	UserDto loginNaver(UserDto user);
	UserDto getUser(String userId);
	UserDto getUserById(int userId);
	void updateUser(UserDto user);
	void deleteUser(String userId);
	String getPassword(String userId);
	String getProfileImgByUserId(String userId);
	//JWT
	int saveRefreshToken(RefreshTokenDto refreshToken);
	String getRefreshToken(int userId);
	int deleteRefreshToken(int userId);
}
