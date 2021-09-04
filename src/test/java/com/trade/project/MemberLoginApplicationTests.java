package com.trade.project;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.trade.project.fixture.MemberFixture.MEMBER_JOIN_JSON;
import static com.trade.project.fixture.MemberFixture.MEMBER_LOGIN_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class MemberLoginApplicationTests extends ProjectApplicationTests{

    protected String jwtTokenData;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc.perform(post("/api/join")
                        .content(MEMBER_JOIN_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc
                .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(MEMBER_LOGIN_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();


        int target = response.indexOf("\"data\"" + ":");

        int lastIndex = response.indexOf("}", target);

        jwtTokenData = response.substring(target + 8, lastIndex - 1);
        System.out.println("jwtTokenData = " + jwtTokenData);
    }
}
