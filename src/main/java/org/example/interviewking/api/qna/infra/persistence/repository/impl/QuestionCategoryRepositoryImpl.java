package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.QuestionCategory;
import org.example.interviewking.api.qna.domain.question.repository.QuestionCategoryRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.QuestionCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionCategoryRepositoryImpl implements QuestionCategoryRepository {

    private final QuestionCategoryJpaRepository questionCategoryJpaRepository;

    @Override
    public Optional<QuestionCategory> findByName(String name) {
        return questionCategoryJpaRepository.findByName(name);
    }
}
