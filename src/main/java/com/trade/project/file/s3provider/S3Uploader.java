package com.trade.project.file.s3provider;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.trade.project.common.exceptions.ErrorCode;
import com.trade.project.common.exceptions.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * 아마존 S3 cloud에 파일을 업로드하는 로직이 작성된 클래스
 * */
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    // 1. MultipartFile을 전달 받는다
    public String upload(MultipartFile multipartFile, String fileName, String dirName) throws IOException {
        File uploadFile = convert(multipartFile, fileName)
                .orElseThrow(() -> new InvalidValueException(ErrorCode.FILE_MULTIPART_CHANGE_ERROR));

        return upload(uploadFile, dirName);
    }

    // 2. S3에 전달할 수 있도록 MultiPartFile 을 File로 전환한다
    // (S3에 Multipartfile 타입은 전송이 안됨)
    private Optional<File> convert(MultipartFile file, String fileName) throws IOException {
        File convertFile = new File(fileName);

        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    // 3. 전환된 File을 S3에 public 읽기 권한으로 Put
    // -> 외부에서 정적 파일을 읽을 수 있도록 하기 위함
   private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 4. 로컬에 생성된 File 삭제
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {

        } else {

        }
    }

    // 5. 업로드된 파일의 S3 URL 주소를 반환
    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }


}
