package com.trade.project.item.domain;

import com.trade.project.common.vo.Images;
import com.trade.project.item.dto.ItemRequest;
import com.trade.project.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    private long price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private Images imageInfos;

    private String tradeArea;

    @Enumerated(EnumType.STRING)
    private ShippingPrice shippingPrice;

    private long hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Item(String title, String content, long price, Category category,
                Images imageInfos, String tradeArea, ShippingPrice shippingPrice,
                Member member, long hits) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category=category;
        this.imageInfos = imageInfos;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
        this.member=member;
        this.hits = hits;
    }

    // 아이템 생성
    public static Item createItem (ItemRequest req, Member member) {
        return Item.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .price(req.getPrice())
                .category(Category.fromString(req.getCategory()))
                .imageInfos(new Images(req.getImageInfo()))
                .tradeArea(req.getTradeArea())
                .shippingPrice(ShippingPrice.convertShippingPrice(req.getShippingPrice()))
                .member(member)
                .build();
    }

}
