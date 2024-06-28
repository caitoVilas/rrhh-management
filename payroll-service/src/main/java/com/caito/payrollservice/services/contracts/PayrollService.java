package com.caito.payrollservice.services.contracts;

import com.caito.payrollservice.api.models.requests.PayrollRequest;
import com.caito.payrollservice.api.models.responses.PayrollListResponse;
import com.caito.payrollservice.api.models.responses.PayrollResponse;

public interface PayrollService {
    void createPayroll(PayrollRequest request);
    PayrollResponse getPayroll(Long id);
    PayrollListResponse getPayrolls(Long id);

}
