package com.ssafy.trip.Oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.trip.Oauth.model.NaverOauthTokenDto;
import com.ssafy.trip.Oauth.model.service.OauthService;
import com.ssafy.trip.exception.DuplicateUserException;
import com.ssafy.trip.hotplace.model.service.HotplaceService;
import com.ssafy.trip.user.model.UserDto;
import com.ssafy.trip.user.model.UserRegistRequest;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.ssafy.trip.user.model.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@CrossOrigin
@RequestMapping("/oauth2")
public class OauthController {

    private final OauthService oauthService;
    private final UserService userService;

    public OauthController(OauthService oauthService, UserService userService) {
        super();
        this.oauthService = oauthService;
        this.userService = userService;
    }

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);


    @GetMapping("/login")
    public ResponseEntity<?> authNaverGetToken(@RequestParam("code") String code,
                                               @RequestParam("state") String state) throws JsonProcessingException, SQLException {
        NaverOauthTokenDto naverOauthToken = oauthService.getAccessToken(code, state);
        UserDto user = oauthService.requestUserInfo(naverOauthToken);
        try {
            userService.regist(UserRegistRequest.builder()
            		.userId(user.getUserId())
            		.nickname(user.getNickname())
            		.emailId(user.getEmailId())
            		.emailDomain(user.getEmailDomain())
            		.profileImg(user.getProfileImg())
            		.build());
        } catch (DuplicateUserException e){
            userService.loginNaver(user);
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/login/naver")
    public void authNaver(HttpServletResponse response) throws IOException {
        String redirect_url = oauthService.getAuthentication();
        response.sendRedirect(redirect_url);
    }

}