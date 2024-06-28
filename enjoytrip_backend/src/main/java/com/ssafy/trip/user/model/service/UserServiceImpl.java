package com.ssafy.trip.user.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.common.ImgUtils;
import com.ssafy.trip.exception.AuthorizationFailedException;
import com.ssafy.trip.exception.DatabaseRequestFailedException;
import com.ssafy.trip.exception.DuplicateUserException;
import com.ssafy.trip.exception.InvalidInputException;
import com.ssafy.trip.exception.ResourceNotFoundException;
import com.ssafy.trip.exception.util.BaseResponseCode;
import com.ssafy.trip.jwt.model.service.JwtService;
import com.ssafy.trip.user.model.LoginResponse;
import com.ssafy.trip.user.model.RefreshTokenDto;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserProfileResponse;
import com.ssafy.trip.user.model.UserRegistRequest;
import com.ssafy.trip.user.model.UserUpdateRequest;
import com.ssafy.trip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final JwtService jwtService;
	private final ImgUtils imgUtils;
	
	
	public UserServiceImpl(UserMapper userMapper, JwtService jwtService, ImgUtils imgUtils) {
		super();
		this.userMapper = userMapper;
		this.jwtService = jwtService;
		this.imgUtils = imgUtils;
	}

	@Override
	@Transactional
	public void regist(UserRegistRequest userRegist) {
		// 이미 아이디가 같은 유저가 있다면 안됨		
		if (!userMapper.checkAlreadyExists(userRegist.getUserId())) {
			int result = userMapper.insertUser(UserDto.builder()
					.userId(userRegist.getUserId())
					.nickname(userRegist.getNickname())
					.password(userRegist.getPassword())
					.emailId(userRegist.getEmailId())
					.emailDomain(userRegist.getEmailDomain())
					.authoritiesId(2)
					.build());
			if (result == 0) {
				throw new DatabaseRequestFailedException(BaseResponseCode.DATABASE_REQUEST_FAILED);
			}
		} else {
			throw new DuplicateUserException(BaseResponseCode.DUPLICATE_USER);
		}
	}

	@Override
	@Transactional
	public LoginResponse login(UserDto user) {
		UserDto login = userMapper.login(user);
		if (login == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			// 성공 시 토큰 발급
			String accessToken = jwtService.createAccessToken("userid", login.getUserId());// key, data
			String refreshToken = jwtService.createRefreshToken("userid", login.getUserId());// key, data
			
			//refresh 토큰 재설정
			userMapper.deleteRefreshToken(login.getId());
			userMapper.saveRefreshToken(RefreshTokenDto.builder()
					.userId(login.getId())
					.refreshToken(refreshToken)
					.build());
			
			//리턴
			return LoginResponse.builder()
					.id(login.getId())
					.userId(login.getUserId())
					.nickname(login.getNickname())
					.profileImg(login.getProfileImg())
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();
		}
	}
	
	@Override
	@Transactional
	public void logout(String userId) {
		int id = userMapper.getIdByUserId(userId);
		int result = userMapper.deleteRefreshToken(id);
		if (result == 0) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
	}

	@Override
	@Transactional
	public LoginResponse loginNaver(UserDto user) {
		UserDto login = userMapper.loginNaver(user);
		if (login == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		} else {
			// 성공 시 토큰 발급
						String accessToken = jwtService.createAccessToken("userid", login.getUserId());// key, data
						String refreshToken = jwtService.createRefreshToken("userid", login.getUserId());// key, data
						
						//refresh 토큰 재설정
						userMapper.deleteRefreshToken(login.getId());
						userMapper.saveRefreshToken(RefreshTokenDto.builder()
								.userId(login.getId())
								.refreshToken(refreshToken)
								.build());
						
						//리턴
						return LoginResponse.builder()
								.id(login.getId())
								.userId(login.getUserId())
								.nickname(login.getNickname())
								.profileImg(login.getProfileImg())
								.accessToken(accessToken)
								.refreshToken(refreshToken)
								.build();
		}
	}

	@Override
	public UserDto getUser(String userId) {
		UserDto user = userMapper.getUser(userId);
		return user;
	}

	@Override
	@Transactional
	public void updateUser(String userId, UserUpdateRequest updateUser, MultipartFile img) {
		if (!userId.equals(updateUser.getUserId())) {
			throw new InvalidInputException(BaseResponseCode.INVALID_INPUT);
		}
		
		String originPath = userMapper.getProfileImgByUserId(userId);
		if (originPath != null && !originPath.isEmpty()) {
			imgUtils.deleteImage(originPath, "user");
		}
		
		String imgPath = "";
		if (img != null && !img.isEmpty()) {	
			imgPath = imgUtils.saveImage(img, "user");
		}
		userMapper.updateUser(UserDto.builder()
				.userId(updateUser.getUserId())
				.nickname(updateUser.getNickname())
				.emailId(updateUser.getEmailId())
				.emailDomain(updateUser.getEmailDomain())
				.profileImg(imgPath)
				.build());
	}

	@Override
	@Transactional
	public void deleteUser(String userId) {
		String originPath = userMapper.getProfileImgByUserId(userId);
		int id = userMapper.getIdByUserId(userId);
		userMapper.deActivate(userId);
		if (originPath != null && !originPath.isEmpty()) {
			imgUtils.deleteImage(originPath, "user");
		}
		
	}

	@Override
	public String getPassword(String userId) {
		return userMapper.getPassword(userId);
	}

	@Override
	@Transactional
	public String reAccessToken(String userId, String refreshToken) {
		String accessToken = null;
		
		UserDto user = getUser(userId);
		
		if (refreshToken.equals(userMapper.getRefreshToken(user.getId()))) {
			accessToken = jwtService.createAccessToken("userid", user.getUserId());
		} else {
			throw new AuthorizationFailedException(BaseResponseCode.AUTHORIZATION_FAILED);
		}
		return accessToken;
	}

	@Override
	public UserProfileResponse getUserProfile(String userId) {
		UserProfileResponse userProfile = userMapper.getUserProfileByUserId(userId);
		if (userProfile == null) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		}
		return userProfile;
	}

	@Override
	@Transactional(readOnly = true)
	public int getIdByUserId(String userId) {
		int id = userMapper.getIdByUserId(userId);
		if (id == 0) {
			throw new ResourceNotFoundException(BaseResponseCode.RESOURCE_NOT_FOUND);
		}
		return id;
	}
}
