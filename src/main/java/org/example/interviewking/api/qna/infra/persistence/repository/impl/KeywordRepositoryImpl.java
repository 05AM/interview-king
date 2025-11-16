package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.Keyword;
import org.example.interviewking.api.qna.domain.question.repository.KeywordRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.KeywordJpaRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {

    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public Keyword save(Keyword keyword) {
        return keywordJpaRepository.save(keyword);
    }

    @Override
    public Optional<Keyword> findByName(String name) {
        return keywordJpaRepository.findByName(name);
    }
}
