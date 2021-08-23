package com.trade.project.member.application;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileRequest {
    private String password;
    private String phoneNumber;
    private int zipCode;
    private String street;

    public ProfileRequest(String password, String phoneNumber, int zipCode, String street) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.street = street;
    }
}
