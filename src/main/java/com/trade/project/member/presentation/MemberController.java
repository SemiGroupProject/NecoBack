package com.trade.project.member.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.member.application.*;
import com.trade.project.member.domain.Member;
import com.trade.project.security.filter.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<NecoResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = memberService.signUp(request);
        return ResponseEntity.ok(ApiUtils.successResponse(loginResponse));
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest request) {
        memberService.join(request);

        return ResponseEntity.ok().build();
    }

    // 회원정보변경
    @PutMapping("/members")
    public ResponseEntity<Void> updateMember(@LoginMember Member member, @RequestBody ProfileRequest request) {
        memberService.updateProfile(member, request);

        return ResponseEntity.ok().build();
    }

    // 회원이 존재하는지 테스트
    @GetMapping("/members")
    public ResponseEntity<NecoResponse<Boolean>> findAccountId(@RequestParam("accountId") String accountId) {

        return ResponseEntity.ok(ApiUtils
                .successResponse(memberService.isExistAccountId(accountId)));
    }

}
