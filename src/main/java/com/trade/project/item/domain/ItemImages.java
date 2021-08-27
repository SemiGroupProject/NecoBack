package com.trade.project.item.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ItemImages {

    @OneToMany(
            mappedBy = "item",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<ItemImage> images = new ArrayList<>();

    public ItemImages(List<ItemImage> images) {
        this.images = images;
    }

    public List<ItemImage> getImages() {
        return images;
    }

    public List<String> getUrls() {
        return images.stream()
                .map(ItemImage::getUrl)
                .collect(toUnmodifiableList());
    }

    public static ItemImages of(ItemImage... itemImages) {
        return new ItemImages(Arrays.asList(itemImages));
    }

}
