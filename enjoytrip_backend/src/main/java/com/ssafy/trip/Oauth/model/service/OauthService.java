package com.ssafy.trip.Oauth.model.service;

public interface OauthService {
    public String getAuthentication();
    public String getAccessToken(String authorizationCode, String state);
}
