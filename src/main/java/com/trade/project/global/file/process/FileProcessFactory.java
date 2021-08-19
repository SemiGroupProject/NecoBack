package com.trade.project.global.file.process;

import com.trade.project.global.file.policy.FilePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// local, s3 로직을 결정하는 클래스
@Component
public class FileProcessFactory {
    private static String fileLocation;

    public static FileProcess getFileProcess(FilePolicy filePolicy) throws IllegalArgumentException {
        if ("s3".equals(fileLocation)) {
            return new S3FileProcess(filePolicy);
        }
        throw new IllegalArgumentException("filetype error");
    }

    @Value("${file.location}")
    private void setFileType(String type) {
        fileLocation = type;
    }

}
