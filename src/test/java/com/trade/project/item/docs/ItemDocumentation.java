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
            fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 아이디"),
            fieldWithPath("itemImages.[]").type(JsonFieldType.ARRAY).description("첨부된 사진 파일 목록"),
            fieldWithPath("itemImages[].url").type(JsonFieldType.STRING).description("첨부된 사진 파일 url"),
            fieldWithPath("itemImages[].fileName").type(JsonFieldType.STRING).description("첨부된 사진 파일 제목"),
            fieldWithPath("tradeArea").type(JsonFieldType.STRING).description("거래 위치"),
            fieldWithPath("shippingPrice").type(JsonFieldType.STRING).description("배송비 여부 (yes, no)")
    };

    public static final FieldDescriptor[] ITEM_POST_RES ={
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 호출 성공 여부"),
            fieldWithPath("result").type(JsonFieldType.NUMBER).description("결과값 : 등록된 상품 번호"),
    };

    public static final FieldDescriptor[] ITEM_LIST_GET_RES = {
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 호출 성공 여부"),
            fieldWithPath("result.[]").type(JsonFieldType.ARRAY).description("결과값 : 상품 정보 리스트"),
            fieldWithPath("result[].id").type(JsonFieldType.NUMBER).description("상품 아이디"),
            fieldWithPath("result[].title").type(JsonFieldType.STRING).description("상품 제목"),
            fieldWithPath("result[].content").type(JsonFieldType.STRING).description("상품 설명"),
            fieldWithPath("result[].price").type(JsonFieldType.NUMBER).description("가격"),
            fieldWithPath("result[].category").type(JsonFieldType.STRING).description("카테고리 제목"),
            fieldWithPath("result[].itemImages.[]").type(JsonFieldType.ARRAY).description("첨부된 사진 파일 목록"),
            fieldWithPath("result[].itemImages[].url").type(JsonFieldType.STRING).description("첨부된 사진 파일 url"),
            fieldWithPath("result[].itemImages[].fileName").type(JsonFieldType.STRING).description("첨부된 사진 파일 제목"),
            fieldWithPath("result[].tradeArea").type(JsonFieldType.STRING).description("거래 위치"),
            fieldWithPath("result[].shippingPrice").type(JsonFieldType.STRING).description("배송비 여부 (yes, no)"),
            fieldWithPath("result[].hits").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("result[].memberId").type(JsonFieldType.NUMBER).description("상품을 등록한 사용자 아이디"),
    };

    public static final FieldDescriptor[] ITEM_GET_RES = {
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 호출 성공 여부"),
            fieldWithPath("result").type(JsonFieldType.OBJECT).description("결과값 : 상품 정보 리스트"),
            fieldWithPath("result.id").type(JsonFieldType.NUMBER).description("상품 아이디"),
            fieldWithPath("result.title").type(JsonFieldType.STRING).description("상품 제목"),
            fieldWithPath("result.content").type(JsonFieldType.STRING).description("상품 설명"),
            fieldWithPath("result.price").type(JsonFieldType.NUMBER).description("가격"),
            fieldWithPath("result.category").type(JsonFieldType.STRING).description("카테고리 제목"),
            fieldWithPath("result.itemImages.[]").type(JsonFieldType.ARRAY).description("첨부된 사진 파일 목록"),
            fieldWithPath("result.itemImages[].url").type(JsonFieldType.STRING).description("첨부된 사진 파일 url"),
            fieldWithPath("result.itemImages[].fileName").type(JsonFieldType.STRING).description("첨부된 사진 파일 제목"),
            fieldWithPath("result.tradeArea").type(JsonFieldType.STRING).description("거래 위치"),
            fieldWithPath("result.shippingPrice").type(JsonFieldType.STRING).description("배송비 여부 (yes, no)"),
            fieldWithPath("result.hits").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("result.memberId").type(JsonFieldType.NUMBER).description("상품을 등록한 사용자 아이디"),
    };


    public static final FieldDescriptor[] CATEGORY_GET_RES = {
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 호출 성공 여부"),
            fieldWithPath("result.[]").type(JsonFieldType.ARRAY).description("결과값 : 카테고리 정보"),
            fieldWithPath("result[].id").type(JsonFieldType.NUMBER).description("카테고리 아이디"),
            fieldWithPath("result[].categoryName").type(JsonFieldType.STRING).description("카테고리 제목"),
            fieldWithPath("result[].level").type(JsonFieldType.NUMBER).description("카테고리 레벨 (계층)"),
            fieldWithPath("result[].parent").type(JsonFieldType.NUMBER).description("참조 부모 id (최상위시 0)"),
    };

    public static final FieldDescriptor[] ITEM_PUT_REQ ={
            fieldWithPath("title").type(JsonFieldType.STRING).description("상품 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("상품 설명"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격"),
            fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 아이디"),
            fieldWithPath("itemImages.[]").type(JsonFieldType.ARRAY).description("첨부된 사진 파일 목록"),
            fieldWithPath("itemImages[].url").type(JsonFieldType.STRING).description("첨부된 사진 파일 url"),
            fieldWithPath("itemImages[].fileName").type(JsonFieldType.STRING).description("첨부된 사진 파일 제목"),
            fieldWithPath("tradeArea").type(JsonFieldType.STRING).description("거래 위치"),
            fieldWithPath("shippingPrice").type(JsonFieldType.STRING).description("배송비 여부 (yes, no)")
    };

}
