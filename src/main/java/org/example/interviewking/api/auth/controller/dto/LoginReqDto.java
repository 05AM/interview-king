package org.example.interviewking.api.auth.controller.dto;

import org.example.interviewking.api.auth.domain.OAuthProvider;

public record LoginReqDto(
    OAuthProvider provider,
    String code
) {
}
