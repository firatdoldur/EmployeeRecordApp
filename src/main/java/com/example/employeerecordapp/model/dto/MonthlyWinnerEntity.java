package com.example.employeerecordapp.model.dto;

import lombok.*;

import javax.persistence.*;

@Entity(name = "monthly_winner")
@Table(name = "monthly_winner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyWinnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee")
    private EmployeeEntity employee;

    @Column
    private int year;

    @Column
    private int month;


}