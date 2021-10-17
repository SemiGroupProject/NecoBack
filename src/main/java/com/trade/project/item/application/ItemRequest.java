package com.trade.project.item.application;

import com.trade.project.item.domain.ItemImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ItemRequest {

    private String title;
    private String content;
    private long price;
    private Long categoryId;
    private List<ItemImage> itemImages;
    private String tradeArea;
    private String shippingPrice;

    @Builder
    public ItemRequest(String title, String content, long price, Long categoryId, List<ItemImage> itemImages, String tradeArea, String shippingPrice) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.itemImages = itemImages;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
    }
}
