package org.example.interviewking.api.auth.service;

import java.util.List;

import org.example.interviewking.api.auth.domain.OAuthAccount;
import org.example.interviewking.api.auth.domain.OAuthRepository;
import org.example.interviewking.api.auth.domain.exception.UnsupportedOAuthProviderException;
import org.example.interviewking.api.auth.dto.OAuthTokenInfo;
import org.example.interviewking.api.auth.dto.OAuthUserInfo;
import org.example.interviewking.api.auth.domain.OAuthProvider;
import org.example.interviewking.api.auth.port.out.OAuthClient;
import org.example.interviewking.api.auth.service.dto.TokenInfoDto;
import org.example.interviewking.api.auth.util.JwtProvider;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.domain.MemberRepository;
import org.example.interviewking.api.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final List<OAuthClient> clients;

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    private final MemberRepository memberRepository;
    private final OAuthRepository oAuthRepository;

    public TokenInfoDto login(OAuthProvider provider, String code) {
        OAuthClient client = clients.stream()
            .filter(c -> c.supports(provider))
            .findFirst()
            .orElseThrow(UnsupportedOAuthProviderException::new);

        // OAuth 서버에서 정보 조회
        OAuthTokenInfo tokenInfo = client.requestAccessToken(code);
        OAuthUserInfo userInfo = client.requestUserInfo(tokenInfo.accessToken());

        // 존재하지 않으면 새 멤버 생성
        Member member = memberRepository.findByEmail(userInfo.email())
            .orElseGet(() -> memberService.create(userInfo.email(), userInfo.name()));

        // 존재하지 않으면 새로운 OAuth 계정 정보 생성
        oAuthRepository.findByMember(member)
            .orElseGet(() -> oAuthRepository.save(
                OAuthAccount.create(member, provider, userInfo.id(), userInfo.email(), tokenInfo.refreshToken())
            ));

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtProvider.createRefreshToken(member.getId());

        return new TokenInfoDto(accessToken, refreshToken);
    }
}
