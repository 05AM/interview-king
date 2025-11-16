package org.example.interviewking.api.qna.domain.answer;

import java.util.List;

import org.example.interviewking.api.common.domain.converter.StringListConverter;
import org.example.interviewking.api.common.domain.entity.BaseCreatedAtEntity;
import org.example.interviewking.api.qna.domain.question.Question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseCreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "my_answer", nullable = false)
    private String myAnswer;

    @Column(name = "pros", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> pros;

    @Column(name = "cons", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> cons;

    @Embedded
    private AnswerScore score;

    @Column(name = "comment", nullable = false)
    private String comment;

    public Answer(Question question, String myAnswer, List<String> pros, List<String> cons, String comment, AnswerScore score) {
        this.question = question;
        this.myAnswer = myAnswer;
        this.pros = pros;
        this.cons = cons;
        this.comment = comment;
        this.score = score;
    }

    public static Answer create(Question question, String myAnswer, List<String> pros, List<String> cons, String comment, AnswerScore score) {
        return new Answer(question, myAnswer, pros, cons, comment, score);
    }
}
