package com.caito.departamentsertvice.services.contracts;

import java.util.Map;

public interface HttpClientService {
    <T> T doGet(String endpoint, Class<T> responseType);
}
