package com.trade.project.common.vo;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.List;

@Embeddable
public class Images {
    private List<ImageInfo> images;

    public Images(){}

    public Images(List<ImageInfo> images) {
        this.images = images;
    }

    public static Images of(ImageInfo... imageInfos) {
        return new Images(Arrays.asList(imageInfos));
    }

}
