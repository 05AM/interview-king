package org.example.interviewking.api.qna.domain.answer;

import org.example.interviewking.api.common.domain.BaseCreatedAtEntity;
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

    @Column(name = "comment", nullable = false)
    private String comment;

    @Lob
    @Column(name = "my_answer", nullable = false)
    private String myAnswer;

    @Lob
    @Column(name = "pros", nullable = false)
    private String pros;

    @Lob
    @Column(name = "cons", nullable = false)
    private String cons;

    @Column(nullable = false)
    private int score;

    // TODO: 나중에 enum으로 바꾸기
    @Column(nullable = false, length = 20)
    private String grade;
}
