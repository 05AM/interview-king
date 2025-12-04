package org.example.interviewking.api.qna.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.interviewking.api.ai.service.AnswerEvaluationService;
import org.example.interviewking.api.ai.service.dto.InterviewEvaluateReqDto;
import org.example.interviewking.api.ai.service.dto.InterviewEvaluationResDto;
import org.example.interviewking.api.qna.controller.dto.QNAReqDto;
import org.example.interviewking.api.qna.controller.dto.QNAResDto;
import org.example.interviewking.api.qna.domain.answer.Answer;
import org.example.interviewking.api.qna.domain.answer.AnswerScore;
import org.example.interviewking.api.qna.domain.question.Keyword;
import org.example.interviewking.api.qna.domain.question.Question;
import org.example.interviewking.api.qna.domain.question.QuestionCategory;
import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.QuestionStatus;
import org.example.interviewking.api.qna.domain.question.QuestionTag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class QNAService {

    private final AnswerEvaluationService evaluationService;

    private final QuestionCategoryQueryService questionCategoryQueryService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionKeywordService questionKeywordService;
    private final TagService tagService;

    public QNAResDto evaluate(Long memberId, QNAReqDto request) {
        // 질문 생성
        QuestionCategory category = questionCategoryQueryService.getByName(request.category());
        Question question = questionService.upsert(
            request.questionId(), memberId, category, request.question()
        );

        InterviewEvaluationResDto evaluated = evaluationService.evaluate(
            InterviewEvaluateReqDto.of(
                question.getText(), request.myAnswer(), question.getTags(), question.getKeywords(),
                question.getModelAnswer()
            )
        );

        // 새로운 질문이면
        if (QuestionStatus.PENDING.equals(question.getStatus())) {
            // 새 키워드 생성 및 기존 키워드 가져오기
            Map<Keyword, Boolean> keywordMap =
                evaluated.keywordMatch().stream()
                    .collect(Collectors.toMap(
                        km -> questionKeywordService.getOrCreateKeyword(km.keyword()),
                        InterviewEvaluationResDto.KeywordMatch::matched
                    ));

            List<QuestionKeyword> keywords = keywordMap.entrySet().stream()
                .map(entry -> QuestionKeyword.create(entry.getKey(), entry.getValue(), question))
                .collect(Collectors.toList());
            questionKeywordService.createAll(keywords);

            // 태그 저장
            List<QuestionTag> tags = evaluated.tags().stream()
                .map(tag -> QuestionTag.create(tag, question))
                .collect(Collectors.toList());
            tagService.createAll(tags);

            // 모범 답안 저장
            questionService.complete(question, evaluated.modelAnswer(), keywords, tags);
        }

        // 답변 저장
        AnswerScore score = AnswerScore.of(
            evaluated.score().logic(), evaluated.score().accuracy(), evaluated.score().structure(),
            evaluated.score().practicality()
        );
        Answer answer = answerService.create(
            question, evaluated.cleanedAnswer(), evaluated.feedback().good(), evaluated.feedback().bad(),
            evaluated.summary(), score
        );

        return buildQNAResDto(question, answer,  evaluated);
    }

    // TODO: DTO 안에서 하도록 수정하기
    private QNAResDto buildQNAResDto(Question question, Answer answer, InterviewEvaluationResDto evaluated) {
        return QNAResDto.builder()
            .tags(
                question.getTags().stream()
                    .map(QuestionTag::getName)
                    .toList()
            )
            .essentialKeywords(
                question.getKeywords().stream()
                    .map(qk -> qk.getKeyword().getName())
                    .distinct()
                    .toList()
            )
            .keywordMatch(
                question.getKeywords().stream()
                    .map(qk -> new QNAResDto.KeywordMatch(
                        qk.getKeyword().getName(),
                        qk.isLastMatched()
                    ))
                    .toList()
            )
            .feedback(
                new QNAResDto.Feedback(
                    answer.getPros(),
                    answer.getCons()
                )
            )
            .score(
                new QNAResDto.Score(
                    answer.getScore().getTotalScore(),
                    answer.getScore().getLogicScore(),
                    answer.getScore().getAccuracyScore(),
                    answer.getScore().getStructureScore(),
                    answer.getScore().getPracticalityScore(),
                    evaluated.score().comment()
                )
            )
            .summary(answer.getComment())
            .cleanedAnswer(answer.getMyAnswer())
            .modelAnswer(question.getModelAnswer())
            .followUpQuestions(evaluated.followUpQuestions())
            .build();
    }
}
