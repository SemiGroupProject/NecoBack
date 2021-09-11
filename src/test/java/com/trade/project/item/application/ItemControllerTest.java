package com.trade.project.item.application;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.common.constant.NecoAPI;


import com.trade.project.security.provider.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.trade.project.fixture.ItemFixture.ITEM_REQUEST_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON_2;
import static com.trade.project.item.docs.ItemDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Item-Controller-Test")
class ItemControllerTest extends ProjectApplicationTests {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    String token = "Bearer ";

    @BeforeEach
    @Transactional
    void setUp() throws Exception {

    }

    @Test
    @DisplayName("상품을 생성한다.")
    @Transactional
    void createItem() throws Exception {

        mockMvc.perform(post("/api/join")
                .content(MEMBER_JOIN_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        token += jwtTokenProvider.createToken("junco",roles);

        this.mockMvc.perform(post("/api/"+NecoAPI.ITEM)
                .header("Authorization", token)
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

    @Test
    @DisplayName("메인 페이지에 출력되는 최신 상품 20개의 정보를 가져온다.")
    void showMain() throws Exception {
        this.mockMvc.perform(get("/api/"+NecoAPI.ITEM)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customResponseFields(ITEM_LIST_GET_RES)
                        )
                );
    }

}
