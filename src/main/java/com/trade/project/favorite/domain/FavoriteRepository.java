package com.trade.project.favorite.domain;

import com.trade.project.item.domain.Item;
import com.trade.project.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findFavoriteByMemberAndItem(Member member, Item item);

    @Query("select count(f.id) from Favorite f where f.item =: item and f.member =: member")
    long findCountFavoritesByItemId(@Param("member") Member member, @Param("item") Item item);
}
