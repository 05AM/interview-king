package org.example.interviewking.api.common.security.model;

import org.example.interviewking.api.member.domain.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginMember {
    private Long memberId;
    private MemberRole role;
}
