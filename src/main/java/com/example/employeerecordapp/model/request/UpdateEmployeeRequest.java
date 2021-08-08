package com.example.employeerecordapp.model.request;

import com.example.employeerecordapp.model.dto.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    private String firstName;
    private String lastName;
    private Integer income;
    private String location;
    private Department department;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
}
