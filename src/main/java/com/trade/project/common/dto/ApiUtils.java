package com.trade.project.common.dto;

import com.trade.project.common.exceptions.ErrorCode;

public class ApiUtils {

    public static <T> NecoResponse<T> successResponse(T response) {
        return new NecoResponse<>(true, response, null);
    }

    public static NecoResponse<ErrorResponse> errorResponse (ErrorCode errorCode){
        return new NecoResponse<>(false, null, ErrorResponse.of(errorCode));
    }

    public static NecoResponse<ErrorResponse> errorResponse (ErrorResponse errorResponse){
        return new NecoResponse<>(false, null, errorResponse);
    }
}
