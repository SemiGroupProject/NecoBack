package com.trade.project.item.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;

import com.trade.project.item.application.*;

import com.trade.project.item.query.ItemDao;
import com.trade.project.member.domain.Member;
import com.trade.project.security.filter.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


import static com.trade.project.common.constant.NecoAPI.CATEGORY;
import static com.trade.project.common.constant.NecoAPI.ITEM;
import static com.trade.project.common.exceptions.ErrorCode.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemDao itemDao;

    // [POST] 상품 등록
    @PostMapping(ITEM)
    public ResponseEntity<NecoResponse<Long>> create(@LoginMember Member member, @RequestBody ItemRequest request){
        Long id = itemService.create(request, member);

        return ResponseEntity
                .created(URI.create(ITEM+"/"+id))
                .body(ApiUtils.successResponse(id));
    }

    // [GET] 카테고리 조회
    @GetMapping(CATEGORY)
    public ResponseEntity<NecoResponse<List<CategoryResponse>>> findCategory(){
        List<CategoryResponse> res = itemService.showCategory();
        return ResponseEntity
                .ok(ApiUtils.successResponse(res));
    }

    // [GET] 상품 조회 - 메인 20개
    @GetMapping(ITEM)
    public ResponseEntity<NecoResponse<List<ItemResponse>>> showMain(){
        List<ItemResponse> res = itemDao.showMain();
        return ResponseEntity
                .ok(ApiUtils.successResponse(res));
    }

    // [GET] 상품 조회 - 상품 상세
    @GetMapping(ITEM+"/{id}")
    public ResponseEntity<NecoResponse<ItemResponse>> show(@PathVariable Long id){
        ItemResponse res = itemService.show(id);
        return ResponseEntity
                .ok(ApiUtils.successResponse(res));
    }

    // [GET] 상품 조회 - 카테고리별 조회
    @GetMapping(value = ITEM, params = {"page", "size", "categoryId"})
    public ResponseEntity<NecoResponse<Page<ItemResponse>>> showPageByCategory (
            PageRequest pageable,
            @RequestParam int categoryId){
        // Page 객체 전부를 넘겨주는데 몇개만 넘겨줘도 될거같음
        Page<ItemResponse> res = itemDao.showPageByCategory(pageable.of(), categoryId);

        return ResponseEntity
                .ok(ApiUtils.successResponse(res));
    }

    // [GET] 상품 조회 - 상품명/지역명 검색
    @GetMapping(value = ITEM, params = {"page", "size", "keyword"})
    public ResponseEntity<NecoResponse<Page<ItemResponse>>> showPageByKeyword (
            PageRequest pageable,
            @RequestParam String keyword){

        Page<ItemResponse> res = itemDao.showPageByKeyword(pageable.of(), keyword);

        return ResponseEntity
                .ok(ApiUtils.successResponse(res));
    }

    // [PUT] 상품 수정
    @PutMapping(ITEM+"/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemRequest request, @PathVariable Long id, @LoginMember Member loginMember){
        itemService.update(request, id, loginMember);
        return ResponseEntity
                .noContent()
                .build();
    }

    // [PUT] 상품 삭제
    @DeleteMapping(ITEM+"/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @LoginMember Member loginMember){
        itemService.delete(id, loginMember);
        return ResponseEntity
                .noContent()
                .build();
    }
}
