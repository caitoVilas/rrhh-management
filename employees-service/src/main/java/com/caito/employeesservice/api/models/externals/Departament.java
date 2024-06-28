package com.caito.employeesservice.api.models.externals;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@Builder
public class Departament implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private BigDecimal annualBudget;
}
