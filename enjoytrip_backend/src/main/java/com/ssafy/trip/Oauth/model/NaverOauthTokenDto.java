package com.ssafy.trip.Oauth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NaverOauthTokenDto {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("access_token")
    private String tokenType;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
