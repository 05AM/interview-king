package org.example.interviewking.api.qna.infra.persistence.repository;

import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionKeywordJpaRepository extends JpaRepository<QuestionKeyword, Long> {
}
