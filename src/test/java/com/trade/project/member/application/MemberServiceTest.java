package com.trade.project.member.application;

import com.trade.project.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.trade.project.fixture.MemberFixture.MEMBER1;
import static com.trade.project.fixture.MemberFixture.PROFILE_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository);
    }

    @DisplayName("아이디 확인: 존재x")
    @Test
    void notExistAccountId() {
        when(memberRepository.findOptionalByAccountId(anyString())).thenReturn(Optional.empty());

        assertThat(memberService.isExistAccountId("user1")).isFalse();
    }

    @DisplayName("아이디 확인: 존재o")
    @Test
    void existAccountId() {
        when(memberRepository.findOptionalByAccountId(anyString())).thenReturn(Optional.of(MEMBER1));

        assertThat(memberService.isExistAccountId("user1")).isTrue();
    }

    @DisplayName("회원정보 변경")
    @Test
    void updateProfile() {
        memberService.updateProfile(MEMBER1, PROFILE_REQUEST);

        assertAll(
                () -> assertThat(MEMBER1.getPhoneNumber()).isEqualTo(PROFILE_REQUEST.getPhoneNumber()),
                () -> assertThat(MEMBER1.getAddressInfo().getZipNo()).isEqualTo(PROFILE_REQUEST.getZipCode()),
                () -> assertThat(MEMBER1.getAddressInfo().getStreet()).isEqualTo(PROFILE_REQUEST.getStreet())
        );
    }

}