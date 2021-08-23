package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;
import com.trade.project.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean isExistAccountId(String accountId) {
        return memberRepository.findOptionalByAccountId(accountId).isPresent();
    }

    @Transactional
    @Override
    public void updateProfile(Member member, ProfileRequest request) {
        member.updateAddressInfo(new AddressInfo(request.getZipCode(), request.getStreet()));
        member.updatePhoneNumber(request.getPhoneNumber());
    }
}
