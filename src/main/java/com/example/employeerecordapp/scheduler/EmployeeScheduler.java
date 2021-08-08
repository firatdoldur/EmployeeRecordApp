package com.example.employeerecordapp.scheduler;

import com.example.employeerecordapp.service.MonthlyWinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeScheduler {

    private final MonthlyWinnerService winnerService;

    /**
     * On the 25th of every month
     * at 12:30
     * will claim a winner of a random employee
     */
    @Scheduled(cron = "0 30 12 25 * ?")
    public void getRandomEmployeeAndMakeItMonthlyWinner_OnceAMonth() {
        System.out.println("CLAIM WINNER STARTED");
        winnerService.claimMonthlyWinner();
        System.out.println("CLAIM WINNER FINISHED");
    }


}
