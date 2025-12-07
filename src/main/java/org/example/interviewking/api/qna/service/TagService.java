package org.example.interviewking.api.qna.service;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionTag;
import org.example.interviewking.api.qna.domain.question.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public List<QuestionTag> createAll(List<QuestionTag> tags) {
        return repository.saveAll(tags);
    }
}
