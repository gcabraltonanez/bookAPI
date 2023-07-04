package com.example.booksAPI.helpers;

import java.util.HashMap;
import java.util.Map;

public class BuildResponse {

    public static Map<String, Object> buildHTTPResponse(String key, Object value, String message){
        Map<String, Object> response = new HashMap<>();
        response.put(key, value);
        response.put("message", message);
        return response;
    }
}
