package com.example.employeerecordapp.service;

import com.example.employeerecordapp.advice.exception.WinnerNotFoundException;
import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.model.dto.MonthlyWinnerEntity;
import com.example.employeerecordapp.model.response.EmployeeResponseDto;
import com.example.employeerecordapp.model.response.MonthlyWinnerResponseDto;
import com.example.employeerecordapp.persistence.service.MonthlyWinnerPersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class MonthlyWinnerServiceTest {

    @Autowired
    @InjectMocks
    private MonthlyWinnerService monthlyWinnerService;

    @MockBean
    private MonthlyWinnerPersistenceService monthlyWinnerPersistenceService;

    @Test
    void test_should_get_monthly_winner() {
        //given
        EmployeeEntity employee1 = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .publicId("test").firstName("testName").lastName("testLastName")
                .income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        MonthlyWinnerResponseDto monthlyWinnerResponseDto = MonthlyWinnerResponseDto.builder().id(1L)
                .employeeResponseDto(employeeResponseDto).year(2021).month(8).build();
        MonthlyWinnerEntity monthlyWinnerEntity = new MonthlyWinnerEntity(1L, employee1, 2021, 8);


        //when
        when(monthlyWinnerPersistenceService.getWinnerOfGivenDate(anyInt(), anyInt())).thenReturn(Optional.of(monthlyWinnerEntity));

        //then
        MonthlyWinnerResponseDto found = monthlyWinnerService.getWinnerOfGivenDate(2021, 8);
        assertThat(found.getMonth()).isEqualTo(monthlyWinnerResponseDto.getMonth());
        assertThat(found.getYear()).isEqualTo(monthlyWinnerResponseDto.getYear());
        assertThat(found.getEmployeeResponseDto().getFirstName()).isEqualTo(monthlyWinnerResponseDto.getEmployeeResponseDto().getFirstName());
    }

    @Test
    void test_should_throw_Winner_Not_Found_exception() {
        assertThrows(WinnerNotFoundException.class, () -> monthlyWinnerService.getWinnerOfGivenDate(2021, 8));
    }
}