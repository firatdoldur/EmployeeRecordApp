package com.example.employeerecordapp.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MonthlyWinnerResponseDto {
    private Long id;
    private EmployeeResponseDto employeeResponseDto;
    private int year;
    private int month;
}
