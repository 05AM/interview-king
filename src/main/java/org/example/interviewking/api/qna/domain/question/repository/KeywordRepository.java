package org.example.interviewking.api.qna.domain.question.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.Keyword;

public interface KeywordRepository {

    Keyword save(Keyword keyword);

    Optional<Keyword> findByName(String name);
}
