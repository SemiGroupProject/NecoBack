package com.trade.project.favorite.application;

import com.trade.project.favorite.domain.Favorite;
import com.trade.project.favorite.domain.FavoriteRepository;
import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.ItemRepository;
import com.trade.project.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public FavoriteResponse createFavorite(Member member, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return new FavoriteResponse(favoriteRepository.save(member.createFavorite(item)).getId());
    }

    @Transactional
    @Override
    public void deleteFavorite(Member member, Long itemId) {
        Optional<Favorite> favoriteOptional = favoriteRepository
                .findFavoriteByMemberAndItem(member, itemRepository.findById(itemId).orElseThrow());

        favoriteRepository.delete(favoriteOptional.orElseThrow());
    }

    @Transactional(readOnly = true)
    @Override
    public FavoriteCountResponse countFavorites(Member member, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return new FavoriteCountResponse(favoriteRepository.findCountFavoritesByItemId(member, item));
    }
}
