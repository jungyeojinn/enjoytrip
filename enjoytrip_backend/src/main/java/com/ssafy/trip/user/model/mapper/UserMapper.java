package com.ssafy.trip.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.RefreshTokenDto;
import com.ssafy.trip.user.model.UserDto;

@Mapper
public interface UserMapper {
	int regi(UserDto user);
	
	UserDto login(UserDto user);
	UserDto getUser(String userId);
	UserDto getUserById(int userId);
	int updateUser(UserDto user);
	int deleteUser(String userId);
	String getPassword(String userId);
	
	int saveRefreshToken(RefreshTokenDto refreshToken);
	String getRefreshToken(int userId);
	int deleteRefreshToken(int userId);
}
