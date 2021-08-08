package com.example.employeerecordapp.persistence.repository;

import com.example.employeerecordapp.model.dto.MonthlyWinnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonthlyWinnerRepository extends CrudRepository<MonthlyWinnerEntity, Long> {
    Optional<MonthlyWinnerEntity> findByYearAndMonth(int year, int month);

}
