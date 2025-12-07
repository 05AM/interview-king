package org.example.interviewking.api.qna.infra.persistence.repository.impl;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionTag;
import org.example.interviewking.api.qna.domain.question.repository.TagRepository;
import org.example.interviewking.api.qna.infra.persistence.repository.TagJpaRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final TagJpaRepository tagJpaRepository;

    @Override
    public List<QuestionTag> saveAll(List<QuestionTag> tags) {
        return tagJpaRepository.saveAll(tags);
    }
}
