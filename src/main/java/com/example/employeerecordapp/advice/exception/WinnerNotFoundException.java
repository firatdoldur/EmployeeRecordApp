package com.example.employeerecordapp.advice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinnerNotFoundException extends RuntimeException {
    public WinnerNotFoundException() {
        super("Winner not fonud!");
    }
}