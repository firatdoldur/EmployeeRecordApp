package com.example.employeerecordapp.persistence.service;

import com.example.employeerecordapp.advice.exception.EmployeeNotFoundException;
import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import com.example.employeerecordapp.persistence.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePersistenceService {
    private final EmployeeRepository employeeRepository;

    public void save(EmployeeEntity userEntity) {
        employeeRepository.save(userEntity);
    }

    public List<EmployeeEntity> findAll() {
        return (List<EmployeeEntity>) employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> findByPublicId(String publicId) {
        return employeeRepository.findByPublicId(publicId);
    }

    public List<EmployeeEntity> getCustomList(Date date, int income) {
        return employeeRepository.findByStartedDateGreaterThanAndIncomeGreaterThan(date, income);
    }

    public void deleteEmployee(String publicId) {
        if (!employeeRepository.existsByPublicId(publicId)) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteByPublicId(publicId);
    }

    public List<EmployeeEntity> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    public Optional<EmployeeEntity> getRandomEmployee() {
        return employeeRepository.findRandomEmployee();
    }
}
