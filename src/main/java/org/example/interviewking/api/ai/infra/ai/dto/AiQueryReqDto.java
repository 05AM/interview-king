package org.example.interviewking.api.ai.infra.ai.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AiQueryReqDto(
    String model,
    List<Message> messages,
    @JsonProperty("max_completion_tokens")
    Integer maxCompletionTokens,
    Double temperature
) {
    public record Message(
        String role,
        String content
    ) {
    }
}
