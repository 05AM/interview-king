package org.example.interviewking.api.member.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.example.interviewking.api.common.domain.entity.BaseCreatedAtEntity;
import org.example.interviewking.api.qna.domain.stat.MemberKeywordStat;

@Getter
@Entity
@Table(
    name = "member",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "member_email_unique",
            columnNames = "email"
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseCreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<MemberKeywordStat> memberKeywordStats = new ArrayList<>();

    private Member(String name, String email, MemberRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static Member create(String name, String email) {
        return new Member(name, email, MemberRole.MEMBER);
    }
}
