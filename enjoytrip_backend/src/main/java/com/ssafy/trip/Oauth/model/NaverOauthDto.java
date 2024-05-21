package com.ssafy.trip.Oauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class NaverOauthDto {
    private String grantType;

    private String clientId;

    private String authorizationCode;

    private String state;
}
