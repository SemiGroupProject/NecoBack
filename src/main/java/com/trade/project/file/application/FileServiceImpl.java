package com.trade.project.file.application;

import com.trade.project.file.policy.FilePolicy;
import com.trade.project.file.process.FileProcess;
import com.trade.project.file.process.FileProcessFactory;
import com.trade.project.file.process.S3FileProcess;
import com.trade.project.file.s3provider.S3Deleter;
import com.trade.project.file.s3provider.S3Uploader;

import com.trade.project.item.domain.ItemImages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final S3Uploader s3Uploader;
    private final S3Deleter s3Deleter;


    public ItemImages uploadFiles(FilePolicy filePolicy, MultipartFile[] files)
            throws IOException, IllegalStateException, IllegalArgumentException {
        FileProcess fileProcess = FileProcessFactory.getFileProcess(filePolicy);

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        return ItemImages.of(fileProcess.uploadFile(files));
    }

    public void deleteFiles(FilePolicy filePolicy, String fileNames) throws IllegalArgumentException {
        FileProcess fileProcess = FileProcessFactory.getFileProcess(filePolicy);

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        fileProcess.deleteFiles(fileNames);
    }

    public boolean isS3FileProcess(FileProcess fileProcess) {
        return fileProcess instanceof S3FileProcess;
    }

    public void setS3UploaderAndS3Deleter(FileProcess fileProcess) {
        ((S3FileProcess) fileProcess).setUploaderAndDeleter(s3Uploader, s3Deleter);
    }

}
