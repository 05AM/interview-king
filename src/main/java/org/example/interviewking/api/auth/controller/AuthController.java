package org.example.interviewking.api.auth.controller;

import org.example.interviewking.api.auth.model.OAuthProvider;
import org.example.interviewking.api.auth.service.OAuthService;
import org.example.interviewking.api.auth.service.dto.TokenInfoDto;
import org.example.interviewking.api.auth.util.JwtCookieProvider;
import org.example.interviewking.api.common.constant.AuthConstants;
import org.example.interviewking.api.common.model.BaseResponse;
import org.example.interviewking.api.common.model.ResponseCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService OAuthService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Void>> login(
        @RequestHeader(AuthConstants.OAUTH_PROVIDER_HEADER) OAuthProvider provider,
        @RequestHeader(AuthConstants.OAUTH_CODE_HEADER) String code
    ) {
        TokenInfoDto tokenInfo = OAuthService.login(provider, code);

        ResponseCookie accessCookie = JwtCookieProvider.createAccessTokenCookie(tokenInfo.accessToken());
        ResponseCookie refreshCookie = JwtCookieProvider.createRefreshTokenCookie(tokenInfo.accessToken());

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, String.valueOf(accessCookie))
            .header(HttpHeaders.SET_COOKIE, String.valueOf(refreshCookie))
            .body(BaseResponse.of(ResponseCode.LOGIN_SUCCESS));
    }
}
