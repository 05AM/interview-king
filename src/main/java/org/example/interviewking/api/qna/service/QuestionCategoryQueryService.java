package org.example.interviewking.api.qna.service;

import org.example.interviewking.api.common.exception.NotFoundException;
import org.example.interviewking.api.qna.domain.question.QuestionCategory;
import org.example.interviewking.api.qna.domain.question.repository.QuestionCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class QuestionCategoryQueryService {

    private final QuestionCategoryRepository questionCategoryRepository;

    public QuestionCategory getByName(String name) {
        return questionCategoryRepository.findByName(name)
            .orElseThrow(() -> new NotFoundException("요청에 해당하는 카테고리가 존재하지 않습니다."));
    }
}
