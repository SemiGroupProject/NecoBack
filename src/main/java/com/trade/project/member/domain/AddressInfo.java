package com.trade.project.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressInfo {

    private int zipNo;
    private String address;

    public AddressInfo(int zipNo, String address) {
        this.zipNo = zipNo;
        this.address = address;
    }
}
