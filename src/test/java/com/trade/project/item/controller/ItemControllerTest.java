package com.trade.project.item.controller;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.common.constant.NecoAPI;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;

import static com.trade.project.fixture.ItemFixture.ITEM_REQUEST_JSON;
import static com.trade.project.item.docs.ItemDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Item-Controller-Test")
class ItemControllerTest extends ProjectApplicationTests {

    // todo : security 설정!!헤더에 토큰넣기
    @Test
    @DisplayName("상품을 생성한다.")
    void createItem() throws Exception {

        this.mockMvc.perform(post(NecoAPI.ITEM)
                .content(ITEM_REQUEST_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document.document(
                        customRequestFields(ITEM_POST_REQ),
                        responseHeaders(
                                headerWithName("Location").description("생성된 아이템 id")
                        ),
                        customResponseFields(ITEM_POST_RES)
                ));

    }

    @Test
    @DisplayName("카테고리 목록을 가져온다")
    void findCategory() throws Exception {
        this.mockMvc.perform(get("/api/category")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customResponseFields(CATEGORY_GET_RES)
                )
        );
    }

}
