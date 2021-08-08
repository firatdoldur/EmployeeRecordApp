package com.example.employeerecordapp.persistence.repository;

import com.example.employeerecordapp.model.dto.Department;
import com.example.employeerecordapp.model.dto.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByPublicId(String publicId);

    void deleteByPublicId(String publicId);

    boolean existsByPublicId(String publicId);

    List<EmployeeEntity> findByStartedDateGreaterThanAndIncomeGreaterThan(Date startDate, int income);

    List<EmployeeEntity> findByDepartment(Department department);

    @Query(nativeQuery = true, value = "SELECT * FROM employee ORDER BY rand() LIMIT 1")
    Optional<EmployeeEntity> findRandomEmployee();
}
