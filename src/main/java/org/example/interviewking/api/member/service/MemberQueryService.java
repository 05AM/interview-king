package org.example.interviewking.api.member.service;

import org.example.interviewking.api.common.exception.NotFoundException;
import org.example.interviewking.api.member.domain.Member;
import org.example.interviewking.api.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Member getById(long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("요청한 id에 해당하는 멤버를 찾을 수 없습니다."));
    }

    public Member getByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("요청한 이메일에 해당하는 멤버를 찾을 수 없습니다."));
    }
}
