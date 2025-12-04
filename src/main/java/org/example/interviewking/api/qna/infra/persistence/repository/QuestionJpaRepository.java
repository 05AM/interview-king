package org.example.interviewking.api.qna.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.qna.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByMemberIdAndId(Long memberId, Long id);

    boolean existsByMemberIdAndText(Long memberId, String text);

    Page<Question> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}
