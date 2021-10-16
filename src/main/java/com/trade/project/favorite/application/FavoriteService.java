package com.trade.project.favorite.application;

import com.trade.project.member.domain.Member;

public interface FavoriteService {

    FavoriteResponse createFavorite(Member member, Long itemId);

    void deleteFavorite(Member member, Long itemId);

    FavoriteCountResponse countFavorites(Member member, Long itemId);

}
