package com.trade.project.item.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.item.application.ItemService;
import com.trade.project.item.domain.enums.Category;
import com.trade.project.item.application.ItemRequest;
import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


import static com.trade.project.common.constant.NecoAPI.ITEM;

@RequiredArgsConstructor
@RestController
@RequestMapping(ITEM)
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<NecoResponse<Long>> create(@RequestBody ItemRequest request){
        // 임시 member
        Member member = new Member("user01","1234","name","phNumber",new AddressInfo(123,"1234"));

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
