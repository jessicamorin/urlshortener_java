package com.jessicamorin.urlshortener.dto;

import lombok.Data;
import java.util.List;

@Data
public class ApiResponse<T> {
    private T data;
    private int code;
    private List<String> errors;

    public ApiResponse(T data, int code, List<String> errors) {
        this.data = data;
        this.code = code;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, 200, List.of());
    }

    public static <T> ApiResponse<T> error(List<String> errors, int code) {
        return new ApiResponse<>(null, code, errors);
    }
}
