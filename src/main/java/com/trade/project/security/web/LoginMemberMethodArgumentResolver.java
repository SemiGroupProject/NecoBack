package com.trade.project.security.web;

import com.trade.project.member.domain.IllegalLoginException;
import com.trade.project.member.domain.MemberRepository;
import com.trade.project.security.filter.LoginMember;
import com.trade.project.security.service.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        AccountContext accountContext = (AccountContext) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return memberRepository.findOptionalByAccountId(accountContext.getUsername())
                .orElseThrow(() -> new IllegalLoginException("토큰정보와 일치하는 회원이 존재하지 않습니다."));
    }
}
