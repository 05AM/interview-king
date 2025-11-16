package org.example.interviewking.api.qna.domain.question.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.QuestionCategory;

public interface QuestionCategoryRepository {

    Optional<QuestionCategory> findByName(String name);
}
