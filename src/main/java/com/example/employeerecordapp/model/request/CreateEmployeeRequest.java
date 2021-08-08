package com.example.employeerecordapp.model.request;

import com.example.employeerecordapp.model.dto.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    @NotNull(message = "income cannot be null")
    private Integer income;
    @NotNull
    private Department department;
    @NonNull
    private String location;
    @NotNull
            (message = "startDate cannot be null")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
}