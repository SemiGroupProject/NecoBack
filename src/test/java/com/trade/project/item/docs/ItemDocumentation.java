package com.trade.project.item.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class ItemDocumentation {
    public static final FieldDescriptor[] ITEM_POST_REQ ={
                fieldWithPath("title").type(JsonFieldType.STRING).description("상품 제목"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("상품 설명"),
                fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격"),
                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                fieldWithPath("itemImages.[]").type(JsonFieldType.ARRAY).description("첨부된 사진 파일 목록"),
                fieldWithPath("itemImages[].url").type(JsonFieldType.STRING).description("첨부된 사진 파일 url"),
                fieldWithPath("itemImages[].fileName").type(JsonFieldType.STRING).description("첨부된 사진 파일 제목"),
                fieldWithPath("tradeArea").type(JsonFieldType.STRING).description("거래 위치"),
                fieldWithPath("shippingPrice").type(JsonFieldType.STRING).description("배송비 여부")
    };

    public static final FieldDescriptor[] ITEM_POST_RES ={
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공, 실패 여부"),
            fieldWithPath("result").type(JsonFieldType.NUMBER).description("결과값 : 등록된 상품 번호"),
            fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러").optional(),
    };

}