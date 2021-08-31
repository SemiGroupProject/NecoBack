package com.trade.project.item.dto;

import com.trade.project.item.domain.ItemImage;
import com.trade.project.item.domain.ItemImages;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemImageResponse {
    private Long id;
    private String url;
    private String fileName;

    public static List<ItemImageResponse> listOf(ItemImages itemImages) {
        List<ItemImage> images = itemImages.getImages();
        return images.stream()
                .map(ItemImageResponse::of)
                .collect(Collectors.toList());
    }

    private static ItemImageResponse of(ItemImage image) {
        return new ItemImageResponse(image.getId(), image.getUrl(), image.getFileName());
    }
}
