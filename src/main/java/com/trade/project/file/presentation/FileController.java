package com.trade.project.file.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.file.application.FileService;
import com.trade.project.file.policy.FilePolicy;
import com.trade.project.item.application.ItemImageResponse;
import com.trade.project.item.domain.ItemImages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class FileController {
    private final FileService fileService;

    @PostMapping("/images")
    public NecoResponse<List<ItemImageResponse>>  upload (@RequestParam("images") MultipartFile[] multipartFile) throws IOException {

        ItemImages itemImage = fileService.uploadFiles(FilePolicy.ITEM, multipartFile);
        List<ItemImageResponse> res = ItemImageResponse.listOf(itemImage);

        return  ApiUtils.successResponse(res);
    }
}
