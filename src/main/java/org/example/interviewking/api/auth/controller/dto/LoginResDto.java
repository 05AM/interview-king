package org.example.interviewking.api.auth.controller.dto;

public record LoginResDto(
    String accessToken,
    String refreshToken
) {
}
