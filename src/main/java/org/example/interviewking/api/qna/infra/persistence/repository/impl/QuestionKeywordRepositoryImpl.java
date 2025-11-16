package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.repository.QuestionKeywordRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.QuestionKeywordJpaRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionKeywordRepositoryImpl implements QuestionKeywordRepository {

    private final QuestionKeywordJpaRepository questionKeywordJpaRepository;

    @Override
    public List<QuestionKeyword> saveAll(List<QuestionKeyword> questionKeywords) {
        return questionKeywordJpaRepository.saveAll(questionKeywords);
    }
}
