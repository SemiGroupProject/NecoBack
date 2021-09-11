package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;
import com.trade.project.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final LoginInfoValidator loginInfoValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberExistResponse isExistAccountId(String accountId) {
        boolean present = memberRepository.findOptionalByAccountId(accountId).isEmpty();

        return new MemberExistResponse(present);
    }

    @Transactional
    @Override
    public void updateProfile(Member member, ProfileRequest request) {
        member.updateAddressInfo(request.getAddressInfo());
        member.updatePhoneNumber(request.getPhoneNumber());
    }

    @Override
    public LoginResponse signUp(LoginRequest request) {
        Optional<Member> optionalMember = memberRepository.findOptionalByAccountId(request.getAccountId());

        return loginInfoValidator.validateInfo(optionalMember, request);
    }

    @Transactional
    @Override
    public void join(JoinRequest request) {
        Member member = new Member(request.getAccountId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhoneNumber(),
                request.getAddressInfo());
        memberRepository.save(member);
    }
}
