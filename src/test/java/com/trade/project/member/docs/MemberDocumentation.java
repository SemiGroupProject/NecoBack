package com.trade.project.member.docs;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class MemberDocumentation {

    public static final FieldDescriptor[] MEMBER_POST_JOIN_REQ = {
            fieldWithPath("accountId").type(JsonFieldType.STRING).description("회원 아이디"),
            fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("회원 성함"),
            fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 전화번호"),
            fieldWithPath("addressInfo").type(JsonFieldType.OBJECT).description("회원 주소정보")
    };

    public static final FieldDescriptor[] MEMBER_GET_DUPLICATE_RES = {
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공, 실패 여부"),
            fieldWithPath("result").type(JsonFieldType.BOOLEAN).description("결과: 결과정보 반환"),
            fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러").optional()
    };

    public static final FieldDescriptor[] MEMBER_PUT_PROFILE_REQ = {
            fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 전화번호"),
            fieldWithPath("addressInfo").type(JsonFieldType.OBJECT).description("회원 주소정보")
    };

    public static final FieldDescriptor[] MEMBER_GET_PROFILE_RES = {
            fieldWithPath("result.accountId").type(JsonFieldType.STRING).description("회원 아이디"),
            fieldWithPath("result.name").type(JsonFieldType.STRING).description("회원 이름"),
            fieldWithPath("result.phoneNumber").type(JsonFieldType.STRING).description("회원 전화번호"),
            fieldWithPath("result.addressInfo").type(JsonFieldType.OBJECT).description("회원 주소정보"),
    };
}
