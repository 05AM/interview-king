package org.example.interviewking.api.qna.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.example.interviewking.api.qna.domain.answer.Answer;
import org.example.interviewking.api.qna.domain.answer.AnswerScore;

public record AnswerDetailResDto(
    Long id,
    Long questionId,
    String myAnswer,
    List<String> pros,
    List<String> cons,
    String comment,
    LocalDateTime createdAt,
    Score score
) {
    public record Score(
        int totalScore,
        int logicScore,
        int accuracyScore,
        int structureScore,
        int practicalityScore,
        String grade
    ) {}

    public static AnswerDetailResDto toDto(Answer answer) {
        AnswerScore score = answer.getScore();

        return new AnswerDetailResDto(
            answer.getId(),
            answer.getQuestion().getId(),
            answer.getMyAnswer(),
            answer.getPros(),
            answer.getCons(),
            answer.getComment(),
            answer.getCreatedAt(),
            new AnswerDetailResDto.Score(
                score.getTotalScore(),
                score.getLogicScore(),
                score.getAccuracyScore(),
                score.getStructureScore(),
                score.getPracticalityScore(),
                score.getGrade().getDisplay()
            )
        );
    }
}
