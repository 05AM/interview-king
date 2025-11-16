package org.example.interviewking.api.qna.domain.question;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.interviewking.api.common.domain.entity.BaseCreatedAtEntity;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.qna.domain.answer.Answer;

@Getter
@Entity
@Table(name = "question")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseCreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private QuestionCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String text;

    @Column(name = "model_answer")
    private String modelAnswer;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(name = "last_answered_at")
    private LocalDateTime lastAnsweredAt;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<QuestionTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<QuestionKeyword> keywords = new ArrayList<>();

    private Question(QuestionCategory category, Member member, String text, QuestionStatus status) {
        this.category = category;
        this.member = member;
        this.text = text;
        this.status = status;
    }

    public static Question create(QuestionCategory category, Member member, String text) {
        return new Question(category, member, text, QuestionStatus.PENDING);
    }

    public void updateLastAnsweredAt() {
        this.lastAnsweredAt = LocalDateTime.now();
    }

    public void complete(String modelAnswer, List<QuestionKeyword> keywords, List<QuestionTag> tags) {
        this.modelAnswer = modelAnswer;
        this.keywords = keywords;
        this.tags = tags;
        this.status = QuestionStatus.COMPLETED;
    }
}
