package com.trade.project.service.file;

import com.trade.project.dto.file.FileRequest;
import com.trade.project.dto.file.FileResponse;
import com.trade.project.global.file.policy.FilePolicy;
import com.trade.project.global.file.process.FileProcess;
import com.trade.project.global.file.process.FileProcessFactory;
import com.trade.project.global.file.process.S3FileProcess;
import com.trade.project.global.file.s3provider.S3Deleter;
import com.trade.project.global.file.s3provider.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Uploader s3Uploader;
    private final S3Deleter s3Deleter;

    /**
     * @todo FileResponse로 보내야할지, FileRespons에서 toVo()메소드를 통해 ImageInfo로 보내야할지,,,,
     * */
    public FileResponse uploadFiles(FileRequest dto)
            throws IOException, IllegalStateException, IllegalArgumentException {
        FileProcess fileProcess = FileProcessFactory.getFileProcess(dto.getFilePolicy());

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        return fileProcess.uploadFile(dto.getFiles());
    }

    public void deleteFiles(FilePolicy filePolicy, String fileNames) throws IllegalArgumentException {
        FileProcess fileProcess = FileProcessFactory.getFileProcess(filePolicy);

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        fileProcess.deleteFiles(fileNames);
    }

    private boolean isS3FileProcess(FileProcess fileProcess) {
        return fileProcess instanceof S3FileProcess;
    }

    private void setS3UploaderAndS3Deleter(FileProcess fileProcess) {
        ((S3FileProcess) fileProcess).setUploaderAndDeleter(s3Uploader, s3Deleter);
    }

}
