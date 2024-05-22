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
    private final String client_id = "sXE8Bh2IfRdKXY9W2y7P";
    private final String client_key = "KMbuTwaZYs";
    private final String tokenUrl = "https://nid.naver.com/oauth2.0/token";
    private final String userInfoUrl = "https://openapi.naver.com/v1/nid/me";

    private final RestTemplate restTemplate;


    public OauthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    public NaverOauthTokenDto getUserToken(String code, String state) {
//        String accessToken = getAccessToken(code, state);
//        UserInfoOauthDto userInfoOauthDto = getUserInfo(accessToken);
//
//        return setUserTokenDto(userInfoOauthDto);
//    }

    @Override
    public String getAuthentication() {
        String state = generateRandomString();
        String redirect_uri = "http%3A%2F%2Flocalhost%3A8080%2Ftrip%2Foauth2%2Flogin";
        String redirect_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        redirect_url += "&client_id="+client_id;
        redirect_url += "&state="+state;
        redirect_url += "&redirect_uri="+redirect_uri;
        return redirect_url;
    }

    @Override
    public NaverOauthTokenDto getAccessToken(String authorizationCode, String state) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("grant_type", "authorization_code");
        params.set("client_id", client_id);
        params.set("client_secret", client_key);
        params.set("code", authorizationCode);
        params.set("state", state);

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                tokenUrl,
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
                userInfoUrl,
                HttpMethod.GET,
                request,
                String.class
        );
        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseBody);
        JsonNode responseNode = rootNode.path("response");

        UserDto user = new UserDto();
        user.setUserId(responseNode.path("id").asText());
        user.setEmailId(responseNode.path("email").asText().split("@")[0]);
        user.setEmailDomain("naver.com");
        user.setNickName(responseNode.path("name").asText());
        log.debug("profile_img:{}", responseNode.path("profile_image").asText());
        user.setProfileImg(responseNode.path("profile_image").asText());
        user.setAuthoritiesId(2);
        return user;
    }
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

}
