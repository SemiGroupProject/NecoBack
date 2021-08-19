package com.trade.project.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageInfo {

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String fileName;

    public ImageInfo(@NonNull String url, @NonNull String fileName) {
        this.url = url;
        this.fileName = fileName;
    }
}
