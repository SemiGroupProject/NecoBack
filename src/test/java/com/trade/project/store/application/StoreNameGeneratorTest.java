package com.trade.project.store.application;

import com.trade.project.store.application.StoreNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StoreNameGeneratorTest {


    @DisplayName("임시 상점이름 생성")
    @Test
    void createRandomName()  {

        String name1 = StoreNameGenerator.createName();
        String name2 = StoreNameGenerator.createName();

        assertThat(name1).isNotEqualTo(name2);
    }

}