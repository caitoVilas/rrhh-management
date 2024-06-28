package com.caito.departamentsertvice.api.exceptions.handlers;

import com.caito.departamentsertvice.api.exceptions.customs.ApiErrorException;
import com.caito.departamentsertvice.api.models.responses.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiErrorExcepcionController {

    @ExceptionHandler(ApiErrorException.class)
    protected ResponseEntity<ExceptionResponse> apiErrorHandler(ApiErrorException e,
                                                                HttpServletRequest request) {
        var response = ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
