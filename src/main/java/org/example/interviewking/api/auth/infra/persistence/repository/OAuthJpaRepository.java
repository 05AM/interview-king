package org.example.interviewking.api.auth.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.auth.domain.OAuthAccount;
import org.example.interviewking.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthJpaRepository extends JpaRepository<OAuthAccount, Long> {
    Optional<OAuthAccount> findByMember(Member member);
}
