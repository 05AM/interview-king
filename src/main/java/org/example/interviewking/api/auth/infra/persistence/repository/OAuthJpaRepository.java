package org.example.interviewking.api.auth.infra.persistence.repository;

import org.example.interviewking.api.auth.domain.OAuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthJpaRepository extends JpaRepository<OAuthAccount, Long> {
}
