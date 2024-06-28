package com.caito.departamentsertvice.api.controllers;

import com.caito.departamentsertvice.api.models.requests.DepartamentRequest;
import com.caito.departamentsertvice.api.models.responses.DepartamentListResponse;
import com.caito.departamentsertvice.api.models.responses.DepartamentResponse;
import com.caito.departamentsertvice.services.contracts.DepartamentService;
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
@RequestMapping("/departament")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "RRHH - Departament")
public class DepartamentController {
    private final DepartamentService departamentService;

    @PostMapping("/create")
    @Operation(summary = "endpoint department creation")
    @Parameter(name = "request", description = "department details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<?> createDepartament(@RequestBody DepartamentRequest request){
        log.info("--> POST endpoint create departament");
        departamentService.createDepartament(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{departamentId}")
    @Operation(summary = "endpoint find department by id")
    @Parameter(name = "departamentId", description = "department Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<DepartamentResponse> getById(@PathVariable Long departamentId){
        log.info("--> GET endpoint find departament by Id");
        return ResponseEntity.ok(departamentService.getById(departamentId));
    }

    @GetMapping("/name")
    @Operation(summary = "endpoint find department by name")
    @Parameter(name = "name", description = "department name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<DepartamentListResponse>> getByName(@RequestParam String name){
        log.info("--> GET / endpoint get departament by name");
        return ResponseEntity.ok(departamentService.getByName(name));
    }

    @GetMapping("/all")
    @Operation(summary = "endpoint find all departments")
    @Parameters({
            @Parameter(name = "page", description = "page to show"),
            @Parameter(name = "size", description = "number of elements to display")
    })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<Page<DepartamentListResponse>> getAll(@RequestParam(defaultValue = "0")int page,
                                                                @RequestParam(defaultValue = "10") int size){
        log.info("--> GET / endpoint get all departaments");
        var response = departamentService.getAll(page, size);
        if (response.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }
}
