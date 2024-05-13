package com.ssafy.trip.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// @GetMapping(value = "/regi")
	// public String regi() {
	// return "regi";
	// }

	@PostMapping("/login")
	public ResponseEntity<?> login(@ModelAttribute UserDto user) throws Exception {
		logger.debug("login {}", user);
		UserDto login = userService.login(user);
		// session.setMaxInactiveInterval(30*60);
		return ResponseEntity.ok().body(login);
	}

	// @GetMapping("/logout")
	// public String logout(HttpSession session) throws Exception {
	// logger.debug("logout ");
	// session.invalidate();
	// return "redirect:/";
	// }

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> getuser(@PathVariable("id") String id) throws Exception {
		logger.debug("getuser {}", id);
		UserDto user = userService.getUser(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/")
	public ResponseEntity<?> regiaf(@RequestBody UserDto user) throws Exception {
		logger.debug("sign up {}", user);
		userService.regi(user);
		return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
	}

	@PatchMapping("/")
	@ResponseBody
	public ResponseEntity<?> updateuser(@RequestBody UserDto user) throws Exception {
		userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body("회원정보 수정 성공");
	}

	@DeleteMapping("/")
	@ResponseBody
	public ResponseEntity<?> deleteuser(@RequestParam("userId") String id) throws Exception {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("회원삭제 성공");
	}
}
