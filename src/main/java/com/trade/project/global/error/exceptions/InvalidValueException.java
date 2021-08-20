package com.trade.project.global.error.exceptions;

import com.trade.project.global.error.ErrorCode;

public class InvalidValueException extends BusinessException {
    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
