package com.trade.project.item.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.domain.enums.Category;
import com.trade.project.item.application.ItemRequest;
import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;
import com.trade.project.security.filter.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


import static com.trade.project.common.constant.NecoAPI.ITEM;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping(ITEM)
    public ResponseEntity<NecoResponse<Long>> create(@LoginMember Member member, @RequestBody ItemRequest request){
        Long id = itemService.create(request, member);

        return ResponseEntity
        .created(URI.create(ITEM+"/"+id))
        .body(ApiUtils.successResponse(id));
    }

    @GetMapping("/category")
    public ResponseEntity<NecoResponse<Category[]>> findCategory(){
        return ResponseEntity
                .ok(ApiUtils.successResponse(Category.values()));
    }

}
