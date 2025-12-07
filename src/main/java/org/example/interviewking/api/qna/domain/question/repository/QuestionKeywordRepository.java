package org.example.interviewking.api.qna.domain.question.repository;

import java.util.List;

import org.example.interviewking.api.qna.domain.question.QuestionKeyword;

public interface QuestionKeywordRepository {

    List<QuestionKeyword> saveAll(List<QuestionKeyword> questionKeywords);
}
