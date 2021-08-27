package com.trade.project.item.service;

import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.ItemRepository;
import com.trade.project.item.dto.ItemRequest;
import com.trade.project.member.domain.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public Long create(ItemRequest itemRequest, Member member) {
        Item item = member.createItem(itemRequest);
        item.createImages(itemRequest);
        itemRepository.save(item);

        log.debug("item Id : " + item.getId());
        return item.getId();
    }

}
