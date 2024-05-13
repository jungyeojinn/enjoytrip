package com.ssafy.trip.user.model.service;

import java.sql.SQLException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.exception.DuplicateUserException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;
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
	@Transactional
	public void regi(UserDto user) throws SQLException {
		// 이미 아이디가 같은 유저가 있다면 안됨
		UserDto alreadyUser = getUser(user.getUserId());
		if (alreadyUser == null) {
			userDao.regi(user);
		} else {
			throw new DuplicateUserException(BaseResponseCode.DUPLICATE_USER);
		}
	}

	@Override
	public UserDto login(UserDto user) throws SQLException {
		UserDto login = userDao.login(user);
		if (login == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			return login;
		}
	}

	@Override
	public UserDto getUser(String userId) throws SQLException {
		UserDto user = userDao.getUser(userId);
		return user;
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
