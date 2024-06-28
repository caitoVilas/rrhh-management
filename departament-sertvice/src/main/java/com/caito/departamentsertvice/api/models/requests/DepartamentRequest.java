package com.caito.departamentsertvice.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor@NoArgsConstructor
@Data@Builder
public class DepartamentRequest implements Serializable {
    private String name;
    private String description;
    private LocalDate creationDate;
    private BigDecimal annualBudget;
    private Long mamanerId;
}
