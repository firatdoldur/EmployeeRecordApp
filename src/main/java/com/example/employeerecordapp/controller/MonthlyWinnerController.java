package com.example.employeerecordapp.controller;

import com.example.employeerecordapp.model.response.MonthlyWinnerResponseDto;
import com.example.employeerecordapp.service.MonthlyWinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.employeerecordapp.util.DateUtils.getTodayMonthValue;
import static com.example.employeerecordapp.util.DateUtils.getTodayYearValue;


@RestController
@RequestMapping("/monthly-winner")
@RequiredArgsConstructor
public class MonthlyWinnerController {
    private final MonthlyWinnerService monthlyWinnerService;

    @GetMapping
    public MonthlyWinnerResponseDto getThisMonthsWinner() {
        return monthlyWinnerService.getWinnerOfGivenDate(getTodayYearValue(), getTodayMonthValue());
    }

    @GetMapping("/custom-date")
    public MonthlyWinnerResponseDto getMonthlyWinner(@RequestParam(value = "year") String year, @RequestParam("month") String month) {
        return monthlyWinnerService.getWinnerOfGivenDate(Integer.parseInt(year), Integer.parseInt(month));
    }

    @DeleteMapping("/{id}")
    public void deleteWinner(@PathVariable("id") Long id) {
        monthlyWinnerService.deleteWinner(id);
    }
}