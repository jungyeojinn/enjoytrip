package com.ssafy.trip.Oauth.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.Oauth.model.NaverOauthTokenDto;
import com.ssafy.trip.user.model.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OauthServiceImpl implements OauthService{
    private static final Logger log = LoggerFactory.getLogger(OauthServiceImpl.class);
    private final String NAVER_CLIENT_ID = "sXE8Bh2IfRdKXY9W2y7P";
    private final String NAVER_CLIENT_KEY = "KMbuTwaZYs";
    private final String NAVER_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
    private final String NAVER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

    private final RestTemplate restTemplate;

    public OauthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAuthentication() {
        String state = generateRandomString();
        String redirectUri = "http%3A%2F%2Flocalhost%3A8080%2Ftrip%2Foauth2%2Flogin";
        String redirectUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        redirectUrl += "&client_id="+ NAVER_CLIENT_ID;
        redirectUrl += "&state="+state;
        redirectUrl += "&redirect_uri="+redirectUri;
        return redirectUrl;
    }

    @Override
    public NaverOauthTokenDto getAccessToken(String authorizationCode, String state) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("grant_type", "authorization_code");
        params.set("client_id", NAVER_CLIENT_ID);
        params.set("client_secret", NAVER_CLIENT_KEY);
        params.set("code", authorizationCode);
        params.set("state", state);

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_TOKEN_URL,
                HttpMethod.POST,
                request,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseBody);
        log.debug("response:{}", responseBody);
        return NaverOauthTokenDto.builder()
                .accessToken(rootNode.path("access_token").asText())
                .refreshToken(rootNode.path("refresh_token").asText())
                .expiresIn(rootNode.path("expires_in").asText())
                .tokenType(rootNode.path("token_type").asText())
                .build();
    }

    @Override
    public UserDto requestUserInfo(NaverOauthTokenDto naverOauthToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + naverOauthToken.getAccessToken());

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_INFO_URL,
                HttpMethod.GET,
                request,
                String.class
        );
        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseBody);
        JsonNode responseNode = rootNode.path("response");

        log.debug("profile_img:{}", responseNode.path("profile_image").asText());
        UserDto user = UserDto.builder()
        .userId(responseNode.path("id").asText())
        .emailId(responseNode.path("email").asText().split("@")[0])
        .emailDomain("naver.com")
        .nickname(responseNode.path("name").asText())
        .profileImg(responseNode.path("profile_image").asText())
        .build();
        return user;
    }
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
