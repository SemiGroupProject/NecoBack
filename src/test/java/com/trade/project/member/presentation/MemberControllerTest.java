package com.trade.project.member.presentation;

import com.trade.project.MemberLoginApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_PROFILE_UPDATE_JSON;
import static com.trade.project.member.docs.MemberDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Member-Controller-Test")
class MemberControllerTest extends MemberLoginApplicationTests {

    @DisplayName("회원가입")
    @Test
    void joinMember() throws Exception{
        mockMvc.perform(post("/api/join")
                .content(MEMBER_JOIN_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document.document(
                        customRequestFields(MEMBER_POST_JOIN_REQ))
                );
    }

    @DisplayName("아이디 증복 확인")
    @Test
    void isExistAccountId() throws Exception {
        mockMvc.perform(get("/api/members").param("accountId", "user1")
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customResponseFields(MEMBER_GET_DUPLICATE_RES))
                );

    }

    //TODO: 로그인 후 유저정보 변경.. 추후 변경해야함
    @DisplayName("유저 정보 변경")
    @Test
    void updateMember() throws Exception {

        mockMvc.perform(put("/api/member").header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + jwtTokenData)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MEMBER_PROFILE_UPDATE_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customRequestFields(MEMBER_PUT_PROFILE_REQ)
                        )
                );
    }

    @DisplayName("유저 정보 반환")
    @Test
    void getProfile() throws Exception {
        mockMvc.perform(get("/api/member")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenData)
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customResponseFields(MEMBER_GET_PROFILE_RES))
                );

    }



}