package com.example.employeerecordapp.service;

import com.example.employeerecordapp.advice.exception.EmployeeNotFoundException;
import com.example.employeerecordapp.converter.EmployeeConverter;
import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.model.response.EmployeeResponseDto;
import com.example.employeerecordapp.persistence.service.EmployeePersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    private EmployeePersistenceService employeePersistenceService;
    @MockBean
    private EmployeeConverter employeeConverter;

    @Autowired
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void test_should_find_all() {
        //Given
        EmployeeEntity employee1 = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeEntity employee2 = EmployeeEntity.builder().id(2L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeResponseDto employeeResponseDto1 = EmployeeResponseDto.builder()
                .publicId(employee1.getPublicId()).firstName(employee1.getFirstName()).lastName(employee1.getLastName())
                .income(employee1.getIncome()).department(employee1.getDepartment()).startedDate(employee1.getStartedDate())
                .location(employee1.getLocation()).build();
        EmployeeResponseDto employeeResponseDto2 = EmployeeResponseDto.builder()
                .publicId(employee2.getPublicId()).firstName(employee2.getFirstName()).lastName(employee2.getLastName())
                .income(employee2.getIncome()).department(employee2.getDepartment()).startedDate(employee2.getStartedDate())
                .location(employee2.getLocation()).build();

        //when
        when(employeeConverter.toEmployeeResponseList(any())).thenReturn(Arrays.asList(employeeResponseDto1, employeeResponseDto2));

        //then
        assertThat(employeeService.findAll()).isEqualTo(Arrays.asList(employeeResponseDto1, employeeResponseDto2));
    }

    @Test
    void findById() {
        //Given
        EmployeeEntity employee = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .publicId(employee.getPublicId()).firstName(employee.getFirstName()).lastName(employee.getLastName())
                .income(employee.getIncome()).department(employee.getDepartment()).startedDate(employee.getStartedDate())
                .location(employee.getLocation()).build();

        // when
        when(employeePersistenceService.findByPublicId(anyString())).thenReturn(Optional.of(employee));
        when(employeeConverter.toEmployeeResponse(employee)).thenReturn(employeeResponseDto);

        //then
        EmployeeResponseDto found = employeeService.findById(employee.getPublicId());

        assertDoesNotThrow(() -> employeeService.findById(employee.getPublicId()));
        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getIncome()).isEqualTo(employee.getIncome());
        assertThat(found.getDepartment()).isEqualTo(employee.getDepartment());
        assertThat(found.getStartedDate()).isEqualTo(employee.getStartedDate());
        assertThat(found.getLocation()).isEqualTo(employee.getLocation());
    }


    @Test
    void test_should_return_random_employee() {
        //Given
        EmployeeEntity employee = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        //when
        when(employeePersistenceService.getRandomEmployee()).thenReturn(Optional.of(employee));

        //then
        EmployeeEntity found = employeeService.getRandomEmployee();
        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getIncome()).isEqualTo(employee.getIncome());
        assertThat(found.getDepartment()).isEqualTo(employee.getDepartment());
        assertThat(found.getStartedDate()).isEqualTo(employee.getStartedDate());
        assertThat(found.getLocation()).isEqualTo(employee.getLocation());
    }

    @Test
    void test_should_Throw_EmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getRandomEmployee());
    }
}