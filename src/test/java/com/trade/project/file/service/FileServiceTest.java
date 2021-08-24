package com.trade.project.file.service;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.common.vo.ImageInfo;
import com.trade.project.file.policy.FilePolicy;
import com.trade.project.file.process.FileProcess;
import com.trade.project.file.process.S3FileProcess;
import com.trade.project.file.s3provider.S3Deleter;
import com.trade.project.file.s3provider.S3Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("file-service-test")
class FileServiceTest extends ProjectApplicationTests {

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private S3Deleter s3Deleter;

    private FileProcess fileProcess;

    private MockMultipartFile[] files;

    @BeforeEach
    void setUp() {
        files = createMockMultipartFiles();
    }

    private MockMultipartFile[] createMockMultipartFiles() {
        MockMultipartFile[] files = new MockMultipartFile[3];
        for (int i = 0; i < 3; i++) {
            StringBuffer fileName = new StringBuffer("test" + i + 1 + ".jpg");

            files[i] = new MockMultipartFile(
                    "content",
                    fileName.toString(),
                    "multipart/mixed",
                    "hello word".getBytes(StandardCharsets.UTF_8));
        }
        return files;
    }


    @Test
    @DisplayName("파일을 1개 업로드한다 - STORE")
    void upload_store()throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo rep = fileProcess.uploadFile(files);

        //then
        assertThat(rep.getFileName()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("파일을 1개 업로드한다 - 광고")
    void upload_ad() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo rep = fileProcess.uploadFile(files);

        //then
        assertThat(rep.getFileName()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("파일을 1개 업로드한다 - 상품")
    void upload_item() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo rep = fileProcess.uploadFile(files);

        //then
        assertThat(rep.getFileName()).contains(files[0].getOriginalFilename());
    }


    @Test
    @DisplayName("delete-store")
    void delete_store() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThatCode(() -> fileProcess.deleteFiles(fileResultInfoDto.getFileName()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete-ad")
    void delete_ad() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThatCode(() -> fileProcess.deleteFiles(fileResultInfoDto.getFileName()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete-item")
    void delete_item() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        ImageInfo fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThatCode(() -> fileProcess.deleteFiles(fileResultInfoDto.getFileName()))
                .doesNotThrowAnyException();
    }
}
