package org.example.interviewking.api.member.service;

import org.example.interviewking.api.common.exception.AlreadyExistsException;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member create(String email, String name) {
        if (memberRepository.isEmailDuplicate(email)) {
            throw new AlreadyExistsException("이미 존재하는 이메일 입니다.");
        }

        Member created = Member.create(name, email);
        memberRepository.save(created);

        return created;
    }
}
