package com.trade.project.dto.file;

import com.trade.project.domain.vo.ImageInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileResponse {
    private String url;

    private String fileName;

    @Builder
    public FileResponse(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    // dto를 vo로 컨버팅
    public ImageInfo toVo(){
        return ImageInfo.builder()
                .url(url)
                .fileName(fileName)
                .build();
    }
}
