package org.example.interviewking.api.qna.service;

import java.util.List;

import org.example.interviewking.api.qna.domain.answer.Answer;
import org.example.interviewking.api.qna.domain.answer.AnswerRepository;
import org.example.interviewking.api.qna.domain.answer.AnswerScore;
import org.example.interviewking.api.qna.domain.question.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(
        Question question, String myAnswer, List<String> pros, List<String> cons, String comment, AnswerScore score
    ) {
        Answer answer = Answer.create(
            question, myAnswer, pros, cons, comment, score
        );

        return answerRepository.save(answer);
    }
}
