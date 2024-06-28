package com.caito.employeesservice.utils.mappers;

import com.caito.employeesservice.api.models.requests.EmployeeRequest;
import com.caito.employeesservice.api.models.responses.EmployeeListResponse;
import com.caito.employeesservice.api.models.responses.EmployeeResponse;
import com.caito.employeesservice.persistence.entities.Employee;

public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeRequest request){
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .hiredDate(request.getHiredDate())
                .position(request.getPosition())
                .seniority(request.getSeniority())
                .salary(request.getSalary())
                .status(request.getStatus())
                .socialSecurityNumber(request.getSocialSecurityNumber())
                .bank(request.getBank())
                .accountNumber(request.getAccountNumber())
                .accountType(request.getAccountType())
                .build();
    }

    public static EmployeeResponse mapToDto(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dateOfBirth(employee.getDateOfBirth())
                .gender(employee.getGender())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .hiredDate(employee.getHiredDate())
                .position(employee.getPosition())
                .seniority(employee.getSeniority())
                .salary(employee.getSalary())
                .status(employee.getStatus())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .bank(employee.getBank())
                .accountNumber(employee.getAccountNumber())
                .accountType(employee.getAccountType())
                .build();
    }

    public static EmployeeListResponse mapListToDto(Employee employee){
        return EmployeeListResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dateOfBirth(employee.getDateOfBirth())
                .gender(employee.getGender())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .hiredDate(employee.getHiredDate())
                .position(employee.getPosition())
                .seniority(employee.getSeniority())
                .salary(employee.getSalary())
                .status(employee.getStatus())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .bank(employee.getBank())
                .accountNumber(employee.getAccountNumber())
                .accountType(employee.getAccountType())
                .build();
    }
}
