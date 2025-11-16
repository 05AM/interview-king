package org.example.interviewking.api.qna.controller;

import org.example.interviewking.api.common.model.BaseResponse;
import org.example.interviewking.api.common.model.ResponseCode;
import org.example.interviewking.api.common.security.model.LoginMember;
import org.example.interviewking.api.qna.controller.dto.QuestionDetailResDto;
import org.example.interviewking.api.qna.controller.dto.QuestionsResDto;
import org.example.interviewking.api.qna.service.QuestionQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {

    private final QuestionQueryService questionQueryService;

    @GetMapping("/questions")
    public ResponseEntity<BaseResponse<QuestionsResDto>> getQuestions(
        @AuthenticationPrincipal LoginMember loginMember,
        Pageable pageable
    ) {
        QuestionsResDto response = questionQueryService.getMemberQuestions(loginMember.getMemberId(), pageable);
        return ResponseEntity.ok(BaseResponse.of(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/questions/{question_id}")
    public ResponseEntity<BaseResponse<QuestionDetailResDto>> getQuestions(
        @AuthenticationPrincipal LoginMember loginMember,
        @PathVariable(name = "question_id") Long questionId
    ) {
        QuestionDetailResDto response = questionQueryService.getQuestionDetail(loginMember.getMemberId(), questionId);
        return ResponseEntity.ok(BaseResponse.of(ResponseCode.SUCCESS, response));
    }
}
