package com.trade.project.favorite.presentation;

import com.trade.project.ProjectApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.project.fixture.FavoriteFixture.ITEM_ID;
import static com.trade.project.fixture.FavoriteFixture.ITEM_ID2;
import static com.trade.project.fixture.MemberFixture.MEMBER_TEST_LOGIN_JSON_1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@DisplayName("Favorite-Controller-Test")
class FavoriteControllerTest extends ProjectApplicationTests {

    private String jwtTokenData;

    @BeforeEach
    void setUp() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                        .content(MEMBER_TEST_LOGIN_JSON_1))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();


        int target = response.indexOf("\"data\"" + ":");

        int lastIndex = response.indexOf("}", target);

        jwtTokenData = response.substring(target + 8, lastIndex - 1);
    }

    @DisplayName("찜 생성")
    @Test
    void createFavorite() throws Exception {

        mockMvc.perform(post("/api/favorite/" + ITEM_ID)
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + jwtTokenData)
                ).andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("찜 제거")
    @Test
    void deleteFavorite() throws Exception {
        mockMvc.perform(delete("/api/favorite/" + ITEM_ID2)
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + jwtTokenData)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("찜 개수 확인")
    @Test
    void countFavorites() throws Exception {
        mockMvc.perform(get("/api/favorites/" + ITEM_ID2))
                .andDo(print())
                .andExpect(status().isOk());
    }




}