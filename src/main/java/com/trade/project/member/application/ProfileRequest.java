package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileRequest {
    private String password;
    private String phoneNumber;
    private AddressInfo addressInfo;

    public ProfileRequest(String password, String phoneNumber, AddressInfo addressInfo) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressInfo = addressInfo;
    }
}
