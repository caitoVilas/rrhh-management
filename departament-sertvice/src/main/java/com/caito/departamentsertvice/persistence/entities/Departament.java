package com.caito.departamentsertvice.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;



@Entity
@Table(name = "departaments")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class Departament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private BigDecimal annualBudget;
    private Long managerId;
}
