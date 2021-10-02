package com.trade.project.favorite.application;

import com.trade.project.favorite.domain.Favorite;
import com.trade.project.favorite.domain.FavoriteRepository;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.application.ItemServiceImpl;
import com.trade.project.item.domain.ItemRepository;
import com.trade.project.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest {

    @Mock private FavoriteRepository favoriteRepository;
    @Mock private ItemRepository itemRepository;

    private ItemService itemService;
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        favoriteService = new FavoriteServiceImpl(favoriteRepository);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void createFavorite(@Mock Member member) throws Exception {

        Favorite favorite = member.createFavorite();


    }
}