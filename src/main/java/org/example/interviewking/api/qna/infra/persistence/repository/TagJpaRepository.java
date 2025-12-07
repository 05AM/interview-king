package org.example.interviewking.api.qna.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<QuestionTag, Long> {
    Optional<QuestionTag> findByName(String name);
}
