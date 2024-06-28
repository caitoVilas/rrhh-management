package com.caito.payrollservice.api.models.responses;

import com.caito.payrollservice.api.models.externals.EmployeeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class PayrollSimpleResponse implements Serializable {
    private Long id;
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
