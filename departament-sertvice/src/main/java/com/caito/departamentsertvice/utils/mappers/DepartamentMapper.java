package com.caito.departamentsertvice.utils.mappers;

import com.caito.departamentsertvice.api.models.externals.Employee;
import com.caito.departamentsertvice.api.models.requests.DepartamentRequest;
import com.caito.departamentsertvice.api.models.responses.DepartamentListResponse;
import com.caito.departamentsertvice.api.models.responses.DepartamentResponse;
import com.caito.departamentsertvice.persistence.entities.Departament;

public class DepartamentMapper {

    public static Departament mapToENtity(DepartamentRequest request){
        return Departament.builder()
                .name(request.getName())
                .description(request.getDescription())
                .creationDate(request.getCreationDate())
                .annualBudget(request.getAnnualBudget())
                .managerId(request.getMamanerId())
                .build();
    }

    public static DepartamentResponse mapTODto(Departament departament, Employee employee){
        return DepartamentResponse.builder()
                .id(departament.getId())
                .name(departament.getName())
                .description(departament.getDescription())
                .creationDate(departament.getCreationDate())
                .annualBudget(departament.getAnnualBudget())
                .manager(employee)
                .build();
    }

    public static DepartamentListResponse mapListToDto(Departament departament){
        return DepartamentListResponse.builder()
                .id(departament.getId())
                .name(departament.getName())
                .description(departament.getDescription())
                .creationDate(departament.getCreationDate())
                .annualBudget(departament.getAnnualBudget())
                .build();
    }
}
