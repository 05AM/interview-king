package org.example.interviewking.api.common.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.interviewking.api.auth.util.JwtProvider;
import org.example.interviewking.api.common.constant.AuthConstants;
import org.example.interviewking.api.common.security.model.LoginMember;
import org.example.interviewking.api.member.domain.MemberRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null) {
            try {
                jwtProvider.validateToken(token);

                Long memberId = jwtProvider.getMemberId(token);
                MemberRole role = jwtProvider.getMemberRole(token);
                LoginMember loginMember = new LoginMember(memberId, role);

                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        loginMember,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role.name()))
                    );

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // TODO: 시큐리티 예외 추가
                throw e;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(AuthConstants.AUTH_HEADER);
        if (bearer == null) {
            return null;
        }
        if (bearer.startsWith(AuthConstants.BEARER_PREFIX)) {
            return bearer.substring(7);
        }
        return null;
    }
}
