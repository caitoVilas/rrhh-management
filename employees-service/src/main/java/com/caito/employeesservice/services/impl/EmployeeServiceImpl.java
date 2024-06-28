package com.caito.employeesservice.services.impl;

import com.caito.employeesservice.api.exceptions.customs.BadRequestException;
import com.caito.employeesservice.api.exceptions.customs.NotFoundException;
import com.caito.employeesservice.api.models.requests.EmployeeRequest;
import com.caito.employeesservice.api.models.responses.EmployeeListResponse;
import com.caito.employeesservice.api.models.responses.EmployeeResponse;
import com.caito.employeesservice.persistence.entities.Employee;
import com.caito.employeesservice.persistence.repositories.EmployeeRepository;
import com.caito.employeesservice.services.contracts.EmployeeService;
import com.caito.employeesservice.utils.constants.EmployeeConsts;
import com.caito.employeesservice.utils.mappers.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;



    @Override
    public void createEmployee(EmployeeRequest request) {
        log.info("--> creation employee service");
        this.validateEmployee(request);
        employeeRepository.save(EmployeeMapper.mapToEntity(request));
    }

    @Override
    public EmployeeResponse getById(Long id) {
        log.info("--> get employee by id");
        var employee = this.getEmployee(id);
        return EmployeeMapper.mapToDto(employee);
    }

    @Override
    public Page<EmployeeListResponse> getAll(int page, int size) {
        log.info("--> get all employees");
        PageRequest pr = PageRequest.of(page, size);
        return employeeRepository.findAll(pr).map(EmployeeMapper::mapListToDto);
    }

    @Override
    public List<EmployeeListResponse> getByName(String name) {
        log.info("--> get all employees for name");
        List<Employee> employees = employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        if (employees.isEmpty())
            throw new NotFoundException(EmployeeConsts.EMPLOYEE_NOT_FOUND);
        return employees.stream().map(EmployeeMapper::mapListToDto).toList();
    }

    private void validateEmployee(EmployeeRequest request) {
        log.info("--> validating employee data...");
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasText(request.getFirstName())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_NAME_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_NAME_EMPTY);
        }
        if (!StringUtils.hasText(request.getLastName())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_LASTNAME_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_LASTNAME_EMPTY);
        }
        if (employeeRepository.findByFirstNameAndLastNameIgnoreCase(request.getFirstName(), request.getLastName())
                .orElse(null) != null){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_EXISTS));
            errors.add(EmployeeConsts.EMPLOYEE_EXISTS);
        }
        if (request.getDateOfBirth() == null || request.getDateOfBirth().toString().isEmpty()){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_DATE_OF_BIRTH_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_DATE_OF_BIRTH_EMPTY);
        }
        if (!StringUtils.hasText(request.getGender().name())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_GENRE_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_GENRE_EMPTY);
        }
        if (!StringUtils.hasText(request.getPhoneNumber())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_PHONE_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_PHONE_EMPTY);
        }
        if (!StringUtils.hasText(request.getEmail())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_MAIL_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_MAIL_EMPTY);
        }else if (employeeRepository.findByEmail(request.getEmail()).orElse(null) != null){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_MAIL_EXISTS));
            errors.add(EmployeeConsts.EMPLOYEE_EXISTS);
        }else if (!this.validateMail(request.getEmail())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_MAIL_MALFORMED));
            errors.add(EmployeeConsts.EMPLOYEE_MAIL_MALFORMED);
        }
        if (request.getHiredDate() == null || request.getHiredDate().toString().isEmpty()) {
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_HIRED_DATE_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_HIRED_DATE_EMPTY);
        }
        if (!StringUtils.hasText(request.getPosition().name())) {
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_POSITION_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_POSITION_EMPTY);
        }
        if (!StringUtils.hasText(request.getSeniority().name())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_SENIORITY_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_SENIORITY_EMPTY);
        }
        if (!StringUtils.hasText(request.getStatus().name())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_STATUS_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_STATUS_EMPTY);
        }
        if (!StringUtils.hasText(request.getSocialSecurityNumber())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_SOCIAL_SECUTITY_NUMBER_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_SOCIAL_SECUTITY_NUMBER_EMPTY);
        }
        if (!StringUtils.hasText(request.getBank())) {
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_BANK_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_BANK_EMPTY);
        }
        if (!StringUtils.hasText(request.getAccountNumber())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_ACCOUNT_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_ACCOUNT_EMPTY);
        }
        if (!StringUtils.hasText(request.getAccountType())){
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_ACCOUNT_TYPE_EMPTY));
            errors.add(EmployeeConsts.EMPLOYEE_ACCOUNT_TYPE_EMPTY);
        }
        if (!errors.isEmpty())
            throw new BadRequestException(errors);
    }




    private boolean validatePassword(String password) {
        final String PASS_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        final Pattern PASS_PATTERN = Pattern.compile(PASS_REGEX);
        if (PASS_PATTERN.matcher(password).matches())
            return true;
        return false;
    }

    private boolean validateMail(String email) {
        Pattern pattern =
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()){
            log.info("---> mail valido");
            return true;
        }else {
            return false;
        }
    }

    private Employee getEmployee(Long id){
        log.info("--> find employee id {}", id);
       return employeeRepository.findById(id).orElseThrow(() -> {
            log.error("--> ERROR: ".concat(EmployeeConsts.EMPLOYEE_NOT_FOUND));
            return new NotFoundException(EmployeeConsts.EMPLOYEE_NOT_FOUND);
        });
    }
}

