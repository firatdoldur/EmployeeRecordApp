package com.example.employeerecordapp.converter;

import com.example.employeerecordapp.model.dto.MonthlyWinnerEntity;
import com.example.employeerecordapp.model.response.MonthlyWinnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinnerConverter {
    private final EmployeeConverter employeeConverter;

    public MonthlyWinnerResponseDto toMonthlyWinnerResponse(MonthlyWinnerEntity entity) {
        return MonthlyWinnerResponseDto.builder()
                .id(entity.getId())
                .employeeResponseDto(employeeConverter.toEmployeeResponse(entity.getEmployee()))
                .year(entity.getYear())
                .month(entity.getMonth())
                .build();
    }
}
