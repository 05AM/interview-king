package org.example.interviewking.api.qna.domain.answer;

import lombok.Getter;

@Getter
public enum AnswerGrade {
    A_PLUS("A+", 90),
    A("A", 85),
    A_MINUS("A-", 80),
    B_PLUS("B+", 75),
    B("B", 70),
    B_MINUS("B-", 65),
    C_PLUS("C+", 60),
    C("C", 55),
    C_MINUS("C-", 50),
    D("D", 45)
    ;

    private String display;
    private int limitScore;

    AnswerGrade(String display, int limitScore) {
        this.display = display;
        this.limitScore = limitScore;
    }

    public static AnswerGrade getByScore(int score) {
        for (AnswerGrade grade : AnswerGrade.values()) {
            if (score >= grade.limitScore) {
                return grade;
            }
        }
        return D;
    }
}
