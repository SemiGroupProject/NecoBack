package com.trade.project.file.process;

import com.trade.project.common.error.exceptions.ErrorCode;
import com.trade.project.common.error.exceptions.InvalidValueException;
import com.trade.project.file.policy.FilePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// local, s3 로직을 결정하는 클래스
@Component
public class FileProcessFactory {
    private static String fileLocation;

    public static FileProcess getFileProcess(FilePolicy filePolicy) throws IllegalArgumentException {
        // 2021.08.21 현재 정책은 s3에만 저장하기로 (로컬에 저장 x)
        if ("s3".equals(fileLocation)) {
            return new S3FileProcess(filePolicy);
        }
        throw new InvalidValueException(ErrorCode.FILE_INVALID_TYPE);
    }

    @Value("${file.location}")
    private void setFileType(String type) {
        fileLocation = type;
    }

}
