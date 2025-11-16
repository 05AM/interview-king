package org.example.interviewking.api.qna.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordJpaRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByName(String name);
}
