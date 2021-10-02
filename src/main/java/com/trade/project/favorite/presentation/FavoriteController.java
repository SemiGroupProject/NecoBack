package com.trade.project.favorite.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.favorite.application.FavoriteService;
import com.trade.project.member.domain.Member;
import com.trade.project.security.filter.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/favorite/{itemId}")
    public ResponseEntity<NecoResponse<Long>> createFavorite(@PathVariable("itemId") Long id, @LoginMember Member member) {


        return ResponseEntity.created(URI.create("/api/favorite"))
                .body(ApiUtils.successResponse(favoriteService.createFavorite(member, id)));
    }
}
