package com.trade.project.member.presentation;

import com.trade.project.common.dto.ApiUtils;
import com.trade.project.common.dto.NecoResponse;
import com.trade.project.member.application.JoinRequest;
import com.trade.project.member.application.LoginRequest;
import com.trade.project.member.application.LoginResponse;
import com.trade.project.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
