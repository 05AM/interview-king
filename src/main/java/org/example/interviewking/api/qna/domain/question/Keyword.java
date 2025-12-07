package org.example.interviewking.api.qna.domain.question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    private Keyword(String name) {
        this.name = name;
    }

    public static Keyword create(String name) {
        return new Keyword(name);
    }
}
