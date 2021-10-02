package com.trade.project.favorite.presentation;

import com.trade.project.ProjectApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


class FavoriteControllerTest extends ProjectApplicationTests {

    @Autowired
    private FavoriteController favoriteController;

    @Test
    void test() throws Exception {
//        mockMvc.perform(post("/api/favorite/{itemId}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .)
    }

}