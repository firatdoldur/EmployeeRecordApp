package com.example.employeerecordapp.model.response;

import com.example.employeerecordapp.model.dto.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EmployeeResponseDto {

    private String publicId;
    private String firstName;
    private String lastName;
    private Integer income;
    private Department department;
    private Date startedDate;
    private String location;

}
