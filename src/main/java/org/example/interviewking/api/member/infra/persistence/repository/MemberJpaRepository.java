package org.example.interviewking.api.member.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
