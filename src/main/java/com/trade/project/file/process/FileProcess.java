package com.trade.project.file.process;

import com.trade.project.item.domain.ItemImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcess {
    ItemImage uploadFile(MultipartFile... files) throws IOException;

    void deleteFiles(String fileNames) throws IllegalArgumentException;

}
