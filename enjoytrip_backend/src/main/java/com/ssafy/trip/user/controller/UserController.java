package com.ssafy.trip.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping(value = "/regi")
	public String regi() {
		return "regi";
	}

	@PostMapping(value = "/login")
	public String login(HttpSession session, @ModelAttribute UserDto user) throws Exception {
		logger.debug("login {}", user);
		UserDto login = userService.login(user);
		// session.setMaxInactiveInterval(30*60);
		if (login != null) {
			session.setAttribute("login", login);
			return "redirect:/";
		} else {
			session.invalidate();
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		logger.debug("logout ");
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/password/{id}")
	public String getpassword(@PathVariable String id) throws Exception {
		return userService.getPassword(id);
	}

	@GetMapping("/user/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> getuser(@PathVariable String id) throws Exception {
		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
		logger.debug("logout {}", userService.getUser(id));
		return response;
	}

	@PostMapping("/user")
	public String regiaf(@RequestBody UserDto user) throws Exception {
		logger.debug("sign up {}", user);
		userService.regi(user);
		return "redirect:/";
	}

	@PatchMapping("/user")
	@ResponseBody
	public ResponseEntity<?> updateuser(@RequestBody UserDto user) throws Exception {
		userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/user/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteuser(@PathVariable String id) throws Exception {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
