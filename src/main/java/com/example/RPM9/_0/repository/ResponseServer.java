package com.example.RPM9._0.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ResponseServer {

    public boolean isSuccess;
    public HttpStatusCode statusCode;
    public List<String> errorMessages;
    public Object result;

    public ResponseServer(boolean isSuccess, HttpStatusCode statusCode, List<String> errorMessages, Object result) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.errorMessages = errorMessages;
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
