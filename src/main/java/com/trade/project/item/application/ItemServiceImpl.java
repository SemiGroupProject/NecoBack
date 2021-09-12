package com.trade.project.item.application;

import com.trade.project.common.exceptions.BusinessException;
import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.ItemRepository;
import com.trade.project.member.domain.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.trade.project.common.exceptions.ErrorCode.ITEM_NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Slf4j
@Transactional
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    public Long create(ItemRequest itemRequest, Member member) {
        Item item = member.createItem(itemRequest);
        item.createImages(itemRequest);
        itemRepository.save(item);

        log.debug("item Id : {} " ,item.getId());
        return item.getId();
    }

    @Override
    public ItemResponse show(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        item.updateHits();
        log.debug("item Hits : {} ", item.getHits());
        return ItemResponse.of(item);
    }

}
