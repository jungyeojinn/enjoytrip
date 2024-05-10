package com.ssafy.trip.user.model.service;

import java.sql.SQLException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userDao;
	
	public UserServiceImpl(UserMapper userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public void regi(UserDto user) throws SQLException {
		userDao.regi(user);
	}

	@Override
	public UserDto login(UserDto user) throws SQLException {
		return userDao.login(user);
	}

	@Override
	public UserDto getUser(String userId) throws SQLException {
		return userDao.getUser(userId);
	}

	@Override
	public void updateUser(UserDto user) throws SQLException {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String userId) throws SQLException {
		userDao.deleteUser(userId);
	}

	@Override
	public String getPassword(String userId) throws SQLException {
		return userDao.getPassword(userId);
	}

}
