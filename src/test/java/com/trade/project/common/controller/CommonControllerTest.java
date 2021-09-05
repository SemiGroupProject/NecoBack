package com.trade.project.common.controller;

import com.trade.project.ProjectApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("Common-Controller-Test")
class CommonController extends ProjectApplicationTests {
    @Test
    @DisplayName("에러를 발생시킨다.")
    void makeError() throws Exception {
        this.mockMvc.perform(get("/api/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andDo(document.document(
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("API 호출 성공 여부"),
                                fieldWithPath("result").type(JsonFieldType.STRING).description("API 응답 결과 리턴 (실패시 null 리턴)").optional(),
                                fieldWithPath("error").type(JsonFieldType.OBJECT).description("에러 리턴 (성공시 null 리턴)"),
                                fieldWithPath("error.message").type(JsonFieldType.STRING).description("에러 메세지").optional(),
                                fieldWithPath("error.errors").type(JsonFieldType.ARRAY).description("바인딩 에러 발생시 추가 에러 정보").optional(),
                                fieldWithPath("error.code").type(JsonFieldType.STRING).description("에러코드").optional()
                        )
                ));

    }
}
