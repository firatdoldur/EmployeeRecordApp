package com.example.employeerecordapp.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateUtils {
    private static final LocalDate localDate = LocalDate.now();

    public static int getTodayMonthValue() {
        return localDate.getMonthValue();
    }

    public static int getTodayYearValue() {
        return localDate.getYear();
    }
}
