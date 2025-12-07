package org.example.interviewking.api.ai.service;

import java.util.List;

import org.example.interviewking.api.ai.infra.ai.dto.AiQueryResDto;
import org.example.interviewking.api.ai.infra.ai.prompt.InterviewEvaluationPromptBuilder;
import org.example.interviewking.api.ai.port.out.AiQueryClient;
import org.example.interviewking.api.ai.service.dto.InterviewEvaluateReqDto;
import org.example.interviewking.api.ai.service.dto.InterviewEvaluationResDto;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerEvaluationService {

    private final AiQueryClient aiQueryClient;
    private final InterviewEvaluationPromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;

    public InterviewEvaluationResDto evaluate(InterviewEvaluateReqDto reqDto) {
        List<String> messages = promptBuilder.buildMessages(reqDto);
        
        AiQueryResDto resDto = aiQueryClient.query(messages.get(0), messages.get(1), 10000, 1);
        String json = resDto.choices().getFirst().message().content();

        System.out.println(json);

        try {
            return objectMapper.readValue(json, InterviewEvaluationResDto.class);
        } catch (Exception e) {
            // TODO: 커스텀 에러로 변환하기
            throw new IllegalStateException("면접 평가 결과 JSON 파싱 실패", e);
        }
    }
}
