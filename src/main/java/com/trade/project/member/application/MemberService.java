package com.trade.project.member.application;

import com.trade.project.member.domain.Member;

public interface MemberService {

    MemberExistResponse isExistAccountId(String accountId);

    void updateProfile(Member member, ProfileRequest request);

    LoginResponse signUp(LoginRequest request);

    void join(JoinRequest request);

}
