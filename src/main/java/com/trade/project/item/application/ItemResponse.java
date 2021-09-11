package com.trade.project.item.application;

import com.querydsl.core.annotations.QueryProjection;
import com.trade.project.item.domain.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemResponse {
    private long id;
    private String title;
    private String content;
    private long price;
    private String category;
    private List<ItemImageResponse> itemImages;
    private String tradeArea;
    private String shippingPrice;
    private long hits;
    private long memberId;

    public static ItemResponse of(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getPrice(),
                item.getCategory().getCategoryName(),
                ItemImageResponse.listOf(item.getItemImages()),
                item.getTradeArea(),
                null,
                item.getHits(),
                item.getMember().getId()
        );
    }

    @QueryProjection
    public ItemResponse(Item item) {
        this(item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getPrice(),
                item.getCategory().getCategoryName(),
                ItemImageResponse.listOf(item.getItemImages()),
                item.getTradeArea(),
                item.getShippingPrice().name(),
                item.getHits(),
                item.getMember().getId());
    }}
