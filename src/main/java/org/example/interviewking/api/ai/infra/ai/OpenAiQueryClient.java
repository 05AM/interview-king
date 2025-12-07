package org.example.interviewking.api.ai.infra.ai;

import java.util.List;

import org.example.interviewking.api.ai.infra.ai.dto.AiQueryReqDto;
import org.example.interviewking.api.ai.infra.ai.dto.AiQueryResDto;
import org.example.interviewking.api.ai.port.out.AiQueryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OpenAiQueryClient implements AiQueryClient {

    private final RestClient restClient;
    private final String model;

    public OpenAiQueryClient(
        @Value("${openai.api-key}") String apiKey,
        @Value("${openai.base-url}") String baseUrl,
        @Value("${openai.model}") String model
    ) {
        this.model = model;
        this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Override
    public AiQueryResDto query(String systemPrompt, String userPrompt, int maxTokens, double temperature) {
        AiQueryReqDto reqDto = new AiQueryReqDto(
            model,
            List.of(
                new AiQueryReqDto.Message("system", systemPrompt),
                new AiQueryReqDto.Message("user", userPrompt)
            ),
            maxTokens,
            temperature
        );

        AiQueryResDto resDto = restClient.post()
            .uri("/chat/completions")
            .body(reqDto)
            .retrieve()
            .body(AiQueryResDto.class);

        if (resDto == null || resDto.choices() == null || resDto.choices().isEmpty()) {
            throw new IllegalStateException("OpenAI 응답이 비어 있습니다. resDto=" + resDto);
        }

        // 비용 추적용
        log.info("사용 모델 - {}", resDto.model());
        log.info("사용량 - 프롬프트: {}, 전체: {}", resDto.usage().promptTokens(), resDto.usage().totalTokens());

        return resDto;
    }
}
