package com.trade.project.fixture;


import com.trade.project.item.domain.Category;
import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.enums.ShippingPrice;
import com.trade.project.item.application.ItemRequest;

public class ItemFixture {

    public static Item ITEM1 = Item.builder()
            .title("item 01")
            .price(11000)
            .category(new Category(1L,"의류",1,0))
            .content("아이템 01")
            .tradeArea("서울시 관악구")
            .shippingPrice(ShippingPrice.YES)
            .build();


    public static ItemRequest ITEM_REQUEST =  ItemRequest.builder()
            .title("item 01")
            .price(11000)
            .categoryId(1L)
            .itemImages(ImageFixture.IMAGE_INFO_LIST)
            .content("아이템 01")
            .tradeArea("서울시 관악구")
            .shippingPrice("yes")
            .build();

    public static String ITEM_REQUEST_JSON = "{\n" +
            "    \"title\":\"item01\",\n" +
            "    \"content\":\"content\",\n" +
            "    \"price\":1000,\n" +
            "    \"categoryId\":1,\n" +
            "    \"itemImages\":\n" +
            "  \t[\n" +
            "      {\n" +
            "        \"url\":\"url\",\n" +
            "        \"fileName\":\"fileName\"\n" +
            "    \t}\n" +
            "      ,{\n" +
            "        \"url\":\"url2\",\n" +
            "        \"fileName\":\"fileName2\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"tradeArea\":\"tradeArea\",\n" +
            "    \"shippingPrice\":\"no\"\n" +
            "}";

}
