package org.example.interviewking.api.qna.service;

import org.example.interviewking.api.common.dto.PageInfoResDto;
import org.example.interviewking.api.common.exception.NotFoundException;
import org.example.interviewking.api.qna.controller.dto.AnswerDetailResDto;
import org.example.interviewking.api.qna.controller.dto.QuestionDetailResDto;
import org.example.interviewking.api.qna.controller.dto.QuestionResDto;
import org.example.interviewking.api.qna.controller.dto.QuestionsResDto;
import org.example.interviewking.api.qna.domain.question.Question;
import org.example.interviewking.api.qna.domain.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class QuestionQueryService {

    private final QuestionRepository questionRepository;

    public Question getById(long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("요청한 id에 해당하는 멤버를 찾을 수 없습니다."));
    }

    public QuestionsResDto getMemberQuestions(Long memberId, Pageable pageable) {
        Page<QuestionResDto> questions = questionRepository.findAllByMemberId(memberId, pageable)
            .map(QuestionResDto::toDto);
        PageInfoResDto pageInfo = PageInfoResDto.from(questions);

        return new QuestionsResDto(questions.toList(), pageInfo);
    }

    public QuestionDetailResDto getQuestionDetail(Long memberId, Long questionId) {
        // TODO: 연관관계 설정하고 한꺼번에 불러오기
        Question question = questionRepository.findMemberQuestionById(memberId, questionId)
            .orElseThrow(() -> new NotFoundException("요청에 해당하는 질문이 존재하지 않습니다."));

        return new QuestionDetailResDto(
            QuestionResDto.toDto(question),
            question.getAnswers().stream()
                .map(AnswerDetailResDto::toDto)
                .toList()
        );
    }
}
