package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileResponse {
    private final String accountId;
    private final String name;
    private final String phoneNumber;
    private final AddressInfo addressInfo;

    public ProfileResponse(Member member) {
        accountId = member.getAccountId();
        name = member.getName();
        phoneNumber = member.getPhoneNumber();
        addressInfo = member.getAddressInfo();
    }
}
