package com.example.RPM9._0.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such products")
public class ResponseServerIfNotSucces {
}
