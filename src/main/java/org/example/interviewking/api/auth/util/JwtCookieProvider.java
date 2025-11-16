package org.example.interviewking.api.auth.util;

import java.time.Duration;

import org.springframework.http.ResponseCookie;

public class JwtCookieProvider {

    public static ResponseCookie createAccessTokenCookie(String accessToken) {
        return ResponseCookie.from("access-token", accessToken)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Duration.ofMinutes(30))
            .sameSite("Strict")
            .build();
    }

    public static ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refresh-token", refreshToken)
            .httpOnly(true)
            .secure(true)
            .path("/api/auth/refresh")
            .maxAge(Duration.ofDays(7))
            .sameSite("Strict")
            .build();
    }
}
