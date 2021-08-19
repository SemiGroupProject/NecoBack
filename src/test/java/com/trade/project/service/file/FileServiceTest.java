package com.trade.project.service.file;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.dto.file.FileResponse;
import com.trade.project.global.file.policy.FilePolicy;
import com.trade.project.global.file.process.FileProcess;
import com.trade.project.global.file.process.S3FileProcess;
import com.trade.project.global.file.s3provider.S3Deleter;
import com.trade.project.global.file.s3provider.S3Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

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
    void getMockMultipartFiles() {
        files = createMockMultipartFiles();
    }

    private MockMultipartFile[] createMockMultipartFiles() {
        MockMultipartFile[] files = new MockMultipartFile[4];
        for (int i = 0; i < 4; i++) {
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
    @DisplayName("upload - store")
    void upload_store()throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResponse rep = fileProcess.uploadFile(files);

        //then
        assertThat(rep.getFileName()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("upload - ad")
    void upload_ad() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResponse rep = fileProcess.uploadFile(files);

        //then
        assertThat(rep.getFileName()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("upload - item")
    void upload_item() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResponse rep = fileProcess.uploadFile(files);

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
        FileResponse fileResultInfoDto = fileProcess.uploadFile(files);

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
        FileResponse fileResultInfoDto = fileProcess.uploadFile(files);

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
        FileResponse fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThatCode(() -> fileProcess.deleteFiles(fileResultInfoDto.getFileName()))
                .doesNotThrowAnyException();
    }
}
