package com.example.employeerecordapp.converter;


import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.model.request.CreateEmployeeRequest;
import com.example.employeerecordapp.model.response.EmployeeResponseDto;
import com.example.employeerecordapp.util.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    public EmployeeEntity requestToEntity(CreateEmployeeRequest employeeRequest) {
        Date now = new Date();
        return EmployeeEntity.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .income(employeeRequest.getIncome())
                .startedDate(employeeRequest.getStartDate())
                .publicId(SecurityUtils.generatePublicId())
                .department(employeeRequest.getDepartment())
                .location(employeeRequest.getLocation())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public EmployeeResponseDto toEmployeeResponse(EmployeeEntity employeeEntity) {
        return EmployeeResponseDto.builder()
                .publicId(employeeEntity.getPublicId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .income(employeeEntity.getIncome())
                .startedDate(employeeEntity.getStartedDate())
                .department(employeeEntity.getDepartment())
                .location(employeeEntity.getLocation())
                .build();
    }

    public List<EmployeeResponseDto> toEmployeeResponseList(List<EmployeeEntity> employeeEntities) {
        return employeeEntities
                .stream()
                .map(this::toEmployeeResponse)
                .collect(Collectors.toList());
    }


}
