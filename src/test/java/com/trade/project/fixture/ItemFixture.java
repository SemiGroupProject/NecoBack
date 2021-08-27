package com.trade.project.fixture;


import com.trade.project.item.domain.Category;
import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.ShippingPrice;
import com.trade.project.item.dto.ItemRequest;

public class ItemFixture {

    public static Item ITEM1 = Item.builder()
            .title("item 01")
            .price(11000)
            .category(Category.BOOK)
            .content("아이템 01")
            .tradeArea("서울시 관악구")
            .shippingPrice(ShippingPrice.YES)
            .build();


    public static ItemRequest ITEM_REQUEST =  ItemRequest.builder()
            .title("item 01")
            .price(11000)
            .category("BOOK")
            .itemImages(ImageFixture.IMAGE_INFO_LIST)
            .content("아이템 01")
            .tradeArea("서울시 관악구")
            .shippingPrice("yes")
            .build();


}
