package com.trade.project.item.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;

import com.trade.project.item.application.ItemResponse;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.domain.enums.Category;
import com.trade.project.item.application.ItemRequest;

import com.trade.project.item.query.ItemDao;
import com.trade.project.member.domain.Member;
import com.trade.project.security.filter.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


import static com.trade.project.common.constant.NecoAPI.CATEGORY;
import static com.trade.project.common.constant.NecoAPI.ITEM;

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
    public ResponseEntity<NecoResponse<Category[]>> findCategory(){
        return ResponseEntity
                .ok(ApiUtils.successResponse(Category.values()));
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


}
