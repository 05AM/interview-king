package org.example.interviewking.api.qna.controller;

import org.example.interviewking.api.common.model.BaseResponse;
import org.example.interviewking.api.common.model.ResponseCode;
import org.example.interviewking.api.common.security.model.LoginMember;
import org.example.interviewking.api.qna.controller.dto.QNAReqDto;
import org.example.interviewking.api.qna.controller.dto.QNAResDto;
import org.example.interviewking.api.qna.service.QNAService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QNAController {

    private final QNAService qnaService;

    @PostMapping("/qna")
    public ResponseEntity<BaseResponse<QNAResDto>> qna(
        @AuthenticationPrincipal LoginMember loginMember,
        @RequestBody QNAReqDto request
    ) {
        QNAResDto response = qnaService.evaluate(loginMember.getMemberId(), request);

        return ResponseEntity.ok(
            BaseResponse.of(ResponseCode.SUCCESS, response)
        );
    }
}
