package org.example.interviewking.api.qna.domain.question;

import org.example.interviewking.api.common.domain.entity.BaseCreatedAtEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@Entity
@Table(name = "question_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionTag extends BaseCreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false, length = 255)
    private String name;

    @Builder
    private QuestionTag(Question question, String name) {
        this.question = question;
        this.name = name;
    }

    private QuestionTag(String name, Question question) {
        this.name = name;
        this.question = question;
    }

    public static QuestionTag create(String name, Question question) {
        return new QuestionTag(name, question);
    }
}
