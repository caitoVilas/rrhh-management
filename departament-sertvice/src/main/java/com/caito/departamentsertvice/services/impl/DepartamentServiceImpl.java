package com.caito.departamentsertvice.services.impl;

import com.caito.departamentsertvice.api.exceptions.customs.BadRequestException;
import com.caito.departamentsertvice.api.exceptions.customs.NotFoundException;
import com.caito.departamentsertvice.api.models.externals.Employee;
import com.caito.departamentsertvice.api.models.requests.DepartamentRequest;
import com.caito.departamentsertvice.api.models.responses.DepartamentListResponse;
import com.caito.departamentsertvice.api.models.responses.DepartamentResponse;
import com.caito.departamentsertvice.persistence.entities.Departament;
import com.caito.departamentsertvice.persistence.repositories.DepartamentRepository;
import com.caito.departamentsertvice.services.contracts.DepartamentService;
import com.caito.departamentsertvice.services.contracts.HttpClientService;
import com.caito.departamentsertvice.utils.constants.DepartamentConsts;
import com.caito.departamentsertvice.utils.enums.PositionName;
import com.caito.departamentsertvice.utils.mappers.DepartamentMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartamentServiceImpl implements DepartamentService {
    private final DepartamentRepository departamentRepository;
    private final HttpClientService httpClientService;

    @Value("${external.employee-url}")
    private String employeeUrl;

    @Override
    public void createDepartament(DepartamentRequest departament) {
        log.info("--> Creating department service");
        this.validateDepartament(departament);
        if (departament.getCreationDate() == null || departament.getCreationDate().toString().isEmpty())
            departament.setCreationDate(LocalDate.now());
        var url = employeeUrl + "/employees/"+ departament.getMamanerId();
        if (departament.getMamanerId() > 0){
            var employee = httpClientService.doGet(url, Employee.class);
            if (employee.getPosition() != PositionName.LEADER){
                log.error("--> ERROR: ".concat(DepartamentConsts.DEAPRTAMANT_MANAGER_NOT_LEADER));
                throw new BadRequestException(List.of(DepartamentConsts.DEAPRTAMANT_MANAGER_NOT_LEADER));
            }
        }

        var response = departamentRepository.save(DepartamentMapper.mapToENtity(departament));

    }

    @Override
    public DepartamentResponse getById(Long id) {
        log.info("--> get departament for Id service");
        var departament = this.getForId(id);
        Employee employee = null;
        if (departament.getManagerId() > 0){
            var url = employeeUrl + "/employees/"+ departament.getManagerId();
             employee = httpClientService.doGet(url, Employee.class);
            if (employee.getPosition() != PositionName.LEADER){
                log.error("--> ERROR: ".concat(DepartamentConsts.DEAPRTAMANT_MANAGER_NOT_LEADER));
                throw new BadRequestException(List.of(DepartamentConsts.DEAPRTAMANT_MANAGER_NOT_LEADER));
            }
        }
        return DepartamentMapper.mapTODto(departament, employee);
    }

    @Override
    public DepartamentResponse updateAnnualBudget(BigDecimal annualBudget) {

        return null;
    }

    @Override
    public List<DepartamentListResponse> getByName(String name) {
        log.info("--> get all departaments service");
        var departaments = departamentRepository.findByNameContainingIgnoreCase(name);
        if (departaments.isEmpty()){
            log.error("ERROR: ".concat(DepartamentConsts.DEPARTAMENT_NOT_FOUND));
            throw  new NotFoundException(DepartamentConsts.DEPARTAMENT_NOT_FOUND);
        }
        return departaments.stream().map(DepartamentMapper::mapListToDto).toList();
    }

    @Override
    public Page<DepartamentListResponse> getAll(int page, int size) {
        log.info("--> get all departaments service");
        PageRequest pr = PageRequest.of(page, size);
        return departamentRepository.findAll(pr).map(DepartamentMapper::mapListToDto);
    }

    private Departament getForId(Long id) {
        return departamentRepository.findById(id).orElseThrow(()-> {
            log.error("--> ERROR: ".concat(DepartamentConsts.DEPARTAMENT_NOT_FOUND));
            return new NotFoundException(DepartamentConsts.DEPARTAMENT_NOT_FOUND);
        });
    }

    private void validateDepartament(DepartamentRequest departament) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasText(departament.getName())){
            log.error("--> ERROR: ".concat(DepartamentConsts.DEPARTAMENT_NAME_EMPTY));
            errors.add(DepartamentConsts.DEPARTAMENT_NAME_EMPTY);
        }
        if (departamentRepository.existsByNameIgnoreCase(departament.getName())){
            log.error("--> ERROR: ".concat(DepartamentConsts.DEPARTAMENT_NAME_EXISTS));
            errors.add(DepartamentConsts.DEPARTAMENT_NAME_EXISTS);
        }
        if (!errors.isEmpty())
            throw new BadRequestException(errors);
    }
}
