package org.example.interviewking.api.qna.service;

import java.util.List;

import org.example.interviewking.api.common.exception.AlreadyExistsException;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.service.MemberQueryService;
import org.example.interviewking.api.qna.domain.question.Question;
import org.example.interviewking.api.qna.domain.question.QuestionCategory;
import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.QuestionTag;
import org.example.interviewking.api.qna.domain.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final MemberQueryService memberQueryService;
    private final QuestionQueryService questionQueryService;

    private final QuestionRepository questionRepository;

    public Question upsert(Long questionId, Long memberId, QuestionCategory category, String text) {
        Member member = memberQueryService.getById(memberId);
        Question question = questionId != null
            ? questionQueryService.getById(questionId)
            : create(member, category, text);
        question.updateLastAnsweredAt();

        return question;
    }

    public Question create(Member member, QuestionCategory category, String text) {
        if (questionRepository.isQuestionDuplicate(member.getId(), text)) {
            throw new AlreadyExistsException("동일한 내용의 질문이 이미 존재합니다.");
        }

        Question created = Question.create(category, member, text);
        questionRepository.save(created);

        return created;
    }

    public Question complete(Question question, String modelAnswer, List<QuestionKeyword> keywords, List<QuestionTag> tags) {
        question.complete(modelAnswer, keywords, tags);
        return questionRepository.save(question);
    }
}
