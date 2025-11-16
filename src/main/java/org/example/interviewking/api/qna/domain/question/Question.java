package org.example.interviewking.api.qna.domain.question;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.interviewking.api.common.domain.BaseCreatedAtEntity;
import org.example.interviewking.api.member.domain.Member;

@Getter
@Entity
@Table(name = "question")
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

    @Lob
    @Column(name = "model_answer", nullable = false)
    private String modelAnswer;

    @Column(name = "last_answered_at", nullable = false)
    private LocalDateTime lastAnsweredAt;
}
