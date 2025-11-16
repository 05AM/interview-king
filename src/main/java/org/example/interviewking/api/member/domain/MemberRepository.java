package org.example.interviewking.api.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(long id);

    Optional<Member> findByEmail(String email);

    boolean isEmailDuplicate(String email);

    Member save(Member member);
}
