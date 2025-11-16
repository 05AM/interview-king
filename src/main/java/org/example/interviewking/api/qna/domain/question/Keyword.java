package org.example.interviewking.api.qna.domain.question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@Entity
@Table(
    name = "keyword",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "keyword_name_unique",
            columnNames = "name"
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Builder
    private Keyword(String name) {
        this.name = name;
    }
}
