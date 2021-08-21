package com.trade.project.common.error.exceptions;

public class InvalidValueException extends BusinessException {
    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
