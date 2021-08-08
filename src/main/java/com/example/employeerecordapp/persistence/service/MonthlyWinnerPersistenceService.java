package com.example.employeerecordapp.persistence.service;

import com.example.employeerecordapp.advice.exception.WinnerNotFoundException;
import com.example.employeerecordapp.model.dto.MonthlyWinnerEntity;
import com.example.employeerecordapp.persistence.repository.MonthlyWinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyWinnerPersistenceService {
    private final MonthlyWinnerRepository monthlyWinnerRepository;

    public void save(MonthlyWinnerEntity monthlyWinnerEntity) {
        monthlyWinnerRepository.save(monthlyWinnerEntity);
    }

    public Optional<MonthlyWinnerEntity> getWinnerOfGivenDate(int year, int month) {
        return monthlyWinnerRepository.findByYearAndMonth(year, month);
    }

    public void deleteWinner(Long id) {
        if (!monthlyWinnerRepository.existsById(id)) {
            throw new WinnerNotFoundException();
        }
        monthlyWinnerRepository.deleteById(id);
    }

}
