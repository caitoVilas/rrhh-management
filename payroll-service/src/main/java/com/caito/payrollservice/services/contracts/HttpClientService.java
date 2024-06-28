package com.caito.payrollservice.services.contracts;

public interface HttpClientService {
    <T> T doGet(String endpoint, Class<T> responseType);
}
