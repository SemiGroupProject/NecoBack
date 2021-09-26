package com.trade.project.item.application;

import com.trade.project.item.domain.ItemImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ItemRequest {

    private String title;
    private String content;
    private long price;
    private String category;
    private List<ItemImage> itemImages;
    private String tradeArea;
    private String shippingPrice;

    @Builder
    public ItemRequest(String title, String content, long price, String category, List<ItemImage> itemImages, String tradeArea, String shippingPrice) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.itemImages = itemImages;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
    }
}
