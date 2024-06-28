package com.caito.payrollservice.persistence.repository;

import com.caito.payrollservice.persistence.entities.PayRoll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<PayRoll, Long>{
    Page<PayRoll> findByEmployeeId(Long employeeId, Pageable pageable);
    List<PayRoll> findByEmployeeId(Long employeeId);
}
