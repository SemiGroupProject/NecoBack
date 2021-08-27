package com.trade.project.item.domain;

import com.trade.project.common.exceptions.InvalidValueException;

import java.util.Arrays;

import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_INVALID_VALUE;

public enum Category {
    CLOTHES("의류",1),
    MEN_CLOTHES("남성의류",2),
    WOMEN_CLOTHES("여성의류",2),
    SHOES("신발",2),

    ELECTRONIC("전자제품",1),
    COMPUTER("컴퓨터",2),
    MOBILE("모바일 제품",2),
    CAMERA("카메라",2),
    HOME_APPLIANCES("가전제품",2),

    ETC("잡화",1),
    BOOK("도서",2),
    TICKET("티켓",2),
    RECORD("음반",2),
    ACC("악세사리",2);

    private final String categoryName;
    private final int level;

    Category(String categoryName, int level) {
        this.categoryName = categoryName;
        this.level=level;
    }

    public static Category fromString(String categoryName) {
        return Arrays.stream(values())
                .filter(v->v.toString().equals(categoryName))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidValueException(CATEGORY_INVALID_VALUE));
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getLevel() {
        return level;
    }
}
