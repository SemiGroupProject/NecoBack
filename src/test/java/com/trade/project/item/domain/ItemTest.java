package com.trade.project.item.domain;

import com.trade.project.common.exceptions.InvalidValueException;
import com.trade.project.item.application.ItemRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_INVALID_VALUE;
import static com.trade.project.fixture.ImageFixture.IMAGE_INFO_LIST;
import static com.trade.project.fixture.ItemFixture.ITEM1;
import static com.trade.project.fixture.ItemFixture.ITEM_REQUEST;
import static com.trade.project.fixture.MemberFixture.MEMBER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ItemTest {

    @Test
    @DisplayName("Request 값으로 아이템을 만든다.")
    void createItem() {
        Item item = Item.createItem(ITEM_REQUEST,MEMBER_ID);

        assertAll(
                ()->assertThat(ITEM1.getTitle()).isEqualTo(item.getTitle()),
                ()->assertThat(ITEM1.getCategory()).isEqualTo(item.getCategory())
        );
    }

    @Test
    @DisplayName("정의되지 않은 Category 값을 요청받으면 예외가 발생한다.")
    void categoryException() {
        ItemRequest request = ItemRequest.builder()
                .category("ERROR")
                .build();

        assertThatThrownBy(() -> Item.createItem(request,MEMBER_ID))
                .isInstanceOf(InvalidValueException.class)
                .hasMessage(CATEGORY_INVALID_VALUE.getMessage());
    }

    @Test
    @DisplayName("아이템 이미지를 생성한다.")
    void createImages() {
        ITEM1.createImages(ITEM_REQUEST);

        assertAll(
                () -> assertThat(IMAGE_INFO_LIST.get(0).getUrl()).isEqualTo(ITEM1.getItemImages().getImages().get(0).getUrl()),
                () -> assertThat(IMAGE_INFO_LIST.get(1).getUrl()).isEqualTo(ITEM1.getItemImages().getImages().get(1).getUrl())
        );
    }
}
