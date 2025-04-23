package com.example.RPM9._0.repository;

public enum HttpStatusCode {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
