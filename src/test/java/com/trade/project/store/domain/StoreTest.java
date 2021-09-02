package com.trade.project.store.domain;

import com.trade.project.common.ImageInfo;
import com.trade.project.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class StoreTest {

    private Store store;

    @Mock private Member member;

    @BeforeEach
    void setUp() {
        store = new Store("name1", "", null, member);
    }

    @DisplayName("이름 변경")
    @Test
    void updateName() {
        String updateName = "상점이름1";
        store.updateName(updateName);

        assertThat(store.getName()).isEqualTo(updateName);
    }

    @DisplayName("이름 변경: 예외처리")
    @Test
    void updateNameException() {
        String updateName1 = "";
        String updateName2 = null;

        assertAll(
                () -> assertThatThrownBy(() -> store.updateName(updateName1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no name to change"),

                () -> assertThatThrownBy(() -> store.updateName(updateName2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no name to change")
        );
    }

    @DisplayName("소개 변경")
    @Test
    void updateIntroduction() {
        String updateIntroduction = "변경된 소개글입니다.";
        store.updateIntroduction(updateIntroduction);

        assertThat(store.getIntroduction()).isEqualTo(updateIntroduction);
    }

    @DisplayName("소개 변경: 예외처리")
    @Test
    void updateIntroductionException() {
        String updateIntroduction = null;

        assertThatThrownBy(() -> store.updateIntroduction(updateIntroduction))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no introduction to change");
    }

    @DisplayName("이미지 정보 변경")
    @Test
    void updateImageInfo() {
        ImageInfo imageInfo = new ImageInfo("url", "name");

        store.updateImageInfo(imageInfo);

        assertThat(store.getImageInfo()).isEqualTo(imageInfo);
    }
}