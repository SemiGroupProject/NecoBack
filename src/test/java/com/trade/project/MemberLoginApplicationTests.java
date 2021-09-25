package com.trade.project;

import org.junit.jupiter.api.BeforeAll;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public abstract class MemberLoginApplicationTests extends ProjectApplicationTests{

    protected String jwtTokenData;

    @BeforeAll
    public void setUp() throws Exception {

    }
}
