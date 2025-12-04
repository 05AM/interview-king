package org.example.interviewking.api.auth.controller;

import org.example.interviewking.api.auth.controller.dto.LoginReqDto;
import org.example.interviewking.api.auth.controller.dto.LoginResDto;
import org.example.interviewking.api.auth.controller.dto.MyInfoResDto;
import org.example.interviewking.api.auth.service.OAuthService;
import org.example.interviewking.api.auth.service.dto.TokenInfoDto;
import org.example.interviewking.api.auth.util.JwtCookieProvider;
import org.example.interviewking.api.common.model.BaseResponse;
import org.example.interviewking.api.common.model.ResponseCode;
import org.example.interviewking.api.common.security.model.LoginMember;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.service.MemberQueryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService OAuthService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResDto>> login(
        @RequestBody LoginReqDto request
    ) {
        TokenInfoDto tokenInfo = OAuthService.login(request.provider(), request.code());
        LoginResDto response = new LoginResDto(tokenInfo.accessToken(), tokenInfo.refreshToken());

        return ResponseEntity.ok()
            .body(BaseResponse.of(ResponseCode.LOGIN_SUCCESS, response));
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<MyInfoResDto>> getMyInfo(
        @AuthenticationPrincipal LoginMember loginMember
    ) {
        Member member = memberQueryService.getById(loginMember.getMemberId());
        MyInfoResDto response = new MyInfoResDto(
            member.getId(),
            member.getName(),
            member.getEmail()
        );

        return ResponseEntity.ok(BaseResponse.of(ResponseCode.LOGIN_SUCCESS, response));
    }
}
