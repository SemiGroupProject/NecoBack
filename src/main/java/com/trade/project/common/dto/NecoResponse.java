package com.trade.project.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NecoResponse <T> {

    //공통 response
    private boolean success;
    private T result;

    public NecoResponse(boolean success, T result) {
        this.success = success;
        this.result = result;
    }
}
