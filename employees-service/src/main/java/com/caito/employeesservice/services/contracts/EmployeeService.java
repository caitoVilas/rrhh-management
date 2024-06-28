package com.caito.employeesservice.services.contracts;

import com.caito.employeesservice.api.models.requests.EmployeeRequest;
import com.caito.employeesservice.api.models.responses.EmployeeListResponse;
import com.caito.employeesservice.api.models.responses.EmployeeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeRequest request);
    EmployeeResponse getById(Long id);
    Page<EmployeeListResponse> getAll(int page, int size);
    List<EmployeeListResponse> getByName(String name);
}
