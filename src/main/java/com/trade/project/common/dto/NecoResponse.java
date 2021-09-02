package com.trade.project.common.dto;

import lombok.AccessLevel;
<<<<<<< HEAD
import lombok.Builder;
=======
>>>>>>> ce7cb6ad713304b47209be09cb15ad38f6d06fb9
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NecoResponse <T> {

<<<<<<< HEAD
    // 공통 response
=======
    //공통 response
>>>>>>> ce7cb6ad713304b47209be09cb15ad38f6d06fb9
    private boolean success;
    private T result;
    private ErrorResponse error;

<<<<<<< HEAD
    @Builder
    public NecoResponse(boolean success, T result,ErrorResponse error) {
        this.success = success;
        this.result = result;
        this.error= error;
=======
    public NecoResponse(boolean success, T result, ErrorResponse error) {
        this.success = success;
        this.result = result;
        this.error = error;
>>>>>>> ce7cb6ad713304b47209be09cb15ad38f6d06fb9
    }
}
