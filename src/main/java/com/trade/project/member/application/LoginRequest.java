package com.trade.project.member.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginRequest {
    private String accountId;
    private String password;
}
