package com.example.booksAPI.helpers;

import java.util.HashMap;
import java.util.Map;

public class BuildResponse {

    public static Map<String, Object> buildHTTPResponse(Integer status, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}
