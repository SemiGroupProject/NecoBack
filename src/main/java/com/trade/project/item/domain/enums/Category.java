package com.trade.project.item.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trade.project.common.exceptions.InvalidValueException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_INVALID_VALUE;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {
    CLOTHES("CLOTHES","의류",1,0),
    MEN_CLOTHES("MEN_CLOTHES","남성의류",2,1),
    WOMEN_CLOTHES("WOMEN_CLOTHES","여성의류",2,1),
    SHOES("SHOES","신발",2,1),

    ELECTRONIC("ELECTRONIC","전자제품",1,0),
    COMPUTER("COMPUTER","컴퓨터",2,5),
    MOBILE("MOBILE","모바일 제품",2,5),
    CAMERA("CAMERA","카메라",2,5),
    HOME_APPLIANCES("HOME_APPLIANCES","가전제품",2,5),

    ETC("ETC","잡화",1,0),
    BOOK("BOOK","도서",2,10),
    TICKET("TICKET","티켓",2,10),
    RECORD("RECORD","음반",2,10),
    ACC("ACC","악세사리",2,10);

    private final String id;
    private final String categoryName;
    private final int level;
    private final int parent;

    Category(String id, String categoryName, int level,int parent) {
        this.id=id;
        this.categoryName = categoryName;
        this.level=level;
        this.parent=parent;
    }

    public static Category fromString(String categoryName) {
        return Arrays.stream(values())
                .filter(v->v.toString().equals(categoryName))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidValueException(CATEGORY_INVALID_VALUE));
    }

    public String getId() {return id;}

    public String getCategoryName() {
        return categoryName;
    }

    public int getLevel() {
        return level;
    }

    public int getParent() {return parent;}

}
