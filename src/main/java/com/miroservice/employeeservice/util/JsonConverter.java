package com.miroservice.employeeservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    // Serialize an object to JSON
    public static String convertToJson(Object object) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }

    // Deserialize JSON to an object of a specified class
    public static <T> T convertFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }
}
