package com.trade.project.item.dto;

import com.trade.project.common.vo.ImageInfo;
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
    private List<ImageInfo> imageInfo;
    private String tradeArea;
    private String shippingPrice;

    @Builder
    public ItemRequest(String title, String content, long price, String category, List<ImageInfo> imageInfo, String tradeArea, String shippingPrice) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.imageInfo = imageInfo;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
    }

}
