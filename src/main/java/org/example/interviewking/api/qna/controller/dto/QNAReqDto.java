package org.example.interviewking.api.qna.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QNAReqDto(
    Long questionId,
    @NotNull String category,
    @NotBlank @Size(min=10, max = 500) String question,
    @NotBlank @Size(min=10, max = 1000) String myAnswer
) {
}