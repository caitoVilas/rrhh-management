package com.caito.departamentsertvice.api.exceptions.customs;

public class ApiErrorException extends RuntimeException {
    public ApiErrorException(String message) {
        super(message);
    }
}
