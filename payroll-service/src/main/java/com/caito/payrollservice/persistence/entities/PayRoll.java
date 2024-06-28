package com.caito.payrollservice.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payroll")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class PayRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private LocalDate payDate;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private BigDecimal grossSalary;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private BigDecimal bonuses;
    private BigDecimal taxes;
    private BigDecimal socialSalaryDeductions;
    private BigDecimal healthInsuranceDeductions;
    private BigDecimal otherDeductions;
    private String comment;

}
