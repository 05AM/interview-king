package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.Question;
import org.example.interviewking.api.qna.domain.question.repository.QuestionRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.QuestionJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;

    @Override
    public Optional<Question> findById(Long id) {
        return questionJpaRepository.findById(id);
    }

    @Override
    public Optional<Question> findMemberQuestionById(Long memberId, Long questionId) {
        return questionJpaRepository.findByMemberIdAndId(memberId, questionId);
    }

    @Override
    public Page<Question> findAllByMemberId(Long memberId, Pageable pageable) {
        return questionJpaRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId, pageable);
    }

    @Override
    public boolean isQuestionDuplicate(Long memberId, String text) {
        return questionJpaRepository.existsByMemberIdAndText(memberId, text);
    }

    @Override
    public Question save(Question question) {
        return questionJpaRepository.save(question);
    }
}
