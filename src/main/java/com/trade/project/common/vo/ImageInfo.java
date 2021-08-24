package com.trade.project.common.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageInfo {

    @Column(length = 1023)
    private String url;

    @Column(length = 1023)
    private String fileName;

    public ImageInfo(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

}
