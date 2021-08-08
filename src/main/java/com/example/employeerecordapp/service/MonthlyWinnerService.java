package com.example.employeerecordapp.service;

import com.example.employeerecordapp.advice.exception.WinnerNotFoundException;
import com.example.employeerecordapp.converter.WinnerConverter;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.model.dto.MonthlyWinnerEntity;
import com.example.employeerecordapp.model.response.MonthlyWinnerResponseDto;
import com.example.employeerecordapp.persistence.service.MonthlyWinnerPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.employeerecordapp.util.DateUtils.getTodayMonthValue;
import static com.example.employeerecordapp.util.DateUtils.getTodayYearValue;

@Service
@RequiredArgsConstructor
public class MonthlyWinnerService {

    private final MonthlyWinnerPersistenceService monthlyWinnerPersistenceService;
    private final EmployeeService employeeService;
    private final WinnerConverter winnerConverter;


    public MonthlyWinnerResponseDto getWinnerOfGivenDate(int year, int month) {
        return winnerConverter.toMonthlyWinnerResponse(monthlyWinnerPersistenceService.getWinnerOfGivenDate(year, month)
                .orElseThrow(WinnerNotFoundException::new));
    }

    public void claimMonthlyWinner() {

        EmployeeEntity employeeEntity = employeeService.getRandomEmployee();
        MonthlyWinnerEntity monthlyWinnerEntity = MonthlyWinnerEntity.builder()
                .employee(employeeEntity)
                .year(getTodayYearValue())
                .month(getTodayMonthValue())
                .build();
        monthlyWinnerPersistenceService.save(monthlyWinnerEntity);
    }

    @Transactional
    public void deleteWinner(Long id) {
        monthlyWinnerPersistenceService.deleteWinner(id);
    }


}
