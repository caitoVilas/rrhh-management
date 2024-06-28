package com.caito.departamentsertvice.api.exceptions.handlers;

import com.caito.departamentsertvice.api.exceptions.customs.BadRequestException;
import com.caito.departamentsertvice.api.models.responses.ExceptionResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionController {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ExceptionResponses> badRequestHandler(BadRequestException e,
                                                                   HttpServletRequest request){
        var response = ExceptionResponses.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .messages(e.getMessages())
                .method(request.getMethod())
                .path(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
