package com.ssafy.trip.Oauth.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.trip.Oauth.model.NaverOauthTokenDto;
import com.ssafy.trip.user.model.UserDto;

public interface OauthService {
    public String getAuthentication();
    public NaverOauthTokenDto getAccessToken(String authorizationCode, String state) throws JsonProcessingException;
    public UserDto requestUserInfo(NaverOauthTokenDto naverOauthToken) throws JsonProcessingException;
}
