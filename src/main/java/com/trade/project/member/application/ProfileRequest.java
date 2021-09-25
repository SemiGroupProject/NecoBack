package com.trade.project.member.application;

import com.trade.project.member.domain.AddressInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProfileRequest {
    private String phoneNumber;
    private AddressInfo addressInfo;

    public ProfileRequest(String phoneNumber, AddressInfo addressInfo) {
        this.phoneNumber = phoneNumber;
        this.addressInfo = addressInfo;
    }
}
