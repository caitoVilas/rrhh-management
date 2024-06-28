package com.caito.departamentsertvice.services.impl;

import com.caito.departamentsertvice.services.contracts.HttpClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpClientServiceImpl implements HttpClientService {
    private final RestTemplate restTemplate;

    @Override
    public <T> T doGet(String endpoint, Class<T> responseType) {
        HttpEntity httpEntity = new HttpEntity<>(this.getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.GET,httpEntity,responseType);
        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            log.error("--> Error in doGet");
            throw new RuntimeException("Error in doGet");
        }
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
