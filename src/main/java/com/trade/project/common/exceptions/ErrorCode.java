package com.trade.project.common.exceptions;


public enum ErrorCode {
    // COMMON ERROR
    INTERNAL_SERVER_ERROR(404, "C_001", "서버가 연결되어있지 않습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "Api는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),
    HANDLE_ACCESS_DENIED(403, "C_006", "권한이 없습니다."),
    MEMBER_NOT_FOUND(400, "C_007", "사용자 정보를 찾을 수 없습니다."),

    // FILE ERROR
    FILE_INVALID_TYPE(400, "F_001", "파일 타입이 잘못됐습니다."), // todo 현재는 프론트단에서만 체크하는 로직으로 백단에서의 체크는  필요 없을까?
    FILE_INPUT_AMOUNT(400, "F_002", "파일 입력 개수를 초과했습니다."),
    FILE_INVALID_PATH(400, "F_003", "잘못된 경로값이 입력됐습니다."),
    FILE_MULTIPART_CHANGE_ERROR(400, "F_004", "MultipartFile -> File로 전환이 실패했습니다."),

    // ITEM ERROR
    CATEGORY_INVALID_VALUE(400,"I_002","카테고리에 적절하지 않은 요청 값이 입력됐습니다."),
    SHIPPING_INVALID_VALUE(400, "I_003","배송여부에 적절하지 않은 요청 값이 입력됐습니다. ");

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