package com.caito.departamentsertvice.api.exceptions.customs;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
