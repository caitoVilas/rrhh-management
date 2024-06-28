package com.caito.employeesservice.api.exceptions.customs;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    private List<String> messages;
    public BadRequestException(List<String> messages) {
        this.messages = messages;
    }
}
