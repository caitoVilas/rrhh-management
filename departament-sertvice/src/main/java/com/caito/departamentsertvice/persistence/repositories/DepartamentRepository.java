package com.caito.departamentsertvice.persistence.repositories;

import com.caito.departamentsertvice.persistence.entities.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentRepository extends JpaRepository<Departament, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Departament> findByNameContainingIgnoreCase(String name);
}
