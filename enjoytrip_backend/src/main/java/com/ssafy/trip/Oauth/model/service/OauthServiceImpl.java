package com.ssafy.trip.Oauth.model.service;

import com.ssafy.trip.Oauth.model.NaverOauthTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

public class OauthServiceImpl implements OauthService{
    private static final String client_id = "GXMg6oG2GLJnlT9GdRk5";
    private static final String client_key = "pUXslYGx63";


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
    public String getAccessToken(String authorizationCode, String state) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("grant_type", "authorization_code");
        params.set("client_id", client_id);
        params.set("client_secret", client_key);
        params.set("code", authorizationCode);
        params.set("state", state);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(getTokenUrl(), request, String.class);

        try {
            return objectMapper.readValue(response.getBody(), OauthTokenDto.class).getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

}
