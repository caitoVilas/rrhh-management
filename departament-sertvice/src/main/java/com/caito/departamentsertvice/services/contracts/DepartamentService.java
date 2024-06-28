package com.caito.departamentsertvice.services.contracts;

import com.caito.departamentsertvice.api.models.requests.DepartamentRequest;
import com.caito.departamentsertvice.api.models.responses.DepartamentListResponse;
import com.caito.departamentsertvice.api.models.responses.DepartamentResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface DepartamentService {
    void createDepartament(DepartamentRequest departament);
    DepartamentResponse getById(Long id);
    DepartamentResponse updateAnnualBudget(BigDecimal annualBudget);
    List<DepartamentListResponse> getByName(String name);
    Page<DepartamentListResponse> getAll(int page, int size);
}
