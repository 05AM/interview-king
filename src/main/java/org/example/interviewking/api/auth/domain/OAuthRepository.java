package org.example.interviewking.api.auth.domain;

import java.util.Optional;

import org.example.interviewking.api.member.domain.Member;

public interface OAuthRepository {

    OAuthAccount save(OAuthAccount oAuthAccount);

    Optional<OAuthAccount> findByMember(Member member);
}
