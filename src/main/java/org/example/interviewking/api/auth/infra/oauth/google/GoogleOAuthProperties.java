package org.example.interviewking.api.auth.infra.oauth.google;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@ConfigurationProperties(prefix = "oauth.client.google")
@Getter
public class GoogleOAuthProperties {

    private final Credentials credentials;
    private final Provider provider;

    public GoogleOAuthProperties(Credentials credentials, Provider provider) {
        this.credentials = credentials;
        this.provider = provider;
    }

    public record Credentials(String clientId, String clientSecret, String redirectUri) { }

    public record Provider(GoogleOAuthProperties.Provider.Uri uri) {
        public record Uri(String userInfo, String token) { }
    }
}
