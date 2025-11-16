package org.example.interviewking.api.ai.infra.ai.prompt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.example.interviewking.api.ai.service.dto.InterviewEvaluateReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class InterviewEvaluationPromptBuilder {

    private final String systemTemplate;
    private final String userTemplate;

    public InterviewEvaluationPromptBuilder(
        @Value("${classpath.prompt.interview.system}") String systemPromptPath,
        @Value("${classpath.prompt.interview.user}") String userPromptPath
    ) {
        try {
            this.systemTemplate = loadTemplate(systemPromptPath);
            this.userTemplate = loadTemplate(userPromptPath);
        } catch (IOException e) {
            throw new IllegalStateException("인터뷰 프롬프트 템플릿 로딩 실패", e);
        }
    }

    private String loadTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    public List<String> buildMessages(InterviewEvaluateReqDto reqDto) {
        String userContent = userTemplate
            .replace("<<QUESTION>>", safe(reqDto.question()))
            .replace("<<ANSWER>>", safe(reqDto.answer()))
            .replace("<<TAGS>>", listToText(reqDto.tags()))
            .replace("<<ESSENTIAL_KEYWORDS>>", listToText(reqDto.essentialKeywords()))
            .replace("<<MODEL_ANSWER>>", safe(reqDto.modelAnswer()));
            // .replace("<<FOLLOW_UP>>", listToText(reqDto.followUpQuestions()));

        return List.of(systemTemplate, userContent);
    }

    private String safe(String value) {
        return Objects.requireNonNullElse(value, "");
    }

    private String listToText(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return String.join(", ", list);
    }
}
