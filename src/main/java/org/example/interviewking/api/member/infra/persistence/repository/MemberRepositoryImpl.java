package org.example.interviewking.api.member.infra.persistence.repository;

import java.util.Optional;

import org.example.interviewking.api.common.exception.NotImplementedException;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.domain.MemberRepository;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        throw new NotImplementedException();
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }
}
