package com.ssafy.trip.user.model.service;

import java.sql.SQLException;

import com.ssafy.trip.user.model.UserDto;

public interface UserService {
	void regi(UserDto user) throws SQLException;
	
	UserDto login(UserDto user) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	void updateUser(UserDto user) throws SQLException;
	void deleteUser(String userId) throws SQLException;
	String getPassword(String userId) throws SQLException;
}
