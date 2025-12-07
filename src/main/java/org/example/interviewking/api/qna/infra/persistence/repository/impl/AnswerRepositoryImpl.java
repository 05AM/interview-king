package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import org.example.interviewking.api.qna.domain.answer.Answer;
import org.example.interviewking.api.qna.domain.answer.AnswerRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.AnswerJpaRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerJpaRepository answerJpaRepository;

    @Override
    public Answer save(Answer answer) {
        return answerJpaRepository.save(answer);
    }
}
