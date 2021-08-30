package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import lombok.Getter;

@Getter
public class JoinRequest {
    private String accountId;

    private String password;

    private String name;

    private String phoneNumber;

    private AddressInfo addressInfo;
}
