package org.example.interviewking.api.qna.controller.dto;

import java.util.List;

public record QuestionDetailResDto(
    QuestionResDto question,
    List<AnswerDetailResDto> answers
) {
}
