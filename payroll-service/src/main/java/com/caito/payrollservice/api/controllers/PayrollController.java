package com.caito.payrollservice.api.controllers;

import com.caito.payrollservice.api.models.requests.PayrollRequest;
import com.caito.payrollservice.api.models.responses.PayrollListResponse;
import com.caito.payrollservice.api.models.responses.PayrollResponse;
import com.caito.payrollservice.services.contracts.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payroll")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "RRHH - Payroll" , description = "Payroll management")
public class PayrollController {
    private final PayrollService payrollService;

    @PostMapping("/create")
    @Operation(summary = "endpoint creation of payroll")
    @Parameter(name = "request", description = "payroll details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<?> createPayroll(@RequestBody PayrollRequest request){
        log.info("--> POST: / endpoint creation of payroll");
        payrollService.createPayroll(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "endpoint query of payroll by id")
    @Parameter(name = "id", description = "payroll id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<PayrollResponse> getPayroll(@PathVariable Long id){
        log.info("--> GET: / endpoint query of payroll by id");
        return ResponseEntity.ok(payrollService.getPayroll(id));
    }

    @GetMapping("/employee/{id}")
    @Operation(summary = "endpoint query of payroll by employee")
    @Parameter(name = "id", description = "emplyee id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<PayrollListResponse> getByEmplyee(@PathVariable Long id){
        log.info("--> GET: / endpoint query of payroll by employee");
        return ResponseEntity.ok(payrollService.getPayrolls(id));
    }
}
