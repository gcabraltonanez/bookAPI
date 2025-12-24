package com.example.booksAPI.dto;

import java.util.Map;

public record ApiError(String message, Map<String, String> details) {
    public ApiError(String message) {
        this(message, null);
    }
}
