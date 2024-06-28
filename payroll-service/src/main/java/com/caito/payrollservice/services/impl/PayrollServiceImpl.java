package com.caito.payrollservice.services.impl;

import com.caito.payrollservice.api.exceptions.customs.BadRequestException;
import com.caito.payrollservice.api.exceptions.customs.NotFoundException;
import com.caito.payrollservice.api.models.externals.EmployeeResponse;
import com.caito.payrollservice.api.models.requests.PayrollRequest;
import com.caito.payrollservice.api.models.responses.PayrollListResponse;
import com.caito.payrollservice.api.models.responses.PayrollResponse;
import com.caito.payrollservice.utils.constants.PayrollConstants;
import com.caito.payrollservice.utils.mappers.PayrollMapper;
import com.caito.payrollservice.persistence.repository.PayrollRepository;
import com.caito.payrollservice.services.contracts.HttpClientService;
import com.caito.payrollservice.services.contracts.PayrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private final PayrollRepository payrollRepository;
    private final HttpClientService httpClientService;

    @Value("${external.employee-url}")
    private String employeeUrl;

    @Override
    public void createPayroll(PayrollRequest request) {
        log.info("Creating payroll for employee service");
        this.validatePayroll(request);
        var payroll = PayrollMapper.mapToEntity(request);
        payrollRepository.save(payroll);
    }

    @Override
    public PayrollResponse getPayroll(Long id) {
        log.info("Getting payroll by id: ".concat(id.toString()));
        var payroll = payrollRepository.findById(id).orElseThrow(() -> {
            log.error("--> ERROR: ".concat(PayrollConstants.PAYROLL_NOT_FOUND));
            return new NotFoundException(PayrollConstants.PAYROLL_NOT_FOUND);
        });
        var url = employeeUrl + "/employees/" + payroll.getEmployeeId();
        EmployeeResponse employee = httpClientService.doGet(url, EmployeeResponse.class);
        if (employee == null){
            log.error("--> ERROR: ".concat(PayrollConstants.PAYROLL_EMPLOYEE_NOT_FOUND));
            throw  new NotFoundException(PayrollConstants.PAYROLL_EMPLOYEE_NOT_FOUND);
        }

        return PayrollMapper.mapToDto(payroll, employee);
    }

    @Override
    public PayrollListResponse getPayrolls(Long id) {
        log.info("Getting payrolls by employee id: ".concat(id.toString()));
        var url = employeeUrl + "/employees/" + id;
        var employee = httpClientService.doGet(url, EmployeeResponse.class);
        if (employee == null){
            log.error("--> ERROR: ".concat(PayrollConstants.PAYROLL_EMPLOYEE_NOT_FOUND));
            throw  new NotFoundException(PayrollConstants.PAYROLL_EMPLOYEE_NOT_FOUND);
        }
        var payrolls = payrollRepository.findByEmployeeId(id);

        return PayrollListResponse.builder()
                .employee(employee)
                .payrolls(payrolls.stream().map(PayrollMapper::mapToDtotoList).collect(Collectors.toList()))
                .build();
    }

    private void validatePayroll(PayrollRequest request) {
        log.info("Validating payroll request");
        List<String> errors = new ArrayList<>();
        if (request.getPeriodStart() == null) {
            log.error("--> ERROR: ".concat(PayrollConstants.PERIOD_START_REQUIRED));
            errors.add(PayrollConstants.PERIOD_START_REQUIRED);
        }
        if (request.getPeriodEnd() == null) {
            log.error("--> ERROR: ".concat(PayrollConstants.PERIOD_END_REQUIRED));
            errors.add(PayrollConstants.PERIOD_END_REQUIRED);
        }
        if (request.getGrossSalary() == null){
            log.error("--> ERROR: ".concat(PayrollConstants.GROSS_SALARY_REQUIRED));
            errors.add(PayrollConstants.GROSS_SALARY_REQUIRED);
        }
        if (request.getDeductions() == null){
            log.error("--> ERROR: ".concat(PayrollConstants.DEDUCTIONS_REQUIRED));
            errors.add(PayrollConstants.DEDUCTIONS_REQUIRED);
        }
        if (request.getNetSalary() == null){
            log.error("--> ERROR: ".concat(PayrollConstants.NET_SALARY_REQUIRED));
            errors.add(PayrollConstants.NET_SALARY_REQUIRED);
        }
        if (request.getSocialSalaryDeductions() == null){
            log.error("--> ERROR: ".concat(PayrollConstants.SOCIAL_SALARY_DEDUCTIONS_REQUIRED));
            errors.add(PayrollConstants.SOCIAL_SALARY_DEDUCTIONS_REQUIRED);
        }
        if (request.getHealthInsuranceDeductions() == null){
            log.error("--> ERROR: ".concat(PayrollConstants.HEALTH_INSURANCE_DEDUCTIONS_REQUIRED));
            errors.add(PayrollConstants.HEALTH_INSURANCE_DEDUCTIONS_REQUIRED);
        }
        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }
    }
}
