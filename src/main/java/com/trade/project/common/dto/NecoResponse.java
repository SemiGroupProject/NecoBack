package com.trade.project.common.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NecoResponse <T> {

    // 공통 response
    private boolean success;
    private T result;
    private ErrorResponse error;

    @Builder
    public NecoResponse(boolean success, T result,ErrorResponse error) {
        this.success = success;
        this.result = result;
        this.error= error;
    }
}
