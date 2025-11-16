package org.example.interviewking.api.auth.util;

import javax.crypto.SecretKey;
import java.util.Date;

import org.example.interviewking.api.auth.model.JwtProperties;
import org.example.interviewking.api.common.exception.UnauthorizedException;
import org.example.interviewking.api.member.domain.MemberRole;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public String createAccessToken(Long memberId, MemberRole role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getAccessExpirationTime());

        return Jwts.builder()
            .subject(String.valueOf(memberId))
            .claim("role", role.toString())
            .issuedAt(now)
            .expiration(expiration)
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(Long memberId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getRefreshExpirationTime());

        return Jwts.builder()
            .subject(String.valueOf(memberId))
            .issuedAt(now)
            .expiration(expiration)
            .signWith(secretKey)
            .compact();
    }

    private Jws<Claims> parseClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new UnauthorizedException("인증 정보가 만료 되었습니다.");
        } catch (Exception e) {
            throw new UnauthorizedException("유효하지 않은 인증 정보입니다.");
        }
    }

    public void validateToken(String token) {
        parseClaims(token);
    }

    public Long getMemberId(String token) {
        return Long.parseLong(parseClaims(token).getPayload().getSubject());
    }

    public MemberRole getMemberRole(String token) {
        String role = parseClaims(token).getPayload().get("role", String.class);
        return MemberRole.valueOf(role);
    }
}
