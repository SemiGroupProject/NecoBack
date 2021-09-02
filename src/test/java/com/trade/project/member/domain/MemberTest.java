package com.trade.project.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.trade.project.fixture.MemberFixture.CHANGE_PASSWORD;
import static com.trade.project.fixture.MemberFixture.MEMBER1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberTest {



    @DisplayName("비밀번호 변경")
    @Test
    void updatePassword() {
        MEMBER1.updatePassword(CHANGE_PASSWORD);

        assertThat(MEMBER1.getPassword()).isEqualTo(CHANGE_PASSWORD);
    }

    @DisplayName("비밀번호 변경: 예외처리")
    @Test
    void updatePasswordException() {
        String updatePasswordNull = null;
        String updatePasswordBlank = "";

        assertAll(
                () -> assertThatThrownBy(() -> MEMBER1.updatePassword(updatePasswordNull))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("There is no password to change"),

                () -> assertThatThrownBy(() -> MEMBER1.updatePassword(updatePasswordBlank))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("There is no password to change")
        );
    }


    @DisplayName("주소정보 변경")
    @Test
    void updateAddressInfo() {
        AddressInfo address = new AddressInfo(5678, "경기도");

        MEMBER1.updateAddressInfo(address);

        assertAll(
                () -> assertThat(MEMBER1.getAddressInfo().getZipNo()).isEqualTo(address.getZipNo()),
                () -> assertThat(MEMBER1.getAddressInfo().getStreet()).isEqualTo(address.getStreet())
        );
    }

    @DisplayName("주소정보 변경: 예외처리")
    @Test
    void updateAddressInfoException() {
        AddressInfo address = null;

        assertThatThrownBy(() -> MEMBER1.updateAddressInfo(address))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no address info to change");
    }
}