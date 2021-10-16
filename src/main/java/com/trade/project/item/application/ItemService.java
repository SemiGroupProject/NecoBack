package com.trade.project.item.application;

import com.trade.project.member.domain.Member;

import java.util.List;

public interface ItemService {
    Long create(ItemRequest itemRequest, Member member);

    ItemResponse show(Long id);

    Long update(ItemRequest request, Long id, Member loginMember);

    void delete(Long id, Member loginMember);

    List<CategoryResponse> showCategory();
}
