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
    private String street;

    public AddressInfo(int zipNo, String street) {
        this.zipNo = zipNo;
        this.street = street;
    }
}
