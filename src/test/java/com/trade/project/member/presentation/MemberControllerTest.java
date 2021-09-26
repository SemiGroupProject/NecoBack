package com.trade.project.member.presentation;

import com.trade.project.ProjectApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static com.trade.project.fixture.MemberFixture.*;
import static com.trade.project.member.docs.MemberDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@DisplayName("Member-Controller-Test")
class MemberControllerTest extends ProjectApplicationTests {

    @DisplayName("로그인")
    @Transactional
    @Test
    void login() throws Exception {
        mockMvc.perform(post("/api/join")
                        .content(MEMBER_JOIN_JSON_2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/login")
                .content(MEMBER_LOGIN_JSON_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        customRequestFields(MEMBER_POST_LOGIN_REQ),
                        customResponseFields(MEMBER_POST_LOGIN_RES)
                        )
                );
    }


    @DisplayName("회원가입")
    @Transactional
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
                        requestParameters(
                                parameterWithName("accountId").description("중복 확인할 아이디")
                        ),
                        customResponseFields(MEMBER_GET_DUPLICATE_RES))
                );

    }

    //TODO: 로그인 후 유저정보 변경.. 추후 변경해야함
    @DisplayName("유저 정보 변경")
    @Transactional
    @Test
    void updateMember() throws Exception {
        mockMvc.perform(post("/api/join")
                        .content(MEMBER_JOIN_JSON_2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc
                .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(MEMBER_LOGIN_JSON_2))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();


        int target = response.indexOf("\"data\"" + ":");

        int lastIndex = response.indexOf("}", target);

        String jwtTokenData = response.substring(target + 8, lastIndex - 1);

        // 회원정보 변경
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
    @Transactional
    @Test
    void getProfile() throws Exception {
        mockMvc.perform(post("/api/join")
                        .content(MEMBER_JOIN_JSON_3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc
                .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(MEMBER_LOGIN_JSON_3))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();


        int target = response.indexOf("\"data\"" + ":");

        int lastIndex = response.indexOf("}", target);

        String jwtTokenData = response.substring(target + 8, lastIndex - 1);

        // 회원정보 반환
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