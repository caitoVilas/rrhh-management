package com.caito.payrollservice.api.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Data@Builder
public class PayrollRequest {
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
