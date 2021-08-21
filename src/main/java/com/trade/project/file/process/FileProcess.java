package com.trade.project.file.process;

import com.trade.project.common.vo.ImageInfo;
import com.trade.project.dto.file.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcess {
    ImageInfo uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    void deleteFiles(String fileNames) throws IllegalArgumentException;

}
