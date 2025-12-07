package org.example.interviewking.api.qna.domain.question;

import org.example.interviewking.api.common.domain.entity.BaseTimeEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "question_keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionKeyword extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "is_last_matched", nullable = false)
    private boolean lastMatched;

    @Builder(access = AccessLevel.PRIVATE)
    private QuestionKeyword(Keyword keyword, boolean lastMatched, Question question) {
        this.keyword = keyword;
        this.lastMatched = lastMatched;
        this.question = question;
    }

    public static QuestionKeyword create(Keyword keyword, boolean lastMatched, Question question) {
        return new QuestionKeyword(keyword, lastMatched, question);
    }
}
