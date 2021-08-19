package com.trade.project.global.file.process;

import com.trade.project.dto.file.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcess {
    FileResponse uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    void deleteFiles(String fileNames) throws IllegalArgumentException;

}
