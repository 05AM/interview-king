package org.example.interviewking.api.qna.controller.dto;

import java.util.List;

import org.example.interviewking.api.common.dto.PageInfoResDto;

public record QuestionsResDto(
    List<QuestionResDto> questions,
    PageInfoResDto pageInfo
) {
}
