package org.example.interviewking.api.qna.infra.persistence.repository;

import org.example.interviewking.api.qna.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
}
