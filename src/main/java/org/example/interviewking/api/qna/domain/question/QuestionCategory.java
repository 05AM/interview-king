package org.example.interviewking.api.qna.domain.question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "question_category",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "question_category_name_unique",
            columnNames = "name"
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    private QuestionCategory(String name) {
        this.name = name;
    }
}
