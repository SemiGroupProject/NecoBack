package com.trade.project.item.application;

import com.trade.project.common.exceptions.BusinessException;
import com.trade.project.item.domain.*;
import com.trade.project.member.domain.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_NOT_FOUND;
import static com.trade.project.common.exceptions.ErrorCode.ITEM_NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Slf4j
@Transactional
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final CategoryRepository categoryRepository;

    public Long create(ItemRequest itemRequest, Member member) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId())
                .orElseThrow(()-> new BusinessException(CATEGORY_NOT_FOUND));

        Item item = member.createItem(itemRequest, category);
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


    @Override
    public Long update(ItemRequest itemRequest, Long id, Member loginMember) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        //todo : memberId 체크

        Category category = categoryRepository.findById(itemRequest.getCategoryId())
                .orElseThrow(()-> new BusinessException(CATEGORY_NOT_FOUND));

        item.updateItem(itemRequest,category);
        itemImageRepository.deleteAllByItemId(id);
        item.createImages(itemRequest);

        log.debug("item Id : {}", item.getId());

        return item.getId();
    }

    @Override
    public void delete(Long id, Member loginMember) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ITEM_NOT_FOUND));

        // todo : memberId 체크
        itemRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> showCategory() {
        List<Category> categorys = categoryRepository.findAll();
        return Collections.unmodifiableList(CategoryResponse.listOf(categorys));
    }

}
