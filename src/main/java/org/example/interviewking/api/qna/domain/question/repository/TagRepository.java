package org.example.interviewking.api.qna.domain.question.repository;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionTag;

public interface TagRepository {

    List<QuestionTag> saveAll(List<QuestionTag> tags);
}
