package org.example.interviewking.api.qna.service;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.Keyword;
import org.example.interviewking.api.qna.domain.question.repository.KeywordRepository;
import org.example.interviewking.api.qna.domain.question.QuestionKeyword;
import org.example.interviewking.api.qna.domain.question.repository.QuestionKeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionKeywordService {

    private final KeywordRepository repository;
    private final QuestionKeywordRepository questionKeywordRepository;

    public Keyword getOrCreateKeyword(String name) {
        // TODO: 존재 여부 조회하고 한꺼번에 저장하도록 만들기
        return repository.findByName(name)
            .orElseGet(() -> repository.save(Keyword.create(name)));
    }

    public List<QuestionKeyword> createAll(List<QuestionKeyword> keywords) {
        return questionKeywordRepository.saveAll(keywords);
    }
}
