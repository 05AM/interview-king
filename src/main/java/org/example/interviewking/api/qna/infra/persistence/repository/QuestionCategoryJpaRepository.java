package org.example.interviewking.api.qna.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCategoryJpaRepository extends JpaRepository<QuestionCategory, Long> {

    Optional<QuestionCategory> findByName(String name);
}
