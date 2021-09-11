package com.trade.project.common.dto;

import com.trade.project.common.exceptions.ErrorCode;

public class ApiUtils {

    public static <T> NecoResponse<T> successResponse(T response) {
        return new NecoResponse<>(true, response);
    }

    public static NecoErrorResponse errorResponse (ErrorCode errorCode){
        return new NecoErrorResponse(false, ErrorResponse.of(errorCode));
    }

    public static NecoErrorResponse errorResponse (ErrorResponse errorResponse){
        return new NecoErrorResponse(false, errorResponse);
    }
}
