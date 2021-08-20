package com.trade.project.global.error;


public enum ErrorCode {
    // GLOBAL ERROR
    INTERNAL_SERVER_ERROR(500, "C_001", "서버가 연결되어있지 않습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "Api는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),


    // FILE ERROR
    FILE_INVALID_TYPE(400, "F_001", "파일 타입이 잘못됐습니다."),
    FILE_INPUT_AMOUNT(400, "F_002", "파일 입력 개수를 초과했습니다."),
    FILE_INVALID_PATH(400, "F_003", "잘못된 경로값이 입력됐습니다."),
    FILE_MULTIPART_CHANGE_ERROR(400, "F_004", "MultipartFile -> File로 전환이 실패했습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}