package org.example.interviewking.api.auth.infra.oauth.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.interviewking.api.auth.dto.OAuthTokenInfo;

public record GoogleTokenInfo(
    @JsonProperty("access_token") String accessToken
) implements OAuthTokenInfo {

    @Override
    public String accessToken() {
        return accessToken;
    }
}
