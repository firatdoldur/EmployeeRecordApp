package com.example.employeerecordapp.validator;

import com.example.employeerecordapp.advice.exception.ParameterException;
import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.request.CreateEmployeeRequest;
import com.example.employeerecordapp.model.request.CustomUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EmployeeValidatorTest {

    @Test
    void shouldValidate_CreateEmployeeRequest() {
        //given

        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest("test", "test", 2500, Department.IT, "test", new Date());

        //then
        assertDoesNotThrow(() -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }


    @Test
    void test_shouldThrow_ParameterException_whenFirstNameIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setLastName("test");
        createEmployeeRequest.setIncome(2500);
        createEmployeeRequest.setDepartment(Department.IT);
        createEmployeeRequest.setLocation("test");
        createEmployeeRequest.setStartDate(new Date());

        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }

    @Test
    void test_shouldThrow_ParameterException_whenLastNameIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setFirstName("test");
        createEmployeeRequest.setIncome(2500);
        createEmployeeRequest.setDepartment(Department.IT);
        createEmployeeRequest.setLocation("test");
        createEmployeeRequest.setStartDate(new Date());
        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }

    @Test
    void test_shouldThrow_ParameterException_whenIncomeIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setFirstName("test");
        createEmployeeRequest.setLastName("test");
        createEmployeeRequest.setDepartment(Department.IT);
        createEmployeeRequest.setLocation("test");
        createEmployeeRequest.setStartDate(new Date());
        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }

    @Test
    void test_shouldThrow_ParameterException_whenDepartmentIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setFirstName("test");
        createEmployeeRequest.setLastName("test");
        createEmployeeRequest.setIncome(2500);
        createEmployeeRequest.setLocation("test");
        createEmployeeRequest.setStartDate(new Date());
        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }

    @Test
    void test_shouldThrow_ParameterException_whenLocationIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setFirstName("test");
        createEmployeeRequest.setLastName("test");
        createEmployeeRequest.setDepartment(Department.IT);
        createEmployeeRequest.setIncome(2500);
        createEmployeeRequest.setStartDate(new Date());
        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }

    @Test
    void test_shouldThrow_ParameterException_whenStartDateIsMissing() {
        //Given
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setFirstName("test");
        createEmployeeRequest.setLastName("test");
        createEmployeeRequest.setDepartment(Department.IT);
        createEmployeeRequest.setLocation("test");
        createEmployeeRequest.setIncome(2500);
        //Then
        assertThrows(ParameterException.class,
                () -> EmployeeValidator.validateEmployeeCreateRequest(createEmployeeRequest));
    }


    @Test
    void test_should_validate_CustomUpdateRequest() {
        //Given
        CustomUpdateRequest customUpdateRequest = new CustomUpdateRequest("izmir");
        assertDoesNotThrow(() -> EmployeeValidator.validateEmployeeUpdateRequest(customUpdateRequest));
    }
}
