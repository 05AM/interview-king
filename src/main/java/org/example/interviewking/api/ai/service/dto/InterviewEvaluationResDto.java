package org.example.interviewking.api.ai.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InterviewEvaluationResDto(
    String version,
    String language,
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
    @JsonProperty("structure_coverage")
    StructureCoverage structureCoverage,
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

    public record StructureCoverage(
        @JsonProperty("conclusion_first")
        boolean conclusionFirst,
        boolean definition,
        boolean features,
        @JsonProperty("pros_and_cons")
        boolean prosAndCons,
        @JsonProperty("example_or_case")
        boolean exampleOrCase
    ) {
    }
}
