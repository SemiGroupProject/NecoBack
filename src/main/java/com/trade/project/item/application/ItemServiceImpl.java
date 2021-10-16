package com.trade.project.item.application;

import com.trade.project.common.exceptions.BusinessException;
import com.trade.project.item.domain.Item;
import com.trade.project.item.domain.ItemImageRepository;
import com.trade.project.item.domain.ItemRepository;
import com.trade.project.member.domain.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.project.common.exceptions.ErrorCode.ITEM_NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Slf4j
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    public Long create(ItemRequest itemRequest, Member member) {
        Item item = member.createItem(itemRequest);
        item.createImages(itemRequest);
        itemRepository.save(item);

        log.debug("item Id : {} " ,item.getId());
        return item.getId();
    }

    @Transactional
    @Override
    public ItemResponse show(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        item.updateHits();
        log.debug("item Hits : {} ", item.getHits());
        return ItemResponse.of(item);
    }

    @Transactional
    @Override
    public Long update(ItemRequest itemRequest, Long id, Member loginMember) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        //todo : memberId 체크

        item.updateItem(itemRequest);
        itemImageRepository.deleteAllByItemId(id);
        item.createImages(itemRequest);

        log.debug("item Id : {}", item.getId());

        return item.getId();
    }

    @Transactional
    @Override
    public void delete(Long id, Member loginMember) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        // todo : memberId 체크
        itemRepository.deleteById(id);
    }

}
