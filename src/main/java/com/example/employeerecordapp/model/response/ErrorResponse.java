package com.example.employeerecordapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final Long timestamp;
}
