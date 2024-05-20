package com.ssafy.trip.Oauth.controller;

import com.ssafy.trip.Oauth.model.service.OauthService;
import com.ssafy.trip.hotplace.model.service.HotplaceService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/oauth2")
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        super();
        this.oauthService = oauthService;
    }

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);



    @GetMapping("/login")
    public ResponseEntity<?> authNaverGetToken(@RequestParam("code") String code, @RequestParam("state") String state){
        oauthService.getAccessToken(code, state);

    }

    @GetMapping("/login/naver")
    public void authNaver(HttpServletResponse response) throws IOException {
        String redirect_url = oauthService.getAuthentication();
        response.sendRedirect(redirect_url);
    }


}