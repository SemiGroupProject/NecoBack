package com.trade.project.favorite.application;

import com.trade.project.favorite.domain.FavoriteRepository;
import com.trade.project.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepository favoriteRepository;

    @Override
    public void createFavorite(Member member, Long itemId) {

    }
}
