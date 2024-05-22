package com.ssafy.trip.user.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.jwt.model.service.JwtService;
import com.ssafy.trip.user.model.LoginResponse;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserProfileResponse;
import com.ssafy.trip.user.model.UserRegistRequest;
import com.ssafy.trip.user.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	private JwtService jwtService;

	public UserController(UserService userService, JwtService jwtService) {
		super();
		this.userService = userService;
		this.jwtService = jwtService;
	}

	// @GetMapping(value = "/regi")
	// public String regi() {
	// return "regi";
	// }

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) throws Exception {
		logger.debug("login {}", user);
		LoginResponse login = userService.login(user);
		// session.setMaxInactiveInterval(30*60);
		logger.info("{}", login);
		return ResponseEntity.ok().body(login);
	}

	 @GetMapping("/logout/{id}")
	 public ResponseEntity<?> logout(@PathVariable("id") String id) throws Exception {
	 logger.debug("logout ");
	 userService.logout(id);
	 return ResponseEntity.ok().body("성공");
	 }

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getuser(@PathVariable("id") String id) throws Exception {
		logger.debug("getuser {}", id);
		UserDto user = userService.getUser(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<UserProfileResponse> getuserProfile(@PathVariable("id") String id) throws Exception {
		logger.debug("getuserProfile {}", id);
		UserProfileResponse userProfile = userService.getUserProfile(id);
		return ResponseEntity.ok().body(userProfile);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> userIds, HttpServletRequest request)
			throws Exception {
		String token = request.getHeader("refresh-token");
		
		if (jwtService.checkToken(token)) {
			String userId = userIds.get("userId");
			String accessToken = userService.reAccessToken(userId, token);
			logger.debug("리프레쉬토큰 발급!!!!!!!");
			return ResponseEntity.ok().body(accessToken);
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}

	@PostMapping("/")
	public ResponseEntity<?> registUser(@RequestBody UserRegistRequest user) throws Exception {
		logger.debug("sign up {}", user);
		userService.regist(user);
		return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
	}

	@PatchMapping(value="/{id}", produces = "application/json", consumes = "multipart/form-data")
	public ResponseEntity<?> updateuser(@PathVariable("id") String id, @RequestPart("user") UserDto user, @RequestPart(value = "img", required = false) MultipartFile img) throws Exception {
		userService.updateUser(id, user, img);
		return ResponseEntity.status(HttpStatus.OK).body("회원정보 수정 성공");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable("id") String id) throws Exception {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("회원삭제 성공");
	}
}
