package com.trade.project.favorite.application;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.favorite.domain.FavoriteRepository;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.application.ItemServiceImpl;
import com.trade.project.item.domain.CategoryRepository;
import com.trade.project.item.domain.ItemImageRepository;
import com.trade.project.item.domain.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest extends ProjectApplicationTests {

    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemImageRepository itemImageRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ItemService itemService;
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        itemService = new ItemServiceImpl(itemRepository, itemImageRepository, categoryRepository);
        favoriteService = new FavoriteServiceImpl(favoriteRepository, itemRepository);
    }
    

}