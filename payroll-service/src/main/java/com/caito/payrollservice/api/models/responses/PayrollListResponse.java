package com.caito.payrollservice.api.models.responses;

import com.caito.payrollservice.api.models.externals.EmployeeResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class PayrollListResponse {
    private EmployeeResponse employee;
    private List<PayrollSimpleResponse> payrolls;
}
