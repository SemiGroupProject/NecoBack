package com.trade.project.member.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberExistResponse {
    private boolean available;

    public MemberExistResponse(boolean available) {
        this.available = available;
    }
}
