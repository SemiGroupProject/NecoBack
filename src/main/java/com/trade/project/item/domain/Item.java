package com.trade.project.item.domain;

import com.trade.project.common.domain.BaseTimeEntity;
import com.trade.project.common.exceptions.InvalidValueException;
import com.trade.project.item.domain.enums.Category;
import com.trade.project.item.domain.enums.ShippingPrice;
import com.trade.project.item.application.ItemRequest;
import com.trade.project.member.domain.Member;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.List;

import static com.trade.project.common.exceptions.ErrorCode.IMAGE_NOT_FOUND;
import static com.trade.project.common.exceptions.ErrorCode.MEMBER_NOT_FOUND;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    private long price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private ItemImages itemImages;

    private String tradeArea;

    @Enumerated(EnumType.STRING)
    private ShippingPrice shippingPrice;

    private long hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Item(String title, String content, long price, Category category,
                ItemImages itemImages, String tradeArea, ShippingPrice shippingPrice,
                Member member, long hits) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category=category;
        this.itemImages = itemImages;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
        this.member=member;
        this.hits = hits;
    }

    // 아이템 생성
    public static Item createItem (ItemRequest req, Member member) {
        if (member.getId() == null) {
            throw new InvalidValueException(MEMBER_NOT_FOUND);
        }

        return Item.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .price(req.getPrice())
                .category(Category.fromString(req.getCategory()))
                .tradeArea(req.getTradeArea())
                .shippingPrice(ShippingPrice.convertShippingPrice(req.getShippingPrice()))
                .member(member)
                .build();
    }

    // 아이템 이미지 생성
    public void createImages(ItemRequest req) {
        List<ItemImage> itemImage = req.getItemImages();

        if (itemImage.isEmpty()) {
            throw new InvalidValueException(IMAGE_NOT_FOUND);
        }

        for (ItemImage info : itemImage) {
            info.updateItem(this);
        }

        itemImages = ItemImages.of(itemImage);
    }

    // 조회수 카운트 올리기
    public void updateHits() {
        this.hits+=1;
    }

    // 아이템 정보 수정
    public void updateItem(ItemRequest req) {
        title = req.getTitle();
        content = req.getContent();
        price = req.getPrice();
        category = Category.fromString(req.getCategory());
        tradeArea = req.getTradeArea();
        shippingPrice = ShippingPrice.convertShippingPrice(req.getShippingPrice());
    }

}
