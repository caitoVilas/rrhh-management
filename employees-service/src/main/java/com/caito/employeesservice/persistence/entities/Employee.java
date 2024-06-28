package com.caito.employeesservice.persistence.entities;

import com.caito.employeesservice.utils.enums.GenderName;
import com.caito.employeesservice.utils.enums.PositionName;
import com.caito.employeesservice.utils.enums.SeniorityName;
import com.caito.employeesservice.utils.enums.StatusName;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "employees")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderName gender;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate hiredDate;
    @Enumerated(EnumType.STRING)
    private PositionName position;
    @Enumerated(EnumType.STRING)
    private SeniorityName seniority;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private StatusName status;
    private String socialSecurityNumber;
    private String bank;
    private String accountNumber;
    private String accountType;
}
