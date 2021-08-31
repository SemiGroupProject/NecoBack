package com.trade.project.item.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trade.project.common.exceptions.InvalidValueException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_INVALID_VALUE;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {
    CLOTHES(1,"의류",1,0),
    MEN_CLOTHES(2,"남성의류",2,1),
    WOMEN_CLOTHES(3,"여성의류",2,1),
    SHOES(4,"신발",2,1),

    ELECTRONIC(5,"전자제품",1,0),
    COMPUTER(6,"컴퓨터",2,5),
    MOBILE(7,"모바일 제품",2,5),
    CAMERA(8,"카메라",2,5),
    HOME_APPLIANCES(9,"가전제품",2,5),

    ETC(10,"잡화",1,0),
    BOOK(11,"도서",2,10),
    TICKET(12,"티켓",2,10),
    RECORD(13,"음반",2,10),
    ACC(14,"악세사리",2,10);

    private final int id;
    private final String categoryName;
    private final int level;
    private final int parent;

    Category(int id, String categoryName, int level,int parent) {
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

    public int getId() {return id;}

    public String getCategoryName() {
        return categoryName;
    }

    public int getLevel() {
        return level;
    }

    public int getParent() {return parent;}

}
