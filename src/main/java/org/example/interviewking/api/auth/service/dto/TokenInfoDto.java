package org.example.interviewking.api.auth.service.dto;

public record TokenInfoDto(
    String accessToken,
    String refreshToken
) {
}
