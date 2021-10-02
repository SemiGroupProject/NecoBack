package com.trade.project.item.application;

import com.trade.project.item.domain.Item;
import com.trade.project.member.domain.Member;

public interface ItemService {
    Long create(ItemRequest itemRequest, Member member);

    ItemResponse show(Long id);

    Long update(ItemRequest request, Long id, Member loginMember);

    void delete(Long id, Member loginMember);

    /**
     * 추후 분리할 Read
     */
    Item findItemById(Long id);
}
