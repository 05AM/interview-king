package org.example.interviewking.api.auth.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secretKey;
    private final long accessExpirationTime;
    private final long refreshExpirationTime;

    public JwtProperties(String secretKey,
        long accessExpirationTime,
        long refreshExpirationTime) {
        this.secretKey = secretKey;
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public long getAccessExpirationTime() {
        return accessExpirationTime;
    }

    public long getRefreshExpirationTime() {
        return refreshExpirationTime;
    }
}
