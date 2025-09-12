package com.jessicamorin.urlshortener.exception;

import lombok.Data;

@Data
public class ApiError {

    private int status;
    private String message;
    private long timestamp = System.currentTimeMillis();

    public ApiError(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiError(int status, String message) {
        this(status, message, System.currentTimeMillis());
    }
}
