package org.example.interviewking.api.auth.infra.persistence.repository;

import org.example.interviewking.api.auth.domain.OAuthAccount;
import org.example.interviewking.api.auth.domain.OAuthRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OAuthRepositoryImpl implements OAuthRepository {

    private final OAuthJpaRepository oAuthJpaRepository;

    @Override
    public OAuthAccount save(OAuthAccount oAuthAccount) {
        return oAuthJpaRepository.save(oAuthAccount);
    }
}
