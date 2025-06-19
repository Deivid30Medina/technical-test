package com.zemsania.technical_test.config.exception;

public class ExternalApiException extends RuntimeException{
    private final int statusCode;

    public ExternalApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
