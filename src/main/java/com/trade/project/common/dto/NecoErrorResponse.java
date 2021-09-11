package com.trade.project.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NecoErrorResponse {
    private boolean success;
    private ErrorResponse error;

    public NecoErrorResponse(boolean success, ErrorResponse errorResponse) {
        this.success = success;
        this.error = errorResponse;
    }
}
