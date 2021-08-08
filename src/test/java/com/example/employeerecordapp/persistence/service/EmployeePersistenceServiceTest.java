package com.example.employeerecordapp.persistence.service;

import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.persistence.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeePersistenceServiceTest {

    @InjectMocks
    @Autowired
    private EmployeePersistenceService employeePersistenceService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void findAll() {
        // Given
        EmployeeEntity employee1 = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeEntity employee2 = EmployeeEntity.builder().id(2L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();

        List<EmployeeEntity> list = Arrays.asList(employee1, employee2);
        //when
        when(employeeRepository.findAll()).thenReturn(list);

        //then
        List<EmployeeEntity> foundList = employeePersistenceService.findAll();
        assertThat(foundList.size()).isEqualTo(2);

    }

    @Test
    void findByPublicId() {
        //Given
        Optional<EmployeeEntity> employee = Optional.of(EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build());

        //when
        when(employeeRepository.findByPublicId(anyString())).thenReturn(employee);

        //then
        EmployeeEntity found = employeePersistenceService.findByPublicId("test").get();
        assertThat(found.getId()).isEqualTo(employee.get().getId());
        assertThat(found.getFirstName()).isEqualTo(employee.get().getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.get().getLastName());
        assertThat(found.getIncome()).isEqualTo(employee.get().getIncome());
        assertThat(found.getDepartment()).isEqualTo(employee.get().getDepartment());
        assertThat(found.getStartedDate()).isEqualTo(employee.get().getStartedDate());
        assertThat(found.getLocation()).isEqualTo(employee.get().getLocation());
    }

    @Test
    void getCustomList() {
        //given
        EmployeeEntity employee1 = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeEntity employee2 = EmployeeEntity.builder().id(2L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();

        List<EmployeeEntity> list = Arrays.asList(employee1, employee2);
        Date date = new Date();

        //when
        when(employeeRepository.findByStartedDateGreaterThanAndIncomeGreaterThan(date, 2500)).thenReturn(list);

        //then
        List<EmployeeEntity> foundList = employeePersistenceService.getCustomList(date, 2500);
        assertThat(foundList.size()).isEqualTo(2);

    }

    @Test
    void test_should_find_IT_department() {
        //given
        EmployeeEntity employee1 = EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();
        EmployeeEntity employee2 = EmployeeEntity.builder().id(2L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build();

        List<EmployeeEntity> list = Arrays.asList(employee1, employee2);

        // when
        when(employeeRepository.findByDepartment(any())).thenReturn(list);

        // then
        List<EmployeeEntity> foundList = employeePersistenceService.findByDepartment(Department.IT);
        assertThat(foundList.size()).isEqualTo(2);
    }

    @Test
    void getRandomEmployee() {
        // given
        Optional<EmployeeEntity> employee = Optional.of(EmployeeEntity.builder().id(1L).publicId("test1").firstName("testName")
                .lastName("testLastName").income(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").createdAt(new Date()).updatedAt(new Date()).build());

        // when
        when(employeeRepository.findRandomEmployee()).thenReturn(employee);

        //then
        EmployeeEntity found = employeePersistenceService.getRandomEmployee().get();
        assertThat(found.getId()).isEqualTo(employee.get().getId());
        assertThat(found.getFirstName()).isEqualTo(employee.get().getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.get().getLastName());
        assertThat(found.getIncome()).isEqualTo(employee.get().getIncome());
        assertThat(found.getDepartment()).isEqualTo(employee.get().getDepartment());


    }
}
