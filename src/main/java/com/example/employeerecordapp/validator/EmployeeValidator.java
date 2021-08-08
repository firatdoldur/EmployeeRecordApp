package com.example.employeerecordapp.validator;


import com.example.employeerecordapp.advice.exception.ParameterException;
import com.example.employeerecordapp.model.request.CreateEmployeeRequest;
import com.example.employeerecordapp.model.request.CustomUpdateRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeValidator {
    public static void validateEmployeeCreateRequest(CreateEmployeeRequest request) {
        if (Objects.isNull(request.getFirstName()) || Strings.isBlank(request.getFirstName())) {
            throw new ParameterException();
        }
        if (Objects.isNull(request.getLastName()) || Strings.isBlank(request.getLastName())) {
            throw new ParameterException();
        }
        if (Objects.isNull(request.getIncome())) {
            throw new ParameterException();
        }
        if (Objects.isNull(request.getDepartment())) {
            throw new ParameterException();
        }
        if (Objects.isNull(request.getLocation()) || Strings.isBlank(request.getLocation())) {
            throw new ParameterException();
        }
        if (Objects.isNull(request.getStartDate())) {
            throw new ParameterException();
        }
    }

    public static void validateEmployeeUpdateRequest(CustomUpdateRequest request) {
        if (Objects.isNull(request.getLocation())) {
            throw new ParameterException();
        }
    }

}
