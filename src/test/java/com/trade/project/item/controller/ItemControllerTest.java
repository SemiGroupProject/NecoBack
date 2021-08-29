package com.trade.project.item.controller;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.common.constant.NecoAPI;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;

import static com.trade.project.fixture.ItemFixture.ITEM_REQUEST_JSON;
import static com.trade.project.item.docs.ItemDocumentation.ITEM_POST_REQ;
import static com.trade.project.item.docs.ItemDocumentation.ITEM_POST_RES;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Item-Controller-Test")
class ItemControllerTest extends ProjectApplicationTests {

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

}
