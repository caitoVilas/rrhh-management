package com.caito.payrollservice.api.models.responses;

import com.caito.payrollservice.api.models.externals.EmployeeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Data@Builder
public class PayrollResponse {
    private Long id;
    private EmployeeResponse employee;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate payDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate periodStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate periodEnd;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal grossSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal deductions;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal netSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal bonuses;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal taxes;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal socialSalaryDeductions;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal healthInsuranceDeductions;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal otherDeductions;
    private String comment;
}
