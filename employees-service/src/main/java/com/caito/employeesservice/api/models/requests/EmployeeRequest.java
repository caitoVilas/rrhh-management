package com.caito.employeesservice.api.models.requests;

import com.caito.employeesservice.utils.enums.GenderName;
import com.caito.employeesservice.utils.enums.PositionName;
import com.caito.employeesservice.utils.enums.SeniorityName;
import com.caito.employeesservice.utils.enums.StatusName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Data@Builder
public class EmployeeRequest implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private GenderName gender;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate hiredDate;
    private PositionName position;
    private SeniorityName seniority;
    private BigDecimal salary;
    private StatusName status;
    private String socialSecurityNumber;
    private String bank;
    private String accountNumber;
    private String accountType;
}
