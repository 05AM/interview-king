package org.example.interviewking.api.qna.domain.question.repository;

import java.util.Optional;

import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.qna.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepository {

    Optional<Question> findById(Long id);

    Optional<Question> findMemberQuestionById(Long memberId, Long questionId);

    Page<Question> findAllByMemberId(Long memberId, Pageable pageable);

    boolean isQuestionDuplicate(Long memberId, String text);

    Question save(Question question);
}
