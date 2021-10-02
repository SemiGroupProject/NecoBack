package com.trade.project.favorite.application;

import com.trade.project.member.domain.Member;

public interface FavoriteService {

    void createFavorite(Member member, Long itemId);
}
