package com.ssafy.trip.user.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.UserDto;

@Mapper
public interface UserMapper {
	void regi(UserDto user) throws SQLException;
	
	UserDto login(UserDto user) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	void updateUser(UserDto user) throws SQLException;
	void deleteUser(String userId) throws SQLException;
	String getPassword(String userId) throws SQLException;
}
