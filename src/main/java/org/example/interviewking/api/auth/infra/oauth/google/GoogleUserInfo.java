package org.example.interviewking.api.auth.infra.oauth.google;

import org.example.interviewking.api.auth.dto.OAuthUserInfo;
import org.example.interviewking.api.auth.domain.OAuthProvider;

public record GoogleUserInfo(
    String name,
    String email,
    String id
) implements OAuthUserInfo {

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.GOOGLE;
    }
}
