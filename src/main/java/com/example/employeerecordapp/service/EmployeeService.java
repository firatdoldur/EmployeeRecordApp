package com.example.employeerecordapp.service;


import com.example.employeerecordapp.advice.exception.EmployeeNotFoundException;
import com.example.employeerecordapp.converter.EmployeeConverter;
import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.model.request.CreateEmployeeRequest;
import com.example.employeerecordapp.model.request.CustomUpdateRequest;
import com.example.employeerecordapp.model.request.UpdateEmployeeRequest;
import com.example.employeerecordapp.model.response.EmployeeResponseDto;
import com.example.employeerecordapp.persistence.service.EmployeePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeePersistenceService employeePersistenceService;
    private final EmployeeConverter employeeConverter;


    public void saveEmployee(CreateEmployeeRequest userRequest) {
        employeePersistenceService.save(employeeConverter.requestToEntity(userRequest));
    }

    public List<EmployeeResponseDto> findAll() {
        return employeeConverter.toEmployeeResponseList(employeePersistenceService.findAll());
    }

    public EmployeeResponseDto findById(String id) {
        return employeeConverter.toEmployeeResponse(
                employeePersistenceService.findByPublicId(id).orElseThrow(EmployeeNotFoundException::new));
    }

    public List<EmployeeResponseDto> getCustomList(Date startDate, int income) {
        return employeeConverter.toEmployeeResponseList(
                employeePersistenceService.getCustomList(startDate, income)
        );
    }

    public void updateEmployee(String publicId, UpdateEmployeeRequest request) {
        EmployeeEntity employeeEntity = employeePersistenceService.findByPublicId(publicId)
                .orElseThrow(EmployeeNotFoundException::new);
        if (Objects.nonNull(request.getFirstName())) {
            employeeEntity.setFirstName(request.getFirstName());
        }
        if (Objects.nonNull(request.getLastName())) {
            employeeEntity.setLastName(request.getLastName());
        }
        if (Objects.nonNull(request.getIncome())) {
            employeeEntity.setIncome(request.getIncome());
        }
        if (Objects.nonNull(request.getDepartment())) {
            employeeEntity.setDepartment(request.getDepartment());
        }
        if (Objects.nonNull(request.getStartDate())) {
            employeeEntity.setStartedDate(request.getStartDate());
        }
        if (Objects.nonNull(request.getLocation())) {
            employeeEntity.setLocation(request.getLocation());
        }

        employeePersistenceService.save(employeeEntity);
    }

    public void updateLocationsByDepartment(CustomUpdateRequest request, Department department) {
        List<EmployeeEntity> employees = employeePersistenceService.findByDepartment(department);
        employees.forEach(employee -> {
            employee.setLocation(request.getLocation());
            employeePersistenceService.save(employee);
        });
    }

    @Transactional
    public void deleteEmployee(String publicId) {
        employeePersistenceService.deleteEmployee(publicId);
    }

    public EmployeeEntity getRandomEmployee() {
        return employeePersistenceService.getRandomEmployee().orElseThrow(EmployeeNotFoundException::new);
    }
}