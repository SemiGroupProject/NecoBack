package com.trade.project.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        this.member = Member.builder()
                .accountId("id")
                .addressInfo(new AddressInfo(1234, "서울시"))
                .name("user1")
                .password("1234")
                .phoneNumber("010-1111-1111")
                .build();
    }


    @DisplayName("비밀번호 변경")
    @Test
    void updatePassword() {
        String updatePassword = "4232";

        member.updatePassword(updatePassword);

        assertThat(member.getPassword()).isEqualTo(updatePassword);
    }

    @DisplayName("비밀번호 변경: 예외처리")
    @Test
    void updatePasswordException() {
        String updatePasswordNull = null;
        String updatePasswordBlank = "";

        assertAll(
                () -> assertThatThrownBy(() -> member.updatePassword(updatePasswordNull))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("There is no password to change"),

                () -> assertThatThrownBy(() -> member.updatePassword(updatePasswordBlank))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("There is no password to change")
        );
    }


    @DisplayName("주소정보 변경")
    @Test
    void updateAddressInfo() {
        AddressInfo address = new AddressInfo(5678, "경기도");

        member.updateAddressInfo(address);

        assertAll(
                () -> assertThat(member.getAddressInfo().getZipNo()).isEqualTo(address.getZipNo()),
                () -> assertThat(member.getAddressInfo().getAddress()).isEqualTo(address.getAddress())
        );
    }

    @DisplayName("주소정보 변경: 예외처리")
    @Test
    void updateAddressInfoException() {
        AddressInfo address = null;

        assertThatThrownBy(() -> member.updateAddressInfo(address))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no address info to change");
    }
}