package org.example.interviewking.api.ai.service.dto;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.QuestionTag;

public record InterviewEvaluateReqDto(
    String question,
    String answer,
    List<String> tags,
    List<String> essentialKeywords,
    String modelAnswer
    // List<String> followUpQuestions
) {

    public static InterviewEvaluateReqDto of(
        String question, String answer, List<QuestionTag> questionTags, List<QuestionKeyword> questionKeywords, String modelAnswer
    ) {
        List<String> tags = questionTags.stream()
            .map(String::valueOf)
            .toList();
        List<String> keywords = questionKeywords.stream()
            .map(String::valueOf)
            .toList();

        return new InterviewEvaluateReqDto(question, answer, tags, keywords, modelAnswer);
    }
}
