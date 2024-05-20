package com.ssafy.trip.Oauth.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

@RestController
@RequestMapping("/oauth2")
public class OauthController {

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);

    @GetMapping("/login")
    public ResponseEntity<?> authNaver(@RequestParam("code") String code, @RequestParam("state") String state){
        return ResponseEntity.status(HttpStatus.OK).body(code+" "+state);
    }
}