package com.caito.employeesservice.api.models.responses;

import com.caito.employeesservice.utils.enums.GenderName;
import com.caito.employeesservice.utils.enums.PositionName;
import com.caito.employeesservice.utils.enums.SeniorityName;
import com.caito.employeesservice.utils.enums.StatusName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Data@Builder
public class EmployeeResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private GenderName gender;
    private String address;
    private String phoneNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate hiredDate;
    private PositionName position;
    private SeniorityName seniority;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal salary;
    private StatusName status;
    private String socialSecurityNumber;
    private String bank;
    private String accountNumber;
    private String accountType;
}
