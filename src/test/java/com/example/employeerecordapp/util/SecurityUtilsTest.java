package com.example.employeerecordapp.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SecurityUtilsTest {

    @Test
    public void shouldGenerate_EightCharacterId() {
        assertThat(SecurityUtils.generatePublicId()).hasSize(8);
    }

}
