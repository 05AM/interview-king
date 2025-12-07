package org.example.interviewking.api.qna.domain.answer;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerScore {

    @Column(name = "score", nullable = false)
    private int totalScore;

    @Comment("말의 논리정연함")
    @Column(name = "logic_score", nullable = false)
    private int logicScore;

    @Comment("내용의 정확도")
    @Column(name = "accuracy_score", nullable = false)
    private int accuracyScore;

    @Comment("답변 구조/완성도")
    @Column(name = "structure_score", nullable = false)
    private int structureScore;

    @Comment("실무 연계성")
    @Column(name = "practicality_score", nullable = false)
    private int practicalityScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false, length = 20)
    private AnswerGrade grade;

    public static AnswerScore of(
        int logicScore, int accuracy, int structure, int practicality
    ) {
        int total = logicScore + accuracy + structure + practicality;
        AnswerGrade grade = AnswerGrade.getByScore(total);
        return new AnswerScore(total, logicScore, accuracy, structure, practicality, grade);
    }
}
