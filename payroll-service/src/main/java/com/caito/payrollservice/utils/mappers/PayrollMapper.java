package com.caito.payrollservice.utils.mappers;

import com.caito.payrollservice.api.models.externals.EmployeeResponse;
import com.caito.payrollservice.api.models.requests.PayrollRequest;
import com.caito.payrollservice.api.models.responses.PayrollListResponse;
import com.caito.payrollservice.api.models.responses.PayrollResponse;
import com.caito.payrollservice.api.models.responses.PayrollSimpleResponse;
import com.caito.payrollservice.persistence.entities.PayRoll;

public class PayrollMapper {

    public static PayRoll mapToEntity(PayrollRequest request){
        return PayRoll.builder()
                .employeeId(request.getEmployeeId())
                .payDate(request.getPayDate())
                .periodStart(request.getPeriodStart())
                .periodEnd(request.getPeriodEnd())
                .grossSalary(request.getGrossSalary())
                .deductions(request.getDeductions())
                .netSalary(request.getNetSalary())
                .bonuses(request.getBonuses())
                .taxes(request.getTaxes())
                .socialSalaryDeductions(request.getSocialSalaryDeductions())
                .healthInsuranceDeductions(request.getHealthInsuranceDeductions())
                .otherDeductions(request.getOtherDeductions())
                .comment(request.getComment())
                .build();
    }

    public static PayrollResponse mapToDto(PayRoll entity, EmployeeResponse employee){
        return PayrollResponse.builder()
                .id(entity.getId())
                .employee(employee)
                .payDate(entity.getPayDate())
                .periodStart(entity.getPeriodStart())
                .periodEnd(entity.getPeriodEnd())
                .grossSalary(entity.getGrossSalary())
                .deductions(entity.getDeductions())
                .netSalary(entity.getNetSalary())
                .bonuses(entity.getBonuses())
                .taxes(entity.getTaxes())
                .socialSalaryDeductions(entity.getSocialSalaryDeductions())
                .healthInsuranceDeductions(entity.getHealthInsuranceDeductions())
                .otherDeductions(entity.getOtherDeductions())
                .comment(entity.getComment())
                .build();
    }

    public static PayrollSimpleResponse mapToDtotoList(PayRoll entity){
        return PayrollSimpleResponse.builder()
                .id(entity.getId())
                .payDate(entity.getPayDate())
                .periodStart(entity.getPeriodStart())
                .periodEnd(entity.getPeriodEnd())
                .grossSalary(entity.getGrossSalary())
                .deductions(entity.getDeductions())
                .netSalary(entity.getNetSalary())
                .bonuses(entity.getBonuses())
                .taxes(entity.getTaxes())
                .socialSalaryDeductions(entity.getSocialSalaryDeductions())
                .healthInsuranceDeductions(entity.getHealthInsuranceDeductions())
                .otherDeductions(entity.getOtherDeductions())
                .comment(entity.getComment())
                .build();
    }

}
