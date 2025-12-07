package org.example.interviewking.api.qna.controller.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.example.interviewking.api.qna.domain.question.Question;
import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.QuestionTag;

public record QuestionResDto(
    Long id,
    String category,
    String text,
    String modelAnswer,
    List<String> tags,
    List<String> keywords,
    String status,
    LocalDateTime lastAnsweredAt
) {

    public static QuestionResDto toDto(Question question) {
        return new QuestionResDto(
            question.getId(),
            question.getCategory().getName(),
            question.getText(),
            question.getModelAnswer(),
            question.getTags().stream()
                .sorted(Comparator.comparing(QuestionTag::getId))
                .map(QuestionTag::getName)
                .toList(),
            question.getKeywords().stream()
                .sorted(Comparator.comparing(QuestionKeyword::getId))
                .map(keyword -> keyword.getKeyword().getName())
                .toList(),
            question.getStatus().name(),
            question.getLastAnsweredAt()
        );
    }
}
