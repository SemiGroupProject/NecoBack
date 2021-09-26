package com.trade.project.common.security;

import com.trade.project.member.domain.Member;
import com.trade.project.security.provider.JwtTokenProvider;
import com.trade.project.security.service.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    JwtTokenProvider jwtTokenProvider;

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(customUser.role()));

        Member member = new Member(customUser.id(), customUser.password());
        AccountContext accountContext = new AccountContext(customUser.userId(), customUser.password(), roles);
        Authentication auth = getAuthentication(accountContext);

        context.setAuthentication(auth);

        return context;
    }

    private Authentication getAuthentication(AccountContext accountContext){
        return new UsernamePasswordAuthenticationToken(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
    }
}