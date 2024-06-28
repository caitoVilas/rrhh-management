package com.caito.departamentsertvice.api.models.responses;

import com.caito.departamentsertvice.api.models.externals.Employee;
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
public class DepartamentResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate creationDate;
    private BigDecimal annualBudget;
    private Employee manager;
}
