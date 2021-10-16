package com.trade.project.file.application;

import com.trade.project.item.domain.ItemImage;
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


public interface FileService {


    ItemImages uploadFiles(FilePolicy filePolicy, MultipartFile[] files) throws IOException;

    void deleteFiles(FilePolicy filePolicy, String fileNames) throws IllegalArgumentException;

    boolean isS3FileProcess(FileProcess fileProcess);

    void setS3UploaderAndS3Deleter(FileProcess fileProcess);
}
