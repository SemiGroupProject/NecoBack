package com.trade.project.fixture;

import com.trade.project.common.vo.ImageInfo;
import com.trade.project.common.vo.Images;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageFixture {
    public static Images IMAGE_LIST = new Images(Arrays.asList(new ImageInfo("url1","url1"),
                                                            new ImageInfo("url2","url2")));

    public static List<ImageInfo> IMAGE_INFO_LIST = Arrays.asList(new ImageInfo("url1","url1"),
                                                                    new ImageInfo("url2","url2"));
}
