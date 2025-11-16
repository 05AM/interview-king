package org.example.interviewking.api.qna.domain.stat;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.qna.domain.question.Keyword;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@Entity
@Table(
    name = "member_keyword_stat",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_member_keyword_stat_member_keyword",
            columnNames = {"member_id", "keyword_id"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberKeywordStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @Column(name = "tried_count", nullable = false)
    private int triedCount;

    @Column(name = "matched_count", nullable = false)
    private int matchedCount;

    @Column(name = "last_matched_at")
    private LocalDateTime lastMatchedAt;

    @Builder
    private MemberKeywordStat(Member member,
        Keyword keyword,
        int triedCount,
        int matchedCount,
        LocalDateTime lastMatchedAt) {
        this.member = member;
        this.keyword = keyword;
        this.triedCount = triedCount;
        this.matchedCount = matchedCount;
        this.lastMatchedAt = lastMatchedAt;
    }
}

