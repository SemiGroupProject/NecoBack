package com.trade.project.member.application;

import com.trade.project.member.domain.Member;
import com.trade.project.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginInfoValidator {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse validateInfo(Optional<Member> optionalMember, LoginRequest request) {
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            if(validateAccountIdAndPassword(member, request)) {
                return new LoginResponse(
                        jwtTokenProvider.createToken(member.getAccountId(), MemberRoleContext.USER.exportRole())
                );
            }
        }

        throw new IllegalArgumentException("not found Member");
    }

    private boolean validateAccountIdAndPassword(Member member, LoginRequest request) {

        boolean isEqualAccountId = member.getAccountId().equals(request.getAccountId());
        boolean isEqualPassword = passwordEncoder.matches(request.getPassword(), member.getPassword());

        return isEqualAccountId && isEqualPassword;
    }
}
