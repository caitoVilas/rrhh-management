package com.caito.employeesservice.api.controllers;

import com.caito.employeesservice.api.models.requests.EmployeeRequest;
import com.caito.employeesservice.api.models.responses.EmployeeListResponse;
import com.caito.employeesservice.api.models.responses.EmployeeResponse;
import com.caito.employeesservice.services.contracts.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "RRHH - Employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    @Operation(summary = "endpoint creation of employee")
    @Parameter(name = "request", description = "employee details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest request){
        log.info("--> POST: / endpoint creation of employee");
        employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "endpoint search of employee by id")
    @Parameter(name = "id", description = "employee id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<EmployeeResponse> findEmployee(@PathVariable("id") Long id){
        log.info("--> GET / endpoint find employees by id");
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "endpoint search all employees")
    @Parameters({
            @Parameter(name = "page", description = "page to show"),
            @Parameter(name = "size", description = "number of elements to display")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<Page<EmployeeListResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        log.info("--> GET / endpoint get all employees ");
        Page<EmployeeListResponse> employees = employeeService.getAll(page, size);
        if (employees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/name")
    @Operation(summary = "endpoint search employees by name")
    @Parameter(name = "name", description = "name of employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<EmployeeListResponse>> getByName(@RequestParam String name){
        log.info("--> GET / endpoint get amployees by name");
        return ResponseEntity.ok(employeeService.getByName(name));
    }
}
