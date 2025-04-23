package com.example.RPM9._0.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {
    public ProductNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}