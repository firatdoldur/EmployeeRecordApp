package com.example.employeerecordapp.controller;

import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.request.CreateEmployeeRequest;
import com.example.employeerecordapp.model.request.CustomUpdateRequest;
import com.example.employeerecordapp.model.request.UpdateEmployeeRequest;
import com.example.employeerecordapp.model.response.EmployeeResponseDto;
import com.example.employeerecordapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static com.example.employeerecordapp.validator.EmployeeValidator.validateEmployeeCreateRequest;
import static com.example.employeerecordapp.validator.EmployeeValidator.validateEmployeeUpdateRequest;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeRequest userRequest) {
        validateEmployeeCreateRequest(userRequest);
        employeeService.saveEmployee(userRequest);
    }

    @GetMapping
    public List<EmployeeResponseDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{publicId}")
    public EmployeeResponseDto findEmployeeById(@PathVariable String publicId) {
        return employeeService.findById(publicId);
    }

    @GetMapping("/custom")
    public List<EmployeeResponseDto> customEmployees(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                     @RequestParam("income") String income) {
        return employeeService.getCustomList(startDate, Integer.parseInt(income));
    }

    @PutMapping("/department/{department}")
    public void updateLocations(@RequestBody CustomUpdateRequest request, @PathVariable Department department) {
        validateEmployeeUpdateRequest(request);
        employeeService.updateLocationsByDepartment(request, department);

    }

    @PutMapping("/{publicId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable String publicId,
                               @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        employeeService.updateEmployee(publicId, updateEmployeeRequest);
    }


    @DeleteMapping("/{publicId}")
    public void deleteEmployee(@PathVariable String publicId) {
        employeeService.deleteEmployee(publicId);
    }


}
