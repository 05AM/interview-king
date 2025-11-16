package org.example.interviewking.api.auth.controller.dto;

import org.example.interviewking.api.auth.model.OAuthProvider;

public record LoginReqDto(
    OAuthProvider provider,
    String code
) {
}
