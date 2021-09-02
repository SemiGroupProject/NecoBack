package com.trade.project.fixture;

import com.trade.project.item.domain.ItemImage;
import com.trade.project.item.domain.ItemImages;

import java.util.Arrays;
import java.util.List;

public class ImageFixture {
    public static ItemImages IMAGE_LIST = new ItemImages(Arrays.asList(new ItemImage("url1","url1"),
                                                            new ItemImage("url2","url2")));

    public static List<ItemImage> IMAGE_INFO_LIST = Arrays.asList(new ItemImage("url1","url1"),
                                                                    new ItemImage("url2","url2"));
}
