package com.caito.payrollservice.api.exceptions.handlers;

import com.caito.payrollservice.api.exceptions.customs.ComunicationException;
import com.caito.payrollservice.api.models.responses.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ComunicationExceptionController {

    @ExceptionHandler(ComunicationException.class)
    protected ResponseEntity<ExceptionResponse> cominicationHandler(ComunicationException ex,
                                                                    HttpServletRequest request) {
       var response = ExceptionResponse.builder()
               .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
               .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
               .timestamp(LocalDateTime.now())
               .message(ex.getMessage())
               .method(request.getMethod())
                .path(request.getRequestURL().toString())
               .build();
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
