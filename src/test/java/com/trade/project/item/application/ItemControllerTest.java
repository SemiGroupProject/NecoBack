package com.trade.project.item.application;

import com.trade.project.ProjectApplicationTests;
import com.trade.project.common.constant.NecoAPI;


import com.trade.project.common.security.WithMockCustomUser;
import com.trade.project.security.provider.JwtTokenProvider;
import com.trade.project.security.service.AccountContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.trade.project.fixture.ItemFixture.ITEM_REQUEST_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON_2;
import static com.trade.project.item.docs.ItemDocumentation.*;
import static com.trade.project.member.docs.MemberDocumentation.MEMBER_GET_DUPLICATE_RES;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        token += jwtTokenProvider.createToken("user01",roles);
    }

    @Test
    @Order(1)
    @DisplayName("????????? ????????????.")
    void createItem() throws Exception {
        this.mockMvc.perform(post("/api/"+NecoAPI.ITEM)
                .header("Authorization", token)
                .content(ITEM_REQUEST_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document.document(
                        customRequestFields(ITEM_POST_REQ),
                        responseHeaders(
                                headerWithName("Location").description("????????? ????????? id")
                        ),
                        customResponseFields(ITEM_POST_RES)
                ));

    }

    @Test
    @DisplayName("???????????? ????????? ????????????")
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
    @DisplayName("?????? ???????????? ???????????? ?????? ?????? 20?????? ????????? ????????????.")
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

    @Test
    @DisplayName("????????? ??????????????? ????????????.")
    void show() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/"+NecoAPI.ITEM+"/{id}","1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("id").description("??????????????? ????????? ????????? ?????????")
                        ),customResponseFields(ITEM_GET_RES))
                );
    }

    @Test
    @DisplayName("??????????????? ?????? ???????????? ????????????.")
    void showPageByCategory() throws Exception {
        mockMvc.perform(get("/api/"+NecoAPI.ITEM).param("page", "1")
                .param("size","20")
                .param("categoryId","1")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        requestParameters(
                                parameterWithName("page").description("????????? ??????"),
                                parameterWithName("size").description("??? ???????????? ????????? ????????? ???"),
                                parameterWithName("categoryId").description("???????????? ?????????")
                                //),
                                //customResponseFields(MEMBER_GET_DUPLICATE_RES))
                        )
                ));
    }

    @Test
    @DisplayName("?????????/??????????????? ???????????? ?????? ???????????? ????????????.")
    void showPageByKeyword() throws Exception {
        mockMvc.perform(get("/api/"+NecoAPI.ITEM).param("page", "1")
                .param("size","20")
                .param("keyword","item01")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        requestParameters(
                                parameterWithName("page").description("????????? ??????"),
                                parameterWithName("size").description("??? ???????????? ????????? ????????? ???"),
                                parameterWithName("keyword").description("?????????")
                                //),
                                //customResponseFields(MEMBER_GET_DUPLICATE_RES))
                        ))
                );
    }

    @Test
    @DisplayName("??????????????? ????????????.")
    void putItem() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/api/"+NecoAPI.ITEM+"/{id}","1")
                .header("Authorization", token)
                .content(ITEM_REQUEST_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("id").description("????????? ????????? ????????? ?????????")
                        ),
                        customRequestFields(ITEM_PUT_REQ)
                ));

    }

    @Test
    @DisplayName("??????????????? ????????????.")
    @Transactional
    void deleteItem() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/"+NecoAPI.ITEM+"/{id}","2")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("id").description("????????? ????????? ????????? ?????????")
                        )
                ));

    }
}
