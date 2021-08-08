package com.example.employeerecordapp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DateUtilsTest {

    private LocalDate localDate;

    @BeforeEach
    void setUp() {
        localDate = LocalDate.now();
    }

    @Test
    void testGetTodayMonthValue() {
        assertThat(DateUtils.getTodayMonthValue()).isEqualTo(localDate.getMonthValue());
    }

    @Test
    void testGetTodayYearValue() {
        assertThat(DateUtils.getTodayYearValue()).isEqualTo(localDate.getYear());
    }
}