package com.trade.project.item.domain;


import com.trade.project.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ItemImage extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1023)
    private String url;

    @Column(length = 1023)
    private String fileName;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    public ItemImage(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public void updateItem(Item item){
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemImage itemImage = (ItemImage) o;
        return Objects.equals(url, itemImage.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}

