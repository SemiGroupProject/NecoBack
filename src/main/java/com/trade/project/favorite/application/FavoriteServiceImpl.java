package com.trade.project.favorite.application;

import com.trade.project.favorite.domain.Favorite;
import com.trade.project.favorite.domain.FavoriteRepository;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.domain.Item;
import com.trade.project.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepository favoriteRepository;
    private final ItemService itemService;

    @Transactional
    @Override
    public Long createFavorite(Member member, Long itemId) {
        Item item = itemService.findItemById(itemId);
        return favoriteRepository.save(member.createFavorite(item)).getId();
    }
}
