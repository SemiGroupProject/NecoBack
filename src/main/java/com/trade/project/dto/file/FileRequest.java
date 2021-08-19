package com.trade.project.dto.file;

import com.trade.project.global.file.policy.FilePolicy;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class FileRequest {
    private FilePolicy filePolicy;
    private MultipartFile[] files;

    public FileRequest(FilePolicy filePolicy, MultipartFile[] files) {
        this.filePolicy = filePolicy;
        this.files = files;
    }
}
