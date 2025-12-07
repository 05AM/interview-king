package org.example.interviewking.api.qna.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record QNAResDto(
    List<String> tags,
    @JsonProperty("essential_keywords")
    List<String> essentialKeywords,
    @JsonProperty("keyword_match")
    List<KeywordMatch> keywordMatch,
    Feedback feedback,
    Score score,
    String summary,
    @JsonProperty("cleaned_answer")
    String cleanedAnswer,
    @JsonProperty("model_answer")
    String modelAnswer,
    @JsonProperty("follow_up_questions")
    List<String> followUpQuestions
) {

    public record KeywordMatch(
        String keyword,
        boolean matched
    ) {
    }

    public record Feedback(
        List<String> good,
        List<String> bad
    ) {
    }

    public record Score(
        int total,
        int logic,
        int accuracy,
        int structure,
        int practicality,
        String comment
    ) {
    }
}
